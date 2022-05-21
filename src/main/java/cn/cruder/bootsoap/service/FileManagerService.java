package cn.cruder.bootsoap.service;

import cn.cruder.bootsoap.enums.DownloadFileEnum;
import cn.cruder.bootsoap.enums.UploadFileEnum;
import cn.cruder.bootsoap.repository.FileRepository;
import cn.cruder.bootsoap.soap.download.DownloadFileRequest;
import cn.cruder.bootsoap.soap.download.DownloadFileResponse;
import cn.cruder.bootsoap.soap.download.DownloadFileResult;
import cn.cruder.bootsoap.soap.upload.UploadFileRequest;
import cn.cruder.bootsoap.soap.upload.UploadFileResponse;
import cn.cruder.bootsoap.soap.upload.UploadFileResult;
import cn.cruder.bootsoap.util.FileStorageUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

/**
 * @author dousx
 * @date 2022-05-21 16:51
 */
@Service
@Slf4j
@AllArgsConstructor
public class FileManagerService {
    private final FileRepository fileRepository;

    /**
     * 上传文件
     *
     * @param request {@link UploadFileRequest}
     * @return {@link UploadFileResponse}
     */
    public UploadFileResponse uploadFile(UploadFileRequest request) {
        UploadFileResponse response = new UploadFileResponse();
        try {
            String storagePath = FileStorageUtil.storagePath();
            String filePath = storagePath + File.separator + request.getName();
            FileUtil.writeBytes(Base64.decode(request.getFile()), filePath);
            log.info("filePath:{}", filePath);
            log.info("request.getName():{}", request.getName());
            UploadFileResult uploadFileResult = new UploadFileResult();
            uploadFileResult.setName(filePath);
            File file = new File(filePath);
            String fileRef = UUID.randomUUID().toString();
            fileRepository.saveFilePath(fileRef, filePath);
            uploadFileResult.setFileRef(fileRef);
            uploadFileResult.setSize(FileUtil.size(file));
            response.setCode(UploadFileEnum.SUCCESS.code());
            response.setResult(uploadFileResult);
        } catch (IORuntimeException e) {
            log.error("uploadFile error \r\nrequest.name:{}\nrequest.file:{}", request.getName(), request.getFile(), e);
            response.setCode(UploadFileEnum.ERROR.code());
            response.setResult(new UploadFileResult());
        }
        return response;
    }

    /**
     * 下载文件
     *
     * @param request {@link DownloadFileRequest}
     * @return {@link DownloadFileResponse}
     */
    public DownloadFileResponse downloadFile(DownloadFileRequest request) {
        DownloadFileResponse downloadFileResponse = new DownloadFileResponse();
        try {
            String fileRef = request.getFileRef();
            String filePath = fileRepository.loadFile(fileRef);
            if (!FileUtil.exist(filePath)) {
                downloadFileResponse.setCode(DownloadFileEnum.FILE_NOT_FOUND.code());
                downloadFileResponse.setResult(new DownloadFileResult());
                return downloadFileResponse;
            } else {
                String encode = Base64.encode(FileUtil.readBytes(filePath));
                DownloadFileResult downloadFileResult = new DownloadFileResult();
                downloadFileResult.setFile(encode);
                downloadFileResult.setName(FileUtil.getName(filePath));
                downloadFileResult.setSize(FileUtil.size(new File(filePath)));
                downloadFileResponse.setCode(DownloadFileEnum.SUCCESS.code());
                downloadFileResponse.setResult(downloadFileResult);
            }
        } catch (IORuntimeException e) {
            downloadFileResponse.setCode(DownloadFileEnum.ERROR.code());
            downloadFileResponse.setResult(new DownloadFileResult());
            log.error("uploadFile error ,request.fileRef: {}", request.getFileRef(), e);
        }
        return downloadFileResponse;
    }
}

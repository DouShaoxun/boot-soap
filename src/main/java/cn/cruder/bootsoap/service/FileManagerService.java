package cn.cruder.bootsoap.service;

import cn.cruder.bootsoap.repository.FileRepository;
import cn.cruder.bootsoap.soap.download.DownloadFileRequest;
import cn.cruder.bootsoap.soap.download.DownloadFileResponse;
import cn.cruder.bootsoap.soap.upload.UploadFileRequest;
import cn.cruder.bootsoap.soap.upload.UploadFileResponse;
import cn.cruder.bootsoap.util.FileStorageUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
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
        String storagePath = FileStorageUtil.storagePath();
        String filePath = storagePath + File.separator + request.getName();
        FileUtil.writeBytes(Base64.decode(request.getFile()), filePath);
        log.info("filePath:{}", filePath);
        log.info("request.getName():{}", request.getName());
        response.setName(filePath);
        File file = new File(filePath);
        String fileRef = UUID.randomUUID().toString();
        fileRepository.saveFilePath(fileRef, filePath);
        response.setFileRef(fileRef);
        response.setSize(FileUtil.size(file));
        return response;
    }

    /**
     * 下载文件
     *
     * @param request {@link DownloadFileRequest}
     * @return {@link DownloadFileResponse}
     */
    public DownloadFileResponse downloadFile(DownloadFileRequest request) {
        String fileRef = request.getFileRef();
        String filePath = fileRepository.loadFile(fileRef);
        DownloadFileResponse downloadFileResponse = new DownloadFileResponse();
        if (FileUtil.exist(filePath)) {
            String encode = Base64.encode(FileUtil.readBytes(filePath));
            downloadFileResponse.setFile(encode);
            downloadFileResponse.setName(FileUtil.getName(filePath));
            downloadFileResponse.setSize(FileUtil.size(new File(filePath)));
        } else {
            // TODO: 2022.05.21 应该有一个状态码
            downloadFileResponse.setFile("");
        }
        return downloadFileResponse;
    }
}

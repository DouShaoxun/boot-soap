package cn.cruder.bootsoap.wb;

import cn.cruder.bootsoap.constant.SoapConstants;
import cn.cruder.bootsoap.soap.download.DownloadFileRequest;
import cn.cruder.bootsoap.soap.download.DownloadFileResponse;
import cn.cruder.bootsoap.soap.upload.UploadFileRequest;
import cn.cruder.bootsoap.soap.upload.UploadFileResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author dousx
 * @date 2022-05-23 21:30
 */
@WebService(name = FileManagerWebService.NAME,
        targetNamespace = SoapConstants.NAME_SPACE)
public interface FileManagerWebService {

    String NAME = "fileManagerWebService";

    /**
     * 上传文件
     *
     * @param request {@link UploadFileRequest}
     * @return {@link UploadFileResponse}
     */
    @WebMethod
    UploadFileResponse uploadFile(UploadFileRequest request);

    /**
     * 下载文件
     *
     * @param request {@link DownloadFileRequest}
     * @return {@link DownloadFileResponse}
     */
    @WebMethod
    DownloadFileResponse downloadFile(DownloadFileRequest request);
}

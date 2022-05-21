package cn.cruder.bootsoap.endpoint;

import cn.cruder.bootsoap.service.FileManagerService;
import cn.cruder.bootsoap.soap.download.DownloadFileRequest;
import cn.cruder.bootsoap.soap.download.DownloadFileResponse;
import cn.cruder.bootsoap.soap.upload.UploadFileRequest;
import cn.cruder.bootsoap.soap.upload.UploadFileResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * @author dousx
 * @date 2022-05-20 11:06
 */
@Endpoint
@Slf4j
@AllArgsConstructor
public class FileManagerEndpoint {

    private static final String NAMESPACE_URI = "cn.cruder.bootsoap.namespace";


    private final FileManagerService fileManagerService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "uploadFileRequest")
    @ResponsePayload
    public UploadFileResponse uploadFile(@RequestPayload UploadFileRequest request) {
        return fileManagerService.uploadFile(request);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "downloadFileRequest")
    @ResponsePayload
    public DownloadFileResponse downloadFile(@RequestPayload DownloadFileRequest request) {
        return fileManagerService.downloadFile(request);
    }


}
package cn.cruder.bootsoap.endpoint;

import cn.cruder.bootsoap.soap.upload.UploadFileRequest;
import cn.cruder.bootsoap.soap.upload.UploadFileResponse;
import cn.cruder.bootsoap.util.FileStorageUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.File;

/**
 * @author dousx
 * @date 2022-05-20 11:06
 */
@Endpoint
@Slf4j
public class FileManagerEndpoint {

    private static final String NAMESPACE_URI = "cn.cruder.bootsoap.namespace";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "uploadFileRequest")
    @ResponsePayload
    public UploadFileResponse uploadFile(@RequestPayload UploadFileRequest request) {
        UploadFileResponse response = new UploadFileResponse();
        log.info("storagePath:{}", FileStorageUtil.storagePath());
        log.info("request.getName():{}", request.getName());
        response.setName("file---name");
        response.setFileRef("setFileRef--");
        response.setSize(100);
        return response;
    }
}
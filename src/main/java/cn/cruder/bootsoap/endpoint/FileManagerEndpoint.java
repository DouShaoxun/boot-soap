package cn.cruder.bootsoap.endpoint;

import cn.cruder.bootsoap.soap.upload.UploadFileRequest;
import cn.cruder.bootsoap.soap.upload.UploadFileResponse;
import cn.cruder.bootsoap.util.FileStorageUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.File;
import java.util.Date;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_MS_FORMAT;

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
        String storagePath = FileStorageUtil.storagePath();
        String filePath = storagePath + File.separator + request.getName();
        FileUtil.writeBytes(Base64.decode(request.getFile()), filePath);
        log.info("filePath:{}", filePath);
        log.info("request.getName():{}", request.getName());
        response.setName(filePath);
        File file = new File(filePath);
        // 下载地址 todo
        response.setFileRef("setFileRef--");
        response.setSize(FileUtil.size(file));
        return response;
    }
}
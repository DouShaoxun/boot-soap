package cn.cruder.bootsoap.endpoint;

import cn.cruder.bootsoap.soap.upload.UploadFileRequest;
import cn.cruder.bootsoap.soap.upload.UploadFileResponse;
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
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "uploadFileRequest")
    @ResponsePayload
    public UploadFileResponse getCountry(@RequestPayload UploadFileRequest request) {
        UploadFileResponse response = new UploadFileResponse();

        String property = System.getProperty("user.dir");
        log.info("user.dir:{}", property);
        log.info("request.getName():{}", request.getName());
        response.setName("file---name");
        response.setFileRef("setFileRef--");
        response.setSize(100);
        return response;
    }
}
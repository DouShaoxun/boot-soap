package cn.cruder.bootsoap;

import cn.cruder.bootsoap.util.FileStorageUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
class BootSoapApplicationTests {
    private static final String SERVER_URL = "http://localhost:8678/ws";
    private static final String CONTENT_TYPE = "text/xml; charset=utf-8";

    @Test
    void contextLoads() throws IOException {
        String fileName = "Docker可视化工具LazyDocker.md";
        String filePath = FileStorageUtil.testDir() + fileName;
        log.info("test file path:{}", filePath);
        // 上传时编码
        String uploadFileEncode = Base64.encode(FileUtil.readBytes(filePath));
        String fileRef = uploadFile(fileName, uploadFileEncode);
        String downloadFileEncode = downloadFile(fileRef);
        log.info("uploadFileEncode   : {}", uploadFileEncode);
        log.info("downloadFileEncode : {}", downloadFileEncode);
        log.info("org.apache.commons.lang3.StringUtils.equals(uploadFileEncode, downloadFileEncode): {}", org.apache.commons.lang3.StringUtils.equals(uploadFileEncode, downloadFileEncode));

    }

    private String uploadFile(String fileName, String uploadFileEncode) throws IOException {
        MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
        String content = getUploadFileRequestContent(fileName, uploadFileEncode);
        RequestBody requestBody = RequestBody.create(mediaType, content);
        Response response = getResponse(requestBody);
        if (response.isSuccessful() && response.code() == HttpStatus.HTTP_OK) {
            String resultXml = new String(response.body().bytes());
            log.info("upload success ... \r\n{}", XmlUtil.format(resultXml));
            JSONObject entries = XML.toJSONObject(resultXml);
            JSONObject envelopeJ = (JSONObject) entries.get("SOAP-ENV:Envelope");
            JSONObject bodyJ = (JSONObject) envelopeJ.get("SOAP-ENV:Body");
            JSONObject uploadFileResponse = (JSONObject) bodyJ.get("ns2:uploadFileResponse");
            String fileRef = (String) uploadFileResponse.get("ns2:fileRef");
            log.info("fileRef:{}", fileRef);
            return fileRef;
        } else {
            throw new RuntimeException("request error,code:" + response.code());
        }
    }

    private String downloadFile(String fileRef) throws IOException {
        MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
        String content = getDownloadFileRequestContent(fileRef);
        RequestBody requestBody = RequestBody.create(mediaType, content);
        Response response = getResponse(requestBody);
        if (response.isSuccessful() && response.code() == HttpStatus.HTTP_OK) {
            String resultXml = new String(response.body().bytes());
            log.info("download success ... \r\n{}", XmlUtil.format(resultXml));
            JSONObject entries = XML.toJSONObject(resultXml);
            JSONObject envelopeJ = (JSONObject) entries.get("SOAP-ENV:Envelope");
            JSONObject bodyJ = (JSONObject) envelopeJ.get("SOAP-ENV:Body");
            JSONObject uploadFileResponse = (JSONObject) bodyJ.get("ns2:downloadFileResponse");
            String downloadFileEncode = (String) uploadFileResponse.get("ns2:file");
            log.info("fileRef:{}", fileRef);
            return downloadFileEncode;
        } else {
            throw new RuntimeException("request error,code:" + response.code());
        }
    }

    /**
     * 发送请求
     *
     * @param requestBody
     * @return
     * @throws IOException
     */
    @NotNull
    private Response getResponse(RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .method("POST", requestBody)
                .addHeader("Content-Type", CONTENT_TYPE)
                .build();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Response response = client.newCall(request).execute();
        return response;
    }

    /**
     * todo 模板填充
     *
     * @param fileName 文件名
     * @param encode   base64编码后的字符串
     * @return
     */
    @NotNull
    private String getUploadFileRequestContent(String fileName, String encode) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                "\r\nxmlns:gs=\"cn.cruder.bootsoap.namespace\">" +
                "\r\n<soapenv:Header/>\r\n<soapenv:Body>" +
                "\r\n<gs:uploadFileRequest>" +
                "\r\n<gs:name>" + fileName + "</gs:name>" +
                "\r\n <gs:file>" + encode + "</gs:file>" +
                "\r\n </gs:uploadFileRequest>" +
                "\r\n </soapenv:Body>" +
                "\r\n</soapenv:Envelope>";
    }


    @NotNull
    private String getDownloadFileRequestContent(String fileRef) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                "\r\nxmlns:gs=\"cn.cruder.bootsoap.namespace\">" +
                "\r\n<soapenv:Header/>" +
                "\r\n<soapenv:Body>" +
                "\r\n<gs:downloadFileRequest>" +
                "\r\n<gs:fileRef>" + fileRef + "</gs:fileRef>" +
                "\r\n</gs:downloadFileRequest>" +
                "\r\n</soapenv:Body>" +
                "\n</soapenv:Envelope>";
    }


}

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
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@Slf4j
class EndpointTests {
    private static final String SERVER_URL = "http://localhost:8678/ws";
    private static final String CONTENT_TYPE = "text/xml; charset=utf-8";
    private static final SAXReader SAX_READER = new SAXReader();
    private static final String downloadFileTemplatePath = FileStorageUtil.testDir() + "template" + File.separator + "download-file-template.xml";
    private static final String uploadFileTemplatePath = FileStorageUtil.testDir() + "template" + File.separator + "upload-file-template.xml";

    @Test
    void fileManagerEndpointTest() throws IOException, DocumentException {
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

    private String uploadFile(String fileName, String uploadFileEncode) throws IOException, DocumentException {
        MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
        String content = getUploadFileRequestContent(fileName, uploadFileEncode);
        log.info("uploadFile requestBody ... \r\n{}", XmlUtil.format(content));
        RequestBody requestBody = RequestBody.create(mediaType, content);
        Response response = getResponse(requestBody);
        if (response.isSuccessful() && response.code() == HttpStatus.HTTP_OK) {
            String resultXml = new String(response.body().bytes());
            log.info("upload success ... \r\n{}", XmlUtil.format(resultXml));
            JSONObject entries = XML.toJSONObject(resultXml);
            JSONObject envelopeJ = (JSONObject) entries.get("SOAP-ENV:Envelope");
            JSONObject bodyJ = (JSONObject) envelopeJ.get("SOAP-ENV:Body");
            JSONObject uploadFileResponse = (JSONObject) bodyJ.get("ns2:uploadFileResponse");
            JSONObject result = (JSONObject) uploadFileResponse.get("ns2:result");
            String fileRef = (String) result.get("ns2:fileRef");
            Integer code = (Integer) uploadFileResponse.get("ns2:code");
            log.info("code:{} ,fileRef:{}", code, fileRef);
            return fileRef;
        } else {
            throw new RuntimeException("request error,code:" + response.code());
        }
    }

    private String downloadFile(String fileRef) throws IOException, DocumentException {
        MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
        String content = getDownloadFileRequestContent(fileRef);
        log.info("downloadFile requestBody ... \r\n{}", XmlUtil.format(content));
        RequestBody requestBody = RequestBody.create(mediaType, content);
        Response response = getResponse(requestBody);
        if (response.isSuccessful() && response.code() == HttpStatus.HTTP_OK) {
            String resultXml = new String(response.body().bytes());
            log.info("download success ... \r\n{}", XmlUtil.format(resultXml));
            JSONObject entries = XML.toJSONObject(resultXml);
            JSONObject envelopeJ = (JSONObject) entries.get("SOAP-ENV:Envelope");
            JSONObject bodyJ = (JSONObject) envelopeJ.get("SOAP-ENV:Body");
            JSONObject uploadFileResponse = (JSONObject) bodyJ.get("ns2:downloadFileResponse");
            Integer code = (Integer) uploadFileResponse.get("ns2:code");
            JSONObject result = (JSONObject) uploadFileResponse.get("ns2:result");
            String downloadFileEncode = (String) result.get("ns2:file");
            log.info("code:{} ,fileRef:{}, downloadFileEncode:{}", code, fileRef, downloadFileEncode);
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
     * @param fileName 文件名
     * @param encode   base64编码后的字符串
     * @return
     */
    @NotNull
    private String getUploadFileRequestContent(String fileName, String encode) throws DocumentException {
        Document document = SAX_READER.read(new File(uploadFileTemplatePath));
        Element uploadFileRequest = document.getRootElement().element("Body").element("uploadFileRequest");
        // 模板填充
        Element name = uploadFileRequest.element("name");
        name.setText(fileName);
        Element file = uploadFileRequest.element("file");
        file.setText(encode);
        return document.asXML();
    }


    @NotNull
    private String getDownloadFileRequestContent(String fileRef) throws DocumentException {
        Document document = SAX_READER.read(new File(downloadFileTemplatePath));
        Element downloadFileRequest = document.getRootElement().element("Body").element("downloadFileRequest");
        // 模板填充
        Element fileRefElement = downloadFileRequest.element("fileRef");
        fileRefElement.setText(fileRef);
        return document.asXML();
    }


}

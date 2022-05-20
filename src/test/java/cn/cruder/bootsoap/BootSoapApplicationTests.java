package cn.cruder.bootsoap;

import cn.cruder.bootsoap.util.FileStorageUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
@Slf4j
class BootSoapApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String fileName = "Docker可视化工具LazyDocker.md";
        String filePath = FileStorageUtil.testDir() + fileName;
        log.info("test file path:{}", filePath);
        String encode = Base64.encode(readBytesFromFile(filePath));
        MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
        String content = getContent(fileName, encode);

        System.out.println();
        System.out.println();
        System.out.println(content);

        System.out.println();
        System.out.println();
        RequestBody requestBody = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url("http://localhost:8678/ws")
                .method("POST", requestBody)
                .addHeader("Content-Type", "text/xml; charset=utf-8")
                .build();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Response response = client.newCall(request).execute();
        int code = response.code();
        if (code == HttpStatus.HTTP_OK) {
            // TODO: 2022.05.20 解析  xml格式
            ResponseBody responseBody = response.body();
        }
        log.info("end..");
    }

    /**
     * todo 模板填充
     *
     * @param fileName 文件名
     * @param encode   base64编码后的字符串
     * @return
     */
    @NotNull
    private String getContent(String fileName, String encode) {
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

    static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }
}

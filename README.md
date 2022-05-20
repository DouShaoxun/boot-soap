# spring-boot-soap

[参考](https://www.baeldung.com/spring-boot-soap-web-service)

## 测试

其中@powershell/request-param.xml是指定参数文件

powershell下执行`curl --header "content-type: text/xml; charset=utf-8" -d '@powershell/upload-file-request.xml' http://localhost:8678/ws`



```shell
curl --location --request POST 'http://localhost:8678/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'Cookie: JSESSIONID=FAB0E97A1DDAC224B0C72B5709B27B17' \
--data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="cn.cruder.bootsoap.namespace">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:uploadFileRequest>
            <gs:name>namessssss--</gs:name>
            <gs:file>s12sdfsdfdg3546564fdsf</gs:file>
        </gs:uploadFileRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```



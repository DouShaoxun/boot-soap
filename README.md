# spring-boot-soap

[参考](https://www.baeldung.com/spring-boot-soap-web-service)

## 测试

其中@powershell/request-param.xml是指定参数文件

powershell下执行`curl --header "content-type: text/xml; charset=utf-8" -d '@powershell/upload-file-request.xml' http://localhost:8678/ws`


`cn.cruder.bootsoap.EndpointTests.fileManagerEndpointTest`测试


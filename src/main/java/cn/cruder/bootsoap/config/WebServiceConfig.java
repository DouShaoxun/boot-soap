package cn.cruder.bootsoap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * @author dousx
 * @date 2022-05-20 11:06
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    /**
     * 创建一个用于处理 SOAP 请求的消息DispatcherServlet
     *
     * @param applicationContext ApplicationContext
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     * 创建一个 DefaultWsdl11Definition 对象。这公开了使用 XsdSchema 的标准 WSDL 1.1。WSDL 名称将与 Bean 名称相同。
     * @param xsdSchema
     * @return
     */
    @Bean
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("FilePort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("cn.cruder.bootsoap.namespace");
        definition.setSchema(xsdSchema);
        return definition;
    }

    @Bean
    public XsdSchema uploadSchema() {
        return new SimpleXsdSchema(new ClassPathResource("upload.xsd"));
    }
}
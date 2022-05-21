//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2022.05.21 时间 05:26:11 PM CST 
//


package cn.cruder.bootsoap.soap.download;



import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="result" type="{cn.cruder.bootsoap.namespace}downloadFileResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "code",
    "result"
})
@XmlRootElement(name = "downloadFileResponse")
public class DownloadFileResponse {

    protected long code;
    @XmlElement(required = true)
    protected DownloadFileResult result;

    /**
     * 获取code属性的值。
     * 
     */
    public long getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     */
    public void setCode(long value) {
        this.code = value;
    }

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DownloadFileResult }
     *     
     */
    public DownloadFileResult getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DownloadFileResult }
     *     
     */
    public void setResult(DownloadFileResult value) {
        this.result = value;
    }

}

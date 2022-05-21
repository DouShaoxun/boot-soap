package cn.cruder.bootsoap.soap.upload;

import javax.xml.bind.annotation.*;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "code",
        "result"
})
@XmlRootElement(name = "uploadFileResponse")
public class UploadFileResponse {

    protected long code;
    @XmlElement(required = true)
    protected UploadFileResult result;

    /**
     * 获取code属性的值。
     */
    public long getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     */
    public void setCode(long value) {
        this.code = value;
    }

    /**
     * 获取result属性的值。
     *
     * @return possible object is
     * {@link UploadFileResult }
     */
    public UploadFileResult getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     *
     * @param value allowed object is
     *              {@link UploadFileResult }
     */
    public void setResult(UploadFileResult value) {
        this.result = value;
    }

}

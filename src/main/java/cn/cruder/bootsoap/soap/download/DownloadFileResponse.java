package cn.cruder.bootsoap.soap.download;


import javax.xml.bind.annotation.*;


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
     * {@link DownloadFileResult }
     */
    public DownloadFileResult getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     *
     * @param value allowed object is
     *              {@link DownloadFileResult }
     */
    public void setResult(DownloadFileResult value) {
        this.result = value;
    }

}

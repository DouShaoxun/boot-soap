package cn.cruder.bootsoap.soap.download;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "fileRef"
})
@XmlRootElement(name = "downloadFileRequest")
public class DownloadFileRequest {

    @XmlElement(required = true)
    protected String fileRef;

    /**
     * 获取fileRef属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getFileRef() {
        return fileRef;
    }

    /**
     * 设置fileRef属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFileRef(String value) {
        this.fileRef = value;
    }

}

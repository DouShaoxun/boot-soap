package cn.cruder.bootsoap.soap.upload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadFileResult", propOrder = {
        "fileRef",
        "name",
        "size"
})
public class UploadFileResult {

    @XmlElement(required = true)
    protected String fileRef;
    @XmlElement(required = true)
    protected String name;
    protected long size;

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

    /**
     * 获取name属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取size属性的值。
     */
    public long getSize() {
        return size;
    }

    /**
     * 设置size属性的值。
     */
    public void setSize(long value) {
        this.size = value;
    }

}

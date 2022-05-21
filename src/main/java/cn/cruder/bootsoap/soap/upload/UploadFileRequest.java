package cn.cruder.bootsoap.soap.upload;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "file"
})
@XmlRootElement(name = "uploadFileRequest")
public class UploadFileRequest {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String file;

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
     * 获取file属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置file属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFile(String value) {
        this.file = value;
    }

}

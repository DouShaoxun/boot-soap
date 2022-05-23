package cn.cruder.bootsoap.soap.download;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadFileResult", propOrder = {
        "name",
        "file",
        "size"
})
public class DownloadFileResult {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String file;
    protected long size;

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

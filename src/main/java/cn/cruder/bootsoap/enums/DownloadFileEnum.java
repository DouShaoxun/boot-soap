package cn.cruder.bootsoap.enums;

/**
 * @author dousx
 * @date 2022-05-21 17:35
 */
public enum DownloadFileEnum {
    SUCCESS(200),
    /**
     * 未找到文件
     */
    FILE_NOT_FOUND(404),
    ERROR(500),
    ;

    DownloadFileEnum(Integer code) {
        this.code = code;
    }

    /**
     * 状态码
     */
    private Integer code;

    public Integer code() {
        return code;
    }
}
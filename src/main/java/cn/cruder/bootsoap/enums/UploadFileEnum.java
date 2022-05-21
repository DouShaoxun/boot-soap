package cn.cruder.bootsoap.enums;

/**
 * @author dousx
 * @date 2022-05-21 17:28
 */
public enum UploadFileEnum {
    SUCCESS(200),
    ERROR(500),
    ;

    UploadFileEnum(Integer code) {
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

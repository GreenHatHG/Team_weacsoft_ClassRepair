package team.weacsoft.classrepair.contests;

/**
 * @author GreenHatHG
 */
public enum ResultCodeEnum {

    //成功
    SUCCESS(0),

    //失败
    FAIL(400),

    //未认证（签名错误）
    UNAUTHORIZED(401),

    //接口不存在
    NOT_FOUND(404),

    //字段错误，不符合范围或者不符合要求
    PROPERTY_ERROR(412),

    //服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }
}

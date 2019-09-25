package team.weacsoft.classrepair.entity;

import lombok.Data;

/**
 * @author GreenHatHG
 */

@Data
public class Result {

    //响应状态码
    private int code;

    //响应提示信息
    private String message;

    //响应结果对象
    private Object data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}

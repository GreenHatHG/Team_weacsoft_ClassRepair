package team.weacsoft.classrepair.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author GreenHatHG
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {

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

package team.weacsoft.classrepair.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GreenHatHG
 */

@Data
@NoArgsConstructor
public class Result {

    //响应状态码
    private int code;

    //响应提示信息
    private String message;

    //响应结果对象
    private Object data;


    public void setAll(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}

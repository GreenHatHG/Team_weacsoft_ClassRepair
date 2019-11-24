package team.weacsoft.classrepair.commons.exception;

import lombok.Data;

/**
 * 自定义异常(CustomException)
 * @author GreenHatHG
 **/

@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }
}
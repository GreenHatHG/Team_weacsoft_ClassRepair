package team.weacsoft.common.exception;

import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 统一异常处理
 * @author GreenHatHG
 */

@Getter
public class BadRequestException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(int status, String msg){
        super(msg);
        this.status = status;
    }
}

package team.weacsoft.classrepair.commons.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 */

@Component
public class ResultFactory {
    private static final int success = 0;

    public static Result buildSuccessResult(Object data) {
        return new Result(success, "success", data);
    }

    public static Result buildSuccessResult(String message) {
        return new Result(success, message);
    }

    public static Result buildFailResult(String message) {
        return new Result(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static Result buildVNotFoundResult(String message) {
        return new Result(HttpStatus.NOT_FOUND.value(), message);
    }

    public static Result buildViolationFailResult(String message) {
        return new Result(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value(), message);
    }

    public static Result buildUnauthorizedResult(String message) {
        return new Result(HttpStatus.UNAUTHORIZED.value(), message);
    }

    public static Result buildCustomResult(int code, String message, Object data){
        return new Result(code, message, data);
    }
}

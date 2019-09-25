package team.weacsoft.classrepair.entity;

import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.contests.ResultCodeEnum;

/**
 * @author GreenHatHG
 */

@Component
public class ResultFactory {
    public static Result buildSuccessResult(Object data) {
        return new Result(ResultCodeEnum.SUCCESS.code, "成功", data);
    }

    public static Result buildSuccessResult(String message) {
        return new Result(ResultCodeEnum.SUCCESS.code, message);
    }

    public static Result buildFailResult(String message) {
        return new Result(ResultCodeEnum.FAIL.code, message);
    }

    public static Result builePropertyErroresult(String message){
        return new Result(ResultCodeEnum.PROPERTY_ERROR.code, message);
    }

    public static Result buildUnauthorizedResult(String message) {
        return new Result(ResultCodeEnum.UNAUTHORIZED.code, message);
    }


}

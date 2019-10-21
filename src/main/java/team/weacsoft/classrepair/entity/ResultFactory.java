package team.weacsoft.classrepair.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 */

@Component
public class ResultFactory {

    private static Result result = new Result();

    /* ----- SUCEESS -----*/
    public static ResponseEntity<Result> buildSuccessResult(Object data) {
        result.setAll(HttpStatus.OK.value(), "成功", data);
        return ResponseEntity.status(HttpStatus.OK.value()).body(result);
    }

    public static ResponseEntity<Result> buildSuccessResult(String message) {
        result.setAll(HttpStatus.OK.value(), message, null);
        return ResponseEntity.status(HttpStatus.OK.value()).body(result);
    }
    /* ----- SUCEESS -----*/

    /* ----- FAIL -----*/
    public static ResponseEntity<Result> buildFailResult(String message) {
        result.setAll(HttpStatus.BAD_REQUEST.value(), message, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(result);
    }

    public static ResponseEntity<Result> buildFailResult(int code, String message) {
        result.setAll(code, message, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(result);
    }

    public static ResponseEntity<Result> buildFailResult(String message, Object obj) {
        result.setAll(HttpStatus.BAD_REQUEST.value(), message, obj);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(result);
    }

    public static ResponseEntity<Result> buildFailResult(int code, String message, Object obj) {
        result.setAll(code, message, obj);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(result);
    }
    /* ----- FAIL -----*/

    public static ResponseEntity<Result> buildPropertyErroResult(String message){
        return buildFailResult(HttpStatus.PRECONDITION_FAILED.value(), message);
    }

    public static ResponseEntity<Result> buildNotAcceptableResult(String message, Object obj){
        return buildFailResult(HttpStatus.NOT_ACCEPTABLE.value(), message, obj);
    }

    public static ResponseEntity<Result> buildNotAcceptableResult(String message){
        return buildFailResult(HttpStatus.NOT_ACCEPTABLE.value(), message);
    }

    public static ResponseEntity<Result> buildFORBIDDENResult(String message){
        return buildFailResult(HttpStatus.FORBIDDEN.value(), message);
    }

}

package team.weacsoft.common.exception.handler;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * 接口返回封装
 * @author GreenHatHG
 */

@Data
public class ApiResp {
    private Integer errorCode = 0;
    private LocalDateTime timestamp;
    private String errormsg;
    private Object data;


    private ApiResp() {
        timestamp = LocalDateTime.now();
    }

    public static ApiResp error(String message){
        ApiResp apiError = new ApiResp();
        apiError.setErrorCode(400);
        apiError.setErrormsg(message);
        return apiError;
    }

    public static ApiResp error(Integer status, String message){
        ApiResp apiError = new ApiResp();
        apiError.setErrorCode(status);
        apiError.setErrormsg(message);
        return apiError;
    }

    public static ApiResp ok(){
        ApiResp apiOk = new ApiResp();
        apiOk.setErrorCode(0);
        return apiOk;
    }

    public static ResponseEntity<ApiResp> ok(Object data){
        ApiResp apiOk = new ApiResp();
        apiOk.setErrorCode(0);
        apiOk.setData(data);
        return new ResponseEntity<>(apiOk, HttpStatus.OK);
    }
}

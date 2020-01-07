package team.weacsoft.common.exception.handler;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * 接口返回封装
 * @author GreenHatHG
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResp {
    private Integer errorcode = 0;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime timestamp;
    private String errormsg;
    private Object data;

    private ApiResp() {
        timestamp = LocalDateTime.now();
    }

    public static ApiResp error(String message){
        ApiResp apiError = new ApiResp();
        apiError.setErrorcode(400);
        apiError.setErrormsg(message);
        return apiError;
    }

    public static ApiResp error(Integer status, String message){
        ApiResp apiError = new ApiResp();
        apiError.setErrorcode(status);
        apiError.setErrormsg(message);
        return apiError;
    }

    public static ApiResp ok(){
        ApiResp apiOk = new ApiResp();
        apiOk.setErrorcode(0);
        return apiOk;
    }

    public static ResponseEntity<ApiResp> ok(Object data){
        ApiResp apiOk = new ApiResp();
        apiOk.setErrorcode(0);
        apiOk.setData(data);
        return new ResponseEntity<>(apiOk, HttpStatus.OK);
    }
}

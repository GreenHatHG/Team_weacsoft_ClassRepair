package team.weacsoft.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityExistException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.utils.ThrowableUtil;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * 全局异常拦截
 * @author GreenHatHG
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiResp> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return new ResponseEntity<>(ApiResp.error(e.getStatus(),e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResp> nullPointerException(NullPointerException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return new ResponseEntity<>(ApiResp.error("发生空指针异常！请检查发送的请求"), BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResp> badRequestException(AccessDeniedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return new ResponseEntity<>(ApiResp.error(FORBIDDEN.value(),e.getMessage()), FORBIDDEN);
    }

    /**
     * 处理 EntityExist
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiResp> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiResp.error(e.getMessage()));
    }

    /**
     * 处理 EntityNotFound
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiResp> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiResp.error(NOT_FOUND.value(),e.getMessage()));
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ApiResp> unauthorizedException(UnauthorizedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiResp.error(UNAUTHORIZED.value(),e.getMessage()));
    }


    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<ApiResp> handle(ConstraintViolationException ex) {
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            String[] path = constraintViolation.getPropertyPath().toString().split("\\.");
            strBuilder.append(path[path.length - 1]).append(" ").append(constraintViolation.getMessage()).append("\n");
        }
        String result = strBuilder.toString();
        return buildResponseEntity(ApiResp.error(BAD_REQUEST.value(),
                "ConstraintViolation:" + result));
    }

    @ExceptionHandler(value = { BindException.class })
    protected ResponseEntity<ApiResp> handleBindException(BindException ex) {
        return buildResponseEntity(ApiResp.error(BAD_REQUEST.value(),
                "BindException:" + buildMessages(ex.getBindingResult())));
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<ApiResp> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return buildResponseEntity(ApiResp.error(BAD_REQUEST.value(),
                "MethodArgumentNotValid:" + buildMessages(ex.getBindingResult())));
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public ResponseEntity<ApiResp> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        return buildResponseEntity(ApiResp.error(BAD_REQUEST.value(),
                "ParamMissing:" + ex.getMessage()));
    }

    @ExceptionHandler(value = { TypeMismatchException.class })
    protected ResponseEntity<ApiResp> handleTypeMismatch(TypeMismatchException ex) {
        return buildResponseEntity(ApiResp.error(BAD_REQUEST.value(),
                "TypeMissMatch:" + ex.getMessage()));
    }

    private String buildMessages(BindingResult result) {
        StringBuilder resultBuilder = new StringBuilder();

        List<ObjectError> errors = result.getAllErrors();
        if (errors.size() > 0) {
            for (ObjectError error : errors) {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    String fieldName = fieldError.getField();
                    String fieldErrMsg = fieldError.getDefaultMessage();
                    resultBuilder.append(fieldName).append(fieldErrMsg).append(";");
                }
            }
        }
        return resultBuilder.toString();
    }

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResp> handleException(Throwable e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiResp.error(e.getMessage()));
    }

    /**
     * 统一返回
     */
    private ResponseEntity<ApiResp> buildResponseEntity(ApiResp apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getErrorcode()));
    }
}
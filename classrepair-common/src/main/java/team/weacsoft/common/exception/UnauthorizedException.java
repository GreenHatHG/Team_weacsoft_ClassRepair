package team.weacsoft.common.exception;

/**
 * @author GreenHatHG
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super("无权访问(Unauthorized):" + msg);
    }
}

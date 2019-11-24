package team.weacsoft.classrepair.commons.exception;

/**
 * @author GreenHatHG
 */
public class NotFoundException extends CustomException {
    public NotFoundException(String message) {
    super(404, message);
    }
}

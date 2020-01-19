package team.weacsoft.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author GreenHatHG
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entity, String field, String val) {
        super(StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist");
    }
}

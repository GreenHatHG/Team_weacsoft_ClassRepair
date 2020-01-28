package team.weacsoft.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

@Component
public class FieldFillConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Long.class, LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")), metaObject);
    }
}

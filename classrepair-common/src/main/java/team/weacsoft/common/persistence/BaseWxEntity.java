package team.weacsoft.common.persistence;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author GreenHatHG
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
public class BaseWxEntity extends BaseEntity implements Serializable {
    /**
     * openid是？
     */
    @JSONField(serialize=false)
    private String openid;
}

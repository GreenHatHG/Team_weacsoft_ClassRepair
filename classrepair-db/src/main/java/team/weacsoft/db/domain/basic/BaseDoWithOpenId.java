package team.weacsoft.db.domain.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public class BaseDoWithOpenId extends BaseDo {

    @JsonIgnore
    @Column(nullable = false, unique = true)
    public String openid;
}

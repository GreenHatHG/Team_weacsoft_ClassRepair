package team.weacsoft.classrepair.bean;

//todo redis

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 模板消息
 * status:
 * 1-未使用，2-已使用，3-已过期
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "form_tmp")
public class FormTmp extends AbstractBasicBean{

    @Column(nullable = false, unique = true)
    private int userId;

    /**
     * 模板消息formid
     */
    @Column(nullable = false, unique = true)
    private String formId;
}

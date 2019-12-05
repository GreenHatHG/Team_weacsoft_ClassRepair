package team.weacsoft.classrepair.bean.basic;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

//todo  统计分析表
//todo  故障设备类型表 存图标 默认为""

/**
 * 每个表都有的字段
 * @author GreenHatHG
 **/

@Data
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseBean {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**
     * 一般1为启用，0为停用，-1为删除,特殊情况除外
     */
    @Column(nullable = false)
    private int state;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Long createTime;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    private Long updateTime;

    /**
     * 删除时间
     */
    @Column(nullable = false)
    private Long deleteTime;

    @PrePersist
    protected void onCreate() {
        createTime = System.currentTimeMillis();
        updateTime = System.currentTimeMillis();
        deleteTime = (long)0;
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = System.currentTimeMillis();
    }
}

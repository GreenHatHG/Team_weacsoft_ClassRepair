package team.weacsoft.classrepair.bean;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

//todo  统计分析表
//todo  故障设备类型表 存图标 默认为null

/**
 * 每个表都有的字段
 * @author GreenHatHG
 **/

@Data
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
abstract class AbstractBasicBean{

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**
     * 一般1为启用，0为停用，特殊情况除外
     */
    @Column(nullable = false)
    private int status;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deleteTiome;
}

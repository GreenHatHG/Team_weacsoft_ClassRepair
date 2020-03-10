package team.weacsoft.feedback.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import team.weacsoft.common.persistence.BaseEntity;

/**
 * @Description 反馈表
 *
 * state中0代表已经处理，1代表未处理，默认为1
 *
 * @ClassName UserFeedback
 * @Author 魔法はまだ解けない
 * @date 2020.03.07 21:46
 */

@Data//代替了getter、setter、equals、hashcode、tostring...
@SuperBuilder//建造者模式的构造方法
@Accessors(chain = true)//set方法将返回本对象
public class UserFeedback extends BaseEntity{
    public UserFeedback(){
        super();
    }
    /**
     * 反馈人id
     */
    @Builder.Default//构造是允许使用默认值
    int orderer=0;

    /**
     * 处理人id
     */
    @Builder.Default
    int receiver=0;

    /**
     * 反馈人电话
     */
    @Builder.Default
    String orderPhone="0";

    /**
     * 反馈问题
     */

    String question;
}

package team.weacsoft.feedback.entity.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import team.weacsoft.common.persistence.BaseWxEntity;

/**
 * @Description 反馈推送消息模板
 * @ClassName PushFeedbackInf
 * @Author 魔法はまだ解けない
 * @date 2020.07.18 19:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
public class PushFeedbackInf extends BaseWxEntity {

    /**
     * 反馈人id
     */
    int orderer = 0;

    /**
     * 反馈人电话
     */
    String orderPhone = "0";

    /**
     * 反馈问题
     */
    String question;
}

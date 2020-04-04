package team.weacsoft.feedback.entity.dto.response;

import lombok.Data;

/**
 * @Description
 * @ClassName ManagerFeedbackResp
 * @Author 魔法はまだ解けない
 * @date 2020.03.10 13:36
 */
@Data
public class ManagerFeedbackResp{

    private int id;

    private String orderPhone;

    private String state;

    private String question;

    private String createTime;

    private String updateTime;

    private String deleteTime;

    private String ordererName;

    private String receiverName;

    private String feedbackId;
}

package team.weacsoft.timetable.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.weacsoft.common.persistence.BaseEntity;

/**
 * @Description 通讯录返回的工作人员类型
 * @ClassName ReceiverMailList
 * @Author 魔法はまだ解けない
 * @date 2020.04.06 14:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverMailList {
    /**
     * 姓名
     */
    private String name="";

    /**
     * 工作人员学号/工号
     */
    private long receiverIdentityId=0;

    /**
     * 工作人员电话
     */
    private String phone="";
}

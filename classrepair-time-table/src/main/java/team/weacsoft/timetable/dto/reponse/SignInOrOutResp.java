package team.weacsoft.timetable.dto.reponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author GreenHatHG
 * @since 2020-01-26
 */

@Getter
@Setter
@ToString
public class SignInOrOutResp {
    private Long createTime;
    private Long updateTime;
    private Integer state;
    private Integer userId;
}

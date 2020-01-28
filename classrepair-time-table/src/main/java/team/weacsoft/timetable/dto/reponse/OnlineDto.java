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
public class OnlineDto {
    private Long createTime;
    private Integer userId;
    private String name;
    private String phone;
    private Long identityId;
    private String nickName;
}

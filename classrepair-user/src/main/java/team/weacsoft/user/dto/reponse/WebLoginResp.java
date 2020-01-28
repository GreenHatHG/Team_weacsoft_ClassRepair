package team.weacsoft.user.dto.reponse;

import lombok.*;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebLoginResp {
    private String token;
}

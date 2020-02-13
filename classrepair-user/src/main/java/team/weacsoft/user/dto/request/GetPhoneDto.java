package team.weacsoft.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @since 2020-02-12
 */

@Data
public class GetPhoneDto {

    /**
     * 加密算法的初始向量
     */
    @NotBlank
    @Size(max=100)
    private String iv;

    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    @NotBlank
    @Size(max=100)
    private String encryptedData;

    /**
     * 为了获得sessionKey
     */
    @NotBlank
    @Size(max=100)
    private String code;
}

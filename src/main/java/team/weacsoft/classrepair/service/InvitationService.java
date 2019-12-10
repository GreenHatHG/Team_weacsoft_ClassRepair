package team.weacsoft.classrepair.service;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.commons.exception.CustomException;
import team.weacsoft.classrepair.commons.security.JwtUtil;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author GreenHatHG
 */

@RestController
@Validated
@Slf4j
public class InvitationService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoService userInfoService;

    // 过期时间1天
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    private static final String REDIS_INVITATION_KEY_PREFIX = "invi";

    /**
     * 获得邀请码
     * @param auth header中Authorization的值
     * @return
     */
    public Map<String, String> getInvitionCode(String auth){
        String code = REDIS_INVITATION_KEY_PREFIX + IdUtil.simpleUUID();
        stringRedisTemplate.opsForValue()
                .set(code, "", EXPIRE_TIME, TimeUnit.MILLISECONDS);
        return ImmutableMap.<String, String> builder().put("code", code).build();
    }

    public Map<String, String> updtaeRoleByCode(String code, String auth){
        try{
            String id = jwtUtil.getIdFromHeader(auth);
            Objects.requireNonNull(stringRedisTemplate.opsForValue().get(code));
            return userInfoService.updateRoleById(id, 2);
        }catch (Exception e){
            throw new CustomException(478, "邀请码无效或已过期");
        }
    }

}

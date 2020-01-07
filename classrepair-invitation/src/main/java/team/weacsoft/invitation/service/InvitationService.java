package team.weacsoft.invitation.service;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.user.service.UserInfoService;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author GreenHatHG
 */
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
            throw new BadRequestException(HttpStatus.valueOf(478), "邀请码无效或已过期");
        }
    }
}

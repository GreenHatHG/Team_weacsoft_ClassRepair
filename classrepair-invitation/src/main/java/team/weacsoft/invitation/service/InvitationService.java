package team.weacsoft.invitation.service;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.user.service.UserInfoUpdateService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author GreenHatHG
 */

@Component
public class InvitationService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoUpdateService userInfoService;

    // 过期时间1天
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    private static final String REDIS_INVITATION_KEY_PREFIX = "invi";

    /**
     * 获得邀请码
     * @return
     */
    public Map<String, String> getInvitionCode(){
        //2位随机数+时间戳后6位
        String code = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
                .substring(7) + RandomUtil.randomInt(9) + RandomUtil.randomInt(9);;
        stringRedisTemplate.opsForValue()
                .set(REDIS_INVITATION_KEY_PREFIX + code, "", EXPIRE_TIME, TimeUnit.MILLISECONDS);
        return ImmutableMap.<String, String> builder().put("code", code).build();
    }

    public Map<String, String> updtaeRoleByCode(String code, HttpServletRequest request){
        try{
            String id = jwtUtil.getIdFromHttpServletRequest(request);
            Objects.requireNonNull(stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_PREFIX + code));
            return userInfoService.updateRoleById(id, 2);
        }catch (Exception e){
            throw new BadRequestException(478, "邀请码无效或已过期");
        }
    }
}

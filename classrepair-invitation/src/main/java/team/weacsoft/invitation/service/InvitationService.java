package team.weacsoft.invitation.service;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.invitation.dto.request.UpdateRoleByCodeDto;
import team.weacsoft.user.dto.common.UpdateRoleDto;
import team.weacsoft.user.entity.UserInfo;
import team.weacsoft.user.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */

@Service
public class InvitationService {
    private StringRedisTemplate stringRedisTemplate;
    private IUserInfoService userInfoService;

    @Autowired
    public InvitationService(StringRedisTemplate stringRedisTemplate, IUserInfoService userInfoService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.userInfoService = userInfoService;
    }

    /**
     * 过期时间1天
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    private static final String REDIS_INVITATION_KEY = "invi";

    /**
     * 获得邀请码
     */
    public Map<String, String> getInvitionCode(){
        String code = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY);
        if(code == null){
            //2位随机数+时间戳后6位
            code = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
                    .substring(7) + RandomUtil.randomInt(9) + RandomUtil.randomInt(9);
            stringRedisTemplate.opsForValue()
                    .set(REDIS_INVITATION_KEY, code, EXPIRE_TIME, TimeUnit.MILLISECONDS);
        }
        return ImmutableMap.<String, String> builder().put("code", code).build();
    }

    public UpdateRoleDto updtaeRoleByCode(UpdateRoleByCodeDto dto, HttpServletRequest request) {
        String id = JwtUtil.getIdFromRequest(request);
        UserInfo userInfo = userInfoService.getById(id);

        String redisCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY);
        if (StringUtils.equals(dto.getCode(), redisCode)) {
            userInfo.setRole(4);
            userInfo.setPassword(Argon2Util.hash(dto.getPassword()));
            userInfoService.updateById(userInfo);
            return (UpdateRoleDto) JsonUtil.getCopyDto(userInfoService.getById(id), new UpdateRoleDto());
        } else {
            throw new BadRequestException(40099, "邀请码无效或已过期");
        }
    }
}

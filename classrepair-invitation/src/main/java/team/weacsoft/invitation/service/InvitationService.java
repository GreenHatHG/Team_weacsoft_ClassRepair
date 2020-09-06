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

    private static final String REDIS_INVITATION_KEY_ROOMMANAGERCODE = "invi_roomManagerCode";
    private static final String REDIS_INVITATION_KEY_LEADERCODE = "invi_leaderCode";
    private static final String REDIS_INVITATION_KEY_TEACHERCODE = "invi_teacherCode";
    private static final String REDIS_INVITATION_KEY_STAFFCODE = "invi_staffCode";

    /**
     * 获得邀请码
     */
    public Map<String, String> getInvitionCode(){
        String roomManagerCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_ROOMMANAGERCODE);
        String leaderCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_LEADERCODE);
        String teacherCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_TEACHERCODE);
        String staffCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_STAFFCODE);
        if(staffCode == null){
            //1位随机数+权限+时间戳后6位
            String substring = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
                    .substring(7);
            roomManagerCode = substring + RandomUtil.randomInt(9) + 6;
            leaderCode = substring + RandomUtil.randomInt(9) + 5;
            teacherCode = substring + RandomUtil.randomInt(9) + 7;
            staffCode = substring + RandomUtil.randomInt(9) + 4;
            stringRedisTemplate.opsForValue()
                    .set(REDIS_INVITATION_KEY_ROOMMANAGERCODE, roomManagerCode, EXPIRE_TIME, TimeUnit.MILLISECONDS);
            stringRedisTemplate.opsForValue()
                    .set(REDIS_INVITATION_KEY_LEADERCODE, leaderCode, EXPIRE_TIME, TimeUnit.MILLISECONDS);
            stringRedisTemplate.opsForValue()
                    .set(REDIS_INVITATION_KEY_TEACHERCODE, teacherCode, EXPIRE_TIME, TimeUnit.MILLISECONDS);
            stringRedisTemplate.opsForValue()
                    .set(REDIS_INVITATION_KEY_STAFFCODE, staffCode, EXPIRE_TIME, TimeUnit.MILLISECONDS);
        }
        return ImmutableMap.<String, String> builder().put("roomManagerCode", roomManagerCode)
                .put("leaderCode", leaderCode).put("teacherCode", teacherCode).put("staffCode", staffCode).build();
    }

    public UpdateRoleDto updtaeRoleByCode(UpdateRoleByCodeDto dto, HttpServletRequest request) {
        String id = JwtUtil.getIdFromRequest(request);
        UserInfo userInfo = userInfoService.getById(id);
        /*
        System.out.println(userInfo);
        if (!userInfo.isPerfect()){
            throw new BadRequestException(400,"信息未完善，不能提前权");
        }
         */
        String code = dto.getCode();

        String roomManagerCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_ROOMMANAGERCODE);
        String leaderCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_LEADERCODE);
        String teacherCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_TEACHERCODE);
        String staffCode = stringRedisTemplate.opsForValue().get(REDIS_INVITATION_KEY_STAFFCODE);

        if(StringUtils.equals(code, roomManagerCode)){
            userInfo.setRole(6);
            userInfo.setPassword(Argon2Util.hash(dto.getPassword()));
            userInfoService.updateById(userInfo);
            return (UpdateRoleDto) JsonUtil.getCopyDto(userInfoService.getById(id), new UpdateRoleDto());
        }
        else if(StringUtils.equals(code, leaderCode)){
            userInfo.setRole(5);
            userInfo.setPassword(Argon2Util.hash(dto.getPassword()));
            userInfoService.updateById(userInfo);
            return (UpdateRoleDto) JsonUtil.getCopyDto(userInfoService.getById(id), new UpdateRoleDto());
        }else if(StringUtils.equals(code, teacherCode)){
            userInfo.setRole(7);
            userInfo.setPassword(Argon2Util.hash(dto.getPassword()));
            userInfoService.updateById(userInfo);
            return (UpdateRoleDto) JsonUtil.getCopyDto(userInfoService.getById(id), new UpdateRoleDto());
        }
        else if (StringUtils.equals(code, staffCode)) {
            userInfo.setRole(4);
            userInfo.setPassword(Argon2Util.hash(dto.getPassword()));
            userInfoService.updateById(userInfo);
            return (UpdateRoleDto) JsonUtil.getCopyDto(userInfoService.getById(id), new UpdateRoleDto());
        } else {
            throw new BadRequestException(40099, "邀请码无效或已过期");
        }
    }
}

package team.weacsoft.classrepair.commons.jwt;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.commons.exception.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class JwtUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * jwt 过期时间,30分钟
     */
    private static final Long TTL = 60000*30L;

    private static final String REDIS_JWT_KEY_PREFIX = "security:jwt:";

    /**
     * 创建jwt
     * @param id 数据表id
     * @param key 随机字符串,对应着一个id
     * @return jwt字符串
     */
    private String createJWT(String id, String key) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key);

        builder.setExpiration(DateUtil.offsetMillisecond(now, TTL.intValue()));

        String jwt = builder.compact();
        // 将生成的JWT保存至Redis
        stringRedisTemplate.opsForValue()
                .set(REDIS_JWT_KEY_PREFIX + id, jwt, TTL, TimeUnit.MILLISECONDS);
        return jwt;
    }

    /**
     * 用于controller层获得
     * @param id 用户表id
     * @return jwt字符串
     */
    public String getJWT(String id){
        //ObjectId是MongoDB数据库的一种唯一ID生成策略
        String key = IdUtil.objectId();
        return createJWT(id, key);
    }

    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    private Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .parseClaimsJws(jwt)
                    .getBody();

            String redisKey = REDIS_JWT_KEY_PREFIX + claims.getId();

            // 校验redis中的JWT是否存在
            Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
            if (Objects.isNull(expire) || expire <= 0) {
                throw new UnauthorizedException("token 已过期，请重新登录!");
            }

            // 校验redis中的JWT是否与当前的一致
            String redisToken = stringRedisTemplate.opsForValue()
                    .get(redisKey);
            if (!StrUtil.equals(jwt, redisToken)) {
                throw new UnauthorizedException("token不正确, 请重新登录!");
            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期");
            throw new UnauthorizedException("Token 已过期");
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            throw new UnauthorizedException("token 解析失败，请尝试重新登录！");
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw new UnauthorizedException("token 解析失败，请尝试重新登录！");
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            throw new UnauthorizedException("token 解析失败，请尝试重新登录！");
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw new UnauthorizedException("token 解析失败，请尝试重新登录！");
        }
    }

    /**
     * 从 request 的 header 中获取 JWT
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getId(String jwt){
        return parseJWT(jwt).getId();
    }
}

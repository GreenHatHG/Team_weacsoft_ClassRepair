package team.weacsoft.common.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.handler.ApiResp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author GreenHatHG
 */

@Component
public class JwtUtil {

    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        JwtUtil.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * jwt 过期时间,30分钟
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    private static final String REDIS_JWT_KEY_PREFIX = "security:jwt:";

    /**
     * 用于controller层获得
     * @param id 用户表id
     * @return jwt字符串
     */
    public static String responseJwt(String id){
        //ObjectId是MongoDB数据库的一种唯一ID生成策略
        String key = Argon2Util.hash(IdUtil.objectId());
        stringRedisTemplate.opsForValue()
                .set(REDIS_JWT_KEY_PREFIX + id, key, EXPIRE_TIME, TimeUnit.MILLISECONDS);
        return JWT.create()
                .withClaim("id", id)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(key));
    }

    /**
     * 校验token是否正确
     * @param token
     * @param id
     * @return
     */
    public static boolean verify(String token, String id) {
        try {
            //对秘钥进行加密后再与用户名混淆在一起
            Algorithm algorithm = Algorithm.HMAC256(
                    Objects.requireNonNull(stringRedisTemplate.opsForValue().get(REDIS_JWT_KEY_PREFIX + id)));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("id", id)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getIdFromRequest(HttpServletRequest request){
        try {
            DecodedJWT jwt = JWT.decode(getJwtFromHttpServletRequest(request));
            return jwt.getClaim("id").asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从 request 的 header 中获取 JWT
     * @param request 请求
     * @return JWT
     */
    public static String getJwtFromHttpServletRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    public static String getIdFromJwt(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asString();
        } catch (Exception e) {
            return null;
        }
    }

    public static void response401(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.append(JSON.toJSON(ApiResp.error(HttpStatus.UNAUTHORIZED.value(),
                "无权访问(Unauthorized):验证信息已过期或者不存在")).toString());
    }
}

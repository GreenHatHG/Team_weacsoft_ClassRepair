package team.weacsoft.common.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.xml.internal.txw2.output.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
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
public class JwtUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * jwt 过期时间,30分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    private static final String REDIS_JWT_KEY_PREFIX = "security:jwt:";

    /**
     * 创建jwt
     * @param id 数据表id
     * @param secret 随机字符串,对应着一个id
     * @return jwt字符串
     */
    private String createJwt(String id, String secret) {
        return JWT.create()
                .withClaim("id", id)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 用于controller层获得
     * @param id 用户表id
     * @return jwt字符串
     */
    public String getJWT(String id){
        //ObjectId是MongoDB数据库的一种唯一ID生成策略
        String key = IdUtil.objectId();
        stringRedisTemplate.opsForValue()
                .set(REDIS_JWT_KEY_PREFIX + id, key, EXPIRE_TIME, TimeUnit.MILLISECONDS);
        return createJwt(id, key);
    }

    /**
     * 校验token是否正确
     * @param token
     * @param id
     * @return
     */
    public boolean verify(String token, String id) {
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

    /**
     * 从 request 的 header 中获取 JWT
     * @param request 请求
     * @return JWT
     */
    String getJwtFromRequest(HttpServletRequest request) {
        return getJwtFromBearerToken(request.getHeader("Authorization"));
    }

    private String getJwtFromBearerToken(String bearerToken){
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }

    public String getId(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getIdFromHeader(String header){
        return getId(getJwtFromBearerToken(header));
    }

    static void response401(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = JSONUtil.parseObj(ApiResp.error(HttpStatus.UNAUTHORIZED.value(),
                "无权访问(Unauthorized):" + e.getMessage()));
        out.append(jsonObject.toString());
    }
}

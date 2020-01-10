package team.weacsoft.user.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

/**
 * argon2加密解密工具
 * @author GreenHatHG
 **/
@Component
public class Argon2Util {
    private static Argon2 argon2 = Argon2Factory.create();

    /**
     * @param password 要加密的密码
     * @return 加密后的密码
     */
    public static String hash(String password) {
        return argon2.hash(10, 65536, 1, password);
    }

    /**
     * @param hash     加密后的密码
     * @param password 用户输入的密码
     * @return
     */
    public static boolean verify(String hash, String password) {
        return argon2.verify(hash, password);
    }
}
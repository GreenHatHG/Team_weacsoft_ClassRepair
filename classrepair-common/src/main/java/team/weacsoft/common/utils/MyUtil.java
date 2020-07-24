package team.weacsoft.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

/**
 * @Description
 * @ClassName MyUtil
 * @Author 魔法はまだ解けない
 * @date 2020.04.04 15:08
 */
public class MyUtil {

    /**
     * 此方法用于生成唯一id
     * 规则：当前日期（8位）20200127 + 时间戳后四位数字 + 三位随机数
     */
    public static String getId(){
        return DateUtil.format(new Date(), "yyyyMMdd") + DateUtil.current(Boolean.FALSE) % 10000 + RandomUtil.randomInt(100, 999);
    }

    //获取当前时间
    public static Long getTime(){
        return new Date().getTime()/1000;
    }
}

package team.weacsoft.classrepair.commons.log;

import lombok.Builder;
import lombok.Data;

/**
 * @author GreenHatHG
 */
@Data
@Builder
public class Syslog {

    private String userid;

    /**
     * 请求参数
     */
    private Object parameter;;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求返回的结果
     */
    private Object result;

    /**
     * 操作时间
     * yyyy-MM-dd HH:mm:ss
     */
    private String operateTime;

    /**
     * 操作描述
     */
    private String description;
}

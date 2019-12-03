package team.weacsoft.classrepair.commons.log;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志类
 * @author GreenHatHG
 **/

@Data
public class OperationLog {

    private String id;

    /**
     * 此id为userInfo表对应的id字段
     */
    private String userInfoId;

    /**
     * 事件
     */
    private String event;

    /**
     * 内容
     */
    private String content;

    private Date createTime;
}

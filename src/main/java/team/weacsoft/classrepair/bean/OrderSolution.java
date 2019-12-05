package team.weacsoft.classrepair.bean;

import team.weacsoft.classrepair.bean.basic.BaseBean;

import javax.persistence.Column;
import javax.persistence.Lob;

/**
 * 维护日志表
 * @author GreenHatHG
 **/
public class OrderSolution extends BaseBean {

    /**
     * 订单id，后端自动生成，规则：当前日期+时间戳前五位数字
     */
    @Column(nullable = false, unique = true)
    private String orderId;

    @Lob
    /**
     * 问题解决方案
     */
    private String solution;
}

package team.weacsoft.common.wx;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeData;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import team.weacsoft.common.log.Log;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  订阅消息模板
 * @author GreenHatHG
 */

@Validated
@Component
@Slf4j
public class TemplateMessage {
    private WxMaSubscribeMessage subscribeMessage;

    @Autowired
    public TemplateMessage() {
        this.subscribeMessage = new WxMaSubscribeMessage();
    }

    /**
     * 发送订单订阅消息
     * @param data 课室 报修问题描述 报修状态 报修时间 处理时间
     */
    @Log(module = "推送模板消息", operation="推送模板消息")
    private void sendMessage(String openId, ImmutableMap<String, String> data,String templateId){
        subscribeMessage.setTemplateId(templateId);
        subscribeMessage.setToUser(openId);

        //创建一个参数集合
        List<WxMaSubscribeData> wxMaSubscribeData = new ArrayList<>();
        data.forEach((k,v)-> wxMaSubscribeData.add(new WxMaSubscribeData(k, v)));

        //把集合给大的data
        subscribeMessage.setData(wxMaSubscribeData);
        try{
            WxMaConfiguration.getWxMaService().getMsgService().sendSubscribeMsg(subscribeMessage);
        }catch (Exception e){
            log.error("模板消息推送失败",e);
        }
    }

    /**
     *  @param openId 微信小程序用户openId
     * @param repairItemId 订单编号
     * @param repairItemContent 订单内容
     * @param state 订单状态
     * @param repairTime 下单时间
     * @param remark 备注
     */
    public void buildMapAndSend(@NotBlank String openId,
                                @NotBlank String repairItemId,
                                StringBuilder repairItemContent,
                                @NotBlank String state,
                                @NotNull  Long repairTime, String remark){
        //data 课室 报修问题描述 报修状态 报修时间 处理时间
        sendMessage(openId, ImmutableMap.<String, String> builder()
                .put("character_string6", repairItemId)
                .put("thing1", repairItemContent.toString())
                .put("phrase2", state)
                .put("date3", DateUtil.format(new Date(repairTime*1000), "yyyy-MM-dd HH:mm"))
                .put("thing5", remark).build(),"cwgf7C2W-s83IVg2me1WCOD3-mslBAzYytiLzp2CKVM");
    }



    /**
     * @param openId 目标用户的id
     * @param question 反馈内容
     * @param feedbackTime 下单时间
     * @param answer 回复内容
     * @param phone 联系电话
     * @param type 反馈状态
     */
    public void buildFeedbackMapAndSend(@NotBlank String openId,
                                @NotBlank String question,
                                @NotNull  Long feedbackTime,
                                @NotNull String answer,
                                @NotBlank String phone, String type){
        //data 课室 报修问题描述 报修状态 报修时间 处理时间
        sendMessage(openId, ImmutableMap.<String, String> builder()
                .put("thing1", question)
                .put("date2", DateUtil.format(new Date(feedbackTime*1000), "yyyy-MM-dd HH:mm"))
                .put("thing3", answer)
                .put("phone_number4", phone)
                .put("phrase5", type).build(),"K-wPOtsViEg_FZreNKRu0cYgQgl_JruGNzGt5nunGnw");
    }
}

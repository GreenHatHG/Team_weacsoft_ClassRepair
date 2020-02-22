package team.weacsoft.common.wx;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeData;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.log.Log;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GreenHatHG
 */

@Validated
@Component
public class TemplateMessage {
    private WxMaSubscribeMessage subscribeMessage;

    @Autowired
    public TemplateMessage() {
        this.subscribeMessage = new WxMaSubscribeMessage();
    }

    /**
     * 发送订阅消息
     * @param data 课室 报修问题描述 报修状态 报修时间 处理时间
     */
    @Log(module = "推送模板消息", operation="推送模板消息")
    private void sendMessage(String openId, ImmutableMap<String, String> data){
        subscribeMessage.setTemplateId("cwgf7C2W-s83IVg2me1WCOD3-mslBAzYytiLzp2CKVM");
        subscribeMessage.setToUser(openId);

        //创建一个参数集合
        List<WxMaSubscribeData> wxMaSubscribeData = new ArrayList<>();
        data.forEach((k,v)-> wxMaSubscribeData.add(new WxMaSubscribeData(k, v)));

        //把集合给大的data
        subscribeMessage.setData(wxMaSubscribeData);

        try{
            WxMaConfiguration.getWxMaService().getMsgService().sendSubscribeMsg(subscribeMessage);
        }catch (Exception e){
            throw new BadRequestException(475, "模板消息推送失败：" + e.getMessage());
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
        sendMessage(openId, ImmutableMap.<String, String> builder()
                .put("character_string6", repairItemId)
                .put("thing1", repairItemContent.toString())
                .put("phrase2", state)
                .put("date3", DateUtil.format(new Date(repairTime*1000), "yyyy-MM-dd HH:mm"))
                .put("thing5", remark).build());
    }
}

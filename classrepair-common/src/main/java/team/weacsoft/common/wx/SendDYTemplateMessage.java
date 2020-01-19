package team.weacsoft.common.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeData;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.log.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GreenHatHG
 */

@Component
public class SendDYTemplateMessage {
    private WxMaSubscribeMessage subscribeMessage;
    private WxMaService wxService;

    @Autowired
    public SendDYTemplateMessage() {
        this.subscribeMessage = new WxMaSubscribeMessage();
        this.wxService = WxMaConfiguration.getWxMaService();
    }

    /**
     * 发送订阅消息
     * @param data 课室 报修问题描述 报修状态 报修时间 处理时间
     */
    @Log(module = "推送模板消息", operation="推送模板消息")
    public void sendMessage(String openId, ImmutableMap<String, String> data){
        subscribeMessage.setTemplateId("9k_ZqA-5XjFldLh1Cx-MIrt2E8vh2RJVIgEJZbIbMfs");
        subscribeMessage.setToUser(openId);

        //创建一个参数集合
        List<WxMaSubscribeData> wxMaSubscribeData = new ArrayList<>();
        data.forEach((k,v)-> wxMaSubscribeData.add(new WxMaSubscribeData(k, v)));

        //把集合给大的data
        subscribeMessage.setData(wxMaSubscribeData);

        try{
            wxService.getMsgService().sendSubscribeMsg(subscribeMessage);
        }catch (Exception e){
            throw new BadRequestException(475, "模板消息推送失败：" + e.getMessage());
        }
    }
}

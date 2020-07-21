package team.weacsoft.repair.quartz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.wx.WxMaConfiguration;
import team.weacsoft.qa.entity.QaType;
import team.weacsoft.qa.service.impl.QaTypeServiceImpl;
import team.weacsoft.repair.entity.PushInfo;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.impl.RepairItemStateServiceImpl;
import team.weacsoft.timetable.entity.DutyUserInfo;
import team.weacsoft.timetable.service.impl.TimeTableServiceImpl;
import team.weacsoft.user.entity.UserInfo;
import team.weacsoft.user.service.impl.UserInfoServiceImpl;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 定时任务，推送到公众号
 * @author GreenHatHG
 * @since 2020-02-28
 */

@Service
public class PushService{

    private RestTemplate restTemplate;
    private RepairItemStateServiceImpl repairItemService;
    private TimeTableServiceImpl timeTableService;
    private UserInfoServiceImpl userInfoService;
    private QaTypeServiceImpl qaTypeService;

    @Value("${classrepair.push.key}")
    private String key;
    @Value("${classrepair.push.iv}")
    private String iv;
    @Value("${classrepair.push.url}")
    private String url;
    @Value("${classrepair.push.template_id}")
    private String templateId;
    private AES aes;

    @Autowired
    public PushService(RestTemplate restTemplate, RepairItemStateServiceImpl repairItemService, TimeTableServiceImpl timeTableService, UserInfoServiceImpl userInfoService, QaTypeServiceImpl qaTypeService) {
        this.restTemplate = restTemplate;
        this.repairItemService = repairItemService;
        this.timeTableService = timeTableService;
        this.userInfoService = userInfoService;
        this.qaTypeService = qaTypeService;
    }

    @PostConstruct
    public void setAes() {
        this.aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
    }

    //发送定时事件信息
    public void sendCronMessage(){
        sendMessage(repairItemService.getBaseMapper().pushDao(), "请尽快处理");
    }

    //发送紧急订单信息
    public void sendUrgentMessage(RepairItem repairItem){
        PushInfo pushInfo = new PushInfo();
        BeanUtil.copyProperties(repairItem, pushInfo);
        UserInfo userInfo = userInfoService.getById(repairItem.getOrderer());

        QaType qaType = qaTypeService.getById(repairItem.getEquipmentType());
        pushInfo.setOrdererName(userInfo.getName());
        pushInfo.setTitle(qaType.getTitle());
        List<PushInfo> data = new ArrayList<>();
        data.add(pushInfo);
        sendMessage(data, "!!紧急订单，请马上处理!!");
    }

    public void sendMessage(List<PushInfo> pushInfos, String remark){
        //TODO 这里出现读取了多个值班人员，但是第一个人信息为null的bug
       List<DutyUserInfo> dutyUserInfos = timeTableService.getBaseMapper().getDutyUserInfos();
        System.out.println(dutyUserInfos.size());

       if(dutyUserInfos != null && !dutyUserInfos.isEmpty()){
           for(PushInfo pushInfo : pushInfos){
               for(DutyUserInfo dutyUserInfo : dutyUserInfos){
                   request(pushInfo, dutyUserInfo.getIdentityId(), remark);
               }
           }
       //没有在线维护人员，发给负责人
       }else{
           List<UserInfo> principals = userInfoService.getPrincipals();
           for(PushInfo pushInfo : pushInfos){
               for(UserInfo userInfo : principals){
                   request(pushInfo, userInfo.getIdentityId(), remark);
               }
           }
       }
       // TODO 还有一种情况是系统刚刚初始化，只有一个超级管理员
    }

    public void request(PushInfo pushInfo, Long identityId, String remark){
        Map<String, Object> json = new HashMap<>(4);
        json.put("number", identityId);
        json.put("template_id", templateId);
        json.put("data", ImmutableMap.<String, String>builder()
                .put("first", "你好，有新的故障")
                .put("keyword1", pushInfo.getTitle())
                .put("keyword2", pushInfo.getClassroom())
                .put("keyword3", RepairItemStateEnum.getDescription(pushInfo.getState()))
                .put("keyword4", pushInfo.getProblem())
                .put("keyword5", DateUtil.format(new Date(pushInfo.getCreateTime()*1000), "yyyy-MM-dd HH:mm:ss"))
                .put("remark", remark).build());
        json.put("miniprogram", ImmutableMap.<String, String>builder()
                .put("appid", WxMaConfiguration.getAppid())
                .put("pagepath", "pages/index/manage/order/orderDetails/orderDetails?orderId="
                        + pushInfo.getRepairItemId()).build());

        Map<String, Object> data = new HashMap<>(2);
//        data.put("test", 1);
        data.put("data", aes.encryptBase64(JSONObject.toJSONString(json)));

        restTemplate.postForEntity(url, data, String.class);
//        System.out.println(restTemplate.postForEntity(url, data, String.class));
    }
}
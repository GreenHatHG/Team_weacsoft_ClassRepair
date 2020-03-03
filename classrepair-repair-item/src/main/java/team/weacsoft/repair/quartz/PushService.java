package team.weacsoft.repair.quartz;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.repair.entity.PushInfo;
import team.weacsoft.repair.service.impl.RepairItemStateServiceImpl;
import team.weacsoft.timetable.entity.DutyUserInfo;
import team.weacsoft.timetable.service.impl.TimeTableServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PushService(RestTemplate restTemplate, RepairItemStateServiceImpl repairItemService, TimeTableServiceImpl timeTableService) {
        this.restTemplate = restTemplate;
        this.repairItemService = repairItemService;
        this.timeTableService = timeTableService;
    }

    @PostConstruct
    public void setAes() {
        this.aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
    }

    public void sendMessage(){
       List<PushInfo> pushInfos = repairItemService.getBaseMapper().pushDao();
       List<DutyUserInfo> dutyUserInfos = timeTableService.getBaseMapper().getDutyUserInfos();
       for(PushInfo pushInfo : pushInfos){
           for(DutyUserInfo dutyUserInfo : dutyUserInfos){
               request(pushInfo, dutyUserInfo);
           }
       }
    }

    public void request(PushInfo pushInfo, DutyUserInfo dutyUserInfo){
        Map<String, Object> json = new HashMap<>(3);
        json.put("number", dutyUserInfo.getIdentityId());
        json.put("template_id", templateId);

        HashMap<String, String> dataJson = new HashMap<>(5);
        dataJson.put("first", "你好，有新的故障");
        dataJson.put("keyword1", pushInfo.getTitle());
        dataJson.put("keyword2", pushInfo.getClassroom());
        dataJson.put("keyword3", RepairItemStateEnum.getDescription(pushInfo.getState()));
        dataJson.put("keyword4", pushInfo.getProblem());
        dataJson.put("keyword5", DateUtil.format(new Date(pushInfo.getCreateTime()*1000), "yyyy-MM-dd HH:mm:ss"));
        dataJson.put("remark", "请尽快处理");
        json.put("data", dataJson);

        Map<String, Object> data = new HashMap<>(2);
//        data.put("test", 1);
        data.put("data", aes.encryptBase64(JSONObject.toJSONString(json)));

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, data, String.class);
    }
}
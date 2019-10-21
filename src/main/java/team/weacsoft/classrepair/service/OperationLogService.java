package team.weacsoft.classrepair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.OperationLog;
import team.weacsoft.classrepair.repository.OperationLogRepository;

/**
 * @author GreenHatHG
 **/

@Component
public class OperationLogService {

    private OperationLogRepository operationLogRepository;
    private OperationLog operationLog = null;
    @Autowired
    public void setOperationLogRepository(OperationLogRepository operationLogRepository) {
        this.operationLogRepository = operationLogRepository;
    }

    public void addLog(String userInfoId, String event, String content){
        try{
            operationLog = new OperationLog(userInfoId, event, content);
            operationLogRepository.save(operationLog);
        }catch (Exception e){
            System.out.println("保存操作事件失败");
            e.printStackTrace();
        }

    }
}

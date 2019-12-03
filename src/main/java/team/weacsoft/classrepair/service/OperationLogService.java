//package team.weacsoft.classrepair.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import team.weacsoft.classrepair.commons.log.OperationLog;
//import team.weacsoft.classrepair.repository.OperationLogRepository;
//
///**
// * @author GreenHatHG
// **/
//
//@Component
//public class OperationLogService {
//
//    @Autowired
//    private OperationLogRepository operationLogRepository;
//
//    private static final Logger log = LoggerFactory.getLogger(OperationLogService.class);
//
//    @Autowired
//    public void setOperationLogRepository(OperationLogRepository operationLogRepository) {
//        this.operationLogRepository = operationLogRepository;
//    }
//
//    public void addLog(String userInfoId, String event, String content){
//        try{
//            OperationLog operationLog = new OperationLog(userInfoId, event, content);
//            operationLogRepository.save(operationLog);
//        }catch (Exception e){
//           log.error("OperationLogService", e);
//        }
//
//    }
//}

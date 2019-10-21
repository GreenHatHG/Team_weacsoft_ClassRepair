//package team.weacsoft.classrepair.controller;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import team.weacsoft.classrepair.bean.TimeTable;
//import team.weacsoft.classrepair.entity.Result;
//import team.weacsoft.classrepair.entity.ResultFactory;
//import team.weacsoft.classrepair.repository.TimeTableRepository;
//import team.weacsoft.classrepair.service.OperationLogService;
//
//import java.util.Map;
//
///**
// * @author GreenHatHG
// **/
//
//@RestController
//@RequestMapping(value="${api}")
//public class TimeTableController {
//
//    private TimeTableRepository timeTableRepository;
//    private OperationLogService operationLogService;
//
//    @Autowired
//    public void setTimeTableRepository(TimeTableRepository timeTableRepository) {
//        this.timeTableRepository = timeTableRepository;
//    }
//
//    @Autowired
//    public void setOperationLogService(OperationLogService operationLogService) {
//        this.operationLogService = operationLogService;
//    }
//
//    @PostMapping("/timetable")
//    public Result addTimeTable(String userid, int status){
//        String event = (status == 1 ? "签到" : "签退");
//        TimeTable timeTable = new TimeTable(userid, status);
//        try{
//            timeTableRepository.save(timeTable);
//        }catch (Exception e){
//            operationLogService.addLog(userid, event + "失败", "-");
//            return ResultFactory.buildFailResult("签到失败");
//        }
//        operationLogService.addLog(userid, event + "成功", "-");
//        return ResultFactory.buildSuccessResult("成功");
//    }
//
//}

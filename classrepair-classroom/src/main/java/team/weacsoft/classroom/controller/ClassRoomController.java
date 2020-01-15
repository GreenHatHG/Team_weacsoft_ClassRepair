package team.weacsoft.classroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classroom.service.ClassroomService;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.utils.JsonUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * @menu 课室管理
 * @author GreenHatHG
 **/

@RestController
public class ClassRoomController {

    @Autowired
    private ClassroomService classroomService;

    //todo 优化：不必每次都生成，可以查缓存

    /**
     * 获取课室信息
     * @return
     */
    @GetMapping("/public/classrooms")
    public ResponseEntity<ApiResp> getClassRooms(){
        return ApiResp.ok(classroomService.getClassRooms());
    }

    @GetMapping("/public/classrooms/classid")
    public ResponseEntity<ApiResp> getClassRoomById(@RequestParam(name = "class_id") @NotBlank @Size(max = 100) Integer classId){
        return ApiResp.ok(JsonUtil.entityExclude(classroomService.findByClassId(classId),
                "createTime", "updateTime", "state", "id"));
    }

}

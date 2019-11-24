package team.weacsoft.classrepair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.service.ClassRoomService;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="${api}")
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    //todo 优化：不必每次都生成，可以查缓存
    @GetMapping("/classrooms")
    public Result getClassRooms(){
        return ResultFactory.buildSuccessResult(classRoomService);
    }

}

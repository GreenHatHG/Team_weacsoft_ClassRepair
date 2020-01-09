package team.weacsoft.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;

/**
 * @author GreenHatHG
 * @menu 公共
 */

@RestController
public class SystemController {

    /**
     * 接口连接测试
     * @return 已连接上塞伯坦星球
     */
    @Log(module = "连通性测试", operation = "测试是否成功连接")
    @GetMapping("/public/test")
    public ResponseEntity<ApiResp> test(){
        return ApiResp.ok("已连接上塞伯坦星球");
    }

}

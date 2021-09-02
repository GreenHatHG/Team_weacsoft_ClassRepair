package team.weacsoft.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;

/**
 * @author GreenHatHG
 * @menu 公共
 */

@RestController
@Api(value = "SystemController" , tags = "测试连接")
public class SystemController {

    /**
     * 接口连接测试
     * @return 已连接上塞伯坦星球
     */
    @ApiOperation(value="接口连接测试", notes="")
    @Log(module = "连通性测试", operation = "测试是否成功连接")
    @GetMapping("/public/test")
    public ResponseEntity<ApiResp> test(){
        return ApiResp.ok("已连接上塞伯坦星球");
    }

}

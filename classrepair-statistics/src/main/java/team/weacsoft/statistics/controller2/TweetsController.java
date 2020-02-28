package team.weacsoft.statistics.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.utils.WeChatUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @ClassName TweetsController
 * @Author 魔法はまだ解けない
 * @date 2020.02.26 19:51
 */
@RestController
@RequestMapping(value="/api/v2/")
@Validated
public class TweetsController {

    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/get_guide")
    public ResponseEntity<ApiResp> getGuide() throws IOException {
        return ApiResp.ok(WeChatUtil.getTweetsURL());
    }

    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @PutMapping("/modify_guide")
    public ResponseEntity<ApiResp> modifyGuide(@RequestBody Map<String,String> dto, HttpServletRequest request) throws IOException {
        String guide_article = dto.get("guide_article");
        WeChatUtil.setTweetsURL(guide_article);
        return ApiResp.ok(guide_article);
    }
}

package team.weacsoft.repair.controller2;

import com.google.common.collect.ImmutableMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.repair.quartz.QuartzJobService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author GreenHatHG
 * @since 2020-01-30
 */
@RestController
@RequestMapping(value="/api/v2/repair_item")
@Validated
public class PushController {

    private QuartzJobService quartzJobService;

    @Autowired
    public PushController(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    /**
     * 推送到公众号，时间设置
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "订单管理", operation = "推送到公众号时间设置")
    @PutMapping("/push/cron")
    public ResponseEntity<ApiResp> addOrderItem(@NotBlank @Size(max = 100) String cron) {
        String cronStr;
        try {
            cronStr = URLDecoder.decode(cron, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BadRequestException(42323, "cron参数编码错误："  + e.getMessage());
        }
        try {
            quartzJobService.updateJobCron(cronStr);
        } catch (SchedulerException e) {
            throw new BadRequestException(45453, "更新失败："  + e.getMessage());
        }
        return ApiResp.ok(ImmutableMap.<String, String>builder().put("cron", cronStr).build());
    }
}
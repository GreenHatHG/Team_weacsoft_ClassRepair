package team.weacsoft.statistics.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.statistics.service.RepairItemExcelService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */
@RestController
@RequestMapping(value="/api/v2/repair_item")
@Validated
public class RepairItemExcelController {

    @Autowired
    private RepairItemExcelService repairItemExcelService;

    /**
     *  导出excel接口
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/excel")
    public ResponseEntity<ApiResp>  getExcel(@RequestParam(name = "start_time", required = false)Long startTime,
                                            @RequestParam(name = "end_time", required = false) Long endTime,
                                              HttpServletRequest request){
        Resource resource = repairItemExcelService.getExcel(startTime, endTime);
        String contentType = null;
        //尝试确定文件的内容类型
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new BadRequestException("Could not determine file type.");
        }

        //如果无法确定类型，则使用默认内容类型
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        System.out.println("02");
        return ApiResp.ok(ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource));
    }
}

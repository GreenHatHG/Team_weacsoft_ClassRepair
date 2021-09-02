package team.weacsoft.material.controller2;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.material.entity.MaterialType;
import team.weacsoft.material.service.IMaterialTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 材料种类及材料 前端控制器
 * </p>
 *
 * @author 魔法はまだ解けない
 * @since 2020-09-10
 */
@Api(value = "MaterialController",  tags = "材料模块 | 材料类型 LoginController ")
@RestController
@Slf4j
@RequestMapping("/api/v2/materials")
public class MaterialTypeController {

    @Autowired
    IMaterialTypeService iMaterialService;

    /**
     * 添加材料大类
     *
     * @param materialType
     * @return
     */
    @ApiOperation(value="添加材料大类", notes="")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "添加材料大类控制器")
    @PostMapping("/types")
    public ResponseEntity<ApiResp> addMaterialType(@RequestBody @Valid MaterialType materialType, HttpServletRequest request) {
        materialType.setUserid(Integer.parseInt(JwtUtil.getIdFromRequest(request)));
        return ApiResp.ok(iMaterialService.addMaterialType(materialType));
    }

    /**
     * 获取材料大类
     *
     * @return
     */
    @ApiOperation(value="获取材料大类", notes="")
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "获取所有材料大类")
    @GetMapping("/types")
    public ResponseEntity<ApiResp> getMaterialType(@RequestParam(required = false) Integer state) {
        return ApiResp.ok(iMaterialService.getMaterialType(state));
    }

    /**
     * 删除材料大类
     * @param id
     * @param request
     * @return
     */
    @ApiOperation(value="删除材料大类", notes="")
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "删除材料大类")
    @DeleteMapping("/types")
    public ResponseEntity<ApiResp> deleteMaterialType(@RequestParam int id, HttpServletRequest request) {

        MaterialType materialType = new MaterialType();
        materialType.setId(id);
        materialType.setState(-1);

        return ApiResp.ok(iMaterialService.updateMaterialType(materialType,request));
    }

    /**
     * 修改材料大类名字
     * @param id
     * @param name
     * @param sort
     * @param state
     * @param request
     * @return
     */
    @ApiOperation(value="修改材料大类名字", notes="")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "修改材料大类")
    @PutMapping("/types")
    public ResponseEntity<ApiResp> updateMaterialType(@RequestParam int id,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false) Integer sort,
                                                      @RequestParam(required = false) Integer state,
                                                      HttpServletRequest request) {

        MaterialType materialType = new MaterialType();
        materialType.setId(id);
        materialType.setName(name);
        materialType.setSort(sort);
        materialType.setState(state);

        return ApiResp.ok(iMaterialService.updateMaterialType(materialType,request));
    }

}

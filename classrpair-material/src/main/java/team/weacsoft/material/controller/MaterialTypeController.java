package team.weacsoft.material.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.material.entity.MaterialType;
import team.weacsoft.material.service.MaterialTypeService;

import javax.validation.constraints.NotNull;

/**
 * 材料分类大类
 */
@RestController
@RequestMapping("/api/v2/materials")
@Validated
public class MaterialTypeController {
    @Autowired
    MaterialTypeService materialTypeService;

    /**
     *  添加材料分类
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料种类管理", operation = "管理员增加材料种类")
    @PostMapping("/types")
    public ResponseEntity<ApiResp> addMaterialTypes(@Validated @RequestBody MaterialType materialType){
        return ApiResp.ok(materialTypeService.addMaterialType(materialType));
    }

    /**
     * 删除材料分类（软删除）
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料种类管理", operation = "管理员删除材料种类")
    @DeleteMapping("/types")
    public ResponseEntity<ApiResp> dropMaterialTypes(@NotNull @RequestParam int id){
        return ApiResp.ok(materialTypeService.deleteMaterialType(id));
    }

    /**
     * 修改材料分类信息
     * @param materialType
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料种类管理", operation = "管理员修改材料信息")
    @PutMapping("/types")
    public ResponseEntity<ApiResp> changeMaterialTypes(@Validated @RequestBody MaterialType materialType,
                                                       @NotNull @RequestParam int id){
        return ApiResp.ok(materialTypeService.updateMaterialType(materialType,id));
    }

    /**
     * 获取所有材料类型信息
     * @param pageRequest
     * @return
     */
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @Log(module = "材料种类管理", operation = "获取所有材料信息")
    @GetMapping("/types")
    public ResponseEntity<ApiResp> getMaterialTypes(PageRequest pageRequest){
        return ApiResp.ok(materialTypeService.getMaterialTypes(pageRequest));
    }
}

package team.weacsoft.material.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.service.IMaterialService;

import javax.validation.constraints.NotNull;

/**
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-07-26
 */
@RestController
@RequestMapping("/api/v2/materials")
@Validated
public class MaterialController {
    @Autowired
    IMaterialService materialService;

    /**
     *  添加材料部件
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "管理员增加材料种类")
    @PostMapping("")
    public ResponseEntity<ApiResp> addMaterialTypes(@Validated @RequestBody Material material){
        return ApiResp.ok(materialService.addMaterial(material));
    }

    /**
     * 删除材料部件（软删除）
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "管理员删除材料种类")
    @DeleteMapping("")
    public ResponseEntity<ApiResp> dropMaterialTypes(@NotNull @RequestParam int id){
        return ApiResp.ok(materialService.deleteMaterial(id));
    }

    /**
     * 修改材料部件信息
     * @param material
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "管理员修改材料信息")
    @PutMapping("")
    public ResponseEntity<ApiResp> changeMaterialTypes(@Validated @RequestBody Material material,
                                                       @NotNull @RequestParam int id){
        return ApiResp.ok(materialService.updateMaterial(material,id));
    }

    /**
     * 获取所有材料部件类型信息
     * @param pageRequest
     * @return
     */
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "获取所有材料信息")
    @GetMapping("")
    public ResponseEntity<ApiResp> getMaterialTypes(PageRequest pageRequest){
        return ApiResp.ok(materialService.getMaterials(pageRequest));
    }

    /**
     * 查询某一种类材料下的部件
     */
}

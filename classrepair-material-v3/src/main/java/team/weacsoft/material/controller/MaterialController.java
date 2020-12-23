package team.weacsoft.material.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.entity.MaterialType;
import team.weacsoft.material.service.IMaterialService;
import team.weacsoft.material.service.IMaterialTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/9/20
 */
@RestController
@Slf4j
@RequestMapping("/api/v2/materials")
public class MaterialController {

    @Autowired
    IMaterialService iMaterialService;

    /**
     * 添加材料
     * @param material
     * @param request
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "材料小类")
    @PostMapping("")
    public ResponseEntity<ApiResp> addMaterial(@RequestBody @Valid Material material, HttpServletRequest request) {
        material.setUserid(Integer.parseInt(JwtUtil.getIdFromRequest(request)));
        return ApiResp.ok(iMaterialService.addMaterial(material));
    }

    /**
     *
     *
     * 获取材料
     * @return
     */
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "获取所有材料")
    @GetMapping("")
    public ResponseEntity<ApiResp> getMaterial(PageRequest pageRequest,
                                               @RequestParam(required = false) Integer id,
                                               @RequestParam(required = false) Integer state,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) Integer fid
                                               ) {
        Material material = new Material();
        material.setId(id);
        material.setState(state);
        material.setName(name);
        material.setFid(fid);
        return ApiResp.ok(iMaterialService.getMaterial(pageRequest,material));
    }


    /**
     *
     *
     * 修改材料
     * @return
     */
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @Log(module = "材料管理", operation = "修改指定材料")
    @PutMapping("")
    public ResponseEntity<ApiResp> updateMaterial(
                                               @RequestParam Integer id,
                                               @RequestParam(required = false) Integer state,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) Integer amount,
                                               @RequestParam(required = false) Double price,
                                               @RequestParam(required = false) Integer fid,
                                               @RequestParam(required = false) Integer sort,
                                               HttpServletRequest request) {
        Material material = new Material();
        material.setId(id);
        material.setState(state);
        material.setName(name);
        material.setFid(fid);
        material.setAmount(amount);
        material.setPrice(price);
        material.setSort(sort);
        return ApiResp.ok(iMaterialService.updateMaterial(material,request));
    }
}

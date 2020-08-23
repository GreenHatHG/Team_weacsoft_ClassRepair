package team.weacsoft.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.material.dto.response.MaterialResp;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.entity.MaterialType;

public interface MaterialTypeService extends IService<MaterialType> {
    MaterialResp addMaterialType(MaterialType materialType);
    MaterialResp deleteMaterialType(int id);
    MaterialResp updateMaterialType(MaterialType materialType,int id);
    Page<Material> getMaterialTypes(PageRequest pageRequest);
}

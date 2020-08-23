package team.weacsoft.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.entity.MaterialType;

public interface MaterialTypeMapper extends BaseMapper<MaterialType> {
    int addMaterialType(MaterialType materialType);
    int deleteMaterialType(int id,Long time);
    IPage<Material> getMaterialTypes(IPage<MaterialType> iPage);
}

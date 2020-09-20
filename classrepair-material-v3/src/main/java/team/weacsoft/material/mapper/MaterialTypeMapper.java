package team.weacsoft.material.mapper;

import team.weacsoft.material.entity.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import team.weacsoft.material.entity.MaterialType;

import java.util.List;

/**
 * <p>
 * 材料种类及材料 Mapper 接口
 * </p>
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-09-10
 */
public interface MaterialTypeMapper extends BaseMapper<Material> {
    int addMaterialType(MaterialType materialType);

    List<MaterialType> getMaterialType(Integer state);
    List<MaterialType> getAllMaterialType();
    int updateMaterialType(MaterialType materialType);
}

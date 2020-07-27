package team.weacsoft.material.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import team.weacsoft.material.entity.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-07-26
 */
public interface MaterialMapper extends BaseMapper<Material> {
    int addMaterial(Material material);
    int deleteMaterial(int id,Long time);
    IPage<Material> getAllMaterials(IPage<Material> iPage);
}

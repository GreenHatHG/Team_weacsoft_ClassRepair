package team.weacsoft.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.entity.MaterialType;

import java.util.List;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/9/20
 */
@Repository
public interface MaterialMapper extends BaseMapper<Material> {
    int addMaterial(MaterialType materialType);
    List<Material> searchMaterial(Material material);
    IPage<Material> getMaterial(IPage<T> page, Material material);
    int updateMaterial(Material material);
}
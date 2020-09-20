package team.weacsoft.material.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.material.entity.Material;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/9/20
 */
public interface IMaterialService {
    Material addMaterial(Material material);
    List<Material> searchMaterial(Material material);
    IPage<Material> getMaterial(PageRequest pageRequest, Material material);
    Material updateMaterial(Material materialType, HttpServletRequest request);
}

package team.weacsoft.material.service;

import team.weacsoft.material.entity.Material;
import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.material.entity.MaterialType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 材料种类及材料 服务类
 * </p>
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-09-10
 */
public interface IMaterialTypeService extends IService<Material> {
    MaterialType addMaterialType(MaterialType materialType);
    List<MaterialType> getMaterialType(Integer state);
    MaterialType updateMaterialType(MaterialType materialType, HttpServletRequest request);
}

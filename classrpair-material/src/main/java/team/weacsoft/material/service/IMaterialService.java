package team.weacsoft.material.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.material.dto.response.MaterialResp;
import team.weacsoft.material.entity.Material;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-07-26
 */
public interface IMaterialService extends IService<Material> {
    MaterialResp addMaterial(Material materialReq);
    MaterialResp deleteMaterial(int id);
    MaterialResp updateMaterial(Material material,int id);
    Page<Material> getMaterials(PageRequest pageRequest);

}

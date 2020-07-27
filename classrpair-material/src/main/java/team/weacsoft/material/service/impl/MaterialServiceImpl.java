package team.weacsoft.material.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.MyUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.material.dto.response.MaterialResp;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.mapper.MaterialMapper;
import team.weacsoft.material.service.IMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-07-26
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

    @Override
    public MaterialResp addMaterial(Material material) {
        int i = baseMapper.addMaterial(material);
        return new MaterialResp(i,"添加成功");
    }

    @Override
    public MaterialResp deleteMaterial(int id) {
        MaterialResp materialResp = new MaterialResp();
        materialResp.setId(id);
        if(baseMapper.deleteMaterial(id, MyUtil.getTime())==0){
            materialResp.setMessage("id不存在");
        }
        materialResp.setMessage("删除成功");
        return materialResp;
    }

    @Override
    public MaterialResp updateMaterial(Material material, int id) {
        material.setId(id);
        MaterialResp materialResp = new MaterialResp();
        materialResp.setId(material.getId());
        if(baseMapper.updateById(material)==0){
            materialResp.setMessage("id不存在");
        }
        materialResp.setMessage("修改成功");
        return materialResp;
    }

    @Override
    public Page<Material> getMaterials(PageRequest pageRequest) {
        Page<Material> allMaterials = (Page<Material>) baseMapper.getAllMaterials(PageUtil.getPage(pageRequest));
        return allMaterials;
    }

}

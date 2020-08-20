package team.weacsoft.material.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.MyUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.material.dto.response.MaterialResp;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.entity.MaterialType;
import team.weacsoft.material.mapper.MaterialTypeMapper;
import team.weacsoft.material.service.MaterialTypeService;

@Service
public class MaterialTypeServiceImpl extends ServiceImpl<MaterialTypeMapper, MaterialType> implements MaterialTypeService {
    @Override
    public MaterialResp addMaterialType(MaterialType materialType) {
        materialType.setCreateTime(MyUtil.getTime());
        int i = baseMapper.addMaterialType(materialType);
        MaterialResp materialResp = new MaterialResp();
        materialResp.setMessage("插入成功");
        materialResp.setId(i);
        return materialResp;
    }

    @Override
    public MaterialResp deleteMaterialType(int id) {
        if(baseMapper.deleteMaterialType(id, MyUtil.getTime())==0){
            throw new BadRequestException("插入失败");
        }
        return new MaterialResp(id,"删除成功");
    }

    @Override
    public MaterialResp updateMaterialType(MaterialType materialType, int id) {
        if(baseMapper.updateById(materialType)==0){
            throw new BadRequestException("修改失败");
        }
        return new MaterialResp(id,"修改成功");
    }

    @Override
    public Page<Material> getMaterialTypes(PageRequest pageRequest) {
        return (Page<Material>) baseMapper.getMaterialTypes(PageUtil.getPage(pageRequest));
    }
}

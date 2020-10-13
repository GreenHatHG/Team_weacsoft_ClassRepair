package team.weacsoft.material.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.MyUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.entity.MaterialType;
import team.weacsoft.material.mapper.MaterialMapper;
import team.weacsoft.material.mapper.MaterialTypeMapper;
import team.weacsoft.material.service.IMaterialService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/9/20
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {
    @Autowired
    MaterialTypeMapper materialTypeMapper;

    @Override
    public Material addMaterial(Material material) {
        List<MaterialType> allMaterialType = materialTypeMapper.getAllMaterialType();
        //判断大类是否在
        AtomicBoolean a= new AtomicBoolean(false);
        allMaterialType.forEach((MaterialType materialType)->{
            if (materialType.getId()==material.getFid())
                a.set(true);
        });
        if (a.get()==false)
            throw new BadRequestException(400,"父类id不存在");
        material.setType(2);
        baseMapper.insert(material);
        return material;
    }

    @Override
    public List<Material> searchMaterial(Material material) {
        List<Material> material1 = baseMapper.searchMaterial(material);
        return material1;
    }
    @Override
    public IPage<Material> getMaterial(PageRequest pageRequest,Material material) {
        IPage<Material> material1 = baseMapper.getMaterial(PageUtil.getPage(pageRequest),material);
        return material1;
    }

    @Override
    public Material updateMaterial(Material material, HttpServletRequest request) {
        //todo 只能修改小类
        int state[]={-1,0,1};
        if (material.getType()!=2){
            throw new BadRequestException(400,"该分类不是材料，请查看是否是属于材料种类即type=1");
        }else if (material.getType()!=1){
            throw new BadRequestException(400,"该分类只存在两种，即：1（大类）和2（小类）");
        }
        if(!Arrays.stream(state).anyMatch((a)->a==material.getState())){
            throw new BadRequestException(400,"该分类只能设置3种状态，即：-1（删除）和2（小类）");
        }

        material.setUpdateTime(MyUtil.getTime());
        material.setUserid(Integer.parseInt(JwtUtil.getIdFromRequest(request)));

        if (baseMapper.updateMaterial(material)==0)
            throw new BadRequestException(400,"更新失败");
        return material;
    }
}

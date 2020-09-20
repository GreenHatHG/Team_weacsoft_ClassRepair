package team.weacsoft.material.service.impl;

import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.MyUtil;
import team.weacsoft.material.entity.Material;
import team.weacsoft.material.entity.MaterialType;
import team.weacsoft.material.mapper.MaterialTypeMapper;
import team.weacsoft.material.service.IMaterialTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 材料种类及材料 服务实现类
 * </p>
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-09-10
 */
@Service
public class MaterialTypeServiceImpl extends ServiceImpl<MaterialTypeMapper, Material> implements IMaterialTypeService {

    /**
     * 添加材料大类方法
     *
     * @param materialType
     * @return
     */
    @Override
    public MaterialType addMaterialType(MaterialType materialType) {
        materialType.setCreateTime(MyUtil.getTime());
        materialType.setId(baseMapper.addMaterialType(materialType));
        return materialType;
    }

    /**
     * 获取所有材料大类
     *
     * @return
     */
    @Override
    public List<MaterialType> getMaterialType(Integer state) {
        return baseMapper.getMaterialType(state);
    }

    /**
     * 修改大类状态
     * 通过材料大类id，修改材料名name、材料状态state、材料排序sort
     * 注：
     * 不能修改id为100的材料
     * 只能修改type为1的材料
     * @param materialType
     * @return
     */
    @Override
    public MaterialType updateMaterialType(MaterialType materialType, HttpServletRequest request) {
        //这里进行两个逻辑判断，不能修改100号材料，不能修改type不为1的材料
        if (materialType.getId()==100)
            throw new BadRequestException(400,"禁止修改100号材料大类");
        if(baseMapper.selectById(new MaterialType().setId(materialType.getId())).getType()!=1)
            throw new BadRequestException(400,"这个不是大类，请调用修改材料接口");

        //如果需要改状态，逻辑如下
        if (materialType.getState()!=null)
            switch (materialType.getState()){
                /**
                 * todo 这里缺少一个判断，如果设为停用，子类也得跟着停用
                 */
                case -1:
                    materialType.setDeleteTime(MyUtil.getTime());
                    break;
                case 0:
                    break;
                case 1:
                    break;
                default:
                    throw new BadRequestException("state参数错误");
            }

        materialType.setUpdateTime(MyUtil.getTime());
        materialType.setUserid(Integer.parseInt(JwtUtil.getIdFromRequest(request)));

        int i = baseMapper.updateMaterialType(materialType);
        if (i==0){
            throw new BadRequestException(400,"修改失败");
        }
        return materialType;
    }
}

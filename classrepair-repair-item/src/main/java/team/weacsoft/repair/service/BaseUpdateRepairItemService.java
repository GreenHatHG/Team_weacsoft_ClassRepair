package team.weacsoft.repair.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */

@Service
public abstract class BaseUpdateRepairItemService extends BaseRepairItemService{
    /**
     * 识别不同的实现类
     */
    protected String type;

    public String getType() {
        return type;
    }

    /**
     * 获得处理后的RepairItem
     */
    @Transactional
    public RepairItem getRepairItem(String repairItemId, HttpServletRequest request){
        RepairItem repairItem = this.getOne(new QueryWrapper<RepairItem>().eq("repair_item_id",repairItemId));
        if(repairItem == null){
            throw new EntityNotFoundException("RepairItem", "RepairItemId", repairItemId);
        }
        UserInfo userInfo = userInfoService.getById(JwtUtil.getIdFromRequest(request));
        if(userInfo.getName()==null||userInfo.getPhone()==null){
            throw new BadRequestException(400,"请完善个人信息");
        }
        if(repairItem.getState()==null){
            throw new EntityNotFoundException("RepairIteam的","state","为空");
        }
        repairItem =  process(repairItem, userInfo);
        this.updateById(repairItem);
        if(!StringUtils.equals(type, "Check")){
            sendMessage(repairItem, userInfo.getOpenid());
        }
        return this.findByRepairItemId(repairItem.getRepairItemId());
    }

    @Transactional
    protected abstract RepairItem process(RepairItem repairItem, UserInfo userInfo);

    protected void sendMessage(RepairItem repairItem, String openId){
        String state = null;

        switch (RepairItemStateEnum.getEnumByState(repairItem.getState())){
            case PROCESSING:
                state = "处理中"; break;
            case PROCESSED:
                state = "已处理"; break;
            case CANCELLED:
                state = "已取消"; break;
            default:
                break;
        }
        sendMessage(repairItem, openId, state,
                repairItem.getProblem().length() > 20 ?  StrUtil.sub(repairItem.getProblem(), 0, 17) + "..."
                        : repairItem.getProblem());
    }
}
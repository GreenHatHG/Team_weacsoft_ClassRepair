package team.weacsoft.repair.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.wx.TemplateMessage;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.mapper.RepairItemMapper;
import team.weacsoft.user.service.IUserInfoService;

import java.util.Date;

/**
 * <p>
 * 报修表 服务实现类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Service
public abstract class BaseRepairItemService extends ServiceImpl<RepairItemMapper, RepairItem> implements IService<RepairItem> {
    @Autowired
    protected IUserInfoService userInfoService;
    @Autowired
    protected TemplateMessage templateMessage;

    public RepairItem findByRepairItemId(String repairItemId){
        RepairItem repairItem = this.getOne(new QueryWrapper<RepairItem>().eq("repair_item_id",repairItemId));
        if(repairItem == null){
            throw new EntityNotFoundException("RepairItem", "RepairItemId", repairItemId);
        }
        return repairItem;
    }

    /**
     * 生成订单id
     */
    protected String getRepairItemId(){
        //DateUtil.today():当前日期字符串，格式：yyyyMMdd
        return DateUtil.format(new Date(), "yyyyMMdd") + System.currentTimeMillis() / 100;
    }

    /**
     * 发送模板消息
     * @param state 订单状态
     * @param remark 备注
     */
    protected void sendMessage(RepairItem repairItem, String openId, String state, String remark) {
//        if (StringUtils.isNotBlank(openId)) {
//            templateMessage.buildMapAndSend(openId, repairItem.getRepairItemId(),
//                    new StringBuilder().append("地点：").append(repairItem.getClassroom()).append("\\n")
//                            .append("问题：").append(repairItem.getEquipmentType()).append("-").append(repairItem.getProblem()),
//                    state, repairItem.getCreateTime(), remark);
//        }
    }

}

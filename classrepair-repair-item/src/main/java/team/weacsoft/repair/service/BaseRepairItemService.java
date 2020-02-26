package team.weacsoft.repair.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.wx.TemplateMessage;
import team.weacsoft.qa.service.IQaTypeService;
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

    protected IUserInfoService userInfoService;
    protected TemplateMessage templateMessage;
    protected IQaTypeService qaTypeService;

    @Autowired
    public void setUserInfoService(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Autowired
    public void setTemplateMessage(TemplateMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    @Autowired
    public void setQaTypeService(IQaTypeService qaTypeService) {
        this.qaTypeService = qaTypeService;
    }

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
        //规则：当前日期（8位）20200127 + 时间戳后四位数字 + 三位随机数
        return DateUtil.format(new Date(), "yyyyMMdd") + DateUtil.current(Boolean.FALSE) % 10000 + RandomUtil.randomInt(100, 999);
    }

    /**
     * 发送模板消息
     * @param state 订单状态
     * @param remark 备注
     */
    @Async
    protected void sendMessage(RepairItem repairItem, String openId, String state, String remark) {
        if (StringUtils.isNotBlank(openId)) {
            templateMessage.buildMapAndSend(openId, repairItem.getRepairItemId(),
                    new StringBuilder().append("地点：").append(repairItem.getClassroom()).append("\n")
                            .append("问题设备：").append(qaTypeService.getById(repairItem.getEquipmentType()).getTitle()),
                    state, repairItem.getCreateTime(), remark);
        }
    }

}

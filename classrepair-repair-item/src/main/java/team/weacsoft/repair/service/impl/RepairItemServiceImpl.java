//package team.weacsoft.repair.service.impl;
//
//import cn.hutool.core.date.DateUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import team.weacsoft.common.exception.BadRequestException;
//import team.weacsoft.common.exception.EntityNotFoundException;
//import team.weacsoft.common.exception.UnauthorizedException;
//import team.weacsoft.common.utils.JsonUtil;
//import team.weacsoft.common.utils.JwtUtil;
//import team.weacsoft.common.wx.TemplateMessage;
//import team.weacsoft.repair.dto.request.AddRepairItemDto;
//import team.weacsoft.repair.entity.RepairItem;
//import team.weacsoft.repair.mapper.RepairItemMapper;
//import team.weacsoft.repair.service.IRepairItemService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import org.springframework.stereotype.Service;
//import team.weacsoft.user.entity.UserInfo;
//import team.weacsoft.user.service.IUserInfoService;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.Date;
//
///**
// * <p>
// * 报修表 服务实现类
// * </p>
// *
// * @author GreenHatHG
// * @since 2020-01-27
// */
//@Transactional
//@Service
//public class RepairItemServiceImpl extends ServiceImpl<RepairItemMapper, RepairItem> implements IRepairItemService {
//    private IUserInfoService userInfoService;
//    private TemplateMessage templateMessage;
//
//    @Autowired
//    public RepairItemServiceImpl(IUserInfoService userInfoService, TemplateMessage templateMessage) {
//        this.userInfoService = userInfoService;
//        this.templateMessage = templateMessage;
//    }
//
//    /**
//     * 生成订单id
//     */
//    public String getRepairItemId(){
//        //DateUtil.today():当前日期字符串，格式：yyyyMMdd
//        return DateUtil.format(new Date(), "yyyyMMdd") + System.currentTimeMillis() / 100;
//    }
//
//    public RepairItem findByRepairItemId(String repairItemId){
//        RepairItem repairItem = this.getOne(new QueryWrapper<RepairItem>().eq("repair_item_id",repairItemId));
//        if(repairItem == null){
//            throw new EntityNotFoundException("RepairItem", "RepairItemId", repairItemId);
//        }
//        return repairItem;
//    }
//
//    @Override
//    public RepairItem addRepairItem(AddRepairItemDto repairItemDto,  HttpServletRequest request) {
//        String id = JwtUtil.getIdFromHttpServletRequest(request);
//        UserInfo userInfo = userInfoService.getById(id);
//        RepairItem repairItem = new RepairItem();
//        JsonUtil.getCopyDto(repairItemDto, repairItem);
//        repairItem.setRepairItemId(getRepairItemId());
//        repairItem.setOrderer(userInfo.getId());
//        if(repairItem.getOrdererPhone() == null){
//            repairItem.setOrdererPhone(userInfo.getPhone() == null ? "" : userInfo.getPhone());
//        }
//        this.save(repairItem);
//        sendMessage(repairItem, userInfo.getOpenid(), "已下单", "无");
//        return repairItem;
//    }
//
//    public RepairItem receive(String repairItemId, HttpServletRequest request) {
//        RepairItem repairItem = findByRepairItemId(repairItemId);
//        if(repairItem.getState() != 1){
//            throw new BadRequestException(40050, "接单失败，该订单未处于未接状态，订单状态：" + repairItem.getState());
//        }
//        repairItem.setState(2);
//        UserInfo receiver = userInfoService.getById(JwtUtil.getIdFromHttpServletRequest(request));
//        repairItem.setReceiver(receiver.getId());
//        this.updateById(repairItem);
//        sendMessage(repairItem, receiver.getOpenid(), "已接单",  "接单人" + receiver.getName());
//        sendMessage(repairItem, userInfoService.getById(repairItem.getOrderer()).getOpenid(), "已接单",  "接单人" + receiver.getName());
//        return this.findByRepairItemId(repairItemId);
//    }
//
//    public RepairItem cancelRepair(String repairItemId, HttpServletRequest request) {
//        UserInfo canceler = userInfoService.getById(JwtUtil.getIdFromHttpServletRequest(request));
//        RepairItem repairItem = findByRepairItemId(repairItemId);
//        if(canceler.getRole() == 1 && canceler.getId().equals(repairItem.getOrderer())){
//            throw new BadRequestException(40066, "普通人员只能取消自己下的单");
//        }
//        if(!(repairItem.getState() == 1 || repairItem.getState() == 2)){
//            throw new BadRequestException(40077, "取消报修失败，该订单未处于未处理或者处理中状态，订单状态：" + repairItem.getState());
//        }
//        repairItem.setState(4);
//        repairItem.setDeleteTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
//        this.updateById(repairItem);
//        sendMessage(repairItem, canceler.getOpenid(), "已取消", "取消者："+canceler.getName());
//        sendMessage(repairItem, userInfoService.getById(repairItem.getReceiver()).getOpenid(), "已取消", "取消者："+canceler.getName());
//        return this.findByRepairItemId(repairItemId);
//    }
//
//    public RepairItem cancelReceive(String repairItemId, HttpServletRequest request) {
//        RepairItem repairItem = findByRepairItemId(repairItemId);
//        if(repairItem.getState() != 2){
//            throw new BadRequestException(40088, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
//        }
//        repairItem.setState(1);
//        repairItem.setReceiver(0);
//        this.updateById(repairItem);
//        UserInfo canceler = userInfoService.getById(JwtUtil.getIdFromHttpServletRequest(request));
//        sendMessage(repairItem, canceler.getOpenid(), "已取消", "取消者：" + canceler.getName());
//        sendMessage(repairItem, userInfoService.getById(repairItem.getOrderer()).getOpenid(), "已取消", "取消者：" + canceler.getName());
//        return this.findByRepairItemId(repairItemId);
//    }
//
//    @Override
//    public RepairItem complete(String repairItemId, HttpServletRequest request) {
//        RepairItem repairItem = findByRepairItemId(repairItemId);
//        if(repairItem.getState() != 2){
//            throw new BadRequestException(40088, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
//        }
//        repairItem.setState(3);
//        this.updateById(repairItem);
//        UserInfo completer = userInfoService.getById(JwtUtil.getIdFromHttpServletRequest(request));
//        sendMessage(repairItem, completer.getOpenid(), "已完成", "完成者：" + completer.getName());
//        sendMessage(repairItem, userInfoService.getById(repairItem.getOrderer()).getOpenid(), "已完成", "完成者：" + completer.getName());
//        return findByRepairItemId(repairItemId);
//    }
//
//    /**
//     * 发送模板消息
//     * @param state 订单状态
//     * @param remark 备注
//     */
//    private void sendMessage(RepairItem repairItem, String openId, String state, String remark) {
////        if (StringUtils.isNotBlank(openId)) {
////            templateMessage.buildMapAndSend(openId, repairItem.getRepairItemId(),
////                    new StringBuilder().append("地点：").append(repairItem.getClassroom()).append("\\n")
////                            .append("问题：").append(repairItem.getEquipmentType()).append("-").append(repairItem.getProblem()),
////                    state, repairItem.getCreateTime(), remark);
////        }
//    }
//}

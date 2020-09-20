package team.weacsoft.orders.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.orders.entity.RespOrder;
import team.weacsoft.orders.mapper.OrderMapper;
import team.weacsoft.orders.service.GetRepairItemService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/9/12
 */
@Service
public class GetRepairItemServiceImpl implements GetRepairItemService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Page<RespOrder> UserGetOrders(PageRequest pageRequest, Integer state, HttpServletRequest httpServletRequest) {
        IPage<RespOrder> respOrderIPage = orderMapper.AllOrders(PageUtil.getPage(pageRequest), state, null, Integer.parseInt(JwtUtil.getIdFromRequest(httpServletRequest)));
        return (Page<RespOrder>) respOrderIPage;
    }

    @Override
    public Page<RespOrder> AdminGetOrders(PageRequest pageRequest,Integer state, Integer self, HttpServletRequest httpServletRequest) {
        int id = Integer.parseInt(JwtUtil.getIdFromRequest(httpServletRequest));
        Page<RespOrder> orders;
        switch (self){
            case 0:
                //调用获取所有的接口
                orders = (Page<RespOrder>) orderMapper.AllOrders(PageUtil.getPage(pageRequest),state, null, null);
                break;
            case 1:
                //1个人
                orders= (Page<RespOrder>) orderMapper.AllOrders(PageUtil.getPage(pageRequest),state,null,null);
                break;
            case 2:
                //2为他人
                orders= (Page<RespOrder>) orderMapper.OtherOrders(PageUtil.getPage(pageRequest),state,id,null);
                break;
            default:
                throw new BadRequestException("self参数错误");
        }
        return orders;
    }
}

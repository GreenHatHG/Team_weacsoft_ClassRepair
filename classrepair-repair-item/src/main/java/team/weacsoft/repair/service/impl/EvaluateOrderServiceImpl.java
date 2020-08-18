package team.weacsoft.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.repair.entity.Evaluate;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.mapper.RepairItemMapper;
import team.weacsoft.repair.service.EvaluateOrderService;

import javax.servlet.http.HttpServletRequest;

@Service
public class EvaluateOrderServiceImpl extends ServiceImpl<RepairItemMapper, RepairItem>implements EvaluateOrderService{

    @Override
    public Evaluate evaluateOrder(Evaluate evaluate, HttpServletRequest httpServletRequest) {
        if (baseMapper.evaluateOrder(evaluate)==0){
            throw new BadRequestException("评价失败");
        }
        return evaluate;
    }
}

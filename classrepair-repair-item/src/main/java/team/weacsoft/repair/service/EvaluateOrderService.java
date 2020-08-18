package team.weacsoft.repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.repair.entity.Evaluate;
import team.weacsoft.repair.entity.RepairItem;

import javax.servlet.http.HttpServletRequest;

public interface EvaluateOrderService extends IService<RepairItem> {
    Evaluate evaluateOrder(Evaluate evaluate, HttpServletRequest httpServletRequest);
}

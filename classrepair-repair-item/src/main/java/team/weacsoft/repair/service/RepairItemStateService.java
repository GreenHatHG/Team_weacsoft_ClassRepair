package team.weacsoft.repair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.repository.RepairItemRepository;

/**
 * @author GreenHatHG
 */

@Component
public class RepairItemStateService {

    @Autowired
    private RepairItemRepository repairItemRepository;

    public Page<RepairItemDo> getAllMissedOrder(Pageable pageable) {
        return repairItemRepository.
                findByReceiverUserIdAndDeleteTimeAndState("", (long) 0, 1, pageable);
    }

    public Page<RepairItemDo> getMyAllMissedOrders(String userId, Pageable pageable) {
        return repairItemRepository.
                findByOrderUserIdAndReceiverUserIdAndDeleteTimeAndState(userId, "", (long) 0, 1, pageable);
    }

    public Page<RepairItemDo> getMyAllProcessedOrders(String userId, Pageable pageable) {
        return repairItemRepository.
                findByOrderUserIdAndReceiverUserIdNotAndDeleteTimeAndState(userId, "", (long) 0, 3, pageable);
    }
}

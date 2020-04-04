package team.weacsoft.repair.dto.response;

import lombok.Data;

/**
 * @Description
 * @ClassName CountOrderForMaintain
 * @Author 魔法はまだ解けない
 * @date 2020.04.04 15:36
 */

@Data
public class StatisticsFromEquipment {

    /**
     * 学号/工号
     */
    private int  identityId=0;

    /**
     * id
     */
    private int  receiver=0;

    /**
     * 维护人员名字
     */
    private String receiverName="";

    /**
     * 接单次数
     */
    private int frequency=1;

    public void add(){
        this.frequency++;
    }
}

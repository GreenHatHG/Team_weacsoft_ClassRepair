package team.weacsoft.common.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

@Setter
@Getter
@ToString
public class PageRequest implements Serializable {
    /**
     * 当前页面数据量
     */
    private int size = 10;
    /**
     * 当前页码
     */
    private int page = 1;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序规则，asc升序，desc降序
     */
    private boolean asc = true;
}

package team.weacsoft.common.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.weacsoft.common.persistence.PageRequest;

/**
 * @author GreenHatHG
 * @since 2020-01-26
 */
public class PageUtil<T> {

    public static<T> Page<T> getPage(PageRequest pageRequest){
        Page<T> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        if(pageRequest.isAsc()){
            page.addOrder(OrderItem.asc(pageRequest.getSort()));
        }else{
            page.addOrder(OrderItem.desc(pageRequest.getSort()));
        }
        return page;
    }
}

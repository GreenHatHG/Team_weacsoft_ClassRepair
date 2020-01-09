package team.weacsoft.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.Arrays;

/**
 * @author GreenHatHG
 */
public class JsonUtil {

    /**
     * @param object 类
     * @param properties 留下的属性
     * @return 过滤后的类
     */
    public static JSON entityInclude(Object object, String... properties){
        return JSON.parseObject(JSON.toJSONString(object,
                new SimplePropertyPreFilter(properties)));
    }

    public static JSON entityExclude(Object object, String... properties){
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().addAll(Arrays.asList(properties));
        return JSON.parseObject(JSON.toJSONString(object, filter));
    }

    public static JSON arrayExclude(Object object, String... properties){
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().addAll(Arrays.asList(properties));
        return JSONArray.parseArray(JSON.toJSONString(object, filter));
    }

}

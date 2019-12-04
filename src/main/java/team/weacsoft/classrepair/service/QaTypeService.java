package team.weacsoft.classrepair.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.QaType;
import team.weacsoft.classrepair.repository.QaTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author GreenHatHG
 */
@Component
public class QaTypeService {

    @Autowired
    private QaTypeRepository qaTypeRepository;

    public List<Map<String, String>> getAll(){
        List<QaType> qaTypes =  qaTypeRepository.findAll();
        List<Map<String, String>> resp = new ArrayList<>();
        for(QaType qaType : qaTypes) {
            resp.add(ImmutableMap.<String, String>builder()
                    .put("id", String.valueOf(qaType.getSort()))
                    .put("title", qaType.getTitle())
                    .build());
        }
        return resp;
    }
}

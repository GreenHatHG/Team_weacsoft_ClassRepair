package team.weacsoft.classrepair.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.QaType;
import team.weacsoft.classrepair.commons.exception.NotFoundException;
import team.weacsoft.classrepair.repository.QaTypeRepository;

import java.util.*;

/**
 * @author GreenHatHG
 */
@Component
public class QaTypeService {

    @Autowired
    private QaTypeRepository qaTypeRepository;

    public List<Map<String, String>> getAll(){
        List<QaType> qaTypes =  qaTypeRepository.findAll();
        qaTypes.sort(Comparator.comparingInt(QaType::getSort));
        List<Map<String, String>> resp = new ArrayList<>();
        for(QaType qaType : qaTypes) {
            resp.add(ImmutableMap.<String, String>builder()
                    .put("id", qaType.getId())
                    .put("sort", String.valueOf(qaType.getSort()))
                    .put("title", qaType.getTitle())
                    .build());
        }
        return resp;
    }

    public Map<String, String> findById(String id){
        Optional<QaType> optionalQaType = qaTypeRepository.findById(id);
        if(!optionalQaType.isPresent()){
            throw new NotFoundException("找不到对应的分类信息,id:"+id);
        }
        QaType qaType = optionalQaType.get();
        return ImmutableMap.<String, String>builder()
                    .put("id", qaType.getId())
                    .put("sort", String.valueOf(qaType.getSort()))
                    .put("title", qaType.getTitle())
                    .build();
    }
}

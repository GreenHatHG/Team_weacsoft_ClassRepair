package team.weacsoft.qatype.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.qatype.domain.QaTypeDo;
import team.weacsoft.qatype.repository.QaTypeRepository;

import java.util.*;

/**
 * @author GreenHatHG
 */
@Component
public class QaTypeService {

    @Autowired
    private QaTypeRepository qaTypeRepository;

    public List<Map<String, String>> getAll(){
        List<QaTypeDo> qaTypes =  qaTypeRepository.findAll();
        qaTypes.sort(Comparator.comparingInt(QaTypeDo::getSort));
        List<Map<String, String>> resp = new ArrayList<>();
        for(QaTypeDo qaType : qaTypes) {
            resp.add(ImmutableMap.<String, String>builder()
                    .put("id", qaType.getId())
                    .put("sort", String.valueOf(qaType.getSort()))
                    .put("title", qaType.getTitle())
                    .build());
        }
        return resp;
    }

    public Map<String, String> findById(String id){
        Optional<QaTypeDo> optionalQaType = qaTypeRepository.findById(id);
        if(!optionalQaType.isPresent()){
            throw new BadRequestException(404, "找不到对应的分类信息,id:"+id);
        }
        QaTypeDo qaType = optionalQaType.get();
        return ImmutableMap.<String, String>builder()
                    .put("id", qaType.getId())
                    .put("sort", String.valueOf(qaType.getSort()))
                    .put("title", qaType.getTitle())
                    .build();
    }
}

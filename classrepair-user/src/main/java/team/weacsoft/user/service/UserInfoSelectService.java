package team.weacsoft.user.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.repository.UserInfoRepository;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class UserInfoSelectService {

    private UserInfoRepository userInfoRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public UserInfoSelectService(UserInfoRepository userInfoRepository, JwtUtil jwtUtil) {
        this.userInfoRepository = userInfoRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserInfoDo save(UserInfoDo userInfo){
        return userInfoRepository.save(userInfo);
    }

    public UserInfoDo findByOpenid(String openid){
        return userInfoRepository.findByOpenid(openid);
    }

    public UserInfoDo findByIdentityId(long identityId){
        UserInfoDo userInfo = userInfoRepository.findByIdentityId(identityId);
        if(userInfo == null){
            throw new EntityNotFoundException("UserInfo", "identityId", String.valueOf(identityId));
        }
        return userInfo;
    }

    public UserInfoDo findById(String id){
        Optional<UserInfoDo> optionalUserInfo = userInfoRepository.findById(id);
        if(!optionalUserInfo.isPresent()){
            throw new EntityNotFoundException("UserInfo", "id", id);
        }

        return optionalUserInfo.get();
    }

    public Page<UserInfoDo> getUserList(Pageable pageable){
        return userInfoRepository.findAll(pageable);
    }

    /**
     * 根据特定字段可选分页搜索用户
     * @param field 字段
     * @param value 字段值
     * @param pageable 分页
     */
    public Page<UserInfoDo> findByCriteria(String field, String value, Pageable pageable, HttpServletRequest request){
        //避免泄露超级管理员账户密码
        if(StringUtils.equals(field, "role") && StringUtils.equals(value, "5")
                && findById(jwtUtil.getIdFromHttpServletRequest(request)).getRole() != 5){
            throw new UnauthorizedException("权限不足，不能查询该字段");
        }
        return userInfoRepository.findAll((Specification<UserInfoDo>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(field), value)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}

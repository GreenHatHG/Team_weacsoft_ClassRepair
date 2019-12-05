package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.UserInfo;

/**
 * @author GreenHatHG
 **/

@Component
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);

    UserInfo findByIdentityId(long identityId);
}

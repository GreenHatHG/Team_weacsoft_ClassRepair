package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weacsoft.classrepair.bean.ClassRoom;
import team.weacsoft.classrepair.bean.UserInfo;

/**
 * @author GreenHatHG
 **/
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);

}

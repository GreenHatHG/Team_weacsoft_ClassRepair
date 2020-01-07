package team.weacsoft.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import team.weacsoft.user.domain.UserInfoDo;

import java.util.List;

/**
 * @author GreenHatHG
 **/

@Service
public interface UserInfoRepository extends JpaRepository<UserInfoDo, String> {
    UserInfoDo findByOpenid(String openid);

    UserInfoDo findByIdentityId(long identityId);

    List<UserInfoDo> findByName(String name);

    UserInfoDo findByNickname(String nickname);
}

package team.weacsoft.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import team.weacsoft.user.domain.UserInfoDo;

/**
 * @author GreenHatHG
 **/

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoDo, String>, JpaSpecificationExecutor<UserInfoDo> {
    UserInfoDo findByOpenid(String openid);

    UserInfoDo findByIdentityId(long identityId);
}

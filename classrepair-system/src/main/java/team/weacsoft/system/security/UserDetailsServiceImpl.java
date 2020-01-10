//package team.weacsoft.system.security;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import team.weacsoft.user.domain.UserInfoDo;
//import team.weacsoft.user.service.UserInfoSelectService;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Spring Security中为用户加载信息的最常用方法，只要需要有关用户的信息，就会在整个框架中使用它
// * 身份验证成功后，将使用UserDetails来构建存储在SecurityContextHolder中的Authentication对象
// * 注意：
// * UserDetailsService它纯粹是一个用于用户数据的DAO，除了向框架内的其他组件提供数据外，不执行任何其他功能。
// * 特别是，它不会对用户进行身份验证，这是由AuthenticationManager完成的。
// * 在许多情况下，如果需要自定义身份验证过程，直接实现AuthenticationProvider更有意义。
// * @author GreenHatHG
// */
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Value("${classrepair.root.id}")
//    private String rootId;
//
//    @Autowired
//    private UserInfoSelectService userInfoSelectService;
//
//    /**
//     * @param id
//     * @return
//     * UserDetails对象包含了一系列在验证时会用到的信息,包括用户名、密码、权限以及其他信息,Spring Security会根据这些信息判定验证是否成功
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//
//        UserInfoDo userInfoDo = userInfoSelectService.findById(id);
//        //这里按照数据库设定，使用数字作为权限的判别
//        //GrantedAuthority是授予主体的权限。 此类权限通常是“角色”，例如ROLE_ADMINISTRATOR或ROLE_HR_SUPERVISOR
//        //Spring Security 包含一个具体的 GrantedAuthority 实现:SimpleGrantedAuthority。
//        //这允许将任何用户指定的 String 转换为 GrantedAuthority
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//
//        if(!StringUtils.equals(id, rootId)){
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + String.valueOf(5)));
//        }
//        else{
//            if(userInfoDo == null){
//                throw new UsernameNotFoundException("查无此人");
//            }
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + String.valueOf(userInfoDo.getState())));
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                "No settings needed here", "No settings needed here", authorities);
//    }
//}

package team.weacsoft.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.common.error.WxErrorException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.user.dto.common.UpdateRoleDto;
import team.weacsoft.user.dto.reponse.BaseResp;
import team.weacsoft.user.dto.reponse.GetUserInfoByTokenResp;
import team.weacsoft.user.dto.request.GetPhoneDto;
import team.weacsoft.user.dto.request.UpdateUserInfoDto;
import team.weacsoft.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 根据token返回user对应的信息
     */
    GetUserInfoByTokenResp getUserInfoByToken(HttpServletRequest request);

    /**
     * 修改用户身份
     * @param id 要修改的用户的id
     * @param role 修改后的role
     * @return
     */
    UpdateRoleDto updateRoleById(String id, Integer role);

    /**
     * 根据特定字段搜索用户
     * @param field 字段
     * @param value 字段值
     * @param pageRequest 分页
     * @param request
     * @return
     */
    Page<UserInfo> getUserInfoByField(String field, String value, PageRequest pageRequest, HttpServletRequest request);

    /**
     * 修改用户自己信息，注意防止恶意修改密码，openId等字段
     */
    BaseResp updateMyUserInfo(HttpServletRequest request, UpdateUserInfoDto dto);

    /**
     * 修改别人的用户信息
     */
    BaseResp updateOtherUserInfo(Integer id, UpdateUserInfoDto dto);
    /**
     * 分页获取用户列表
     */
    Page<UserInfo> getUserList(PageRequest pageRequest);

    /**
     * 获取用户手机号
     */
    Map<String, String> getPhone(GetPhoneDto dto, HttpServletRequest request) throws WxErrorException;
}

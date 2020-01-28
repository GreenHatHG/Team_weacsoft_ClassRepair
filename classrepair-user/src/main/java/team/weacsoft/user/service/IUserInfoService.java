package team.weacsoft.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.user.dto.request.UpdateUserInfoDto;
import team.weacsoft.user.dto.common.UpdateRoleDto;
import team.weacsoft.user.dto.reponse.BaseResp;
import team.weacsoft.user.dto.reponse.GetUserInfoByTokenResp;
import team.weacsoft.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

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
     * 修改用户信息，注意防止恶意修改密码，openId等字段
     * @param request
     * @param dto
     * @return
     */
    BaseResp updateUserInfo(HttpServletRequest request, UpdateUserInfoDto dto);

    /**
     * 分页获取用户列表
     * @return
     */
    Page<UserInfo> getUserList(PageRequest pageRequest);
}

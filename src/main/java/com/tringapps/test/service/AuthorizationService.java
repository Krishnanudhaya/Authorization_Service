package com.tringapps.test.service;

import com.tringapps.test.vo.PrivilegeVO;
import com.tringapps.test.vo.ResponseVO;
import com.tringapps.test.vo.RoleVO;
import com.tringapps.test.vo.UserVO;

public interface AuthorizationService {

    ResponseVO addRole(RoleVO roleVO);

    ResponseVO addPrivilege(PrivilegeVO privilegeVO);

    ResponseVO setRolePrivilege(RoleVO roleVO);

    ResponseVO addUser(UserVO userVO);

    ResponseVO setUserRole(UserVO userVO);

    ResponseVO fetchUserPrivilege(Integer userId);
}

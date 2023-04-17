package com.tringapps.test.service.impl;

import com.tringapps.test.model.Privilege;
import com.tringapps.test.model.Role;
import com.tringapps.test.model.User;
import com.tringapps.test.repository.PrivilegeRepository;
import com.tringapps.test.repository.RoleRepository;
import com.tringapps.test.repository.UserRepository;
import com.tringapps.test.service.AuthorizationService;
import com.tringapps.test.vo.PrivilegeVO;
import com.tringapps.test.vo.ResponseVO;
import com.tringapps.test.vo.RoleVO;
import com.tringapps.test.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseVO addRole(RoleVO roles) {
        ResponseVO responseVO = new ResponseVO();
        try {
            Role roleEntity = roleRepository.save(new Role(roles.getName()));
            responseVO.setSuccess(true);
            responseVO.setStatusMessage("Role added successfully");
            responseVO.setData(roleEntity.getId());
        } catch (Exception ex) {
            logger.error("AuthorizationServiceImpl :: addRole ::" + ex.getStackTrace());
        }
        return responseVO;
    }

    @Override
    public ResponseVO addPrivilege(PrivilegeVO privilegeVO) {
        ResponseVO responseVO = new ResponseVO();
        try {
            Privilege privilegeEntity = privilegeRepository.save(new Privilege(privilegeVO.getName()));
            responseVO.setSuccess(true);
            responseVO.setStatusMessage("Privilege added successfully");
            responseVO.setData(privilegeEntity.getId());
        } catch (Exception ex) {
            logger.error("AuthorizationServiceImpl :: addPrivilege  ::" + ex.getStackTrace());
        }
        return responseVO;
    }

    @Override
    public ResponseVO setRolePrivilege(RoleVO roleVO) {
        ResponseVO responseVO = new ResponseVO();
        try {
            Integer roleId = roleVO.getId();
            List<Integer> privilegeIds = roleVO.getPrivilegeIds();
            // TODO : Null check :: the roleId and privilegeIds
            if (Objects.nonNull(roleId) && Objects.nonNull(privilegeIds) && privilegeIds.size() > 0) {
                Optional<Role> roleObj = roleRepository.findById(roleId);
                List<Privilege> privileges = privilegeRepository.findByIdIn(privilegeIds);
                if (roleObj.isPresent()) {
                    roleObj.get().setPrivileges(privileges);
                    roleRepository.save(roleObj.get());
                    responseVO.setSuccess(true);
                    responseVO.setStatusMessage("Role Privilege added successfully");
                }
            }
        } catch (Exception ex) {
            logger.error("AuthorizationServiceImpl :: setRolePrivilege ::" + ex.getStackTrace());
        }
        return responseVO;
    }

    @Override
    public ResponseVO addUser(UserVO userVO) {
        ResponseVO responseVO = new ResponseVO();
        try {
            User userEntity = userRepository.save(new User(userVO.getEmail()));
            responseVO.setSuccess(true);
            responseVO.setStatusMessage("User added successfully");
            responseVO.setData(userEntity.getId());
        } catch (Exception ex) {
            logger.error("AuthorizationServiceImpl :: addUser ::" + ex.getStackTrace());
        }
        return responseVO;
    }

    @Override
    public ResponseVO setUserRole(UserVO userVO) {
        ResponseVO responseVO = new ResponseVO();
        try {
            Integer userId = userVO.getId();
            List<Integer> roleIds = userVO.getRoleIds();
            // TODO : Null check :: the roleId and privilegeIds
            if (Objects.nonNull(userId) && Objects.nonNull(roleIds) && roleIds.size() > 0) {
                Optional<User> userObj = userRepository.findById(userId);
                List<Role> roles = roleRepository.findByIdIn(roleIds);
                if (userObj.isPresent()) {
                    userObj.get().setRoles(roles);
                    userRepository.save(userObj.get());
                    responseVO.setSuccess(true);
                    responseVO.setStatusMessage("User Role added successfully");
                }
            }
        } catch (Exception ex) {
            logger.error("AuthorizationServiceImpl :: setUserRole ::" + ex.getStackTrace());
        }
        return responseVO;
    }

    @Override
    public ResponseVO fetchUserPrivilege(Integer userId) {
        ResponseVO responseVO = new ResponseVO();
        try {
            List<Privilege> privileges = privilegeRepository.findPrivilegesByUserId(userId);
            List<PrivilegeVO> privilegeVOS = privileges.stream().map(e -> {
                PrivilegeVO privilegeVO = new PrivilegeVO();
                BeanUtils.copyProperties(e, privilegeVO);
                return privilegeVO;
            }).collect(Collectors.toList());
            responseVO.setSuccess(true);
            responseVO.setData(privilegeVOS);
            responseVO.setStatusMessage("User Privileges fetched successfully");
        } catch (Exception ex) {
            logger.error("AuthorizationServiceImpl :: fetchUserPrivilege ::" + ex.getStackTrace());
        }
        return responseVO;
    }
}

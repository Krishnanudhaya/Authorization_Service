package com.tringapps.test.controller;


import com.tringapps.test.service.AuthorizationService;
import com.tringapps.test.vo.PrivilegeVO;
import com.tringapps.test.vo.ResponseVO;
import com.tringapps.test.vo.RoleVO;
import com.tringapps.test.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/role")
    public ResponseEntity<?> addRole(@RequestBody RoleVO roleVO){
        ResponseVO responseVO = authorizationService.addRole(roleVO);
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }

    @PostMapping("/privilege")
    public ResponseEntity<?> addPrivilege(@RequestBody PrivilegeVO privilegeVO){
        ResponseVO responseVO = authorizationService.addPrivilege(privilegeVO);
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody UserVO userVO){
        ResponseVO responseVO = authorizationService.addUser(userVO);
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }


    @PostMapping("/setRolePrivilege")
    public ResponseEntity<?> setRolePrivilege(@RequestBody RoleVO roleVO){
        ResponseVO responseVO = authorizationService.setRolePrivilege(roleVO);
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }


    @PostMapping("/setUserRole")
    public ResponseEntity<?> setUserRole(@RequestBody UserVO userVO){
        ResponseVO responseVO = authorizationService.setUserRole(userVO);
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }

    @GetMapping("/fetchUserPrivilege")
    public ResponseEntity<?> fetchUserPrivilege(@RequestParam(value = "userId")Integer userId) {
        ResponseVO responseVO = authorizationService.fetchUserPrivilege(userId);
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }

}

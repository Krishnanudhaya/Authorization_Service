package com.tringapps.test.vo;

import java.util.List;

public class RoleVO {

    private Integer id;

    private String name;

    private List<Integer> privilegeIds;

    public RoleVO() {
    }

    public RoleVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(List<Integer> privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}

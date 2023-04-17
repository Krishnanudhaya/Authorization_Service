package com.tringapps.test.repository;


import com.tringapps.test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    @Override
    void delete(Role role);

    List<Role> findByIdIn(List<Integer> roleIds);
}

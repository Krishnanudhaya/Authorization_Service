package com.tringapps.test.repository;

import com.tringapps.test.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

    List<Privilege> findByIdIn(List<Integer> privilegeIds);

    @Query(nativeQuery = true,value = "select p.* from users_roles ur " +
            "join roles_privileges rp on ur.role_id =rp.role_id " +
            "join privilege p on p.id=rp.privilege_id " +
            "where ur.user_id=?1 ")
    List<Privilege> findPrivilegesByUserId(Integer userId);
}

package com.tringapps.test.repository;

import com.tringapps.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}

package com.swapp.swapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swapp.swapp.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String userName);
}

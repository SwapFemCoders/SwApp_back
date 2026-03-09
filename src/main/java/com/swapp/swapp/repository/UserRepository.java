package com.swapp.swapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapp.swapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

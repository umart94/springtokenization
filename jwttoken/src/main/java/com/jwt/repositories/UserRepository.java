package com.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
	UserEntity findByUsername(String username);
}

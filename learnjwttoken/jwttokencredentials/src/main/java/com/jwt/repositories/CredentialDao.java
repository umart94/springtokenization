package com.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entities.Credential;

@Repository
public interface CredentialDao extends JpaRepository<Credential, Integer> {
	Credential findByUsername(String username);
	Credential findByUsernameAndChannelName(String username,String channelName);
	
}
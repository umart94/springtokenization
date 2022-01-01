package com.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwt.entities.Channel;

@Repository
public interface ChannelDao extends JpaRepository<Channel, Integer> {
	//Channel findByUsername(String username);
	
	@Query(value = "SELECT * FROM channel a WHERE a.channel_name = :channelName AND a.credential_id= :credential ", nativeQuery = true)
	public Channel getChannelIdByChannelAndCredential(String channelName,int credential);
	
}

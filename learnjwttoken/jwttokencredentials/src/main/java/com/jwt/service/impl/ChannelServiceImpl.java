package com.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.entities.Channel;
import com.jwt.entities.Credential;
import com.jwt.repositories.ChannelDao;
import com.jwt.repositories.CredentialDao;
import com.jwt.request.AuthenticationRequest;
import com.jwt.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService{

	
	@Autowired
	private CredentialDao credentialDao;
	
	
	@Autowired
	private ChannelDao channelDao;
	
	@Override
	public Channel saveCustomerChannel(AuthenticationRequest authenticationRequest) {
		// TODO Auto-generated method stub
		Credential credentialDetails = credentialDao.findByUsername(authenticationRequest.getUsername());
		Channel newChannel = new Channel();
		newChannel.setChannelName(authenticationRequest.getChannel());
		newChannel.setCredential(credentialDetails);
		return (Channel) channelDao.save(newChannel);
	}

}

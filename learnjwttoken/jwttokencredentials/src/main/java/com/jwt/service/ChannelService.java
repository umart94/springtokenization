package com.jwt.service;

import com.jwt.entities.Channel;
import com.jwt.request.AuthenticationRequest;

public interface ChannelService {


	public Channel saveCustomerChannel(AuthenticationRequest authenticationRequest);
}

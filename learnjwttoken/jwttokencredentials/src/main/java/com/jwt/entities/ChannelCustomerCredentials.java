package com.jwt.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "channel_customer_credentials")

public class ChannelCustomerCredentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2516248315446279127L;

	@Id
	@GeneratedValue
	private Long ID;
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;

	@Column(name = "cnic")
	private String cnic;
	
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "account_title")
	private String accountTitle;

	@Column(name = "channel_pin")
	private String channelPin;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "retry_count")
	private int retryCount;
	
	@Column(name = "is_default")
	private boolean isDefault;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getCnic() {
		return cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountTitle() {
		return accountTitle;
	}

	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}

	public String getChannelPin() {
		return channelPin;
	}

	public void setChannelPin(String channelPin) {
		this.channelPin = channelPin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}

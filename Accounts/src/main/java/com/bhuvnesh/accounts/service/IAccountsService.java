package com.bhuvnesh.accounts.service;

import com.bhuvnesh.accounts.dto.CustomerDto;

public interface IAccountsService {

	/*
	 * @param CustomerDto
	*/
	void createAccount(CustomerDto customerDto);
	
	CustomerDto fetchAccount(String mobileNumber);
	
	boolean updateAccount(CustomerDto customerDto);
	boolean deleteAccount(String  mobileNumber);
	
}

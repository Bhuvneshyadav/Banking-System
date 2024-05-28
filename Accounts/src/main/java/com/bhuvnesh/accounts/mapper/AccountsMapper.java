package com.bhuvnesh.accounts.mapper;

import com.bhuvnesh.accounts.dto.AcccountsDto;
import com.bhuvnesh.accounts.entity.Accounts;

public class AccountsMapper {
	
	public static AcccountsDto mapToAccountsDto(Accounts accounts, AcccountsDto accountsDto)
	{
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}
	public static Accounts mapToAccounts(AcccountsDto accountsDto, Accounts accounts)
	{
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}
}

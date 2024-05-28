package com.bhuvnesh.accounts.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bhuvnesh.accounts.constants.AccountsConstants;
import com.bhuvnesh.accounts.dto.AcccountsDto;
import com.bhuvnesh.accounts.dto.CustomerDto;
import com.bhuvnesh.accounts.entity.Accounts;
import com.bhuvnesh.accounts.entity.Customer;
import com.bhuvnesh.accounts.exception.CustomerAlreadyExistsException;
import com.bhuvnesh.accounts.exception.ResourceNotFoundException;
import com.bhuvnesh.accounts.mapper.AccountsMapper;
import com.bhuvnesh.accounts.mapper.CustomerMapper;
import com.bhuvnesh.accounts.repository.AccountsRespository;
import com.bhuvnesh.accounts.repository.CustomerRespository;
import com.bhuvnesh.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service //act as a service 
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService{
	
	private AccountsRespository accountsRespository;
	private CustomerRespository customerRepository;
	
	
	
	public void createAccount(CustomerDto customerDto) {
		
		Customer customer=CustomerMapper.maptoCustomer(customerDto, new Customer());
		//find the duplicate mobile number;
		Optional<Customer> newCustomer=customerRepository.findByMobileNumber(customer.getMobileNumber());
		if(newCustomer.isPresent())
		{
			throw new CustomerAlreadyExistsException("Customer already registered with given mobile number"+ customerDto.getMobileNumber());
		}
		
		Customer savedCustomer=customerRepository.save(customer);
		
		accountsRespository.save(createNewAccount(savedCustomer));
		
	}
	
	private Accounts createNewAccount(Customer customer)
	{
		Accounts newAccount= new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber=1000000000L+new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		newAccount.setAccountType(AccountsConstants.SAVING);
		
		return newAccount;
	}
	
	@Override
	public CustomerDto fetchAccount(String mobileNumber)
	{
		Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
				);
		
		Accounts account=accountsRespository.findBycustomerId(customer.getCustomerId()).orElseThrow(
				()-> new ResourceNotFoundException("Account", "customer", customer.getCustomerId().toString())
				);
		CustomerDto customerDto=CustomerMapper.maptoCustomerDto(customer, new CustomerDto());
		AcccountsDto accountDto=  AccountsMapper.mapToAccountsDto(account, new AcccountsDto());
		customerDto.setAccountDto(accountDto);
		
		
		return customerDto;
		
	}
	

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		boolean isUpdate=false;
		AcccountsDto accountDto=customerDto.getAccountDto();
		if(accountDto!= null)
		{
			Accounts accounts = accountsRespository.findByAccountNumber(accountDto.getAccountNumber()).orElseThrow(
					()-> new ResourceNotFoundException("Account","AccountNumber",accountDto.getAccountNumber().toString())
					);
			
		
		AccountsMapper.mapToAccounts(accountDto, accounts);
		accounts=accountsRespository.save(accounts);
		
		Long customerId= accounts.getCustomerId();
		Customer customer = customerRepository.findById(customerId).orElseThrow(
				()-> new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
				);
		CustomerMapper.maptoCustomer(customerDto, customer);
		customerRepository.save(customer);
		isUpdate=true;
		}
		return isUpdate;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		// TODO Auto-generated method stub
		
		
		Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
				);
		accountsRespository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		
		return true;
	}
	
	

}

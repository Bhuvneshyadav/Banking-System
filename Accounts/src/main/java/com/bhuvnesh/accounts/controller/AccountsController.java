package com.bhuvnesh.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhuvnesh.accounts.constants.AccountsConstants;
import com.bhuvnesh.accounts.dto.CustomerDto;
import com.bhuvnesh.accounts.dto.ResponseDto;
import com.bhuvnesh.accounts.service.IAccountsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
@Validated
public class AccountsController {
	@Autowired
	public IAccountsService iAccountsService;
	
// write all the methods for rest
	@PostMapping("/create")
	public ResponseEntity<ResponseDto>  createAccount(@Valid @RequestBody CustomerDto customerDto)
	{
		//iAccountsService=new AccountServiceImpl();
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
								.body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
		
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam 
			@Pattern(regexp="(^$|[0-9]{10})", message ="Mobile Number must be 10 digits")
			String mobileNumber)
	{
		CustomerDto customerDto=iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody  CustomerDto customerDto)
	{
		boolean isUpdate=iAccountsService.updateAccount(customerDto);
		if(isUpdate)
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
		}
		else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
		}
	}
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam 
			@Pattern(regexp="(^$|[0-9]{10})", message ="Mobile Number must be 10 digits")
			String mobileNumber)
	{
		boolean isDelete =iAccountsService.deleteAccount(mobileNumber);
		if(isDelete)
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
		}
		else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
		}
	}
		
	
}

package com.bhuvnesh.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AcccountsDto {
	
	@NotEmpty(message ="AccountNumber can not be a null or empty")
	@Pattern(regexp="(^$|[0-9]{10})", message ="Account Number must be 10 digits")
	private Long accountNumber;
	
	@NotEmpty(message="Account Type can not be a null or empty")
	private String accountType;
	
	@NotEmpty(message="Branch Address can not be null")
	private String branchAddress;
}
package com.bhuvnesh.accounts.mapper;

import com.bhuvnesh.accounts.dto.CustomerDto;
import com.bhuvnesh.accounts.entity.Customer;

public class CustomerMapper {

		public static CustomerDto maptoCustomerDto(Customer customer, CustomerDto customerDto)
		{
			customerDto.setName(customer.getName());
			customerDto.setEmail(customer.getEmail());
			customerDto.setMobileNumber(customer.getMobileNumber());
			
			return customerDto;
		}
		
		public static Customer maptoCustomer(CustomerDto customerDto, Customer customer)
		{
			customer.setName(customerDto.getName());
			customer.setEmail(customerDto.getEmail());
			customer.setMobileNumber(customerDto.getMobileNumber());
			return customer;
		}
}

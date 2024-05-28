package com.bhuvnesh.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhuvnesh.accounts.entity.Customer;

@Repository
public interface CustomerRespository  extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByMobileNumber(String mobileNumber);

}

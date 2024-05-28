package com.bhuvnesh.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bhuvnesh.accounts.entity.Accounts;
import com.bhuvnesh.accounts.entity.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface AccountsRespository  extends JpaRepository<Accounts, Long>{
	
	Optional<Accounts> findBycustomerId(Long customerId);
	Optional<Accounts> findByAccountNumber(Long accountNumber);

	@Transactional
	@Modifying
	Optional<Accounts> deleteByCustomerId(Long accountNumber);
	
}

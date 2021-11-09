package com.testoper.bankservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.entity.Bank;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByIdAndBank(Long accountId, Bank bank);

}

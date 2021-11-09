package com.testoper.bankservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testoper.bankservice.entity.ApplicationUser;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

	Optional<ApplicationUser> findByUsername(String username);

}

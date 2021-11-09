package com.testoper.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testoper.paymentservice.entity.Payment;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
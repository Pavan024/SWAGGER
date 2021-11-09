package com.testoper.bankservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testoper.bankservice.common.Constants;
import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.exception.NotFoundException;
import com.testoper.bankservice.repository.BankRepository;
import com.testoper.bankservice.request.CreateBankRequest;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@Service
public class BankService {

	@Autowired
	BankRepository bankRepository;

	private static final Logger log = LoggerFactory.getLogger(BankService.class);

	/**
	 * 
	 * Creates Bank and saves into the Map
	 * 
	 * @param createBankRequest
	 * @return
	 */
	public Bank createBank(CreateBankRequest createBankRequest) {
		ModelMapper modelMapper = new ModelMapper();
		Bank bank = modelMapper.map(createBankRequest, Bank.class);
		log.info("Creating Bank");
		Bank createdBank = bankRepository.save(bank);
		log.info("Bank created");
		return createdBank;
	}

	/**
	 * 
	 * Gets Bank based on the Bank Id
	 * 
	 * @param id
	 * @return
	 */
	public Bank getBank(Long id) {
		Optional<Bank> bank = bankRepository.findById(id);
		if (!bank.isPresent()) {
			throw new NotFoundException(Constants.BANK_NOT_FOUND);
		}
		return bank.get();
	}

}

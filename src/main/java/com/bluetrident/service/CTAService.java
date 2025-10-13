package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.config.exception.NotFoundException;
import com.bluetrident.entity.CustomerTransactionAttribute;
import com.bluetrident.entity.TokenPayload;

@Service
public interface CTAService {
	
	/**
	 * 
	 * @param siteId
	 * @param customerBusinessId
	 * @param customerId
	 * @param userId
	 * @return
	 * @throws CustomerNotFoundException
	 */
	public CustomerTransactionAttribute getCustomerTransactionAttribute(Long siteId, Long customerBusinessId,
			Long customerId, Long userId, String username) throws NotFoundException;

	/**
	 * 
	 * @param siteId
	 * @param tokenPayload
	 * @return
	 * @throws ApplicationException
	 */
	public CustomerTransactionAttribute getCustomerTransactionAttribute(Long siteId, TokenPayload tokenPayload)
			throws ApplicationException;

	

}

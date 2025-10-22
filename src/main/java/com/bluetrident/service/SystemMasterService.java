package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.entity.SystemMaster;

@Service
public interface SystemMasterService {
	
	/**
	 * 
	 * @param identifierCode
	 * @return
	 * @throws ApplicationException
	 */
	SystemMaster getGenderByCode(String identifierCode) throws ApplicationException;

}

package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.entity.SystemMaster;
import com.bluetrident.repository.ISystemMasterRepository;
import com.bluetrident.service.SystemMasterService;

@Service
public class SystemMasterServiceImpl implements SystemMasterService {
	
	@Autowired
	ISystemMasterRepository systemMasterRepository;

	@Override
	public SystemMaster getGenderByCode(String identifierCode) throws ApplicationException {
		if (identifierCode == null) {
			return null;
		}
		SystemMaster gender = null;
		try {
			gender = this.systemMasterRepository.findByIdentifierCode(identifierCode).get(0);
		} catch (Exception e) {

		}
		return gender;
	}

}

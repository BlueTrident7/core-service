package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.config.exception.NotFoundException;
import com.bluetrident.entity.CustomerBusinessSite;
import com.bluetrident.entity.CustomerTransactionAttribute;
import com.bluetrident.entity.TokenPayload;
import com.bluetrident.repository.ICustomerBusinessSiteRepository;
import com.bluetrident.service.CTAService;
import com.bluetrident.util.AppUtil;

@Service
public class CTAServiceImpl implements CTAService {

	@Autowired
	ICustomerBusinessSiteRepository customerBusinessSiteRepository;

	@Override
	public CustomerTransactionAttribute getCustomerTransactionAttribute(Long siteId, Long customerBusinessId,
			Long customerId, Long userId, String username) throws NotFoundException {
		CustomerTransactionAttribute cta = new CustomerTransactionAttribute();
		CustomerBusinessSite customerBusinessSite = new CustomerBusinessSite();
		try {
			if (siteId != null) {
				customerBusinessSite = this.customerBusinessSiteRepository.findBySiteId(siteId).get(0);
			} else if (customerBusinessId != null) {
				customerBusinessSite = this.customerBusinessSiteRepository.findByCustomerBusinessId(customerBusinessId)
						.get(0);
			} else {
				customerBusinessSite = this.customerBusinessSiteRepository.findByCustomerId(customerId).get(0);
			}
		} catch (Exception e) {
			throw new NotFoundException("Customer Business Site not found!!");
		}
		if (customerBusinessSite != null) {
			cta.setCustomerBusinessId(customerBusinessSite.getCustomerBusinessId());
			cta.setCustomerId(customerBusinessSite.getCustomerId());
			cta.setSiteId(customerBusinessSite.getSiteId());
			cta.setActive(true);
			cta.setCreatedBy(userId);
			cta.setCreatedUser(username);
			try {
				cta.setCreatedDate(AppUtil.getCurrentUtcDateTime());
			} catch (Exception e) {
			}
			cta.setLastModifiedBy(userId);
			try {
				cta.setLastModifiedDate(AppUtil.getCurrentUtcDateTime());
			} catch (Exception e) {
			}
		}
		return cta;
	}

	@Override
	public CustomerTransactionAttribute getCustomerTransactionAttribute(Long siteId, TokenPayload tokenPayload)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}

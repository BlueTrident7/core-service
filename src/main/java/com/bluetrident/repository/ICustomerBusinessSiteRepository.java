package com.bluetrident.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluetrident.entity.CustomerBusinessSite;

@Repository
public interface ICustomerBusinessSiteRepository extends JpaRepository<CustomerBusinessSite, Long> {
	/**
	 * 
	 * @param siteId
	 * @return
	 */
	List<CustomerBusinessSite> findBySiteId(Long siteId);

	/**
	 * 
	 * @param customerId
	 * @return
	 */
	List<CustomerBusinessSite> findByCustomerId(Long customerId);

	/**
	 * 
	 * @param customerBusinessId
	 * @return
	 */
	List<CustomerBusinessSite> findByCustomerBusinessId(Long customerBusinessId);
}

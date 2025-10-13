package com.bluetrident.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CustomerTransactionAttribute extends Auditable<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "customer_business_id")
	private Long customerBusinessId;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "site_id")
	private Long siteId;

}

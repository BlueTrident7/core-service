package com.bluetrident.entity;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_business_site")
@SQLDelete(sql = "UPDATE customer_business_site SET active = false WHERE id=?")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerBusinessSite extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long siteId;

	@NotNull
	private String site;

	@NotNull
	private Long customerId;

	@NotNull
	private Long customerBusinessId;

	private String imageUrl;

	

}

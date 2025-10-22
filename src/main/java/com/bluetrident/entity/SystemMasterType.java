package com.bluetrident.entity;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "system_master", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "identifier_code", "type_id" }) })
@SQLDelete(sql = "UPDATE system_master SET active = false WHERE id=?")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemMasterType  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "name_en")
	private String nameEn;

	@Size(max = 255)
	private String description;

	@NotNull
	@Column(name = "identifier_code", unique = true)
	@Size(max = 50)
	private String identifierCode;

}

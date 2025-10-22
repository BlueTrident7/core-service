package com.bluetrident.entity;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
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
public class SystemMaster  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@NotNull
	@Column(name = "name_en")
	private String nameEn;

	@Size(max = 255)
	private String description;

	@NotNull
	@Column(name = "identifier_code")
	@Size(max = 50)
	private String identifierCode;
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "type_Id", referencedColumnName = "id", nullable = false),
			@JoinColumn(name = "type_identifier", referencedColumnName = "identifier_code", nullable = false) })
	@JsonIgnore
	private SystemMasterType masterType;

}

package com.bluetrident.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluetrident.entity.SystemMaster;

@Repository
public interface ISystemMasterRepository extends JpaRepository<SystemMaster, Long> {

	List<SystemMaster> findByIdentifierCode(String identifierCode);
}

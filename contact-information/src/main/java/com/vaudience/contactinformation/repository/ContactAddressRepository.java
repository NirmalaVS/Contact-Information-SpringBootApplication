package com.vaudience.contactinformation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaudience.contactinformation.entity.ContactAddress;
import com.vaudience.contactinformation.entity.ContactBasicInfo;

/**
 * This is the repository class for ContactAddress
 * 
 * @author Nirmala Sivakumar
 *
 */

@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress, Long> {
	
	List<ContactBasicInfo> findByAddressIdIn(List<Long> addressIdList);
	
	@Query(value = "SELECT Address_Id FROM Contact_Address WHERE Zip_Code = ?1", nativeQuery = true)
	List<Long> findByZipCode(int zipCode);
}

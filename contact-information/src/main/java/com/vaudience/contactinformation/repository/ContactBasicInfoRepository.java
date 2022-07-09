package com.vaudience.contactinformation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaudience.contactinformation.entity.ContactBasicInfo;

@Repository
public interface ContactBasicInfoRepository extends JpaRepository<ContactBasicInfo, Long> {

	@Query(value = "SELECT * FROM Contact_Basic_Info WHERE Contact_Id = ?1", nativeQuery = true)
	Optional<ContactBasicInfo> findByContactId(String contactId);

}

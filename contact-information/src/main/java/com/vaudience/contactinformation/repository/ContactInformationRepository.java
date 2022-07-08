package com.vaudience.contactinformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaudience.contactinformation.entity.ContactBasicInfo;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactBasicInfo, Long>{

}

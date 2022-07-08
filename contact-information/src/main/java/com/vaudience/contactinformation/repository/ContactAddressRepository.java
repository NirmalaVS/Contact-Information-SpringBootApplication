package com.vaudience.contactinformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaudience.contactinformation.entity.ContactAddress;

@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress, Long> {

}

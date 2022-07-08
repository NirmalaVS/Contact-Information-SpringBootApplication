package com.vaudience.contactinformation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaudience.contactinformation.entity.ContactBasicInfo;
import com.vaudience.contactinformation.exceptionhandlers.RecordNotFoundException;
import com.vaudience.contactinformation.repository.ContactAddressRepository;
import com.vaudience.contactinformation.repository.ContactBasicInfoRepository;

@Service
public class ContactInformationService {

	@Autowired
	ContactBasicInfoRepository contactBasicInfoRepository;

	@Autowired
	ContactAddressRepository contactAddressRepository;
	
	/**
	 * This is service impl for getContactInformation method
	 * Retrieves all the contactInformation stored in Database
	 * 
	 * @return List
	 */

	public List<ContactBasicInfo> getContactInformation() {
		List<ContactBasicInfo> contactBasicInfoList = contactBasicInfoRepository.findAll();
		if (!contactBasicInfoList.isEmpty()) {
			return contactBasicInfoList;
		} else {
			return new ArrayList<ContactBasicInfo>();
		}
	}
	
	/**
	 * This is service impl for saveOrUpdateContactInformation method
	 * Either saves new record or updates existing record based on contact Id
	 * 
	 * @param contactBasicInfo
	 * @return
	 */

	public ContactBasicInfo saveOrUpdateContactInformation(ContactBasicInfo contactBasicInfo) {
		if (contactBasicInfo.getContactId() == null) {
			return contactBasicInfoRepository.save(contactBasicInfo);
		} else {
			Optional<ContactBasicInfo> contactBasicInfoOptional = contactBasicInfoRepository
					.findByContactId(contactBasicInfo.getContactId());
			if (contactBasicInfoOptional.isPresent()) {
				ContactBasicInfo contactBasicInfoDb = contactBasicInfoOptional.get();
				contactBasicInfoDb.setContactAddress(contactBasicInfo.getContactAddress());
				contactBasicInfoDb.setFullName(contactBasicInfo.getFullName());
				contactBasicInfoDb.setDateOfBirth(contactBasicInfo.getDateOfBirth());
				contactBasicInfoDb = contactBasicInfoRepository.save(contactBasicInfoDb);

				return contactBasicInfoDb;
			} else {
				throw new RecordNotFoundException(
						"No Contact information available for given contact Id " + contactBasicInfo.getContactId());
			}
		}
	}

}

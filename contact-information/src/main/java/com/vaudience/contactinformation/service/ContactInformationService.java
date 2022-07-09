package com.vaudience.contactinformation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vaudience.contactinformation.entity.ContactBasicInfo;
import com.vaudience.contactinformation.repository.ContactAddressRepository;
import com.vaudience.contactinformation.repository.ContactBasicInfoRepository;

@Service
public class ContactInformationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactInformationService.class);

	@Autowired
	ContactBasicInfoRepository contactBasicInfoRepository;

	@Autowired
	ContactAddressRepository contactAddressRepository;

	/**
	 * This is service impl for getContactInformation method Retrieves all the
	 * contactInformation stored in Database
	 * 
	 * @return List
	 */
	public ResponseEntity<List<ContactBasicInfo>> getContactInformation() {
		try {
			List<ContactBasicInfo> contactBasicInfoList = contactBasicInfoRepository.findAll();
			if (contactBasicInfoList.isEmpty()) {
				LOGGER.debug("No Record is available in database!!");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			LOGGER.debug("List of contact information available {}", contactBasicInfoList.toArray());
			return new ResponseEntity<>(contactBasicInfoList, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error while retriving contact information in method getContactInformation {}",
					ex.getMessage(), ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This is service impl for saveOrUpdateContactInformation method Either saves
	 * new record or updates existing record based on contact Id
	 * 
	 * @param contactBasicInfo
	 * @return
	 */

	public ResponseEntity<ContactBasicInfo> saveOrUpdateContactInformation(ContactBasicInfo contactBasicInfo) {
		try {
			if (contactBasicInfo.getContactId() == null) {
				contactBasicInfoRepository.save(contactBasicInfo);
				LOGGER.debug("Contact Information saved in method saveOrUpdateContactInformation {}",
						contactBasicInfo.toString());
				return new ResponseEntity<>(contactBasicInfo, HttpStatus.CREATED);
			} else {
				Optional<ContactBasicInfo> contactBasicInfoOptional = contactBasicInfoRepository
						.findByContactId(contactBasicInfo.getContactId());
				if (contactBasicInfoOptional.isPresent()) {
					ContactBasicInfo contactBasicInfoDb = contactBasicInfoOptional.get();
					contactBasicInfoDb.setContactAddress(contactBasicInfo.getContactAddress());
					contactBasicInfoDb.setFullName(contactBasicInfo.getFullName());
					contactBasicInfoDb.setDateOfBirth(contactBasicInfo.getDateOfBirth());
					contactBasicInfoDb.setContactAddress(contactBasicInfo.getContactAddress());
					contactBasicInfoRepository.save(contactBasicInfoDb);
					LOGGER.debug("Contact Information updated in method saveOrUpdateContactInformation {}",
							contactBasicInfoDb.toString());
					return new ResponseEntity<>(contactBasicInfoDb, HttpStatus.OK);
				} else {
					LOGGER.debug("No contact information available for contact Id {}", contactBasicInfo.getContactId());
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Error while retriving contact information in method saveOrUpdateContactInformation {}",
					ex.getMessage(), ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This is service impl of getContactBasedOnZipCode
	 * 
	 * @param zipCode
	 * @return
	 */
	public ResponseEntity<List<ContactBasicInfo>> getContactBasedOnZipCode(int zipCode) {
		try {
			List<ContactBasicInfo> contactsList = new ArrayList<>();
			List<Long> addressIdList = contactAddressRepository.findByZipCode(zipCode);
			if (!addressIdList.isEmpty()) {
				List<ContactBasicInfo> contactBasicInfoList = contactBasicInfoRepository.findAll();
				if (!contactBasicInfoList.isEmpty()) {
					for (ContactBasicInfo contactBasicInfo : contactBasicInfoList) {
						if (addressIdList.contains(contactBasicInfo.getContactAddress().getAddressId())) {
							contactsList.add(contactBasicInfo);
						}
					}
					LOGGER.debug(
							"Contact Information retrieved for the zipcode in method getContactBasedOnZipCode {} and list is {}",
							zipCode, contactsList.toArray());
					return new ResponseEntity<>(contactsList, HttpStatus.OK);
				}
			}
			LOGGER.debug("No contact information available for zipCode {}", zipCode);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			LOGGER.error("Error while retriving contact information in method getContactBasedOnZipCode {}",
					ex.getMessage(), ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Service Implementation to DeleteContactInformation by contactId
	 * 
	 * @param contactId
	 * @return
	 */

	public ResponseEntity<String> deleteContactInformation(String contactId) {
		try {
			Optional<ContactBasicInfo> contactBasicInfoOptional = contactBasicInfoRepository.findByContactId(contactId);
			if (contactBasicInfoOptional.isPresent()) {
				contactBasicInfoRepository.delete(contactBasicInfoOptional.get());
			} else {
				LOGGER.debug("No contact information available for contactId {}", contactId);
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			LOGGER.debug("Contact information deleted for contactId {}", contactId);
			return ResponseEntity.ok("Deleted successfully");
		} catch (Exception ex) {
			LOGGER.error("Error while deleting contact information in method deleteContactInformation {}",
					ex.getMessage(), ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Service Implementation to updateFirstName by contactId
	 * 
	 * @param contactId,fullName
	 * @return
	 */
	public ResponseEntity<ContactBasicInfo> updateFirstName(String contactId, String fullName) {
		try {
			Optional<ContactBasicInfo> contactBasicInfoOptional = contactBasicInfoRepository.findByContactId(contactId);
			if (contactBasicInfoOptional.isPresent()) {
				ContactBasicInfo contactBasicInfoDb = contactBasicInfoOptional.get();
				contactBasicInfoDb.setFullName(fullName);
				contactBasicInfoRepository.save(contactBasicInfoDb);
				LOGGER.debug("Contact information saved for contactId {}", contactId);
				return new ResponseEntity<>(contactBasicInfoDb, HttpStatus.OK);
			} else {
				LOGGER.debug("No contact information available for contactId {}", contactId);
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		} catch (Exception ex) {
			LOGGER.error("Error while updating contact information in method updateFirstName {}", ex.getMessage(), ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

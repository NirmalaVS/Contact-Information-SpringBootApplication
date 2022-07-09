package com.vaudience.contactinformation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaudience.contactinformation.entity.ContactBasicInfo;
import com.vaudience.contactinformation.service.ContactInformationService;

@RestController
public class ContactInformationRestController {

	@Autowired
	ContactInformationService contactInformationService;

	/**
	 * GetMapping to get contact Informations stored in Database
	 * 
	 * @return List of contactInformations
	 */

	@GetMapping(value = "/contactInfo")
	public @ResponseBody List<ContactBasicInfo> getContactInformations() {
		return contactInformationService.getContactInformation();
	}

	/**
	 * PostMapping to post contactInformation
	 * 
	 * @param contactBasicInfo
	 * @return JsonValue
	 */

	@PostMapping(value = "/contactInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ContactBasicInfo postContactInformation(@RequestBody ContactBasicInfo contactBasicInfo) {
		return contactInformationService.saveOrUpdateContactInformation(contactBasicInfo);
	}

	/**
	 * GetMapping to get contact Information based on zipcode
	 * 
	 * @param zipCode
	 * @return
	 */

	@GetMapping(value = "/contactInfo/{zipCode}")
	public @ResponseBody List<ContactBasicInfo> getContactBasedOnZipCode(@PathVariable(value = "zipCode") int zipCode) {
		return contactInformationService.getContactBasedOnZipCode(zipCode);
	}

	/**
	 * DeleteMapping to delete based on contactId
	 * 
	 * @param contactId
	 * @return
	 */

	@DeleteMapping(value = "/contactInfo/{contactId}")
	public ResponseEntity<String> deleteContactInformation(@PathVariable(value = "contactId") String contactId) {
		return contactInformationService.deleteContactInformation(contactId);
	}
	

}

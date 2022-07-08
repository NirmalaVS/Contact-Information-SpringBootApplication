package com.vaudience.contactinformation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vaudience.contactinformation.entity.ContactBasicInfo;
import com.vaudience.contactinformation.repository.ContactAddressRepository;
import com.vaudience.contactinformation.repository.ContactBasicInfoRepository;
import com.vaudience.contactinformation.service.ContactInformationService;

@Controller
public class ContactInformationRestController {

	@Autowired
	ContactInformationService contactInformationService;

	/**
	 * GetMapping to get contact Informations stored in Database
	 * 
	 * @return List of contactInformations
	 */

	@GetMapping(value = "/contactInfos")
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

}

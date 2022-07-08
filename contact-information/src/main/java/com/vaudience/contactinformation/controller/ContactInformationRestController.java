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

@Controller
public class ContactInformationRestController {

	@Autowired
	ContactBasicInfoRepository contactBasicInfoRepository;

	@Autowired
	ContactAddressRepository contactAddressRepository;

	@GetMapping(value = "/contactInfos")
	public @ResponseBody List<ContactBasicInfo> getContactInformations() {
		return contactBasicInfoRepository.findAll();
	}

	@PostMapping(value = "/contactInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ContactBasicInfo postContactInformation(@RequestBody ContactBasicInfo contactBasicInfo) {
		return contactBasicInfoRepository.save(contactBasicInfo);
	}

}

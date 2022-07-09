package com.vaudience.contactinformation.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.vaudience.contactinformation.controller.ContactInformationRestController;
import com.vaudience.contactinformation.entity.ContactAddress;
import com.vaudience.contactinformation.entity.ContactBasicInfo;
import com.vaudience.contactinformation.service.ContactInformationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactInformationRestController.class)
public class ContactInformationRestControllerTest {

	@MockBean
	ContactInformationService contactInformationService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testGetContactInformations() throws Exception {
		ContactAddress contactAddress = new ContactAddress(8, "Stuttgart", 70569);
		String dob = "1997-14-11";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
		ContactBasicInfo contactBasicInfo = new ContactBasicInfo("CO11", "Willams", dateOfBirth, contactAddress);

		List<ContactBasicInfo> contactBasicInfoList = Arrays.asList(contactBasicInfo);
		Mockito.when(contactInformationService.getContactInformation()).thenReturn(contactBasicInfoList);

		mockMvc.perform(get("/contactInfos")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].fullName", Matchers.is("Willams")));
	}

}

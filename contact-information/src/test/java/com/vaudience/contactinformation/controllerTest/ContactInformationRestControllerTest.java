package com.vaudience.contactinformation.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

	String mockJson = "{\"fullName\":\"Aditya\",\"dateOfBirth\":\"1996-11-14\",\"contactAddress\":{\"cityName\":\"Stuttgart\",\"zipCode\":70569}}";

	@Test
	public void getContactInformationsTest() throws Exception {
		ContactAddress contactAddress = new ContactAddress(8, "Stuttgart", 70569);
		ContactBasicInfo contactBasicInfo = new ContactBasicInfo("CO11", "Willams", new Date(), contactAddress);
		System.out.println(contactBasicInfo.toString());
		List<ContactBasicInfo> contactBasicInfoList = Arrays.asList(contactBasicInfo);
		ResponseEntity<List<ContactBasicInfo>> response = new ResponseEntity<>(contactBasicInfoList, HttpStatus.OK);
		Mockito.when(contactInformationService.getContactInformation()).thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contactInfo").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].fullName", Matchers.is("Willams")));
	}

	@Test
	public void createContactInformationTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/contactInfo").accept(MediaType.APPLICATION_JSON)
				.content(mockJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

}

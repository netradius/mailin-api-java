package com.netradius.sendinblue;

import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import static com.netradius.sendinblue.StringHelper.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author Erik R. Jensen
 */
@Slf4j
public class TemplateEmailIT {

	private static SendinBlueClient client;
	private static String toAddress;

	@BeforeClass
	public static void init() throws IOException {
		client = IntegrationTestHelper.getClient();
		Properties config = IntegrationTestHelper.getConfig();
		toAddress = config.getProperty("TemplateEmailIT.toAddress");
		if (isEmpty(toAddress)) {
			throw new IllegalArgumentException("TemplateEmailIT.toAddress is mandatory");
		}
	}

	@Test
	public void testTemplateEmail() {
		TemplateEmail email = new TemplateEmail()
				.setId(3)
				.setTo(toAddress)
				.addAttribute("TEST_ATTRIBUTE", UUID.randomUUID().toString())
				.validate();

		SendinBlueResponse response = client.send(email);
		assertThat(response.getCode(), equalTo("success"));
		log.info("Received response: " + response);
	}

	//get list of templates
	@Test
	public void testGetListOfActiveTemplates() {
		Map<String, Object> data = new HashMap<>();
		data.put("type", "template");
		data.put("status", "temp_active");
		data.put("page", 1);
		data.put("page_limit", 500);
		String response = client.getCampaignsV2(data);
		assertNotNull(response);
		log.info("Received response: " + response);
	}

}

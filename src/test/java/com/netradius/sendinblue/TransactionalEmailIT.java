package com.netradius.sendinblue;

import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static com.netradius.sendinblue.StringHelper.*;

/**
 * This test demonstrates how to send a transactional email.
 * https://apidocs.sendinblue.com/tutorial-sending-transactional-email/
 *
 * @author Erik R. Jensen
 */
@Slf4j
public class TransactionalEmailIT {

	private static SendinBlueClient client;
	private static String toAddress;
	private static String fromAddress;


	@BeforeClass
	public static void init() throws IOException {
		client = IntegrationTestHelper.getClient();
		Properties config = IntegrationTestHelper.getConfig();
		toAddress = config.getProperty("TransactionalEmailIT.to.address");
		if (isEmpty(toAddress)) {
			throw new IllegalArgumentException("TransactionalEmailIT.to.address is mandatory");
		}
		fromAddress = config.getProperty("TransactionalEmailIT.from.address");
		if (isEmpty(fromAddress)) {
			throw new IllegalArgumentException("TransactionalEmailIT.to.address is mandatory");
		}
	}

	@Test
	public void testTransactionalEmail() {
		String html =
				"<!DOCTYPE HTML>" +
				"<html><body>" +
				"<h1>Transactional Email Integration Test</h1>" +
				"<p>This email was generated from an integration test and can be discarded.</p>" +
				"</body></html>";

		TransactionalEmail email = new TransactionalEmail()
				.addTo(toAddress, "Integration Test")
				.setFrom(fromAddress, "Integration Test")
				.setSubject("Transactional Email Integration Test")
				.setHtml(html)
				.validate();

		SendinBlueResponse response = client.sendEmail(email);

		log.info("Received response: " + response);
	}


}

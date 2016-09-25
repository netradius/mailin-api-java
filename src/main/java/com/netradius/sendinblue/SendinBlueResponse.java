package com.netradius.sendinblue;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Erik R. Jensen
 */
@Data
public class SendinBlueResponse {
	protected String code;
	protected String message;
	protected Map<String, String> data = new HashMap<>();
}

package com.netradius.sendinblue;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.netradius.sendinblue.StringHelper.*;

/**
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
public class TemplateEmail implements Validated<TemplateEmail> {

	protected int id;
	protected String to;
	protected String cc;
	protected String bcc;
	protected String replyTo;
	@SerializedName("attr")
	protected Map<String, String> attributes = new HashMap<>();
	@SerializedName("attachment_url")
	protected String attachmentUrl;
	@SerializedName("attachment")
	protected Map<String, String> attachments = new HashMap<>();
	protected Map<String, String> headers = new HashMap<>();

	public TemplateEmail addTo(String address) {
		if (to == null) {
			to = address;
			return this;
		}
		to += "|" + address;
		return this;
	}

	public TemplateEmail addCc(String address) {
		if (cc == null) {
			cc = address;
			return this;
		}
		cc += "|" + address;
		return this;
	}

	public TemplateEmail addBcc(String address) {
		if (bcc == null) {
			bcc = address;
			return this;
		}
		bcc += "|" + address;
		return this;
	}

	public TemplateEmail addAttribute(String key, String value) {
		attributes.put(key, value);
		return this;
	}

	public TemplateEmail addAttachment(String filename, byte[] data) {
		attachments.put(filename, Base64.getEncoder().encodeToString(data));
		return this;
	}

	public TemplateEmail addHeader(String name, String value) {
		headers.put(name, value);
		return this;
	}

	@Override
	public TemplateEmail validate() {
		if (id == 0) {
			throw new IllegalArgumentException("id is mandatory");
		}
		if (isEmpty(to)) {
			throw new IllegalArgumentException("to is mandatory");
		}
		return this;
	}
}

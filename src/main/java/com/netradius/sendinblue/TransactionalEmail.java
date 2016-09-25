package com.netradius.sendinblue;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
public class TransactionalEmail implements Validated<TransactionalEmail> {

	protected Map<String, String> to = new HashMap<>();
	protected Map<String, String> cc = new HashMap<>();
	protected Map<String, String> bcc = new HashMap<>();
	protected String[] from;
	@SerializedName("replyto")
	protected String[] replyTo;
	protected String subject;
	protected String html;
	protected String text;
	@SerializedName("attachment")
	protected Map<String, String> attachments = new HashMap<>();
	@SerializedName("header")
	protected Map<String, String> headers = new HashMap<>();
	@SerializedName("inline_image")
	protected Map<String, String> inlineImages = new HashMap<>();

	public TransactionalEmail addTo(String address, String name) {
		to.put(address, name);
		return this;
	}

	public TransactionalEmail addCc(String address, String name) {
		to.put(address, name);
		return this;
	}

	public TransactionalEmail addBcc(String address, String name) {
		to.put(address, name);
		return this;
	}

	public TransactionalEmail setFrom(String address, String name) {
		from = new String[] {address, name};
		return this;
	}

	public TransactionalEmail setReplyTo(String address, String name) {
		replyTo = new String[] {address, name};
		return this;
	}

	public TransactionalEmail addAttachmentUrl(String url) {
		attachments.put(url, null);
		return this;
	}

	public TransactionalEmail addAttachmentEncoded(String filename, byte[] data) {
		attachments.put(filename, Base64.getEncoder().encodeToString(data));
		return this;
	}

	public TransactionalEmail addHeader(String name, String value) {
		headers.put(name, value);
		return this;
	}

	public TransactionalEmail addInlineImage(String filename, byte[] data) {
		inlineImages.put(filename, Base64.getEncoder().encodeToString(data));
		return this;
	}

	@Override
	public TransactionalEmail validate() {
		if (to.isEmpty()) {
			throw new IllegalArgumentException("to is mandatory");
		}
		if (from == null) {
			throw new IllegalArgumentException("from is mandatory");
		}
		if (StringHelper.isEmpty(subject)) {
			throw new IllegalArgumentException("subject is mandatory");
		}
		if (StringHelper.isEmpty(html)) {
			throw new IllegalArgumentException("html is mandatory");
		}
		return this;
	}
}

package com.netradius.sendinblue;

// This code copied from https://github.com/netradius/java-commons so we didn't have to add an additional dependency to the project.

/**
 * Provides helpful utility methods for Strings.
 *
 * @author Erik R. Jensen
 */
public class StringHelper {

	/**
	 * Checks if a string is empty. An string is considered empty by this method when it is null,
	 * contains no characters or only contains whitespace characters.
	 *
	 * @param str the string to check
	 * @return true of the string is empty, false if otherwise
	 */
	public static boolean isEmpty(String str) {
		return str == null || trimWhitespace(str).isEmpty();
	}

	/**
	 * Trims off any leading or trailing whitespace from the given string. This method will handle null or empty
	 * strings.
	 *
	 * @param str the string to trim
	 * @return the trimmed string
	 */
	public static String trimWhitespace(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return trimLeadingWhitespace(trimTrailingWhitespace(str));
	}

	/**
	 * Trims off any leading whitespace from the given string. This method will handle null and empty strings.
	 *
	 * @param str the string to trim
	 * @return the trimmed string
	 */
	public static String trimLeadingWhitespace(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return str.substring(i, str.length());
			}
		}
		return "";
	}

	/**
	 * Trims off any training whitespace from the given string. This method will handle null and empty strings.
	 *
	 * @param str the string to trim
	 * @return the trimmed string
	 */
	public static String trimTrailingWhitespace(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		for (int i = str.length() - 1; i >= 0; i--) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return str.substring(0, i + 1);
			}
		}
		return "";
	}

}

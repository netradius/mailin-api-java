package com.netradius.sendinblue;

/**
 * Contractor for all types which can be validated.
 *
 * @author Erik R. Jensen
 */
public interface Validated<T> {

	/**
	 * All classes inheriting this interface are expected to validate their data. If validation fails
	 * like a missing mandatory field, the implementation of this method should through an IllegalArgumentException
	 * containing the details of the validation failure.
	 *
	 * @return this instance for chaining method calls
	 */
	T validate();

}

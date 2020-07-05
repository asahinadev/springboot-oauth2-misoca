package com.example.spring.oauth2.misoca.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaxOption {

	/** 送信時に決定 */
	USE_SENDER,
	/** 内税 */
	INCLUDE,
	/** 外税 */
	EXCLUDE,
	/** 非課税 */
	EXEMPT;

	@Override
	@JsonValue
	public String toString() {
		return this.name();
	}

}

package com.example.spring.oauth2.misoca.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaxType {
	NONE,
	STANDARD_TAX_5,
	STANDARD_TAX_8,
	STANDARD_TAX_10,
	;

	@Override
	@JsonValue
	public String toString() {
		if (this == NONE) {
			return "";
		}
		return this.name();
	}

}

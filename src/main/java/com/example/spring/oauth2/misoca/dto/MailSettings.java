package com.example.spring.oauth2.misoca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailSettings {

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Subject {

		private String estimate;
		private String delivery_slip;
		private String invoice;

	}

	private Subject subject;
}

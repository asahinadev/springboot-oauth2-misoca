package com.example.spring.oauth2.misoca.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactGroups {
	private Long id;
	private Long user_id;
	private String recipient_name;
	private String recipient_ruby;
	private String recipient_title;
	@JsonProperty("mail_settings")
	MailSettings mail_settings;
	private boolean trashed;
	private String trashed_at;
	private TaxOption tax_option = TaxOption.USE_SENDER;
	private String corporate_number;
	private float lock_version;
	private String created_at;
	private String updated_at;
	List<BankAccounts> bank_accounts = new ArrayList<>();
}

package com.example.spring.oauth2.misoca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contacts {
	private Long id;
	private Long contact_group_id;
	private Long user_id;
	private String recipient_code;
	private String recipient_name;
	private String recipient_ruby;
	private String recipient_title;
	private String recipient_notes;
	private String recipient_mail_address;
	private String recipient_mail_address_cc;
	private String recipient_tel_no;
	private String recipient_fax_no;
	private String memo;
	private Boolean favorited;
	private String favorited_at;
	private Boolean trashed;
	private String trashed_at;
	private String recipient_zip_code;
	private String recipient_address1;
	private String recipient_address2;
	private String recipient_name1;
	private String recipient_name2;
	private String recipient_name3;
	private String recipient_name4;
	private String recipient_title2;
	private TaxOption tax_option = TaxOption.USE_SENDER;
	private String corporate_number;
	private Long lock_version;
	private String created_at;
	private String updated_at;
}

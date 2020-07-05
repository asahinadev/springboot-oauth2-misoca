package com.example.spring.oauth2.misoca.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sender {
	private String sender_name1;
	private String sender_name2;
	private String sender_name3;
	private String sender_zip_code;
	private String sender_address1;
	private String sender_address2;
	private String sender_address3;
	private String sender_tel;
	private String sender_fax;
	private String sender_email;
	private String recipient_zip_code;
	private String recipient_address1;
	private String recipient_address2;
	private String recipient_name1;
	private String recipient_name2;
	private String recipient_name3;
	private String recipient_name4;
	private String recipient_title;
	private String notes;
	private String private_sender_memo;
	private String tax_option;
	private String tax_rounding_policy;
	private Long total_amount;
	private Long tax;
	private Long total_amount_including_tax;
	ArrayList<BankAccounts> bank_accounts = new ArrayList<>();

}

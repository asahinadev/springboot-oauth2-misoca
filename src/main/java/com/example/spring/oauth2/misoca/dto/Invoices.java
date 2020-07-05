package com.example.spring.oauth2.misoca.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Invoices {
	private float id;
	private String issue_date;
	private String payment_due_on;
	private String archived_at;
	private String trashed_at;
	private String invoice_number;
	private float invoice_status;
	private float payment_status;
	private boolean issued;
	private float user_id;
	private String recipient_name;
	private String recipient_title;
	private String recipient_notes;
	private String subject;
	Sender body;
	List<Items> items = new ArrayList<>();
	private Long contact_id;
	private Long lock_version;
	private String created_at;
	private String updated_at;
}

package com.example.spring.oauth2.misoca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
	private String name;
	private float unit_price;
	private float quantity;
	private String unit_name;
	private TaxType tax_type = TaxType.NONE;
	private Boolean excluding_withholding_tax;
}

package com.example.spring.oauth2.misoca.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.oauth2.misoca.api.MisocaApi;
import com.example.spring.oauth2.misoca.dto.Invoices;
import com.example.spring.oauth2.misoca.dto.Invoices;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {

	final MisocaApi api;

	@Autowired
	public InvoicesController(MisocaApi api) {
		this.api = api;
	}

	/**
	 * 請求書一覧を取得します。
	 * 
	 * @param client  自動設定
	 * @param trashed {@link RequestParam}
	 * @return List Of {@link Invoices}
	 */
	@GetMapping
	public Flux<Invoices> index(OAuth2AuthorizedClient client,
			@RequestParam(name = "trashed", required = false) Boolean trashed) {

		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>(2);
		if (trashed != null) {
			params.add("trashed", String.valueOf(trashed));
		}

		return api.getResponse(client, "/invoices", Map.of(), params)
				.bodyToFlux(Invoices.class);
	}

	/**
	 * 請求書を取得します。
	 * 
	 * @param client 自動設定
	 * @param id     {@link PathVariable} 対象-ID
	 * @return {@link Invoices}
	 */
	@GetMapping("{id}")
	public Mono<Invoices> get(OAuth2AuthorizedClient client, @PathVariable("id") Long id) {

		return api.getResponse(client, "/invoice/{id}", Map.of("id", id), MisocaApi.emptyParams())
				.bodyToMono(Invoices.class);
	}

	/**
	 * 請求書のPDFを取得します。
	 * 
	 * @param client 自動設定
	 * @param id     {@link PathVariable} 対象-ID
	 */
	@GetMapping("{id}")
	public Mono<Void> getPdf(OAuth2AuthorizedClient client, @PathVariable("id") Long id) {

		api.getResponse(client, "/invoice/{id}/pdf", Map.of("id", id), MisocaApi.emptyParams())
				.bodyToMono(Invoices.class);
	}

	/**
	 * 取引先を作成します。
	 * 
	 * @param client   自動設定
	 * @param Invoices {@link RequestBody} 登録対象
	 * @return {@link Invoices}
	 */
	@PostMapping
	public Mono<Invoices> post(OAuth2AuthorizedClient client, @RequestBody Invoices Invoices) {

		return api.postResponse(client, "/invoice", Map.of(), Invoices).bodyToMono(Invoices.class);
	}

}

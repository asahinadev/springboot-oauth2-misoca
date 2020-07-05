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
import com.example.spring.oauth2.misoca.dto.ContactGroups;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contact_groups")
public class ContactGroupsController {

	final MisocaApi api;

	@Autowired
	public ContactGroupsController(MisocaApi api) {
		this.api = api;
	}

	/**
	 * 取引先一覧を取得します。
	 * 
	 * @param client  自動設定
	 * @param trashed {@link RequestParam}
	 * @return List Of {@link ContactGroups}
	 */
	@GetMapping
	public Flux<ContactGroups> index(OAuth2AuthorizedClient client,
			@RequestParam(name = "trashed", required = false) Boolean trashed) {

		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>(2);
		if (trashed != null) {
			params.add("trashed", String.valueOf(trashed));
		}

		return api.getResponse(client, "/contact_groups", Map.of(), params)
				.bodyToFlux(ContactGroups.class);
	}

	/**
	 * 取引先を取得します。
	 * 
	 * @param client 自動設定
	 * @param id     {@link PathVariable} 対象-ID
	 * @return {@link ContactGroups}
	 */
	@GetMapping("{id}")
	public Mono<ContactGroups> get(OAuth2AuthorizedClient client, @PathVariable("id") Long id) {

		return api.getResponse(client, "/contact_group/{id}", Map.of("id", id), MisocaApi.emptyParams())
				.bodyToMono(ContactGroups.class);
	}

	/**
	 * 取引先を作成します。
	 * 
	 * @param client        自動設定
	 * @param contactGroups {@link RequestBody} 登録対象
	 * @return {@link ContactGroups}
	 */
	@PostMapping
	public Mono<ContactGroups> post(OAuth2AuthorizedClient client, @RequestBody ContactGroups contactGroups) {

		return api.postResponse(client, "/contact_group", Map.of(), contactGroups).bodyToMono(ContactGroups.class);
	}

}

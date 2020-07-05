package com.example.spring.oauth2.misoca.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.oauth2.misoca.api.MisocaApi;
import com.example.spring.oauth2.misoca.dto.Contacts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

	final MisocaApi api;

	@Autowired
	public ContactsController(MisocaApi api) {
		this.api = api;
	}

	/**
	 * 送り先一覧を取得します。
	 * 
	 * @param client           自動設定
	 * @param trashed          {@link RequestParam}
	 * @param contact_group_id {@link RequestParam}
	 * @return List Of {@link Contacts}
	 */
	@GetMapping
	public Flux<Contacts> index(OAuth2AuthorizedClient client,
			@RequestParam(name = "trashed", required = false) Boolean trashed,
			@RequestParam(name = "contact_group_id", required = false) Long contact_group_id) {

		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>(2);
		if (trashed != null) {
			params.add("trashed", String.valueOf(trashed));
		}

		if (contact_group_id != null) {
			params.add("contact_group_id", String.valueOf(contact_group_id));
		}

		return api.getResponse(client, "/contacts", Map.of(), params).bodyToFlux(Contacts.class);
	}

	/**
	 * 送り先を取得します。
	 * 
	 * @param client 自動設定
	 * @param id     {@link PathVariable} 対象-ID
	 * @return {@link Contacts}
	 */
	@GetMapping("{id}")
	public Mono<Contacts> get(OAuth2AuthorizedClient client, @PathVariable("id") Long id) {

		return api.getResponse(client, "/contact/{id}", Map.of("id", id), MisocaApi.emptyParams())
				.bodyToMono(Contacts.class);
	}

	/**
	 * 送り先を登録します。
	 * 
	 * @param client  自動設定
	 * @param contact {@link RequestBody} 登録対象
	 * @return {@link Contacts}
	 */
	@PostMapping
	public Mono<Contacts> post(OAuth2AuthorizedClient client, @RequestBody Contacts contact) {

		return api.postResponse(client, "/contact", Map.of(), contact).bodyToMono(Contacts.class);
	}

	/**
	 * 送り先を非表示にします。
	 * 
	 * @param client 自動設定
	 * @param id     {@link PathVariable} 対象-ID
	 * @return {@link Contacts}
	 */
	@PutMapping("{id}/trashed")
	public Mono<Contacts> put(OAuth2AuthorizedClient client, @PathVariable("id") Long id) {

		return api.putResponse(client, "/contact/{id}/trashed", Map.of("id", id)).bodyToMono(Contacts.class);
	}

	/**
	 * 送り先を表示にします。
	 * 
	 * @param client 自動設定
	 * @param id     {@link PathVariable} 対象-ID
	 * @return {@link Contacts}
	 */
	@DeleteMapping("{id}/trashed")
	public Mono<Contacts> delete(OAuth2AuthorizedClient client, @PathVariable("id") Long id) {

		return api.deleteResponse(client, "/contact/{id}/trashed", Map.of("id", id)).bodyToMono(Contacts.class);
	}
}

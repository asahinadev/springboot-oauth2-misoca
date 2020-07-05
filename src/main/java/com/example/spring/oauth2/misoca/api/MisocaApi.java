package com.example.spring.oauth2.misoca.api;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

@Service
public class MisocaApi {

	public static MultiValueMap<String, String> emptyParams() {
		return new LinkedMultiValueMap<>();
	}

	@Autowired
	WebClient misocaClient;

	public ResponseSpec getResponse(OAuth2AuthorizedClient client,
			String path, Map<String, Object> uriVariables, MultiValueMap<String, String> params) {

		return misocaClient.get().uri(b -> b.path(path).replaceQueryParams(params).build(uriVariables))
				.attributes(attributes(client)).retrieve();
	}

	public ResponseSpec postResponse(OAuth2AuthorizedClient client,
			String path, Map<String, Object> uriVariables, Object body) {

		return misocaClient.post().uri(b -> b.path(path).build(uriVariables))
				.contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.attributes(attributes(client)).retrieve();
	}

	public ResponseSpec patchResponse(OAuth2AuthorizedClient client,
			String path, Map<String, Object> uriVariables, Object body) {

		return misocaClient.patch().uri(b -> b.path(path).build(uriVariables))
				.contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.attributes(attributes(client)).retrieve();
	}

	public ResponseSpec putResponse(OAuth2AuthorizedClient client, String path, Map<String, Object> uriVariables) {
		return putResponse(client, path, uriVariables, Map.of());
	}

	public ResponseSpec putResponse(OAuth2AuthorizedClient client,
			String path, Map<String, Object> uriVariables, Object body) {

		return misocaClient.put().uri(b -> b.path(path).build(uriVariables))
				.contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.attributes(attributes(client)).retrieve();
	}

	public ResponseSpec deleteResponse(
			OAuth2AuthorizedClient client, String path, Map<String, Object> uriVariables) {

		return misocaClient.delete().uri(b -> b.path(path).build(uriVariables))
				.attributes(attributes(client)).retrieve();
	}

	protected Consumer<Map<String, Object>> attributes(OAuth2AuthorizedClient client) {
		return ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client);
	}
}

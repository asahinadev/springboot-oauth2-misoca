package com.example.spring.oauth2.misoca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {

	protected static final String MISOKA_API = "https://app.misoca.jp/api/v3/";

	@Bean
	public WebClient misocaClient(
			ReactiveClientRegistrationRepository reactive,
			ServerOAuth2AuthorizedClientRepository oauth2) {

		ServerOAuth2AuthorizedClientExchangeFilterFunction filter;
		filter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(reactive, oauth2);

		return WebClient.builder().baseUrl(MISOKA_API).filter(filter).build();
	}

}

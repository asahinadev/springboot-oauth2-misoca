package com.example.spring.oauth2.misoca.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/* beans */

	public String[] permitAllList() {
		return Arrays.asList("/error**", "/login**", "/webjars/**", "/oauth2/**", "/**/*.css", "/**/*.js", "/**/*.map",
				"/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.ttf", "/favicon.ico"

		/* END */).toArray(new String[11]);
	}

	/* configure */

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 認証エリア設定
		configure(http.authorizeRequests());

		// BASIC 認証
		configure(http.httpBasic());

		// FORM 認証
		configure(http.formLogin());

		// LOGOUT 設定
		configure(http.logout());

		// CSRF
		configure(http.csrf());

		// CORS
		configure(http.cors());

		// OAuth v2 認証
		configure(http.oauth2Login());
	}

	/**
	 * 認証エリア設定,
	 * 
	 * @param auth 設定
	 */
	protected void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry auth) {
		auth
				// 認証不要
				.antMatchers(permitAllList()).permitAll()
				// 認証必要
				.anyRequest().authenticated();

	}

	/**
	 * BASIC 認証,
	 * 
	 * @param basic 設定
	 */
	protected void configure(HttpBasicConfigurer<HttpSecurity> basic) {
		basic.disable();
	}

	/**
	 * FORM 認証,
	 * 
	 * @param form 設定
	 */
	protected void configure(FormLoginConfigurer<HttpSecurity> form) {
		form.disable();
	}

	/**
	 * LOGOUT 設定.
	 * 
	 * @param logout 設定
	 */
	protected void configure(LogoutConfigurer<HttpSecurity> logout) {
		// logout.disable();
	}

	/**
	 * CSRF 対策
	 * 
	 * @param csrf 設定
	 */
	protected void configure(CsrfConfigurer<HttpSecurity> csrf) {
		csrf.disable();
	}

	/**
	 * CORS 対策
	 * 
	 * @param cors 設定
	 */
	protected void configure(CorsConfigurer<HttpSecurity> cors) {
		cors.disable();
	}

	/**
	 * OAUTH2 認証設定
	 * 
	 * @param oauth 設定
	 */
	protected void configure(OAuth2LoginConfigurer<HttpSecurity> oauth) {

		oauth

				// 認証エンドポイント
				.authorizationEndpoint().and()

				// リダイレクトエンドポイント
				.redirectionEndpoint().and()

				// アクセストークンエンドポイント
				.tokenEndpoint().and()

				// ユーザー情報エンドポイント
				.userInfoEndpoint().and();
	}

}

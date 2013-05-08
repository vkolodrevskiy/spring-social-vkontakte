package org.springframework.social.vkontakte.security;

import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class VKontakteAuthenticationService extends OAuth2AuthenticationService<VKontakte> {

	public VKontakteAuthenticationService(String apiKey, String appSecret) {
		super(new VKontakteConnectionFactory(apiKey, appSecret));
	}

}

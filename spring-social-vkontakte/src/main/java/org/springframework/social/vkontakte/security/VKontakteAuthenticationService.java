/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.vkontakte.security;

import org.springframework.social.security.provider.OAuth2AuthenticationService;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

/**
 * VKontakte Authentication Service.
 * @author vkolodrevskiy
 */
public class VKontakteAuthenticationService extends OAuth2AuthenticationService<VKontakte> {

	public VKontakteAuthenticationService(String apiKey, String appSecret) {
		super(new VKontakteConnectionFactory(apiKey, appSecret));
	}
}

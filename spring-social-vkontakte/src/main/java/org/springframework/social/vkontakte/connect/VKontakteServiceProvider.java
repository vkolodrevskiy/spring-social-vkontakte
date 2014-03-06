/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.vkontakte.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.impl.VKontakteTemplate;

/**
 * VKontakte ServiceProvider implementation.
 * @author vkolodrevskiy
 */
public class VKontakteServiceProvider extends AbstractOAuth2ServiceProvider<VKontakte> {

    protected final String clientSecret;

	public VKontakteServiceProvider(String clientId, String clientSecret) {
        super(new VKontakteOAuth2Template(clientId, clientSecret));
        this.clientSecret = clientSecret;
	}

    public VKontakte getApi(String accessToken) {
		return new VKontakteTemplate(accessToken, clientSecret);
	}
}

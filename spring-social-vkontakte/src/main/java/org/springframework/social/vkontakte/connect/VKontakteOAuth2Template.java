/*
 * Copyright 2011-2014 the original author or authors.
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

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;

import java.util.Map;

/**
 * VKontakte-specific extension of OAuth2Template.
 * @author vkolodrevskiy
 */
public class VKontakteOAuth2Template extends OAuth2Template {

	public VKontakteOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret, "http://oauth.vk.com/authorize", "https://oauth.vk.com/access_token");
        setUseParametersForClientAuthentication(true);
	}

    // When scope has "offline" option VKontakte returns expires_in=0, setting it to null in this case
    @Override
    protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
        return super.createAccessGrant(accessToken, scope, refreshToken, expiresIn == 0 ? null : expiresIn, response);
    }
}

/*
 * Copyright 2011-2016 the original author or authors.
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * VKontakte {@link org.springframework.social.ServiceProvider} implementation.
 *
 * @author vkolodrevskiy
 */
public class VKontakteServiceProvider extends AbstractOAuth2ServiceProvider<VKontakte> {

    private final String clientSecret;
    private final Integer clientId;

    public VKontakteServiceProvider(String clientId, String clientSecret) {
        super(new VKontakteOAuth2Template(clientId, clientSecret));
        this.clientSecret = clientSecret;
        this.clientId = Integer.parseInt(clientId);
    }

    public VKontakte getApi(String accessToken) {
        Integer providerUserId = null;
        Matcher idMathcer = Pattern.compile("\\[id=([^\\]]*)\\]").matcher(accessToken);
        if (idMathcer.find()) {
            providerUserId = Integer.valueOf(idMathcer.group(1));
            accessToken = accessToken.replaceAll("\\[id=" + providerUserId + "\\]", "");
        }

        String email = null;
        Matcher emailMathcer = Pattern.compile("\\[email=([^\\]]*)\\]").matcher(accessToken);
        if (emailMathcer.find()) {
            email = emailMathcer.group(1);
            accessToken = accessToken.replaceAll("\\[email=" + email + "\\]", "");
        }

        return new VKontakteTemplate(providerUserId, email, accessToken, clientId, clientSecret);
    }
}

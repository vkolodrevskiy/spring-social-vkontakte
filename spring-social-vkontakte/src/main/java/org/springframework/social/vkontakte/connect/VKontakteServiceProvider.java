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

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.impl.VKontakteTemplate;

import java.util.List;

/**
 * VKontakte ServiceProvider implementation.
 * @author vkolodrevskiy
 */
public class VKontakteServiceProvider extends AbstractOAuth2ServiceProvider<VKontakte> {
    private final static Log log = LogFactory.getLog(VKontakteServiceProvider.class);

    private final String clientSecret;
    private final Integer clientId;

    public VKontakteServiceProvider(String clientId, String clientSecret) {
        super(new VKontakteOAuth2Template(clientId, clientSecret));
        this.clientSecret = clientSecret;
        this.clientId = Integer.parseInt(clientId);
    }

    public VKontakte getApi(String accessToken) {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

        Integer providerUserId = null;
        try {
            UserXtrCounters user = vk.users().get(new UserActor(-1, accessToken)).execute().get(0);
            providerUserId = user.getId();
        } catch (ApiException | ClientException e) {
            log.error("Error while getting current user info.", e);
        }

        return new VKontakteTemplate(providerUserId, accessToken, clientId, clientSecret);
    }
}

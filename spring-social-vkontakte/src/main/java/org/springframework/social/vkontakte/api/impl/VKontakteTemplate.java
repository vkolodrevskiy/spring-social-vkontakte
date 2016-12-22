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
package org.springframework.social.vkontakte.api.impl;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.vkontakte.api.VKontakte;

/**
 * {@link VKontakte} implementation.
 *
 * @author vkolodrevskiy
 */
public class VKontakteTemplate extends AbstractOAuth2ApiBinding implements VKontakte {
    private UserActor userActor;
    private ServiceActor serviceActor;

    private final Integer providerUserId;
    private final String email;
    private final String accessToken;
    private final Integer clientId;
    private final String clientSecret;

    public VKontakteTemplate() {
        this.providerUserId = null;
        this.accessToken = null;
        this.clientId = null;
        this.clientSecret = null;
        this.email = null;
        initialize();
    }

    public VKontakteTemplate(Integer providerUserId, String email, String accessToken, Integer clientId, String clientSecret) {
        super(accessToken);
        this.providerUserId = providerUserId;
        this.email = email;
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        initialize();
    }

    private void initialize() {
        userActor = new UserActor(providerUserId, accessToken);
        serviceActor = new ServiceActor(clientId, clientSecret, accessToken);
    }

    @Override
    public ServiceActor getServiceActor() {
        return serviceActor;
    }

    @Override
    public UserActor getUserActor() {
        return userActor;
    }

    @Override
    public String getEmail() {
        return email;
    }
}

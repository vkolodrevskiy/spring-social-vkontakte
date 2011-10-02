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
package org.springframework.social.vkontakte.api.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.vkontakte.api.*;
import org.springframework.social.vkontakte.api.impl.json.VKontakteModule;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>This is the central class for interacting with VKontakte.</p>
 * <p>
 * There are some operations, such as searching, that do not require OAuth
 * authentication. In those cases, you may use a {@link VKontakteTemplate} that is
 * created through the default constructor and without any OAuth details.
 * Attempts to perform secured operations through such an instance, however,
 * will result in {@link org.springframework.social.NotAuthorizedException} being thrown.
 * </p>
 * @author vkolodrevskiy
 */
public class VKontakteTemplate extends AbstractOAuth2ApiBinding implements VKontakte {

    private UsersOperations usersOperations;
    private WallOperations wallOperations;
    private FriendsOperations friendsOperations;

    private final String accessToken;
    private final String uid;

    public VKontakteTemplate() {
        initialize();
        this.accessToken = null;
        this.uid = null;
    }

    public VKontakteTemplate(String accessToken, String uid) {
        super(accessToken);
        this.accessToken = accessToken;
        this.uid = uid;
        initialize();
    }

    private void initialize() {
        registerJsonModule();
        getRestTemplate().setErrorHandler(new VKontakteErrorHandler());
        initSubApis();
    }

    private void registerJsonModule() {
        List<HttpMessageConverter<?>> converters = getRestTemplate().getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJacksonHttpMessageConverter) {
                MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;

                List<MediaType> mTypes = new LinkedList<MediaType>(jsonConverter.getSupportedMediaTypes());
                mTypes.add(new MediaType("text", "javascript", MappingJacksonHttpMessageConverter.DEFAULT_CHARSET));
                jsonConverter.setSupportedMediaTypes(mTypes);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new VKontakteModule());
                jsonConverter.setObjectMapper(objectMapper);
            }
        }
    }

    private void initSubApis() {
        usersOperations = new UsersTemplate(getRestTemplate(), accessToken, uid, isAuthorized());
        friendsOperations = new FriendsTemplate(getRestTemplate(), accessToken, isAuthorized());
        wallOperations = new WallTemplate(getRestTemplate(), accessToken, isAuthorized());
    }

    @Override
    public UsersOperations usersOperations() {
        return usersOperations;
    }

    @Override
    public WallOperations wallOperations() {
        return wallOperations;
    }

    @Override
    public FriendsOperations friendsOperations() {
        return friendsOperations;
    }
}

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
package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

    private IUsersOperations usersOperations;
    private IWallOperations wallOperations;
    private IFriendsOperations friendsOperations;
    private ISecureOperations secureOperations;
    private ILocationOperations locationOperations;
    private IAudioOperations audioOperations;
    private IGroupsOperations groupsOperations;
    private INewsFeedOperations newsFeedOperations;

    private ObjectMapper objectMapper;

    private final String accessToken;
    
    protected final String clientSecret;

    // TODO: remove?
    public VKontakteTemplate() {
        initialize();
        this.accessToken = null;
        this.clientSecret = null;
    }

    public VKontakteTemplate(String accessToken, String clientSecret) {
        super(accessToken);
        this.accessToken = accessToken;
        this.clientSecret = clientSecret;
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
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;

                List<MediaType> mTypes = new LinkedList<MediaType>(jsonConverter.getSupportedMediaTypes());
                mTypes.add(new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET));
                jsonConverter.setSupportedMediaTypes(mTypes);

                objectMapper = new ObjectMapper();
                objectMapper.registerModule(new VKontakteModule());
                jsonConverter.setObjectMapper(objectMapper);
            }
        }
    }

    private void initSubApis() {
        usersOperations = new UsersTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized());
        friendsOperations = new FriendsTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized());
        wallOperations = new WallTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized());
        secureOperations = new SecureTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized(), clientSecret);
        locationOperations = new LocationTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized());
        audioOperations = new AudioTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized());
        groupsOperations = new GroupsTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized());
        newsFeedOperations = new NewsFeedTemplate(getRestTemplate(), accessToken, objectMapper, isAuthorized());
    }

    public IUsersOperations usersOperations() {
        return usersOperations;
    }

    public IWallOperations wallOperations() {
        return wallOperations;
    }

    public IFriendsOperations friendsOperations() {
        return friendsOperations;
    }

    public ISecureOperations secureOperations() {
        return secureOperations;
    }

    public ILocationOperations locationOperations() {
        return locationOperations;
    }

    public IAudioOperations audioOperations() {
        return audioOperations;
    }

    public IGroupsOperations groupsOperations() {
        return groupsOperations;
    }

    public INewsFeedOperations newsFeedOperations() {
        return newsFeedOperations;
    }
}

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

import org.springframework.social.support.URIBuilder;
import org.springframework.social.vkontakte.api.UsersOperations;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.social.vkontakte.api.VKontakteProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * User operations.
 * @author vkolodrevskiy
 */
class UsersTemplate extends AbstractVKontakteOperations implements UsersOperations {
    private final RestTemplate restTemplate;

    private final String uid;

    public UsersTemplate(RestTemplate restTemplate, String accessToken, String uid, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken);
        this.restTemplate = restTemplate;
        this.uid = uid;
    }

    @Override
    public List<VKontakteProfile> getProfiles(List<String> userIds) {
        requireAuthorization();
        Properties props = new Properties();

        StringBuilder uids = new StringBuilder();
        if(userIds != null) {
            for(String uid : userIds) {
                if(uids.toString().isEmpty())
                    uids.append(uid.trim());
                else uids.append(",").append(uid.trim());
            }
        }

        props.put("uids", userIds == null ? uid : uids.toString());
        props.put("fields", "uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate");
        URI uri = URIBuilder.fromUri(makeOperationURL("getProfiles", props)).build();

        VKontakteProfiles profiles = restTemplate.getForObject(uri, VKontakteProfiles.class);
        checkForError(profiles);

        return profiles.getProfiles();
    }

    @Override
    public VKontakteProfile getProfile() {
        return getProfiles(null).get(0);
    }
}

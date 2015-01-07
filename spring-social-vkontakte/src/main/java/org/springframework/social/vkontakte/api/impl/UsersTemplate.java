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
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.IUsersOperations;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.social.vkontakte.api.VKontakteProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * User operations.
 * <br>Here should be implemented methods, that can be found under http://vk.com/dev/methods <code>Users</code> section.
 * @author vkolodrevskiy
 */
class UsersTemplate extends AbstractVKontakteOperations implements IUsersOperations {
    private final RestTemplate restTemplate;
    private final String userEmail;

    public UsersTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser, String userEmail) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
        this.userEmail = userEmail;
    }

    @Override
    public List<VKontakteProfile> getUsers(List<String> userIds) {
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

        props.put("user_ids", userIds == null ? "" : uids.toString());
        props.put("fields", "uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate,sex,screen_name");

        // see documentation under http://vk.com/dev/users.get
        URI uri = makeOperationURL("users.get", props, ApiVersion.VERSION_3_0);

        VKontakteProfiles profiles = restTemplate.getForObject(uri, VKontakteProfiles.class);
        checkForError(profiles);

        return profiles.getProfiles();
    }

    @Override
    public VKontakteProfile getUser() {
        VKontakteProfile vKontakteProfile = getUsers(null).get(0);
        vKontakteProfile.setEmail(userEmail);
        return vKontakteProfile;
    }
}

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
import org.springframework.social.vkontakte.api.vkenums.NameCase;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

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

    public List<VKontakteProfile> getUsers(List<String> userIds) {
        return getUsers(userIds, null);
    }

    public List<VKontakteProfile> getUsers(List<String> userIds, String fields) {
        return getUsers(userIds, fields, NameCase.nom);
    }

    public List<VKontakteProfile> getUsers(List<String> userIds, String fields, NameCase nameCase) {
        requireAuthorization();
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();

        if (userIds != null) {
            StringBuilder sb = new StringBuilder();
            for(String uid : userIds){
                sb.append(uid).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            data.set("user_ids", sb.toString());
        }
        if (nameCase != NameCase.nom) {
            data.set("name_case", nameCase.toString());
        }

        data.set("fields", fields != null? fields: IUsersOperations.DEFAULT_FIELDS);

        // see documentation under http://vk.com/dev/users.get
        URI uri = makeOperationPOST("users.get", data, ApiVersion.VERSION_5_27);
        VKontakteProfiles profiles = restTemplate.postForObject(uri, data, VKontakteProfiles.class);
        checkForError(profiles);

        return profiles.getProfiles();
    }

    public VKontakteProfile getUser() {
        VKontakteProfile vKontakteProfile = getUsers(null).get(0);
        vKontakteProfile.setEmail(userEmail);
        return vKontakteProfile;
    }

    public VKontakteProfile getUser(String fields) {
        return getUsers(null, fields).get(0);
    }
}

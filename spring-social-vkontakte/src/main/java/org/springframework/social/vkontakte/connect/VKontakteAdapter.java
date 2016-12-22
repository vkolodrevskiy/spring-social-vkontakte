/*
 * Copyright 2013-2016 the original author or authors.
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

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.vkontakte.api.VKontakte;

/**
 * VKontakte {@link ApiAdapter} implementation.
 *
 * @author vkolodrevskiy
 */
public class VKontakteAdapter implements ApiAdapter<VKontakte> {
    private final static Log log = LogFactory.getLog(VKontakteAdapter.class);

    private final VkApiClient vkApiClient;

    public VKontakteAdapter() {
        this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());
    }

    public boolean test(VKontakte vkontakte) {
        try {
            UserXtrCounters user = vkApiClient.users().get(vkontakte.getUserActor()).fields(UserField.SCREEN_NAME, UserField.PHOTO_200)
                    .lang(Lang.EN).execute().get(0);
            return true;
        } catch (ApiException | ClientException e) {
            return false;
        }
    }

    public void setConnectionValues(VKontakte vkontakte, ConnectionValues values) {
        try {
            UserXtrCounters user = vkApiClient.users().get(vkontakte.getUserActor())
                    .fields(UserField.PHOTO_200)
                    .lang(Lang.EN).execute().get(0);
            values.setProviderUserId(String.valueOf(user.getId()));
            values.setDisplayName(user.getFirstName() + " " + user.getLastName());
            values.setProfileUrl("https://vk.com/id" + user.getId());
            values.setImageUrl(user.getPhoto200());
        } catch (ApiException | ClientException e) {
            log.error("Error while getting current user info.", e);
        }
    }

    public UserProfile fetchUserProfile(VKontakte vkontakte) {
        try {
            UserXtrCounters user = vkApiClient.users().get(vkontakte.getUserActor())
                    .fields(UserField.SCREEN_NAME, UserField.PHOTO_200)
                    .lang(Lang.EN).execute().get(0);
            return new UserProfileBuilder()
                    .setId(String.valueOf(user.getId()))
                    .setUsername(user.getScreenName())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setName(user.getFirstName() + " " + user.getLastName())
                    .build();
        } catch (ApiException | ClientException e) {
            log.error("Error while getting current user info.", e);
            return new UserProfileBuilder().build();
        }
    }

    public void updateStatus(VKontakte vkontakte, String message) {
        // It's not good idea to post something.
    }
}

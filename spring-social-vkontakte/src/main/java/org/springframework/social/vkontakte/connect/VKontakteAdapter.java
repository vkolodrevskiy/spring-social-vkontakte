/*
 * Copyright 2013-2014 the original author or authors.
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

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.web.client.HttpClientErrorException;

/**
 * VKontakte ApiAdapter implementation.
 * @author vkolodrevskiy
 */
public class VKontakteAdapter implements ApiAdapter<VKontakte> {
	public boolean test(VKontakte vkontakte) {
		try {
			vkontakte.usersOperations().getUser();
			return true;
		} catch (HttpClientErrorException e) {
			return false;
		}
	}

	public void setConnectionValues(VKontakte vkontakte, ConnectionValues values) {
		VKontakteProfile profile = vkontakte.usersOperations().getUser();
		values.setProviderUserId("" + profile.getId());
		values.setDisplayName(profile.getFirstName() + " " + profile.getLastName());
		values.setProfileUrl("http://vk.com/id" + profile.getId());
		values.setImageUrl(profile.getPhoto200());
	}

	public UserProfile fetchUserProfile(VKontakte vkontakte) {
		VKontakteProfile profile = vkontakte.usersOperations().getUser();
		return new UserProfileBuilder()
                .setUsername(profile.getScreenName())
                .setFirstName(profile.getFirstName())
                .setLastName(profile.getLastName())
                .setName(profile.getFirstName() + " " + profile.getLastName())
                .build();
	}

	public void updateStatus(VKontakte vkontakte, String message) {
        // vk api does not allow to perform status.set or wall.post methods for websites,
        // so according to method contract we do nothing here
	}
}

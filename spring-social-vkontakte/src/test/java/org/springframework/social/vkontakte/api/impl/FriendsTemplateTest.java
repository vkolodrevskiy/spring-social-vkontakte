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

import org.junit.Test;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.VKontakteErrorException;
import org.springframework.social.vkontakte.api.VKontakteProfile;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link FriendsTemplate} test.
 * @author vkolodrevskiy
 */
public class FriendsTemplateTest extends AbstractVKontakteApiTest {
    @Test
	public void get_currentUser() throws ParseException {
		mockServer.expect(requestTo("https://api.vk.com/method/friends.get?access_token=ACCESS_TOKEN&v=5.27&fields=first_name%2Clast_name%2Cphoto_50%2Cphoto_100%2Cphoto_200%2Ccontacts%2Cbdate%2Csex%2Cscreen_name"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("list-of-profiles-5_27"), APPLICATION_JSON));

		List<VKontakteProfile> friends = vkontakte.friendsOperations().get();
        assertFriends(friends);
	}

	@Test(expected = MissingAuthorizationException.class)
	public void get_currentUser_unauthorized() {
		unauthorizedVKontakte.friendsOperations().get();
	}

    @Test
	public void get_byUserId() throws ParseException {
		mockServer.expect(requestTo("https://api.vk.com/method/friends.get?access_token=ACCESS_TOKEN&v=5.27&fields=first_name%2Clast_name%2Cphoto_50%2Cphoto_100%2Cphoto_200%2Ccontacts%2Cbdate%2Csex%2Cscreen_name&user_id=123"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("list-of-profiles-5_27"), APPLICATION_JSON));

		List<VKontakteProfile> friends = vkontakte.friendsOperations().get(123L);
        assertFriends(friends);
    }

	@Test(expected = MissingAuthorizationException.class)
	public void getFriends_byUserId_unauthorized() {
		unauthorizedVKontakte.friendsOperations().get("123");
	}

	@Test(expected = VKontakteErrorException.class)
	public void get_expiredToken() {
		mockServer.expect(requestTo("https://api.vk.com/method/friends.get?access_token=ACCESS_TOKEN&v=5.27&fields=first_name%2Clast_name%2Cphoto_50%2Cphoto_100%2Cphoto_200%2Ccontacts%2Cbdate%2Csex%2Cscreen_name&user_id=123"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));

		vkontakte.friendsOperations().get(123L);
	}

    @Test(expected = VKontakteErrorException.class)
    public void getOnline_expiredToken() {
        mockServer.expect(requestTo("https://api.vk.com/method/friends.getOnline?access_token=ACCESS_TOKEN&v=5.27"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));

        vkontakte.friendsOperations().getOnline(false, -1, -1);
    }

    @Test(expected = VKontakteErrorException.class)
    public void getOnline_tooManyRequests() {
        mockServer.expect(requestTo("https://api.vk.com/method/friends.getOnline?access_token=ACCESS_TOKEN&v=5.27"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error-code-6-too-many-requests"), APPLICATION_JSON));

        vkontakte.friendsOperations().getOnline(false, -1, -1);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getOnline_unauthorized() {
        unauthorizedVKontakte.friendsOperations().getOnline(1L, false, -1, -1);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getOnline_currentUser_unauthorized() {
        unauthorizedVKontakte.friendsOperations().getOnline(false, -1, -1);
    }

    @Test
    public void getOnlineSiteAndMobile() {
        mockServer.expect(requestTo("https://api.vk.com/method/friends.getOnline?access_token=ACCESS_TOKEN&v=5.27&user_id=1&online_mobile=1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("friends-getonline-site-and-mobile-5_27"), APPLICATION_JSON));

        List<List<String>> friends = vkontakte.friendsOperations().getOnline(1L, true, -1, -1);
        assertEquals(15, friends.get(0).size());
        assertEquals(6, friends.get(1).size());
    }

    @Test
    public void getOnlineSiteOnly() {
        mockServer.expect(requestTo("https://api.vk.com/method/friends.getOnline?access_token=ACCESS_TOKEN&v=5.27"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("friends-getonline-site-only-5_27"), APPLICATION_JSON));

        List<List<String>> friends = vkontakte.friendsOperations().getOnline(false, -1, -1);
        assertEquals(20, friends.get(0).size());
    }

    @Test
    public void getOnlineAllParametersPresent() {
        // check that access_token=ACCESS_TOKEN&v=5.8&user_id=123&count=5&online_mobile=1 parameters are present
        mockServer.expect(requestTo("https://api.vk.com/method/friends.getOnline?access_token=ACCESS_TOKEN&v=5.27&user_id=123&count=5&offset=6&online_mobile=1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("friends-getonline-site-only-5_27"), APPLICATION_JSON));
        vkontakte.friendsOperations().getOnline(123L, true, 5, 6);
    }

    private void assertFriends(List<VKontakteProfile> profiles) throws ParseException {
        // TODO: write tests
        assertEquals(3, profiles.size());

        assertEquals(1, profiles.get(0).getId());
        assertEquals("Павел", profiles.get(0).getFirstName());
        assertEquals("Дуров", profiles.get(0).getLastName());
        assertEquals("Telegram", profiles.get(0).getOccupation().getName());
        assertEquals(2006, profiles.get(0).getGraduation());
        assertEquals(7, profiles.get(0).getPersonal().getLangs().size());
        assertEquals(1, profiles.get(0).getUniversities().get(0).getCountryId());
        assertEquals(1992, profiles.get(0).getSchools().get(0).getYearTo());

        assertEquals(2183, profiles.get(1).getId());
        assertEquals("Владислав", profiles.get(1).getFirstName());
        assertEquals("Ефремов", profiles.get(1).getLastName());
        assertEquals(1988, profiles.get(1).getBirthDate().getYear());
        assertEquals("№ 239", profiles.get(1).getSchools().get(0).getName());

        assertEquals(77478, profiles.get(2).getId());
        assertEquals("Егор", profiles.get(2).getFirstName());
        assertEquals("Иванов", profiles.get(2).getLastName());
        assertEquals("M", profiles.get(2).getGender());
        assertEquals(216966, profiles.get(2).getRelationPartner().getId());
        assertEquals("sibling", profiles.get(2).getRelatives().get(0).getType());
    }
}

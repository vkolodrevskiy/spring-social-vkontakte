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
		mockServer.expect(requestTo("https://api.vk.com/method/friends.get?access_token=ACCESS_TOKEN&v=3.0&fields=uid%2Cfirst_name%2Clast_name%2Cphoto%2Cphoto_medium%2Cphoto_big%2Ccontacts%2Cbdate%2Csex%2Cscreen_name"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("list-of-profiles"), APPLICATION_JSON));

		List<VKontakteProfile> friends = vkontakte.friendsOperations().get();
        assertFriends(friends);
	}

	@Test(expected = MissingAuthorizationException.class)
	public void get_currentUser_unauthorized() {
		unauthorizedVKontakte.friendsOperations().get();
	}

    @Test
	public void get_byUserId() throws ParseException {
		mockServer.expect(requestTo("https://api.vk.com/method/friends.get?access_token=ACCESS_TOKEN&v=3.0&fields=uid%2Cfirst_name%2Clast_name%2Cphoto%2Cphoto_medium%2Cphoto_big%2Ccontacts%2Cbdate%2Csex%2Cscreen_name&uid=123"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("list-of-profiles"), APPLICATION_JSON));

		List<VKontakteProfile> friends = vkontakte.friendsOperations().get("123");
        assertFriends(friends);
    }

	@Test(expected = MissingAuthorizationException.class)
	public void getFriends_byUserId_unauthorized() {
		unauthorizedVKontakte.friendsOperations().get("123");
	}

	@Test(expected = VKontakteErrorException.class)
	public void get_expiredToken() {
		mockServer.expect(requestTo("https://api.vk.com/method/friends.get?access_token=ACCESS_TOKEN&v=3.0&fields=uid%2Cfirst_name%2Clast_name%2Cphoto%2Cphoto_medium%2Cphoto_big%2Ccontacts%2Cbdate%2Csex%2Cscreen_name&uid=123"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));

		vkontakte.friendsOperations().get("123");
	}

    private void assertFriends(List<VKontakteProfile> profiles) throws ParseException {
        assertEquals(3, profiles.size());

        assertEquals("24840", profiles.get(0).getUid());
        assertEquals("John", profiles.get(0).getFirstName());
        assertEquals("Doe", profiles.get(0).getLastName());
        assertEquals(1977, profiles.get(0).getBirthDate().getYear());
        assertEquals(7, profiles.get(0).getBirthDate().getMonth());
        assertEquals(7, profiles.get(0).getBirthDate().getDay());
        assertEquals("http://cs9889.vkontakte.ru/u24840/e_5b02b7ad.jpg", profiles.get(0).getPhoto());
        assertEquals("http://cs9889.vkontakte.ru/u24840/b_04d9723f.jpg", profiles.get(0).getPhotoMedium());
        assertEquals("http://cs9889.vkontakte.ru/u24840/a_f934a8e6.jpg", profiles.get(0).getPhotoBig());

        assertEquals("135474", profiles.get(1).getUid());
        assertEquals("William", profiles.get(1).getFirstName());
        assertEquals("Peters", profiles.get(1).getLastName());
        assertEquals(1977, profiles.get(1).getBirthDate().getYear());
        assertEquals(7, profiles.get(1).getBirthDate().getMonth());
        assertEquals(8, profiles.get(1).getBirthDate().getDay());
        assertEquals("http://cs5596.vkontakte.ru/u135474/e_148bbf42.jpg", profiles.get(1).getPhoto());
        assertEquals("http://cs5596.vkontakte.ru/u135474/b_176664e7.jpg", profiles.get(1).getPhotoMedium());
        assertEquals("http://cs5596.vkontakte.ru/u135474/a_ead98900.jpg", profiles.get(1).getPhotoBig());

        assertEquals("501245", profiles.get(2).getUid());
        assertEquals("Ivan", profiles.get(2).getFirstName());
        assertEquals("Petrov", profiles.get(2).getLastName());
        assertEquals(1977, profiles.get(2).getBirthDate().getYear());
        assertEquals(7, profiles.get(2).getBirthDate().getMonth());
        assertEquals(9, profiles.get(2).getBirthDate().getDay());
        assertEquals("http://vkontakte.ru/images/question_c.gif", profiles.get(2).getPhoto());
        assertEquals("http://vkontakte.ru/images/question_b.gif", profiles.get(2).getPhotoMedium());
        assertEquals("http://vkontakte.ru/images/question_a.gif", profiles.get(2).getPhotoBig());
    }
}

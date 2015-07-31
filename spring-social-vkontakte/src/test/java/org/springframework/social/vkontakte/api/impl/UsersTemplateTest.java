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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link UsersTemplate} test
 * 
 * @author vkolodrevskiy
 */
public class UsersTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void getUser_currentUser() {
        mockServer
                .expect(requestTo("https://api.vk.com/method/users.get"))
                .andExpect(method(POST)).andRespond(withSuccess(jsonResource("list-of-profiles-5_27"), APPLICATION_JSON));

        VKontakteProfile profile = vkontakte.usersOperations().getUser("sex, bdate, city, country, photo_50, photo_100, photo_200_orig, photo_200, photo_400_orig, photo_max, photo_max_orig, photo_id, online, online_mobile, domain, has_mobile, contacts, connections, site, education, universities, schools, can_post, can_see_all_posts, can_see_audio, can_write_private_message, status, last_seen, common_count, relation, relatives, counters, screen_name, maiden_name, timezone, occupation,activities, interests, music, movies, tv, books, games, about, quotes, personal, friends_status");

        assertEquals(1, profile.getId());
        assertEquals("Павел", profile.getFirstName());
        assertEquals("Дуров", profile.getLastName());
        assertEquals(705, profile.getCounters().getFriends());
    }

    @Test
    public void getUsers_currentUser() {
        mockServer
                .expect(requestTo("https://api.vk.com/method/users.get"))
                .andExpect(method(POST)).andRespond(withSuccess(jsonResource("list-of-profiles-5_27"), APPLICATION_JSON));
        String[] userIds = {"durov", "2183", "77478"};
        List<VKontakteProfile> profiles = vkontakte.usersOperations().getUsers(Arrays.asList(userIds));

        assertProfiles(profiles);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getUser_unauthorized() {
        unauthorizedVKontakte.usersOperations().getUser();
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getUsers_unauthorized() {
        String[] userIds = {"1", "2"};
        unauthorizedVKontakte.usersOperations().getUsers(Arrays.asList(userIds));
    }

    @Test(expected = VKontakteErrorException.class)
    public void getUser_expiredToken() {
        mockServer
                .expect(requestTo("https://api.vk.com/method/users.get"))
                .andExpect(method(POST)).andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));

        vkontakte.usersOperations().getUser();
    }

    @Test(expected = VKontakteErrorException.class)
    public void getUsers_expiredToken() {
        mockServer
               .expect(requestTo("https://api.vk.com/method/users.get"))
                .andExpect(method(POST)).andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));
        String[] userIds = {"1", "2"};
        vkontakte.usersOperations().getUsers(Arrays.asList(userIds));
    }

    private void assertProfiles(List<VKontakteProfile> profiles) {
        assertEquals(4, profiles.size());

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

        assertEquals("banned", profiles.get(3).getDeactivated());
    }
}

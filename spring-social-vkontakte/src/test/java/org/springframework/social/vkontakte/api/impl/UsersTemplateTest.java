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

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.VKontakteErrorException;
import org.springframework.social.vkontakte.api.VKontakteProfile;

/**
 * {@link UsersTemplate} test
 * 
 * @author vkolodrevskiy
 */
public class UsersTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void getProfile_currentUser() {
        mockServer
                .expect(requestTo("https://api.vkontakte.ru/method/getProfiles?access_token=ACCESS_TOKEN&fields=uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate&uids=USER_ID"))
                .andExpect(method(GET)).andRespond(withResponse(jsonResource("list-of-profiles"), responseHeaders));

        VKontakteProfile profile = vkontakte.usersOperations().getProfile();

        assertEquals("24840", profile.getUid());
        assertEquals("John", profile.getFirstName());
        assertEquals("Doe", profile.getLastName());
        assertEquals("http://cs9889.vkontakte.ru/u24840/e_5b02b7ad.jpg", profile.getPhoto());
        assertEquals("http://cs9889.vkontakte.ru/u24840/b_04d9723f.jpg", profile.getPhotoMedium());
        assertEquals("http://cs9889.vkontakte.ru/u24840/a_f934a8e6.jpg", profile.getPhotoBig());
    }

    @Test
    public void getProfiles_currentUser() {
        mockServer
                .expect(requestTo("https://api.vkontakte.ru/method/getProfiles?access_token=ACCESS_TOKEN&fields=uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate&uids=1,2,3"))
                .andExpect(method(GET)).andRespond(withResponse(jsonResource("list-of-profiles"), responseHeaders));

        List<VKontakteProfile> profiles = vkontakte.usersOperations().getProfiles(new ArrayList<String>() {
            private static final long serialVersionUID = 583451105415032388L;

            {
                add("1");
                add("2");
                add("3");
            }
        });

        assertProfiles(profiles);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getProfile_unauthorized() {
        unauthorizedVKontakte.usersOperations().getProfile();
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getProfiles_unauthorized() {
        unauthorizedVKontakte.usersOperations().getProfiles(new ArrayList<String>() {
            private static final long serialVersionUID = 9175797703832707981L;

            {
                add("1");
                add("2");
            }
        });
    }

    @Test(expected = VKontakteErrorException.class)
    public void getProfile_expiredToken() {
        mockServer
                .expect(requestTo("https://api.vkontakte.ru/method/getProfiles?access_token=ACCESS_TOKEN&fields=uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate&uids=USER_ID"))
                .andExpect(method(GET)).andRespond(withResponse(jsonResource("error-code-5"), responseHeaders));

        vkontakte.usersOperations().getProfile();
    }

    @Test(expected = VKontakteErrorException.class)
    public void getProfiles_expiredToken() {
        mockServer
                .expect(requestTo("https://api.vkontakte.ru/method/getProfiles?access_token=ACCESS_TOKEN&fields=uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate&uids=1,2"))
                .andExpect(method(GET)).andRespond(withResponse(jsonResource("error-code-5"), responseHeaders));

        vkontakte.usersOperations().getProfiles(new ArrayList<String>() {
            private static final long serialVersionUID = 4211781776280003660L;
            {
                add("1");
                add("2");
            }
        });
    }

    private void assertProfiles(List<VKontakteProfile> profiles) {
        assertEquals(3, profiles.size());

        assertEquals("24840", profiles.get(0).getUid());
        assertEquals("John", profiles.get(0).getFirstName());
        assertEquals("Doe", profiles.get(0).getLastName());
        assertEquals("http://cs9889.vkontakte.ru/u24840/e_5b02b7ad.jpg", profiles.get(0).getPhoto());
        assertEquals("http://cs9889.vkontakte.ru/u24840/b_04d9723f.jpg", profiles.get(0).getPhotoMedium());
        assertEquals("http://cs9889.vkontakte.ru/u24840/a_f934a8e6.jpg", profiles.get(0).getPhotoBig());

        assertEquals("135474", profiles.get(1).getUid());
        assertEquals("William", profiles.get(1).getFirstName());
        assertEquals("Peters", profiles.get(1).getLastName());
        assertEquals("http://cs5596.vkontakte.ru/u135474/e_148bbf42.jpg", profiles.get(1).getPhoto());
        assertEquals("http://cs5596.vkontakte.ru/u135474/b_176664e7.jpg", profiles.get(1).getPhotoMedium());
        assertEquals("http://cs5596.vkontakte.ru/u135474/a_ead98900.jpg", profiles.get(1).getPhotoBig());

        assertEquals("501245", profiles.get(2).getUid());
        assertEquals("Ivan", profiles.get(2).getFirstName());
        assertEquals("Petrov", profiles.get(2).getLastName());
        assertEquals("http://vkontakte.ru/images/question_c.gif", profiles.get(2).getPhoto());
        assertEquals("http://vkontakte.ru/images/question_b.gif", profiles.get(2).getPhotoMedium());
        assertEquals("http://vkontakte.ru/images/question_a.gif", profiles.get(2).getPhotoBig());
    }
}

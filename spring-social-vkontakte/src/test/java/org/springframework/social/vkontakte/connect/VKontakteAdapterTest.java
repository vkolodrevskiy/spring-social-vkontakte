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
package org.springframework.social.vkontakte.connect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.vkontakte.api.UsersOperations;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteDate;
import org.springframework.social.vkontakte.api.VKontakteProfile;

/**
 * {@link VKontakteAdapter} test.
 * @author vkolodrevskiy
 */
public class VKontakteAdapterTest {
	private VKontakteAdapter apiAdapter = new VKontakteAdapter();
	
	private VKontakte vkontakte = Mockito.mock(VKontakte.class);
    private UsersOperations usersOperations = Mockito.mock(UsersOperations.class);
	
	@Test
	public void fetchProfile() {
		Mockito.when(usersOperations.getProfile()).thenReturn(new VKontakteProfile("123", "Viktor", "Kolodrevskiy", "http://cs9686.vkontakte.ru/u6398868/a_4e041afa.jpg", "http://cs9686.vkontakte.ru/u6398868/a_4e041afa.jpg", "http://cs9686.vkontakte.ru/u6398868/a_4e041afa.jpg", "1111", "222", new VKontakteDate(15,12,1977)));
        Mockito.when(vkontakte.usersOperations()).thenReturn(usersOperations);

        UserProfile profile = apiAdapter.fetchUserProfile(vkontakte);
		assertEquals("Viktor Kolodrevskiy", profile.getName());
		assertEquals("Viktor", profile.getFirstName());
		assertEquals("Kolodrevskiy", profile.getLastName());
		assertNull(profile.getEmail());
		assertNull(profile.getUsername());
	}
}

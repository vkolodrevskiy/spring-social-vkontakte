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

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.vkontakte.api.IUsersOperations;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteDate;
import org.springframework.social.vkontakte.api.VKontakteProfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * {@link VKontakteAdapter} test.
 * @author vkolodrevskiy
 */
public class VKontakteAdapterTest {
    private VKontakteAdapter apiAdapter = new VKontakteAdapter();

    private VKontakte vkontakte = Mockito.mock(VKontakte.class);
    private IUsersOperations usersOperations = Mockito.mock(IUsersOperations.class);

    @Test
    public void fetchProfile() {
        VKontakteProfile tmpProfile = new VKontakteProfile();
        tmpProfile.setScreenName("id123");
        tmpProfile.setFirstName("Viktor");
        tmpProfile.setLastName("Kolodrevskiy");
        Mockito.when(usersOperations.getUser()).thenReturn(tmpProfile);
        Mockito.when(vkontakte.usersOperations()).thenReturn(usersOperations);

        UserProfile profile = apiAdapter.fetchUserProfile(vkontakte);
        assertEquals("id123", profile.getUsername());
        assertEquals("Viktor", profile.getFirstName());
        assertEquals("Kolodrevskiy", profile.getLastName());
        assertNull(profile.getEmail());
    }
}

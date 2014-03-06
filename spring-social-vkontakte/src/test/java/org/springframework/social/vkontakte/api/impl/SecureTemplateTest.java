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
import org.springframework.social.vkontakte.api.VKontakteErrorException;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link org.springframework.social.vkontakte.api.impl.SecureTemplate} test.
 * @author bsideup
 */
public class SecureTemplateTest extends AbstractVKontakteApiTest {

    @Test(expected = VKontakteErrorException.class)
    public void notification_accessDenied() {
        mockServer.expect(requestTo("https://api.vk.com/method/secure.sendNotification?access_token=ACCESS_TOKEN&client_secret=CLIENT_SECRET&v=5.8&user_ids=1%2C2%2C3&message=hello"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error-code-15"), APPLICATION_JSON));

        vkontakte.secureOperations().sendNotification(Arrays.asList("1", "2", "3"), "hello");
    }
}

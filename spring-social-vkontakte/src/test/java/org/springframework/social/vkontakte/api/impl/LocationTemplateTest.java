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

import org.junit.Test;
import org.springframework.social.vkontakte.api.VKontakteErrorException;

import org.springframework.social.vkontakte.api.City;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link LocationTemplate} test.
 * @author badbob
 */
public class LocationTemplateTest extends AbstractVKontakteApiTest {

    @Test
    public void getCity() {
        mockServer.expect(requestTo("https://api.vk.com/method/database.getCityById?access_token=ACCESS_TOKEN&v=5.27&city_ids=1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("location-post-response-5_27"), APPLICATION_JSON));
        City city = vkontakte.locationOperations().getCityById(1);
        assertEquals("Москва", city.getTitle());
    }

    @Test(expected = VKontakteErrorException.class)
    public void getCity_expiredToken() {
	mockServer.expect(requestTo("https://api.vk.com/method/database.getCityById?access_token=ACCESS_TOKEN&v=5.27&city_ids=54"))
		.andExpect(method(GET))
		.andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));

	vkontakte.locationOperations().getCityById(54);
    }
}

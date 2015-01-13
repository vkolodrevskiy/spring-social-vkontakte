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
import org.springframework.social.vkontakte.api.Group;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link org.springframework.social.vkontakte.api.impl.GroupsTemplate} test.
 * @author dIsoVi
 */
public class GroupsTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void getGroups() {
        mockServer.expect(requestTo("https://api.vk.com/method/groups.get?access_token=ACCESS_TOKEN&v=5.27&extended=1"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("groups-getgroups-response-5_27"), APPLICATION_JSON));
        List<Group> groups = vkontakte.groupsOperations().getGroups();
        assertEquals(40, groups.size());
        assertEquals("roem", groups.get(0).getScreenName());
    }

    @Test
    public void getGroupsShort() {
        mockServer.expect(requestTo("https://api.vk.com/method/groups.get?access_token=ACCESS_TOKEN&v=5.27"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("groups-short-getgroups-response-5_27"), APPLICATION_JSON));
        List<Group> groups = vkontakte.groupsOperations().getGroups(0, null, -1, 0, false);
        assertEquals(53, groups.size());
    }
}

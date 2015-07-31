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
import org.springframework.social.vkontakte.api.VKontakteErrorException;
import org.springframework.social.vkontakte.api.VKontakteProfile;

import java.util.Arrays;
import java.util.Collections;
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

    @Test
    public void getGroupsByIds() {
        mockServer.expect(requestTo("https://api.vk.com/method/groups.getById?access_token=ACCESS_TOKEN&v=5.27&group_ids=apiclub%2C55293029"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("groups-getByIds-response-5_27"), APPLICATION_JSON));

        List<Group> groups = vkontakte.groupsOperations().getByIds(Arrays.asList("apiclub", "55293029"));
        assertEquals("should be 2 groups", 2, groups.size());

        Group apiClubGroup = groups.get(0);
        assertEquals("apiclub", apiClubGroup.getScreenName());
        assertEquals(1, apiClubGroup.getGroupId());

        Group mntofGroup = groups.get(1);
        assertEquals("mntof", mntofGroup.getScreenName());
        assertEquals(55293029, mntofGroup.getGroupId());
    }

    @Test(expected = VKontakteErrorException.class)
    public void getGroupsByIds_unauthorized() {
        mockServer
                .expect(requestTo("https://api.vk.com/method/groups.getById?access_token=ACCESS_TOKEN&v=5.27&group_ids=2%2C3"))
                .andExpect(method(GET)).andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));

        vkontakte.groupsOperations().getByIds(Arrays.asList("2", "3"));
    }

    @Test
    public void getMembers() {
        mockServer.expect(requestTo("https://api.vk.com/method/groups.getMembers?access_token=ACCESS_TOKEN&v=5.27&fields=first_name&count=20&group_id=apiclub&offset=30"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("groups-getMembers-response-5_27"), APPLICATION_JSON));

        List<VKontakteProfile> members = vkontakte.groupsOperations().getMembers("apiclub", "first_name", 20, 30);
        assertEquals("should be 20 members", 20, members.size());

        VKontakteProfile yuryProfile = members.get(0);
        assertEquals("Yury", yuryProfile.getFirstName());
        assertEquals(620, yuryProfile.getId());
    }

    @Test(expected = VKontakteErrorException.class)
    public void getMembers_unauthorized() {
        mockServer
                .expect(requestTo("https://api.vk.com/method/groups.getMembers?access_token=ACCESS_TOKEN&v=5.27&fields=last_name&count=10&group_id=id&offset=5"))
                .andExpect(method(GET)).andRespond(withSuccess(jsonResource("error-code-5"), APPLICATION_JSON));

        vkontakte.groupsOperations().getMembers("id", "last_name", 10, 5);
    }


}

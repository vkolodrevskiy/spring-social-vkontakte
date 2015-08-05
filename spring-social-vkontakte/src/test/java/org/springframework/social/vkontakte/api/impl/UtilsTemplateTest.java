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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Test;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.social.vkontakte.api.VKObject;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link UtilsTemplate} test.
 * @author dIsoVi
 */
public class UtilsTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void resolveScreenName() {
        mockServer.expect(requestTo("https://api.vk.com/method/utils.resolveScreenName?access_token=ACCESS_TOKEN&v=5.27&screen_name=durov"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("utils-resolve-screen-name-5.27"), APPLICATION_JSON));
        VKObject vkObject = vkontakte.utilsOperations().resolveScreenName("durov");
        assertEquals("user", vkObject.getType());
        assertEquals(1, vkObject.getId());
    }

    @Test
    public void resolveScreenNameEmpty() {
        mockServer.expect(requestTo("https://api.vk.com/method/utils.resolveScreenName?access_token=ACCESS_TOKEN&v=5.27&screen_name=durov"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("utils-resolve-screen-name-empty-5.27"), APPLICATION_JSON));
        VKObject vkObject = vkontakte.utilsOperations().resolveScreenName("durov");
        assertEquals(null, vkObject);
    }

    @Test
    public void testExecute() throws Exception {
        mockServer.expect(requestTo("https://api.vk.com/method/execute"))
                .andExpect(method(POST))
                .andRespond(withSuccess(jsonResource("utils-execute-response-5_35"), APPLICATION_JSON));
        String code = "var posts = API.wall.get({\"count\": 1});\n" +
                "\n" +
                "if (posts.count<0) {\n" +
                "  return {\"post\": null, \"copy_owner\": null};\n" +
                "} else {\n" +
                "  var post = posts.items[0];\n" +
                "  var copy_owner=null;\n" +
                "  if (post.copy_history[0]){\n" +
                "    if (post.copy_history[0].owner_id > 0) {\n" +
                "      copy_owner = API.users.get({\"user_id\": post.copy_history[0].owner_id})[0];\n" +
                "    } else\n" +
                "    if (post.copy_history[0].owner_id  < 0) {\n" +
                "      copy_owner = API.groups.getById({\"group_ids\": -post.copy_history[0].owner_id})[0];\n" +
                "    }\n" +
                "    return {\"post\": post, \"copy_owner\": copy_owner};\n" +
                "  } else {\n" +
                "    return {\"post\": post, \"copy_owner\": null};\n" +
                "  }\n" +
                "}";
        VKGenericResponse response = vkontakte.utilsOperations().execute(code);
        assertEquals(1, response.getResponse().get("post").get("copy_history").size());
    }
}

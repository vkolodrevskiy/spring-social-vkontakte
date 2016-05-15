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
import org.springframework.social.vkontakte.api.Comment;
import org.springframework.social.vkontakte.api.CommentsResponse;
import org.springframework.social.vkontakte.api.Post;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.social.vkontakte.api.attachment.Attachment;
import org.springframework.social.vkontakte.api.attachment.AttachmentType;
import org.springframework.social.vkontakte.api.attachment.PhotosListAttachment;
import org.springframework.social.vkontakte.api.attachment.StickerAttachment;
import org.springframework.social.vkontakte.api.impl.wall.CommentsQuery;
import org.springframework.social.vkontakte.api.impl.wall.CommunityWall;
import org.springframework.social.vkontakte.api.impl.wall.UserWall;
import org.springframework.social.vkontakte.api.vkenums.SortOrder;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link WallTemplate} test.
 * @author vkolodrevskiy
 */
public class WallTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void getPosts() {
        mockServer.expect(requestTo("https://api.vk.com/method/wall.get?access_token=ACCESS_TOKEN&v=5.27"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("wall-getposts-response-5_27"), APPLICATION_JSON));
        List<Post> posts = vkontakte.wallOperations().getPosts();
        assertPosts(posts);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getCurrentUserPostsUnauthorized() {
        unauthorizedVKontakte.wallOperations().getPosts();
    }

    /**
     * Check if impl can get wall of user by id even if he's not unauthorized
     */
    @Test
    public void getUserPostsUnauthorized() {
        unauthorizedMockServer.expect(requestTo("https://api.vk.com/method/wall.get?v=5.27&owner_id=1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("wall-getposts-response-5_27"), APPLICATION_JSON));
        List<Post> posts = unauthorizedVKontakte.wallOperations().getPostsForUser(1L, -1, -1);
        assertPosts(posts);
    }

    @Test
    public void getCommunityWallComments() {
        mockServer
                .expect(requestTo("https://api.vk.com/method/wall.getComments"))
                .andExpect(method(POST))
                .andExpect(content().string("owner_id=-85635407&post_id=3199&preview_length=0&access_token=ACCESS_TOKEN&v=5.33"))
                .andRespond(withSuccess(jsonResource("wall-getComments-response-5_33"), APPLICATION_JSON));
        CommentsQuery request = new CommentsQuery.Builder(new CommunityWall(85635407), 3199).build();

        CommentsResponse response = vkontakte.wallOperations().getComments(request);

        assertEquals(16, response.getCount());
    }

    @Test
    public void getUserWallComments() {
        mockServer
                .expect(requestTo("https://api.vk.com/method/wall.getComments"))
                .andExpect(method(POST))
                .andExpect(content().string("owner_id=85635407&post_id=3199&need_likes=1&start_comment_id=3204&offset=2&count=3&sort=desc&preview_length=9999&extended=1&access_token=ACCESS_TOKEN&v=5.33"))
                .andRespond(withSuccess(jsonResource("wall-getComments-response-5_33"), APPLICATION_JSON));
        CommentsQuery request = new CommentsQuery(new UserWall(85635407), 3199, true, 3204, 2, 3, SortOrder.desc, 9999, true);

        CommentsResponse response = vkontakte.wallOperations().getComments(request);

        assertEquals(16, response.getCount());
        assertEquals(10, response.getRealOffset());

        assertEquals(6, response.getComments().size());
        assertEquals(4, response.getProfiles().size());

        assertComment(response.getComments().get(0));
        assertProfile(response.getProfiles().get(0));
    }

    private void assertComment(Comment comment) {
        assertEquals(3205, comment.getId());
        assertEquals(1375980597000L, comment.getDate().getTime());
        assertEquals(85635407, comment.getFromId());
        assertEquals("[id3197366|Андрей], поправил", comment.getText());
        assertEquals(3197366, comment.getReplyToUser());
        assertEquals(3200, comment.getReplyToComment());
        assertEquals(666, comment.getLikes().getCount());
        assertEquals(true, comment.getLikes().isUserLikes());
        assertEquals(2, comment.getAttachments().size());
    }

    private void assertProfile(VKontakteProfile profile) {
        assertEquals(3197366, profile.getId());
        assertEquals("suppo", profile.getScreenName());
        assertEquals("M", profile.getGender());
        assertEquals(true, profile.isOnline());
    }

    private void assertPosts(List<Post> posts) {
        assertEquals(23, posts.size());
        Attachment photosListAttachment = posts.get(1).getAttachments().get(1);
        assertEquals(AttachmentType.PHOTOS_LIST, photosListAttachment.getType());
        assertEquals(306810815, ((PhotosListAttachment)photosListAttachment).getPhotosList().get(0).getPhotoId());
        assertEquals(45555, posts.get(1).getId());
        assertEquals(22694, posts.get(1).getLikes().getCount());
        assertEquals(false, posts.get(1).getLikes().isUserLikes());
        assertEquals(2, posts.get(1).getAttachments().size());
        assertEquals(3, ((StickerAttachment)posts.get(22).getCopyHistory().get(0).getAttachments().get(0)).getSticker().getProductId());
    }
}

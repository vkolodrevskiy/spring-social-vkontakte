package org.springframework.social.vkontakte.api.impl;

import org.junit.Test;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.Audio;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link AudioTemplate} test.
 *
 * @author vkolodrevskiy
 */
public class AudioTemplateTest extends AbstractVKontakteApiTest {
    @Test(expected = MissingAuthorizationException.class)
    public void get_unauthorized() {
        unauthorizedVKontakte.audioOperations().get();
    }

    @Test(expected = MissingAuthorizationException.class)
    public void get_with_ownerId_unauthorized() {
        unauthorizedVKontakte.audioOperations().get(123);
    }

    @Test
    public void get() throws ParseException {
        mockServer.expect(requestTo("https://api.vk.com/method/audio.get?access_token=ACCESS_TOKEN&v=5.21&need_user=0"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("audio-valid-response-5_21"), APPLICATION_JSON));

        List<Audio> audios = vkontakte.audioOperations().get();
        assertNotNull(audios);
        assertEquals("audio list size", 10, audios.size());
    }

    @Test
    public void getByOwnerId() throws ParseException {
        mockServer.expect(requestTo("https://api.vk.com/method/audio.get?access_token=ACCESS_TOKEN&v=5.21&owner_id=145&need_user=0"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("audio-valid-response-5_21"), APPLICATION_JSON));

        List<Audio> audios = vkontakte.audioOperations().get(145);
        assertNotNull(audios);
        assertEquals("audio list size", 10, audios.size());
    }
}

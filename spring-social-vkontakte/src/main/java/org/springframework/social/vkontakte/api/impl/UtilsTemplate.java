package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.IUtilsOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.social.vkontakte.api.VKObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.IUtilsOperations} implementation.
 * @author dIsoVi
 */
public class UtilsTemplate extends AbstractVKontakteOperations implements IUtilsOperations {
    private final RestTemplate restTemplate;

    public UtilsTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    public VKObject resolveScreenName(String screenName) {
        Properties props = new Properties();
        props.put("screen_name", screenName);
        URI uri = makeOperationURL("utils.resolveScreenName", props, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        return deserializeVK50Item(response, VKObject.class);
    }

    // TODO: give user the ability to deserialize response by setting template class?
    public VKGenericResponse execute(String code) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
        data.set("code", code);
        URI uri = makeOperationPOST("execute", data, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.postForObject(uri, data, VKGenericResponse.class);
        checkForError(response);
        return response;
    }
}

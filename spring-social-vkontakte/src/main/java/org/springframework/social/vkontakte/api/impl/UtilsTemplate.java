package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.IUtilsOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.social.vkontakte.api.VKObject;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.IUtilsOperations} implementation.
 * @author dIsoVi
 */
public class UtilsTemplate extends AbstractVKontakteOperations implements IUtilsOperations {
    private final RestTemplate restTemplate;

    public UtilsTemplate(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(false, null, objectMapper);
        this.restTemplate = restTemplate;
    }

    public VKObject resolveScreenName(String screenName) {
        Properties props = new Properties();
        props.put("screen_name", screenName);
        URI uri = makeOperationURL("utils.resolveScreenName", props, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        return deserializeVK50Item(response, VKObject.class);
    }
}

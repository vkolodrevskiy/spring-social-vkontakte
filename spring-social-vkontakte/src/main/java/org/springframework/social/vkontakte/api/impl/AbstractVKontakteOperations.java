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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.social.vkontakte.api.VKResponse;
import org.springframework.social.vkontakte.api.VKontakteErrorException;
import org.springframework.social.vkontakte.api.impl.json.VKArray;
import org.springframework.util.Assert;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Generic class for operation templates.
 * @author vkolodrevskiy
 */
class AbstractVKontakteOperations {
    private final static String VK_REST_URL = "https://api.vk.com/method/";

    private final boolean isAuthorized;
    private final String accessToken;
    protected final ObjectMapper objectMapper;

    public AbstractVKontakteOperations(boolean isAuthorized, String accessToken, ObjectMapper objectMapper) {
        this.isAuthorized = isAuthorized;
        this.accessToken = accessToken;
        this.objectMapper = objectMapper;
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException("vkontakte");
        }
    }

    protected URI makeOperationURL(String method, Properties params, ApiVersion apiVersion) {
        URIBuilder uri = URIBuilder.fromUri(VK_REST_URL + method);

        preProcessURI(uri);

        // add api version
        // TODO: I think finally we should migrate to latest api
        uri.queryParam("v", apiVersion.toString());

        for (Map.Entry<Object, Object> objectObjectEntry : params.entrySet()) {
            uri.queryParam(objectObjectEntry.getKey().toString(), objectObjectEntry.getValue().toString());
        }
        return uri.build();
    }

    protected void preProcessURI(URIBuilder uri) {
        // add access_token
        // TODO: for some methods we do not need access token, so think about it.
        uri.queryParam("access_token", accessToken);
    }

    // throw exception if VKontakte response contains error
    // TODO: consider to throw specific exceptions for each error code.
    //       like for error code 113 that would be let's say InvalidUserIdVKException
    protected <T extends VKResponse> void checkForError(T toCheck) {
        if(toCheck.getError() != null) {
            throw new VKontakteErrorException(toCheck.getError());
        }
    }

    protected <T> VKArray<T> deserializeArray(VKGenericResponse response, Class<T> itemClass) {
        checkForError(response);

        Assert.isTrue(response.getResponse().isArray());
        ArrayNode items = (ArrayNode) response.getResponse();
        int count = items.get(0).asInt();
        return new VKArray<T>(count, deserializeItems(items, itemClass));
    }

    /**
     * for responses of VK API 5.0+
     * @param response
     * @param itemClass
     * @param <T>
     * @return
     */
    protected <T> VKArray<T> deserializeVK50ItemsResponse(VKGenericResponse response, Class<T> itemClass) {
        checkForError(response);
        JsonNode jsonNode = response.getResponse();
        JsonNode itemsNode = jsonNode.get("items");
        Assert.isTrue(itemsNode.isArray());
        int count = jsonNode.get("count").asInt();
        return new VKArray<T>(count, deserializeItems((ArrayNode)itemsNode, itemClass));
    }

    protected <T> List<T> deserializeItems(ArrayNode items, Class<T> itemClass) {
        List<T> elements = new ArrayList<T>();
        for (int i = 0; i < items.size(); i++) {
            elements.add(objectMapper.convertValue(items.get(i), itemClass));
        }
        return elements;
    }
}

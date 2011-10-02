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

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.VKResponse;
import org.springframework.social.vkontakte.api.VKontakteErrorException;

import java.util.Map;
import java.util.Properties;

/**
 * Generic class for operation templates.
 * @author vkolodrevskiy
 */
class AbstractVKontakteOperations {
    private final static String VK_REST_URL = "https://api.vkontakte.ru/method/";

    private final boolean isAuthorized;
    private final String accessToken;

    public AbstractVKontakteOperations(boolean isAuthorized, String accessToken) {
        this.isAuthorized = isAuthorized;
        this.accessToken = accessToken;
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException();
        }
    }

    protected String makeOperationURL(String method, Properties params) {
        StringBuilder url = new StringBuilder(VK_REST_URL);
        url.append(method).append("?access_token=").append(accessToken);
        for (Map.Entry<Object, Object> objectObjectEntry : params.entrySet()) {
            url.append("&").append(objectObjectEntry.getKey()).append("=").append(objectObjectEntry.getValue());
        }
        return url.toString();
    }

    /* throw exception if VKontakte response contains error */
    // TODO: consider to throw specific exceptions for each error code.
    //       like for error code 113 that would be let's say InvalidUserIdVKException
    protected <T extends VKResponse> void checkForError(T toCheck) {
        if(toCheck.getError() != null) {
            throw new VKontakteErrorException(toCheck.getError());
        }
    }
}

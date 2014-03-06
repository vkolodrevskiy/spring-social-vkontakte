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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.support.URIBuilder;

/**
 * Generic class for secure operation templates.
 * @author bsideup
 */
public class AbstractVKontakteSecureOperations extends AbstractVKontakteOperations {

    protected String clientSecret;

    public AbstractVKontakteSecureOperations(boolean isAuthorized, String accessToken, ObjectMapper objectMapper, String clientSecret) {
        super(isAuthorized, accessToken, objectMapper);
        this.clientSecret = clientSecret;
    }

    @Override
    protected void preProcessURI(URIBuilder uri) {
        super.preProcessURI(uri);

        uri.queryParam("client_secret", clientSecret);
    }
}

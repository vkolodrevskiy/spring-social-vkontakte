/*
 * Copyright 2016 the original author or authors.
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
package org.springframework.social.vkontakte.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * Override {@link AccessGrant} to pass user id.
 *
 * @author vkolodrevskiy
 */
public class VkAccessGrant extends AccessGrant {
    private final Integer userId;

    public VkAccessGrant(String accessToken) {
        this(accessToken, null, null, null, null);
    }

    public VkAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Integer userId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}

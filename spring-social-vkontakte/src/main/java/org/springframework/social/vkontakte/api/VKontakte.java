/*
 * Copyright 2011-2016 the original author or authors.
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
package org.springframework.social.vkontakte.api;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.social.ApiBinding;

/**
 * This is the central place for getting
 * {@link com.vk.api.sdk.client.actors.Actor} instances to interact with VK SDK,
 * also it provides user email if corresponding permission was requested and approved.
 * Implemented by {@link org.springframework.social.vkontakte.api.impl.VKontakteTemplate}.
 *
 * @author vkolodrevskiy
 */
public interface VKontakte extends ApiBinding {
    /**
     * Returns instance of {@code ServiceActor}.
     *
     * @return {@link ServiceActor}
     */
    public ServiceActor getServiceActor();

    /**
     * Returns instance of {@code UserActor}.
     *
     * @return {@link UserActor}
     */
    public UserActor getUserActor();

    /**
     * Returns user email.
     *
     * @return {@link UserActor}
     */
    public String getEmail();
}

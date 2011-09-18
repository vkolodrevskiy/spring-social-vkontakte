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
package org.springframework.social.vkontakte.api.impl.json;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.social.vkontakte.api.*;

/**
 * Jackson module for setting up mixin annotations on VKontakte model types.
 * This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 * @author vkolodrevskiy
 */
public class VKontakteModule extends SimpleModule {

    public VKontakteModule() {
        super("VKontakteModule", new Version(1, 0, 0, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(VKontakteProfile.class, VKontakteProfileMixin.class);
        context.setMixInAnnotations(VKontakteProfiles.class, VKontakteProfilesMixin.class);
        context.setMixInAnnotations(Post.class, PostMixin.class);
        context.setMixInAnnotations(Posts.class, PostsMixin.class);
        context.setMixInAnnotations(VKResponse.class, VKResponseMixin.class);
        context.setMixInAnnotations(org.springframework.social.vkontakte.api.Error.class, ErrorMixin.class);
    }
}

/*
 * Copyright 2013 the original author or authors.
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
package org.springframework.social.vkontakte.config.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

import java.lang.annotation.*;

/**
 * Annotation to enable VKontakte in a Spring Social application.
 * Configures a {@link VKontakteConnectionFactory} bean (and a {@link org.springframework.social.connect.ConnectionFactoryLocator} bean if one isn't already registered).
 * Also configures a request-scoped {@link VKontakte} bean fetched from the current user's {@link org.springframework.social.connect.ConnectionRepository}.
 * @author vkolodrevskiy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(VKontakteProviderConfigRegistrar.class)
public @interface EnableVKontakte {

	/**
	 * The application's consumer key as issued by vkontakte.
	 */
	String appId();
	
	/**
	 * The application's consumer secret as issued by vkontakte.
	 */
	String appSecret();
}

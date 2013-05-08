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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.AbstractProviderConfigRegistrarSupport;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.social.vkontakte.security.VKontakteAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * {@link org.springframework.context.annotation.ImportBeanDefinitionRegistrar} for configuring a {@link VKontakteConnectionFactory} bean and a request-scoped {@link VKontakte} bean.
 * @author Craig Walls
 */
public class VKontakteProviderConfigRegistrar extends AbstractProviderConfigRegistrarSupport {

	public VKontakteProviderConfigRegistrar() {
		super(EnableVKontakte.class, VKontakteConnectionFactory.class, VKontakteApiHelper.class);
	}

	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return VKontakteAuthenticationService.class;
	}
	
	static class VKontakteApiHelper implements ApiHelper<VKontakte> {
		
		private final UsersConnectionRepository usersConnectionRepository;

		private final UserIdSource userIdSource;

		private VKontakteApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
			this.usersConnectionRepository = usersConnectionRepository;
			this.userIdSource = userIdSource;		
		}

		public VKontakte getApi() {
			if (logger.isDebugEnabled()) {
				logger.debug("Getting API binding instance for VKontakte provider");
			}
					
			Connection<VKontakte> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(VKontakte.class);
			if (logger.isDebugEnabled() && connection == null) {
				logger.debug("No current connection; Returning default TwitterTemplate instance.");
			}
			return connection != null ? connection.getApi() : null;
		}
		
		private final static Log logger = LogFactory.getLog(VKontakteApiHelper.class);

	}

}

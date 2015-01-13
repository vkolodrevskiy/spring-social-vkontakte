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
package org.springframework.social.vkontakte.api;

import org.springframework.social.ApiBinding;

/**
 * Interface specifying a basic set of operations for interacting with VKontakte.
 * Implemented by {@link org.springframework.social.vkontakte.api.impl.VKontakteTemplate}.
 * @author vkolodrevskiy
 */
public interface VKontakte extends ApiBinding {

    /**
	 * API for performing operations on VKontakte user profiles.
	 */
    public IUsersOperations usersOperations();

	/**
	 * API for performing operations on feeds.
	 */
    public IWallOperations wallOperations();

	/**
	 * API for performing operations with a user's set of friends.
	 */
    public IFriendsOperations friendsOperations();

    /**
     * API for performing secure operations.
     */
    public ISecureOperations secureOperations();

    /**
     * API for performing operations with a location info.
     */
    public ILocationOperations locationOperations();

    /**
     * API for performing operations with audio.
     */
    public IAudioOperations audioOperations();


    /**
     * API for performing operations with groups.
     */
    public IGroupsOperations groupsOperations();

    /**
     * API for performing operations with newsfeed.
     */
    public INewsFeedOperations newsFeedOperations();
}

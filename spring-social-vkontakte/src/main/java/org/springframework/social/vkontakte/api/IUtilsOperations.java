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
package org.springframework.social.vkontakte.api;

/**
 * Utils operations.
 * @author dIsoVi
 */
public interface IUtilsOperations {
    /**
     * utils.resolveScreenName
     * Detects a type of object (e.g., user, community, application) and its ID by screen name.
     * @param screenName Screen name of the user, community (e.g., apiclub, andrew, or rules_of_war), or application.
     * @return an object with type and id
     */
    VKObject resolveScreenName(String screenName);

    /**
     * A universal method for calling a sequence of other methods while saving and filtering interim results.
     * @param code - Algorithm code in VKScript (https://vk.com/dev/execute)
     * @return data requested by the algorithm
     */
    VKGenericResponse execute(String code);
}

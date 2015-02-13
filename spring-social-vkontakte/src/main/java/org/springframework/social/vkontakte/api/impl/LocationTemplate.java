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
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.City;
import org.springframework.social.vkontakte.api.CityList;
import org.springframework.social.vkontakte.api.ILocationOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Implementation VK geo location API.
 *
 * @see <a href="http://vk.com/dev/places">http://vk.com/dev/places</a>
 * @author badbob
 */
public class LocationTemplate extends AbstractVKontakteOperations implements ILocationOperations {

	private final RestTemplate restTemplate;

	public LocationTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
	        super(isAuthorizedForUser, accessToken, objectMapper);
		this.restTemplate = restTemplate;
	}
	
	public City getCityById(Integer id) {
		List<City> cities = getCityById(Collections.singletonList(id));
		return cities.size() > 0 ? cities.get(0) : null;
	}

    public List<City> getCityById(Collection<Integer> ids) {
        String idsString = buildCidsAsString(ids);
        Properties props = new Properties();

        props.put("city_ids", idsString);
        URI uri = makeOperationURL("database.getCityById", props, ApiVersion.VERSION_5_27);

        CityList cityList = restTemplate.getForObject(uri, CityList.class);
        checkForError(cityList);

        return cityList.getCities();
    }

    // This method compile a coma-separated string from collection of integer city identifier.
    // Actually, this method do the same thing as org.apache.commons.lang.StringUtils.join(ids, ',').
    // But, I decide, that there is to many overheads to include commons-lang lib to use one single method.
	private String buildCidsAsString(Collection<Integer> ids) {
		String delim = "";
		final StringBuilder sb = new StringBuilder();

		for (Integer i : ids) {
		    sb.append(delim).append(i);
		    delim = ",";
		}

		return sb.toString();
	}
}

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

import java.io.Serializable;

/**
 * City.
 *
 * @author badbob
 * @author vkolodrevskiy
 */
public class City implements Serializable {

	private static final long serialVersionUID = 162578751841414977L;

	/**
	 * City identifier in internal Vkontakte format.
	 * It doesn't correlate with KLADR or FIAS databases.
	 */
	private Integer id;
	
	/**
	 * City name in nominative (russian language).
	 */
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

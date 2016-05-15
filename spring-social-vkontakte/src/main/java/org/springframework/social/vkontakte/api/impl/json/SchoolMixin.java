/*
 * Copyright 2011-2015 the original author or authors.
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SchoolMixin {
	@JsonProperty("id")
    private long id;
    @JsonProperty("country")
    private long countryId;
    @JsonProperty("city")
    private long cityId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("year_from")
    private int yearFrom;
    @JsonProperty("year_to")
    private int yearTo;
    @JsonProperty("year_graduated")
    private int yearGraduated;
    @JsonProperty("class")
    private String classLitera;
    @JsonProperty("speciality")
    private String speciality;
    @JsonProperty("type")
    private int type;
    @JsonProperty("type_str")
    private String typeStr;
}

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

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UniversityMixin {
	@JsonProperty("id")
    private long id;
    @JsonProperty("country")
    private long countryId;
    @JsonProperty("city")
    private long cityId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("faculty")
    private long facultyId;
    @JsonProperty("faculty_name")
    private String facultyName;
    @JsonProperty("chair")
    private long chairId;
    @JsonProperty("chair_name")
    private String chairName;
    @JsonProperty("graduation")
    private int graduation;
    @JsonProperty("education_form")
    private String educationForm;
    @JsonProperty("education_status")
    private String educationStatus;
}

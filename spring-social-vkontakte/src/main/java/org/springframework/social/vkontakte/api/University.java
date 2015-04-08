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
package org.springframework.social.vkontakte.api;

/**
 * University.
 *
 * @author dIsoVi
 */
public class University {
    private long id;
    private long countryId;
    private long cityId;
    private String name;
    private long facultyId;
    private String facultyName;
    private long chairId;
    private String chairName;
    private int graduation;
    private String educationForm;
    private String educationStatus;

    public long getId() {
        return id;
    }

    public long getCountryId() {
        return countryId;
    }

    public long getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public long getChairId() {
        return chairId;
    }

    public String getChairName() {
        return chairName;
    }

    public int getGraduation() {
        return graduation;
    }

    public String getEducationForm() {
        return educationForm;
    }

    public String getEducationStatus() {
        return educationStatus;
    }
}

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
 * School.
 *
 * @author dIsoVi
 */
public class School {
    private long id;
    private long countryId;
    private long cityId;
    private String name;
    private int yearFrom;
    private int yearTo;
    private int yearGraduated;
    private String classLitera;
    private String speciality;
    private int type;
    private String typeStr;

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

    public int getYearFrom() {
        return yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public int getYearGraduated() {
        return yearGraduated;
    }

    public String getClassLitera() {
        return classLitera;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getType() {
        return type;
    }

    public String getTypeStr() {
        return typeStr;
    }
}

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
package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.*;

import java.io.IOException;
import java.util.List;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.VKontakteProfile}
 * 
 * @author vkolodrevskiy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class VKontakteProfileMixin {
    @JsonProperty("id")
    private long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("sex")
    @JsonDeserialize(using = VKGenderDeserializer.class)
    private String gender; // maybe use name 'sex' as in vk API?
    @JsonProperty("maiden_name")
    private String maidenName;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("screen_name")
    private String screenName;
    @JsonProperty("bdate")
    @JsonDeserialize(using = VKDateDeserializer.class)
    private VKontakteDate birthDate;
    @JsonProperty("timezone")
    private int timeZone;
    @JsonProperty("photo_50")
    private String photo50;
    @JsonProperty("photo_100")
    private String photo100;
    @JsonProperty("photo_200")
    private String photo200;
    @JsonProperty("photo_max")
    private String photoMax;
    @JsonProperty("photo_200_orig")
    private String photo200Orig;
    @JsonProperty("photo_400_orig")
    private String photo400Orig;
    @JsonProperty("photo_max_orig")
    private String photoMaxOrig;
    @JsonProperty("photo_id")
    private String photoId;
    @JsonProperty("has_mobile")
    private boolean hasMobile;
    @JsonProperty("online")
    private boolean online;
    @JsonProperty("can_post")
    private boolean canPost;
    @JsonProperty("can_see_all_posts")
    private boolean canSeeAllPosts;
    @JsonProperty("can_see_audio")
    private boolean canSeeAudio;
    @JsonProperty("can_write_private_message")
    private boolean canWritePrivateMessage;
    @JsonProperty("mobile_phone")
    private String mobilePhone;
    @JsonProperty("home_phone")
    private String homePhone;
    @JsonProperty("skype")
    private String skype;
    @JsonProperty("facebook")
    private String facebook;
    @JsonProperty("facebook_name")
    private String facebookName;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("site")
    private String site;
    @JsonProperty("status")
    private String status;
    @JsonProperty("common_count")
    private int commonCount;
    @JsonProperty("university")
    private long universityId;
    @JsonProperty("university_name")
    private String universityName;
    @JsonProperty("faculty")
    private long facultyId;
    @JsonProperty("faculty_name")
    private String facultyName;
    @JsonProperty("graduation")
    private int graduation;
    @JsonProperty("education_form")
    private String educationForm;
    @JsonProperty("education_status")
    private String educationStatus;
    @JsonProperty("relation")
    private int relation;
    @JsonProperty("interests")
    private String interests;
    @JsonProperty("music")
    private String music;
    @JsonProperty("activities")
    private String activities;
    @JsonProperty("movies")
    private String movies;
    @JsonProperty("tv")
    private String tv;
    @JsonProperty("books")
    private String books;
    @JsonProperty("games")
    private String games;
    @JsonProperty("about")
    private String about;
    @JsonProperty("quotes")
    private String quotes;
    @JsonProperty("city")
    private City city;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("occupation")
    private Occupation occupation;
    @JsonProperty("personal")
    @JsonDeserialize(using = PersonalDeserializer.class)
    private Personal personal;
    @JsonProperty("universities")
    private List<University> universities;
    @JsonProperty("schools")
    private List<School> schools;
    @JsonProperty("relatives")
    private List<Relative> relatives;
    @JsonProperty("relation_partner")
    private VKontakteProfile relationPartner;

    /**
     * VK Api sometimes returns Personal as empty Array instead of Object (v5.27)
     */
    static class PersonalDeserializer extends JsonDeserializer<Personal>
    {
        @Override
        public Personal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException
        {
            JsonNode node = jp.readValueAsTree();
            if (node.isObject()) {
                return jp.getCodec().treeToValue(node, Personal.class);
            }
            return null;
        }
    }

    static class VKGenderDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            int genderValue = jp.getIntValue();
            if(genderValue == 1) {
                return "W";
            } else if(genderValue == 2){
                return "M";
            }
            return null;
        }
    }

    static class VKDateDeserializer extends JsonDeserializer<VKontakteDate> {
        @Override
        public VKontakteDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            VKontakteDate result = null;
            String dateValue = jp.getText();
            if (dateValue != null) {
                // Step 1. Splitting original data into pieces
                String[] date = dateValue.split("\\.");
                try {
                    // Step 2. Some dates can have only day and month, others might have date month and year
                    if (date.length == 2 || (date.length == 3 && date[2].length() == 0)) {
                        result = new VKontakteDate(Integer.valueOf(date[0]), Integer.valueOf(date[1]), 0);
                    } else if (date.length == 3) {
                        result = new VKontakteDate(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
                    } else {
                        System.err.print("Unrecognizable date " + dateValue + "\n");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }
}

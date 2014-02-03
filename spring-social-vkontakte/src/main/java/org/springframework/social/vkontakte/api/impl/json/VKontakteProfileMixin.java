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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.VKontakteDate;

import java.io.IOException;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.VKontakteProfile}
 * 
 * @author vkolodrevskiy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class VKontakteProfileMixin {
    @JsonCreator
    VKontakteProfileMixin(@JsonProperty("id") String uid, @JsonProperty("screen_name") String screnName,@JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
            @JsonProperty("photo") String photo, @JsonProperty("photo_medium") String photoMedium, @JsonProperty("photo_big") String photoBig,
            @JsonProperty("home_phone") String homePhone, @JsonProperty("mobile_phone") String mobilePhone,
            @JsonProperty("bdate") @JsonDeserialize(using = VKDateDeserializer.class) VKontakteDate birthDate, 
            @JsonProperty("sex") @JsonDeserialize(using = VKGenderDeserializer.class) String gender) {
    }
    
    static class VKGenderDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String genderValue = jp.getText();
            if(genderValue.equals("1")) {
                return "W";
            } else if(genderValue.equals("2")){
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

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
package org.springframework.social.vkontakte.api.impl.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.vkontakte.api.VKontakteDate;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.VKontakteProfile}
 * 
 * @author vkolodrevskiy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class VKontakteProfileMixin {
    @JsonCreator
    VKontakteProfileMixin(@JsonProperty("uid") String uid, @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
            @JsonProperty("photo") String photo, @JsonProperty("photo_medium") String photoMedium, @JsonProperty("photo_big") String photoBig,
            @JsonProperty("home_phone") String homePhone, @JsonProperty("mobile_phone") String mobilePhone,
            @JsonProperty("bdate") @JsonDeserialize(using = VKDateDeserializer.class) VKontakteDate birthDate) {
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

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.MapperConfig.Base;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.deser.StdDeserializer;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.VKontakteProfile}
 * @author vkolodrevskiy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class VKontakteProfileMixin {
    @JsonCreator
    VKontakteProfileMixin(@JsonProperty("uid") String uid,
                          @JsonProperty("first_name") String firstName,
                          @JsonProperty("last_name") String lastName,
                          @JsonProperty("photo") String photo,
                          @JsonProperty("photo_medium") String photoMedium,
                          @JsonProperty("photo_big") String photoBig,
                          @JsonProperty("home_phone") String homePhone,
                          @JsonProperty("mobile_phone") String mobilePhone,
                          @JsonProperty("bdate") @JsonDeserialize(using=VKBirthDateDeserializer.class) Date birthDate) { }
    
    static class VKBirthDateDeserializer extends JsonDeserializer<Date>{
        final static private DateFormat VK_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        
        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String dateValue = jp.getText();
            try {
                return dateValue != null && !"".equals(dateValue) ? VK_DATE_FORMAT.parse(dateValue) : null;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
        
    }
}

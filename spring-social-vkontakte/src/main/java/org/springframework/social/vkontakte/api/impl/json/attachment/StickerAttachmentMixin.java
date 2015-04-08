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
package org.springframework.social.vkontakte.api.impl.json.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StickerAttachmentMixin {

    @JsonProperty("id")
    private long stickerId;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("photo_64")
    private String photo64;

    @JsonProperty("photo_128")
    private String photo128;

    @JsonProperty("photo_256")
    private String photo256;

    @JsonProperty("photo_352")
    private String photo352;

    @JsonProperty("width")
    private long width;

    @JsonProperty("height")
    private long height;
}

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
package org.springframework.social.vkontakte.api.attachment;

/**
 * Attachment associated with an external photo.
 */
public class PhotoAttachment extends Attachment {

    private String photoId;
    private String ownerId;
    private String src;
    private String srcBig;

    public String getPhotoId() {
        return photoId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getSrc() {
        return src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    @Override
    public String toString() {
        return "PhotoAttachment{" +
                "photoId='" + photoId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", src='" + src + '\'' +
                ", srcBig='" + srcBig + '\'' +
                '}';
    }
}

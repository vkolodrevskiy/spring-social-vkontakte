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
 * Audio attachment.
 */
public class AudioAttachment extends Attachment {

    private String id;
    private String ownerId;
    private String performer;
    private String title;
    private int duration;

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "AudioAttachment{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", performer='" + performer + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}

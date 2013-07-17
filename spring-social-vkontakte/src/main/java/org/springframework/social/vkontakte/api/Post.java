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
package org.springframework.social.vkontakte.api;

import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Model class representing an entry in a wall.
 * @author vkolodrevskiy
 */
public class Post {

    private final String id;
    private final Date date;
    private final String text;

    private Likes likes;
    private Reposts reposts;

    private List<Attachment> attachments;

    public Post(String id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public static class Likes {
        private int count;
        private boolean userLikes;
        private boolean canLike;
        private boolean canPublish;

        public int getCount() {
            return count;
        }

        public boolean isUserLikes() {
            return userLikes;
        }

        public boolean isCanLike() {
            return canLike;
        }

        public boolean isCanPublish() {
            return canPublish;
        }

        @Override
        public String toString() {
            return "Likes{" +
                    "count=" + count +
                    ", userLikes=" + userLikes +
                    ", canLike=" + canLike +
                    ", canPublish=" + canPublish +
                    '}';
        }
    }

    public static class Reposts {
        private int count;
        private boolean userReposted;

        public int getCount() {
            return count;
        }

        public boolean isUserReposted() {
            return userReposted;
        }

        @Override
        public String toString() {
            return "Reposts{" +
                    "count=" + count +
                    ", userReposted=" + userReposted +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", reposts=" + reposts +
                ", attachments=" + (attachments == null ? null : StringUtils.collectionToDelimitedString(attachments, ",\n")) +
                '}';
    }
}

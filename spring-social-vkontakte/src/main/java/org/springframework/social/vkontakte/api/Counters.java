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
 * Counters.
 * https://vk.com/dev/fields
 *
 * @author dIsoVi
 */
public class Counters {
    private int albums;
    private int videos;
    private int audios;
    private int notes;
    private int photos;
    private int groups;
    private int gifts;
    private int friends;
    private int onlineFriends;
    private int mutualFriends;
    private int followers;
    private int subscriptions;
    private int pages;

    public int getAlbums() {
        return albums;
    }

    public int getVideos() {
        return videos;
    }

    public int getAudios() {
        return audios;
    }

    public int getNotes() {
        return notes;
    }

    public int getPhotos() {
        return photos;
    }

    public int getGroups() {
        return groups;
    }

    public int getGifts() {
        return gifts;
    }

    public int getFriends() {
        return friends;
    }

    public int getOnlineFriends() {
        return onlineFriends;
    }

    public int getMutualFriends() {
        return mutualFriends;
    }

    public int getFollowers() {
        return followers;
    }

    public int getSubscriptions() {
        return subscriptions;
    }

    public int getPages() {
        return pages;
    }
}

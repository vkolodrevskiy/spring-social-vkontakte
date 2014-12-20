package org.springframework.social.vkontakte.api;

import java.util.List;

public class NewsFeedResponse {
    List<Post> news;
    String nextFrom;
    long count;
    long totalCount;
    // TODO: add support for extended response
    List<Group> groups;
    List<VKontakteProfile> profiles;

    public NewsFeedResponse(List<Post> news, String nextFrom, long count, long totalCount, List<Group> groups, List<VKontakteProfile> profiles) {
        this.news = news;
        this.nextFrom = nextFrom;
        this.count = count;
        this.totalCount = totalCount;
        this.groups = groups;
        this.profiles = profiles;
    }
    public NewsFeedResponse(List<Post> news, String nextFrom, long count, long totalCount) {
        this.news = news;
        this.nextFrom = nextFrom;
        this.count = count;
        this.totalCount = totalCount;
    }

    public List<Post> getNews() {
        return news;
    }

    public String getNextFrom() {
        return nextFrom;
    }

    public long getCount() {
        return count;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<VKontakteProfile> getProfiles() {
        return profiles;
    }
}

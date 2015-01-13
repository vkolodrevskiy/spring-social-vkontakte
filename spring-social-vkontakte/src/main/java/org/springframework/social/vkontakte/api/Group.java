package org.springframework.social.vkontakte.api;

import java.util.Date;

public class Group {
    String description;
    String wikiPage;
    long membersCount;
    Date startDate;
    Date finishDate;
    String status;
    String contacts;
    String links;
    String site;
    private long groupId;
    private String groupName;
    private String screenName;
    private boolean isClosed;
    private String deactivated;
    private boolean isMember;
    private String type;
    private String photo50;
    private String photo100;
    private String photo200;
    private long cityId;
    private long countryId;
    private Place place;

    public Group(long id) {
        groupId = id;
    }

    public Group() {

    }

    public String getScreenName() {
        return screenName;
    }

    public String getDeactivated() {
        return deactivated;
    }

    public long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public boolean isMember() {
        return isMember;
    }

    public String getType() {
        return type;
    }

    public String getPhoto50() {
        return photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public String getPhoto200() {
        return photo200;
    }

    public long getCityId() {
        return cityId;
    }

    public long getCountryId() {
        return countryId;
    }

    public Place getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public String getWikiPage() {
        return wikiPage;
    }

    public long getMembersCount() {
        return membersCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public String getStatus() {
        return status;
    }

    public String getContacts() {
        return contacts;
    }

    public String getLinks() {
        return links;
    }

    public String getSite() {
        return site;
    }
}

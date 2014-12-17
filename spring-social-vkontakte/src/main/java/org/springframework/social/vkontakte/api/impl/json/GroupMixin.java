package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.Place;
import org.springframework.social.vkontakte.api.impl.json.deserializers.UnixTimeDeserializer;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GroupMixin {
    @JsonCreator
    public GroupMixin(long id) {
        groupId = id;
    }

    @JsonProperty("id")
    private long groupId;

    @JsonProperty("name")
    private String groupName;

    @JsonProperty("screen_name")
    private String screenName;

    @JsonProperty("is_closed")
    private boolean isClosed;

    @JsonProperty("deactivated")
    private String deactivated;

    @JsonProperty("is_member")
    private boolean isMember;

    @JsonProperty("type")
    private String type;

    @JsonProperty("photo_50")
    private String photo50;

    @JsonProperty("photo_100")
    private String photo100;

    @JsonProperty("photo_200")
    private String photo200;

    @JsonProperty("city")
    private long cityId;

    @JsonProperty("country")
    private long countryId;

    @JsonProperty("place")
    private Place place;

    @JsonProperty("description")
    String description;

    @JsonProperty("wiki_page")
    String wikiPage;

    @JsonProperty("members_count")
    long membersCount;

    @JsonProperty("start_date")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    Date startDate;

    @JsonProperty("finish_date")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    Date finishDate;

    @JsonProperty("status")
    String status;

    @JsonProperty("contacts")
    String contacts;

    @JsonProperty("links")
    String links;

    @JsonProperty("site")
    String site;
}

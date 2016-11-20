package org.springframework.social.vkontakte.api;

import java.util.Properties;

/**
 * An object that represents a new post to be created.
 * Offers a builder-like way of creating a new post.
 * Given to {@link org.springframework.social.vkontakte.api.IWallOperations#post(PostData)}.
 *
 * @author Nikolay Papakha
 */
public class PostData {

    private String ownerId;

    private Boolean friendsOnly;

    private Boolean fromGroup;

    private String message;

    private String attachments;

    private String services;

    private Boolean signed;

    private Integer publishDate;

    private Double lat;

    private Double lon;

    private Integer placeId;

    private Integer postId;

    private String guid;

    private Boolean markAsAds;

    public PostData(String ownerId, String message) {
        this.ownerId = ownerId;
        this.message = message;
    }

    public PostData(String ownerId) {
        this.ownerId = ownerId;
    }

    public Properties toProperties() {
        Properties properties = new Properties();
        properties.put("owner_id", ownerId);
        if (friendsOnly != null) {
            properties.put("friends_only", friendsOnly ? "1" : "0");
        }
        if (fromGroup != null) {
            properties.put("from_group", fromGroup ? "1" : "0");
        }
        if (message != null) {
            properties.put("message", message);
        }
        if (attachments != null) {
            properties.put("attachments", attachments);
        }
        if (services != null) {
            properties.put("services", services);
        }
        if (signed != null) {
            properties.put("signed", signed);
        }
        if (publishDate != null) {
            properties.put("publishDate", publishDate);
        }
        if (lat != null) {
            properties.put("lat", lat);
        }
        if (lon != null) {
            properties.put("lon", lon);
        }
        if (placeId != null) {
            properties.put("placeId", placeId);
        }
        if (postId != null) {
            properties.put("postId", postId);
        }
        if (guid != null) {
            properties.put("guid", guid);
        }
        if (markAsAds != null) {
            properties.put("mark_as_ads", markAsAds ? "1" : "0");
        }
        return properties;
    }

    /**
     * @param message A message for the post.
     * @return the PostData object for additional configuration
     */
    public PostData message(String message) {
        this.message = message;
        return this;
    }

    /**
     * @param friendsOnly true if post should be available for friends only.
     * @return the PostData object for additional configuration
     */
    public PostData friendsOnly(Boolean friendsOnly) {
        this.friendsOnly = friendsOnly;
        return this;
    }

    /**
     * @param fromGroup true if post should be posted on behalf of group.
     * @return the PostData object for additional configuration
     */
    public PostData fromGroup(Boolean fromGroup) {
        this.fromGroup = fromGroup;
        return this;
    }

    /**
     * @param attachments Comma separated list of attachments.
     * @return the PostData object for additional configuration
     */
    public PostData attachments(String attachments) {
        this.attachments = attachments;
        return this;
    }

    /**
     * @param services Comma separated list of services (i.e facebook, twitter) for post export.
     * @return the PostData object for additional configuration
     */
    public PostData services(String services) {
        this.services = services;
        return this;
    }

    /**
     * @param signed true if post on behalf of group should be signed.
     * @return the PostData object for additional configuration
     */
    public PostData signed(Boolean signed) {
        this.signed = signed;
        return this;
    }

    /**
     * @param publishDate Publication date (in Unix time). If used, posting will be delayed until the set time.
     * @return the PostData object for additional configuration
     */
    public PostData publishDate(Integer publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    /**
     * @param lat Geographical longitude of a check-in, in degrees (from -180 to 180).
     * @return the PostData object for additional configuration
     */
    public PostData lat(Double lat) {
        this.lat = lat;
        return this;
    }

    /**
     * @param lon Geographical longitude of a check-in, in degrees (from -180 to 180). .
     * @return the PostData object for additional configuration
     */
    public PostData lon(Double lon) {
        this.lon = lon;
        return this;
    }

    /**
     * @param placeId ID of the location where the user was tagged.
     * @return the PostData object for additional configuration
     */
    public PostData placeId(Integer placeId) {
        this.placeId = placeId;
        return this;
    }

    /**
     * @param postId Post ID. Used for publishing of scheduled and suggested posts. .
     * @return the PostData object for additional configuration
     */
    public PostData postId(Integer postId) {
        this.postId = postId;
        return this;
    }

    /**
     * @param guid string
     * @return the PostData object for additional configuration
     */
    public PostData guid(String guid) {
        this.guid = guid;
        return this;
    }

    /**
     * @param markAsAds, either true or false, default false.
     * @return the PostData object for additional configuration
     */
    public PostData guid(Boolean markAsAds) {
        this.markAsAds = markAsAds;
        return this;
    }
}

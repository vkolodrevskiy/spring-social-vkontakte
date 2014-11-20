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

}

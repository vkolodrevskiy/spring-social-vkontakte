package org.springframework.social.vkontakte.api;

import java.util.Collection;
import java.util.List;

/**
 * Defines operations for interacting with a user's groups.
 * @author dIsoVi
 */
public interface IGroupsOperations {
    List<Group> getGroups();

    List<Group> getGroups(long offset, long count);

    List<Group> getGroups(long userId);

    List<Group> getGroups(long userId, long offset, long count);

    List<Group> getGroups(long userId, String filter, long offset, long count, boolean extended);

    /**
     * Returns information about communities by their IDs
     * @param groupIds IDs or screen names of communities.
     * @return list of {@link Group} by IDs.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    List<Group> getByIds(Collection<String> groupIds);

    /**
     * Returns a list of community members. Max users returned by this method - 1000.
     * Please, use {@link #getMembers(String, String, int, int)}, when there are more members in a group.
     * @param groupId ID or screen name of the community.
     * @return list of members.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    List<VKontakteProfile> getMembers(String groupId);

    /**
     * Returns a list of community members.
     * @param groupId ID or screen name of the community.
     * @param fields VKontakte fields to retrieve, comma-delimited.
     * @return list of members.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    List<VKontakteProfile> getMembers(String groupId, String fields);

    /**
     * Returns a list of community members.
     * @param groupId ID or screen name of the community.
     * @param fields VKontakte user fields to retrieve, comma-delimited.
     * @param count Number(positive number) of members to return. Maximum value 1000.
     * @param offset Offset needed to return a specific subset of community members. (positive number)
     * @return list of members.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    List<VKontakteProfile> getMembers(String groupId, String fields, int count, int offset);

}

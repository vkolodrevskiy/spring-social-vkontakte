package org.springframework.social.vkontakte.api;

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
}

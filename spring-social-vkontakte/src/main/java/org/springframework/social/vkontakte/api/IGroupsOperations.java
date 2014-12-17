package org.springframework.social.vkontakte.api;

import java.util.List;

public interface IGroupsOperations {
    List<Group> getGroups();

    List<Group> getGroups(long offset, long count);

    List<Group> getGroups(long userId);

    List<Group> getGroups(long userId, long offset, long count);

    List<Group> getGroups(long userId, String filter, long offset, long count, boolean extended);
}

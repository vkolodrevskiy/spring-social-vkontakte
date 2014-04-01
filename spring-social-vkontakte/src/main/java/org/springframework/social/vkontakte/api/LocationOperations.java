package org.springframework.social.vkontakte.api;

import java.util.Collection;
import java.util.List;

public interface LocationOperations {

	List<City> getCityById(Collection<Integer> ids);

	City getCityById(Integer id);
}

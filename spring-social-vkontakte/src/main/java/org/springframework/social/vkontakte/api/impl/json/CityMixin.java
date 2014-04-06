
package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
abstract class CityMixin extends VKResponseMixin {

	@JsonProperty("cid")
	Integer id;

	@JsonProperty("name")
	String name;
}

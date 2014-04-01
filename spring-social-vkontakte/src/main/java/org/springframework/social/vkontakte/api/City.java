package org.springframework.social.vkontakte.api;

import java.io.Serializable;

public class City implements Serializable {

	private static final long serialVersionUID = 162578751841414977L;

	/**
	 * City identifier in internal Vkontakte format.
	 * It doesn't correlate with KLADR or FIAS databases.
	 */
	private Integer id;
	
	/**
	 * City name in nominative (russian language).
	 */
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

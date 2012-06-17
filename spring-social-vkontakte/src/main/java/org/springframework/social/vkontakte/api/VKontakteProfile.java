/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.vkontakte.api;


/**
 * Model class containing a VKontakte user's profile information.
 * @author vkolodrevskiy
 */
public class VKontakteProfile {

    private final String uid;
    private final String firstName;
    private final String lastName;
    private final String photo;
    private final String photoMedium;
    private final String photoBig;
    private final String homePhone;
    private final String mobilePhone;
    private final VKontakteDate birthDate;

    public VKontakteProfile(String uid, String firstName, String lastName,
                            String photo, String photoMedium, String photoBig, String homePhone, String mobilePhone, VKontakteDate birthDate) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.photoMedium = photoMedium;
        this.photoBig = photoBig;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.birthDate = birthDate;
    }

    /**
	 * The user's VKontskte ID
	 * @return The user's VKontakte ID
	 */
    public String getUid() {
        return uid;
    }

    /**
	 * The user's first name
	 * @return The user's first name
	 */
    public String getFirstName() {
        return firstName;
    }

	/**
	 * The user's last name
	 * @return The user's last name
	 */
    public String getLastName() {
        return lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhotoMedium() {
        return photoMedium;
    }

    public String getPhotoBig() {
        return photoBig;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public VKontakteDate getBirthDate() {
        return birthDate;
    }
}

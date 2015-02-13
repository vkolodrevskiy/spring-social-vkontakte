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

import java.util.List;

/**
 * Model class containing a VKontakte user's profile information.
 *
 * @author vkolodrevskiy
 */
public class VKontakteProfile {

    private final static String PROFILE_URL_EXTESTION = "http://vk.com/";
    private long id;
    private String firstName;
    private String lastName;
    private String gender; // maybe use name 'sex' as in vk API?
    private String maidenName;
    private String domain;
    private String screenName;
    private VKontakteDate birthDate;
    private int timeZone;
    private String photo50;
    private String photo100;
    private String photo200;
    private String photoMax;
    private String photo200Orig;
    private String photo400Orig;
    private String photoMaxOrig;
    private String photoId;
    private boolean hasMobile;
    private boolean online;
    private boolean canPost;
    private boolean canSeeAllPosts;
    private boolean canSeeAudio;
    private boolean canWritePrivateMessage;
    private String mobilePhone;
    private String homePhone;
    private String skype;
    private String facebook;
    private String facebookName;
    private String twitter;
    private String site;
    private String status;
    private int commonCount;
    private long universityId;
    private String universityName;
    private long facultyId;
    private String facultyName;
    private int graduation;
    private String educationForm;
    private String educationStatus;
    private int relation;
    private String interests;
    private String music;
    private String activities;
    private String movies;
    private String tv;
    private String books;
    private String games;
    private String about;
    private String quotes;
    private City city;
    private Country country;
    private Occupation occupation;
    private Personal personal;
    private List<University> universities;
    private List<School> schools;
    private List<Relative> relatives;
    private VKontakteProfile relationPartner;

    public VKontakteProfile getRelationPartner() {
        return relationPartner;
    }

    public void setRelationPartner(VKontakteProfile relationPartner) {
        this.relationPartner = relationPartner;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public VKontakteDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(VKontakteDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public String getPhoto200() {
        return photo200;
    }

    public void setPhoto200(String photo200) {
        this.photo200 = photo200;
    }

    public String getPhotoMax() {
        return photoMax;
    }

    public void setPhotoMax(String photoMax) {
        this.photoMax = photoMax;
    }

    public String getPhoto200Orig() {
        return photo200Orig;
    }

    public void setPhoto200Orig(String photo200Orig) {
        this.photo200Orig = photo200Orig;
    }

    public String getPhoto400Orig() {
        return photo400Orig;
    }

    public void setPhoto400Orig(String photo400Orig) {
        this.photo400Orig = photo400Orig;
    }

    public String getPhotoMaxOrig() {
        return photoMaxOrig;
    }

    public void setPhotoMaxOrig(String photoMaxOrig) {
        this.photoMaxOrig = photoMaxOrig;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public boolean isHasMobile() {
        return hasMobile;
    }

    public void setHasMobile(boolean hasMobile) {
        this.hasMobile = hasMobile;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isCanPost() {
        return canPost;
    }

    public void setCanPost(boolean canPost) {
        this.canPost = canPost;
    }

    public boolean isCanSeeAllPosts() {
        return canSeeAllPosts;
    }
    // TODO: counters

    public void setCanSeeAllPosts(boolean canSeeAllPosts) {
        this.canSeeAllPosts = canSeeAllPosts;
    }

    public boolean isCanSeeAudio() {
        return canSeeAudio;
    }

    public void setCanSeeAudio(boolean canSeeAudio) {
        this.canSeeAudio = canSeeAudio;
    }

    public boolean isCanWritePrivateMessage() {
        return canWritePrivateMessage;
    }

    public void setCanWritePrivateMessage(boolean canWritePrivateMessage) {
        this.canWritePrivateMessage = canWritePrivateMessage;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCommonCount() {
        return commonCount;
    }

    public void setCommonCount(int commonCount) {
        this.commonCount = commonCount;
    }

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getGraduation() {
        return graduation;
    }

    public void setGraduation(int graduation) {
        this.graduation = graduation;
    }

    public String getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(String educationForm) {
        this.educationForm = educationForm;
    }

    public String getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getProfileURL() {
        return PROFILE_URL_EXTESTION + screenName;
    }


}

package com.ericski.api500px;

import java.util.Map;

public class User 
{
	
	int id;
	String username;
	String firstname;
	String lastname;
	String fullname;
	String userpic_url;
	String sex; // Sex of the user. Values: 1 and 2 for male and female respectively, 0 if user refused to specify their sex.
	String city;
	String state;
	String country;
	String registration_date;
	String about;
	int upgrade_status; // Whether the user is a premium user, integer. Non-zero values identify premium users.
	String domain;
	boolean fotomoto_on;
	String locale; //User's preferred locale, . Current values: 'en', 'ru', 'de', 'br'.
	boolean show_nude;
	int friends_count;
	int followers_count;
	int photos_count;
	int in_favorites_count;
	int affection;
	Map<String, String> contacts; //A dictionary of user's contacts, object. Keys should be treated as provider names, and values as user IDs with given provider.	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	protected void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	protected void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	protected void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFullname() {
		return fullname;
	}
	protected void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUserpic_url() {
		return userpic_url;
	}
	protected void setUserpic_url(String userpic_url) {
		this.userpic_url = userpic_url;
	}
	public String getSex() {
		return sex;
	}
	protected void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	protected void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	protected void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	protected void setCountry(String country) {
		this.country = country;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	protected void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public String getAbout() {
		return about;
	}
	protected void setAbout(String about) {
		this.about = about;
	}
	public int getUpgrade_status() {
		return upgrade_status;
	}
	protected void setUpgrade_status(int upgrade_status) {
		this.upgrade_status = upgrade_status;
	}
	public String getDomain() {
		return domain;
	}
	protected void setDomain(String domain) {
		this.domain = domain;
	}
	public boolean isFotomoto_on() {
		return fotomoto_on;
	}
	protected void setFotomoto_on(boolean fotomoto_on) {
		this.fotomoto_on = fotomoto_on;
	}
	public String getLocale() {
		return locale;
	}
	protected void setLocale(String locale) {
		this.locale = locale;
	}
	public boolean isShow_nude() {
		return show_nude;
	}
	protected void setShow_nude(boolean show_nude) {
		this.show_nude = show_nude;
	}
	public int getFriends_count() {
		return friends_count;
	}
	protected void setFriends_count(int friends_count) {
		this.friends_count = friends_count;
	}
	public int getFollowers_count() {
		return followers_count;
	}
	protected void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}
	public int getPhotos_count() {
		return photos_count;
	}
	protected void setPhotos_count(int photos_count) {
		this.photos_count = photos_count;
	}
	public int getIn_favorites_count() {
		return in_favorites_count;
	}
	protected void setIn_favorites_count(int in_favorites_count) {
		this.in_favorites_count = in_favorites_count;
	}
	public int getAffection() {
		return affection;
	}
	protected void setAffection(int affection) {
		this.affection = affection;
	}
	public Map<String, String> getContacts() {
		return contacts;
	}
	protected void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}

}

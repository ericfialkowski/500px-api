package com.ericski.api500px;

import java.util.ArrayList;

public class Filters {
	
	Integer category;
	Integer user_id;
	Integer exlcude;
	
	ArrayList<Integer> friends_ids;
	
	public Integer getCategory() {
		return category;
	}
	protected void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getUser_id() {
		return user_id;
	}
	protected void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public ArrayList<Integer> getFriends_ids() {
		return friends_ids;
	}
	protected void setFriends_ids(ArrayList<Integer> friends_ids) {
		this.friends_ids = friends_ids;
	}
	public Integer getExlcude()
	{
		return exlcude;
	}
	public void setExlcude(Integer exlcude)
	{
		this.exlcude = exlcude;
	}	
}
package com.ericski.api500px;

public class Comment
{
	String body;
	String created_at; // "2011-07-20T13:59:19Z"
	int id;
	int to_whom_user_id;
	User user; // user who made the comment
	int user_id; // user id who made the comment

	public String getBody()
	{
		return body;
	}

	protected void setBody(String body)
	{
		this.body = body;
	}

	public String getCreated_at()
	{
		return created_at;
	}

	protected void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}

	public int getId()
	{
		return id;
	}

	protected void setId(int id)
	{
		this.id = id;
	}

	public int getTo_whom_user_id()
	{
		return to_whom_user_id;
	}

	protected void setTo_whom_user_id(int to_whom_user_id)
	{
		this.to_whom_user_id = to_whom_user_id;
	}

	public User getUser()
	{
		return user;
	}

	protected void setUser(User user)
	{
		this.user = user;
	}

	public int getUser_id()
	{
		return user_id;
	}

	protected void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

}

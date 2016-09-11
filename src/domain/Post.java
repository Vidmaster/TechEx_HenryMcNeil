package domain;

import java.util.Date;

public class Post {
	private int id;
	private int userId;
	private String userName;
	private String subject;
	private String body;
	private Date posted;
	private Date updated;
	private int numComments;
	
	public Post() {
		
	}
	
	public Post(int id, int userId, String userName, String subject, String body, Date posted, Date updated,
			int numComments) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.subject = subject;
		this.body = body;
		this.posted = posted;
		this.updated = updated;
		this.numComments = numComments;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public Date getPosted() {
		return posted;
	}
	
	public void setPosted(Date posted) {
		this.posted = posted;
	}
	
	public Date getUpdated() {
		return updated;
	}
	
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	public int getNumComments() {
		return numComments;
	}
	
	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", userName=" + userName + ", subject=" + subject + ", body="
				+ body + ", posted=" + posted + ", updated=" + updated + ", numComments=" + numComments + "]";
	}
	
}

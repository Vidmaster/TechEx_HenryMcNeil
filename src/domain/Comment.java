package domain;

import java.util.Date;

public class Comment {
	private int id;
	private int postId;
	private String authorName;
	private String subject;
	private String text;
	private Date posted;
	  
	public Comment() {
		
	}
	
	public Comment(int id, int postId, String authorName, String subject, String text, Date posted) {
			super();
			this.id = id;
			this.postId = postId;
			this.authorName = authorName;
			this.subject = subject;
			this.text = text;
			this.posted = posted;
	}
	  
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPostId() {
		return postId;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getPosted() {
		return posted;
	}
	
	public void setPosted(Date posted) {
		this.posted = posted;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", postId=" + postId + ", authorName=" + authorName + ", subject=" + subject
				+ ", text=" + text + ", posted=" + posted + "]";
	}
	  
	  
}

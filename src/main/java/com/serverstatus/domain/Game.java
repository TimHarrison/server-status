package com.serverstatus.domain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.userdetails.User;

public class Game {
	
	@Id
	private String id;
//	private long publicId;
	@CreatedBy
	private User createdBy;
	@LastModifiedBy
	private User lastModifiedBy;
	@CreatedDate
	private Date createDate;
	@LastModifiedDate
	private Date updateDate;
	
	private String title;
	private String description;
	
	public Game() {}
	
	public Game(String title, String description) {
//		this.publicId = publicId;
		this.title = title;
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Game[id=%s, title='%s', description='%s'", id, title, description);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
//	public long getPublicId() {
//		return publicId;
//	}
//
//	public void setPublicId(int publicId) {
//		this.publicId = publicId;
//	}
	
}

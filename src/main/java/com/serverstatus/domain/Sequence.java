package com.serverstatus.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Sequence {
	
	@Id
	private String id;
	@CreatedDate
	private Date createDate;
	@LastModifiedDate
	private Date updateDate;
	@DBRef
	private Game game;
	
	private int publicId;
	private StatusType taskType;
	
	public Sequence() {}
	
	public Sequence(StatusType tasktype, Game game) {
		this.taskType = tasktype;
		this.game = game;
	}

	public Game getOrganization() {
		return game;
	}

	public void setOrganization(Game game) {
		this.game = game;
	}

	public int getPublicId() {
		return publicId;
	}

	public void setPublicId(int publicId) {
		this.publicId = publicId;
	}

	public StatusType getTaskType() {
		return taskType;
	}

	public void setTaskType(StatusType taskType) {
		this.taskType = taskType;
	}

}

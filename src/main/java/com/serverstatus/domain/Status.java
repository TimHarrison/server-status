package com.serverstatus.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Status {
	
	@Id
	private String id;
	@CreatedDate
	private Date createDate;
	@DBRef
	private Game game;
	private StatusType status;
	
	public Status() {}
	
	public Status(StatusType status, Game game) {
		this.status = status;
		this.game = game;
	}
	
	@Override
	public String toString() {
		return String.format("Status[status='%s', game='%s', created='%s'", status, game.getTitle(), createDate);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}

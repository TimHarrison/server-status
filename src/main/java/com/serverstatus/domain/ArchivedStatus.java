package com.serverstatus.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Repository;

@Repository
public class ArchivedStatus {
	
	@Id
	private String id;
	@CreatedDate
	private Date createDate;
	@DBRef
	private Game game;
	private CurrentStatusType currentStatus;
	private float upPercent;
	
	public ArchivedStatus() {}
	
	public ArchivedStatus(Game game) {
		this.game = game;
	}
	
	public ArchivedStatus(CurrentStatusType currentStatus, Game game, float upPercent) {
		this.currentStatus = currentStatus;
		this.game = game;
		this.upPercent = upPercent;
	}
	
	@Override
	public String toString() {
		return String.format("Status[currentStatus='%s', game='%s', upPercent='%s'", currentStatus, game.getTitle(), upPercent);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public CurrentStatusType getStatus() {
		return currentStatus;
	}

	public void setStatus(CurrentStatusType currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public float getUpPercent() {
		return upPercent;
	}

	public void setUpPercent(float upPercent) {
		this.upPercent = upPercent;
	}
	
}

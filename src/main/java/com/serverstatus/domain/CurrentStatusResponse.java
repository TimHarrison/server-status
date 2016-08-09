package com.serverstatus.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class CurrentStatusResponse {

	@DBRef
	private Game game;
	private CurrentStatusType status;
	private float percentageUp;
	
	public CurrentStatusResponse() {}
	
	public CurrentStatusResponse(Game game) {
		this.game = game;
	}
	
	public CurrentStatusResponse(CurrentStatusType status, Game game) {
		this.status = status;
		this.game = game;
	}
	
	public CurrentStatusResponse(CurrentStatusType status, Game game, float percentageUp) {
		this.status = status;
		this.game = game;
		this.percentageUp = percentageUp;
	}
	
	@Override
	public String toString() {
		return String.format("CurrentStatus[status='%s', game='%s', percentageUp='%s'", status, game.getTitle(), percentageUp);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public CurrentStatusType getStatus() {
		return status;
	}

	public void setStatus(CurrentStatusType status) {
		this.status = status;
	}

	public float getPercentageUp() {
		return percentageUp;
	}

	public void setPercentageUp(float percentageUp) {
		this.percentageUp = percentageUp;
	}
	
}

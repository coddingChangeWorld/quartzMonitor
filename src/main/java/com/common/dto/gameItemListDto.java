package com.common.dto;

import java.sql.Timestamp;
import java.util.Date;

public class gameItemListDto {
	private Integer id;
	private String arenaName;
	private String awayTeamId;
	private String awayName;
	private String awayAbbr;
	private String gameId;
	private String homeTeamId;
	private String homeName;
	private String homeAbbr;
	private Integer awayScore;
	private Integer homeScore;
	private Date gameTime;
	private Date updateTime;
	private String status;
	private Integer ruleCount;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArenaName() {
		return this.arenaName;
	}

	public void setArenaName(String arenaName) {
		this.arenaName = arenaName;
	}

	public String getAwayTeamId() {
		return this.awayTeamId;
	}

	public void setAwayTeamId(String awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public String getAwayName() {
		return this.awayName;
	}

	public void setAwayName(String awayName) {
		this.awayName = awayName;
	}

	public String getGameId() {
		return this.gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getHomeTeamId() {
		return this.homeTeamId;
	}

	public void setHomeTeamId(String homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public String getHomeName() {
		return this.homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public Integer getAwayScore() {
		return this.awayScore;
	}

	public void setAwayScore(Integer awayScore) {
		this.awayScore = awayScore;
	}

	public Integer getHomeScore() {
		return this.homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Date getGameTime() {
		return this.gameTime;
	}

	public void setGameTime(Timestamp gameTime) {
		this.gameTime = new Date(gameTime.getTime());
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRuleCount() {
		return this.ruleCount;
	}

	public void setRuleCount(Integer ruleCount) {
		this.ruleCount = ruleCount;
	}

	public String getAwayAbbr() {
		return this.awayAbbr;
	}

	public void setAwayAbbr(String awayAbbr) {
		this.awayAbbr = awayAbbr;
	}

	public String getHomeAbbr() {
		return this.homeAbbr;
	}

	public void setHomeAbbr(String homeAbbr) {
		this.homeAbbr = homeAbbr;
	}
}

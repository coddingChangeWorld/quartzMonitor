package com.common.dto;

import com.common.tool.JacksonJson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.service.sport.bet.json.ruleJson;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class counterUserOrderDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String profileId;
	private Integer homeScore;
	private Integer awayScore;
	private String profileStatus;
	private String gameId;
	private String publishUserId;
	private String matchRuleId;
	private String strategy;
	private String json;
	private String betOrderId;
	private String bet;
	private Double num;
	private Double amount;
	private String userId;
	private ruleJson rulejson;

	public void counter() {
		if ("winorlose".equals(this.strategy)) {
			int tempHomeScore = this.homeScore.intValue() + this.rulejson.getAas_score();
			if ("win".equals(this.bet)) {
				this.amount = Double.valueOf(tempHomeScore > this.awayScore.intValue() ? this.rulejson.getWin_odds().doubleValue() * this.num.doubleValue() : -this.num.doubleValue());
			} else if ("lose".equals(this.bet)) {
				this.amount = Double.valueOf(tempHomeScore < this.awayScore.intValue() ? this.rulejson.getWin_odds().doubleValue() * this.num.doubleValue() : -this.num.doubleValue());
			}
		} else if ("bigorsmall".equals(this.strategy)) {
			int totleScore = this.homeScore.intValue() + this.awayScore.intValue();
			if ("big".equals(this.bet)) {
				this.amount = Double.valueOf(totleScore > this.rulejson.getBase_score() ? this.rulejson.getBig_odds().doubleValue() * this.num.doubleValue() : -this.num.doubleValue());
			} else if ("small".equals(this.bet)) {
				this.amount = Double.valueOf(totleScore < this.rulejson.getBase_score() ? this.rulejson.getSmall_odds().doubleValue() * this.num.doubleValue() : -this.num.doubleValue());
			}
		}
	}

	public String getProfileId() {
		return this.profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public Integer getHomeScore() {
		return this.homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Integer getAwayScore() {
		return this.awayScore;
	}

	public void setAwayScore(Integer awayScore) {
		this.awayScore = awayScore;
	}

	public String getProfileStatus() {
		return this.profileStatus;
	}

	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}

	public String getGameId() {
		return this.gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPublishUserId() {
		return this.publishUserId;
	}

	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}

	public String getMatchRuleId() {
		return this.matchRuleId;
	}

	public void setMatchRuleId(String matchRuleId) {
		this.matchRuleId = matchRuleId;
	}

	public String getStrategy() {
		return this.strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getJson() {
		return this.json;
	}

	public void setJson(String json) {
		if (!StringUtils.isEmpty(json)) {
			this.rulejson = ((ruleJson) JacksonJson.fromJson(json, ruleJson.class));
		}
		this.json = json;
	}

	public String getBetOrderId() {
		return this.betOrderId;
	}

	public void setBetOrderId(String betOrderId) {
		this.betOrderId = betOrderId;
	}

	public String getBet() {
		return this.bet;
	}

	public void setBet(String bet) {
		this.bet = bet;
	}

	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

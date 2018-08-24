package com.data.collect.service;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;

import com.data.collect.model.AppProfile;
import com.data.collect.model.Team;

public class ResultCollectService extends AbstractCollectService {
	
	private AppProfile profile;

	public ResultCollectService(String url) {
		this.url = url;
	}

	public void parseJson(String url, String gameId) {
		String pathUrl = StringUtils.isEmpty(url) ? this.url : url;
		if (StringUtils.isEmpty(pathUrl)) {
			this.logger.info("获取json数据的url不能为空");
		}
		try {
			String data = new String(doGet());
			this.logger.info("数据路径"+pathUrl);
			this.logger.info(data);
			JsonNode jsonNode = mapper.readTree(data);
			System.out.println(jsonNode.findValue("timestamp").getTextValue());
			JsonNode games = jsonNode.get("payload").get("date").get("games");
			System.out.println(games.isArray());
			System.out.println(mapper.writeValueAsString(games));
			System.out.println(games.size());
			System.out.println("时间\t\t\t\t\t对阵(客@主)\t\t\t比分\t\t\t地点\t\t\t转播\t\t\t链接");
			for (int i = 0; i < games.size(); i++) {
				JsonNode gameObj = games.get(i);

				AppProfile profiledm = new AppProfile();

				JsonNode profile = gameObj.get("profile");
				//long ot = Long.valueOf(profile.get("utcMillis").getTextValue()).longValue();

				String jsGameId = profile.get("gameId").getTextValue();
				if (jsGameId.equals(gameId)) {
					//String utc = DateFormatUtils.formatUTC(ot, DateFormatUtils.ISO_DATETIME_FORMAT.getPattern());
					profiledm.setGameId(gameId);
					JsonNode homeTeam = gameObj.get("homeTeam").get("profile");
					JsonNode awayTeam = gameObj.get("awayTeam").get("profile");
					StringBuffer column2 = new StringBuffer();
					if (awayTeam != null) {
						Team awayTeamBean = (Team) mapper.readValue(awayTeam, Team.class);
						profiledm.setAwayTeamId(awayTeamBean.getId());
						profiledm.setAwayName(awayTeamBean.getName());
						column2.append(awayTeamBean.getName());
						column2.append("@");
					}
					if (homeTeam != null) {
						Team homeTeamBean = (Team) mapper.readValue(homeTeam, Team.class);
						profiledm.setHomeTeamId(homeTeamBean.getId());
						profiledm.setHomeName(homeTeamBean.getName());
						column2.append(homeTeamBean.getName());
						column2.append("\t\t\t");
					}
					if (!column2.toString().isEmpty()) {
						System.out.print(column2.toString());
					}
					JsonNode boxScore = gameObj.get("boxscore");
					String status = boxScore.get("status").getTextValue();
					if (boxScore != null) {
						profiledm.setAwayScore(Integer.valueOf(boxScore.get("awayScore").asInt()));
						profiledm.setHomeScore(Integer.valueOf(boxScore.get("homeScore").asInt()));
						profiledm.setStatus(status);
						System.out.print(boxScore.get("awayScore") + "-" + boxScore.get("homeScore") + "\t\t\t");
					}
					profiledm.setUpdateTime(new Timestamp(new Date().getTime()));
					this.profile = profiledm;

					System.out.println(profile.get("arenaName") + "\t\t\t");
				}
			}
		} catch (Exception localException) {
		}
	}

	public AppProfile getProfile() {
		return this.profile;
	}

	public void setProfile(AppProfile profile) {
		this.profile = profile;
	}

	public static void main(String[] args) {
		String url = "http://china.nba.com/static/data/scores/daily_2017-10-19.json";
		ResultCollectService service = new ResultCollectService(url);
		service.parseJson(url, "0021700003");
	}
}

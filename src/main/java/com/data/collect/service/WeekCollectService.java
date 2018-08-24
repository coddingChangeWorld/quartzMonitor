package com.data.collect.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;

import com.data.collect.model.AppProfile;
import com.data.collect.model.Team;

public class WeekCollectService extends AbstractCollectService {
	private Map<String,AppProfile> proList = new HashMap<String,AppProfile>();
	//private Map<String, AppTeam> teamItems = new HashMap<String, AppTeam>();

	public WeekCollectService() {
	}

	public WeekCollectService(String url) {
		this.url = url;
	}

	public void collect() {
		this.proList=parseJson(null);
	}

	public Map<String,AppProfile> parseJson(String url) {
		String pathUrl = StringUtils.isEmpty(url) ? this.url : url;
		if (StringUtils.isEmpty(pathUrl)) {
			this.logger.info("获取json数据的url不能为空");
		}
		try {
			String data = new String(doGet());
			//System.out.println(data);

			JsonNode jsonNode = mapper.readTree(data);
			//System.out.println(jsonNode.findValue("timestamp").getTextValue());

			JsonNode games = jsonNode.get("payload").get("dates");
			//System.out.println(games.isArray());
			//System.out.println(mapper.writeValueAsString(games));
			//System.out.println(games.size());
			//System.out.println("时间\t\t\t\t\t对阵(客@主)\t\t\t比分\t\t\t地点\t\t\t转播\t\t\t链接");
			Map<String,AppProfile> tempProList = new HashMap<String,AppProfile>();
			for (int i = 0; i < games.size(); i++) {
				JsonNode gameObjs = games.get(i);
				JsonNode gameUTC = gameObjs.get("games");
				for (int j = 0; j < gameUTC.size(); j++) {
					AppProfile profiledm = new AppProfile();
					JsonNode gameObj = gameUTC.get(j);
					JsonNode profile = gameObj.get("profile");
					long ot = Long.valueOf(profile.get("utcMillis").getTextValue()).longValue();
					String utc = DateFormatUtils.format(ot, com.common.tool.DateUtils.sADefaultDateTimePattern, TimeZone.getTimeZone("GMT+8:00"));

					long gmt = org.apache.commons.lang.time.DateUtils.parseDate(utc, new String[] { com.common.tool.DateUtils.sADefaultDateTimePattern }).getTime();
					Timestamp stamp = new Timestamp(gmt);
					profiledm.setGameTime(stamp);
					profiledm.setGameId(profile.get("gameId").getTextValue());

					//System.out.print(utc + "\t\t\t");
					JsonNode homeTeam = gameObj.get("homeTeam").get("profile");
					JsonNode awayTeam = gameObj.get("awayTeam").get("profile");
					//StringBuffer column2 = new StringBuffer();
					if (awayTeam != null) {
						Team awayTeamBean = (Team) mapper.readValue(awayTeam, Team.class);
						//AppTeam away = new AppTeam();
						//BeanUtils.copyProperties(away, awayTeamBean);
						//addFilterTeamItems(away);
						profiledm.setAwayTeamId(awayTeamBean.getId());
						profiledm.setAwayName(awayTeamBean.getName());
						//column2.append(awayTeamBean.getName());
						//column2.append("@");
					}
					if (homeTeam != null) {
						Team homeTeamBean = (Team) mapper.readValue(homeTeam, Team.class);
						//AppTeam home = new AppTeam();
						//BeanUtils.copyProperties(home, homeTeamBean);
						//addFilterTeamItems(home);
						profiledm.setHomeTeamId(homeTeamBean.getId());
						profiledm.setHomeName(homeTeamBean.getName());
						//column2.append(homeTeamBean.getName());
						//column2.append("\t\t\t");
					}
					/*if (!column2.toString().isEmpty()) {
						System.out.print(column2.toString());
					}*/
					JsonNode boxScore = gameObj.get("boxscore");
					if (boxScore != null) {
						profiledm.setAwayScore(Integer.valueOf(boxScore.get("awayScore").asInt()));
						profiledm.setHomeScore(Integer.valueOf(boxScore.get("homeScore").asInt()));
						//System.out.print(boxScore.get("awayScore") + "-" + boxScore.get("homeScore") + "\t\t\t");
					}
					profiledm.setUpdateTime(new Timestamp(new Date().getTime()));
					//this.proList.add(profiledm);
					if (!tempProList.containsKey(profiledm.getGameId())) {
						tempProList.put(profiledm.getGameId(), profiledm);
					}
					//System.out.println(profile.get("arenaName") + "\t\t\t");
				}
			}
			return tempProList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*private void addFilterTeamItems(AppTeam team) {
		if (!this.teamItems.containsKey(team.getId())) {
			this.teamItems.put(team.getId(), team);
		}
	}*/

	public Map<String, AppProfile> getProList() {
		return proList;
	}

	public void setProList(Map<String, AppProfile> proList) {
		this.proList = proList;
	}

	/*public Map<String, AppTeam> getTeamItems() {
		return this.teamItems;
	}

	public void setTeamItems(Map<String, AppTeam> teamItems) {
		this.teamItems = teamItems;
	}*/

	public static void main(String[] args) {
		String pathUrl = "http://china.nba.com/static/data/season/schedule_7.json";
		//String pathUrl = "http://china.nba.com/static/data/scores/daily_2018-05-05.json";
		WeekCollectService service = new WeekCollectService(pathUrl);
		Map<String,AppProfile> tempProList=service.parseJson(null);
		if(tempProList==null){
			System.out.println("今天没有比赛");
		}else{
			System.out.println("数量"+tempProList.keySet().size());
		}
	}


}

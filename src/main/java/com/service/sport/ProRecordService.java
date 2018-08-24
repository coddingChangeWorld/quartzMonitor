package com.service.sport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.dao.BaseDaoImpl;
import com.common.dto.gameItemListDto;
import com.common.dto.profileDto;
import com.data.collect.model.AppProfile;
import com.data.collect.model.AppTeam;
import com.sundoctor.example.service.SimpleService;

@Service("proRecordService")
public class ProRecordService {
	private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);
	@Autowired
	private BaseDaoImpl baseDaoImpl;

	public void executeSyncTeam(List<AppProfile> proList) throws Exception {
		logger.info("将场次数据保存到数据表，长度：" + proList.size());
		for (int i = 0; i < proList.size(); i++) {
			AppProfile profile = (AppProfile) proList.get(i);
			this.baseDaoImpl.add(profile);
		}
	}

	public void executeAddcTeam(List<AppTeam> teamItems) throws Exception {
		for (int i = 0; i < teamItems.size(); i++) {
			AppTeam team = (AppTeam) teamItems.get(i);
			this.baseDaoImpl.add(team);
		}
	}

	public int updateGameResult(AppProfile profile) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("homeScore", profile.getHomeScore());
		param.put("awayScore", profile.getAwayScore());
		param.put("gameId", profile.getGameId());
		param.put("status", profile.getStatus());
		return this.baseDaoImpl.executeByNameSql("updateProfileResultForScore", param);
	}

	public List<AppProfile> queryProfileForDay(String time) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("time", time);
		List<AppProfile> list = this.baseDaoImpl.findByNameSql("queryProfile", param, AppProfile.class);
		return list;
	}
	
	public profileDto queryProfileForId(Integer id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		
		List<profileDto> profiles = this.baseDaoImpl.findByNameSql("queryProfiledto", param, profileDto.class);
		if(profiles==null || profiles.size()<1) return null;
		return profiles.get(0);
	}
	
	public List<profileDto> queryProfileForParam(Map<String, Object> param) throws Exception {
		List<profileDto> profiles = this.baseDaoImpl.findByNameSql("queryGameListDto", param, profileDto.class);
		return profiles;
	}

	public List<gameItemListDto> queryGameItemList(String time) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("time", time);
		List<gameItemListDto> list = this.baseDaoImpl.findByNameSql("queryGameItemList", param, gameItemListDto.class);
		return list;
	}

	public List<AppTeam> queryTeamAll() {
		Map<String, Object> param = new HashMap<String, Object>();
		List<AppTeam> list = this.baseDaoImpl.findByNameSql("queryTeamAll", param, AppTeam.class);
		return list;
	}
}

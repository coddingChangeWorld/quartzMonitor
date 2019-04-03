package com.controller.sport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.dao.BaseDaoImpl;
import com.common.base.dao.Page;
import com.common.dto.dateLabelTabList;
import com.common.dto.gameItemListDto;
import com.common.dto.profileDto;
import com.common.tool.DataResponse;
import com.common.tool.DateUtils;
import com.common.tool.JacksonJson;
import com.data.collect.model.AppProfile;
import com.data.collect.model.AppTeam;
import com.data.collect.service.DayCollectService;
import com.data.collect.service.WeekCollectService;
import com.service.sport.ProRecordService;

@Controller
@RequestMapping({ "/nba" })
public class nbaController {
	@Autowired
	private BaseDaoImpl baseDaoImpl;
	@Autowired
	private ProRecordService proRecordService;

	@RequestMapping({ "/teamList" })
	@ResponseBody
	public DataResponse teamList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		int pageIndex = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("limit") == null ? "1" : request.getParameter("limit"));
		Page<AppTeam> page = new Page<AppTeam>();
		page.setCurrentPage(pageIndex);
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		this.baseDaoImpl.findByNameSqlPage("queryTeamList", map, page, AppTeam.class);

		// modelMap.addAttribute("page", page);
		return new DataResponse<List<AppTeam>>(200, page.getResult(), page.getTotalsCount());
	}

	@RequestMapping({ "/gameList" })
	@ResponseBody
	public DataResponse gameList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		int pageIndex = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("limit") == null ? "1" : request.getParameter("limit"));
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		String startTime = request.getParameter("startTime") == null ? "" : request.getParameter("startTime");
		String endTime = request.getParameter("endTime") == null ? "" : request.getParameter("endTime");
		Page<AppProfile> page = new Page<AppProfile>();
		page.setCurrentPage(--pageIndex);
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		if (!id.isEmpty())
			map.put("id", id);
		if (!startTime.isEmpty())
			map.put("startTime", startTime + " 00:00:00");
		if (!endTime.isEmpty())
			map.put("endTime", endTime + " 23:59:59");
		this.baseDaoImpl.findByNameSqlPage("queryGameList", map, page, AppProfile.class);

		// modelMap.addAttribute("page", page);
		return new DataResponse<List<AppProfile>>(200, page.getResult(), page.getTotalsCount());
	}

	@RequestMapping({ "/toTeamList" })
	public String toTeamList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return "teamList";
	}

	@RequestMapping({ "/toGameList" })
	public String toGameList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return "gameList";
	}

	@RequestMapping({ "/webGameList" })
	@ResponseBody
	public DataResponse WebGameList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		String time = DateUtils.format(new Date(), "yyyy-MM-dd");
		List<gameItemListDto> list = this.proRecordService.queryGameItemList(time);
		boolean currentDayNot = list.size() > 0 ? false : true;
		/*
		 * for (int i = 0; i < list.size(); i++) { gameItemListDto file =
		 * (gameItemListDto)list.get(i); if (3 >
		 * Integer.parseInt(file.getStatus())) { currentDayNot = false; break; }
		 * }
		 */
		if (currentDayNot) {
			time = DateUtils.format(DateUtils.addDate(new Date(), 1), "yyyy-MM-dd");
			list = this.proRecordService.queryGameItemList(time);
		}

		return new DataResponse<List<gameItemListDto>>(200, list, 0);
	}

	@RequestMapping({ "/WebProfile" })
	@ResponseBody
	public DataResponse WebProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = Integer.parseInt(request.getParameter("id"));
		profileDto profile = this.proRecordService.queryProfileForId(id);
		return new DataResponse<profileDto>(200, profile);
	}

	@RequestMapping({ "/gameDetail" })
	public String gameDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		String time = DateUtils.format(new Date(), "yyyy-MM-dd");
		List<gameItemListDto> list = this.proRecordService.queryGameItemList(time);
		boolean currentDayNot = true;
		for (int i = 0; i < list.size(); i++) {
			gameItemListDto file = (gameItemListDto) list.get(i);
			if (3 > Integer.parseInt(file.getStatus())) {
				currentDayNot = false;
				break;
			}
		}
		if (currentDayNot) {
			time = DateUtils.format(DateUtils.addDate(new Date(), 1), "yyyy-MM-dd");
			list = this.proRecordService.queryGameItemList(time);
		}
		modelMap.addAttribute("list", list);
		return "gameDetail";
	}

	@RequestMapping({ "/rulePublish" })
	public String rulePublish(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return "publishRule";
	}

	@RequestMapping({ "/profileList" })
	@ResponseBody
	public String profileList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		String time = request.getParameter("time") == null ? "2017-10-20" : request.getParameter("time");
		List<AppProfile> list = this.proRecordService.queryProfileForDay(time);
		return JacksonJson.toJson(list);
	}
	
	@RequestMapping({ "/fireForDayCollect" })
	@ResponseBody
	public DataResponse fireForDayCollect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		String time = request.getParameter("time") == null ? DateUtils.format(new Date(), DateUtils.Y_M_D) : request.getParameter("time");
		String pathUrl = "http://china.nba.com/static/data/scores/daily_"+time+".json";
		DayCollectService service = new DayCollectService(pathUrl);
		service.collect();
		Map<String, AppProfile> prolist = service.getProList();
		List<AppProfile> dbProRecord=this.proRecordService.queryProfileForDay(time);
		List<String> dbProRecordKey=new ArrayList<String>();
		for (int i = 0; i < dbProRecord.size(); i++) {
			dbProRecordKey.add(dbProRecord.get(i).getGameId());
		}
		/*去掉已经存在的*/
		for (Iterator<String> i = prolist.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			if (dbProRecordKey.contains(obj.toString())) {
				i.remove();
			}
		}
		this.proRecordService.executeSyncTeam(new ArrayList<AppProfile>(prolist.values()));
		return new DataResponse("成功同步"+prolist.keySet().size()+"场");
	}
	
	/**
	 * 
	 * @Title: weekProfileList
	 * @Description: TODO(NBA未来一周的比赛数据获取)
	 * @author: linjie
	 * @date: 2018-9-3
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "/weekProfileList" })
	@ResponseBody
	public DataResponse weekProfileList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		String pathUrl = "http://china.nba.com/static/data/season/schedule_7.json";
		WeekCollectService collectService = new WeekCollectService(pathUrl);
		collectService.collect();
		Map<String, AppProfile> weekProList = collectService.getProList();
		Map<String,dateLabelTabList<profileDto>> groupTimeList=new HashMap<String,dateLabelTabList<profileDto>>(); //key=time, value=list 按时间分组
		
		// Map<String,profileDto> weekProListDto=

		// BeanUtils.copyProperties(home, homeTeamBean);
		Map<String, Object> param = new HashMap<String, Object>();
		List<profileDto> profiles = new ArrayList<profileDto>();
		if (weekProList.keySet().size() > 0) {
			param.put("ids", weekProList.keySet().toArray(new Object[] {}));
			profiles = proRecordService.queryProfileForParam(param);
			for (int i = 0; i < profiles.size(); i++) {
				profileDto dbprofile = profiles.get(i);
				AppProfile remonetprofile = weekProList.get(dbprofile.getGameId());
				dbprofile.setSyncStatus("2");
				BeanUtils.copyProperties(dbprofile, remonetprofile);
				/*分组*/
				String time=DateUtils.format(dbprofile.getGameTime(), DateUtils.Y_M_D);
				if(groupTimeList.containsKey(time)){
					groupTimeList.get(time).getList().add(dbprofile);
				}else{
					dateLabelTabList<profileDto> dateLabelTabItem=new dateLabelTabList<profileDto>();
					dateLabelTabItem.setDate(time);
					dateLabelTabItem.setTime(dbprofile.getGameTime());
					dateLabelTabItem.setType("NBA");
					dateLabelTabItem.setList(new ArrayList<profileDto>(Arrays.asList(dbprofile)));
					groupTimeList.put(time, dateLabelTabItem);
				}
				//else groupTimeList.put(time, new ArrayList<profileDto>(Arrays.asList(dbprofile)));
				weekProList.remove(dbprofile.getGameId());
			}
			for (Map.Entry<String, AppProfile> entry : weekProList.entrySet()) {
				AppProfile remonetprofile = entry.getValue();
				profileDto remonetprofiledto = new profileDto();
				remonetprofiledto.setSyncStatus("1");
				BeanUtils.copyProperties(remonetprofiledto, remonetprofile);
				/*分组*/
				/*分组*/
				String time=DateUtils.format(remonetprofiledto.getGameTime(), DateUtils.Y_M_D);
				if(groupTimeList.containsKey(time)){
					groupTimeList.get(time).getList().add(remonetprofiledto);
				}else{
					dateLabelTabList<profileDto> dateLabelTabItem=new dateLabelTabList<profileDto>();
					dateLabelTabItem.setDate(time);
					dateLabelTabItem.setTime(remonetprofiledto.getGameTime());
					dateLabelTabItem.setType("NBA");
					dateLabelTabItem.setList(new ArrayList<profileDto>(Arrays.asList(remonetprofiledto)));
					groupTimeList.put(time, dateLabelTabItem);
				}
				//profiles.add(remonetprofiledto);
			}

		}
		ArrayList<dateLabelTabList> result=new ArrayList<dateLabelTabList>(groupTimeList.values());
		Collections.sort(result);
		return new DataResponse<ArrayList<dateLabelTabList>>(200, result ,0);
	}
}

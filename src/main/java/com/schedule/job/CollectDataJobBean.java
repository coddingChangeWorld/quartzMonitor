package com.schedule.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.common.tool.DateUtils;
import com.data.collect.model.AppProfile;
import com.data.collect.model.AppTeam;
import com.data.collect.service.DayCollectService;
import com.service.sport.ProRecordService;
import com.sundoctor.example.service.MyQuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CollectDataJobBean extends QuartzJobBean {
	@Autowired
	private ProRecordService proRecordService;
	private static final Logger logger = LoggerFactory.getLogger(MyQuartzJobBean.class);

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("执行采集数据任务");
		this.proRecordService = ((ProRecordService) getApplicationContext(context).getBean("proRecordService", ProRecordService.class));
		Date fireTime=context.getFireTime();
		logger.debug("任务触发时间"+DateUtils.format(fireTime, DateUtils.Y_M_D_HMS));
		//String pathUrl = "http://china.nba.com/static/data/season/schedule_7.json";
		String day=DateUtils.format(fireTime, DateUtils.Y_M_D);
		String pathUrl = "http://china.nba.com/static/data/scores/daily_"+day+".json";
		DayCollectService service = new DayCollectService(pathUrl);
		service.collect();
		Map<String, AppProfile> prolist = service.getProList();
		//Map<String, AppTeam> teamItems = service.getTeamItems();
		List<AppTeam> dbteamItems = this.proRecordService.queryTeamAll();
		List<AppProfile> dbProRecord=this.proRecordService.queryProfileForDay(day);
		List<String> dbProRecordKey=new ArrayList<String>();
		List<String> dbteamKey=new ArrayList<String>();
		for (int i = 0; i < dbteamItems.size(); i++) {
			dbteamKey.add(dbteamItems.get(i).getId());
		}
		for (int i = 0; i < dbProRecord.size(); i++) {
			dbProRecordKey.add(dbProRecord.get(i).getGameId());
		}
		/*for (Iterator<String> i = teamItems.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			if (dbteamKey.contains(obj.toString())) {
				//teamItems.remove(key);
				i.remove();
			}
		}*/
		/*去掉已经存在的*/
		for (Iterator<String> i = prolist.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			if (dbProRecordKey.contains(obj.toString())) {
				i.remove();
			}
		}
		try {
			//this.proRecordService.executeAddcTeam(new ArrayList<AppTeam>(teamItems.values()));
			this.proRecordService.executeSyncTeam(new ArrayList<AppProfile>(prolist.values()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ApplicationContext getApplicationContext(JobExecutionContext jobexecutioncontext) {
		try {
			return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContextKey");
		} catch (SchedulerException e) {
			logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
			throw new RuntimeException(e);
		}
	}
}

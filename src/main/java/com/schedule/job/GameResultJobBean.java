package com.schedule.job;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.common.tool.DateUtils;
import com.common.tool.SpringContextUtil;
import com.data.collect.model.AppProfile;
import com.data.collect.service.ResultCollectService;
import com.service.sport.OrderHandler;
import com.service.sport.ProRecordService;
import com.sundoctor.quartz.service.SchedulerService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class GameResultJobBean extends QuartzJobBean {
	private OrderHandler handler;
	private String baseUrl = "http://china.nba.com/static/data/scores/";
	private ProRecordService proRecordService;
	private SchedulerService schedulerService;
	private static final Logger logger = LoggerFactory.getLogger(GameResultJobBean.class);
	

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String startTime=DateUtils.format(new Date(), DateUtils.Y_M_D_HMS);
		logger.info("开始采集比分结果数据数据/"+startTime);
		this.proRecordService = ((ProRecordService) getApplicationContext(context).getBean("proRecordService", ProRecordService.class));
		this.schedulerService = ((SchedulerService) getApplicationContext(context).getBean("schedulerService", SchedulerService.class));
		String url = this.baseUrl + "daily_" + DateUtils.format(new Date(), "yyyy-MM-dd") + ".json";
		ResultCollectService service = new ResultCollectService(url);
		String gameId = "";
		//JobDataMap jobdata = context.getJobDetail().getJobDataMap();
		//String jobName = context.getJobDetail().getKey().getName();
		String triggerName = context.getTrigger().getKey().getName();
		gameId = triggerName.substring(triggerName.indexOf("-") + 1);
		service.parseJson(url, gameId);
		AppProfile profile = service.getProfile();
		if (profile != null) {
			int up = this.proRecordService.updateGameResult(profile);
			if (up > 0) {
				logger.info("比赛结果同步成功  gameId=" + gameId + ",homeScore=" + profile.getHomeScore() + ",awayScore=" + profile.getAwayScore());
			}
			
			if("3".equals(profile.getStatus())){
				String id = "";
				 this.handler = ((OrderHandler)SpringContextUtil.getBean("bbuserOrderService"));
				 this.handler.handler(id);
				 logger.info("比赛下注结果计算完成  gameId=" + gameId);
				this.schedulerService.removeTrigdger(triggerName);
				logger.info("比赛结束删除定时器");
			}
		}
		String endTime=DateUtils.format(new Date(), DateUtils.Y_M_D_HMS);
		logger.info("结束采集比分结果数据数据/"+endTime);
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

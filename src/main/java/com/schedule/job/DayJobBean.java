package com.schedule.job;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.common.tool.BeanFactory;
import com.common.tool.DateUtils;
import com.common.tool.PropertiesCache;
import com.common.tool.SpringContextUtil;
import com.data.collect.model.AppProfile;
import com.service.sport.ProRecordService;
import com.sundoctor.quartz.service.SchedulerService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DayJobBean extends QuartzJobBean {
	@Autowired
	private ProRecordService proRecordService;
	@Autowired
	private SchedulerService schedulerService;
	private static final Logger logger = LoggerFactory.getLogger(DayJobBean.class);

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String startTime=DateUtils.format(new Date(), DateUtils.Y_M_D_HMS);
		logger.info("生产比分结果数据采集任务/"+startTime);
		this.proRecordService = ((ProRecordService) getApplicationContext(context).getBean("proRecordService", ProRecordService.class));
		this.schedulerService = ((SchedulerService) getApplicationContext(context).getBean("schedulerService", SchedulerService.class));
		PropertiesCache cache = (PropertiesCache) getApplicationContext(context).getBean("cacheProperties", PropertiesCache.class);
		Properties properties = cache.getCacheProperties();
		String time = DateUtils.format(new Date(), "yyyy-MM-dd");
		//String time = "2017-10-20";
		List<AppProfile> list = this.proRecordService.queryProfileForDay(time);
		for (int i = 0; i < list.size(); i++) {
			AppProfile file = (AppProfile) list.get(i);
			String triggerName = "trigger-" + file.getGameId();
			Date gtime = file.getGameTime();
			int delayTime = Integer.parseInt(properties.getProperty("job.delay.time"));
			gtime = DateUtils.addMinute(gtime, delayTime);
			String cron = DateUtils.getCron(gtime);
			String selType = properties.getProperty("job.delay.interval.selType");
			String val = properties.getProperty("job.delay.notget.interval");
			String intervalCron=DateUtils.addCronInterval(selType, val, cron);
			logger.info(file.getGameId()+"结果采集初始时间"+intervalCron);
			//String intervalCron = "0 0/5 * ? * * *";
			//String jobName = "jobDetail-" + file.getId();
			//JobBuilder b = new JobBuilder();
	        //b.ofType(ameResultJobBean.class);
			//JobDetail jobDetail = JobBuilder.newJob(GameResultJobBean.class).storeDurably().withIdentity(jobName, null).build();
			JobDetail jobDetail = (JobDetail) SpringContextUtil.getBean("jobDetail3");
			this.schedulerService.scheduleDetail(triggerName, jobDetail, intervalCron);
			logger.info("添加比赛结果采集任务" + file.getGameId() + "/" + intervalCron);
		}
	}

	/*public static void main(String[] args) {
		Date gtime = DateUtils.format("2017-10-20 09:00", "yyyy-MM-dd HH:mm");
		String cron = DateUtils.getCron(gtime);
		System.out.println(cron);
		String gameId = "";
		String triggerName="trigger-0041700225";
	    gameId=triggerName.substring(triggerName.indexOf("-")+1);
	    System.out.println(gameId);
	}*/

	private ApplicationContext getApplicationContext(JobExecutionContext jobexecutioncontext) {
		try {
			return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContextKey");
		} catch (SchedulerException e) {
			logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
			throw new RuntimeException(e);
		}
	}
}

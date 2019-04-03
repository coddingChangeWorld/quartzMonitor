package com.sundoctor.quartz.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.quartz.CronExpression;
import org.quartz.JobDetail;

public abstract interface SchedulerService {
	
	/**
	 * 
	 * @Title: modifyJobTime
	 * @Description: TODO(修改任务的触发时间)
	 * @author: Administrator
	 * @date: 2019年1月9日
	 * @param jobName
	 * @param groupName
	 * @param paramCronExpression
	 */
	public abstract void ramodifyJobTime(String jobName,String triggerName, CronExpression cronExpression);
	/**
	 * @Title: modifyJobTime
	 * @Description: TODO(修改任务的触发时间)
	 * @author: Administrator
	 * @date: 2019年1月9日
	 * @param jobName
	 * @param groupName
	 * @param paramCronExpression
	 */
	public abstract void modifyJobTime(String jobName, String groupName, CronExpression paramCronExpression);

	public abstract List<Map<String, Object>> getQrtzTriggers();

	public abstract void schedule(String paramString);

	public abstract void schedule(String paramString1, String paramString2);

	public abstract void schedule(String paramString1, String paramString2, String paramString3);

	public abstract void scheduleDetail(String paramString1, String paramString2, String paramString3);

	public abstract void scheduleDetail(String paramString1, JobDetail paramJobDetail, String paramString2);

	public abstract void schedule(CronExpression paramCronExpression);

	public abstract void schedule(String paramString, CronExpression paramCronExpression);

	public abstract void schedule(String paramString1, String paramString2, CronExpression paramCronExpression);

	public abstract void schedule(Date paramDate);

	public abstract void schedule(Date paramDate, String paramString);

	public abstract void schedule(String paramString, Date paramDate);

	public abstract void schedule(String paramString1, Date paramDate, String paramString2);

	public abstract void schedule(Date paramDate1, Date paramDate2);

	public abstract void schedule(Date paramDate1, Date paramDate2, String paramString);

	public abstract void schedule(String paramString, Date paramDate1, Date paramDate2);

	public abstract void schedule(String paramString1, Date paramDate1, Date paramDate2, String paramString2);

	public abstract void schedule(Date paramDate, int paramInt);

	public abstract void schedule(Date paramDate1, Date paramDate2, int paramInt);

	public abstract void schedule(Date paramDate1, Date paramDate2, int paramInt, String paramString);

	public abstract void schedule(String paramString, Date paramDate1, Date paramDate2, int paramInt);

	public abstract void schedule(String paramString1, Date paramDate1, Date paramDate2, int paramInt, String paramString2);

	public abstract void schedule(Date paramDate, int paramInt, long paramLong);

	public abstract void schedule(Date paramDate1, Date paramDate2, int paramInt, long paramLong);

	public abstract void schedule(Date paramDate1, Date paramDate2, int paramInt, long paramLong, String paramString);

	public abstract void schedule(String paramString, Date paramDate1, Date paramDate2, int paramInt, long paramLong);

	public abstract void schedule(String paramString1, Date paramDate1, Date paramDate2, int paramInt, long paramLong, String paramString2);

	public abstract void schedule(Map<String, Object> paramMap);

	public abstract void pauseTrigger(String paramString);

	public abstract void pauseTrigger(String paramString1, String paramString2);

	public abstract void resumeTrigger(String paramString);

	public abstract void resumeTrigger(String paramString1, String paramString2);

	public abstract boolean removeTrigdger(String paramString);

	public abstract boolean removeTrigdger(String paramString1, String paramString2);
}

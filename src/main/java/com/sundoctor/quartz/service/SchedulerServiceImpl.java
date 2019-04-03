package com.sundoctor.quartz.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.CronExpression;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.tool.PropertiesCache;
import com.common.tool.SpringContextUtil;
import com.sundoctor.quartz.dao.QuartzDao;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {
	private static final String NULLSTRING = null;
	private static final Date NULLDATE = null;
	@Autowired
	private Scheduler scheduler;
	@Autowired
	private JobDetail jobDetail;
	@Autowired
	private QuartzDao quartzDao;

	public List<Map<String, Object>> getQrtzTriggers() {
		return this.quartzDao.getQrtzTriggers();
	}

	public void schedule(String cronExpression) {
		schedule(NULLSTRING, cronExpression);
	}

	public void schedule(String name, String cronExpression) {
		schedule(name, NULLSTRING, cronExpression);
	}

	public void schedule(String name, String group, String cronExpression) {
		try {
			schedule(name, group, new CronExpression(cronExpression));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void scheduleDetail(String name, String jobDetailName, String cronExpression) {
		try {
			schedule(name, jobDetailName, NULLSTRING, new CronExpression(cronExpression));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void scheduleDetail(String name, JobDetail jobDetail, String cronExpression) {
		try {
			schedule(name, jobDetail, NULLSTRING, new CronExpression(cronExpression));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void schedule(CronExpression cronExpression) {
		schedule(NULLSTRING, cronExpression);
	}

	public void schedule(String name, CronExpression cronExpression) {
		schedule(name, NULLSTRING, cronExpression);
	}

	public void schedule(String name, String jobDetailName, String group, CronExpression cronExpression) {
		JobDetail jobDetailBean = (JobDetail) SpringContextUtil.getBean(jobDetailName);
		if (isValidExpression(cronExpression)) {
			if ((name == null) || (name.trim().equals(""))) {
				name = UUID.randomUUID().toString();
			}
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExpression);

			TriggerKey triggerKey = new TriggerKey(name, group);

			trigger.setJobName(jobDetailBean.getKey().getName());
			trigger.setKey(triggerKey);
			try {
				this.scheduler.addJob(jobDetailBean, true);
				if (this.scheduler.checkExists(triggerKey)) {
					this.scheduler.rescheduleJob(triggerKey, trigger);
				} else {
					this.scheduler.scheduleJob(trigger);
				}
			} catch (SchedulerException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	public void schedule(String name, JobDetail jobDetail, String group, CronExpression cronExpression) throws ParseException {
		if (!isValidExpression(cronExpression)) {
			PropertiesCache cache = (PropertiesCache)SpringContextUtil.getBean("cacheProperties");
			Properties properties = cache.getCacheProperties();
			String selType = properties.getProperty("job.delay.expire.interval.selType");
			String val = properties.getProperty("job.delay.expire.notget.interval");
			String cron=com.common.tool.DateUtils.getNowCronInterval(selType, val);
			cronExpression=new CronExpression(cron);
			System.out.println("重置定时时间"+cron);
		}
		if ((name == null) || (name.trim().equals(""))) {
			name = UUID.randomUUID().toString();
		}
		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setCronExpression(cronExpression);

		TriggerKey triggerKey = new TriggerKey(name, group);

		trigger.setJobName(jobDetail.getKey().getName());
		trigger.setKey(triggerKey);
		try {
			this.scheduler.addJob(jobDetail, true);
			if (this.scheduler.checkExists(triggerKey)) {
				this.scheduler.rescheduleJob(triggerKey, trigger);
			} else {
				this.scheduler.scheduleJob(trigger);
			}
		} catch (SchedulerException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	public void schedule(String name, String group, CronExpression cronExpression) {
		if (isValidExpression(cronExpression)) {
			if ((name == null) || (name.trim().equals(""))) {
				name = UUID.randomUUID().toString();
			}
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExpression);

			TriggerKey triggerKey = new TriggerKey(name, group);

			trigger.setJobName(this.jobDetail.getKey().getName());
			trigger.setKey(triggerKey);
			try {
				this.scheduler.addJob(this.jobDetail, true);
				if (this.scheduler.checkExists(triggerKey)) {
					this.scheduler.rescheduleJob(triggerKey, trigger);
				} else {
					this.scheduler.scheduleJob(trigger);
				}
			} catch (SchedulerException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	public void schedule(Date startTime) {
		schedule(startTime, NULLDATE);
	}

	public void schedule(Date startTime, String group) {
		schedule(startTime, NULLDATE, group);
	}

	public void schedule(String name, Date startTime) {
		schedule(name, startTime, NULLDATE);
	}

	public void schedule(String name, Date startTime, String group) {
		schedule(name, startTime, NULLDATE, group);
	}

	public void schedule(Date startTime, Date endTime) {
		schedule(startTime, endTime, 0);
	}

	public void schedule(Date startTime, Date endTime, String group) {
		schedule(startTime, endTime, 0, group);
	}

	public void schedule(String name, Date startTime, Date endTime) {
		schedule(name, startTime, endTime, 0);
	}

	public void schedule(String name, Date startTime, Date endTime, String group) {
		schedule(name, startTime, endTime, 0, group);
	}

	public void schedule(Date startTime, int repeatCount) {
		schedule(null, startTime, NULLDATE, 0);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount) {
		schedule(null, startTime, endTime, 0);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount, String group) {
		schedule(null, startTime, endTime, 0, group);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount) {
		schedule(name, startTime, endTime, 0, 0L);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount, String group) {
		schedule(name, startTime, endTime, 0, 0L, group);
	}

	public void schedule(Date startTime, int repeatCount, long repeatInterval) {
		schedule(null, startTime, NULLDATE, repeatCount, repeatInterval);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		schedule(null, startTime, endTime, repeatCount, repeatInterval);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval, String group) {
		schedule(null, startTime, endTime, repeatCount, repeatInterval, group);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		schedule(name, startTime, endTime, repeatCount, repeatInterval, NULLSTRING);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval, String group) {
		if (isValidExpression(startTime)) {
			if ((name == null) || (name.trim().equals(""))) {
				name = UUID.randomUUID().toString();
			}
			TriggerKey triggerKey = new TriggerKey(name, group);

			SimpleTriggerImpl trigger = new SimpleTriggerImpl();
			trigger.setKey(triggerKey);
			trigger.setJobName(this.jobDetail.getKey().getName());

			trigger.setStartTime(startTime);
			trigger.setEndTime(endTime);
			trigger.setRepeatCount(repeatCount);
			trigger.setRepeatInterval(repeatInterval);
			try {
				this.scheduler.addJob(this.jobDetail, true);
				if (this.scheduler.checkExists(triggerKey)) {
					this.scheduler.rescheduleJob(triggerKey, trigger);
				} else {
					this.scheduler.scheduleJob(trigger);
				}
			} catch (SchedulerException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	public void schedule(Map<String, Object> map) {
		String name = MapUtils.getString(map, "triggerName");
		if (StringUtils.isEmpty(StringUtils.trim(name))) {
			name = UUID.randomUUID().toString();
		} else {
			name = name + "&" + UUID.randomUUID().toString();
		}
		String group = MapUtils.getString(map, "triggerGroup");
		if (StringUtils.isEmpty(group)) {
			group = "DEFAULT";
		}
		TriggerKey triggerKey = new TriggerKey(name, group);

		SimpleTriggerImpl trigger = new SimpleTriggerImpl();

		trigger.setJobName(this.jobDetail.getKey().getName());
		trigger.setKey(triggerKey);
		trigger.setRepeatInterval(1000L);

		String temp = MapUtils.getString(map, "startTime");
		if (StringUtils.isNotEmpty(temp)) {
			try {
				trigger.setStartTime(DateUtils.parseDate(temp, new String[] { "yyyy-MM-dd HH:mm" }));
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}
		}
		temp = MapUtils.getString(map, "endTime");
		if (StringUtils.isNotEmpty(temp)) {
			try {
				trigger.setEndTime(DateUtils.parseDate(temp, new String[] { "yyyy-MM-dd HH:mm" }));
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}
		}
		temp = MapUtils.getString(map, "repeatCount");
		if ((StringUtils.isNotEmpty(temp)) && (NumberUtils.toInt(temp) > 0)) {
			trigger.setRepeatCount(NumberUtils.toInt(temp));
		}
		temp = MapUtils.getString(map, "repeatInterval");
		if ((StringUtils.isNotEmpty(temp)) && (NumberUtils.toLong(temp) > 0L)) {
			trigger.setRepeatInterval(NumberUtils.toLong(temp) * 1000L);
		}
		try {
			this.scheduler.addJob(this.jobDetail, true);
			if (this.scheduler.checkExists(triggerKey)) {
				this.scheduler.rescheduleJob(triggerKey, trigger);
			} else {
				this.scheduler.scheduleJob(trigger);
			}
		} catch (SchedulerException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void pauseTrigger(String triggerName) {
		pauseTrigger(triggerName, NULLSTRING);
	}

	public void pauseTrigger(String triggerName, String group) {
		try {
			this.scheduler.pauseTrigger(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	public void resumeTrigger(String triggerName) {
		resumeTrigger(triggerName, NULLSTRING);
	}

	public void resumeTrigger(String triggerName, String group) {
		try {
			this.scheduler.resumeTrigger(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean removeTrigdger(String triggerName) {
		return removeTrigdger(triggerName, NULLSTRING);
	}

	public boolean removeTrigdger(String triggerName, String group) {
		TriggerKey triggerKey = new TriggerKey(triggerName, group);
		try {
			this.scheduler.pauseTrigger(triggerKey);
			return this.scheduler.unscheduleJob(triggerKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @Title: isValidExpression
	 * @Description: TODO(验证cron是否已经过期)
	 * @author: Administrator
	 * @date: 2018-5-9
	 * @param cronExpression
	 * @return
	 */
	private boolean isValidExpression(CronExpression cronExpression) {
		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setCronExpression(cronExpression);

		Date date = trigger.computeFirstFireTime(null);

		return (date != null) && (date.after(new Date()));
	}

	private boolean isValidExpression(Date startTime) {
		SimpleTriggerImpl trigger = new SimpleTriggerImpl();
		trigger.setStartTime(startTime);

		Date date = trigger.computeFirstFireTime(null);

		return (date != null) && (date.after(new Date()));
	}
	
	/** 
     * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名) 
     * @param jobName 
     * @param time 
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     * @author Comsys-LZP 
     * @date 2014-6-26 下午03:49:21 
     * @version V2.0 
     */  
	@SuppressWarnings("unchecked")
	public void ramodifyJobTime(String jobName, String triggerName, CronExpression cronExpression) {
		try {
			TriggerKey triggerKey = new TriggerKey(triggerName, org.quartz.utils.Key.DEFAULT_GROUP);
			CronTriggerImpl trigger = (CronTriggerImpl) this.scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cronExpression.getCronExpression())) {
				JobKey jobkey = new JobKey(jobName, org.quartz.utils.Key.DEFAULT_GROUP);
				JobDetail jobDetail = this.scheduler.getJobDetail(jobkey);
				Class objJobClass = jobDetail.getJobClass();
				removeJob(jobName, org.quartz.utils.Key.DEFAULT_GROUP, triggerName, org.quartz.utils.Key.DEFAULT_GROUP);
				addJob(jobName, triggerName, objJobClass, cronExpression);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void modifyJobTime(String triggerName, String groupName, CronExpression cronExpression) {
		if (isValidExpression(cronExpression)) {
			try {
				TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
				CronTriggerImpl trigger = (CronTriggerImpl) this.scheduler.getTrigger(triggerKey);
	            if (trigger == null) {  
	                return;  
	            }
	            String oldTime = trigger.getCronExpression();
	            if (!oldTime.equalsIgnoreCase(cronExpression.getCronExpression())) {
	            	//JobKey jobkey=new JobKey(jobName, groupName);
	                //JobDetail jobDetail = this.scheduler.getJobDetail(jobkey);
	                // 修改时间  
	                trigger.setCronExpression(cronExpression);  
	                // 重启触发器
	                this.scheduler.rescheduleJob(triggerKey, trigger);
	                this.scheduler.pauseTrigger(triggerKey);
	                this.scheduler.resumeTrigger(triggerKey);
	            }
			} catch (SchedulerException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
	}
	
	/** 
     * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
     * @param jobName 
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     * @author Comsys-LZP 
     * @date 2014-6-26 下午03:49:51 
     * @version V2.0 
     */  
    public void removeJob(String jobName,String triggerName) {  
        try {
        	TriggerKey triggerKey = new TriggerKey(jobName, org.quartz.utils.Key.DEFAULT_GROUP);
        	JobKey jobkey=new JobKey(jobName, org.quartz.utils.Key.DEFAULT_GROUP);
        	this.scheduler.pauseTrigger(triggerKey);// 停止触发器  
        	this.scheduler.unscheduleJob(triggerKey);// 移除触发器  
        	this.scheduler.deleteJob(jobkey);// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description: 移除一个任务 
     * @param jobName 
     * @param jobGroupName 
     * @param triggerName 
     * @param triggerGroupName 
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     * @author Comsys-LZP 
     * @date 2014-6-26 下午03:50:01 
     * @version V2.0 
     */  
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {  
        try {
        	TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
        	JobKey jobkey=new JobKey(jobName, jobGroupName);
        	this.scheduler.pauseTrigger(triggerKey);// 停止触发器 
        	this.scheduler.unscheduleJob(triggerKey);// 移除触发器 
        	this.scheduler.deleteJob(jobkey);// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  
    }
    
    /** 
     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 
     *  
     * @param jobName 
     *            任务名 
     * @param cls 
     *            任务 
     * @param time 
     *            时间设置，参考quartz说明文档 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author Comsys-LZP 
     * @date 2014-6-26 下午03:47:44 
     * @version V2.0 
     */  
    @SuppressWarnings("unchecked")  
    public void addJob(String jobName,String triggerName, Class cls, CronExpression cronExpression) {  
        try { 
        	JobDetailImpl jobDetail=new JobDetailImpl();
        	jobDetail.setName(jobName);
        	jobDetail.setGroup(org.quartz.utils.Key.DEFAULT_GROUP);  //默认分组
        	jobDetail.setJobClass(cls);
        	jobDetail.setDurability(true);
        	//jobDetail.
            //JobDetail jobDetail = new JobDetail(jobName, org.quartz.utils.Key.DEFAULT_GROUP, cls);// 任务名，任务组，任务执行类  
            // 触发器  
            CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExpression); // 触发器时间设定 
			TriggerKey triggerKey = new TriggerKey(triggerName, org.quartz.utils.Key.DEFAULT_GROUP);  //默认分组

			trigger.setJobName(jobDetail.getKey().getName());
			trigger.setKey(triggerKey);
            this.scheduler.scheduleJob(jobDetail, trigger);  
            // 启动  
            if (!this.scheduler.isShutdown()) {  
            	this.scheduler.start();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}

package com.controller.quartz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.tool.DateUtils;
import com.common.tool.JacksonJson;

@Controller
@RequestMapping({"/cronWeb"})
public class CronExpressionController {
	
	@RequestMapping(value={"/CalcRunTime"}, produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String CalcRunTime(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		String CronExpression=request.getParameter("CronExpression");
		List<String> result=GetTaskeFireTime(CronExpression,5);
		return JacksonJson.toJson(result);
	}
	
	
	/**
	 * 
	 * @Title: GetTaskeFireTime
	 * @Description: 获取任务在未来周期内哪些时间会运行
        <param name="CronExpressionString">Cron表达式</param>
        <param name="numTimes">运行次数</param>
        <returns>运行时间段</returns>
	 * @author: Administrator
	 * @date: 2018年11月26日
	 * @param CronExpressionString
	 * @param numTimes
	 * @return
	 * @throws Exception 
	 */
	public static List<String> GetTaskeFireTime(String CronExpressionString, int numTimes) throws Exception{
		if (numTimes < 0)
        {
            throw new Exception("参数numTimes值大于等于0");
        }
        //时间表达式
		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setCronExpression(CronExpressionString);
		List<Date> dates=TriggerUtils.computeFireTimes(trigger, null, numTimes);
        /*ITrigger trigger = TriggerBuilder.Create().WithCronSchedule(CronExpressionString).Build();
        IList<DateTimeOffset> dates = TriggerUtils.ComputeFireTimes(trigger as IOperableTrigger, null, numTimes);*/
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < dates.size(); i++) {
        	Date dtf=dates.get(i);
        	list.add(DateUtils.format(dtf, DateUtils.sADefaultDateTimePattern));
		}
        
        return list;
	}

}

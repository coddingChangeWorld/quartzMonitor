package com.controller.quartz;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.common.base.dao.BaseDaoImpl;
import com.common.dto.message;
import com.common.tool.DataResponse;
import com.common.tool.JacksonJson;
import com.data.collect.model.AppProfile;
import com.sundoctor.quartz.service.SchedulerService;

@Controller
@RequestMapping({"/schedule"})
public class scheduleController
{
  @Autowired
  private BaseDaoImpl baseDaoImpl;
  @Autowired
  private SchedulerService schedulerService;
  private message msg;
  
  @RequestMapping(value={"/service"}, produces={"application/json;charset=UTF-8"})
  @ResponseBody
  public String teamList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
    throws Exception
  {
    String jobtype = request.getParameter("jobtype");
    String action = request.getParameter("action");
    this.msg = new message();
    if ((jobtype.equals("0")) && (action.equals("add"))) {
      addSimpleTrigger(request, response);
    } else if ((jobtype.equals("1")) && (action.equals("add"))) {
      addCronTriggerByExpression(request, response);
    } else if ((jobtype.equals("2")) && (action.equals("add"))) {
      addCronTriggerBy(request, response);
    } else if ((jobtype.equals("200")) && (action.equals("pause"))) {
      pauseTrigger(request, response);
    } else if ((jobtype.equals("200")) && (action.equals("resume"))) {
      resumeTrigger(request, response);
    } else if ((jobtype.equals("200")) && (action.equals("remove"))) {
      removeTrigdger(request, response);
    }
    return JacksonJson.toJson(this.msg);
  }
  
	@RequestMapping(value = { "/query" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public DataResponse query(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		List<Map<String, Object>> results = this.schedulerService.getQrtzTriggers();
		// modelMap.addAttribute("list", results);
		return new DataResponse(200, results, 0);
	}

	@RequestMapping(value = { "/jobList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String jobList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return "manager/jobList";
	}
	
	@RequestMapping(value = { "/modifyCron" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public DataResponse modifyCron(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		String triggerName = request.getParameter("triggerName");
	    String cronPression = request.getParameter("cronPression");
	    String group = request.getParameter("group");
	    
	    schedulerService.modifyJobTime(triggerName, group, new CronExpression(cronPression));
	    String results="任务时间修改成功";
		return new DataResponse(200, null , results);
	}

	@RequestMapping(value = { "/main" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String main(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return "manager/schedule";
	}

	private void addSimpleTrigger(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "p_");
		if (StringUtils.isEmpty(MapUtils.getString(filterMap, "startTime"))) {
			if (this.msg != null) {
				this.msg.setCode(1);
			}
		}
		this.schedulerService.schedule(filterMap);
	}

	private void addCronTriggerByExpression(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String triggerName = request.getParameter("triggerName");
		String jobDetailName = request.getParameter("jobDetail");
		String cronExpression = request.getParameter("cronExpression");
		if ((StringUtils.isEmpty(triggerName)) || (StringUtils.isEmpty(cronExpression))) {
			if (this.msg != null) {
				this.msg.setCode(1);
			}
		}
		this.schedulerService.scheduleDetail(triggerName, jobDetailName, cronExpression);
	}
  
	private void addCronTriggerBy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String triggerName = request.getParameter("triggerName");
		String val = request.getParameter("val");
		String selType = request.getParameter("selType");
		if ((StringUtils.isEmpty(triggerName)) || (StringUtils.isEmpty(val)) || (NumberUtils.toLong(val) < 0L) || (NumberUtils.toLong(val) > 59L)) {
			if (this.msg != null) {
				this.msg.setCode(1);
			}
		}
		String expression = null;
		if (StringUtils.equals(selType, "second")) {
			expression = "0/" + val + " * * ? * * *";
		} else if (StringUtils.equals(selType, "minute")) {
			expression = "0 0/" + val + " * ? * * *";
		}
		this.schedulerService.schedule(triggerName, expression);
	}

	private void pauseTrigger(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String triggerName = URLDecoder.decode(request.getParameter("triggerName"), "utf-8");
		String group = URLDecoder.decode(request.getParameter("group"), "utf-8");

		this.schedulerService.pauseTrigger(triggerName, group);
	}

	private void resumeTrigger(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String triggerName = URLDecoder.decode(request.getParameter("triggerName"), "utf-8");
		String group = URLDecoder.decode(request.getParameter("group"), "utf-8");

		this.schedulerService.resumeTrigger(triggerName, group);
	}
  
	private void removeTrigdger(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String triggerName = URLDecoder.decode(request.getParameter("triggerName"), "utf-8");
		String group = URLDecoder.decode(request.getParameter("group"), "utf-8");

		boolean rs = this.schedulerService.removeTrigdger(triggerName, group);
		if (!rs) {
			if (this.msg != null) {
				this.msg.setCode(1);
			}
		}
	}
	
	private void editTrigdger(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
}

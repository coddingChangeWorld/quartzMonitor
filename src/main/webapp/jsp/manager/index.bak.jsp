<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/960.css');</style>
	<style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/colour.css');</style>
	<style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/template.css');</style>
	<style type="text/css">@import url('${pageContext.request.contextPath}/components/calendar/skins/aqua/theme.css');</style>
	<script type="text/javascript" src='<c:url value="/components/jquery/jquery-1.7.2.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/components/jquery/jquery.json-2.3.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/components/jquery/jquery.form.js"/>'></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/calendar/calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/calendar/calendar-setup.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/calendar/lang/calendar-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/job.js"></script>
  </head>
  
  <body>
  <div class="wrapper">
  	<%@ include file="header.jsp"%>
  	<div id="content" class="container_16 clearfix">
				<!-- 内容开始 -->
				<p>
					<a
						href="${pageContext.request.contextPath}/schedule/query?jobtype=100&action=query">调度任务监控管理</a>
				</p>
				<%-- <p><a href="${pageContext.request.contextPath}/nba/teamList">球队列表</a></p> --%>
				<form id="myForm1"
					action="${pageContext.request.contextPath}/schedule/service"
					method="post">
					<input type="hidden" name="jobtype" value="0"/>
					<input type="hidden" name="action" value="add"/>
					<table>
						<tr>
							<th colspan="3" bgcolor="00ff00"><b>Simple Trigger</b>
							</th>
						</tr>
						<tr>
							<td nowrap>Trigger名称：</td>
							<td><input type="text" name="p_triggerName" size="20">
							</td>
							<td>（必填）</td>
						</tr>
						<tr>
							<td nowrap>Trigger分组：</td>
							<td><select name="p_triggerGroup">
									<option value="DEFAULT">defaulf</option>
									<option value="行政组">行政组</option>
									<option value="财务组">财务组</option>
									<option value="开发组">开发组</option>
							</select>
							</td>
							<td>（Trigger分组，Quartz默认组为DEFAULT）</td>
						</tr>
						<tr>
							<td nowrap>开始时间：</td>
							<td><input type="text" id="p_startTime" name="p_startTime"
								size="20">
							</td>
							<td><img id="calBegin"
								src="components/calendar/skins/aqua/cal.gif" border="0"
								alt="开始时间" style="cursor:pointer">（Trigger执行开始时间，必填）</td>
						</tr>
						<tr>
							<td nowrap>结束时间：</td>
							<td><input type="text" id="p_endTime" name="p_endTime"
								size="20">
							</td>
							<td><img id="calEnd"
								src="components/calendar/skins/aqua/cal.gif" border="0"
								alt="结束时间" style="cursor:pointer">（Trigger执行结束时间，可以不填写）</td>
						</tr>
						<tr>
							<td nowrap>执行次数：</td>
							<td><input " type="text" name="p_repeatCount" size="20">
							</td>
							<td>次（表示Trigger启动后执行多少次结束，不填写执行一次）</td>
						<tr>
							<td nowrap>执行间隔：</td>
							<td><input " type="text" name="p_repeatInterval" size="20">
							</td>
							<td>秒（表示Trigger间隔多长时间执行一次，不填写前后两次执行没有时间间隔，直到任务结束）</td>
						</tr>
						<tr>
							<td colspan="3"><input type="submit" value="添加Trigger">
							</td>
						</tr>
					</table>
				</form>
				<script type="text/javascript">
					Calendar.setup({
						inputField : "p_startTime",
						ifFormat : "%Y-%m-%d %H:%M",
						showsTime : "true",
						button : "calBegin",
						step : 1
					});

					Calendar.setup({
						inputField : "p_endTime",
						ifFormat : "%Y-%m-%d %H:%M",
						showsTime : "true",
						button : "calEnd",
						step : 1
					});
				</script>

				<br>

				<form id="myForm2"
					action="${pageContext.request.contextPath}/schedule/service"
					method="post">
					<input type="hidden" name="jobtype" value="1"/>
					<input type="hidden" name="action" value="add"/>
					<table>
						<tr>
							<th colspan="3" bgcolor="00ff00"><b>Cron Trigger 1</b>
							</th>
						</tr>
						<tr>
							<td nowrap>Trigger 名称：</td>
							<td><input type="text" name="triggerName" size="20">
							</td>
							<td>（必填）</td>
						</tr>
						<tr>
							<td nowrap>jobDetail：</td>
							<td><select name="jobDetail">
									<option value="jobDetail">jobDetail</option>
									<option value="jobDetail2">jobDetail2</option>
							</select>
							</td>
							<td>（jobDetail，Quartz默认为jobDetail）</td>
						</tr>
						<tr>
							<td nowrap>Cron表达式：</td>
							<td><input type="text" name="cronExpression" size="20">
							</td>
							<td>（必填，Cron表达式(如"0/10 * * ? * *
								*"，每10秒中执行调试一次)，对使用者要求比较，要会写Cron表达式，实际项目中不适用）</td>
						</tr>
						<tr>
							<td colspan="3"><input type="submit" value="添加Trigger">
							</td>
						</tr>
					</table>
				</form>

				<br>

				<form id="myForm3"
					action="${pageContext.request.contextPath}/schedule/service"
					method="post">
					<input type="hidden" name="jobtype" value="2"/>
					<input type="hidden" name="action" value="add"/>
					<table>
						<tr>
							<th colspan="4" bgcolor="00ff00" widthd="100%"><b>Cron
									Trigger 2</b>
							</th>
						</tr>
						<tr>
							<td nowrap>Trigger 名称：</td>
							<td colspan="3"><input type="text" name="triggerName"
								size="20">（必填）</td>
						</tr>
						<tr>
							<td nowrap>每</td>
							<td nowrap><input type="text" name="val" size="5">
							</td>
							<td nowrap><select name="selType">
									<option value="second">秒</option>
									<option value="minute">分</option>
							</select>
							</td>
							<td>执行一次（必填，范围 0-59）
							<td>
						</tr>
						<tr>
							<td colspan="4"><input type="submit" value="添加Trigger">
							</td>
						</tr>
					</table>
				</form>

				<br>

				<table>
					<tr>
						<th bgcolor="00ff00"><b>Cron Trigger ...</b>
						</th>
					</tr>
					<tr>
						<td>Cron
							Trigger功能比较强大，Cron表达式写法多种多样，我没有找到一个比较通用的方式去转换所有Cron表达式，
							在此只列起一几种比较简单的情况，希望能起到一个抛砖引玉作用，更多具体情况请参加相关文档。如果你有什么 好的方式也请告知，互相学习。</td>
					</tr>
				</table>
				<!-- 内容结束 -->
	 </div>
  </div>
  </body>
</html>

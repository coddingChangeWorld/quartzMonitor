<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'schedule.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="./components/layui/css/layui.css" media="all">
<%-- <script type="text/javascript" src='<c:url value="/components/jquery/jquery-1.7.2.min.js"/>'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/job.js"></script> --%>
</head>

<body>
	<!-- 内容开始 -->
	<p>
		<a
			href="${pageContext.request.contextPath}/schedule/jobList">调度任务监控管理</a>
	</p>
	<%-- <p><a href="${pageContext.request.contextPath}/nba/teamList">球队列表</a></p> --%>
	<form class="layui-form" id="myForm1"
		action="${pageContext.request.contextPath}/schedule/service"
		method="post">
		<input type="hidden" name="jobtype" value="0" /> <input type="hidden"
			name="action" value="add" />
		<b style="display:block; width:100%; padding:5px;background-color:00ff00;text-align:center;margin:5px 0px;">Simple Trigger</b>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">Trigger名称</label>
				<div class="layui-input-inline">
					<input type="text" size="20" name="p_triggerName"
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="width:200px;">
				<label class="layui-form-label">Trigger分组</label>
				<div class="layui-input-block">
					<select name="p_triggerGroup" lay-filter="aihao">
						<option value="0">defaulf</option>
						<option value="1" selected="">行政组</option>
						<option value="2">财务组</option>
						<option value="3">开发组</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">开始时间</label>
				<div class="layui-input-block">
					<input type="text" name="p_startTime" id="p_startTime"
						autocomplete="off" class="layui-input">
						<i class="layui-icon" style="display: inline-block;position:absolute;top:-2;right:0;font-size: 36px;">&#xe637;</i>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">结束时间</label>
				<div class="layui-input-block">
					<input type="text" name="p_endTime" id="p_endTime"
						autocomplete="off" class="layui-input">
						<i class="layui-icon" style="display: inline-block;position:absolute;top:-2;right:0;font-size: 36px;">&#xe637;</i>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">执行次数</label>
				<div class="layui-input-inline">
					<input type="text" size="10"
						p_repeatCount"
						lay-verify="required" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">次（表示Trigger启动后执行多少次结束，不填写执行一次）</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">执行间隔</label>
				<div class="layui-input-inline">
					<input type="text" size="10"
						p_repeatInterval"
						lay-verify="required" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">秒（表示Trigger间隔多长时间执行一次，不填写前后两次执行没有时间间隔，直到任务结束）</div>
			</div>
		</div>
		<div class="layui-form-item">
			<button class="layui-btn" lay-submit="" lay-filter="demo2">添加Trigger</button>
		</div>
	</form>
	<br>

	<form class="layui-form" id="myForm2"
		action="${pageContext.request.contextPath}/schedule/service"
		method="post">
		<input type="hidden" name="jobtype" value="1" /> <input type="hidden"
			name="action" value="add" />
		<b style="display:block; width:100%; padding:5px;background-color:00ff00;text-align:center;margin:5px 0px;">Cron Trigger 1</b>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">Trigger名称</label>
				<div class="layui-input-inline">
					<input type="text" size="20" name="triggerName"
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline" style="width:240px;">
				<label class="layui-form-label">jobDetail</label>
				<div class="layui-input-block">
					<select name="jobDetail" lay-filter="aihao">
						<option value="jobDetail" selected="">数据采集</option>
						<option value="jobDetail2">结果采集</option>
					</select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">Cron表达式</label>
				<div class="layui-input-inline">
					<input type="text" size="10"
						p_repeatInterval"
						lay-verify="required" autocomplete="off"
						class="layui-input" name="cronExpression">
				</div>
				<div class="layui-form-mid layui-word-aux">（必填，Cron表达式(如"0/10 * * ? * *
					*"，每10秒中执行调试一次)，对使用者要求比较，要会写Cron表达式，实际项目中不适用）</div>
			</div>
		</div>
		<div class="layui-form-item">
			<button class="layui-btn" lay-submit="" lay-filter="demo2">添加Trigger</button>
		</div>
	</form>

	<br>

	<form class="layui-form" id="myForm3"
		action="${pageContext.request.contextPath}/schedule/service"
		method="post">
		<input type="hidden" name="jobtype" value="2" /> <input type="hidden"
			name="action" value="add" />
	<b style="display:block; width:100%; padding:5px;background-color:00ff00;text-align:center;margin:5px 0px;">Cron Trigger 2</b>
	<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">Trigger名称</label>
				<div class="layui-input-inline">
					<input type="text" size="20" name="triggerName"
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">每</label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" size="5" name="val"
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 100px;">
					<select name="selType" lay-filter="aihao">
						<option value="second" selected="">秒</option>
						<option value="minute">分</option>
					</select>
				</div>
				<div class="layui-form-mid layui-word-aux">执行一次（必填，范围 0-59）</div>
			</div>
	</div>
	<div class="layui-form-item">
			<button class="layui-btn" lay-submit="" lay-filter="demo2">添加Trigger</button>
		</div>
	</form>

	<br>
	<div class="layui-form-item">
			Cron Trigger功能比较强大，Cron表达式写法多种多样，我没有找到一个比较通用的方式去转换所有Cron表达式，
				在此只列起一几种比较简单的情况，希望能起到一个抛砖引玉作用，更多具体情况请参加相关文档。如果你有什么 好的方式也请告知，互相学习。</td>
		</div>
	<!-- 内容结束 -->

	<script src="./components/layui/layui.js"></script>
	<script>
		layui.use([ 'form', 'layedit', 'laydate' ],
						function() {
							var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

							//日期
							laydate.render({
								elem : '#p_startTime'
							});
							laydate.render({
								elem : '#p_endTime'
							});

							//创建一个编辑器
							var editIndex = layedit.build('LAY_demo_editor');

							//自定义验证规则
							form.verify({
								title : function(value) {
									if (value.length < 5) {
										return '标题至少得5个字符啊';
									}
								},
								pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
								content : function(value) {
									layedit.sync(editIndex);
								}
							});

							//监听指定开关
							form.on('switch(switchTest)', function(data) {
								layer.msg('开关checked：'
										+ (this.checked ? 'true' : 'false'), {
									offset : '6px'
								});
								layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF',
										data.othis)
							});

							//监听提交
							form.on('submit(demo1)', function(data) {
								layer.alert(JSON.stringify(data.field), {
									title : '最终的提交信息'
								})
								return false;
							});

						});
	</script>

</body>
</html>

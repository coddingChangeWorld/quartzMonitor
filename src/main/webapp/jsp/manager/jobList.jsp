<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=urf-8">
<title>调度任务列表</title>
<style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/colour.css');</style>
<style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/template.css');</style>
<script type="text/javascript" src='<c:url value="/components/jquery/jquery-1.7.2.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/components/jquery/jquery.json-2.3.js"/>'></script>

<link rel="stylesheet" href='<c:url value="/components/layui/css/layui.css"/>' media="all">
<script type="text/javascript">
	function doCmd(state, triggerName, group, triggerState) {

		if (state == 'pause' && triggerState == 'PAUSED') {
			alert("该Trigger己经暂停！");
			return;
		}

		if (state == 'resume' && triggerState != 'PAUSED') {
			alert("该Trigger正在运行中！");
			return;
		}

		//客户端两次编码，服务端再解码，否测中文乱码 
		triggerName = encodeURIComponent(encodeURIComponent(triggerName));
		group = encodeURIComponent(encodeURIComponent(group));
		$
				.ajax({
					url : '${pageContext.request.contextPath}/schedule/service?jobtype=200&action='
							+ state
							+ '&triggerName='
							+ triggerName
							+ '&group='
							+ group,
					type : 'post',
					//dataType: 'xml',
					// timeout: 3000,
					error : function() {
						alert("执行失败！");
					},
					success : function(obj) {
						if (obj.code == 200) {
							alert("执行成功！");
							window.location.reload();
						} else {
							alert("执行失败！");
						}
					}
				});
	}
</script>
</head>
<body>

<div class="wrapper">
		<p>
			<a href="${pageContext.request.contextPath}/schedule/main">添加调度任务</a>
		</p>
		<table class="layui-table" lay-data="{height:315, url:'<c:url value="/schedule/query"/>', page:true, id:'test',height: 'full-25'}" lay-filter="test" id="test">
        <thead>
            <tr>
                <th lay-data="{field:'display_name', width:150, sort: true}">Trigger名称</th>
                <th lay-data="{field:'next_fire_time', width:160}">下次执行时间</th>
                <th lay-data="{field:'prev_fire_time', width:160, sort: true}">上次执行时间</th>
                <th lay-data="{field:'PRIORITY', width:100}">优先级</th>
                <th lay-data="{field:'statu', width:120}">Trigger 状态</th>
                <th lay-data="{field:'TRIGGER_TYPE', width:90, sort: true}">Trigger 类型</th>
                <th lay-data="{field:'start_time', width:160, sort: true}">开始时间</th>
                <th lay-data="{field:'end_time', width:160}">结束时间</th>
                <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}">操作</th>
            </tr>
        </thead>
    </table>
   <script type="text/html" id="barDemo"> 
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" onclick="doCmd('pause','{{ d.TRIGGER_NAME }}','{{ d.TRIGGER_GROUP }}','{{ d.TRIGGER_STATE }}')">暂停</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit" onclick="doCmd('resume','{{ d.TRIGGER_NAME }}','{{ d.TRIGGER_GROUP }}','{{ d.TRIGGER_STATE }}')">恢复</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="doCmd('remove','{{ d.TRIGGER_NAME }}','{{ d.TRIGGER_GROUP }}','{{ d.TRIGGER_STATE }}')">删除</a>
</script>
  </div>

	<div class="wrapper">
		
		<%-- <div id="content" class="container_16 clearfix">
			<p>
				<a href="${pageContext.request.contextPath}/manager/index">添加调度任务</a>
			</p>
			<table border="1" width="960px" style="table-layout:fixed;">
				<tr>
					<th nowrap>Trigger 名称</th>
					<!-- <th nowrap>Trigger 分组</th> -->
					<th nowrap style="width: 100px;">下次执行时间</th>
					<th nowrap style="width: 100px;">上次执行时间</th>
					<th nowrap>优先级</th>
					<th nowrap>Trigger 状态</th>
					<th nowrap>Trigger 类型</th>
					<th nowrap style="width: 100px;">开始时间</th>
					<th nowrap style="width: 100px;">结束时间</th>
					<th nowrap>动作命令</th>
				</tr>
				<c:forEach var="map" items="${list}">
					<tr>
						<td nowrap>${map.display_name}</td>
						<!-- <td nowrap>${map.trigger_group}</td> -->
						<td nowrap>${map.next_fire_time}</td>
						<td nowrap>${map.prev_fire_time}</td>
						<td nowrap>${map.priority}</td>
						<td nowrap>${map.statu}</td>
						<td nowrap>${map.trigger_type}</td>
						<td nowrap>${map.start_time}</td>
						<td nowrap>${map.end_time}</td>
						<td nowrap><input type="button" id="pause" value="暂停"
							onclick="doCmd('pause','${map.trigger_name}','${map.trigger_group}','${map.trigger_state}')">
							<input type="button" id="resume" value="恢复"
							onclick="doCmd('resume','${map.trigger_name}','${map.trigger_group}','${map.trigger_state}')">
							<input type="button" id="remove" value="删除"
							onclick="doCmd('remove','${map.trigger_name}','${map.trigger_group}','${map.trigger_state}')">
						</td>
					</tr>
				</c:forEach>
			</table>
		</div> --%>
	</div>
	
	<script src="../components/layui/layui.js"></script>
    <script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=urf-8">
<title>调度任务列表</title>
<link rel="stylesheet" href='<c:url value="/components/layui/css/layui.css"/>' media="all">
<script src="../components/layui/layui.js"></script>
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
					<th lay-data="{fixed: 'right', width:230, align:'center', toolbar: '#barDemo'}">操作</th>
				</tr>
			</thead>
		</table>
		<script type="text/html" id="barDemo"> 
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" onclick="doCmd('pause','{{ d.TRIGGER_NAME }}','{{ d.TRIGGER_GROUP }}','{{ d.TRIGGER_STATE }}')">暂停</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit" onclick="doCmd('resume','{{ d.TRIGGER_NAME }}','{{ d.TRIGGER_GROUP }}','{{ d.TRIGGER_STATE }}')">恢复</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="doCmd('remove','{{ d.TRIGGER_NAME }}','{{ d.TRIGGER_GROUP }}','{{ d.TRIGGER_STATE }}')">删除</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="editCron('{{ d.TRIGGER_NAME }}','{{ d.TRIGGER_GROUP }}','{{ d.TRIGGER_STATE }}','{{ d.cron_expression }}')">修改</a>
</script>
	</div>
	<div class="wrapper">
	</div>
	<script>
		layui.use(['table','jquery','layer','laytpl'], function() {
			var table = layui.table,layer = layui.layer;
			window.$ = layui.jquery;
			window.active = {
		    reload: function(){
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		      });
		    }
		  };
		});
		//console.log($(".layui-table").attr("id"));
	</script>
	<script type="text/javascript">
	function editCron(triggerName, group, triggerState,cronPression){
		var cronIndex=top.layer.open({
			      type: 2,
			      title: '编辑定时器',
			      shadeClose: true,
			      shade: 0.6,
			      maxmin: true, //开启最大化最小化按钮
			      area: ['893px', 'auto'],
			      content: '<c:url value="/jsp/cron.jsp"/>',
			      btn: ['保存'],
			      yes: function(index, layero){
			      	 var cron = layero.find("iframe")[0].contentWindow.getCron();
				     $.ajax({
						url : '${pageContext.request.contextPath}/schedule/modifyCron',
						type : 'post',
						data:{'triggerName':triggerName,'cronPression':cron,'group':group},
						//dataType: 'xml',
						// timeout: 3000,
						error : function() {
							alert("执行失败！");
						},
						success : function(obj) {
							top.layer.close(index);
							if (obj.code == 200) {
								active['reload'] ? active['reload'].call(this) : '';
							} else {
								alert("执行失败！");
							}
						}
					});
				  },
				  success:function(layero,index){
				  	top.layer.iframeAuto(index);
				    layero.find("iframe")[0].contentWindow.initCron(cronPression);
				  }
			    });
		
	}
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
		$.ajax({
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
</body>
</html>

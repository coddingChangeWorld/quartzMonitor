<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>球队列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--<style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/960.css');</style>-->
	<style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/colour.css');</style> 
	 <style type="text/css">@import url('${pageContext.request.contextPath}/styles/admin/template.css');</style>
	<style type="text/css">@import url('${pageContext.request.contextPath}/components/calendar/skins/aqua/theme.css');</style>
	<link rel="stylesheet" href="./components/layui-src/css/layui.css" media="all">
	<script type="text/javascript" src='<c:url value="/components/jquery/jquery-1.7.2.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/components/jquery/jquery.json-2.3.js"/>'></script>
	<%-- <script type="text/javascript" src='<c:url value="/components/jquery/jquery.form.js"/>'></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/calendar/calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/calendar/calendar-setup.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/components/calendar/lang/calendar-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/job.js"></script>  --%>
	<style>
	.demoTable{
		margin:10px 0;
	}
	</style>
  </head>
  
  <body>
  <div class="wrapper">
  	<div class="layui-form-item demoTable">
				
		  <div class="layui-inline">
		  	<label class="layui-form-label">搜索ID：</label>
		  	<div class="layui-input-inline">
		    <input class="layui-input" name="id" id="game_id" autocomplete="off">
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
		  <button class="layui-btn" data-type="reload">搜索</button>
		<button class="layui-btn" data-type="weekCollect">周数据采集</button>
		<button class="layui-btn" data-type="dayCollect">天数据采集</button>
	</div>
  	<table class="layui-table"  lay-filter="gameList">
        <thead>
            <tr>
                <th lay-data="{field:'id', width:50, sort: true}">ID</th>
                <!-- <th lay-data="{field:'arenaName', width:150}">分区</th> -->
                <th lay-data="{field:'awayName', width:100, sort: true}">客队</th>
                <th lay-data="{field:'awayScore', width:100, sort: true}">客队得分</th>
                <th lay-data="{field:'awayTeamId', width:120}">awayTeamId</th>
                <th lay-data="{field:'gameId', width:120}">gameId</th>
                <th lay-data="{field:'gameTime', width:180, sort: true}">比赛时间</th>
                <th lay-data="{field:'homeName', width:100, sort: true}">主队</th>
                <th lay-data="{field:'homeScore', width:80}">主队得分</th>
                <th lay-data="{field:'homeTeamId', width:120, sort: true}">homeTeamId</th>
                <th lay-data="{field:'status', width:100, sort: true,templet: '#statusTpl'}">状态</th>
                <th lay-data="{field:'updateTime', width:200, sort: true}">跟新时间</th>
            </tr>
        </thead>
    </table>
  </div>
  <script src="./components/layui-src/layui.js"></script>
  <script type="text/html" id="statusTpl">
	{{# if(d.status=='1'){ }}
		未开始
	{{#  }else if(d.status=='2'){ }}
		进行中
	{{#  }else if(d.status=='3'){ }}
		已结束
	{{#  } }}
   </script> 
    <script>

        layui.use(['table','laydate','layer'], function() {
            var table = layui.table, laydate = layui.laydate;

            //日期
            var d = new Date();
			laydate.render({
				elem : '#p_startTime',
				value:new Date()
			});
			var T = 24*60*60*1000,endWeekTime = d.getTime()+(7*T);
			var endWeek = new Date(endWeekTime);
			laydate.render({
				elem : '#p_endTime',
						//lay.digit(d.getMonth() + 1)
						value: endWeek.getFullYear() + '-' + lay.digit(endWeek.getMonth() + 1) + '-' + lay.digit(endWeek.getDate()+7)
			});

		var $ = layui.$, active = {
			reload: function(){
		      //执行重载
		      table.reload('gameList', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            id: gameId.val(),
		            startTime:startTime.val(),
		            endTime:endTime.val()
		        }
		        ,response:{
					  statusCode:200
				  }
		      });
		    },
			weekCollect:function () {
				layer.msg('hello');
			},
			dayCollect:function () {
				layer.msg('hello');
			}
		  };
			var gameId = $('#game_id'),
					startTime = $('#p_startTime'),
					endTime = $('#p_endTime'),
					tableOptions = {
						url: './nba/gameList', //请求地址
						method: 'POST', //方式
						id: 'gameList', //生成 Layui table 的标识 id，必须提供，用于后文刷新操作，笔者该处出过问题
						page: true, //是否分页
						height:315,
						where: {
							id: gameId.val(),
							startTime:startTime.val(),
							endTime:endTime.val()}, //请求后端接口的条件，该处就是条件错误点，按照官方给出的代码示例，原先写成了 where: { key : { type: "all" } }，结果并不是我想的那样，如此写，key 将是后端的一个类作为参数，里面有 type 属性，如果误以为 key 是 Layui 提供的格式，那就大错特错了
						response: { //定义后端 json 格式，详细参见官方文档
							statusCode: '200', //状态字段成功值
						}
					};
			table.init('gameList', tableOptions);
		  $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
        });

        console.log(gameId.val());
		console.log(startTime.val());
        
        
    </script>
  </body>
</html>

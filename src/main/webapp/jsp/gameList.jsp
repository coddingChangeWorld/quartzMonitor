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
	<link rel="stylesheet" href="./components/layui/css/layui.css" media="all">
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
		    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
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
	</div>
  	<table class="layui-table" lay-data="{height:315, url:'./nba/gameList', page:true, id:'test',height: 'full-25'}" lay-filter="test" id="test">
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
  <script src="./components/layui/layui.js"></script>
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
        layui.use(['table','laydate'], function() {
            var table = layui.table, laydate = layui.laydate;
            //日期
            var d = new Date();
			laydate.render({
				elem : '#p_startTime',
				value:new Date()
			});
			laydate.render({
				elem : '#p_endTime',
				//lay.digit(d.getMonth() + 1)
				value: d.getFullYear() + '-' + lay.digit(d.getMonth() + 1) + '-' + lay.digit(d.getDate()+7)
			});
            var $ = layui.$, active = {
		    reload: function(){
		     var demoReload = $('#demoReload');
		     var startTime = $('#p_startTime');
		     var endTime = $('#p_endTime');
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            id: demoReload.val(),
		            startTime:startTime.val(),
		            endTime:endTime.val()
		        }
		      });
		    }
		  };
		  $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
        });
        
        
    </script>
  </body>
</html>

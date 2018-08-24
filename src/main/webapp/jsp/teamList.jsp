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
	
  </head>
  
  <body>
  <div class="wrapper">
  	<table class="layui-table" lay-data="{height:315, url:'./nba/teamList', page:true, id:'test',height: 'full-25'}" lay-filter="test" id="test">
        <thead>
            <tr>
                <th lay-data="{field:'id', width:150, sort: true}">ID</th>
                <th lay-data="{field:'abbr', width:150}">英文简称</th>
                <th lay-data="{field:'city', width:150, sort: true}">城市</th>
                <th lay-data="{field:'cityEn', width:100}">英文城市</th>
                <th lay-data="{field:'code', width:177}">编码</th>
                <th lay-data="{field:'conference', width:90, sort: true}">分区-英文</th>
                <th lay-data="{field:'displayConference', width:90, sort: true}">分区-中文</th>
                <th lay-data="{field:'displayAbbr', width:80}">名称</th>
                <th lay-data="{field:'nameEn', width:135, sort: true}">名称-英文</th>
                <th lay-data="{field:'division', width:135, sort: true}">分区</th>
            </tr>
        </thead>
    </table>
  </div>
    <%-- <table  border="1">
            <tr>
            	<th nowrap>ID</th>
                <th nowrap>英文简称</th>
                <th nowrap>城市</th>
                <th nowrap>英文城市</th>
                <th nowrap>编码</th>
                <th nowrap>分区-英文</th>
                <th nowrap>分区-中文</th>
                <th nowrap>名称</th>
                <th nowrap>名称-英文</th>
                <th nowrap>分区</th>
            </tr>
            <c:forEach var="map" items="${page.result}">
                <tr>
                    <td nowrap>${map.id}</td>
                    <td nowrap>${map.abbr}</td>
                    <td nowrap>${map.city}</td>
                    <td nowrap>${map.cityEn}</td>
                    <td nowrap>${map.code}</td>
                    <td nowrap>${map.conference}</td>
                    <td nowrap>${map.displayConference}</td>
                    <td nowrap>${map.displayAbbr}</td>
                    <td nowrap>${map.nameEn}</td>
                    <td nowrap>${map.division}</td>
                </tr>
            </c:forEach>
        </table>
        <div id="page" class="pagination">
        	<a href="#" class="first" data-action="first">《《</a>
        	 <a href="#" class="previous" data-action="previous">《</a>
        	  <input readonly="readonly" data-max-page="40" type="text"> 
        	  <a href="#" class="next" data-action="next">》</a> 
        	  <a href="#" class="last" data-action="last">》》</a>
        </div> --%>
  <script src="./components/layui/layui.js"></script>
    <script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>main</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="./components/layui/css/layui.css" media="all" />
<script type="text/javascript">
	Date.prototype.Format = function(fmt) { //
		var o = {
			"M+" : this.getMonth() + 1, //Month
			"d+" : this.getDate(), //Day
			"h+" : this.getHours(), //Hour
			"m+" : this.getMinutes(), //Minute
			"s+" : this.getSeconds(), //Second
			"q+" : Math.floor((this.getMonth() + 3) / 3), //Season
			"S" : this.getMilliseconds()
		//millesecond
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};
</script>
</head>

<body class="wrapper">
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div id="view"></div>
	</div>
	<script id="gameItem" type="text/html">
	{{# 
     var TimestampFmt=function(timestamp){
					    var time=new Date(timestamp).Format("yyyy-MM-dd hh:mm");
						return time;
					};
  }}

<ul class="layui-timeline">
{{#  layui.each(d.data, function(index, ditem){ }}
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">{{ ditem.date }}</h3>
	<div id="view" class="layui-row layui-col-space15">
      	{{#  layui.each(ditem.data, function(index, item){ }}
  		<div class="layui-col-md5">
					<div class="layui-card">
						<div class="layui-card-header">NBA未来一周赛程数据  {{ TimestampFmt(item.gameTime) }}</div>
						<div class="layui-card-body" style="margin:auto;">
							<div class="item-vtm">
							<span class="vtm-abbr">{{ item.awayName }}</span>
							</div>
							<div class="scoreboard-row1">
								<span class="winner">{{ item.awayScore }}</span>
								<span class="boxscore-dash">-</span>
								<span>{{ item.homeScore }}</span>
							</div>
							<div class="item-htm">
								<span class="vtm-abbr">{{ item.homeName }}</span>
							</div>
							<div class="item-info">
								<div>{{#  if(item.syncStatus == 1){ }}
										<span style="color:red">未同步</span>
 								{{#  }else if(item.syncStatus == 2){ }}
								 		<span style="color:green">已同步</span>
								{{#  } }}
								</div>
							</div>
							<div class="item-operate">
							   {{#  if(item.syncStatus == 1){ }}
										<button pid="{{ item.id }}" id="syncAdd" class="layui-btn layui-btn-sm">同步</button>
 								{{#  }else if(item.syncStatus == 2){ }}
								 		<button pid="{{ item.id }}" id="syncUp" class="layui-btn layui-btn-sm">更新</button>
								{{#  } }}
							</div>
							<!--<div class="clear"></div>-->
						</div>
					</div>
				</div>
	  {{#  }); }}
	</div>
    </div>
  </li>
{{#  }); }}
</ul>
	
 	  {{#  if(d.data.length === 0){ }}
    		未来一周没有比赛
  		{{#  } }} 
	</script>
	<script src="./components/layui/layui.js"></script>

	<script>
		var message;
		layui.config({
			base : './components/',
			version : '1.0.1'
		}).use([ 'app', 'message', 'jquery', 'layer', 'laytpl' ], function() {
			var app = layui.app,
				$ = layui.jquery,
				layer = layui.layer,
				laytpl = layui.laytpl;
			/* laytpl.TimestampFmt = function(timestamp){
			    var time=new Date(timestamp).format("dd/MM/yyyy hh:mm");
				return time;
			} */
			$.ajax({
				type : "GET",
				url : "./nba/weekProfileList",
				//data: {username:$("#username").val(), content:$("#content").val()},
				dataType : "json",
				success : function(data) {
					var getTpl = gameItem.innerHTML,
						view = document.getElementById('view');
					//alert(JSON.stringify(data.list));
					layui.laytpl(getTpl).render(data, function(html) {
						view.innerHTML = html;
						$('#syncAdd').on('click', function() {
							$.ajax({
								type : "GET",
								url : "./nba/weekProfileList",
								//data: {username:$("#username").val(), content:$("#content").val()},
								dataType : "json",
								success : function(data) {}
							});
						});
						$('#syncUp').on('click', function() {});
					});
				}
			});
		});
	</script>
</body>
</html>

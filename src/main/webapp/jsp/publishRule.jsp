<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="./components/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="./styles/app.css" media="all" />
<script>
var id="16";
</script>
</head>
<body>
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		<ul class="layui-tab-title">
			<li class="layui-this">winorlose</li>
			<li>bigorsmall</li>
		</ul>
		<div class="layui-tab-content" style="height: 100px;">
			<div class="layui-tab-item layui-show">
				<form action="" class="layui-form">
					<div class="layui-col-lg3 layui-col-md12">
						<div class="margin-left-20 margin-top-20">
							<!--侧边栏-->
							<div class="publish">
								<div class="layui-form-item">
									<label class="layui-form-label layui-form-label-custom"> <i class="iconfont icon-chenggong icon-p"></i> 置顶 </label>
									<div class="layui-input-block">
										<input type="checkbox" lay-skin="switch" name="isTop" lay-text="是|否" value="1">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label layui-form-label-custom"> <i class="iconfont icon-chenggong icon-p"></i> 文章来源 </label>
									<div class="layui-input-block">
										<input type="radio" name="isOriginal" value="0" title="转载" checked> <input type="radio" name="isOriginal" value="1" title="原创">
									</div>
								</div>
								<div class="layui-form-item a-source">
									<label class="layui-form-label layui-form-label-custom"> <i class="iconfont icon-chenggong icon-p"></i> 来源地址 </label>
									<div class="layui-input-block">
										<input type="text" name="sourceUrl" placeholder="转载时该项必填*" autocomplete="off" class="layui-input" style="width: 90%">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label layui-form-label-custom"> <i class="iconfont icon-chenggong icon-p"></i> 状态 </label>
									<div class="layui-input-block">
										<input type="radio" name="status" value="0" title="保存草稿" checked> <input type="radio" name="status" value="1" title="马上发布">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label layui-form-label-custom"> <i class="iconfont icon-chenggong icon-p"></i> 评论 </label>
									<div class="layui-input-block">
										<input type="checkbox" lay-skin="switch" name="isComment" lay-text="开启|关闭" value="1" checked="true">
									</div>
								</div>

								<hr>
								<div class="publish-btn">
									<a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-sm" lay-submit lay-filter="add">发布</a>
									<!--<a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-primary"  lay-submit lay-filter="look">预览</a>-->
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="layui-tab-item" id="bigorsmallView">
				
			</div>
		</div>
	</div>
	<script id="winorlose" type="text/html">
	
	</script>
	<script id="bigorsmall" type="text/html">
		<form action="" class="layui-form">
					<div class="layui-col-lg3 layui-col-md12">
						<div class="margin-left-20 margin-top-20">
							<!--侧边栏-->
							<div class="publish">
								<div class="layui-form-item">
									<img type="image/svg+xml" src="./image/nba/{{ d.homeAbbr }}_logo.svg" class="team-img">
									<img type="image/svg+xml" src="./image/nba/{{ d.awayAbbr }}_logo.svg" class="team-img clear">
								</div>
								
								<div class="publish-btn">
									<a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-sm" lay-submit lay-filter="add">发布</a>
								</div>
							</div>
						</div>
					</div>
				</form>
	</script>
	<script src="./components/layui/layui.js"></script>
	<script>
		var table,$,layer,form,flow,upload,element,util,laytpl;
		layui.use([ 'table', 'jquery', 'layer', 'form', 'flow', 'upload','element', 'util','laytpl'], function() {
			table = layui.table;
			$ = layui.$;
			layer = layui.layer;
			form = layui.form;
			flow = layui.flow;
			upload = layui.upload;
			element = layui.element;
			util = layui.util;
			laytpl = layui.laytpl;
			$.ajax({
			             type: "GET",
			             url: "./nba/WebProfile",
			             data: {id:id},
			             dataType: "json",
			             success: function(data){
		             			var getTpl = bigorsmall.innerHTML,view = document.getElementById('bigorsmallView');
		             			//alert(JSON.stringify(data.list));
								layui.laytpl(getTpl).render(data.data, function(html){
								  view.innerHTML = html;
								});
								
								/* $('#publish').on('click', function(){
								    layer.open({
								      type: 2,
								      title: 'iframe父子操作',
								      maxmin: false,
								      shadeClose: true, //点击遮罩关闭层
								      area : ['800px' , '520px'],
								      offset: [ 
								          $(window).height()-300
								          ,0
								        ],
								      content: './nba/rulePublish?id='+$(this).attr("pid")
								    });
								  }); */
		                      }
		         });
		});
	</script>
</body>
</html>

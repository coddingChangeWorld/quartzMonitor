var leftMenu={id:"side"};
var userInfo=;
(function ($,leftMenu) {
	var data={menus:[{className:"order",text:"我的订单",href:""},
	   	          {className:"favorite",text:"我的收藏",href:""},
		          {className:"expert",text:"我关注的专家",href:""},
		          {className:"match",text:"我关注的赛事",href:""},
		          {className:"coupon",text:"我的方案",href:"./nba/rulePublish"},
		          {className:"hongdou",text:"我的红豆",href:"",extend:true}]};
	$.fn.leftHtmlParse=function(){
		if(typeof(currentMenuIndex)!="undefined" && currentMenuIndex!="" && currentMenuIndex!=null){
			data.menus[currentMenuIndex].className+=" current";
		}
		data.userInfo=userInfo;
		data.login=login;
		var tpl = $.ajax({
            url: "./static/tpl/left.txt",  //注意:这里路径要取全
            async: false
        })
		//var tpl = $("#tpl").html();
		//预编译模板
	    var template = Handlebars.compile(tpl.responseText);
	    //匹配json内容
	    var html = template(data);
	  //输入模板
	    $("#"+leftMenu.id).html(html);
	}
})(jQuery,leftMenu);

//登录前检查用户名是否存在
function CheckUserNameExist() {
	if ($("#UserName").val()) {
		$.ajax({
			url: '/quartzMonitor/user/user_exist',
			data: {
				UserName: $("#UserName").val()
			},
			type: 'post',
			dataType: 'json',
			success: function(Json) {
				if (Json.code == 200) {
					$("#UserName").addClass("inputnotice");
				} else {
					$("#UserName").removeClass("inputnotice");
				}
			}
		});
	} else {
		$("#UserName").addClass("inputnotice");
	}
}
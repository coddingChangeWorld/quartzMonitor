var uId="cjdladmin";
var token="";
function getToken(){
	$.ajax( {  
	     url:'http://local.dev.netschina.com/security-center/api/token',// 跳转到 action  
	     data:{  
	              cId : "b80eab2a-d3d1-4e86-88a7-ddb35b0a0d01",  
	              uId : uId,  
	              key : "30092dbf-1634-47ca-9b2a-5d18db8aebae" 
	     },  
	     type:'get',  
	     cache:false,  
	     dataType:'json',  
	     success:function(data) {  
	    	 var message=objToJson(data);
	    	 $("#message").html(message);
	         if(data.code =="200" ){  
	        	 token=data.data.token;
	        	 alert(token);
	        	 getaccessPath("30092dbf-1634-47ca-9b2a-5d18db8aebae","b80eab2a-d3d1-4e86-88a7-ddb35b0a0d01");
	         } 
	      },  
	      error : function() {  
	           // view("异常！");  
	           alert("异常！");  
	      }  
	 });
}

function getaccessPath(key,cId){
	$.ajax( {  
	     url:'http://local.dev.netschina.com/security-center/api/accessPath',// 跳转到 action  
	     data:{  
	              cId : "b80eab2a-d3d1-4e86-88a7-ddb35b0a0d01",
	              key : "30092dbf-1634-47ca-9b2a-5d18db8aebae" 
	     },  
	     type:'get',  
	     cache:false,  
	     dataType:'json',  
	     success:function(data) {  
	    	 var message=objToJson(data);
	    	 $("#message").append("<br/>"+message);
	         if(data.code =="200" ){  
	        	 var accessPath=data.data.accessPath;
	        	 $("#message").append("<br/>"+accessPath);
	        	 location.href=accessPath+"?uId="+uId+"&token="+token;
	         } 
	      },  
	      error : function() {  
	           // view("异常！");  
	           alert("异常！");  
	      }  
	 });
}



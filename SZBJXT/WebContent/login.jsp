<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
     <title>登入界面</title>      
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<script type="text/javascript" src="/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/js/config.js"></script>
  </head>
  
  <body>
  <%--  	<a href="<%=basePath%>user/getAllUser.htm">进入用户管理页</a></h5>
   	<a href="<%=basePath%>user/index.htm">进入项目管理页</a></h5> --%>
  <div> 	
   <form class="box login">
	<fieldset class="boxBody">
	  <label>用户名</label>
	  <input type="text" id="user_name" tabindex="1" placeholder="UserName" >
	  <label><a href="#" class="rLink" tabindex="5"> </a>密码</label>
	  <input type="password" id="password" tabindex="2" placeholder="PassWord">
	</fieldset>
	<footer>
	  <label><input type="checkbox" tabindex="3">Keep me logged in</label>
	  <input type="button" class="btnLogin" value="Login" tabindex="4" onclick="Login();">
	</footer>
</form>
	</div>
	<script type="text/javascript">
	function Login(){
		var user_name = $("#user_name").val();
		var password = $("#password").val();
		if(user_name==""){
				alert("用户名不能为空");	
				return;
		}else if(password==""){
				alert("密码不能为空");
				return;
		}
		var id ={user_name:user_name,password:password};
		console.log(id);
		$.ajax({
			type : "post",
			datatype : "json",
			url : commonUrl + "/user/Login.htm",
			data :id,
			async : true,
			cache : false,
			success : function(data) {	
				console.log(data);
				 if("success"==data.result){
					 location.href="/user/index.htm";
				 }else{
					 alert(data.result);
				 }
				 			
			},
			error : function(xhr, status, err) {
			}
		});		
	}
	
	   $(document).keypress(function(e) {  
		    // 回车键事件  
		       if(e.which == 13) {  
		    	   	var user_name = $("#user_name").val();
		   			var password = $("#password").val();
		   			if(user_name==""){
		   				alert("用户名不能为空");		   				
		   			}else if(password==""){
		   				alert("密码不能为空");
		   			}else{
		   				Login();
		   			}
		       }  
		   }); 
	 
	</script>
  </body>
</html>

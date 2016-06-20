<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="x-ua-compatible" content="ie=7"/>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/user_login.css'/>">
<title>insert title</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.3/jquery.min.js"></script>
<script>
function login()
{
	var jsonData ={
		"userName": $("#j_username").val(),
		"password": $("#j_password").val(),
	};
	$.ajax({method: "POST",
	    url: "teacher/login",
	    headers: {'Content-type':'application/json;charset=UTF-8'},
	    data:JSON.stringify(jsonData),
	   	dataType: "json",
		success:function(data, status) {
			location.href="view/teacher/manage.jsp?teacherName="+data.data.teacherName;
		},
	    error:function(){  
            alert("error")
        }  
	});
}

</script>
</head>

<body >

<div class="wrapper">
     <div class="logo"></div> 
     <div class="content">
	      <div class="login">
		       <form action="<c:url value="teacher/login"/>" method="post">
			         <div><input type="text" id="j_username"  value="" onMouseOver="this.className='user2'" onMouseOut="this.className='user1'" /></div>
			         <div><input type="password" id="j_password" value="" class="password1" onMouseOver="this.className='password2'" onMouseOut="this.className='password1'"/></div>
					 <div><input  type="button" class="btn" value="登录 Login" onclick="login()"/></div>
			   </form>
		  </div>	 
	 </div>
	 <div class="footer">Copyright(C) &nbsp 2013-2014 &nbsp  inputChinese </div>
</div>

</body>
</html>

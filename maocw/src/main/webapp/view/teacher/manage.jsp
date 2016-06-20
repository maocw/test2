<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/resource.jsp" %>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/js/bootstrap-3.3.5-dist/css/bootstrap.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/js/bootstrap-3.3.5-dist/css/bootstrap.min.css'/>" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-3.3.5-dist/js/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-3.3.5-dist/js/bootstrap-table.min.js"></script>

<title>教师个人课表</title>
<script type="text/javascript">
	var baseUrl = '<%=path %>';
	var teacher_name = getParam('teacherName');
	function getParam(paramName) {
	    paramValue = "";
	    isFound = false;
	    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
	        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&");
	        i = 0;
	        while (i < arrSource.length && !isFound) {
	            if (arrSource[i].indexOf("=") > 0) {
	                if (arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase()) {
	                    paramValue = arrSource[i].split("=")[1];
	                    isFound = true;
	                }
	            }
	            i++;
	        }
	    }
	    return paramValue;
	}
</script>
</head>
<body>

 <table id="lesson_table" class="table table-bordered" >
    <tr>
    	<td colspan="2"></td>
    	<td >星期一</td>
    	<td >星期二</td>
    	<td >星期三</td>
    	<td >星期四</td>
    	<td >星期五</td>
    	<td >星期六</td>
    	<td >星期日</td>
    </tr>
    <tr id="tr1">
    	<td rowspan="2" width="20px">上午</td><td>第12节</td>
  		<td id="11"></td><td id="21"></td><td id="31"></td>
    	<td id="41"></td><td id="51"></td><td id="61"></td><td id="71"></td>
    </tr>
    <tr>
    	<td width="20px">第34节</td>
    	<td id="12"></td><td id="22"></td><td id="32"></td>
    	<td id="42"></td><td id="52"></td><td id="62"></td><td id="72"></td>
    </tr>
    <tr><td colspan="2">中午</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr>
    	<td rowspan="2">下午</td><td>第56节</td>
    	<td id="13"></td><td id="23"></td><td id="33"></td>
    	<td id="43"></td><td id="53"></td><td id="63"></td><td id="73"></td>
    </tr>
    <tr>
    	<td>第78节</td>
    	<td id="14"></td><td ></td><td id="34"></td>
    	<td id="44"></td><td id="54"></td><td id="64"></td><td id="74"></td>
    </tr>
    <tr>
    	<td colspan="2">晚上</td>
    	<td id="15"></td><td id="25"></td><td id="35">
    	</td><td id="45"></td><td id="55"></td><td id="65"></td><td id="75"></td>
    </tr>
 </table>
<script type="text/javascript">
	var json_name ={
		"teacherName": teacher_name
	};
	window.onload=function(){	
				
		if(teacher_name==null || teacher_name==''){
			location.href=baseUrl+"/login.jsp";
		}		
		$.ajax({method: "POST",
		    url: baseUrl+"/teacher/getLesson?teacherName="+teacher_name,
		    headers: {'Content-type':'application/json;charset=UTF-8'},
		   	dataType: "json",
			success:function(data, status) {
				var list = new Array();
				list = data.rows;
				for(var i=0;i<list.length;i++){
					var message = list[i].lessoName + ' in ' + list[i].space + ' with ' + list[i].teacherName;
					var serial =  list[i].lessonTime + list[i].serial;
					var node = document.getElementById(serial);
					node.setAttribute("width", "150px");
					var nodeA = document.createElement("a");
					nodeA.setAttribute("href", '<%=path %>' + "/view/attend/attend.jsp?lessoName="+list[i].lessoName);
					var textnode=document.createTextNode(message);
					nodeA.appendChild(textnode);
					node.appendChild(nodeA);
				}
			},
		    error:function(){  
	            alert("error");
	        }  
		});
	}
	
</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!-- 公共资源CSS,JS  -->
<!--Css -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/js/jquery-easyui/themes/default/easyui.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/js/jquery-easyui/themes/icon.css'/>" />
<!-- ** Javascript ** -->
<script type="text/javascript"
	src="<c:url value='/js/jquery-easyui/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-easyui/jquery.edatagrid.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-easyui/locale/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript" 
	src="<c:url value='/js/marquee/marquee.js'/>"></script>
<script type="text/javascript" 
	src="<c:url value='/js/commons/winShow.js'/>"></script>
<script type="text/javascript" 
	src="<c:url value='/js/commons/paramDefine.js'/>"></script>
<script type="text/javascript" 
	src="<c:url value='/js/commons/methodDefine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-easyui/sessionTimeout.js'/>"></script>

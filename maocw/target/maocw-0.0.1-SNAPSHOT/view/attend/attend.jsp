<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/view/resource.jsp" %>
<%
	String lessoName = request.getParameter("lessoName");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<c:url value='/css/main.css'/>" />
<head>
<style type="text/css">
.textField{text-align:right;}
.table-new{font-size:12px;}
a {color:#07519A; text-decoration: none}
a:hover {color: #FF6600; text-decoration: underline}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课堂签到管理</title>	
</head>

<body>
	<div class="easyui-layout" fit="true" id='person_layout'>
		<div id="searchbar" class="nav-top" region="north" style="height:70px; padding:5px 5px;" title="搜索"
			data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false">
			<tr>
				<label>学号：</label>
				<input type="text" value="" id="s_id" class="search_text" />      
				<label>姓名：</label>
				<input type="text" value="" id="s_name" class="search_text" />
				<label>班级：</label>
				<input type="text" value="" id="s_class" class="search_text" />
				</tr>
			<tr>
				<input type="text" value="" id="s_nums" class="search_text" />        				
				<label>次以上缺课（迟到）：</label>
				<input type="button" class="search-btn" value="搜索" onMouseOver="this.className='on-search-btn'" onMouseOut="this.className='search-btn'" onfocus="this.blur()" onclick="javascript:searchA()"/>
				<input type="button" class="reset-btn" value="重置" onMouseOver="this.className='on-reset-btn'" onMouseOut="this.className='reset-btn'" onfocus="this.blur()" onclick="javascript:reset()" />
				</tr>	
 						
		</div>
		<div region="center" border="false">
			<table id="list-table" toolbar="#toolbar" 
				pagination="true" idField="id" fit="true" rownumbers="true"
				fitColumns="false" singleSelect="false" ctrlSelect="false" >				
			</table>
		</div>
	</div>	
	<div id="insertPersonWin" class="easyui-window" title="学生信息" closed="true" align="center" style="display:none;width:600px;height:auto;top:50px;" buttons="#dlg-buttons" modal="true">  
		<form id="insertPersonForm"  method="post" name="CheckFirst"> 
		 <input id="id" name="id" type="hidden"/>
    	 <table border="0" class="table-new"  cellpadding="4"  cellspacing="4">
    	 	<tr>   	 		
    	 		<td class="textField" >
    	 			学号：
    	 		</td >
    	 		<td>
    	 			<input type="text"  name="number" id="number" />
    	 		</td> 	 		
    	 	</tr>	
    	 	<tr>   	 		
    	 		<td class="textField" >
    	 			姓名：
    	 		</td >
    	 		<td>
    	 			<input type="text"  name="name" id="name" />
    	 		</td> 	 		
    	 	</tr>
    	 	<tr>
    	 		<td class="textField" >
    	 			班级：
    	 		</td>
    	 		<td>
    	 			<input type="text"  name="gradeClass" id="gradeClass"  />
    	 		</td> 	 		
    	 	</tr>
    	 	<tr>
    	 		<td class="textField" >
    	 			签到状态：
    	 		</td>
    	 		<td>
    	 			<input type="text"  name="status" id="status"  />
    	 		</td> 	 		
    	 	</tr>
    	 	
    	 </table>
    	 </form>
    	 <div style="padding:10px;text-align:center;">  
                <a href="#" class="easyui-linkbutton" icon="icon-ok"onclick="saveOrder()">保存</a>  
                <a href="#" class="easyui-linkbutton" icon="icon-cancel"onclick="closeWin()">取消</a>  
         </div>   	
	</div>
	<div id="QRImage" class="easyui-window" title="扫码签到" closed="true" align="center" width:600px;height:auto;" >
		<div><img class="QR-img" 
				src="<c:url value="/CodeServlet"/>" 
		</div>
	</div>  
<script type="text/javascript">
	var baseurl = '<%=path %>';
</script>

<script type="text/javascript" >
	
   function clearSpecDiv(){
	    $("#spec").val('');
    }

   $.extend($.fn.validatebox.defaults.rules, {
	   s_nums: {// 验证
       	validator: function (value) { 		
				if(value > 0){
					return false;
				}
        		return true;
        	},
        	message: '输入数据不能小于0'
    	}
		
	}); 
		   
   function searchA(){	
	  var number = $('#s_id').val();
	  var name = $('#s_name').val();
	  var gradeClass = $('#s_class').val();
	  var absenTimes = $('#s_nums').val();
      $('#list-table').datagrid('reload',{
    	 number:number,
         name:name,
         gradeClass:gradeClass,
         absenTimes:absenTimes,
       });
     };	
 
    function reset() {
      document.getElementById("s_id").value="";
      document.getElementById("s_name").value="";
      document.getElementById("s_class").value="";
      document.getElementById("s_nums").value="";
    };

	function refreshData(){
		reset();
		$('#list-table').datagrid('load');
		$('#list-table').datagrid('clearSelections');
	};


	$(function() {		
		var lessoName = '<%=lessoName%>';
		if(lessoName=='null' || lessoName==''){
			location.href=baseurl+"/login.jsp";
		}
		reset();
		$('#insertPersonWin').show();
		//设置text须要验证           
        /*人员信息录入的表单*/
		var form = document.CheckFirst;
		var $form = $(form);   	
		
		var count = 1;		
		var handler = function(){
			if(count>4){
				cleartime();
			}
			$.ajax({
				type:"PUT",
	        	url:baseurl+"/student/selfHeart",    	        	
	    		success:function(data) {
	    			if(data.message!=null){
	    				alert(data.message+" logOut");
	    			}	        	
	    		},
			});
			//count++;
		} 
		var timer = setInterval( handler , 32*1000);
		
		function cleartime(){
			clearInterval(timer);
		}
		
		
		$.ajax({
			type:"GET",
        	url:baseurl+"/teacher/initialize?lessoName="+'<%=lessoName%>',    	        	
    	    contentType: "application/json",
    	   	dataType: "json",
    		success:function(data) {
    		
    		},
    	    error:function(){  
    	    	$.messager.show({ title: '初始化失败', msg : 'error！' });
            }  
        });
				  					
		var url = '<c:url value="/student/search"/>';
		
		$('#list-table').datagrid({
			url:url+"?lessoName="+'<%=lessoName%>',
			loadMsg:'加载中，请稍等。。。',
			columns:[[
			          	{field:'id',checkbox:true},
						{field:'number',title: '学号',sortable:true,width:"100"},												
						{field:'name',title: '姓名',sortable:true,width:"100"},
						{field:'gradeClass',title: '班级',sortable:true,width:"100"},	
						{field:'absenTimes',title: '缺课次数',sortable:false,width:"70"},	
						{field:'rate', title: '缺课率',sortable:false ,width:"70"},
						{field:'status', title: '本节课是否签到',sortable:true ,width:"100"},
						{field:'totalTimes', title: '上课总数',sortable:false ,width:"70"}
						
	            	]],
           	toolbar : [ {text: '生成二维码',iconCls: 'icon-add',handler: addOrder},
            	{text: '编辑学生信息',iconCls: 'icon-edit',handler: editOrder},
            	{text: '删除数据',iconCls: 'icon-delete',handler: delOrder},
            	{text: '点击刷新',iconCls: 'icon-reload',handler: refreshData},
            	{text: '导出EXCEL表格到桌面',iconCls: 'icon-export',handler: templateDownload},
				{text:'退出下课',iconCls:'icon-clear',handler:exit},
            	{text:'开启wifi',iconCls:'icon-start',handler:start},
            	{text:'关闭wifi',iconCls:'icon-end',handler:end}
            	],
            onSortColumn: function(sort,order){
			    dataGridSort = sort;
			    dataGridOrder = order;
	        }		                  
		});	

	});


	
 /***************************************** 订单的增删改查 开始  **************************************************/
			function closeWin(){
				$('.validatebox-tip').remove();
		    	$('#insertPersonWin').window('close');
		    }
			 
			function addOrder(){		    	
		    	//$('#insertPersonForm')[0].reset();
		    	//$('#id','#insertPersonForm').val('');
		    	//$('#insertPersonWin').window('open');
		    	$('#QRImage').window('open');
		    	
		    }
			
			function exit(){
				var studentDtos =$('#list-table').edatagrid('getSelections');
				$.ajax({
    				type:"POST",
    	        	url:baseurl+"/teacher/logOut",    	        	
    	    	    data:JSON.stringify(studentDtos),
    	    	    contentType: "application/json",
    	    	   	dataType: "json",
    	    		success:function(data, status) {
    	    			$.messager.show({ title: '提示信息', msg : '退出成功' });
    	    			location.href=baseurl+"/login.jsp";
    	    		},
    	    	    error:function(){  
    	    	    	$.messager.show({ title: '提示信息', msg : 'error！' });
    	            }  
    	        });
			}
			
			function start(){
				$.ajax({
					type:'POST',
					url:baseurl+"/teacher/startWifi",
					contentType: "application/json",
    	    	   	dataType: "json",
    	    	   	success:function(data,status){
    	    	   		alert("开启成功！");
    	    	   	},
					error:function(){
						$.message.show({ title: '提示信息', msg : '开启失败' });
					}
				});
			}
			
			function end(){
				$.ajax({
					type:'POST',
					url:baseurl+"/teacher/closeWifi",
					contentType: "application/json",
    	    	   	dataType: "json",
    	    	   	success:function(data,status){
    	    	   		$.message.show({ title: '提示信息', msg : '关闭成功' });
    	    	   	},
					error:function(){
						$.message.show({ title: '提示信息', msg : '关闭失败' });
					}
				});
			}
			 
			 function editOrder(){

				 var order =$('#list-table').edatagrid('getSelections');
				 if(order.length==1){
				 	var ida = order[0].number;
				 	var url = '<c:url value="/student/load"/>';
	    			$('#insertPersonForm')[0].reset();
	    			$('#insertPersonWin').window('open');
	    			$.ajax({
	    	        	url:url+"?number="+ida,
	    	        	dataType: "json",
	    	        	success:function(data,status){   	        			
	       	        		$('#insertPersonForm').form('load',data.data);	       	            		       	        		
	    	        	},
	    				error:function(){
	    					alert("error");
	    				}
	    	        });
	    		}else{
	    			$.messager.alert('提示','请选择一项进行修改!','info');
	    			$('#list-table').edatagrid('clearSelections');
	    		}
			 }
			 
			 function saveOrder(){
				var url = '<c:url value="/student/insert"/>';
		    	$('#insertPersonForm').form('submit',{ 
		    		method:"POST",
	                url: url,  
	                dataType: "json",
	                async:false,
	                success: function(result){
	                	closeWin();
	                	var result = eval('(' + result + ')');  // change the JSON string to javascript object   
	                	if(result.success){
	                		$.messager.show({ title: '提示信息', msg : '保存成功' });
	                		$('#list-table').edatagrid('reload');
	                    	$('#list-table').datagrid('clearSelections');	 
	                	}else{
	                		$.messager.show({ title: '提示信息', msg : '保存失败' });                 	
	                	}        							
	                }
	            });
		    }	
			
			 
			 //Del
				function delOrder() {
					
					var rows = $('#list-table').datagrid('getSelections');
					if (rows.length > 0) {
					    var ids = '';
						for ( var i = 0; i < rows.length; i++) {
									ids += rows[i].number + ',';
						}
						ids = ids.substring(0, ids.length - 1);
						$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
							if (r) {
								$.ajax({
					                type: "POST",
					                dataType: "json",
					                url: '<c:url value="/student/delete"/>',
					                data: {"studentIds":ids},
					                success: function(returnData) {
					                	if(returnData.success){				          
					                		$.messager.show({ title: '提示信息', msg : '删除成功' });			                		
					                		$('#list-table').datagrid('reload');
											$('#list-table').datagrid('clearSelections');		                		 
						                }else{
						                	$.messager.show({ title: '提示信息', msg : '删除失败' }); 
						                }				                				                														                	
					                } 
					            });
							}
						});
					} else {
						$.messager.alert('消息', '请选择要删除的数据!','info');
					}
				}		
			 
			 
		function templateDownload(){
			var order =$('#list-table').edatagrid('getSelections');
			if(order.length>=1){
				var studentDtos =$('#list-table').edatagrid('getSelections');
    			$.ajax({
    				type:"PUT",
    	        	url:baseurl+"/teacher/exportExcel",    	        	
    	    	    data:JSON.stringify(studentDtos),
    	    	    contentType: "application/json",
    	    	   	dataType: "json",
    	    		success:function(data, status) {
    	    			$.messager.show({ title: '提示信息', msg : '导出到桌面成功' });
    	    			$('#list-table').datagrid('clearSelections');	 
    	    		},
    	    	    error:function(){  
    	    	    	$.messager.show({ title: '提示信息', msg : '导出到桌面失败' });
    	            }  
    	        });
			}else{
				$.messager.alert('提示','请选择至少一项数据导出到桌面!','info');
    			$('#list-table').edatagrid('clearSelections');
			}

		}			 
				    			
	</script>
</body>
</html>
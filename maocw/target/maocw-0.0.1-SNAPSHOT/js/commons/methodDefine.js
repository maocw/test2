
function createCombox(id,required,valueField,textField,url){
	$('#'+id).combobox({
        required: required,
	 	valueField: valueField,
	  	textField: textField,
	  	url: url,
	    panelHeight: 100
	});	
}

function createCombox(id,required,valueField,textField,url,height,childId,childRequired,childValueField,childTextField,childUrl,paramName){
	
	if(typeof(height) == 'undefined'){
		height = 100;	
	}
	return $('#'+id).combobox({
		method:'get',
        required: required,
	 	valueField: valueField,
	  	textField: textField,
	  	url: url,
	    panelHeight: height,
	    onSelect:function(value){
			if(paramName!=''&& paramName!=null && value!=null){
				var murl = childUrl + paramName +'='+ value.id; 				
				$('#'+childId).combobox({
					required: childRequired,
				    valueField: childValueField,
			        textField: childTextField,
			        method:'get',
				    url: murl,
					panelHeight: height
				});	 
		    }
			   	 																																																												
		}
	});
}

function createComboxForWidth(id,required,valueField,textField,url,height,width){
	$('#'+id).combobox({
        required: required,
	 	valueField: valueField,
	  	textField: textField,
	  	url: url,
	  	width: width,
	    panelHeight: height
	});	
}

//用于删除成功后修改其刷新的页面
function changeCurrPageForDataGrid(rows,grid){
	var options = grid.datagrid('getPager').data("pagination").options;  		                			                			                	            	
	if(rows.length == (options.total % options.pageSize) && options.pageNumber > 1){			                	
		options.pageNumber = options.pageNumber - 1; 			                						    
        gird.datagrid('getPager').pagination('select',options.pageNumber);					              
	}
}

$.extend($.fn.validatebox.defaults.rules, { 
	
	maxLength : { // 判断最大长度
		validator : function(value, param) {
			return value.length <= param[0];
		},
		message : '最多只能输入 {0} 个字(包括空格、标点符号)。'
	},
	idcard : {// 验证身份证
		validator : function(value) {
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
		},
		message : '身份证号码格式不正确'
	}, 
	uniqueValue : {
		validator : function(value) {	
		alert('value=='+value);
			$.ajax({
	            type: "post",
	            dataType: "json",
	            async:false,
	            url: '<c:url value="/bus/product/tt"/>',
	            success: function(result) {
	            	return result.success;
	            }
	        });
			
		},
		message : '该名称已经存在,请换一个！'
	},
	intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    date: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '清输入合适的日期格式'
    },
    integer: {// 验证正整数 
        validator: function (value) {
    	 	var type = /^[0-9]*[1-9][0-9]*$/;
    	 	var re = new RegExp(type);
            return value.match(re) != null;
        },
        message: '请输入正整数'
    },
	fingerprintId : {// 指纹序列号
		validator : function(value) {
			return /^[1-9]\d{6}$/i.test(value);
		},
		message : '身份证号码格式不正确'
	}                  
});


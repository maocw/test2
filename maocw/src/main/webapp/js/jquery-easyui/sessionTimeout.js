$(function(){
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        cache: false,
        complete: function(XHR, TS){
            var resText = XHR.responseText;
            var sessionstatus = XHR.getResponseHeader("sessionstatus");
            if (911 == XHR.status && "timeout" == sessionstatus) {
                // ���ȷ������ת
            alert("session Timeout!");
            window.location.replace("index.jsp");
            }
        }, error:function(XMLHttpRequest, textStatus, errorThrown){      	
        	if(XMLHttpRequest.status=='200'){
        		// alert("session Timeout!");
        		window.location.replace("/vdi/view/main.jsp"); 
        	}				                	
         } 
    });
});
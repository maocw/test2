var orderPerson = [{id:'小明',text:'小明',"selected":true},{id:'小王',text:'小王'}];
var customer = [{id:'小明',text:'小明',"selected":true},{id:'小王',text:'小王'}];
var goodType = [
    {id:'1',text:'新式浴霸'},
    {id:'2',text:'老式浴霸'},
    {id:'3',text:'LED灯'},
    {id:'4',text:'普通节能灯'},
    {id:'5',text:'换气扇'},
    {id:'6',text:'铝扣板'},
    {id:'7',text:'龙骨'},
    {id:'8',text:'边角'},		    
    {id:'9',text:'吊杆'},
    {id:'10',text:'玉石板'},
  	{id:'11',text:'其他'}
];

var setWay = [{id:'0',text:'付现',"selected":true},{id:'1',text:'一个月后付款'},{id:'2',text:'两个月后付款'},{id:'3',text:'其他'}];
var isGet = [{id:false,text:'否',"selected":true},{id:true,text:'是'}];
var factory = [
	{id:'凯盛电器',text:'凯盛电器'},
	{id:'美福电器',text:'美福电器'},
	{id:'龙豪板业',text:'龙豪板业'},
	{id:'美佳英板业',text:'美佳英板业'},
	{id:'凯之兰集成吊顶',text:'凯之兰集成吊顶'},
	{id:'其他',text:'其他'}
];
var province = [{id:'安徽',text:'安徽'},{id:'浙江',text:'浙江'},{id:'江苏',text:'江苏'}];
var city = [{id:'马鞍山',text:'马鞍山'},{id:'嘉兴',text:'嘉兴'},{id:'苏州',text:'苏州'},{id:'芜湖',text:'芜湖'}];

var unitValue =[
    {id:'个',text:'个'},
	{id:'根',text:'根'},
	{id:'台',text:'台'},
	{id:'片',text:'片'},
	{id:'套',text:'套'},
	{id:'米',text:'米'},
	{id:'其他',text:'其他'}
];
//
var goodTypeForSearch = [
	{id:'',text:'无'},              
    {id:'1',text:'新式浴霸'},
    {id:'2',text:'老式浴霸'},
    {id:'3',text:'LED灯'},
    {id:'4',text:'普通节能灯'},
    {id:'5',text:'换气扇'},
    {id:'6',text:'铝扣板'},
    {id:'7',text:'龙骨'},
    {id:'8',text:'边角'},		    
    {id:'9',text:'吊杆'},
    {id:'10',text:'玉石板'},
  	{id:'11',text:'其他'}
];



var factoryForSearch = [
	{id:'',text:'无'}, 
   	{id:'凯盛电器',text:'凯盛电器'},
   	{id:'美福电器',text:'美福电器'},
   	{id:'龙豪板业',text:'龙豪板业'},
   	{id:'美佳英板业',text:'美佳英板业'},
   	{id:'凯之兰集成吊顶',text:'凯之兰集成吊顶'},
   	{id:'其他',text:'其他'}
];


//货物类型转换
var goodTypeChange = function(value,row,index){
	 switch(value){
        case "1":return "新式浴霸";
        case "2":return "老式浴霸";
        case "3":return "LED灯";
        case "4":return "普通节能灯";
        case "5":return "换气扇";
        case "6":return "铝扣板";
        case "7":return "龙骨";
        case "8":return "边角";
        case "9":return "吊杆";
        case "10":return "玉石板";
        default:return '其他';
     }
}

//结算方式转换
var setWayChange = function(value,row,index){
	if(value=='0'){
		return '付现';
	}else if(value=='1'){
		return '一个月后付款';
	}else if(value=='2'){
		return '两个月后付款';
	}else{
		return '其他';
	}
	
}

//是否付款转换
var hasGetChange = function(value,row,index){
	return value?'是':'否';
}

//验收状况
var approvalStatus = function(value,row,index){
	return value?'未验收':'已验收';
}

//显示所有内容
var showAllItem = function(value,rec){  
    var val = '';
    if(value!=null && value!=''){
		val = value;
		if(val.length>10){
			return '<a name="show" onMouseover="showDiv1(event,'+ "'" + val + "'" +')" onMouseout="hideDiv()" >' + val + '</a>';  
		}else{
			return '<font>' + val + '</font>';  
		}		
	}								    
	}  
		
var isCloseTable = false;

var selectedOrderId = '';

//自定义map
var map = new Object();    
map.put = function (key,value){    
    var s = "map." + key + ' = "' + value + '";';    
    eval(s);    
}    
map.get = function(key){    
    var v = eval("map." + key + ";");    
    return v;    
}    
   
map.keySet = function(){    
    var keySets = new Array();    
    for(key in map){    
        if(!(typeof(map[key])=="function")){    
            keySets.push(key);    
        }    
    }    
    return keySets;    
} 

map.put("isGet","hasGet");  
map.put("customer","customer"); 
map.put("type","typeName");
map.put("method","methodName");
map.put("completeTime","completeTime");

	   	
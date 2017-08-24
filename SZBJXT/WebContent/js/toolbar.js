var url="";
$(document).ready(function(){
    getzhizaolist();    				  	
});
function addzhizao(){
	 $('#dlg').dialog('open').dialog('setTitle','添加织造信息');  
	  $('#zhizaoForm').form('clear');  
	  url = '/zhizao/Addzhizao.htm'; 
}
function searchtoolbar(){
	$('#zhizaotable').datagrid('load',{
    	cloth_id: $('#cloth_id').val(),
    	model: $('#model').val(),
	});  
}
function savezhizao(){	 
	 var caozuo_type=  $("input[name='state']:checked").val();
	 if(!("0"==caozuo_type||"1"==caozuo_type)){
		 alert("请选择具体操作状态，保存或者提交");
		 return;
	 }
	var id =$("#zhizaoForm").serialize();
	console.log(id);
	$.ajax({
		type : "post",
		datatype : "json",
		//url : commonUrl + "/user/AddUser.htm",
		url : commonUrl +url,
		data :id,
		async : true,
		cache : false,
		success : function(data) {			 
			console.log(data);
			 if("success"==data.result){
				 alert("保存成功");
				 $('#dlg').dialog('close');
				 $('#zhizaotable').datagrid('reload'); // 刷新用户数据  
			 }else{
				 alert("保存失败");
			 }
			 			
		},
		error : function(xhr, status, err) {
		}
	});
	
}
function getzhizaolist()
{      
	$('#zhizaotable').datagrid({   
		url : commonUrl + "/zhizao/getAllzhizaolist.htm",
	    pagination:true,   
	    pageSize:20,   
	    pageNumber:1,  
	    singleSelect: true,
	    rownumbers:true  ,
	    toolbar:$("#tb")
	   
	});    
		 			 
}

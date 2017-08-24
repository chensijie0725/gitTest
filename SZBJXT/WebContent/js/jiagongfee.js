var url="";
$(document).ready(function(){
    getjiagongfeelist();    				  	
});
function savejiagongfee(){	 
	 var caozuo_type=  $("input[name='state']:checked").val();
	 if(!("0"==caozuo_type||"1"==caozuo_type)){
		 alert("请选择具体操作状态，保存或者提交");
		 return;
	 }
	var id =$("#jiagongfeeForm").serialize();
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
				 $('#jiagongfeetable').datagrid('reload'); // 刷新用户数据  
			 }else{
				 alert("保存失败");
			 }
			 			
		},
		error : function(xhr, status, err) {
		}
	});
	
}
function getjiagongfeelist()
{      
	$('#jiagongfeetable').datagrid({   
		url : commonUrl + "/jiagongfee/getAlljiagongfeelist.htm",
	    pagination:true,   
	    pageSize:10,   
	    pageNumber:1,  
	    singleSelect: true,
	    rownumbers:true  ,
	    toolbar: [{  
            id: 'btnAdd',  
            text: "添加",  
            iconCls: 'icon-add',  
            handler: function () {    
            	alert("该功能暂未开放");
            	/*  $('#dlg').dialog('open').dialog('setTitle','添加用户');  
            	  $('#UserForm').form('clear');  
            	   url = '/user/AddUser.htm'; */  
            }  
        }, {  
            id: 'btnEdit',  
            text: "修改",  
            iconCls: 'icon-edit',  
            handler: function () {                   
                var row = $('#jiagongfeetable').datagrid('getSelected'); 
                if (row){  
                    $('#dlg').dialog('open').dialog('setTitle','修改纱织信息');  
                    $('#jiagongfeeForm').form('load',row); 
                    $("#id").attr("value",row.id);                                  
                    url = '/jiagongfee/Updjiagongfee.htm'; 
                }  
            } 
        },{  
                id: 'btnEdit',  
                text: "删除",  
                iconCls: 'icon-remove',  
                handler: function () {   
                	alert("该功能暂未开放");
                    /*var row = $('#shazhitable').datagrid('getSelected');  
                    if (row){  
                        $.messager.confirm('Confirm','你确定要进行删除操作么？',function(r){  
                            if (r){    
                           var id ={id:row.id};
                           console.log(id);
                           	$.ajax({
                            		type : "post",
                            		datatype : "json",
                            		url : commonUrl +"/user/DelUser.htm",
                            		data :id,
                            		async : true,
                            		cache : false,
                            		success : function(data) {			 
                            			console.log(data);
                            			 if("success"==data.result){
                            				 alert("删除成功");
                            				 $('#dlg').dialog('close');
                            				 $('#usertable').datagrid('reload'); // 刷新用户数据  
                            			 }else{
                            				 alert("删除失败");
                            			 }                            			 			
                            		},
                            		error : function(xhr, status, err) {
                            		}
                            	});	                           	                            	                         	
                            }  
                        });  
                    }               */                                                                                
                } 

        },{  
            id: 'btnSearch',  
            text: "查询",  
            iconCls: 'icon-search',  
            handler: function () {    
                $('#jiagongfeetable').datagrid('load',{
                	fee_type: $('#fee_type').val(),
                	jiagong_type: $('#jiagong_type').val(),
            	});                                              
            } 
        }  ,
        {
            text: '<span>费用类型:&nbsp;&nbsp;</span> <select id="fee_type" name="fee_type">'+
            		'<option value="">全部</option>'+
            		'<option value="1">特殊加工费用</option>'+
            		'<option value="2">印花费用</option>'+
            		'<option value="3">外加工费用</option>'
            		+'</select>'
        } ,
        {
            text: '<span>加工类型:</span> <input id="jiagong_type"  >'
        }      
        ],  
	});   
	
	 
	
	
	 
}

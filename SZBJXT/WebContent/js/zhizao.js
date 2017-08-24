var url="";
$(document).ready(function(){
    getzhizaolist();    				  	
});
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
	    toolbar: [{  
            id: 'btnAdd',  
            text: "添加",  
            iconCls: 'icon-add',  
            handler: function () {    
            	  $('#dlg').dialog('open').dialog('setTitle','添加织造信息');  
            	  $('#zhizaoForm').form('clear');  
            	   url = '/zhizao/Addzhizao.htm';   
            }  
        }, {  
            id: 'btnEdit',  
            text: "修改",  
            iconCls: 'icon-edit',  
            handler: function () {                   
                var row = $('#zhizaotable').datagrid('getSelected'); 
                if (row){  
                    $('#dlg').dialog('open').dialog('setTitle','修改纱织信息');  
                    $('#zhizaoForm').form('load',row); 
                    $("#id").attr("value",row.id);                                  
                    url = '/zhizao/Updzhizao.htm'; 
                }  
            } 
        },{  
                id: 'btnEdit',  
                text: "删除",  
                iconCls: 'icon-remove',  
                handler: function () {   
                    var row = $('#zhizaotable').datagrid('getSelected');  
                    if (row){  
                        $.messager.confirm('Confirm','你确定要进行删除操作么？',function(r){  
                            if (r){    
                           var id ={id:row.id};
                           console.log(id);
                           	$.ajax({
                            		type : "post",
                            		datatype : "json",
                            		url : commonUrl +"/zhizao/Delzhizao.htm",
                            		data :id,
                            		async : true,
                            		cache : false,
                            		success : function(data) {			 
                            			console.log(data);
                            			 if("success"==data.result){
                            				 alert("删除成功");
                            				 $('#dlg').dialog('close');
                            				 $('#zhizaotable').datagrid('reload'); // 刷新用户数据  
                            			 }else{
                            				 alert("删除失败");
                            			 }                            			 			
                            		},
                            		error : function(xhr, status, err) {
                            		}
                            	});	                           	                            	                         	
                            }  
                        });  
                    }                                                                                             
                } 

        },
        {
            text: '<span>布号:</span> <input id="cloth_id"  >'
        },
        {
            text: '<span>机型:</span> <input id="model"  >'
        },
        {  
            id: 'btnSearch',  
            text: "查询",  
            iconCls: 'icon-search',  
            handler: function () {    
                $('#zhizaotable').datagrid('load',{
                	cloth_id: $('#cloth_id').val(),
                	model: $('#model').val(),
            	});                                              
            } 
        } 
        ],  
	});   
		 			 
}

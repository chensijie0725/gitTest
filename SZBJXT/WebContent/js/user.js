var url="";
$(document).ready(function(){	 
     getuserlist();    				  	
});
function saveUser(){	 
	var id =$("#UserForm").serialize();
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
				 $('#usertable').datagrid('reload'); // 刷新用户数据  
			 }else{
				 alert("保存失败");
			 }
			 			
		},
		error : function(xhr, status, err) {
		}
	});
	
}
function getuserlist()
{      
	
	$('#usertable').datagrid({   
		url : commonUrl + "/user/getAllUserlist.htm",
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
            	  $('#dlg').dialog('open').dialog('setTitle','添加用户');  
            	  $('#UserForm').form('clear');  
            	   url = '/user/AddUser.htm';   
            }  
        }, {  
            id: 'btnEdit',  
            text: "修改",  
            iconCls: 'icon-edit',  
            handler: function () {                   
                var row = $('#usertable').datagrid('getSelected'); 
                if (row){  
                    $('#dlg').dialog('open').dialog('setTitle','修改用户信息');  
                    $('#UserForm').form('load',row); 
                    $("#id").attr("value",row.id);
                    url = '/user/UpdUser.htm'; 
                }  
            } 
        },{  
                id: 'btnEdit',  
                text: "删除",  
                iconCls: 'icon-remove',  
                handler: function () {   
                    var row = $('#usertable').datagrid('getSelected');  
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
                    }                                                                                               
                } 

        },{  
            id: 'btnSearch',  
            text: "查询",  
            iconCls: 'icon-search',  
            handler: function () {    
                var user_name = $("#search_name").val();
                $('#usertable').datagrid('load',{
                  	search_name: $('#search_name').val(),
            	});                                              
            } 
        }  ,
        {
            text: '<span>用户名:</span> <input id="search_name"  >'
        }
        ],  
	});   
	
	 
	
	
	/*$('#usertable').datagrid('getPager').pagination({   
	    displayMsg:'当前显示从 [{from}] 到 [{to}] 共[{total}]条记录',   
	    onSelectPage : function(pPageIndex, pPageSize) {   
	        //改变opts.pageNumber和opts.pageSize的参数值，用于下次查询传给数据层查询指定页码的数据   
	        var gridOpts = $('#usertable').datagrid('options');   
	        gridOpts.pageNumber = pPageIndex;   
	        gridOpts.pageSize = pPageSize;     
	        //定义查询条件   
	        var queryCondition = {name:"世纪之光"};   
	        //异步获取数据到javascript对象，入参为查询条件和页码信息   
	        var oData = getAjaxDate("orderManageBuz","qryWorkOrderPaged",queryCondition,gridOpts);   
	        //使用loadDate方法加载Dao层返回的数据   
	        $('#usertable').datagrid('loadData',{"total" : oData.page.recordCount,"rows" : oData.data});   
	    }   
	});  */
     
}

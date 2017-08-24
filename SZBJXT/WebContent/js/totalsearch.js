var url="";
$(document).ready(function(){
    gettotalsearchfee();    				  	
});
function gettotalsearchfee()
{      
	$('#totalsearchfeetable').datagrid({   
		url : commonUrl + "/totalsearch/getAlltotalsearchfeelist.htm",
	    pagination:true,   
	    pageSize:10,   
	    pageNumber:1,  
	    singleSelect: true,
	    rownumbers:true  ,    
	    toolbar: [  
        
        {
            text: '<span>布号:</span> <input id="cloth_id"  >'
        }   ,
        {  
            id: 'btnSearch',  
            text: "查询",  
            iconCls: 'icon-search',  
            handler: function () {    
                $('#totalsearchfeetable').datagrid('load',{
                	cloth_id: $('#cloth_id').val(),
            	});                                              
            } 
        } 
        ],          
        onLoadSuccess:function(data){
        	 
        },
        onLoadError:function(data){
        	console.log(data.responseText); 
        	  var result = eval( "("+data.responseText+")" );
               $.messager.alert("提示",result.msgInfo);          
        }
	});   
	
	 
	
	
	 
}

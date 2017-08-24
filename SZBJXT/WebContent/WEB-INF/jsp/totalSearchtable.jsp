 <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>申州报价查询系统</title>
 <link rel="stylesheet" type="text/css" href="/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/css/wu.css" />
<link rel="stylesheet" type="text/css" href="/css/icon.css" />
<script type="text/javascript" src="/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/config.js"></script>
<script type="text/javascript" src="/js/totalsearch.js"></script>	 
</head>
<body>
 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
	<h2>报价查询界面</h2>
	<p>用户可在此进行对报价结果查询</p>
	<div style="margin:20px 0;"></div>
 
	<table id="totalsearchfeetable"  title="报价结果查询" style="width:1200px;height:650px">		 		 
		<thead>
			<tr>
				<th data-options="field:'cloth_id',width:100">布号</th>
				<th data-options="field:'pingming',width:100">品名</th>				 
				<th data-options="field:'ganghao',width:80,align:'right'">缸号</th>				
				<th data-options="field:'shazhi_fee',width:120,align:'right'">纱支费用</th>	
				<th data-options="field:'zhizao_fee',width:120,align:'right'">织造费用</th>	 				
				<th data-options="field:'rangzheng_fee',width:140,align:'right'">染整基本费用</th>
				<th data-options="field:'specialjg_fee',width:140,align:'right'">特殊加工费用</th>
				<th data-options="field:'yinhua_fee',width:80,align:'right'">印花费用</th>
				<th data-options="field:'waijiagong_fee',width:80,align:'right'">外加工费用</th>
				<th data-options="field:'total_fee',width:80,align:'right'">总价</th>
			</tr>
		</thead>			 
	</table>
 <h1>${errorInfo}</h1>
<div id="dlg" class="easyui-dialog" style="font-size:18px;width:500px;height:420px;padding:10px 20px" closed="true" buttons="#dlg-buttons">  
    <div class="ftitle">特殊加工信息</div>  
    <form id="jiagongfeeForm" method="post" action="" style="margin-top:10px;">  
    <input type="hidden" name="id" id="id" value="">     
        <div class="fitem">  
            <label>单价:&nbsp;&nbsp;</label>  
            <input name="danjia" id="danjia" class="easyui-validatebox" required="true">  
        </div> 
         <div class="fitem">  
            <label>损耗:&nbsp;&nbsp;&nbsp;&nbsp;</label>  
            <input name="sunhao" id="sunhao" class="easyui-validatebox" required="true">  
        </div>                            
        <div class="fitem">  
            <label>具体操作:</label>  
            <input name="state" type="radio" value="0"  required="true">提交  
            <input name="state" type="radio" value="1"  required="true" >保存
        </div>         
    </form>  
</div>  
<div id="dlg-buttons">  
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savejiagongfee()">save</a>  
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
</div>  
</body>
    <script type="text/javascript">
 
	</script>



</html>
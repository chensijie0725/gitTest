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
<script type="text/javascript" src="/js/user.js"></script>	 
</head>
<body>
 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
	<h2>用户管理信息</h2>
	<p>只有超管权限用户才可以对用户信息进行操作</p>
	<div style="margin:20px 0;"></div>
 
	<table id="usertable"   title="Basic DataGrid" style="width:850px;height:650px">
		 
		 
		<thead>
			<tr>
				<th data-options="field:'id',width:80">ID</th>
				<th data-options="field:'user_name',width:120">用户名</th>				 
				<th data-options="field:'password',width:120,align:'right'">密码</th>
				<th data-options="field:'power',width:120,align:'right'">是否超管</th>
			</tr>
		</thead>	 
	</table>
 
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">  
    <div class="ftitle">用户信息</div>  
    <form id="UserForm" method="post" action="">  
    <input type="hidden" name="id" id="id" value="">
        <div class="fitem">  
            <label>用户名:&nbsp;&nbsp;&nbsp;</label>  
            <input name="user_name" id="user_name" class="easyui-validatebox" required="true">  
        </div>  
        <div class="fitem">  
            <label>密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>  
            <input name="password" id="password" class="easyui-validatebox" required="true">  
        </div>  
        <div class="fitem">  
            <label>是否超管:</label>  
            <input name="power" type="radio" value="0">是  
            <input name="power" type="radio" value="1">否
        </div>         
    </form>  
</div>  
<div id="dlg-buttons">  
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>  
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
</div>  
</body>
    <script type="text/javascript">
 
	</script>



</html>
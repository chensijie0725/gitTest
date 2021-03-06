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
<script type="text/javascript" src="/js/shazhi.js"></script>	 
</head>
<body>
 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
	<h2>纱织价格管理信息</h2>
	<p>用户可在此进行对纱织信息的修改  注：保存状态并无实际生产效果，提交后才生效</p>
	<div style="margin:20px 0;"></div>
 
	<table id="shazhitable"   title="纱支价格信息" style="width:1280px;height:650px">		 		 
		<thead>
			<tr>
				<th data-options="field:'id',width:80">ID</th>
				<th data-options="field:'yuanliao_id',width:120">原料编号</th>				 
				<th data-options="field:'shazhi',width:160,align:'right'">纱支型号</th>
				<th data-options="field:'danjia',width:80,align:'right'">单价（元）</th>
				<th data-options="field:'sunhao',width:80,align:'right'">损耗（%）</th>
			<!-- 	<th data-options="field:'shijibili',width:100,align:'right'">实际比例（%）</th> -->
				<th data-options="field:'shazhi_price',width:100,align:'right'">纱支价格（元）</th>
				<th data-options="field:'sub_time',width:140,align:'right'">提交时间</th>
				<th data-options="field:'edit_time',width:140,align:'right'">修改时间</th>
				<th data-options="field:'sub_people',width:80,align:'right'">提交人</th>
				<th data-options="field:'edit_people',width:80,align:'right'">修改人</th>
				<th data-options="field:'state',width:80,align:'right'">记录状态</th>
			</tr>
		</thead>	 
	</table>
 
<div id="dlg" class="easyui-dialog" style="font-size:18px;width:500px;height:420px;padding:10px 20px" closed="true" buttons="#dlg-buttons">  
    <div class="ftitle">纱织信息</div>  
    <form id="shazhiForm" method="post" action="" style="margin-top:10px;">  
    <input type="hidden" name="id" id="id" value="">
        <div class="fitem">  
            <label>原料编号:&nbsp;&nbsp;</label>  
            <input name="yuanliao_id" id="yuanliao_id" class="easyui-validatebox" required="true">  
        </div>  
        <div class="fitem">  
            <label>纱织型号:&nbsp;&nbsp;</label>  
            <input name="shazhi" id="shazhi" class="easyui-validatebox" required="true">  
        </div>  
         <div class="fitem">  
            <label>单价:（元）&nbsp;&nbsp;&nbsp;&nbsp;</label>  
            <input name="danjia" id="danjia" class="easyui-validatebox" required="true">  
        </div> 
         <div class="fitem">  
            <label>损耗:（%）&nbsp;&nbsp;&nbsp;&nbsp;</label>  
            <input name="sunhao" id="sunhao" class="easyui-validatebox" required="true">  
        </div> 
       <!--   <div class="fitem">  
            <label>实际比例:（%）&nbsp;&nbsp;</label>  
            <input name="shijibili" id="shijibili" class="easyui-validatebox" required="true">  
        </div> 
          <div class="fitem">  
            <label>纱织价格:（元）&nbsp;&nbsp;</label>  
            <input name="shazhi_price" id="shazhi_price" class="easyui-validatebox" required="true">  
        </div>  -->
        
        <div class="fitem">  
            <label>具体操作:</label>  
            <input name="state" type="radio" value="0"  required="true">提交  
            <input name="state" type="radio" value="1"  required="true" >保存
        </div>         
    </form>  
</div>  
<div id="dlg-buttons">  
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveshazhi()">save</a>  
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
</div>  
</body>
    <script type="text/javascript">
 
	</script>



</html>
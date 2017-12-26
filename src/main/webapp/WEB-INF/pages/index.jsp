<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<!-- Bootstrap -->
<link href="${ctx}/static/embed/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/embed/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/DateUtil.js"></script>
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<title>Come on !This is a LuceneLearn Project ! Just work harder!</title>
</head>
<body>
  	<nav class="navbar navbar-default">
		<div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->
			    <div class="navbar-header">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <a class="navbar-brand" href="#">Bestest</a>
			    </div>
			
			      <form class="navbar-form navbar-left">
			        <div class="form-group">
			          <input type="text" id="name" class="form-control" placeholder="Name">
			          <input type="text" id="idCard" class="form-control" placeholder="idCard">
			          <input type="text" id="lkNoroom" class="form-control" placeholder="lkNoroom">
			          <input type="text" id="lkLtimeBefore" class="form-control" placeholder="lkLtimeBefore" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00'})" >
			          <input type="text" id="lkLtime" class="form-control" placeholder="lkLtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 23:59:59'})" >
			          <input type="text" id="lkEtime" class="form-control" placeholder="lkEtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00'})">
			          <input type="text" id="lkEtimeAfter" class="form-control" placeholder="lkEtimeAfter" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 23:59:59'})">
			        </div>
			        <button type="button" class="btn btn-default" id="queryButton">查询</button>
			      </form>
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
	</nav>
	
	<!-- 结果集的展现区域 -->
	<div class="container-fluid">
		<div class="span12" id="costTime"></div>
		<table class="table table-bordered table-condensed table-condensed table-hover table-sort-nobgimg" id="tableResult" style="display: none;">
		    <thead>
		        <tr class="span12">
		        		<th>序号</th>
		            <th>姓名</th>
		            <th>性别</th>
		            <th>身份证号</th>
		            <th>旅客地址</th>
		            <th>旅馆名</th>
		            <th>房号</th>
		            <th>入住时间</th>
		            <th>退房时间</th>
		            <th>操作</th>
		        </tr>
		    </thead>
		    <tbody id="tbody"><!--使用 js用来写html -->
		        
		    </tbody>
		</table>
	</div>
	


</body>
<script type="text/javascript" src="${ctx}/static/embed/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hotel.js"></script>
</html>

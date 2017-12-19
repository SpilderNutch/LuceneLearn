<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<!-- Bootstrap -->
<link href="${ctx}/static/embed/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/js/jquery-3.2.1.js"></script>

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
			      <a class="navbar-brand" href="#">Brand</a>
			    </div>
			
			    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
			        <li><a href="#">Link</a></li>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="#">Action</a></li>
			            <li><a href="#">Another action</a></li>
			            <li><a href="#">Something else here</a></li>
			            <li role="separator" class="divider"></li>
			            <li><a href="#">Separated link</a></li>
			            <li role="separator" class="divider"></li>
			            <li><a href="#">One more separated link</a></li>
			          </ul>
			        </li>
			      </ul>
			      <form class="navbar-form navbar-left">
			        <div class="form-group">
			          <input type="text" id="name" class="form-control" placeholder="Search">
			        </div>
			        <button type="button" class="btn btn-default" id="queryButton">查询</button>
			      </form>
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
	</nav>
	
	<!-- 结果集的展现区域 -->
	<div class="container-fluid">
		
	
	
	</div>
	


</body>
<script type="text/javascript" src="${ctx}/static/embed/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hotel.js"></script>
</html>

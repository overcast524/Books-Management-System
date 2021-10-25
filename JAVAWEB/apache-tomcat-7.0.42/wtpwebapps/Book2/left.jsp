<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>左侧</title>
<link href="jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="jquery-ui.js"></script>
<script type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
	})
</script>
<style type="text/css">
li {
	list-style-type: none;
	padding-top: 9px;
}

a {
	text-decoration: none;
}
h3{
     color: #613A0C;
}

#accordion {
	width: 260px;
	height: 600px;
	margin-left: 38% ;
	 margin-top: 10px;
}
 
</style>

</head>
<body   bgcolor="">
<div  class="container-fluid ">
<div class="col col-md-5 col-md-offset-2">
	<div id="accordion"  >
		<h3>
			<font size="4" face="黑体"  ><b>分类管理</b></font>
		</h3>
		<div>
			<li><span class="ui-icon ui-icon-plus"></span> <a
				href="addfl.jsp" target="right"><font size="4" face="黑体"
					color="#337AB7">添加分类</font></a></li>
			<li><span class="ui-icon ui-icon-search"></span><a
				href="FenleiServlet?action=showPasgefl" target="right"><font
					size="4" face="黑体" color="#337AB7">查看所有分类</font></a></li>

		</div>

		<h3>
			<font size="4" face="黑体"  ><b>图书管理</b></font>
		</h3>

		<div id="book2">

			<li><span class="ui-icon ui-icon-plus"></span> <a
				href="addbook.jsp" target="right"><font size="4" face="大篆"
					color="#337AB7">添加图书</font></a></li>


			<li><span class="ui-icon ui-icon-search"></span><a
				href="BookServlet?action=showPasgeBook" target="right"><font
					face="黑体" size="4" color="#337AB7">查看图书</font></a></li>


		</div>

		<h3>
			<font size="4" face="黑体"  ><b>用户管理</b></font>
		</h3>

		<div id="user2">

			<li><span class="ui-icon ui-icon-plus"></span> <a
				href="adduser.jsp" target="right"><font size="4" face="黑体"
					color="#337AB7">添加用户</font></a></li>

			<li><span class="ui-icon ui-icon-search"></span><a
				href="UserServlet?action=showPasgeUser" target="right"><font
					face="黑体" size="4" color="#337AB7">查看用户</font></a></li>

		</div>

		<h3>
			<font size="4" face="黑体"  ><b>管理员信息管理</b></font>
		</h3>

		<div>

			<li><span class="ui-icon ui-icon-search"></span><a
				href="showAdmin.jsp" target="right"><font size="4" face="黑体"
					color="#337AB7">查看管理员信息</font></a></li>

			<li><span class="ui-icon ui-icon-wrench"></span><a
				href="changeMm.jsp" target="_top" ><font size="4" face="黑体"
					color="#337AB7">修改密码</font></a></li>
			<li><span class="ui-icon ui-icon-power"></span><a
				href="exit.jsp" target="_top"><font size="4" face="黑体"
					color="#D93E5C">退出系统</font></a></li>

		</div>

	</div>
	</div>
	</div>
</body>
</html>
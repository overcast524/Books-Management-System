<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

<title>查看图书</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script type="text/javascript" src="js/ajax.js"></script>
<script>
 


 
	window.onload = function() {
		var chek = document.getElementsByName("ids");

		var selectAll = document.getElementById("selectAll");

		selectAll.onclick = function() {
			//全选

			for (i = 0; i < chek.length; i++) {

				chek[i].checked = true;

			}

		}
		//全不选
		var selectNot = document.getElementById("selectNot");

		selectNot.onclick = function() {

			for (i = 0; i < chek.length; i++) {

				chek[i].checked = false;

			}

		}

		//反选
		var fanxuan = document.getElementById("fanxuan");

		fanxuan.onclick = function() {

			for (i = 0; i < chek.length; i++) {
				if (chek[i].checked == true) {
					chek[i].checked = false;

				} else {
					chek[i].checked = true;

				}

			}

		}

		//删除	  
		var df = document.getElementById("dfd");

		df.onclick = function() {

			var flag = false;

			for (i = 0; i < chek.length; i++) {

				if (chek[i].checked == true) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				alert("请至少选一项");
				return;

			} else {

				var str = "";

				for (var i = 0; i < chek.length; i++) {

					if (chek[i].checked == true) {

						str += chek[i].value + ",";

					}
				}
				str = str.slice(0, str.length - 1);
				//alert(str);

				var flage = confirm("你确定删除所勾选的图书吗？");
				if (flage == true) {//确定
					window.location.href = "<%=base%>BookServlet?action=delete&ids="+ str + "&pageNew=${pb.pageNew}";

				} else {//取消

					window.location.href = "<%=base%>BookServlet?action=showPasgeBook&pageNew=${pb.pageNew}";

				}
			}
		}

		//导出部分	  
		var outIds = document.getElementById("outIds");

		outIds.onclick = function() {

			var flag = false;

			for (i = 0; i < chek.length; i++) {

				if (chek[i].checked == true) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				alert("请至少选一项");
				return;

			} else {    
				
				var str = "";

				for (var i = 0; i < chek.length; i++) {

					if (chek[i].checked == true) {

						str += chek[i].value + ",";

					}
				}
				str = str.slice(0, str.length - 1);
				 

				var flag = confirm("你确定导出所勾选的图书信息吗？");
				if (flag == true) {//确定
					window.location.href = "<%=base%>OutPutBookServlet?action=outids&ids="+str;

				} else {//取消
					window.location.href = "<%=base%>BookServlet?action=showPasgeBook&pageNew="+${pb.pageNew};
				}
			}
		};
		//导出全部
		var outAll=document.getElementById("outAll");
		outAll.onclick = function() {
			var flag = confirm("你确定导出全部的图书信息吗？");
			if (flag == true) {//确定
				window.location.href = "<%=base%>OutPutBookServlet?action=all";
			 
		} else {//取消
			window.location.href = "<%=base%>BookServlet?action=showPasgeBook&pageNew="+${pb.pageNew };
		}
		}
		
		var flname = document.f1.findflname;

		ajax({
			method : "POST",
			url : "FenleiServlet",
			params : "action=showOne2",
			type : "json",
			success : function(content) {

				for (var i = 0; i < content.length; i++) {

					var name = content[i];

					var opt = document.createElement("option");
					opt.value = name.name;
					opt.innerHTML = name.name;
					flname.appendChild(opt);

				}

			}
		});
	
		
	};
	/*
	$(function(){
		 
	$("#tb2 tr").mouseover(function(){
		$(this).css("background-color","#F8C7D4");
	})
	
	$("#tb2 tr").mouseout(function(){
		$(this).css("background-color","#FFFBFC");
		 
	})
	
	});*/
	

</script>
<style>
.col {
	width: 1000px;
	height: 500px;
}

#div3 {
	margin-top: 30px;
	width: 1000px;
	height: 520px;
	margin-left: 30px;
}

#div2 {
	margin-left: 10px;
}

#div1 {
	height: 840px;
	background-image: url("tu/t6.jpg");
	background-size: cover;
}

#li {
	color: #337AB7;
	font-size: 17px;
}

#tb2 {
	width: 800px;
}

.r2 {
	margin-top: 3px;
}

#tb2 {
	margin-top: 5px;
	width: 860px;
}

#f1 {
	color: #337AB7;
	width: 400px;
}

.ss {
	margin-left: 40%;
}
</style>
</head>
<body>

	<div class="container-fluid" id="div1">
		<c:if test="${!empty mag }">
			<script>
			alert("${mag }");
		</script>
		</c:if>
		<c:remove var="mag" />
		<div class="col col-md-5 " id="div2">
			<ul class="nav nav-tabs">
				<li class="active"><a id="selectAll" href="#">全选</a></li>
				<li><a id="selectNot" href="#">全不选</a></li>
				<li><a id="fanxuan" href="#">反选</a></li>
				<li><a id="outIds" href="#">导出选中</a></li>
				<li><a id="outAll" href="#">导出全部</a></li>
				<li><a href="<%=base%>addbook.jsp">添加图书</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">高级搜索<span class="caret"></span></a>
					<ul class="dropdown-menu dropdown-menu-right" role="menu">
						<li>
							<form action="BookServlet" class="form-horizontal" id="f1" name="f1">
                               <input type="hidden" name="action" value="gaojifindbook"> 
								<div class="control-group   ">
									<br>
									<label class="col-sm-4  control-label ">选择分类： </label>
									<div class="controls col-sm-6 ">
										<select name="findflname" class="form-control  input-sm" id="findflname">
											<option>----请选择----</option>
											<!--<c:forEach items="${list }" var="s">

												<option>${s.name}</option>
											</c:forEach>-->
										</select><br>
									</div>
								</div>

								<div class="control-group  ">
									<label class="col-sm-4  control-label  ">书名:</label>
									<div class="controls col-sm-6 ">
										<input type="text" name="name" class="form-control  input-sm" /><br>
									</div>
								</div>

								<div class="control-group   ">
									<label class="col-sm-4 control-label  "> 出版社:</label>
									<div class="controls  col-sm-6">
										<input type="text" name="press" class="form-control  input-sm" /><br>
									</div>
								</div>


								<div class="control-group   ">
									<label class="col-sm-4 control-label ">借书人:</label>
									<div class="controls  col-sm-6">
										<input type="text" name="reader" class="form-control  input-sm" /><br>
									</div>
								</div>

								<div class="control-group    ">
									<label class="control-label col-sm-4 "> 状态:</label>
									<div class="controls  col-sm-6">
										<input type="text" name="state" class="form-control  input-sm" /><br>
									</div>
								</div>

								<div class="control-group  ">

									<div class="controls ss">
										<button type="submit" class="btn   btn-info ">
											<span class="glyphicon glyphicon-search"></span> 开始搜索
										</button>
										<br>
										<br>
									</div>
								</div>

							</form>
						</li>

					</ul></li>
			</ul>
			<div class="container-fluid " id="div3">
				<table id="t">
					<tr height="6%">
						<td align="center" colspan=2><br> <font size="7"
							color="#337AB7" face="宋体"><strong>查看图书</strong></font> <!--
							<form class="form-horizontal">
							<div class="row">
								<div class="control-group col-sm-4">
									<label  class="col-sm-5  " >选择分类： </label>
									<div class="controls col-sm-7 ">
										<select name="findflname"  class="form-control  input-sm" >
											<option>----请选择----</option>
											<c:forEach items="${list }" var="s">

												<option>${s.name}</option>
											</c:forEach>
										</select>
									</div>
									</div>
									<div class="control-group col-sm-4 ">
									<label class="col-sm-4 control-label">书名:</label>
									<div class="controls col-sm-8 ">
										<input type="text"  class="form-control  input-sm"/>
									</div>
									</div>
									<div class="control-group col-sm-4 ">
									<label class="col-sm-4 control-label"> 作者:</label>
									<div class="controls  col-sm-8">
										<input type="text"  class="form-control  input-sm"/>
									</div>
								</div>
								</div>
							 
								<div class="row r2">
								<div class="control-group col-sm-4  ">
									<label class="col-sm-4 control-label">借书人:</label>
									<div class="controls  col-sm-8">
										<input type="text"  class="form-control  input-sm" />
									</div>
									</div>
									<div class="control-group col-sm-4 ">
									<label class="control-label col-sm-4"> 状态:</label>
									<div class="controls  col-sm-8">
										<input type="text"  class="form-control  input-sm"/>
									</div>
								 </div>
								 <div class="control-group col-sm-4 ">
								 <div class="controls  col-sm-6">
							 <input type="submit"  value="高级搜索" class="form-control"/>
							  </div>
							 </div>
							 </div>
							</form> 
							 --></td>
					</tr>

					<tr align="center">
						<td>
							<table class="table table-bordered table-hover " cellspacing="0"
								cellpadding="20" id="tb2">
								<tr align="center">
									<td>图书编号</td>
									<td>分类名称</td>
									<td>图书名称</td>
									<td>图书价格</td>
									<td>图书出版社</td>
									<td>状态</td>
									<td>借书人</td>
									<td><button id="dfd">删除</button></td>
									<td>修改</td>
								</tr>
								<c:forEach items="${pb.beanList }" var="s" varStatus="ss">
									<tr align='center'>
										<td>${ss.index+1}</td>
										<td>${s.flname}</td>
										<td>${s.name}</td>
										<td>${s.money}</td>
										<td>${s.press}</td>
										<td>${s.state}</td>
										<td>${s.reader}</td>
										<td><input type="checkbox" name="ids" value="${s.id}" /></td>
										<td><a
											href='<%=base %>BookServlet?action=showOne&id=${s.id }&pageNew=${pb.pageNew }'>
												<input type="button" value="修改" class="btn btn-info btn-sm" />
										</a></td>
									</tr>
								</c:forEach>
							</table>

               
				<c:if  test="${showPesge=='gao'}" > 
							<p>第${pb.pageNew }页/共${pb.pages } 
							<ul class="pagination ">

								<li><a
									href="${pb.url }&pageNew=1">首页</a>
									</li>
								<c:if test="${pb.pageNew>1 }">
									<li><a aria-label="Previous"
										href="${pb.url }&pageNew=${pb.pageNew-1 }"><span
											aria-hidden="ture">上一页</span></a></li>
								</c:if>

								<!-- 分页2种情况
			               1.页数小于10
			                   2.页数大于10
			                         -->

								<c:choose>
									<c:when test="${pb.pages<=10 }">
										<c:set var="begin" value="1"></c:set>
										<c:set var="end" value="${pb.pages }"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="begin" value="${pb.pageNew-5 }"></c:set>
										<c:set var="end" value="${pb.pageNew+4 }"></c:set>
										<c:if test="${begin<=1 }">
											<c:set var="begin" value="1"></c:set>
											<c:set var="end" value="10"></c:set>
										</c:if>
										<c:if test="${end>=pb.pages }">
											<c:set var="begin" value="${pb.pages-9 }"></c:set>
											<c:set var="end" value="${pb.pages}"></c:set>
										</c:if>

									</c:otherwise>
								</c:choose>
								<!-- 每页面显示10页数 -->

								<c:forEach begin="${begin }" end="${end }" var="i">
									<c:choose>
										<c:when test="${pb.pageNew==i }">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pb.url }&pageNew=${i}">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNew<pb.pages }">
									<li><a
										href="${pb.url }&pageNew=${pb.pageNew+1 }"
										aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
									</li>
								</c:if>

								 
								<li><a
									href="${pb.url }&pageNew=${pb.pages}">尾页
								</a>
								</li>
								</p>
				
							</ul>
					  </c:if>
					  <c:if test="${showPesge=='book'}"> 
								<p>第${pb.pageNew }页/共${pb.pages } 
							<ul class="pagination ">

								<li><a
									href="<%=base%>BookServlet?action=showPasgeBook&pageNew=1">首页</a>
									</li>
								<c:if test="${pb.pageNew>1 }">
									<li><a aria-label="Previous"
										href="<%=base %>BookServlet?action=showPasgeBook&pageNew=${pb.pageNew-1 }"><span
											aria-hidden="ture">上一页</span></a></li>
								</c:if>

								<!-- 分页2种情况
			               1.页数小于10
			                   2.页数大于10
			                         -->

								<c:choose>
									<c:when test="${pb.pages<=10 }">
										<c:set var="begin" value="1"></c:set>
										<c:set var="end" value="${pb.pages }"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="begin" value="${pb.pageNew-5 }"></c:set>
										<c:set var="end" value="${pb.pageNew+4 }"></c:set>
										<c:if test="${begin<=1 }">
											<c:set var="begin" value="1"></c:set>
											<c:set var="end" value="10"></c:set>
										</c:if>
										<c:if test="${end>=pb.pages }">
											<c:set var="begin" value="${pb.pages-9 }"></c:set>
											<c:set var="end" value="${pb.pages}"></c:set>
										</c:if>

									</c:otherwise>
								</c:choose>
								<!-- 每页面显示10页数 -->

								<c:forEach begin="${begin }" end="${end }" var="i">
									<c:choose>
										<c:when test="${pb.pageNew==i }">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="<%=base %>BookServlet?action=showPasgeBook&pageNew=${i}">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNew<pb.pages }">
									<li><a
										href="<%=base %>BookServlet?action=showPasgeBook&pageNew=${pb.pageNew+1 }"
										aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
									</li>
								</c:if>

								 
								<li><a
									href="<%=base %>BookServlet?action=showPasgeBook&pageNew=${pb.pages}">尾页
								</a>
								</li>
								</p>
				
							</ul> 
					  </c:if>
				 
					  <br>

						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
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
<link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css" />
<script type="text/javascript" src="iconfont/iconfont.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>查看图书</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script>
	 $(function(){
		
		 $(".not").click(function(){
          alert("亲，请先登录\(^-^)/");		 
		  
		 
		 });
		 
		 $(".jieshu").click(function(){
	          alert("亲,非常抱歉,本书已借出,再看看别的吧(T-T)");		 
			  
			 
			 });
		 $(".huanshu").click(function(){
	          alert("亲,这本书没有借出,难道你要送我一本($-$)");		 
			  
			 
			 });	 
	
		
	}); 
</script>
<style>
#div3 {
	height: 840px;
	 	background-image: url("tu/t7.jpg");
	background-size: cover;
 
}

.footer {
	color: #777;
	border-top: 1px solid #e5e5e5;
	text-align: center;
	padding: 30px 0;
}

.fm {
	margin-top: 10px;
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

		<div class="container-fluid " id="div3">
			<div class="container" id="div4">
				<table id="t" class="table ">
					<tr height="6%">
						<td align="center" colspan=2><br>
							<div class="col-md-3 col-md-offset-4">
								<font size="7" color="#337AB7" face="宋体"><strong>图书信息</strong></font>
							</div>
							<div class="col-md-4 col-md-offset-1 fm">
								<form class="navbar-form navbar-left"
									action="UserForegroundServlet" >
									<input type="hidden" name="action" value="showPasgeUsBook">
									<input type="hidden" name="qusername" value="${qusername }">
									
									<div class="form-group">
										<input type="text" class="form-control" placeholder="图书名"
											name="name">
									</div>
									<button type="submit" class="btn btn-default">搜索</button>
								</form>
							</div></td>
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
									<td>借阅</td>
								</tr>
								<c:forEach items="${pb.beanList }" var="s" varStatus="ss">
									<tr align='center'>
										<td>${ss.index+1}</td>
										<td>${s.flname}</td>
										<td>${s.name}</td>
										<td>${s.money}</td>
										<td>${s.press}</td>
										<td><c:if test="${s.state=='未借出'}">
												 未借出 

											</c:if> <c:if test="${s.state=='借出'}">
												 借出   
											</c:if></td>
										<td>
									 <c:choose>
										<c:when test="${empty qusername }">
												<a  href="#" id="jieshu" class="  btn  btn-sm  btn-default not" > 借阅 </a> &nbsp;&nbsp;&nbsp;&nbsp;
						                   <a href="#" id="huanshu" class="btn  btn-sm  btn-default not" > 还书 </a>
											</c:when>
									 <c:otherwise>
											<c:if test="${s.state=='未借出'}">
												<a  href="<%=base%>UserForegroundServlet?action=jieshu&pageNew=${pb.pageNew+1 }&id=${s.id }&qusername=${qusername }"
													id="jieshu" class="btn btn-info btn-sm " > 借阅 </a> 
						                       <a href="#" id="huanshu"  class="btn btn-sm  btn-default huanshu"> 还书 </a>
											</c:if>
											<c:if test="${s.state=='借出'}">
												<a  href="#" id="jieshu" class="btn btn-sm  btn-default jieshu"> 借阅 </a> 
						                   <a href="<%=base%>UserForegroundServlet?action=huanshu&pageNew=${pb.pageNew+1 }&id=${s.id }&qusername=${qusername }" id="huanshu" class="btn  btn-info  btn-sm"> 还书 </a>
											</c:if>
												</c:otherwise>
										 </c:choose>
										 </td>
									</tr>
								</c:forEach>
							</table>

                    <c:if  test="${showPesge=='soushuo'}" > 
							<p>第${pb.pageNew }页/共${pb.pages }&nbsp;&nbsp;&nbsp;&nbsp;
							<ul class="pagination ">

								<li><a
									href="${pb.url }&pageNew=1&qusername=${qusername }">首页</a>
									&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<c:if test="${pb.pageNew>1 }">
									<li><a aria-label="Previous"
										href="${pb.url }&pageNew=${pb.pageNew-1 }&qusername=${qusername }"><span
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
												href="${pb.url }&pageNew=${i}&qusername=${qusername }">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNew<pb.pages }">
									<li><a
										href="${pb.url }&pageNew=${pb.pageNew+1 }&qusername=${qusername }"
										aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
									</li>
								</c:if>

								<li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;<a
									href="${pb.url }&pageNew=${pb.pages}&qusername=${qusername }">尾页
								</a>
								</li>
								</p>
							</ul> 
							</c:if>
					 <c:if  test="${showPesge=='xianshi'}" > 
							<p>第${pb.pageNew }页/共${pb.pages }&nbsp;&nbsp;&nbsp;&nbsp;
							<ul class="pagination ">

								<li><a
									href="<%=base%>UserForegroundServlet?action=showPasgeBook&pageNew=1&qusername=${qusername }">首页</a>
									&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<c:if test="${pb.pageNew>1 }">
									<li><a aria-label="Previous"
										href="<%=base %>UserForegroundServlet?action=showPasgeBook&pageNew=${pb.pageNew-1 }&qusername=${qusername }"><span
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
												href="<%=base %>UserForegroundServlet?action=showPasgeBook&pageNew=${i}&qusername=${qusername }">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNew<pb.pages }">
									<li><a
										href="<%=base %>UserForegroundServlet?action=showPasgeBook&pageNew=${pb.pageNew+1 }&qusername=${qusername }"
										aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
									</li>
								</c:if>

								<li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;<a
									href="<%=base %>UserForegroundServlet?action=showPasgeBook&pageNew=${pb.pages}&qusername=${qusername }">尾页
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
		<footer class="footer">
			<!-- 下 -->

			<h4>
				<font size="3" color="#b1b1b1">甲骨文图书管理系统（王旭）版权所有&copy;2025-2030</font>
			</h4>
		</footer>
	</div>
</body>
</html>
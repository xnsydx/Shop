<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
		
			<c:if test="${empty user }">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</c:if>
			<c:if test="${!empty user }">
				<li style="color:red">欢迎您，${user.username }</li>
				<li><a href="${pageContext.request.contextPath }/user?method=logout">退出</a></li>
			</c:if>
			
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/product?method=myOrders">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }/product?method=index">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categoryUl">
				
					<%-- <c:forEach items="${categoryList }" var="category">
						<li><a href="#">${category.cname }</a></li>
					</c:forEach> --%>
					
				</ul>
			<form class="navbar-form navbar-right" role="search" >
					<div class="form-group" style="position:relative">
						<input id="search" type="text" class="form-control" placeholder="Search" onkeyup="searchWord(this)">
						<div id="showDiv" style="position:absolute;z-index:1000;background:#fff;width:179px;borad:1px solid #ccc;"></div>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
		
		<!-- header.jsp加载完毕后 去服务器端获得所有的category数据 -->
		<script type="text/javascript">
			//header.jsp加载完毕后 去服务器端获得所有的category数据
			$(function(){
				var content = "";
				$.post(
					"${pageContext.request.contextPath}/product?method=categoryList",
					function(data){
						//[{"cid":"xxx","cname":"xxxx"},{},{}]
						//动态创建<li><a href="#">${category.cname }</a></li>
						for(var i=0;i<data.length;i++){
							content+="<li><a href='${pageContext.request.contextPath}/product?method=productList&cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
						}
						
						//将拼接好的li放置到ul中
						$("#categoryUl").html(content);
					},
					"json"
				);
			});
		</script>
		
		<!-- 完成异步搜索 -->
		<script type="text/javascript">
			function overFn(obj){
				$(obj).css("background","#DBEAF9");
			}
			function outFn(obj){
				$(obj).css("background","#fff");
			}
			function clickFn(obj){
				$("#search").val($(obj).html());
				$("#showDiv").css("display","none");
			}
		
		
			function searchWord(obj){
				//1.获取输入框内容
				//alert(obj.value);
				var word=$(obj).val();
				if(word.length>0){
					//2.根据输入框内容去数据库中模糊查询，--List<product>
					var content="";
					$.post(
						"${pageContext.request.contextPath}/searchWord",
						{"word":word},
						function(data){//data返回的是个数组
							//3.将返回的商品的名称，返回在showDiv中
							if(data.length>0){
								for(i=0;i<data.length;i++){
									content+="<div style='padding: 5px;cursor: pointer;' onmouseover='overFn(this)' onmouseout='outFn(this)' onclick='clickFn(this)'>"+data[i]+"</div>";
								}
								$("#showDiv").html(content);
								$("#showDiv").css("display","block");
							}
						},
						"json"
					)
				}else{
					$("#showDiv").css("display","none");
				}
				
				
			}
		
		</script>
		
	</nav>
</div>
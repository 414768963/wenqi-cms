<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
<title>${article.title }</title>
<script type="text/javascript">
	var articleId = "${id}";
</script>
</head>
<body>
	<nav class="nav justify-content-start" style="background-color: #222;">
	<c:if test="${USER_SESSION_ID!=null && USER_SESSION_ID.headimg!=null }">
		<a class="nav-link navbar-brand" href="/"> 
			<img src="${USER_SESSION_ID.headimg }"
			width="30" height="30" alt="">
		</a>
	</c:if>
	<c:if test="${USER_SESSION_ID==null || USER_SESSION_ID.headimg==null }">
		<a class="nav-link navbar-brand" href="/"> 
			<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg"
			width="30" height="30" alt="">
		</a>
	</c:if>
		<c:if test="${USER_SESSION_ID!=null }">
			<a class="nav-link" href="/user/center">发文</a>
			<a class="nav-link" href="/user/center">个人中心</a>
			<a class="nav-link" href="javascript:;">${USER_SESSION_ID.nickname }</a>
			<a class="nav-link" href="/user/logout">退出</a>
		</c:if>
		<c:if test="${USER_SESSION_ID==null }">
			<a class="nav-link" href="/user/login">登录</a>
		</c:if>
	</nav>
	<div class="container-fluid">
		<div class="row offset-1" style="margin-top: 15px;">
			<div class="col-7">
				<h1>${article.title }</h1>
				<div style="margin-top: 10px;margin-bottom: 10px;font-weight: bold;color: #777;">
					<span>${user.nickname }</span> 
					<span><fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					<div style="margin-top: 10px;">
						    <button type="button" class="btn btn-primary" onclick="tousuShow()">投诉</button>
					</div>
				</div>
				<div style="font-size: 24">
					${article.content }
				</div>
				<div id="comment">
					<div class="row" style="margin-top: 10px;">
						  <div class="col-10">
							  <form class="was-validated">
							    <textarea class="form-control" rows="2" id="content" placeholder="请输入评论内容" required></textarea>
							  </form>
						  </div>
						  <div style="margin-top: 10px;">
						    <button type="button" class="btn btn-primary" onclick="addComment();">评论</button>
						  </div>
					</div>
				</div>
			</div>
			
			<div class="col-3">
				<c:if test="${articleList.size()>0 }">
					<div class="right">
						<div>相关文章</div>
						<ul class="list-unstyled">
							<c:forEach items="${articleList }" var="item">
								<li class="media">
									<a href="/article/${item.id }.html"><img style="width: 64px; height: 64px;"	src="${item.picture }" class="mr-3" alt=""></a>
									<div class="media-body">
										<h5 class="mt-0 mb-1"><a href="/article/${item.id }.html">${item.title }</a></h5>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</div>
<div class="modal" tabindex="-1" role="dialog" id="tousuModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">投诉内容</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
		<form id="complainModal">
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="complaintype" id="complaintype" value="A">
			  <label class="form-check-label" for="inlineRadio1">涉及黄色</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="complaintype" id="complaintype" value="B">
			  <label class="form-check-label" for="inlineRadio2">涉及暴力</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="complaintype" id="complaintype" value="C">
			  <label class="form-check-label" for="inlineRadio3">涉及违宗教政策</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="complaintype" id="complaintype" value="D">
			  <label class="form-check-label" for="inlineRadio4">涉及国家安全</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="complaintype" id="complaintype" value="F">
			  <label class="form-check-label" for="inlineRadio5">抄袭内容</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="complaintype" id="complaintype" value="G">
			  <label class="form-check-label" for="inlineRadio6">其它</label>
			</div>
			  <div class="form-group">
			    URL地址:<input type="text" class="form-control" id="urlip" name="urlip">
			  </div>
		
		</form>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="tousu();">确定</button>
      </div>
    </div>
  </div>
</div>

	<script type="text/javascript" src="/public/js/jquery.min.1.12.4.js"></script>
	<script type="text/javascript" src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$.get("/comment/list",{articleId:articleId},function(res){
			$("#comment").append(res);
		},"html");
		
		function addComment(){
			var content = $("#content").val();
			if(content==null || content==""){
				alert("评论内容不能为空");
				return;
			}
			$.post("/comment/add",{articleid:articleId,content:content},function(res){
				if(res.result){
					$("#content").val("");
					gotoPage(1);
				}else if(res.errorCode==10000){
					alert("你还未登录");
					location.href="/user/login";
				}
			})
		}
		function tousuShow() {
			$.post("/user/isLogin",function(res){
				if(res.result){
					$('#tousuModal').modal('show');
				}else{
					alert("您还未登录");
					location.href="/user/login";
				}
			})
		}
		function tousu() {
			var data = $("form").serialize();
			console.log(data);
			$.post("/admin/complain/add?article_id="+articleId,data,function(res){
				if(res.result){
					alert("投诉成功!");
					$('#tousuModal').modal('hide');
				}
				if(res.errorCode==1001){
					alert("不能投诉自己!");
					$('#tousuModal').modal('hide');
				}
				if(res.errorCode==1002){
					alert("URL路径不正确");
					$('#tousuModal').modal('hide');
				}
			})
		}
		
	</script>
</body>
</html>
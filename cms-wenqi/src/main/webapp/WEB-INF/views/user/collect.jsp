<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的收藏夹</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/cms.css" rel="stylesheet">
</head>
<body>
  	<div style="margin-top: 18px;">
		<c:forEach items="${list }" var="item">
		  <div class="media">
			  <div class="media-body">
			    <h4 class="mt-1">
			    	<a href="${item.url }" target="_blank">${item.text }</a>
			    	<button type="button" class="btn btn-primary" onclick="del()">删除</button>
			    </h4>
			    <input type="hidden" id="collectId" name="collectId" value="${item.id }">
			    <%-- <p style="color: #999;">${item.nickname }  <fmt:formatDate value="${item.created }" pattern="yyyy-MM-dd HH:mm:ss"/></p> --%>
			  </div>
			</div>
	  </c:forEach>
	</div>
				
<script type="text/javascript" src="/public/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function del() {
		var collect=$("#collectId").val();
		$.post("/collect/del",{collectId:collect},function(res){
			if(res.result){
				alert("删除成功!");
			}else if(res.errorCode==2022){
				alert("此收藏不是你的");
			}
		})
	}


</script>
</body>
</html>
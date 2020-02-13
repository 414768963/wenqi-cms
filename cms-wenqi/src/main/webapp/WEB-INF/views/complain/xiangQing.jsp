<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
  <thead>
    <tr>
      <th scope="col">文章编号</th>
      <th scope="col">标题</th>
      <th scope="col">投诉人</th>
      <th scope="col">投诉时间</th>
      <th scope="col">投诉类型</th>
      <th scope="col">投诉证据</th>
    </tr>
  </thead>
  <tbody>
       	<tr>
       		<td>${complain.article_id }</td>
       		<td>${complain.title }</td>
       		<td>${complain.username }</td>
       		<td>${complain.creatTime }</td>
       		<td>
	       		<c:if test="${complain.complaintype=='A'}">涉及黄色</c:if>
		      	<c:if test="${complain.complaintype=='B'}">涉及暴力</c:if>
		      	<c:if test="${complain.complaintype=='C'}">涉及违宗教政策</c:if>
		      	<c:if test="${complain.complaintype=='D'}">涉及国家安全</c:if>
		      	<c:if test="${complain.complaintype=='F'}">抄袭内容</c:if>
		      	<c:if test="${complain.complaintype=='G'}">其它</c:if>
       		</td>
       		<td>${complain.urlip }</td>
       	
       	</tr>
  </tbody>
</table>
</body>
</html>
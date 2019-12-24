<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link href="/public/css/bootstrap.min.css" rel="stylesheet">
    <title>禁用页面</title>
  </head>
  <body>
	<h1>该页面已被禁止查看</h1>
    <button type="button" class="btn btn-primary" onclick="goBack();">返回首页</button>
	<script type="text/javascript" src="/public/js/jquery.min.1.12.4.js"></script>
	<script type="text/javascript" src="/public/js/bootstrap.min.js"></script>
  </body>
  <script>
  	function goBack() {
		location.href="/";
	}
  
  </script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form class="form-inline">
	投诉类型:
	<select id="complaintype" name="complaintype">
		<option value="">--请选择--</option>
		<option value="A">涉及黄色</option>
		<option value="B">涉及暴力</option>
		<option value="C">涉及违宗教政策</option>
		<option value="D">涉及国家安全</option>
		<option value="F">抄袭内容</option>
		<option value="G">其它</option>
	</select>
	次数大于:<input type="text" name="complainnum1" id="complainnum1" value="${complain.complainnum1 }">
	次数小于:<input type="text" name="complainnum2" id="complainnum2" value="${complain.complainnum2 }">
	<input type="hidden" name="pageNum" value="${pageInfo.pageNum }">
	<button type="button" class="btn btn-primary mb-2" onclick="query()">查询</button>
</form>
<table class="table">
  <thead>
    <tr>
      <th scope="col">订单编号</th>
      <th scope="col">标题</th>
      <th scope="col">文章内容</th>
      <th scope="col">投诉类型</th>
      <th scope="col" onclick="paiX();">投诉次数</th>
      <th scope="col">投诉详情</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${pageInfo.list }" var="item">
    	<tr>
	      <th scope="row">${item.id }</th>
	      <td>${item.title }</td>
	      <td>${item.title }</td>
	      <td>
	      	<c:if test="${item.complaintype=='A'}">涉及黄色</c:if>
	      	<c:if test="${item.complaintype=='B'}">涉及暴力</c:if>
	      	<c:if test="${item.complaintype=='C'}">涉及违宗教政策</c:if>
	      	<c:if test="${item.complaintype=='D'}">涉及国家安全</c:if>
	      	<c:if test="${item.complaintype=='F'}">抄袭内容</c:if>
	      	<c:if test="${item.complaintype=='G'}">其它</c:if>
	      </td>
	      <td>${item.complainnum }</td>
	      <td><button type="button" class="btn btn-primary" onclick="xiangQing('${item.id }')">详情</button></td>
	      <td>
	      	<c:if test="${item.complainnum>=50 }">
	      		<button type="button" class="btn btn-primary" onclick="stopShow('${item.article_id }')">禁止</button>
	      	</c:if>
	      </td>
	    </tr>
    </c:forEach>
  </tbody>
</table>
<div class="row">
	<div class="col-9">
		<jsp:include page="../common/page.jsp"></jsp:include>
	</div>
</div>
<script>
	
	function gotoPage(pageNo){
		$("[name=pageNum]").val(pageNo);
		query();
	}
	
	function query() {
		var data = $("form").serialize();
		var complainnum1=$("#complainnum1").val();
		var complainnum2=$("#complainnum2").val();
		if(complainnum1>complainnum2){
			alert("开始值不能大于结束值");
		}else{
			reload(data);
		}
	}
	
	function stopShow(id) {
		$.post("/admin/complain/stop",{id:id},function(res){
			if(res.result){
				alert("禁看成功!");
			}
		});
	}
	function paiX() {
		var data = $("form").serialize();
		reload(data);
	}
	function xiangQing(id) {
		location.href="/admin/complain/xiangQing?id="+id;
	}
</script>

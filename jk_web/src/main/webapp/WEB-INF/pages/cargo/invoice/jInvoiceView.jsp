<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
			浏览发票
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">发票号：</td>
					<td class="tableContent">${scNo}</td>
					<td class="columnTitle">提单号：</td>
					<td class="tableContent">${blNo}</td>
				</tr>
				<tr>
					<td class="columnTitle">贸易条款：</td>
					<td class="tableContent">${tradeTerms}</td>
					<td class="columnTitle">状态：</td>
					<td class="tableContent"><c:if test="${state == 0}">草稿</c:if>
						<c:if test="${state == 1}">
							<font color="green">已发票</font>
						</c:if></td>
				</tr>
			</table>
		</div>


	</form>
</body>
</html>


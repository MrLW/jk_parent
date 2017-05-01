<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<script type="text/javascript">
	function isOnlyChecked() {
		var checkBoxArray = document.getElementsByName('id');
		var count = 0;
		for (var index = 0; index < checkBoxArray.length; index++) {
			if (checkBoxArray[index].checked) {
				count++;
			}
		}
		//jquery
		//var count = $("[input name='id']:checked").size();
		if (count == 1)
			return true;
		else
			return false;
	}
	//实现更新
	function toUpdate() {
		if (isOnlyChecked()) {
			formSubmit('financeAction_toupdate', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}

	// 查看formSubmit('shippingOrderAction_toview','_self');this.blur();
	function toView() {
		if (isOnlyChecked()) {
			formSubmit('financeAction_toview', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
	// 删除formSubmit('shippingOrderAction_delete','_self');this.blur();
	function todelete() {
		if (isOnlyChecked()) {
			formSubmit('financeAction_delete', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
	// 提交
	function tocommit() {
		if (isOnlyChecked()) {
			formSubmit('financeAction_commit', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
	// 取消
	function tocancel() {
		if (isOnlyChecked()) {
			formSubmit('financeAction_cancel', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
</script>
<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="view"><a href="#"
								onclick="javascript:toView()">查看</a></li>
							<li id="new"><a href="#"
								onclick="formSubmit('financeAction_tocreate','_self');this.blur();">新增</a></li>
							<li id="update"><a href="#"
								onclick="javascript:toUpdate()">修改</a></li>
							<li id="delete"><a href="#"
								onclick="javascript:todelete()">删除</a></li>
							<li id="new"><a href="#" onclick="javascript:tocommit()">提交</a></li>
							<li id="new"><a href="#" onclick="javascript:tocancel()">取消</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 财务列表
		</div>

		<div>


			<div class="eXtremeTable">
				<table id="ec_table" class="tableRegion" width="98%">
					<thead>
						<tr>
							<td class="tableHeader"><input type="checkbox" name="selid"
								onclick="checkAll('id',this)"></td>
							<td class="tableHeader">序号</td>
							<td class="tableHeader">制单日期</td>
							<td class="tableHeader">制单人</td>
							<td class="tableHeader">状态</td>
						</tr>
					</thead>
					<tbody class="tableBody">
						${links}

						<c:forEach items="${results}" var="o" varStatus="status">
							<tr align="left" class="odd" onmouseover="this.className='highlight'"
								onmouseout="this.className='odd'">
								<td><input type="checkbox" name="id" value="${o.id}" /></td>
								<td>${status.index+1}</td>
								<td>${o.inputDate}</td>
								<td>${o.inputBy}</td>
								<td><c:if test="${o.state == 0}">草稿</c:if> <c:if
										test="${o.state == 1}">
										<font color="green">已制单</font>
									</c:if></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		</div>


	</form>
</body>
</html>


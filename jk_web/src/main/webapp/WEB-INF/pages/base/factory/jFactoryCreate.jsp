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
							<li id="save"><a href="#"
								onclick="formSubmit('factoryAction_insert','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 新增厂家
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
						<td class="columnTitle">排序号：</td>
					<td class="tableContent"><input type="text" name="orderNo"
						value="" /></td>
					<td class="columnTitle">厂家全称：</td>
					<td class="tableContent"><input type="text" name="fullName"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">名称缩写：</td>
					<td class="tableContent"><input type="text" name="factoryName"
						value="" /></td>
					<td class="columnTitle">联系人：</td>
					<td class="tableContent"><input type="text" name="contacts"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">电话：</td>
					<td class="tableContent"><input type="text" name="phone"
						value="" /></td>
					<td class="columnTitle">手机：</td>
					<td class="tableContent"><input type="text" name="mobile"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">传真：</td>
					<td class="tableContent"><input type="text" name="fax"
						value="" /></td>
					<td class="columnTitle">地址：</td>
					<td class="tableContent"><input type="text" name="address"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">验货员：</td>
					<td class="tableContent"><input type="text" name="inspector"
						value="" /></td>
					<td class="columnTitle">说明：</td>
					<td class="tableContent"><input type="text" name="remark"
						value="" /></td>
				</tr>
				<tr>
				
					<td class="columnTitle">
						<!-- 货物/附件 -->类型：
					</td>
					<td class="tableContent">
						<!-- <input type="text" name="ctype" value=""/> --> <select
						name="ctype">
							<option value="货物">货物</option>
							<option value="附件">附件</option>
					</select>
					</td>
					<td class="columnTitle">1正常0停用：</td>
					<td class="tableContent">
						<!-- <input type="text" name="state"
						value="" /> --> <select name="ctype">
							<option value="1">正常</option>
							<option value="2">停用</option>
					</select>
					</td>
				</tr>
			</table>
		</div>


	</form>
</body>
</html>


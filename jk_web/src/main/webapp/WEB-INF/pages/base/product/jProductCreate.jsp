<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<script type="text/javascript">
	function select(obj) {
		// 获取角标
		var index = obj.selectedIndex;
		// 获取值
		var factoryName = obj.options[index].value.split(",")[0];
		
		var factoryNameOption = document.getElementsByName("factoryName")[0] ;
		factoryNameOption.value = factoryName ;
	}
</script>
<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('productAction_insert','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 新增产品
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">编号：</td>
					<td class="tableContent"><input type="text" name="productNo"
						value="" /></td>
					<td class="columnTitle">照片：</td>
					<td class="tableContent"><input type="text"
						name="productImage" value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">描述：</td>
					<td class="tableContent"><input type="text" name="description"
						value="" /></td>
					<td class="columnTitle">厂家：</td>
					<td class="tableContent"><select onchange="select(this)" name="factoryId">
							<c:forEach items="${factoryList }" var="factory">
								<option id="factoryId" value="${factory.factoryName},${factory.id}">${factory.fullName }
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="columnTitle">厂家简称：</td>
					<td class="tableContent"><input type="text" name="factoryName"
						value="升华" readonly="readonly"/></td>
					<td class="columnTitle">市场价：</td>
					<td class="tableContent"><input type="text" name="price"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">尺寸长：</td>
					<td class="tableContent"><input type="text" name="sizeLenght"
						value="" /></td>
					<td class="columnTitle">尺寸宽：</td>
					<td class="tableContent"><input type="text" name="sizeWidth"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">尺寸高：</td>
					<td class="tableContent"><input type="text" name="sizeHeight"
						value="" /></td>
					<td class="columnTitle">颜色：</td>
					<td class="tableContent"><input type="text" name="color"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">包装：</td>
					<td class="tableContent"><input type="text" name="packing"
						value="" /></td>
					<td class="columnTitle">包装单位：</td>
					<td class="tableContent"><input type="text" name="packingUnit"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">集装箱类别20：</td>
					<td class="tableContent"><input type="text" name="type20"
						value="" /></td>
					<td class="columnTitle">集装箱类别40：</td>
					<td class="tableContent"><input type="text" name="type40"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">集装箱类别40HC：</td>
					<td class="tableContent"><input type="text" name="type40hc"
						value="" /></td>
					<td class="columnTitle">数量：</td>
					<td class="tableContent"><input type="text" name="qty"
						value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">体积：</td>
					<td class="tableContent"><input type="text" name="cbm"
						value="" /></td>
					<td class="columnTitle">大箱尺寸长：</td>
					<td class="tableContent"><input type="text"
						name="mpsizeLenght" value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">大箱尺寸宽：</td>
					<td class="tableContent"><input type="text" name="mpsizeWidth"
						value="" /></td>
					<td class="columnTitle">大箱尺寸高：</td>
					<td class="tableContent"><input type="text"
						name="mpsizeHeight" value="" /></td>
				</tr>
				<tr>
					<td class="columnTitle">备注：</td>
					<td class="tableContent"><input type="text" name="remark"
						value="" /></td>
				</tr>
			</table>
		</div>


	</form>
</body>
</html>


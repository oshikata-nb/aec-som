<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@page import="com.asahikaseieng.app.comboboxes.SelectInspectionDivision"%>
<%@page import="com.asahikaseieng.app.comboboxes.SelectInspectionCondition"%>
<%@page import="com.asahikaseieng.app.radiobuttons.RadioInspectionValueType"%>
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<!-- Style Sheet(Item用) -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {

	<%-- 検査名称のauto complete --%>
		new Ajax.Autocompleter(
		  "inspectionCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/NamesForAutoComplete.do?op=searchNames",
		  {
		  	paramName : "code",
		  	parameters : 'nameDivision=STDV',
		    afterUpdateElement : setNamesLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('inspectionCd', 'keypress', clearLabel.bindAsEventListener($('inspectionName')), false);


		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("inspectionCd");

		<%-- カンマ編集 --%>
		format();

		storeInitValues('dirtyFlg');

	}, false);

	<%-- 検査名称auto completeの選択後処理 --%>
	function setNamesLabel(text, li) {
	    $("lblInspectionName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("inspectionName").value = li.getElementsByTagName('span')[0].innerText;
	}


	<%-- 変更確認 --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
	}

	<%-- 確認メッセージ --%>
	function putUpdate() {
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- カンマ編集 --%>
	function format() {
		var valueType1 = $("valueType0");	// 数値
		var value1 = $F("value1");
		var value2 = $F("value2");
		var resultValue1 = $F("resultValue1");
		if( valueType1.checked ) {
			// 数値
			$("value1Format").value = formatNumber(value1);
			$("value2Format").value = formatNumber(value2);
			$("result1Format").value = formatNumber(resultValue1);
		} else {
			// 文字列
			$("value1Format").value = value1;
			$("value2Format").value = value2;
			$("result1Format").value = resultValue1;
		}
	}
	
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgRdirectionInspectionDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="directionDivision" styleId="directionDivision" />
	<nested:hidden property="directionNo" styleId="directionNo" />

	<table cellpadding="0" cellspacing="0" border="0" width="750">
		<tr>
			<td class="valignTop alignLeft">
				<!-- ヘッダー部 -->
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td height="10" colspan="2"></td>
					</tr>
					<tr>
						<td class="fcTitle fb" width="250">包装実績検査詳細</td>
						<td>
							<table cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td>
										<%@ include file="/jsp/common/disp_info_message.jsf"%>
										<%-- メッセージ表示は、共通のメッセージ表示jsfをincludeする --%>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="bcTitleLine" colspan="2"></td>
					</tr>
					<tr>
						<td height="5" colspan="2"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<%-- メッセージ表示 --%>
				<%@ include file="/jsp/common/disp_error_message.jsf"%>
				<%-- メッセージ表示 ここまで --%>
			</td>
		</tr>
		<%-- ヘッダー部 --%>
		<tr>
			<td>
				<table cellspacing="2" cellpadding="2" border="0" width="100%">
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="120">基本処方ｺｰﾄﾞ</td>
						<td width="150">
						<nested:write property="recipeCd" />
						</td>
						<td class="fcTitleDetail fb bcTitleDetail" width="120">バージョン</td>
						<td>
							<nested:write property="recipeVersion" />
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="120" >基本処方名称</td>
						<td>
							<nested:write property="recipeName" />
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="120">品目コード</td>
						<td width="155">
							<nested:write property="itemCd" />
						</td>

						<td class="fcTitleDetail fb bcTitleDetail" width="120">品目名称</td>
						<td>
							<nested:write property="itemName" />
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="120">工程順序</td>
						<td width="155">
							<nested:write property="stepNo" />
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="120">工程コード</td>
						<td width="155">
							<nested:write property="operationCd" />
						</td>

						<td class="fcTitleDetail fb bcTitleDetail" width="120">工程名称</td>
						<td>
							<nested:write property="operationName" />
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="120">用途</td>
						<td>
							<nested:write property="recipeUse" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="bcTitleLine"></td>
		</tr>
		<tr>
			<td height="5" colspan="2"></td>
		</tr>

		<%-- 明細部 --%>
		<tr>
			<td>
				<table width="750" cellspacing="2" cellpadding="1" border="0">
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="160">検査コード</td>
						<td>
							<nested:text property="inspectionCd" styleId="inspectionCd" maxlength="20" size="20" onchange="setDirtyFlg();"/>
							<div id="autocomplete_selection" class="autocomplete"></div>
						</td>
						<td width="490">
							<%-- 検査名称 --%>
							<div id="lblInspectionName">
								<nested:write property="inspectionName" />
							</div>
							<nested:hidden property="inspectionName" styleId="inspectionName"/>
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="160">区分</td>
						<td colspan="2">
							<%
								pageContext.setAttribute("selectInspectionDivision",
								new com.asahikaseieng.app.comboboxes.SelectInspectionDivision(false, false));
							%>
							<nested:select property="strInspectionDivision" styleId="strInspectionDivision" onchange="setDirtyFlg();">
								<nested:options name="selectInspectionDivision" property="values" labelName="selectInspectionDivision" labelProperty="labels" />
							</nested:select>
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="160">基準値1</td>
						<td>
							<nested:text property="value1" styleId="value1" maxlength="20" size="20" onchange="setDirtyFlg();"  onblur="format();"/>
							<nested:hidden property="value1Format" styleId="value1Format" />
						</td>
						<td>
							<% pageContext.setAttribute("RadioInspectionValueType",	new RadioInspectionValueType());	%>
							<logic:iterate id="mp" name="RadioInspectionValueType" property="map" indexId="index">
								<nested:radio idName="mp" property="valueType" onchange="setDirtyFlg();"styleId="<%="valueType" + pageContext.findAttribute("index").toString() %>" value="key" onclick="format();"/>
								<bean:write name="mp" property="value" />
							</logic:iterate>
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="160">条件</td>
						<td colspan="2">
							<%
								pageContext.setAttribute("selectInspectionCondition",
								new com.asahikaseieng.app.comboboxes.SelectInspectionCondition(false, false));
							%>
							<nested:select property="strInspectionCondition" styleId="strInspectionCondition" onchange="setDirtyFlg();">
								<nested:options name="selectInspectionCondition" property="values" labelName="selectInspectionCondition" labelProperty="labels" />
							</nested:select>
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="160">基準値2</td>
						<td colspan="2">
							<nested:text property="value2" styleId="value2" maxlength="20" size="20" onchange="setDirtyFlg();" style="ime-mode:disabled" onblur="format();"/>
							<nested:hidden property="value2Format" styleId="value2Format" />
						</td>
					</tr>
					<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="160">実績値</td>
						<td>
							<nested:text property="resultValue1" styleId="resultValue1" maxlength="20" size="20" onchange="setDirtyFlg();"  onblur="format();"/>
							<nested:hidden property="result1Format" styleId="result1Format" />
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="160">備考</td>
						<td colspan="2">
							<nested:textarea property="remark" rows="5" cols="60" styleId="remark" onchange="setDirtyFlg();" />
						</td>
					</tr>
					<tr>
						<td class="fcTitleDetail fb bcTitleDetail" width="160">注釈</td>
						<td colspan="2">
							<nested:textarea property="notes" rows="5" cols="60" styleId="notes" onchange="setDirtyFlg();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="alignCenter">
				<table cellpadding="0" cellspacing="0" width="450" border="0">
					<tr>
						<td colspan="3" height="2"></td>
					</tr>
					<tr>
						<nested:notEqual property="directionStatus" value="7">
							<nested:equal property="updateAuthority" value="true">
								<td class="alignCenter">
									<div id="cssButton">
									<a href="#" onclick="if (!(putUpdate())) {return false;} else {return form_submit('op', 'update'); return false;}"
																		class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
									</a>
									</div>
								</td>
							</nested:equal>
                       	</nested:notEqual>
						<td class="alignCenter">
							<div id="cssButton">
							<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
								&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
							</a>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</nested:form>

</body>
</html:html>

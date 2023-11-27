<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf"%>
<%@ include file="/jsp/common/searchNameForMaster.jsf"%>

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

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strResultSDay");
		defineAsRequiredField("strResultEDay");
		defineAsRequiredField("strResultSTime");
		defineAsRequiredField("strResultETime");

		<%-- 数値フィールド定義 --%>
        defineAsNumberField("strConditionTemp");
        defineAsNumberField("strResultConditionTemp");
        defineAsNumberField("strConditionTime");
        defineAsNumberField("strStirSpeed1");
        defineAsNumberField("strResultStirSpeed");
        defineAsNumberField("strStirSpeed2");
        defineAsNumberField("strWaterWeight");
        defineAsNumberField("strPh");
        defineAsNumberField("strResultPh");
        
		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strResultSDay");
		defineAsDateField("strResultEDay");
		
		<%-- 時間型フィールド定義 --%>
		defineAsTimeField("strResultSTime");
		defineAsTimeField("strResultETime");
		storeInitValues('dirtyFlg');

		<%-- 検索後入力された場合の不整合をただす --%>
	}, false);

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

	<%-- RECIPE1のフォーマット --%>
	function formatRecipe1(obj) {
		var value = obj.value;
		if (value != null) {
			var decimalPoint = $("decimalPointRecipe1").value;
			var roundDivision = $("roundDivisionRecipe1").value;
			obj.value = digitStringFormat(decimalPoint,roundDivision,value);
		}
	}

	<%-- RECIPE2のフォーマット --%>
	function formatRecipe2(obj) {
		var value = obj.value;
		if (value != null) {
			var decimalPoint = $("decimalPointRecipe2").value;
			var roundDivision = $("roundDivisionRecipe2").value;
			obj.value = digitStringFormat(decimalPoint,roundDivision,value);
		}
	}

	<%-- SONOTAのフォーマット --%>
	function formatSonota(obj) {
		var value = obj.value;
		if (value != null) {
			var decimalPoint = $("decimalPointSonota").value;
			var roundDivision = $("roundDivisionSonota").value;
			obj.value = digitStringFormat(decimalPoint,roundDivision,value);
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>
<%
request.setCharacterEncoding("UTF-8");
%>

<nested:form action="/RdirectionProcedureDetail" method="post"
	onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<%-- 小数点桁数、端数区分 --%>
	<nested:hidden property="decimalPointRecipe1"
		styleId="decimalPointRecipe1" />
	<nested:hidden property="roundDivisionRecipe1"
		styleId="roundDivisionRecipe1" />
	<nested:hidden property="decimalPointRecipe2"
		styleId="decimalPointRecipe2" />
	<nested:hidden property="roundDivisionRecipe2"
		styleId="roundDivisionRecipe2" />
	<nested:hidden property="decimalPointSonota"
		styleId="decimalPointSonota" />
	<nested:hidden property="roundDivisionSonota"
		styleId="roundDivisionSonota" />


	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">製造実績工程詳細</td>
							<td>
							<table cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td><%@ include file="/jsp/common/disp_info_message.jsf"%>
									<%-- メッセージ表示は、共通のメッセージ表示jsfをincludeする --%></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="5" colspan="2"></td>
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
					<td><%-- メッセージ表示 --%> <%@ include
						file="/jsp/common/disp_error_message.jsf"%>
					<%-- メッセージ表示 ここまで --%></td>
				</tr>

				<tr>
					<td><!-- 明細部 -->
					<table width="750" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="100">製造指図番号</td>
							<td width="155"><nested:write property="directionNo" /></td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="100">品目コード</td>
							<td><nested:write property="itemCd" /></td>
							<td class="fcTitleDetail fb bcTitleDetail" width="80">品目名称</td>
							<td><nested:write property="itemName" /></td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="100">工程順序</td>
							<td>
							<div id="lblRefStepNo"><nested:write property="strSeq" />
							</div>
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
				<tr>
					<td><!-- 明細部 -->
					<table width="" cellspacing="2" cellpadding="1" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="130">工程コード</td>
							<td width="130"><nested:write property="operationCd" /></td>

							<td class="fcTitleDetail fb bcTitleDetail" width="130">工程名称</td>
							<td><nested:write property="operationName" /></td>
						</tr>

						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="130">工程開始実績日時</td>
							<td><nested:text property="strResultSDay" maxlength="10"
								size="12" styleId="strResultSDay" onchange="setDirtyFlg();"
								style="ime-mode:disabled" /> <nested:text
								property="strResultSTime" maxlength="5" size="7"
								styleId="strResultSTime" onchange="setDirtyFlg();"
								style="ime-mode:disabled" /></td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="130">工程終了実績日時</td>
							<td><nested:text property="strResultEDay" maxlength="10"
								size="12" styleId="strResultEDay" onchange="setDirtyFlg();"
								style="ime-mode:disabled" /> <nested:text
								property="strResultETime" maxlength="5" size="7"
								styleId="strResultETime" onchange="setDirtyFlg();"
								style="ime-mode:disabled" /></td>
						</tr>

					</table>

					<table width="750" cellspacing="2" cellpadding="1" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" rowspan="9"
								width="130">工程条件</td>
						</tr>
						<td>
						<table width="100%" cellspacing="2" cellpadding="1" border="0">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="140">温度</td>
								<td><nested:text property="strConditionTemp"
									styleId="strConditionTemp" maxlength="22" size="15"
									readonly="true" /> ℃</td>
								<td><nested:text property="strResultConditionTemp"
									styleId="strResultConditionTemp" maxlength="22" size="15"
									style="ime-mode:disabled" onblur="return formatRecipe2(this);"
									onchange="setDirtyFlg();" /> ℃</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="140">時間</td>
								<td><nested:text property="strConditionTime"
									styleId="strConditionTime" maxlength="22" size="15"
									readonly="true" /> 分</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="140">攪拌速度1</td>
								<td><nested:text property="strStirSpeed1"
									styleId="strStirSpeed1" maxlength="22" size="15"
									readonly="true" /> Hz</td>
								<td><nested:text property="strResultStirSpeed"
									styleId="strResultStirSpeed" maxlength="22" size="15"
									style="ime-mode:disabled" onblur="return formatRecipe1(this);"
									onchange="setDirtyFlg();" /> Hz</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="140">攪拌速度2</td>
								<td><nested:text property="strStirSpeed2"
									styleId="strStirSpeed2" maxlength="22" size="15"
									readonly="true" /> Hz</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="140">洗浄水絶対重量</td>
								<td><nested:text property="strWaterWeight"
									styleId="strWaterWeight" maxlength="22" size="15"
									readonly="true" /> kg</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="140">pH</td>
								<td><nested:text property="strPh" styleId="strPh"
									maxlength="22" size="15" readonly="true" /></td>
								<td><nested:text property="strResultPh"
									styleId="strResultPh" maxlength="22" size="15"
									style="ime-mode:disabled" onblur="return formatRecipe2(this);"
									onchange="setDirtyFlg();" /></td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="140">
								本流/予備溶解</td>
								<td>
								<%
											pageContext.setAttribute("selectMainStream",
											new com.asahikaseieng.app.comboboxes.SelectMainStream(
											false, false));
								%> <nested:select property="strMainStream"
									styleId="strMainStream" onchange="setDirtyFlg();">
									<nested:options name="selectMainStream" property="values"
										labelName="selectMainStream" labelProperty="labels" />
								</nested:select></td>
								<td></td>
							</tr>
						</table>
						</td>
					</table>

					<table width="750" cellspacing="2" cellpadding="1" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="130">工程条件自由入力</td>
							<td><nested:textarea property="condition" rows="5" cols="60"
								styleId="condition" onchange="setDirtyFlg();" /></td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="130">備考</td>
							<td><nested:textarea property="procedureRemark" rows="5"
								cols="60" styleId="procedureRemark" onchange="setDirtyFlg();" />
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail" width="130">注釈</td>
							<td><nested:textarea property="procedureNotes" rows="5"
								cols="60" styleId="procedureNotes" onchange="setDirtyFlg();" />
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
							<nested:lessEqual property="directionStatus" value="7">
								<nested:equal property="updateAuthority" value="true">
									<td class="alignCenter">
									<div id="cssButton"><a href="#"
										onclick="if (!(putUpdate())) {return false;}else{return form_submit('op', 'update'); return false;}"
										class="cssButton">&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp; </a>
									</div>
									</td>
								</nested:equal>
							</nested:lessEqual>
							<td width="50"><br>
							</td>

							<td class="alignCenter">
							<div id="cssButton"><a href="#"
								onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}"
								class="cssButton">&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp; </a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</nested:form>

</body>
</html:html>

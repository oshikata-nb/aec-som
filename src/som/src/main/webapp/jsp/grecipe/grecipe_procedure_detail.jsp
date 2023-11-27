<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
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

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("operationCd");

		<%-- 数値型フィールド定義 --%>
		<logic:equal name="grecipeProcedureDetailForm" property="recipeUse" value="3">
			defineAsNumberField("strConditionTemp");
			defineAsNumberField("strConditionTime");
			defineAsNumberField("strStirSpeed1");
			defineAsNumberField("strStirSpeed2");
			defineAsNumberField("strWaterWeight");
		</logic:equal>
		<logic:equal name="grecipeProcedureDetailForm" property="recipeUse" value="4">
			defineAsNumberField("strNet");
			defineAsNumberField("strWeightMin");
			defineAsNumberField("strWeightMax");
			defineAsNumberField("strFilter");
			defineAsNumberField("strMesh");
			defineAsNumberField("strAutoCheckerMin");
			defineAsNumberField("strAutoCheckerMax");
			defineAsNumberField("strGrossCheckerMin");
			defineAsNumberField("strGrossCheckerMax");
			defineAsNumberField("strAutoCheckerAve");
			defineAsNumberField("strGrossCheckerAve");
			defineAsNumberField("strOpeningTorqueMin");
			defineAsNumberField("strOpeningTorqueMax");
			defineAsNumberField("strClosingTorqueMin");
			defineAsNumberField("strClosingTorqueMax");
			defineAsNumberField("strTorquePressure");
			defineAsNumberField("strAirPressure");
			defineAsNumberField("strVcloseTime");
			defineAsNumberField("strHotAirPresetTemp");
			defineAsNumberField("strHotAirPressure");
			defineAsNumberField("strFirstHeatSeal");
			defineAsNumberField("strSecondHeatSeal");
		</logic:equal>

		storeInitValues('dirtyFlg');

		<%-- 工程名称のauto complete --%>
		new Ajax.Autocompleter(
		  "operationCd",
		  "autocomplete_operation",
		  "<%= request.getContextPath() %>/OperationForAutoComplete.do?op=searchOperation",
		  {
		  	paramName : "code",
		  	parameters : 'recipeUse='+ $('recipeUse').value,
		    afterUpdateElement : setOperationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('operationCd', 'keypress', clearLabel.bindAsEventListener($('operationName')), false);

	}, false);

	<%-- 工程名称auto completeの選択後処理 --%>
	function setOperationLabel(text, li) {
	    $("lblOperationName").update(li.getElementsByTagName('span')[0].innerText);
	    $("operationName").value = li.getElementsByTagName('span')[0].innerText;
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

	<%-- RECIPE5のフォーマット --%>
	function formatRecipe5(obj) {
		var value = obj.value;
		if (value != null) {
			var decimalPoint = $("decimalPointRecipe5").value;
			var roundDivision = $("roundDivisionRecipe5").value;
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
<nested:form action="/GrecipeProcedureDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="recipeId" styleId="recipeId" />
	<nested:hidden property="approvalStatus" styleId="approvalStatus" />
	<nested:hidden property="recipeUse" styleId="recipeUse" />
	<nested:hidden property="operationName" styleId="operationName" />
	<nested:hidden property="srhLink" styleId="srhLink" />
	<%-- 小数点桁数、端数区分 --%>
	<nested:hidden property="decimalPointRecipe1" styleId="decimalPointRecipe1" />
	<nested:hidden property="roundDivisionRecipe1" styleId="roundDivisionRecipe1" />
	<nested:hidden property="decimalPointRecipe2" styleId="decimalPointRecipe2" />
	<nested:hidden property="roundDivisionRecipe2" styleId="roundDivisionRecipe2" />
	<nested:hidden property="decimalPointRecipe5" styleId="decimalPointRecipe5" />
	<nested:hidden property="roundDivisionRecipe5" styleId="roundDivisionRecipe5" />
	<nested:hidden property="decimalPointSonota" styleId="decimalPointSonota" />
	<nested:hidden property="roundDivisionSonota" styleId="roundDivisionSonota" />

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
									<td class="fcTitle fb">工程情報詳細</td>
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
							<%-- メッセージ表示 --%> <%@ include file="/jsp/common/disp_error_message.jsf"%>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>

					<%-- ヘッダー部 --%>
					<tr>
						<td>
							<table cellspacing="2" cellpadding="2" border="0" width="100%">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">原処方ｺｰﾄﾞ</td>
									<td width="150">
										<nested:write property="recipeCd" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">バージョン</td>
									<td>
										<nested:write property="recipeVersion" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">品目コード</td>
									<td width="155">
										<nested:write property="product" />
									</td>
					
									<td class="fcTitleDetail fb bcTitleDetail" width="120">品目名称</td>
									<td>
										<nested:write property="itemName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">工程順序</td>
									<td width="155">
										<nested:write property="seq" />
									</td>
									<nested:equal property="recipeUse" value="3"><%-- 製造時 --%>
										<td class="fcTitleDetail fb bcTitleDetail" width="120">配合率計</td>
										<td>
											<nested:write property="sumQty" />%
										</td>
									</nested:equal>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">承認ステータス</td>
									<td width="155">
										<nested:write property="approvalStatusName" />
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
									<td class="fcTitleDetail fb bcTitleDetail" width="160">工程コード</td>
									<td width="120">
										<div id="autocomplete_operation" class="autocomplete"></div>
										<nested:text property="operationCd" styleId="operationCd" maxlength="10" size="10" onchange="setDirtyFlg();"/>
									</td>
									<td>
										<div id="lblOperationName">
										<nested:write property="operationName" />
										</div>
									</td>
								</tr>
							</table>

							<nested:equal property="recipeUse" value="3"><%-- 製造時 --%>
							<table width="" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160" rowspan="7">工程条件</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">温度</td>
									<td width="120">
										<nested:text property="strConditionTemp" styleId="strConditionTemp" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe2(this);" style="ime-mode:disabled"/>
										℃
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">時間</td>
									<td width="120">
										<nested:text property="strConditionTime" styleId="strConditionTime" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe1(this);" style="ime-mode:disabled"/>
										分
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">攪拌速度1</td>
									<td width="120">
										<nested:text property="strStirSpeed1" styleId="strStirSpeed1" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe1(this);" style="ime-mode:disabled"/>
										Hz
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">攪拌速度2</td>
									<td width="120">
										<nested:text property="strStirSpeed2" styleId="strStirSpeed2" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe1(this);" style="ime-mode:disabled"/>
										Hz
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">洗浄水絶対重量</td>
									<td width="120">
										<nested:text property="strWaterWeight" styleId="strWaterWeight" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatSonota(this);" style="ime-mode:disabled"/>
										Kg
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">本流/予備溶解</td>
									<td width="120">
										<%
											pageContext.setAttribute("selectMainStream",
											new com.asahikaseieng.app.comboboxes.SelectMainStream(false, false));
										%>
										<nested:select property="strMainStream" styleId="strMainStream" onchange="setDirtyFlg();">
											<nested:options name="selectMainStream" property="values" labelName="selectMainStream" labelProperty="labels" />
										</nested:select>
									</td>
								</tr>
							</table>
							</nested:equal>

							<nested:equal property="recipeUse" value="4"><%-- 包装時 --%>
							<table width="" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">正味質量(Net)</td>
									<td width="140">
										<nested:text property="strNet" styleId="strNet" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">量目管理幅最小</td>
									<td width="140">
										<nested:text property="strWeightMin" styleId="strWeightMin" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">量目管理幅最大</td>
									<td width="140">
										<nested:text property="strWeightMax" styleId="strWeightMax" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">濾過用フィルター</td>
									<td width="140">
										<nested:text property="strFilter" styleId="strFilter" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										μm
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">濾過用メッシュ</td>
									<td width="150">
										<nested:text property="strMesh" styleId="strMesh" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										メッシュ
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ｵｰﾄﾁｪｯｶｰ最小</td>
									<td width="140">
										<nested:text property="strAutoCheckerMin" styleId="strAutoCheckerMin" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ｵｰﾄﾁｪｯｶｰ最大</td>
									<td width="140">
										<nested:text property="strAutoCheckerMax" styleId="strAutoCheckerMax" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ｸﾞﾛｽﾁｪｯｶｰ最小</td>
									<td width="140">
										<nested:text property="strGrossCheckerMin" styleId="strGrossCheckerMin" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ｸﾞﾛｽﾁｪｯｶｰ最大</td>
									<td width="140">
										<nested:text property="strGrossCheckerMax" styleId="strGrossCheckerMax" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ｵｰﾄﾁｪｯｶｰ中心</td>
									<td width="140">
										<nested:text property="strAutoCheckerAve" styleId="strAutoCheckerAve" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ｸﾞﾛｽﾁｪｯｶｰ中心</td>
									<td width="140">
										<nested:text property="strGrossCheckerAve" styleId="strGrossCheckerAve" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										g
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">開きトルク最小</td>
									<td width="140">
										<nested:text property="strOpeningTorqueMin" styleId="strOpeningTorqueMin" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										N・cm
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">開きトルク最大</td>
									<td width="140">
										<nested:text property="strOpeningTorqueMax" styleId="strOpeningTorqueMax" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										N・cm
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">締めトルク最小</td>
									<td width="140">
										<nested:text property="strClosingTorqueMin" styleId="strClosingTorqueMin" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										N・cm
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">締めトルク最大</td>
									<td width="140">
										<nested:text property="strClosingTorqueMax" styleId="strClosingTorqueMax" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										N・cm
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">トルク圧</td>
									<td width="140">
										<nested:text property="strTorquePressure" styleId="strTorquePressure" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										MPa
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">エアー圧</td>
									<td width="140">
										<nested:text property="strAirPressure" styleId="strAirPressure" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										MPa
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">巻締め時間</td>
									<td width="140">
										<nested:text property="strVcloseTime" styleId="strVcloseTime" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										秒
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ﾎｯﾄｴｱｰ設定温度</td>
									<td width="140">
										<nested:text property="strHotAirPresetTemp" styleId="strHotAirPresetTemp" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										℃
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">ﾎｯﾄｴｱｰ吹き出し圧力</td>
									<td width="140">
										<nested:text property="strHotAirPressure" styleId="strHotAirPressure" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">第一ﾋｰﾄｼｰﾙ設定温度</td>
									<td width="140">
										<nested:text property="strFirstHeatSeal" styleId="strFirstHeatSeal" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										℃
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">第二ﾋｰﾄｼｰﾙ設定温度</td>
									<td width="140">
										<nested:text property="strSecondHeatSeal" styleId="strSecondHeatSeal" maxlength="22" size="10" onchange="setDirtyFlg();" onblur="return formatRecipe5(this);" style="ime-mode:disabled"/>
										℃
									</td>
								</tr>
							</table>
							</nested:equal>

							<table width="750" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">工程条件自由入力</td>
									<td>
										<nested:textarea property="condition" rows="5" cols="60" styleId="condition" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">備考</td>
									<td>
										<nested:textarea property="remark" rows="5" cols="60" styleId="remark" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">注釈</td>
									<td>
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
									<nested:notEqual property="srhLink" value="1">
										<nested:equal property="approvalStatus" value="1">
											<nested:equal property="updateAuthority" value="true">
												<td class="alignCenter">
													<div id="cssButton">
														<a href="#" onclick="if (!(putUpdate())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
															&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</nested:equal>
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
			</td>
		</tr>
	</table>

</nested:form>

</body>
</html:html>

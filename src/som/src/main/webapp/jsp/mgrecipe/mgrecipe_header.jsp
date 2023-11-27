<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>

		initializeFormState();

		<%-- 必須フィールド定義 --%>
		<logic:lessEqual name="mgrecipeHeaderForm" property="mode" value="2">
			defineAsRequiredField("recipeCd");
			defineAsRequiredField("recipeVersion");
		</logic:lessEqual>
		<logic:equal name="mgrecipeHeaderForm" property="mode" value="0">
			defineAsRequiredField("inputProduct");
		</logic:equal>
		defineAsRequiredField("inputRecipeName");
		defineAsRequiredField("startDate");
		defineAsRequiredField("endDate");
		defineAsRequiredField("stdQty");
		defineAsRequiredField("unitQty");
		defineAsRequiredField("minQty");
		defineAsRequiredField("maxQty");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("startDate");
		defineAsDateField("endDate");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("recipePriority");
		defineAsNumberField("stdQty");
		defineAsNumberField("unitQty");
		defineAsNumberField("minQty");
		defineAsNumberField("maxQty");

		<%-- 品目名称のauto complete --%>
		<logic:equal name="mgrecipeHeaderForm" property="mode" value="0">
			new Ajax.Autocompleter(
			  "inputProduct",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailDigitItemQueue",
			  {
			  	paramName : "code",
			    afterUpdateElement : setItemQueueLabel
			  }
			);
			Event.observe("inputProduct", 'keypress', clearDivLabel.bindAsEventListener($('itemName')), false);
			Event.observe("inputProduct", 'keypress', clearDivLabel.bindAsEventListener($('unitName')), false);
		</logic:equal>

		<%-- 会計部門のautocomplete --%>
		new Ajax.Autocompleter(
			"sectionCd",
			"autocomplete_bumon",
			"<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
			{
				paramName : "code",
				afterUpdateElement : setBumonLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('sectionCd', 'keypress', clearLabel.bindAsEventListener($('sectionName')), false);

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();


	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 用途変更時イベント --%>
	function changeUseAction(){
		setDirtyFlg();
		return form_submit('op', 'changeUse');
	}
	<%-- 削除確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
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
	<%-- 承認依頼実行確認メッセージ --%>
	function putApprovalRequest(){
		alertMsg = new Array();
		alertMsg[0] = "承認依頼を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 承認確認メッセージ --%>
	function putApprovalConfirm(){
		alertMsg = new Array();
		alertMsg[0] = "承認を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 否認実行確認メッセージ --%>
	function putApprovalReject(){
		alertMsg = new Array();
		alertMsg[0] = "否認を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 承認取消実行確認メッセージ --%>
	function putApprovalCancel(){
		alertMsg = new Array();
		alertMsg[0] = "承認取消を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "印刷してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("itemName").update(li.getElementsByTagName('span')[0].innerText);
	    var unit = getHiddenValue(li,"unitOfOperationManagement");
	    $("unitDivision").value = unit;		<%-- validate用にhiddenに単位区分を格納 --%>
		<%-- 単位名称 --%>
	    var unitName = getHiddenValue(li,"name01");
	    $("unitName").value = unitName;		<%-- validate時の再表示の為にhiddenに単位名称を格納 --%>

	    $("unitName1").update(unitName);
	    $("unitName2").update(unitName);
	    $("unitName3").update(unitName);
	    $("unitName4").update(unitName);

		formatHaigo();
	}

	function setBumonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("sectionName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function formatHaigo() {
		<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
	    var decPoint = $F("smallnumLength");
	    var round = $F("roundDivision");
		$("stdQty").value = digitStringFormat(decPoint,round,$F("stdQty"));
		$("unitQty").value = digitStringFormat(decPoint,round,$F("unitQty"));
		$("minQty").value = digitStringFormat(decPoint,round,$F("minQty"));
		$("maxQty").value = digitStringFormat(decPoint,round,$F("maxQty"));

	}

	<%-- 配合再計算チェック --%>
	function setCalc() {
	    var decPoint = $F("smallnumLength");
	    var round = $F("roundDivision");
		var str1 = $("stdQty").value.replace(/\,/g, "");
		var str2 = digitStringFormat(decPoint,round,$F("orgStdQty"));
		str2 = str2.replace(/\,/g, "");

		if (str1 == str2) {
			$("calc").checked = false;
		} else {
			$("calc").checked = true;
		}
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/MgrecipeHeader" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>
	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="mode" />
	<nested:hidden property="recipeId" styleId="recipeId" />
	<nested:hidden property="unitName" styleId="unitName" /><%-- 単位名称 --%>
	<nested:hidden property="unitDivision" styleId="unitDivision" /><%-- 桁数区分 --%>
	<nested:hidden property="smallnumLength" styleId="smallnumLength" />
	<nested:hidden property="roundDivision" styleId="roundDivision" />
	<nested:hidden property="srhLink" styleId="srhLink" />
	<nested:hidden property="orgStdQty" styleId="orgStdQty" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">基本処方</td>
							<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<%-- メッセージ表示 --%>
										<%@ include file="/jsp/common/disp_info_message.jsf"%>
										<%-- メッセージ表示 ここまで --%>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="bcTitleLine" colspan="2"></td>
						</tr>
						<tr>
							<td height= 5  colspan= 2 ></td>
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

<%-- 共通項目部>>>>> --%>
				<tr>
					<td>
						<table cellspacing="2" cellpadding="2" border="0" width="100%">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">基本処方ｺｰﾄﾞ</td>
								<td width="150">
									<nested:lessEqual property="mode" value="2">
										<nested:text property="recipeCd" maxlength="20" size="20" styleId="recipeCd" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="bcTitleDefine"/>
									</nested:lessEqual>
									<nested:greaterEqual property="mode" value="3">
										<nested:write property="recipeCd" />
									</nested:greaterEqual>
								</td>

								<td class="fcTitleDetail fb bcTitleDetail" width="120">バージョン</td>

								<td>
									<nested:lessEqual property="mode" value="2">
										<nested:text property="recipeVersion" maxlength="4" size="20" styleId="recipeVersion" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="bcTitleDefine"/>
									</nested:lessEqual>
									<nested:greaterEqual property="mode" value="3">
										<nested:write property="recipeVersion" />
									</nested:greaterEqual>
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
									<nested:write property="product" />
								</td>

								<td class="fcTitleDetail fb bcTitleDetail" width="120">品目名称</td>
								<td>
									<nested:write property="itemName" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">生産工場</td>
								<td width="155">
									<nested:write property="productionLineName" />
								</td>

								<td class="fcTitleDetail fb bcTitleDetail" width="120">用途</td>
								<td>
									<nested:equal property="mode" value="0">
										<%
											pageContext.setAttribute( "selectRecipeUse",new com.asahikaseieng.app.comboboxes.SelectRecipeUse(false, false, true));
										%>
										<nested:select property="recipeUse" styleId="recipeUse" onchange="changeUseAction();">
											<nested:options name="selectRecipeUse" property="values" labelName="selectRecipeUse" labelProperty="labels" />
										</nested:select>
									</nested:equal>
									<nested:notEqual property="mode" value="0">
										<nested:write property="recipeUseName" />
									</nested:notEqual>
								</td>
							</tr>
							<nested:greaterEqual property="mode" value="3">
								<nested:equal property="recipeUse" value="4">
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail" width="120">荷姿</td>
										<td colspan="3">
											<nested:write property="styleOfPacking" />
										</td>
									</tr>
								</nested:equal>
							</nested:greaterEqual>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">承認ステータス</td>
								<td width="155">
									<nested:write property="approvalStatusName" />
								</td>

								<nested:greaterEqual property="mode" value="3">
									<td class="alignRight" colspan="2">
										<table cellspacing="1" cellpadding="1"  border="0">
											<tr>
												<div id="cssButton">
													<a href="#"
														onclick="if (!(reportConfirm())) {return false;}else{return check_form_submit('op', 'report'); return false;}"
														class="cssButton">&nbsp;&nbsp;印&nbsp;&nbsp;刷&nbsp;&nbsp;
													</a>
												</div>
												<nested:notEqual property="srhLink" value="1">
													<td>
														<div id="cssButton">
															<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'copy'); return false;}" class="cssButton">コピー作成</a>
														</div>
													</td>
												</nested:notEqual>
											</tr>
										</table>
									</td>
								</nested:greaterEqual>
							</tr>
						</table>
					</td>
				</tr>
<%-- 共通項目部<<<<< --%>
				<tr>
					<td></td>
				</tr>
<%-- タブ --%>
				<tr>
					<td>
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
								<nested:lessEqual property="mode" value="2">
									<span id="slidetabsmenu">
										<li class="current"><a title="ヘッダー情報"><span>ヘッダー情報</span></a></li>
									</span>
								</nested:lessEqual>
								<nested:greaterEqual property="mode" value="3">
									<%@ include file="/jsp/mgrecipe/mgrecipe_tab.jsp"%>
								</nested:greaterEqual>
 							</td>
						</tr>
					</table>
				</tr>

<%-- 詳細内容 --%>
				<tr>
					<td>
					<span id="slidetabs" style="clear: left;">
						<table border="0" cellspacing="2" cellpadding="2" width="100%">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">登録日</td>
								<td colspan="3">
									<nested:write property="inputDate" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">基本処方名称</td>
								<td colspan="3">
									<nested:text property="inputRecipeName" size="40" maxlength="40" styleId="inputRecipeName" onchange="setDirtyFlg();" />
								</td>
							</tr>
							<nested:equal property="mode" value="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">品目コード</td>
									<td>
										<nested:text property="inputProduct" maxlength="20" size="20" styleId="inputProduct" onchange="setDirtyFlg();" styleClass="bcTitleDefine"/>
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">品目名称</td>
									<td>
										<div id="itemName"><nested:write property="inputProductName" /></div>
									</td>
								</tr>
							</nested:equal>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">ステータス</td>
								<td>
									<nested:select property="recipeStatus" style="margin: 0;padding: 0" styleId="productionLine" onchange="setDirtyFlg();">
										<nested:optionsCollection property="statusCombo" label="labales" value="values" />
									</nested:select>
								</td>
								<nested:equal property="recipeUse" value="3">
								<td class="fcTitleDetail fb bcTitleDetail" width="130">収率</td>
								<td>
									<nested:write property="processLoss"/>&nbsp;%
								</td>
								</nested:equal>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">生産工場</td>
								<td>
									<nested:select property="inputProductionLine" style="margin: 0;padding: 0" styleId="inputProductionLine" onchange="setDirtyFlg();">
										<nested:optionsCollection property="lineCombo" label="labales" value="values" />
									</nested:select>
								</td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">優先度</td>
								<td>
									<nested:text property="recipePriority" maxlength="2" size="20" styleId="recipePriority" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignRight"/>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" rowspan="2" width="120">標準生産量</td>
								<td>
									<nested:text property="stdQty" maxlength="22" size="20" styleId="stdQty" onchange="setDirtyFlg();" onblur="formatHaigo(); setCalc();" style="ime-mode:disabled" styleClass="alignRight"/>
									<span id="unitName1"><nested:write property="unitName"/></span>
								</td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">単位生産量</td>
								<td>
									<nested:text property="unitQty" maxlength="22" size="20" styleId="unitQty" onchange="setDirtyFlg();" onblur="formatHaigo();" style="ime-mode:disabled" styleClass="alignRight"/>
									<span id="unitName2"><nested:write property="unitName"/></span>
								</td>
							</tr>
							<tr>
								<td>
									<nested:checkbox property="calc" onchange="setDirtyFlg();" styleId="calc"/>配合再計算
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">最低生産量</td>
								<td>
									<nested:text property="minQty" maxlength="22" size="20" styleId="minQty" onchange="setDirtyFlg();" onblur="formatHaigo();" style="ime-mode:disabled" styleClass="alignRight"/>
									<span id="unitName3"><nested:write property="unitName"/></span>
								</td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">最高生産量</td>
								<td>
									<nested:text property="maxQty" maxlength="22" size="20" styleId="maxQty" onchange="setDirtyFlg();" onblur="formatHaigo();" style="ime-mode:disabled" styleClass="alignRight"/>
									<span id="unitName4"><nested:write property="unitName"/></span>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail">会計部門</td>
								<td>
									<nested:text property="sectionCd" maxlength="10" size="10" styleId="sectionCd" onchange="setDirtyFlg();" />
									<div id="autocomplete_bumon" class="autocomplete"></div>
								</td>

								<td>
									<div id="lblSectionName">
										<nested:write property="sectionName" />
									</div>
									<nested:hidden property="sectionName" styleId="sectionName"/>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">開始有効日</td>
								<td>
									<nested:text property="startDate" maxlength="10" size="10" styleId="startDate" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="bcTitleDefine"/>
								</td>
								<nested:equal property="recipeUse" value="3">
									<td class="fcTitleDetail fb bcTitleDetail" width="130">配合量</td>
									<nested:greaterEqual property="mode" value="2">
										<td>
											<nested:write property="sumQty"/>&nbsp;Kg
										</td>
									</nested:greaterEqual>
								</nested:equal>
								<nested:notEqual property="recipeUse" value="3">
									<td></td>
									<td></td>
								</nested:notEqual>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">終了有効日</td>
								<td>
									<nested:text property="endDate" maxlength="10" size="10" styleId="endDate" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="bcTitleDefine"/>
								</td>
								<nested:equal property="recipeUse" value="3">
									<td class="fcTitleDetail fb bcTitleDetail" width="130">洗浄水絶対重量計</td>
									<td>
										<nested:greaterEqual property="mode" value="2">
											<nested:write property="waterWeight"/>
											<nested:notEmpty property="waterWeight">&nbsp;Kg</nested:notEmpty>
										</nested:greaterEqual>
									</td>
								</nested:equal>
								<nested:notEqual property="recipeUse" value="3">
									<td></td>
									<td></td>
								</nested:notEqual>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">備考</td>
								<td colspan="3">
									<nested:textarea property="recipeMemo" cols="60" rows="6" styleId="recipeMemo" onchange="setDirtyFlg();"/>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">注釈</td>
								<td colspan="3">
									<nested:textarea property="recipeDescription" cols="60" rows="6" styleId="recipeDescription" onchange="setDirtyFlg();"/>
								</td>
							</tr>
							<nested:equal property="mode" value="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">工程パターン</td>
									<td colspan="3">
										<nested:select property="operationPattern" style="margin: 0;padding: 0" styleId="operationPattern" onchange="setDirtyFlg();">
											<nested:optionsCollection property="operationCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
							</nested:equal>
						</table>
					</span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table>
						<tr>
							<nested:notEqual property="srhLink" value="1">
								<nested:greaterEqual property="mode" value="3">
									<nested:equal property="approvalStatus" value="1">
										<nested:equal property="approvalRequestAuthority" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if (!(putApprovalRequest())) {return false;}else{return form_submit('op', 'approvalRequest'); return false;}" class="cssButton">
													&nbsp;&nbsp;承&nbsp;認&nbsp;依&nbsp;頼&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>
									<nested:equal property="approvalStatus" value="2">
										<nested:equal property="approvalAuthority" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if (!(putApprovalConfirm())) {return false;}else{return form_submit('op', 'approval'); return false;}" class="cssButton">
													&nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
										<nested:equal property="negationAuthority" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if (!(putApprovalReject())) {return false;}else{return form_submit('op', 'reject'); return false;}" class="cssButton">
													&nbsp;&nbsp;否&nbsp;&nbsp;認&nbsp;&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>
									<nested:equal property="approvalStatus" value="3">
										<nested:equal property="approvalCancelAuthority" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if (!(putApprovalCancel())) {return false;}else{return form_submit('op', 'approvalCancel'); return false;}" class="cssButton">
													&nbsp;&nbsp;承&nbsp;認&nbsp;取&nbsp;消&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>
								</nested:greaterEqual>
								<nested:lessEqual property="approvalStatus" value="1">
									<nested:equal property="updateAuthority" value="true">
										<td class="alignLeft">
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>
									</nested:equal>
								</nested:lessEqual>
								<nested:greaterEqual property="mode" value="3">
									<nested:equal property="approvalStatus" value="1">
										<nested:equal property="deleteAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
													&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>
								</nested:greaterEqual>
								<td class="allRight">
									<div id="cssButton">
										<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
											&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
										</a>
									</div>
								</td>
							</nested:notEqual>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>

</nested:form>
</body>
</html:html>

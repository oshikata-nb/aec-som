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
		<logic:lessEqual name="grecipeHeaderForm" property="mode" value="2">
			defineAsRequiredField("recipeCd");
			defineAsRequiredField("recipeVersion");
		</logic:lessEqual>
		defineAsRequiredField("inputProduct");
		defineAsRequiredField("inputRecipeName");
		defineAsRequiredField("startDate");
		defineAsRequiredField("endDate");
		defineAsRequiredField("stdQty");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("startDate");
		defineAsDateField("endDate");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("stdQty");

		<%-- 品目名称のauto complete --%>
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
		Event.observe("inputProduct", 'keypress', clearText.bindAsEventListener($('unitName')), false);

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
	function changeUseAction(statusCombo){
		setDirtyFlg();
		<logic:equal name="grecipeHeaderForm" property="mode" value="3">
			return useChange(statusCombo);
		</logic:equal>
		<logic:notEqual name="grecipeHeaderForm" property="mode" value="3">
			return form_submit('op', 'changeUse');
		</logic:notEqual>
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

		formatHaigo();
	}

	<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
	function formatHaigo() {
	    var decPoint = $F("smallnumLength");
	    var round = $F("roundDivision");
		$("stdQty").value = digitStringFormat(decPoint,round,$F("stdQty"));
	}
	function useChange(statusCombo){
		var target = $("sumQty1");
		var target2 = $("sumQty2");
		if(target != null){
			if(statusCombo.value == "3"){
				target.setStyle({"display" : "block"});
				target2.setStyle({"display" : "block"});
			}else{
				target.setStyle({"display" : "none"});
				target2.setStyle({"display" : "none"});
			}
		}
		return false;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/GrecipeHeader" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb">原処方</td>
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
								<td class="fcTitleDetail fb bcTitleDetail" width="120">原処方ｺｰﾄﾞ</td>
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
								<td class="fcTitleDetail fb bcTitleDetail" width="120">承認ステータス</td>
								<td width="155">
									<nested:write property="approvalStatusName" />
								</td>

								<nested:greaterEqual property="mode" value="3">
									<td class="alignRight" colspan="2">
										<table cellspacing="1" cellpadding="1"  border="0">
											<tr>
												<td>
													<div id="cssButton">
														<a href="#"
															onclick="if (!(reportConfirm())) {return false;}else{return check_form_submit('op', 'report'); return false;}"
															class="cssButton">&nbsp;&nbsp;印&nbsp;&nbsp;刷&nbsp;&nbsp;
														</a>
													</div>
												</td>
			
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
									<%@ include file="/jsp/grecipe/grecipe_tab.jsp"%>
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
									<nested:write property="inputDate" format="yyyy/MM/dd"/>
								</td>
							</tr>
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
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">ステータス</td>
								<td>
									<nested:select property="recipeStatus" style="margin: 0;padding: 0" styleId="recipeStatus" onchange="setDirtyFlg();">
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
								<td class="fcTitleDetail fb bcTitleDetail" width="120">用途</td>
								<td>
									<nested:equal property="mode" value="0">
										<%
											pageContext.setAttribute( "selectRecipeUse",new com.asahikaseieng.app.comboboxes.SelectRecipeUse(false, false, true));
										%>
										<nested:select property="recipeUse" styleId="recipeUse" onchange="changeUseAction(this);">
											<nested:options name="selectRecipeUse" property="values" labelName="selectRecipeUse" labelProperty="labels" />
										</nested:select>
									</nested:equal>
									<nested:notEqual property="mode" value="0">
										<nested:write property="recipeUseName" />
									</nested:notEqual>
								</td>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">仕上(%)</td>
								<td>
									<nested:text property="stdQty" maxlength="22" size="20" styleId="stdQty" onchange="setDirtyFlg();" onblur="formatHaigo();" style="ime-mode:disabled" styleClass="alignRight"/>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">開始有効日</td>
								<td>
									<nested:text property="startDate" maxlength="10" size="10" styleId="startDate" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="bcTitleDefine"/>
								</td>
								<nested:equal property="mode" value="3">
									<nested:equal property="recipeUse" value="3">
										<td class="fcTitleDetail fb bcTitleDetail" width="130" id="sumQty1">配合量</td>
										<nested:greaterEqual property="mode" value="2">
											<td id="sumQty2">
												<nested:write property="sumQty"/>
											</td>
										</nested:greaterEqual>
									</nested:equal>
									<nested:notEqual property="recipeUse" value="3">
										<td></td>
										<td></td>
									</nested:notEqual>
								</nested:equal>
								<nested:notEqual property="mode" value="3">
									<td></td>
									<td></td>
								</nested:notEqual>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">終了有効日</td>
								<td>
									<nested:text property="endDate" maxlength="10" size="10" styleId="endDate" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="bcTitleDefine"/>
								</td>
								<td></td>
								<td></td>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから　--%>
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
	    	if (continueConfirm()) {
	        	location.href=url;
				g_lock = true;
	    	}
      	}
	}

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("recipeIdVersion");
		defineAsRequiredField("compoundTankNo");
		defineAsRequiredField("planedQty");
		defineAsRequiredField("planedSdateDate");
		defineAsRequiredField("planedSdateTime");
		defineAsRequiredField("planedEdateDate");
		defineAsRequiredField("planedEdateTime");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("planedSdateDate");
		defineAsTimeField("planedSdateTime");
		defineAsDateField("planedEdateDate");
		defineAsTimeField("planedEdateTime");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("planedQty");


		<%-- フォーム設定の初期化 --%>
		initializeFormState();
		storeInitValues(/^srh.*/, 'dirtyFlg');


		<%-- 調合タンクNOオートコンプリート --%>
		new Ajax.Autocompleter(
			"compoundTankNo",
			"autocomplete_selection",
			"<%= request.getContextPath() %>/DirectionRecipeAsprovaForAutoComplete.do?op=searchRecipeAsprova",
			{
				paramName : "code",
				callback : compoundTankNoCallBack,
				afterUpdateElement : setCompoundTankNoLabel
			}
		);
		Event.observe("compoundTankNo", 'keypress', clearDivLabel.bindAsEventListener($('compoundTankNoLabel')), false);
		Event.observe("compoundTankNo", 'keypress', clearText.bindAsEventListener($('compoundTankName')), false);

		<%-- ホールドタンクNOオートコンプリート --%>
		new Ajax.Autocompleter(
			"holdTankNo",
			"autocomplete_selection",
			"<%= request.getContextPath() %>/DirectionRecipePegResouceForAutoComplete.do?op=searchHoldTank",
			{
				paramName : "code",
				callback : holdTankNoCallBack,
				afterUpdateElement : setHoldTankNoLabel
			}
		);
		Event.observe("holdTankNo", 'keypress', clearDivLabel.bindAsEventListener($('holdTankNoLabel')), false);
		Event.observe("holdTankNo", 'keypress', clearText.bindAsEventListener($('holdTankName')), false);

		<%-- 予備溶解タンクNOオートコンプリート --%>
		new Ajax.Autocompleter(
			"dissolutionTankNo",
			"autocomplete_selection",
			"<%= request.getContextPath() %>/DirectionRecipeResouceForAutoComplete.do?op=searchDissolutionTank",
			{
				paramName : "code",
				callback : dissolutionTankNoCallBack,
				afterUpdateElement : setDissolutionTankNoLabel
			}
		);
		Event.observe("dissolutionTankNo", 'keypress', clearDivLabel.bindAsEventListener($('dissolutionTankNoLabel')), false);
		Event.observe("dissolutionTankNo", 'keypress', clearText.bindAsEventListener($('dissolutionTankName')), false);
		refreshLabel();

	}, false);

	<%-- 調合タンクのオートコンプリート実行時レシピインデックス取得 --%>
	function compoundTankNoCallBack(editor,paramText){
        return paramText + '&recipeId='+ $F('recipeId');
    }
	<%-- ホールドタンクのオートコンプリート実行時調合タンクNO取得 --%>
	function holdTankNoCallBack(editor,paramText){
        return paramText + '&compoundTankNo='+ $F('compoundTankNo');
    }
	<%-- 予備溶解タンクのオートコンプリート実行時生産ライン取得 --%>
	function dissolutionTankNoCallBack(editor,paramText){
        return paramText + '&productionLine='+ $F('productionLine');
    }

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
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
	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 基本処方検索後の設定処理 --%>
	function setRecipeValues(list) {
		$("recipeIdVersion").value = list["recipeCd"] + "," + list["recipeVersion"];
		$("recipeId").value = list["recipeId"];
		$("recipeCd").value = list["recipeCd"];
		$("recipeVersion").value = list["recipeVersion"];
		$("recipeName").value = list["recipeName"];
		$("recipeNameLabel").update(list["recipeName"]);
		$("itemCd").value = list["itemCd"];
		$("itemName").value = list["itemName"];
		$("itemLabel").update(list["itemCd"] + ":" + list["itemName"]);
		$("shipperDivisionLabel").value = list["shipperDivisionName"];
		$("shipperDivisionLabelLabel").update(list["shipperDivisionName"]);
		$("otherCompanyCd1").value = list["otherCompanyCd1"];
		$("otherCompanyCd1Label").update(list["otherCompanyCd1"]);
		$("productionLine").value = list["productionLine"];
		$("productionLineName").value = list["productionLineName"];
		$("lineLabel").update(list["productionLine"] + ":" + list["productionLineName"]);
		$("unitName").value = list["unitName"];
		$("unitOfOperationManagement").value = list["unitDivision"];
		$("unitLabel").update(list["unitName"]);
		$("remark").value = list["recipeDescription"];
		$("notes").value = list["recipeMemo"];
	}

	<%-- 調合タンクauto completeの選択後処理 --%>
	function setCompoundTankNoLabel(text, li) {
		var name = li.getElementsByTagName('span')[0].innerText;
		$("compoundTankNoLabel").update(name);
		$("compoundTankName").value = name;
	}
	<%-- ホールドタンクauto completeの選択後処理 --%>
	function setHoldTankNoLabel(text, li) {
		var name = li.getElementsByTagName('span')[0].innerText;
		$("holdTankNoLabel").update(name);
		$("holdTankName").value = name;
	}
	<%-- 予備溶解タンクauto completeの選択後処理 --%>
	function setDissolutionTankNoLabel(text, li) {
		var name = li.getElementsByTagName('span')[0].innerText;
		$("dissolutionTankNoLabel").update(name);
		$("dissolutionTankName").value = name;
	}

	<%-- 仕込み予定数量のフォーマット --%>
	function formatQty() {

	    var decPoint = $('decimalPoint').value;
	    var round = $('roundDivision').value;
		var planedQty = digitFormat(decPoint,round,$F('planedQty'));
		$("planedQty").value = planedQty;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DirectionNewDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><%-- ヘッダー部 --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb" width="250">製造指図新規入力</td>
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
							<td height="5" colspan="2" ></td>
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

				<tr>
					<td>
						<table  cellspacing="2" cellpadding="1" border="0" width="100%">
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch" width="130">製造指図番号</td>
								<td colspan="3">
									<nested:write property="directionNo" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">基本処方コード</td>
								<td colspan="3" valign="middle">
									<nested:text property="recipeIdVersion" maxlength="20" size="20" styleId="recipeIdVersion" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft" readonly="true"/>
									<span id="cssButton">
										<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
										<a href="#"  class="cssButton"
											onclick="open_modal_popup_edge(700, 530,'RecipeHeaderSearch'); setDirtyFlg(); return false;">
											選
										</a>
									</span>&nbsp;
									<span id="recipeNameLabel"><nested:write property="recipeName" /></span>
									<nested:hidden property="recipeId" styleId="recipeId" />
									<nested:hidden property="recipeCd" styleId="recipeCd" />
									<nested:hidden property="recipeVersion" styleId="recipeVersion" />
									<nested:hidden property="recipeName" styleId="recipeName" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">品目コード</td>
								<td colspan="3">
									<span id="itemLabel">
										<nested:write property="itemCd" />
										<nested:notEmpty property="itemCd">:</nested:notEmpty>
										<nested:write property="itemName" />
									</span>
									<nested:hidden property="itemCd" styleId="itemCd" />
									<nested:hidden property="itemName" styleId="itemName" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">自社/花王区分</td>
								<td colspan="3">
									<div id="shipperDivisionLabelLabel">
										<nested:write property="shipperDivisionLabel" />
									</div>
									<nested:hidden property="shipperDivisionLabel" styleId="shipperDivisionLabel" />
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch" width="130">他社コード１</td>
								<td colspan="3">
									<div id="otherCompanyCd1Label">
										<nested:write property="otherCompanyCd1" />
									</div>
									<nested:hidden property="otherCompanyCd1" styleId="otherCompanyCd1" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">生産工場</td>
								<td colspan="3">
									<div id="lineLabel">
										<nested:write property="productionLine" />
										<nested:notEmpty property="itemCd">:</nested:notEmpty>
										<nested:write property="productionLineName" />
									</div>
									<nested:hidden property="productionLine" styleId="productionLine" />
									<nested:hidden property="productionLineName" styleId="productionLineName" />
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch" width="130">調合タンクNO</td>
								<td colspan="3">
									<nested:text property="compoundTankNo" maxlength="10" size="10" styleId="compoundTankNo" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
									<span id="compoundTankNoLabel"><nested:write property="compoundTankName" /></span>
									<div id="autocomplete_selection" class="autocomplete"></div>
									<nested:hidden property="compoundTankName" styleId="compoundTankName" />
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch" width="130">ホールドタンクNO</td>
								<td colspan="3">
									<nested:text property="holdTankNo" maxlength="10" size="10" styleId="holdTankNo" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
									<span id="holdTankNoLabel"><nested:write property="holdTankName" /></span>
									<nested:hidden property="holdTankName" styleId="holdTankName" />
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch" width="130">予備溶解タンクNO</td>
								<td colspan="3">
									<nested:text property="dissolutionTankNo" maxlength="10" size="10" styleId="dissolutionTankNo" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
									<span id="dissolutionTankNoLabel"><nested:write property="dissolutionTankName" /></span>
									<nested:hidden property="dissolutionTankName" styleId="dissolutionTankName" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">仕込み予定数量</td>
								<td colspan="3">
									<nested:text property="planedQty" maxlength="20" size="20" styleId="planedQty" onchange="setDirtyFlg();" onblur="formatQty();" style="ime-mode:disabled" styleClass="alignright"/>
									<span id="unitLabel">
										<nested:write property="unitName"/>
									</span>
									<nested:hidden property="unitName" styleId="unitName" />
									<nested:hidden property="unitOfOperationManagement" styleId="unitOfOperationManagement" />
									<nested:hidden property="decimalPoint" styleId="decimalPoint" />
									<nested:hidden property="roundDivision" styleId="roundDivision" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">製造開始予定日時</td>
								<td colspan="3">
									<nested:text property="planedSdateDate" maxlength="10" size="10" styleId="planedSdateDate" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
									<nested:text property="planedSdateTime" maxlength="5" size="5" styleId="planedSdateTime" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">製造終了予定日時</td>
								<td colspan="3">
									<nested:text property="planedEdateDate" maxlength="10" size="10" styleId="planedEdateDate" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
									<nested:text property="planedEdateTime" maxlength="5" size="5" styleId="planedEdateTime" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
								</td>
							</tr>
							<tr>
								<td colspan="4"><br></td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">備考</td>
								<td colspan="3">
									<nested:textarea property="remark" cols="80" rows="4" onchange="setDirtyFlg();" styleId="remark" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">注釈</td>
								<td colspan="3">
									<nested:textarea property="notes" cols="80" rows="4" onchange="setDirtyFlg();" styleId="notes" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div id="cssButton">
							<nested:equal property="updateAuthority" value="true">
								<a href="javascript:void();" class="cssButton" onclick="if(putConfirm()){return form_submit('op', 'update')}; return false;">&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;録&nbsp;&nbsp;</a>
							</nested:equal>
							<a href="javascript:void();" class="cssButton" onclick="if(putDirtyConfirm()){return form_submit('op', 'back')}; return false;">&nbsp;戻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;る&nbsp;</a>
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

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

		if ($('stockpdStateFlg').value == "0") {
			<%-- 必須フィールド定義 --%>
			defineAsRequiredField("recipeCdVersion");
			defineAsRequiredField("strGoodHousingSum");
			<%-- 数値型フィールド定義 --%>
			defineAsNumberField("strGoodHousingSum");
		}

		var count = $F("detailCount");
		for(var i = 0 ; i < count; i++){
			var prefix = "detailList[" + i + "].";

			if ($(prefix + "stockpdFlg").value == "0") {
				<%-- 数値型フィールド定義 --%>
				defineAsNumberField(prefix + "strResultQty");
				defineAsNumberField(prefix + "strSampleQty");
				defineAsNumberField(prefix + "strLossQty");
				defineAsNumberField(prefix + "strDefectQty");
				defineAsNumberField(prefix + "strAdjustQty");
			}
		}

		if ($('stockpdStateFlg').value == "0") {
			<%-- 基本処方コードのauto complete --%>
			new Ajax.Autocompleter(
			  "recipeCdVersion",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/MaterialRinputRecipeHeaderForAutoComplete.do?op=searchRecipeHeader",
			  {
			  	paramName : "code",
			  	callback : recipeCdVersionCallBack,
			    afterUpdateElement : setRecipeCdLabel
			  }
			);
			Event.observe('recipeCdVersion', 'keypress', clearText.bindAsEventListener($('recipeName')), false);
			Event.observe('recipeCdVersion', 'keypress', clearLabel.bindAsEventListener($('recipeName')), false);
			Event.observe('recipeCdVersion', 'keypress', clearText.bindAsEventListener($('recipeId')), false);
			Event.observe('recipeCdVersion', 'keypress', clearText.bindAsEventListener($('recipeCd')), false);
			Event.observe('recipeCdVersion', 'keypress', clearText.bindAsEventListener($('recipeVersion')), false);
		}

	    $("lblRecipeName").update($F('recipeName'));

		<%-- 使用量合計(横計)の計算 --%>
		initCalcSumUseQty();

		if ($('stockpdStateFlg').value == "0") {
			$("strGoodHousingSum").focus();
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 基本処方コードauto completeの選択後処理 --%>
	function setRecipeCdLabel(text, li) {
	    $("recipeCdVersion").value = getHiddenValue(li,"code");
	    $("lblRecipeName").update(li.getElementsByTagName('span')[0].innerText);
	    $("recipeName").value = (li.getElementsByTagName('span')[0].innerText);
	    $("recipeId").value = getHiddenValue(li,"recipeId");
	    $("recipeCd").value = getHiddenValue(li,"recipeCd");
	    $("recipeVersion").value = getHiddenValue(li,"recipeVersion");
	}

	<%-- 基本処方のオートコンプリート実行時検索キー項目取得 --%>
	function recipeCdVersionCallBack(editor,paramText){
		return paramText + '&srhItemCd=' + $('itemCd').value;
	}

	<%-- 計算確認メッセージ --%>
	function putCalcConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "計算してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	
	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 在庫引落リセットの確認メッセージ --%>
	function putResetConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "在庫引落を取消します。よろしいですか？";
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			if (!continueConfirm()) {
				return false;
			}
			return confirm(alertMsg[0]);
		} else {
			return confirm(alertMsg[0]);
		}
	}

	<%-- 生産出来高フォーマット --%>
	function format(){
		var buf =  $F("strGoodHousingSum");
		if( !blank(buf)){
			var buf2 = parseFloat(buf.replace(/,/g, ""));
			if( !isNaN(buf2) ){
				$("strGoodHousingSum").value = digitFormat($F("decimalPoint"),$F("round"),buf2);
			}
		}
	}

	<%-- 配合(実績数量～不良数量)フォーマット --%>
	function formatHaigo(index, property){
		var buf =  $F("detailList[" + index + "]." + property);
		if( !blank(buf)){
			var buf2 = parseFloat(buf.replace(/,/g, ""));
			if( !isNaN(buf2) ){
				$("detailList[" + index + "]." + property).value = digitFormat($F("decimalPointHaigo"),$F("roundHaigo"),buf2);
			}
		}
	}

	<%-- 配合(調整数量)フォーマット --%>
	function formatHaigoAdj(index){
		var buf =  $F("detailList[" + index + "].strAdjustQty");
		if( !blank(buf)){
			var buf2 = parseFloat(buf.replace(/,/g, ""));
			if( !isNaN(buf2) ){
				$("detailList[" + index + "].strAdjustQty").value = digitFormat($F("decimalPointHaigoAdj"),$F("roundHaigoAdj"),buf2);
			}
		}
	}

	<%-- 使用量合計(横計)の計算(初期処理) --%>
	function initCalcSumUseQty() {
		var count = $F("detailCount");

		for(var i = 0 ; i < count ; i++ ){
			var sumUseQty = 0;	// 使用量合計
			var resQty = $F("detailList[" + i + "].strResultQty");		// 実績数量
			var samQty = $F("detailList[" + i + "].strSampleQty");		// サンプル
			var losQty = $F("detailList[" + i + "].strLossQty");		// ロス数量
			var defQty = $F("detailList[" + i + "].strDefectQty");		// 不良数量
			var adjQty = $F("detailList[" + i + "].strAdjustQty");		// 調整数量

			if(!blank(resQty)){
				resQty = removeComma(resQty);
				if(!isNaN(resQty)) {
					sumUseQty = decimalAdd(sumUseQty, resQty, $F("decimalPointHaigo"))
				}
			}
			if(!blank(samQty)){
				samQty = removeComma(samQty);
				if(!isNaN(samQty)) {
					sumUseQty = decimalAdd(sumUseQty, samQty, $F("decimalPointHaigo"))
				}
			}
			if(!blank(losQty)){
				losQty = removeComma(losQty);
				if(!isNaN(losQty)) {
					sumUseQty = decimalAdd(sumUseQty, losQty, $F("decimalPointHaigo"))
				}
			}
<%--
			if(!blank(defQty)){
				defQty = removeComma(defQty);
				if(!isNaN(defQty)) {
					sumUseQty = decimalAdd(sumUseQty, defQty, $F("decimalPointHaigo"))
				}
			}
--%>
			if(!blank(adjQty)){
				adjQty = removeComma(adjQty);
				if(!isNaN(adjQty)) {
					sumUseQty = decimalAdd(sumUseQty, adjQty, $F("decimalPointHaigo"))
				}
			}

			$("detailList[" + i + "].strSumUseQty").value = sumUseQty;
			// ロット検索画面へ引き渡すためのデフォルト値
			$("detailList[" + i + "].strSumUseQtyDefault").value = sumUseQty;
		}
	}

	<%-- 使用量合計(横計)の計算 --%>
	function calcSumUseQty(index) {
		var sumUseQty = 0;	// 使用量合計

		var resQty = $F("detailList[" + index + "].strResultQty");		// 実績数量
		var samQty = $F("detailList[" + index + "].strSampleQty");		// サンプル
		var losQty = $F("detailList[" + index + "].strLossQty");		// ロス数量
		var defQty = $F("detailList[" + index + "].strDefectQty");		// 不良数量
		var adjQty = $F("detailList[" + index + "].strAdjustQty");		// 調整数量

		if(!blank(resQty)){
			resQty = removeComma(resQty);
			if(!isNaN(resQty)) {
				sumUseQty = decimalAdd(sumUseQty, resQty, $F("decimalPointHaigo"))
			}
		}
		if(!blank(samQty)){
			samQty = removeComma(samQty);
			if(!isNaN(samQty)) {
				sumUseQty = decimalAdd(sumUseQty, samQty, $F("decimalPointHaigo"))
			}
		}
		if(!blank(losQty)){
			losQty = removeComma(losQty);
			if(!isNaN(losQty)) {
				sumUseQty = decimalAdd(sumUseQty, losQty, $F("decimalPointHaigo"))
			}
		}
<%--
		if(!blank(defQty)){
			defQty = removeComma(defQty);
			if(!isNaN(defQty)) {
				sumUseQty = decimalAdd(sumUseQty, defQty, $F("decimalPointHaigo"))
			}
		}
--%>
		if(!blank(adjQty)){
			adjQty = removeComma(adjQty);
			if(!isNaN(adjQty)) {
				sumUseQty = decimalAdd(sumUseQty, adjQty, $F("decimalPointHaigo"))
			}
		}

		$("detailList[" + index + "].strSumUseQty").value = sumUseQty;
	}

	<%-- カンマ編集解除 --%>
	function removeComma(value) {
	    return value.split(",").join("")
	}
	
	<%-- 加算処理 --%>
	function decimalAdd(val1, val2, decPoint) {
		var str1 = removeComma(String(val1));
		var str2 = String(val2);
		var decNum = "1"
		var decLen = 0;
		var dLen1 = str1.lastIndexOf(".") + 1;
		dLen1 = str1.length - dLen1;
		var dLen2 = str2.lastIndexOf(".") + 1;
		dLen2 = str2.length - dLen2;
		if (dLen1 > dLen2) {
			decLen = dLen1
		} else {
			decLen = dLen2
		}
		for(var i = 0 ; i < decLen ; i++){
			decNum = decNum + "0";
		}
		var intVal1 = eval(rightMoveDecPoint(str1, decLen));
		var intVal2 = eval(rightMoveDecPoint(str2, decLen));
		var addIntVal = intVal1 + intVal2;
		var decVal = leftMoveDecPoint(String(addIntVal), decLen);
		return decimalFormat(decPoint, decVal);
	}
	
	<%-- 少数点右移動処理 --%>
	function rightMoveDecPoint(str, decPoint) {
		var strVal = "";
		var strRetVal = "";
		var sign = str.indexOf("-");
		strVal = str.replace("-", "");
		var decIdx = strVal.lastIndexOf(".");
		
		for ( var i = 0; i < decIdx; i++) {
			if (strRetVal == "") {
   				if (strVal.charAt(i) != "0") {
   					strRetVal = strRetVal + strVal.charAt(i);
   				}
   			} else {
				strRetVal = strRetVal + strVal.charAt(i);
   			}
   		}
   		var j = 0;
		for ( j = decIdx + 1; j < decIdx + 1 + decPoint; j++) {
			if (strVal.length > j) {
				if (strRetVal != "") {
   					strRetVal = strRetVal + strVal.charAt(j);
   				} else {
   					if (strVal.charAt(j) != "0") {
	    				strRetVal = strRetVal + strVal.charAt(j);
	    			}
   				}
   			} else {
   				strRetVal = strRetVal + "0";
   			}
		}
		if ( j < strVal.length) {
			strRetVal = "." + strRetVal;
			for (; j < strVal.length; j++) {
   				strRetVal = strRetVal + strVal.charAt(j);
			}
		}
		if (sign != -1) {
   			strRetVal = "-" + strRetVal;		
		}
		if (strRetVal == "") strRetVal = "0";
		return strRetVal;
	}

	<%-- 少数点左移動処理 --%>
	function leftMoveDecPoint(str, decPoint) {
		var strVal = "";
		var strRetVal = "";
		var sign = str.indexOf("-");
		strVal = str.replace("-", "");
		var decIdx = strVal.lastIndexOf(".");
		
		for ( var i = strVal.length; i < decIdx; i++) {
			strRetVal = strVal.charAt(i) + strRetVal;
   		}
   		if (decIdx == -1) decIdx = strVal.length;
   		var j = 0;
		for ( j = decIdx - 1; j > decIdx - 1 - decPoint; j--) {
			if (0 <= j) {
   				strRetVal = strVal.charAt(j) + strRetVal;
   			} else {
   				strRetVal = "0" + strRetVal;
   			}
		}
		if (-1 < j) {
			strRetVal = "." + strRetVal;
			for (; j > -1; j--) {
   				strRetVal = strVal.charAt(j) + strRetVal;
			}
		}
		if (strRetVal.length > 0) {
			decIdx = strRetVal.lastIndexOf(".");
			if (decIdx == -1) {
   				strRetVal = "0." + strRetVal;
			} else if (decIdx == 0) {
   				strRetVal = "0" + strRetVal;
			}
		}
		if (sign != -1) {
   			strRetVal = "-" + strRetVal;		
		}
		return strRetVal;
	}
	
	<%-- フォーマット処理 --%>
	function decimalFormat(decimalPoint, val){
		var fmt = formatNumber(String(val));
		var pos = fmt.lastIndexOf(".");
		if(pos >= 0){
			var len = fmt.length;
			var decNum = fmt.substring(pos+1,len).length;
			if(decNum < decimalPoint){
				for(var i = 0 ; i < (decimalPoint - decNum) ; i++){
					fmt = fmt + "0";
				}
			}
		} else {
			if(decimalPoint > 0){
				fmt = fmt + "."
				for(var i = 0 ; i < decimalPoint ; i++){
					fmt = fmt + "0";
				}
			}
		}
		return fmt;
	}
	<%-- 選択ボタンイベント（次画面遷移） --%>
	function selectAction(itemCd,index){
		var url = "<%= request.getContextPath() + "/MaterialRinputLotInventorySearch.do?op=init&srhItemCd=" %>" + itemCd;
		url= url + "&strSumUseQty=" + $F("detailList[" + index + "].strSumUseQtyDefault");
		url= url + "&srhBuySubcontractOrderNo=" + $F("buySubcontractOrderNo");
		url= url + "&srhRecipeId=" + $F("recipeId");
		url= url + "&srhStepNo=" + $F("detailList[" + index + "].stepNo");
		url= url + "&srhLineNo=" + $F("detailList[" + index + "].lineNo");
		location.href = url;
	}

	<%-- チェックを入れる --%>
	function setLine(line) {
		$("line").value = line;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/MaterialRinputDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="detailCount" styleId="detailCount" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="decimalPointHaigo" styleId="decimalPointHaigo" />
	<nested:hidden property="roundHaigo" styleId="roundHaigo" />
	<nested:hidden property="decimalPointHaigoAdj" styleId="decimalPointHaigoAdj" />
	<nested:hidden property="roundHaigoAdj" styleId="roundHaigoAdj" />
	<nested:hidden property="buySubcontractOrderNo" styleId="buySubcontractOrderNo" />
	<nested:hidden property="stockpdStateFlg" styleId="stockpdStateFlg" />
	<nested:hidden property="line" styleId="line" />

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
							<td class="fcTitle fb" width="250">外注原材料投入実績入力</td>
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

				<tr>
					<td><!-- 明細部 -->
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注番号</td>
									<td><nested:write property="buySubcontractOrderNo" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">発注書NO</td>
									<td><nested:write property="orderSheetNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注日</td>
									<td><nested:write property="strOrderDate" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">納品希望日</td>
									<td><nested:write property="strSuggestedDeliverlimitDate" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td>
										<nested:write property="itemCd" />
										<nested:hidden property="itemCd" styleId="itemCd" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">品目名称</td>
									<td><nested:write property="itemQueueName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード１</td>
									<td><nested:write property="otherCompanyCd1" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">受入日</td>
									<td><nested:write property="strAcceptDate" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先コード</td>
									<td><nested:write property="venderCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先名称</td>
									<td><nested:write property="venderShortedName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先コード</td>
									<td><nested:write property="locationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">納入先名称</td>
									<td><nested:write property="locationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">担当部署コード</td>
									<td><nested:write property="chargeOrganizationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">担当部署名称</td>
									<td><nested:write property="chargeOrganizationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">部署コード</td>
									<td><nested:write property="organizationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">部署名称</td>
									<td><nested:write property="organizationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注者コード</td>
									<td><nested:write property="tantoCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">発注者名称</td>
									<td><nested:write property="tantoNm" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">生産予定数量</td>
									<td>
										<span style="width:120;" class="alignRight">
											<nested:write property="strOrderQuantity" />
										</span>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">単位</td>
									<td><nested:write property="unit" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
									<td><nested:write property="styleOfPacking" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">重量</td>
									<td>
										<span style="width:120;" class="alignRight">
											<nested:write property="strOrderConvertQuantity" />
										</span>
										&nbsp;Kg
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">基本処方コード</td>
									<td>
										<%-- 在庫引落明細ありの場合 --%>
										<nested:notEqual property="stockpdStateFlg" value="0">
											<nested:write property="recipeCdVersion" />
										</nested:notEqual>
										<%-- 在庫引落明細なしの場合 --%>
										<nested:equal property="stockpdStateFlg" value="0">
											<nested:text property="recipeCdVersion" maxlength="20" size="20" styleId="recipeCdVersion" onchange="setDirtyFlg();" styleClass="alignLeft"/>
											<div id="autocomplete_selection" class="autocomplete"></div>
										</nested:equal>
										<nested:hidden property="recipeName" styleId="recipeName" />
										<nested:hidden property="recipeId" styleId="recipeId" />
										<nested:hidden property="recipeCd" styleId="recipeCd" />
										<nested:hidden property="recipeVersion" styleId="recipeVersion" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">基本処方名称</td>
									<td>
										<span id="lblRecipeName">
											<nested:write property="recipeName" />
										</span>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">生産出来高</td>
									<td width="200">
										<%-- 在庫引落明細データありの場合 --%>
										<nested:notEqual property="stockpdStateFlg" value="0">
											<span style="width:120;" class="alignRight">
												<nested:write property="strGoodHousingSum" />
											</span>
										</nested:notEqual>
										<%-- 在庫引落明細なしの場合 --%>
										<nested:equal property="stockpdStateFlg" value="0">
											<nested:text property="strGoodHousingSum" maxlength="22" size="13" styleId="strGoodHousingSum" style="ime-mode:disabled"
											 onblur="format();" onchange="setDirtyFlg();" />
											
											<span id="cssButton">
												<nested:notEmpty property="strAcceptDate">
												<a href="#"  class="cssButton" 
													onclick="if (!(putCalcConfirm())) {return false;}else{return form_submit('op', 'calculate'); return false;}">
													計算
												</a>
												</nested:notEmpty>
												<nested:empty property="strAcceptDate">
													未受入
												</nested:empty>
											</span>
											
										</nested:equal>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="20"></td>
						</tr>
						<%-- 複数明細部 --%>
						<tr>
							<td>
							<table cellspacing="2" cellpadding="1" id="tblList">
								<tr class="bcTitleList fb fcTitleList">
									<td width="70">品目ｺｰﾄﾞ</td>
									<td width="80">品目名称</td>
									<td width="70">使用数</td>
									<td>実績数量</td>
									<td>サンプル</td>
									<td>ロス数量</td>
									<td>不良数量</td>
									<td>調整数量</td>
									<td>単位</td>
									<td>在庫</td>
								</tr>
								<nested:notEmpty property="detailList">
								<nested:iterate id="detailList" property="detailList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<nested:define id="itemCd" property="itemCd" />
									<%-- 使用量合計(横計) --%>
									<nested:hidden property="strSumUseQty" styleId="strSumUseQty" />
									<%-- 使用量合計(横計)デフォルト値 --%>
									<nested:hidden property="strSumUseQtyDefault" styleId="strSumUseQtyDefault" />
									<nested:hidden property="stockpdFlg" styleId="stockpdFlg" />
									<nested:hidden property="stepNo" styleId="stepNo" />
									<nested:hidden property="lineNo" styleId="lineNo" />

									<td><%-- 品目ｺｰﾄﾞ --%>
										<nested:write property="itemCd" />
									</td>
									<td><%-- 品目名称 --%>
										<nested:write property="itemName" />
									</td>
									<td align="right"><%-- 使用数 --%>
										<nested:write property="strQty" />
										<nested:hidden property="strQty" styleId="strQty" />
									</td>
									<nested:equal property="stockpdFlg" value="0">
										<td><%-- 実績数量 --%>
											<nested:text property="strResultQty" maxlength="22" size="9" styleId="strResultQty"
											 onblur="<%="formatHaigo(" + pageContext.findAttribute("index").toString()  + ", 'strResultQty');calcSumUseQty(" + pageContext.findAttribute("index").toString() + ");" %>" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										</td>
										<td><%-- サンプル --%>
											<nested:text property="strSampleQty" maxlength="22" size="9" styleId="strSampleQty"
											 onblur="<%="formatHaigo(" + pageContext.findAttribute("index").toString()  + ", 'strSampleQty');calcSumUseQty(" + pageContext.findAttribute("index").toString() + ");" %>" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										</td>
										<td><%-- ロス数量 --%>
											<nested:text property="strLossQty" maxlength="22" size="9" styleId="strLossQty"
											 onblur="<%="formatHaigo(" + pageContext.findAttribute("index").toString()  + ", 'strLossQty');calcSumUseQty(" + pageContext.findAttribute("index").toString() + ");" %>" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										</td>
										<td><%-- 不良数量 --%>
											<nested:text property="strDefectQty" maxlength="22" size="9" styleId="strDefectQty"
											 onblur="<%="formatHaigo(" + pageContext.findAttribute("index").toString()  + ", 'strDefectQty');calcSumUseQty(" + pageContext.findAttribute("index").toString() + ");" %>" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										</td>
										<td><%-- 調整数量 --%>
											<nested:text property="strAdjustQty" maxlength="22" size="9" styleId="strAdjustQty"
											 onblur="<%="formatHaigoAdj(" + pageContext.findAttribute("index").toString()  + ");calcSumUseQty(" + pageContext.findAttribute("index").toString() + ");" %>" onchange="setDirtyFlg();" style="ime-mode:disabled" />
										</td>
									</nested:equal>
									<nested:equal property="stockpdFlg" value="1">
										<td><%-- 実績数量 --%>
											<nested:write property="strResultQty" />
											<nested:hidden property="strResultQty" styleId="strResultQty" />
										</td>
										<td><%-- サンプル --%>
											<nested:write property="strSampleQty" />
											<nested:hidden property="strSampleQty" styleId="strSampleQty" />
										</td>
										<td><%-- ロス数量 --%>
											<nested:write property="strLossQty" />
											<nested:hidden property="strLossQty" styleId="strLossQty" />
										</td>
										<td><%-- 不良数量 --%>
											<nested:write property="strDefectQty" />
											<nested:hidden property="strDefectQty" styleId="strDefectQty" />
										</td>
										<td><%-- 調整数量 --%>
											<nested:write property="strAdjustQty" />
											<nested:hidden property="strAdjustQty" styleId="strAdjustQty" />
										</td>
									</nested:equal>
									<td><%-- 単位 --%>
										<nested:write property="unit" />
									</td>
									<nested:equal name="materialRinputDetailForm" property="calcFlg" value="false">
										<nested:equal property="stockpdFlg" value="0">
										<td><%-- 在庫 選択 --%>
											<div id="cssButton">
												<a href='#' onclick="if (!(putDirtyConfirm())) {return false;}else{selectAction('<%=pageContext.findAttribute("itemCd").toString()%>','<%=pageContext.findAttribute("index").toString()%>');return false;}" class="cssButton">選</a>
											</div>
										</td>
										</nested:equal>
										<nested:equal property="stockpdFlg" value="1">
											<td><%-- 在庫 キャンセル --%>
												<div id="cssButton">
													<a href='#' onclick="if (!(putResetConfirm())) {return false;}else{setLine('<%=pageContext.findAttribute("index").toString()%>');return form_submit('op', 'reset');return false;}" class="cssButton">戻</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>
								</tr>
								</nested:iterate>
								</nested:notEmpty>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
							<table>
								<tr>
									<nested:notEmpty property="detailList">
										<nested:equal property="updateAuthority" value="true">
											<nested:notEqual property="stockpdStateFlg" value="2">
												<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
												</td>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
											</nested:notEqual>
										</nested:equal>
									</nested:notEmpty>
									<td class="allRight">
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
			</td>
		</tr>
	</table>

</nested:form>
</body>
</html:html>

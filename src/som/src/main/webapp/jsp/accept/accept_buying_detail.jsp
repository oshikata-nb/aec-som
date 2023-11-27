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

		<%-- フォーカス設定 --%>
		if ( !blank($('focusPosition').value) ) {
			document.forms[0].elements[$F('focusPosition')].focus();
			$('focusPosition').value = "";
			//mulStockingAmount();
		}

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("accountDebitSectionCd");
		defineAsRequiredField("accountCreditSectionCd");
		defineAsRequiredField("debitTitleCd");
		defineAsRequiredField("creditTitleCd");
		defineAsRequiredField("strHousingUnitprice");
		defineAsRequiredField("strStockingDate");
		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strHousingUnitprice");
		defineAsNumberField("strFareAmount");
		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strStockingDate");

		var count = $F("detailCount");
		for(var i = 0 ; i < count; i++){
			var prefix = "detailList[" + i + "].";

			<%-- 数値型フィールド定義 --%>
			<%-- 増付枚数 --%>
			defineAsNumberField(prefix + "strIncreaseQuantity");
		}

		<%-- 仕入数量計算 --%>
		calcStockingQuantity();
		<%-- 金額計算 --%>
		calcStockingAmount();

<%-- 2014/1/28 新消費税対応--%>
		if($("strStockingAmount").value != "0" && $("strTaxAmount").value == "0" && $("taxDivision").value != "3"){
			<%-- 2020/09/04_AEC佐藤（追加）_もし消費税率が0以外の場合は仕入金額、消費税額計算を行う--%>
			if($("strTaxRatio").value != "0"){
				calcTax();
			}
		}
<%-- 2014/1/28 新消費税対応--%>

		<%-- 会計部門借方のauto complete --%>
		new Ajax.Autocompleter(
		  "accountDebitSectionCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
		  {
		  	paramName : "code",
		    afterUpdateElement : setBumonDebitLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('accountDebitSectionCd', 'keypress', clearLabel.bindAsEventListener($('accountDebitSectionName')), false);

		<%-- 会計部門貸方のauto complete --%>
		new Ajax.Autocompleter(
		  "accountCreditSectionCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
		  {
		  	paramName : "code",
		    afterUpdateElement : setBumonCreditLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('accountCreditSectionCd', 'keypress', clearLabel.bindAsEventListener($('accountCreditSectionName')), false);

		<%-- 勘定科目借方のauto complete --%>
		new Ajax.Autocompleter(
		  "debitTitleCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
		  {
		  	paramName : "code",
		    afterUpdateElement : setAccountsDebitLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('debitTitleCd', 'keypress', clearLabel.bindAsEventListener($('debitTitleName')), false);

		<%-- 勘定科目貸方のauto complete --%>
		new Ajax.Autocompleter(
		  "creditTitleCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
		  {
		  	paramName : "code",
		    afterUpdateElement : setAccountsCreditLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('creditTitleCd', 'keypress', clearLabel.bindAsEventListener($('creditTitleName')), false);

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 会計部門借方auto completeの選択後処理 --%>
	function setBumonDebitLabel(text, li) {
	    $("lblAccountDebitSectionName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("accountDebitSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 会計部門貸方auto completeの選択後処理 --%>
	function setBumonCreditLabel(text, li) {
	    $("lblAccountCreditSectionName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("accountCreditSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%--  勘定科目借方auto completeの選択後処理 --%>
	function setAccountsDebitLabel(text, li) {
	    $("lblDebitTitleName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("debitTitleName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%--  勘定科目貸方auto completeの選択後処理 --%>
	function setAccountsCreditLabel(text, li) {
	    $("lblCreditTitleName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("creditTitleName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 仕入数量の計算 --%>
	function calcStockingQuantity() {
		var count = $F("detailCount");
		var stockingQuantity = 0;
		var sumStockingQuantity = 0;

		for(var i = 0 ; i < count ; i++ ){
			var acQty =  $("detailList[" + i + "].strAcceptQuantity").innerText;	// 受入数量
			var inQty =  $F("detailList[" + i + "].strIncreaseQuantity");			// 増付数量
			if(blank(inQty)){
				inQty = "0";
			} else {
				$("detailList[" + i + "].strIncreaseQuantity").value = digitStringFormat($F("decimalPointQty"),$F("roundQty"),inQty);
			}

			if(!blank(acQty)){
				acQty = parseFloat(acQty.replace(/,/g, ""));
				inQty = parseFloat(inQty.replace(/,/g, ""));
				if(!isNaN(acQty) && !isNaN(inQty)){
					stockingQuantity = acQty - inQty;
				}
			} else {
				stockingQuantity = 0;
			}

			<%-- 桁数丸め部品呼び出し --%>
			stockingQuantity = digitFormat($F("decimalPointQty"),$F("roundQty"),stockingQuantity);
			$("detailList[" + i + "].lblStrStockingQuantity").update(stockingQuantity);
			$("detailList[" + i + "].strStockingQuantity").value = stockingQuantity;
			sumStockingQuantity += parseFloat(stockingQuantity.replace(/,/g, ""));
		}

		// 仕入数量合計
		sumStockingQuantity = digitFormat($F("decimalPointQty"),$F("roundQty"),sumStockingQuantity);
		$("lblSumStockingQuantity").update(sumStockingQuantity);
		$("sumStockingQuantity").value = sumStockingQuantity;
	}

	<%-- 金額の計算 --%>
	function calcStockingAmount(){
		var unitDiv = $F("unitDiv");								// 運用管理単位
		var unitpriceDivision = $F("unitpriceDivision");			// 単価区分
		var kgOfFractionManagement = $F("kgOfFractionManagement");	// Kg換算係数(在庫)
		var housingUnitprice = "0";		// 単価
		var sumStockingQuantity = 0;	// 仕入数量
		var strFareAmount = 0;			// 運賃
		var strStockingAmount = 0;		// 金額

		// 単価
		if( !blank($F("strHousingUnitprice"))){
			var buf = parseFloat($F("strHousingUnitprice").replace(/,/g, ""));
			if(!isNaN(buf) ){
				housingUnitprice = digitFormat($F("decimalPointTanka"),$F("roundTanka"),buf);
				$("strHousingUnitprice").value = housingUnitprice;
			}
		}

		// 仕入数量
		if( !blank($F("sumStockingQuantity"))){
			var buf = parseFloat($F("sumStockingQuantity").replace(/,/g, ""));
			if(!isNaN(buf) ){
				sumStockingQuantity = buf;
			}
		}

		// 運賃
		if( !blank($F("strFareAmount"))){
			var buf = parseFloat($F("strFareAmount").replace(/,/g, ""));
			if(!isNaN(buf) ){
				strFareAmount = digitFormat($F("decimalPointUntin"),$F("roundUntin"),buf);
				$("strFareAmount").value = strFareAmount;
				strFareAmount = parseFloat(strFareAmount.replace(/,/g, ""));
			}
		}

		// 運用管理単位がKg('2')以外かつ単価区分がKg単価('2')の場合
		if (unitDiv != "2" && unitpriceDivision == "2") {
			// 金額＝単価×仕入数量×Kg換算係数＋運賃
			var ksu = sumStockingQuantity * kgOfFractionManagement;
			strStockingAmount = multiFloat(housingUnitprice,ksu,$F("decimalPointTanka")) + strFareAmount;
		} else {
			// 金額＝単価×仕入数量＋運賃
			var ksu = sumStockingQuantity ;
			strStockingAmount = multiFloat(housingUnitprice,ksu,$F("decimalPointTanka")) + strFareAmount;
		}

		<%-- 桁数丸め部品呼び出し：金額 --%>
		var bufAmount = digitFormat($F("decimalPoint"),$F("round"),strStockingAmount);
		$("lblStrStockingAmount").update(bufAmount);
		$("strStockingAmount").value = bufAmount;

		<%-- インボイス対応 ①消費税、②軽減措置金額、③軽減措置消費税額の計算 --%>
 		var StockingAmount = $("strStockingAmount").value;
		var StockingAmount2 = parseFloat(StockingAmount.replace(/,/g, ""));	// 金額
		var taxAmount = 0;													// 消費税
		var taxRatio = $F("strTaxRatio"); 									// 税率

		var invoiceAmount = 0;							// 金額(軽減措置)
		var invoiceTaxAmount = 0;						// 消費税(軽減措置)
		var invoiceTaxRatio = $F("invoiceTaxRatio"); 	// 税額控除割合

		// ①消費税 = 金額 × 税率
		taxAmount = StockingAmount2 * taxRatio / 100;
		<%-- 桁数丸め部品呼び出し：消費税 --%>
		var bufTax = digitRound2($F("decimalPointTax"),$F("roundTax"),taxAmount);
		$("lblStrTaxAmount").update(formatNumber(String(bufTax)));
		$("strTaxAmount").value = formatNumber(String(bufTax));

		// ②消費税(軽減措置) = 消費税 * 税額控除割合
		invoiceTaxAmount = bufTax * invoiceTaxRatio;
		<%-- 桁数丸め部品呼び出し：消費税(軽減措置) --%>
		var bufInvoTax  = digitRound2($F("decimalPointTax"),$F("roundTax"),invoiceTaxAmount);

		// ③金額(軽減措置) = 仕入金額 + 消費税 - 消費税(軽減措置)
		invoiceAmount = StockingAmount2 + bufTax - bufInvoTax;

		if($("reducedTaxTargetFlg").value == "1"){
			$("lblStrInvoiceAmount").update(formatNumber(String(invoiceAmount)));
			$("lblStrInvoiceTaxAmount").update(formatNumber(String(bufInvoTax)));
			$("strInvoiceAmount").value = formatNumber(String(invoiceAmount));
			$("strInvoiceTaxAmount").value = formatNumber(String(bufInvoTax));
		} else {
			$("strInvoiceAmount").value = $("strStockingAmount").value;
			$("strInvoiceTaxAmount").value = $("strTaxAmount").value;
		}
	}

<%-- 2014/1/28 新消費税対応--%>
	function calcTax(){
		form_submit('op', 'calcStockingAmountAndTax');
	}
<%-- 2014/1/28 新消費税対応--%>

	<%-- 行削除確認メッセージ --%>
	function putDelListConfirm() {
		var count = $F("detailCount");

		var flag = false;
		for(var i = 0 ; i < count ; i++){
			var chk = $F("detailList[" + i + "].checkFlg");
			if(chk){
				flag = true;
				break;
			}
		}
		if(flag){
			alertMsg = new Array();
			alertMsg[0] = "削除してよろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			alertMsg = new Array();
			alertMsg[0] = "行が選択されていません。";
			alert(alertMsg[0]);
			return false;
		}

	}

	<%-- 登録確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 承認依頼確認メッセージ --%>
	function approveConfirm() {

		if (!putDirtyConfirm()) {
			return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "承認依頼してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setTaxCdChangeFlg() {
		$("taxCdChangeFlg").value = "true";
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

	<%-- フォーカス位置の保存 --%>
	function setFocusPosition(pos){
		$("focusPosition").value = pos;
	}

	<%-- 仕訳反転処理 --%>
	function categoryDivisionAction(combo){
		var value = combo.value;
		var index = 0;
		while(true){
			var categoryDivisionList = $("categoryDivisionList" + index);
			if(categoryDivisionList == null){
				break;
			}else{
				var cvalue = categoryDivisionList.value;
				if(value == cvalue){
					var reversalFlg = $F("reversalFlgList" + index);	// 仕訳反転フラグ
					var preReversalFlg = $F("preReversalFlg");			// 仕訳反転フラグ前回値
					$("preReversalFlg").value = reversalFlg;

					//仕訳反転処理
					if((reversalFlg == "1" && (preReversalFlg == "" || preReversalFlg == "0"))
					   || (reversalFlg == "0" && (preReversalFlg == "" || preReversalFlg == "1"))) {

						//会計部門
						var accountDebitSectionCd = $F("accountDebitSectionCd");		// 借方
						var accountDebitSectionName = $F("accountDebitSectionName");	// 借方
						var accountCreditSectionCd = $F("accountCreditSectionCd");		// 貸方
						var accountCreditSectionName = $F("accountCreditSectionName");	// 貸方
						//勘定科目
						var debitTitleCd = $F("debitTitleCd");			// 借方
						var debitTitleName = $F("debitTitleName");		// 借方
						var creditTitleCd = $F("creditTitleCd");		// 貸方
						var creditTitleName = $F("creditTitleName");	// 貸方

						//会計部門を逆転
						$("accountDebitSectionCd").value = accountCreditSectionCd;
						$("accountDebitSectionName").value = accountCreditSectionName;
						$("lblAccountDebitSectionName").update(accountCreditSectionName);
						$("accountCreditSectionCd").value = accountDebitSectionCd;
						$("accountCreditSectionName").value = accountDebitSectionName;
						$("lblAccountCreditSectionName").update(accountDebitSectionName);
						//勘定科目を逆転
						$("debitTitleCd").value = creditTitleCd;
						$("debitTitleName").value = creditTitleName;
						$("lblDebitTitleName").update(creditTitleName);
						$("creditTitleCd").value = debitTitleCd;
						$("creditTitleName").value = debitTitleName;
						$("lblCreditTitleName").update(debitTitleName);
					}
					break;
				}
			}
			index++;
		}
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/AcceptBuyingDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="detailCount" styleId="detailCount" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="unit" styleId="unit" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="decimalPointQty" styleId="decimalPointQty" />
	<nested:hidden property="roundQty" styleId="roundQty" />
	<nested:hidden property="decimalPointTanka" styleId="decimalPointTanka" />
	<nested:hidden property="roundTanka" styleId="roundTanka" />
	<nested:hidden property="decimalPointUntin" styleId="decimalPointUntin" />
	<nested:hidden property="roundUntin" styleId="roundUntin" />
	<nested:hidden property="unitDiv" styleId="unitDiv" />
	<nested:hidden property="unitpriceDivision" styleId="unitpriceDivision" />
	<nested:hidden property="kgOfFractionManagement" styleId="kgOfFractionManagement" />
	<nested:hidden property="status2" styleId="status2" />
	<nested:hidden property="taxCdChangeFlg" />
	<%-- 20200904_AEC佐藤_消費税率取得のため追加 --%>
	<nested:hidden property="strTaxRatio" />
	<%-- フォーカス位置保存 --%>
	<nested:hidden property="focusPosition" styleId="focusPosition" />
	<%-- 分類コードリスト --%>
	<nested:iterate id="item" property="categoryDivisionList" indexId="index">
		<input type="hidden" id="categoryDivisionList<bean:write name='index'/>" value="<bean:write name='item'/>" >
	</nested:iterate>
	<%-- 仕分反転フラグリスト --%>
	<nested:iterate id="item" property="reversalFlgList" indexId="index">
		<input type="hidden" id="reversalFlgList<bean:write name='index'/>" value="<bean:write name='item'/>" >
	</nested:iterate>
	<%-- 仕訳反転フラグ前回値 --%>
	<nested:hidden property="preReversalFlg" styleId="preReversalFlg" />
	<%-- 軽減措置金額計算用の税率 --%>
	<nested:hidden property="invoiceTaxRatio" styleId="invoiceTaxRatio" />
	<nested:hidden property="strInvoiceAmount" styleId="strInvoiceAmount" />
	<nested:hidden property="strInvoiceTaxAmount" styleId="strInvoiceTaxAmount" />
	<nested:hidden property="decimalPointTax" styleId="decimalPointTax" />
	<nested:hidden property="roundTax" styleId="roundTax" />
	<nested:hidden property="reducedTaxTargetFlg" styleId="reducedTaxTargetFlg" />

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
							<td class="fcTitle fb" width="250">仕入入力</td>
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
									<td>
										<nested:write property="buySubcontractOrderNo" />
										<nested:notEmpty property="orderDivideNo">
											-<nested:write property="orderDivideNo" />
										</nested:notEmpty>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">発注書NO</td>
									<td><nested:write property="orderSheetNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入番号</td>
									<td>
										<nested:write property="slipNo" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">担当部署</td>
									<td>
										<nested:write property="chargeOrganizationName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">発注日</td>
									<td><nested:write property="strOrderDate" /></td>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">仕入先受注番号</td>
									<td><nested:write property="siOrderNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td><nested:write property="itemCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">品目名称</td>
									<td><nested:write property="itemQueueName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード</td>
									<td><nested:write property="otherCompanyCd1" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">発注数量</td>
									<td>
										<span style="width:120" class="alignRight">
											<nested:write property="strOrderQuantity" />
										</span>
										　<nested:write property="unit" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">受入重量</td>
									<td>
										<span style="width:120" class="alignRight">
											<nested:write property="strAcceptConvertQuantitySum" />
										</span>
										　kg
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
									<td><nested:write property="styleOfPacking" /></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先</td>
									<td><nested:write property="venderCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先名称</td>

									<td>
										<nested:equal property="reducedTaxTargetFlg" value="1">
											<font color=red>【未申請業者等】</font>
										</nested:equal>
										<nested:write property="venderShortedName" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
									<td><nested:write property="locationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">納入先名称</td>
									<td><nested:write property="locationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納品希望日</td>
									<td><nested:write property="strSuggestedDeliverlimitDate" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入区分</td>
									<td>
										<nested:select property="categoryDivision" styleId="categoryDivision" onchange="categoryDivisionAction(this);">
											<nested:optionsCollection property="stockinDivisionCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入数量計</td>
									<td>
										<span style="width:120" class="alignRight" id="lblSumStockingQuantity">
										<nested:write property="sumStockingQuantity" />
										</span>
										　<nested:write property="unit" />
										<nested:hidden property="sumStockingQuantity" styleId="sumStockingQuantity" />
									</td>
<!-- 2014/1/28 新消費税対応 -->
<%--
									<td class="fcTitleDetail fb bcTitleDetail">消費税課税区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyTaxDivision", new com.asahikaseieng.app.comboboxes.SelectCompanyTaxDivision(false, false)); %>
										<nested:select property="taxDivision" onchange="setDirtyFlg();" disabled="true">
											<nested:options name="SelectCompanyTaxDivision" property="values" labelName="SelectCompanyTaxDivision" labelProperty="labels"/>
										</nested:select>
									</td>
--%>
<!-- 2014/1/28 新消費税対応 -->

								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">単価</td>
									<td width="170">
										<nested:text property="strHousingUnitprice" styleId="strHousingUnitprice" maxlength="22" size="13" style="ime-mode:disabled"
										 onchange="calcStockingAmount();calcTax();" />
										<nested:write property="unitpriceUnit" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">運賃</td>
									<td>
										<nested:text property="strFareAmount" styleId="strFareAmount" maxlength="22" size="13" style="ime-mode:disabled"
										 onblur="calcStockingAmount();calcTax();" onchange="setDirtyFlg();" />
										円
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入日</td>
									<td>
										<nested:text property="strStockingDate" styleId="strStockingDate" maxlength="8" size="10" style="ime-mode:disabled" onchange="setDirtyFlg();calcStockingAmount();calcTax();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="70">消費税率</td>
									<td>
<%-- 										<nested:select property="strTaxRatio" style="margin: 0;padding: 0" onchange="setDirtyFlg();calcStockingAmount();calcTax();">
											<nested:optionsCollection property="taxCombo" label="labales" value="values" /> --%>
										<nested:select property="taxCd" style="margin: 0;padding: 0" onchange="setDirtyFlg();setTaxCdChangeFlg();calcStockingAmount();calcTax();">
											<nested:options property="taxValues" labelProperty="taxLabels"/>
										</nested:select>%
										<% pageContext.setAttribute("SelectNewTaxDivision", new com.asahikaseieng.app.comboboxes.SelectNewTaxDivision(false, false)); %>
										<nested:select property="taxDivision" onchange="setDirtyFlg();calcStockingAmount();calcTax();">
											<nested:options name="SelectNewTaxDivision" property="values" labelName="SelectNewTaxDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
<!-- 2014/1/28 新消費税対応 -->
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">金額</td>
									<td>
										<span style="width:120" class="alignRight" id="lblStrStockingAmount">
										<nested:write property="strStockingAmount" />
										</span>
										　円
										<nested:hidden property="strStockingAmount" styleId="strStockingAmount" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="70">消費税額</td>
									<td>
											<span style="width:158" class="alignRight" id="lblStrTaxAmount">
												<nested:write property="strTaxAmount" />
											</span>　円
										<nested:hidden property="strTaxAmount" styleId="strTaxAmount" />
									</td>
								</tr>
<!-- 2014/1/28 新消費税対応 -->

								<nested:equal property="reducedTaxTargetFlg" value="1">
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">軽減措置金額</td>
										<td>
												<span style="width:120" class="alignRight" id="lblStrInvoiceAmount">
													<nested:write property="strInvoiceAmount" />
												</span>　円
										</td>

										<td class="fcTitleDetail fb bcTitleDetail">軽減措置消費税額</td>
										<td>
												<span style="width=158px"  class="alignRight" id="lblStrInvoiceTaxAmount">
													<nested:write property="strInvoiceTaxAmount" />
												</span>　円
										</td>
									</tr>
								</nested:equal>
							</table>
							</td>
						</tr>
						<tr>
							<table cellspacing="2" cellpadding="1" id="tblList" border="0" >
								<tr>
									<td class="bcTitleList fb fcTitleList">仕訳</td>
									<td width="280"></td>
								</tr>
								<tr>
									<td colspan="2">
									<table cellspacing="2" cellpadding="1" id="tblList" border="1" >
										<tr class="bcTitleList fb fcTitleList">
											<td>&nbsp;</td>
											<td colspan="2">借方</td>
											<td colspan="2">貸方</td>
										</tr>
										<tr class="bcList1">
											<td class="bcTitleList fb fcTitleList">
												会計部門
											</td>
											<td>
												<nested:text property="accountDebitSectionCd" maxlength="10" size="10" styleId="accountDebitSectionCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
												<div id="autocomplete_selection" class="autocomplete"></div>
											</td>
											<td width="120px">
												<div id="lblAccountDebitSectionName">
													<nested:write property="accountDebitSectionName" />
												</div>
												<nested:hidden property="accountDebitSectionName" styleId="accountDebitSectionName" />
											</td>
											<td>
												<nested:text property="accountCreditSectionCd" maxlength="10" size="10" styleId="accountCreditSectionCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
											</td>
											<td width="120px">
												<div id="lblAccountCreditSectionName">
													<nested:write property="accountCreditSectionName" />
												</div>
												<nested:hidden property="accountCreditSectionName" styleId="accountCreditSectionName" />
											</td>
										</tr>
										<tr class="bcList2">
											<td class="bcTitleList fb fcTitleList">
												勘定科目
											</td>
											<td>
												<nested:text property="debitTitleCd" maxlength="10" size="10" styleId="debitTitleCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
											</td>
											<td width="120px">
												<div id="lblDebitTitleName">
													<nested:write property="debitTitleName" />
												</div>
												<nested:hidden property="debitTitleName" styleId="debitTitleName" />
											</td>
											<td>
												<nested:text property="creditTitleCd" maxlength="10" size="10" styleId="creditTitleCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
											</td>
											<td width="120px">
												<div id="lblCreditTitleName">
													<nested:write property="creditTitleName" />
												</div>
												<nested:hidden property="creditTitleName" styleId="creditTitleName" />
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
						</tr>
						<tr>
							<td class="fcTitle fb" height="20">ロット分割</td>
						</tr>
						<tr>
							<td><%-- 複数明細部 --%>
							<table cellspacing="2" cellpadding="1" id="tblList">
								<tr class="bcTitleList fb fcTitleList">
									<td width="50">行番号</td>
									<td>ﾒｰｶｰﾛｯﾄ番号</td>
									<td width="100">入荷ﾛｯﾄ番号</td>
									<td width="100">受入数量</td>
									<td width="80">受入日</td>
									<td>増付数量</td>
									<td width="100">仕入数量</td>
								</tr>
								<%-- 明細部 >>>>> --%>
								<nested:iterate id="detailList" property="detailList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<td align="right"><%-- 行番号 --%>
										<nested:write property="strRowNo" />
									</td>
									<td><%-- ﾒｰｶｰﾛｯﾄ番号 --%>
										<nested:write property="supplierLotno" />
									</td>
									<td><%-- 入荷ﾛｯﾄ番号 --%>
										<nested:write property="lotNo" />
									</td>
									<td align="right"><%-- 受入数量 --%>
										<span id="<%="detailList[" + pageContext.findAttribute("index").toString() + "].strAcceptQuantity" %>">
											<nested:write property="strAcceptQuantity" />
										</span>
									</td>
									<td><%-- 受入日 --%>
										<nested:write property="strAcceptDate" />
									</td>
									<td><%-- 増付数量 --%>
<!-- 2014/1/28 新消費税対応 -->
										<nested:text property="strIncreaseQuantity" maxlength="22" size="10" styleId="strIncreaseQuantity" style="ime-mode:disabled"
										  onchange="<%="setDirtyFlg();setFocusPosition('detailList[" + pageContext.findAttribute("index").toString() + "].strIncreaseQuantity');calcStockingQuantity();calcStockingAmount();calcTax();form_submit('op', 'getHousingUnitprice');"%>" />
<!-- 2014/1/28 新消費税対応 -->
									</td>
									<td align="right"><%-- 仕入数量 --%>
										<span id="<%="detailList[" + pageContext.findAttribute("index").toString() + "].lblStrStockingQuantity" %>">
											<nested:write property="strStockingQuantity" />
										</span>
										<nested:hidden property="strStockingQuantity" styleId="strStockingQuantity" />
									</td>
								</tr>
								</nested:iterate>
							</table>
							</td>
						</tr>
						<tr>
							<td height="10"></td>
						</tr>
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注書備考</td>
									<td><nested:textarea property="orderSheetRemark2" cols="70" rows="3" styleId="orderSheetRemark2" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">備考</td>
									<td><nested:textarea property="remark2" cols="70" rows="3" styleId="remark2" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
							<table>
								<tr>
									<nested:notEqual property="status2" value="2">
									<nested:notEqual property="status2" value="3">
										<nested:equal property="updateAuthority" value="true">
											<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
										</nested:equal>
									</nested:notEqual>
									</nested:notEqual>
									<nested:equal property="status2" value="1">
										<nested:equal property="approvalRequestAuthority" value="true">
											<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(approveConfirm())) {return false;}else{return form_submit('op', 'approve'); return false;}" class="cssButton">
													&nbsp;承認依頼&nbsp;
													</a>
												</div>
											</td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
										</nested:equal>
									</nested:equal>
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

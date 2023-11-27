<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- Style Sheet --%>
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

		<%-- 返品情報表示状態変更 --%>
		changeReturnsField();

		if($("cancelFlg").value == "0"){

			// 新規初期表示以外は金額計算を行う
			if($("insertFlg").value == "0") {
				mulStockingAmount();
			}
		<%-- フォーカス設定(品目、他社コード、仕入先、数量、入力時のPOSTから帰ってきたとき) --%>
		if ( !blank($('focusPosition').value) ) {
			document.forms[0].elements[$F('focusPosition')].focus();
			$('focusPosition').value = "";

			mulStockingAmount();
		}

			<%-- フォーカス設定(品目、他社コード、仕入先、数量、入力時のPOSTから帰ってきたとき) --%>
 			if ( !blank($('focusPosition').value) ) {
				document.forms[0].elements[$F('focusPosition')].focus();
				$('focusPosition').value = "";
				mulStockingAmount();
			}

			<%-- 仕入先のauto complete --%>
			new Ajax.Autocompleter(
			  "venderCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
			  {
			  	paramName : "code",
			  	parameters : 'venderDivision=SI',
			    afterUpdateElement : setVenderLabel
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('venderCd', 'keypress', clearDivLabel.bindAsEventListener($('venderShortedName')), false);

			<%-- 品目名称のauto complete --%>
			new Ajax.Autocompleter(
			  "itemCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
			  {
			  	paramName : "code",
			    afterUpdateElement : setItemQueueLabel
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('itemMasterName')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);

			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('inoutNo')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('inoutLotNo')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('inoutQty')), false);
			Event.observe('itemCd', 'keypress', clearDivLabel.bindAsEventListener($('lblInoutQty')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('inoutWeight')), false);
			Event.observe('itemCd', 'keypress', clearDivLabel.bindAsEventListener($('lblInoutWeight')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('inoutDate')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('ryName')), false);
			Event.observe('itemCd', 'keypress', clearText.bindAsEventListener($('inputorName')), false);

			<%-- 他社コード１のauto complete --%>
			new Ajax.Autocompleter(
			  "otherCompanyCd1",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItemOtherCompany",
			  {
			  	paramName : "code",
			    afterUpdateElement : setOtherCompany1Label
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('itemMasterName')), false);
			Event.observe('otherCompanyCd1', 'keypress', clearText.bindAsEventListener($('itemCd')), false);

			<%-- 会計部門のauto complete --%>
			<%-- 借方 --%>
			new Ajax.Autocompleter(
			  "accountDebitSectionCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
			  {
			  	paramName : "code",
			    afterUpdateElement : setAccountDebitSectionName
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('accountDebitSectionCd', 'keypress', clearLabel.bindAsEventListener($('accountDebitSectionName')), false);

			<%-- 貸方 --%>
			new Ajax.Autocompleter(
			  "accountCreditSectionCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
			  {
			  	paramName : "code",
			    afterUpdateElement : setAccountCreditSectionName
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('accountCreditSectionCd', 'keypress', clearLabel.bindAsEventListener($('accountCreditSectionName')), false);

			<%-- 勘定科目のauto complete --%>
			<%-- 借方 --%>
			new Ajax.Autocompleter(
			  "debitTitleCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
			  {
			 	paramName : "code",
				afterUpdateElement : setDebitTitleName
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('debitTitleCd', 'keypress', clearLabel.bindAsEventListener($('debitTitleName')), false);
			<%-- 貸方 --%>
			new Ajax.Autocompleter(
			  "creditTitleCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
			  {
			 	paramName : "code",
				afterUpdateElement : setCreditTitleName
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('creditTitleCd', 'keypress', clearLabel.bindAsEventListener($('creditTitleName')), false);

			<%-- 担当部署のauto complete --%>
			new Ajax.Autocompleter(
			  "chargeOrganizationCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			  {
			  	paramName : "code",
			    afterUpdateElement : setChargeOrganizationLabel
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('chargeOrganizationCd', 'keypress', clearDivLabel.bindAsEventListener($('tantoSectionName')), false);

			<%-- 部署のauto complete --%>
			new Ajax.Autocompleter(
			  "organizationCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			  {
			  	paramName : "code",
			    afterUpdateElement : setOrganizationLabel
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('organizationCd', 'keypress', clearDivLabel.bindAsEventListener($('sectionName')), false);

			<%-- 担当者のauto complete --%>
			new Ajax.Autocompleter(
			  "tantoCd",
			  "autocomplete_selection",
			  "<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			  {
			  	paramName : "code",
			    afterUpdateElement : setLoginLabel
			  }
			);
			<%-- 名称のクリアauto complete --%>
			Event.observe('tantoCd', 'keypress', clearDivLabel.bindAsEventListener($('tantoNm')), false);

		}

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strStockingDate");
		if($("cancelFlg").value == "0"){
			defineAsRequiredField("itemCd");
			defineAsRequiredField("venderCd");
			defineAsRequiredField("strStockingQuantity");
			defineAsRequiredField("strHousingUnitprice");
			defineAsRequiredField("accountDebitSectionCd");
			defineAsRequiredField("accountCreditSectionCd");
			defineAsRequiredField("debitTitleCd");
			defineAsRequiredField("creditTitleCd");
			defineAsRequiredField("chargeOrganizationCd");
			defineAsRequiredField("organizationCd");
			defineAsRequiredField("tantoCd");
		}
			<%-- 日付型フィールド定義 --%>
			defineAsDateField("strStockingDate");

			<%-- 数値型フィールド定義 --%>
			defineAsNumberField("strStockingQuantity");
			defineAsNumberField("strHousingUnitprice");

			<%-- 検索後入力された場合の不整合をただす --%>

			storeInitValues(/^srh.*/, 'dirtyFlg');
			refreshLabel();

	}, false);

	<%-- 仕入先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("venderShortedName").update(li.getElementsByTagName('span')[0].innerText);
	    <%-- 仕入先を変更した時に連動して変わるデータを画面にセット --%>
	    setFocusPosition("venderCd");
	    form_submit('op', 'getVenderRelatedData');
	}
	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("itemMasterName").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	    <%-- 品目を変更した時に連動して変わるデータを画面にセット --%>
	    setFocusPosition("itemCd");
	    form_submit('op', 'getItemRelatedData');
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("itemMasterName").value = li.getElementsByTagName('span')[0].innerText;
	    $("itemCd").value = getHiddenValue(li,"code");
	    <%-- 品目を変更した時に連動して変わるデータを画面にセット --%>
		setFocusPosition("otherCompanyCd1");
	    form_submit('op', 'getItemRelatedData');
	}

	<%-- 会計部門 --%>
	<%-- 借方auto completeの選択後処理 --%>
	function setAccountDebitSectionName(text, li) {
	    $("lblAccountDebitSectionName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("accountDebitSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 貸方auto completeの選択後処理 --%>
	function setAccountCreditSectionName(text, li) {
	    $("lblAccountCreditSectionName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("accountCreditSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 勘定科目 --%>
	<%-- 借方auto completeの選択後処理 --%>
	function setDebitTitleName(text, li) {
	    $("lblDebitTitleName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("debitTitleName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 貸方auto completeの選択後処理 --%>
	function setCreditTitleName(text, li) {
	    $("lblCreditTitleName").innerText = li.getElementsByTagName('span')[0].innerText;
	    $("creditTitleName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 担当部署auto completeの選択後処理 --%>
	function setChargeOrganizationLabel(text, li) {
	    $("tantoSectionName").update(li.getElementsByTagName('span')[0].innerText);
	}
	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
	    $("sectionName").update(li.getElementsByTagName('span')[0].innerText);
	}
	<%-- 担当者auto completeの選択後処理 --%>
	function setLoginLabel(text, li) {
	    $("tantoNm").update(li.getElementsByTagName('span')[0].innerText);
	}

	<%-- 入庫ロケーションauto completeの選択後処理 --%>
	function setLocationName(text, li) {
	    $("locationName").update(li.getElementsByTagName('span')[0].innerText);
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してもよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function approvalRrequestAlert() {
		alertMsg = new Array();
		alertMsg[0] = "承認依頼してもよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function cancelAlert() {
		alertMsg = new Array();
		alertMsg[0] = "取消してもよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	<%-- 変更フラグセット --%>
	function setTaxCdChangeFlg() {
		$("taxCdChangeFlg").value = "true";
	}

	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirmIssue() {
		var flg = $("dirtyFlg").value;
		if (flg == "true" && isEdited()) {
			// 何か値が変更されている場合
			alertMsg = new Array();
			alertMsg[0] = "画面の編集内容は反映されません、よろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			return true;
		}
	}

	/* 軽減税率対応 */
	function calcTax(){
	    setFocusPosition("taxCd");
		form_submit('op', 'calcStockingAmountAndTax');
	}


	<%-- 金額を計算 --%>
	<%-- 個あたり:単価*数量 --%>
	<%-- Kgあたり:単価*数量*(品目M)Kg換算係数(在庫) --%>
	function mulStockingAmount(){
		mulStockingQuantity();	<%-- 数量のフォーマット編集 --%>
		mulHousingUnitprice();	<%-- 単価のフォーマット編集 --%>
		var unitpriceDivision = $("unitpriceDivision").value;			<%-- 単価区分 --%>
		var strStockingQuantity = $("strStockingQuantity").value;		<%-- 数量 --%>
		var strHousingUnitprice = $("strHousingUnitprice").value;		<%-- 単価 --%>
		var kgOfFractionManagement = $("kgOfFractionManagement").value;		<%-- (品目M)Kg換算係数(在庫) --%>

		<%-- 個あたり:1 --%>
		if (unitpriceDivision == "1" || unitpriceDivision == "") {
			if( !blank(strStockingQuantity)  &&  !blank(strHousingUnitprice)){
				var strStockingQuantity2 = parseFloat(strStockingQuantity.replace(/,/g, ""));
				var strHousingUnitprice2 = parseFloat(strHousingUnitprice.replace(/,/g, ""));

				if( !isNaN(strStockingQuantity2) && !isNaN(strHousingUnitprice2) ){
					var sum = multiFloat(strHousingUnitprice,strStockingQuantity2,$F("strHousingUnitpriceDecimalPoint"));
					<%-- 桁数丸め部品呼び出し：金額 --%>
					var buf1 = digitFormat($F("strStockingAmountDecimalPoint"),$F("strStockingAmountRound"),sum);
					$("labelStockingAmount").update(formatNumber(String(buf1)));
					$("strStockingAmount").value = formatNumber(String(buf1));
				}
			}
		}
		<%-- Kgあたり:2 --%>
		if (unitpriceDivision == "2") {
			if( !blank(strStockingQuantity)  &&  !blank(strHousingUnitprice) &&  !blank(kgOfFractionManagement)){
				var strStockingQuantity2 = parseFloat(strStockingQuantity.replace(/,/g, ""));
				var strHousingUnitprice2 = parseFloat(strHousingUnitprice.replace(/,/g, ""));
				var kgOfFractionManagement2 = parseFloat(kgOfFractionManagement.replace(/,/g, ""));

				if( !isNaN(strStockingQuantity2) && !isNaN(strHousingUnitprice2) ){
					var tf = strStockingQuantity2 * kgOfFractionManagement2;
					var sum = multiFloat(strHousingUnitprice,tf,$F("strHousingUnitpriceDecimalPoint"));
					<%-- 桁数丸め部品呼び出し：金額 --%>
					var buf1 = digitFormat($F("strStockingAmountDecimalPoint"),$F("strStockingAmountRound"),sum);
					$("labelStockingAmount").update(formatNumber(String(buf1)));
					$("strStockingAmount").value = formatNumber(String(buf1));
				}
			}
		}
<%-- 2014/3/18 新消費税対応--%>
<%-- インボイス対応 ①消費税、②軽減措置金額、③軽減措置消費税額の計算 --%>
 		var StockingAmount = $("strStockingAmount").value;
		var StockingAmount2 = parseFloat(StockingAmount.replace(/,/g, ""));
	    var TaxDivision = $("strTaxDivision").value;
	    var TaxRatio = $("taxRatio").value;
	    var TaxAmount;
		var invoiceAmount = 0;							// 金額(軽減措置)
		var invoiceTaxAmount = 0;						// 消費税(軽減措置)
		var invoiceTaxRatio = $F("invoiceTaxRatio"); 	// 税額控除割合

		if( !isNaN(StockingAmount2)){
			// ①消費税 = 金額 × 税率
		    if (TaxDivision == "3" || isNaN(StockingAmount2)){
		    	var TaxAmount = 0;
		    }else{
		    	var TaxAmount = StockingAmount2 * TaxRatio /100;
		    }
		    var bufTax = digitRound2($F("strTaxAmountDecimalPoint"),$F("strTaxAmountRound"),TaxAmount);
			$("lblStrTaxAmount").update(formatNumber(String(bufTax)));
			$("strTaxAmount").value = formatNumber(String(bufTax));

			// ②消費税(軽減措置) = 消費税 * 税額控除割合
			invoiceTaxAmount = bufTax * invoiceTaxRatio;
			<%-- 桁数丸め部品呼び出し：消費税(軽減措置) --%>
		    var bufInvoTax = digitRound2($F("strTaxAmountDecimalPoint"),$F("strTaxAmountRound"),invoiceTaxAmount);

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
<%-- 2014/3/18 新消費税対応--%>
	}
		<%-- 数量を数値フォーマットにする --%>
	function mulStockingQuantity(){
		var strStockingQuantity = $("strStockingQuantity").value;		<%-- 数量 --%>
		if( !blank(strStockingQuantity) ) {
			var strStockingQuantity2 = parseFloat(strStockingQuantity.replace(/,/g, ""));
			<%-- 桁数丸め部品呼び出し：数量 --%>
			var buf1 = digitFormat($F("strStockingQuantityDecimalPoint"),$F("strStockingQuantityRound"),strStockingQuantity2);
			$("strStockingQuantity").value = buf1;
		}
	}
		<%-- 単価を数値フォーマットにする --%>
	function mulHousingUnitprice(){
		var strHousingUnitprice = $("strHousingUnitprice").value;
		if( !blank(strHousingUnitprice) ) {
			var strHousingUnitprice2 = parseFloat(strHousingUnitprice.replace(/,/g, ""));
			<%-- 桁数丸め部品呼び出し：数量 --%>
			var buf1 = digitFormat($F("strHousingUnitpriceDecimalPoint"),$F("strHousingUnitpriceRound"),strHousingUnitprice2);
			$("strHousingUnitprice").value = buf1;
		}
	}

		<%-- フォーカス位置の保存 --%>
	function setFocusPosition(pos){
		$("focusPosition").value = pos;
	}


	<%-- 空白チェック（null,全てスペースも空白と判断する) --%>
	function blank(s){
		var reg = new RegExp(/^\s*$/);
		return reg.test(s);
	}

	<%-- 金額丸め処理 --%>
<%--	function onComplete(response) {
		var result = evalJSON(response.responseText);
		$("calcStockingAmount").update(result.value);
		$("strStockingAmount").value = result.value;
	} --%>

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

	<%-- 仕入区分変更時処理 --%>
	function changeReturnsField() {
		var div = $F("categoryDivision");
<%--		var spot = $F("spotDivision");--%>
<%--		var stock = $F("stockDivision");--%>
		if (div == 2 <%--and spot != 2 and stock !=3 --%>) {
			$("divReturns").style.display = "block";
		} else {
			$("divReturns").style.display = "none";
		}
	}
	<%-- 20211208 Asclab Kouchi EdgeIE対応 --%>
	<%-- ポップアップ後の設定処理 --%>
	function setBuyingInoutValues(buyingInoutFlg,inoutNo,lotNo,strInoutQty,strInoutWeight,inoutDate,ryName,inputorName) {
		if (buyingInoutFlg) {
			$("inoutNo").value = inoutNo;
			$("inoutLotNo").value = lotNo;
			$("inoutQty").value = strInoutQty;
			$("inoutWeight").value = strInoutWeight;
			$("inoutDate").value = inoutDate;
			$("ryName").value = ryName;
			$("inputorName").value = inputorName;
			$("lblInoutQty").update($("inoutQty").value);
			$("lblInoutWeight").update($("inoutWeight").value);
		}
	}

<%-- 2014/1/28 新消費税対応--%>
	<%-- 売上日変更時処理 --%>
	function changeStockingDate() {
		form_submit('op', 'changeStockingDate');
	}
<%--2014/1/28 新消費税対応　--%>

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/BuyingDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" />
	<nested:hidden property="paymentInvoiceCd" />

	<%-- 運用管理単位  validation-xmlで使用--%><nested:hidden property="unitOfOperationManagement" styleId="unitOfOperationManagement" />
	<%-- Kg換算係数  (Kgあたりの場合金額計算で必要)--%><nested:hidden property="kgOfFractionManagement" styleId="kgOfFractionManagement" />
	<%-- 単価単位  (仕入別単価マスタの  金額計算で必要)--%><nested:hidden property="unitpriceDivision" styleId="unitpriceDivision" />
	<%-- スポット品区分 --%><nested:hidden property="spotDivision" styleId="spotDivision" />
	<%-- 在庫管理区分 --%><nested:hidden property="stockDivision" styleId="stockDivision" />
	<%-- 小数点位置：数量 --%><nested:hidden property="strStockingQuantityDecimalPoint" styleId="strStockingQuantityDecimalPoint" />
	<%-- 小数点位置：重量 --%><nested:hidden property="strConvertQuantityDecimalPoint" styleId="strConvertQuantityDecimalPoint" />
	<%-- 小数点位置：単価 --%><nested:hidden property="strHousingUnitpriceDecimalPoint" styleId="strHousingUnitpriceDecimalPoint" />
	<%-- 小数点位置：金額 --%><nested:hidden property="strStockingAmountDecimalPoint" styleId="strStockingAmountDecimalPoint" />
	<%-- 端数区分：数量 --%><nested:hidden property="strStockingQuantityRound" styleId="strStockingQuantityRound" />
	<%-- 端数区分：重量 --%><nested:hidden property="strConvertQuantityRound" styleId="strConvertQuantityRound" />
	<%-- 端数区分：単価 --%><nested:hidden property="strHousingUnitpriceRound" styleId="strHousingUnitpriceRound" />
	<%-- 端数区分：金額 --%><nested:hidden property="strStockingAmountRound" styleId="strStockingAmountRound" />
	<%-- フォーカス位置保存 --%><nested:hidden property="focusPosition" styleId="focusPosition" />
	<%-- 取消ボタンを表示するかどうかのフラグ --%><nested:hidden property="cancelFlg" styleId="cancelFlg" />
	<%-- 仕入区分を入力可／不可のフラグ --%><nested:hidden property="categoryFlg" styleId="categoryFlg" />
	<%-- 入庫ﾛｹｰｼｮﾝが表示項目から除外されたため念のためhiddenにもつ --%>

	<%-- 2014/3/18 新消費税対応 --%>
	<%-- 小数点位置：消費税 --%><nested:hidden property="strTaxAmountDecimalPoint" styleId="strTaxAmountDecimalPoint" />
	<%-- 端数区分：消費税 --%><nested:hidden property="strTaxAmountRound" styleId="strTaxAmountRound" />
	<%-- 2014/3/18 新消費税対応 --%>

	<nested:hidden property="housingLocationCd" styleId="housingLocationCd" />
	<%-- 分類コードリスト --%>
	<nested:iterate id="item" property="categoryDivisionList" indexId="index">
		<input type="hidden" id="categoryDivisionList<bean:write name='index'/>" value="<bean:write name='item'/>" >
	</nested:iterate>
	<%-- 仕分反転フラグリスト --%>
	<nested:iterate id="item" property="reversalFlgList" indexId="index">
		<input type="hidden" id="reversalFlgList<bean:write name='index'/>" value="<bean:write name='item'/>" >
	</nested:iterate>
	<%-- 仕訳反転フラグ前回値 --%><nested:hidden property="preReversalFlg" styleId="preReversalFlg" />
	<%-- 金額 --%><nested:hidden property="strStockingAmount" styleId="strStockingAmount" />
	<%-- 消費税額--%><nested:hidden property="strTaxAmount" styleId="strTaxAmount" />
	<%-- 消費税コード変更フラグ--%>	<nested:hidden property="taxCdChangeFlg" />
	<%-- 消費税コード変更フラグ--%>	<nested:hidden property="taxRatio" />
	<nested:hidden property="invoiceTaxRatio" styleId="invoiceTaxRatio" />
	<nested:hidden property="strInvoiceAmount" styleId="strInvoiceAmount" />
	<nested:hidden property="strInvoiceTaxAmount" styleId="strInvoiceTaxAmount" />
	<nested:hidden property="reducedTaxTargetFlg" styleId="reducedTaxTargetFlg" />


	<nested:hidden property="inoutNo" />

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
							<td height="5"  colspan="2" ></td>
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
					<td><%-- 明細部 --%>
						<table cellspacing="2" cellpadding="1" width="" border="0" >
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">仕入伝票番号</td>
								<td width="180">
									<nested:write property="slipNo" />
								</td>

								<td class="bcTitleSearch fb fcTitleSearch" width="140">仕入区分</td>
								<td>
									<nested:equal property="categoryFlg" value="1">
										<nested:select property="categoryDivision" style="margin: 0;padding: 0" onchange="categoryDivisionAction(this);changeReturnsField();">
											<nested:optionsCollection property="stockinDivisionCombo" label="labales" value="values" />
										</nested:select>
									</nested:equal>
									<nested:equal property="categoryFlg" value="0">
										<nested:hidden property="categoryDivision" styleId="categoryDivision" />
										<span id="categoryName"><nested:write property="categoryName" /></span>
									</nested:equal>
								</td>
							</tr>
						</table>
						<table cellspacing="2" cellpadding="1" width="" border="0" >
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">仕入日付</td>
								<td>
									<nested:text property="strStockingDate" maxlength="10" styleId="strStockingDate"  style="ime-mode:disabled" onchange="setDirtyFlg();setFocusPosition('strStockingDate');changeStockingDate();"/>
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
								<td>
									<nested:equal property="cancelFlg" value="1">
									<nested:text property="itemCd" maxlength="20" styleId="itemCd" readonly="true" />
									</nested:equal>
									<nested:notEqual property="cancelFlg" value="1">
									<nested:text property="itemCd" maxlength="20" styleId="itemCd" onchange="setDirtyFlg();"/>
									</nested:notEqual>
								</td>
								<td align="left">
									<nested:equal property="spotDivision" value="2">
										<%-- スポット区分＝２、品目名称入力可 --%>
										<nested:text property="itemMasterName" maxlength="40" size="56" styleId="itemMasterName" onchange="setDirtyFlg();" />
									</nested:equal>
									<nested:notEqual property="spotDivision" value="2">
										<%-- スポット区分！＝２、品目名称入力不可 --%>
										<nested:text property="itemMasterName" maxlength="40" size="56" readonly="true" styleId="itemMasterName" styleClass="noborderAl" tabindex="-1"/>
									</nested:notEqual>
									<nested:notEqual property="cancelFlg" value="1">
									<div id="autocomplete_selection" class="autocomplete"></div>
									</nested:notEqual>
								</td>
							</tr>

							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">仕入先コード</td>
								<td>
								<nested:equal property="cancelFlg" value="1">
								<nested:text property="venderCd" maxlength="10" styleId="venderCd1" readonly="true" />
								</nested:equal>
								<nested:notEqual property="cancelFlg" value="1">
								<nested:text property="venderCd" maxlength="10" styleId="venderCd" onchange="setDirtyFlg();"/>
								</nested:notEqual>
								</td>

								<td align="left">
									<span id="venderShortedName">
									<nested:equal property="reducedTaxTargetFlg" value="1">
										<font color=red>【未申請業者等】</font>
									</nested:equal>
									<nested:write property="venderShortedName" /></span>
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">他社コード１</td>
								<td align="left">
								    <nested:equal property="cancelFlg" value="1">
									<nested:text property="otherCompanyCd1" maxlength="9" styleId="otherCompanyCd1"  style="ime-mode:disabled" readonly="true"/>
								    </nested:equal>
								    <nested:notEqual property="cancelFlg" value="1">
									<nested:text property="otherCompanyCd1" maxlength="9" styleId="otherCompanyCd1"  style="ime-mode:disabled" onchange="setDirtyFlg();"/>
									</nested:notEqual>
								</td>

							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">購入数量</td>
								<td>
								    <nested:equal property="cancelFlg" value="1">
									<nested:text property="strStockingQuantity" maxlength="22" styleId="strStockingQuantity" style="ime-mode:disabled" readonly="true" />
								    </nested:equal>
								    <nested:notEqual property="cancelFlg" value="1">
									<nested:text property="strStockingQuantity" maxlength="22" styleId="strStockingQuantity" style="ime-mode:disabled" onchange="setDirtyFlg();setFocusPosition('strHousingUnitprice');form_submit('op', 'getStockingQuantityRelatedData');" />
									</nested:notEqual>
								</td>
								<td>
									<nested:write property="stockingQuantityUnit" />
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">単価</td>
								<td>
								    <nested:equal property="cancelFlg" value="1">
									<nested:text property="strHousingUnitprice" maxlength="22" styleId="strHousingUnitprice" style="ime-mode:disabled" readonly="true" />
								    </nested:equal>
								    <nested:notEqual property="cancelFlg" value="1">
									<nested:text property="strHousingUnitprice" maxlength="22" styleId="strHousingUnitprice" style="ime-mode:disabled" onchange="setDirtyFlg();mulStockingAmount();" />
									</nested:notEqual>
								</td>
								<td>
									<span id="housingUnitpriceUnit"><nested:write property="housingUnitpriceUnit" /></span>
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch" width="80">金額</td>
								<td class="alignRight">
									<span id="labelStockingAmount"><nested:write property="strStockingAmount" /></span>
								</td>
								<td>
									<table cellspacing="2" cellpadding="1" width="" border="0" >
										<tr>
											<td align="left">円</td>
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

											<td class="fcTitleDetail fb bcTitleDetail" width="100">消費税額</td>
											<td width=100px" class="alignRight">
													<span id="lblStrTaxAmount">
														<nested:write property="strTaxAmount" />
													</span>円
											</td>
											<td class="fcTitleDetail fb bcTitleDetail" width="70">消費税率</td>
											<td>
												<%-- <nested:select property="strTaxRatio" style="margin: 0;padding: 0" onchange="setDirtyFlg();mulStockingAmount();">
													<nested:optionsCollection property="taxCombo" label="labales" value="values" /> --%>
												<nested:select property="taxCd" style="margin: 0;padding: 0" onchange="setDirtyFlg();setTaxCdChangeFlg();calcTax();">
																<nested:options property="taxValues" labelProperty="taxLabels"/>
												</nested:select>
											</td>
											<td>
												<%
												pageContext.setAttribute("SelectNewTaxDivision", new com.asahikaseieng.app.comboboxes.SelectNewTaxDivision(false, false));
												%>
												<nested:select property="strTaxDivision" onchange="setDirtyFlg();setTaxCdChangeFlg();mulStockingAmount();">
													<nested:options name="SelectNewTaxDivision" property="values" labelName="SelectNewTaxDivision" labelProperty="labels"/>
												</nested:select>
											</td>
	<!-- 2014/1/28 新消費税対応 -->

										</tr>
									</table>
								</td>
							</tr>
							<nested:equal property="reducedTaxTargetFlg" value="1">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail">軽減措置金額</td>
								<td class="alignRight">
										<span id="lblStrInvoiceAmount">
											<nested:write property="strInvoiceAmount" />
										</span>
								</td>
								<td>
									<table cellspacing="2" cellpadding="1" width="" border="0" >
										<td align="left">円</td>

										<td class="fcTitleDetail fb bcTitleDetail" width="100">軽減措置税額</td>
										<td width=100px" class="alignRight">
											<span id="lblStrInvoiceTaxAmount">
												<nested:write property="strInvoiceTaxAmount"/>
											</span>円
										</td>
									</table>
								</td>
							</tr>
							</nested:equal>
						</table >
						<table cellspacing="2" cellpadding="1" id="tblList" border="0" >
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch" width="85">仕訳</td>
								<td width="200"></td>
							</tr>
							<tr>
								<td colspan="2" valign="Top">
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
										<%-- 借方 --%>
										<td>
								    		<nested:equal property="cancelFlg" value="1">
											<nested:text property="accountDebitSectionCd" maxlength="15" size="10" styleId="accountDebitSectionCd" styleClass="alignLeft" readonly="true"/>
								    		</nested:equal>
								    		<nested:notEqual property="cancelFlg" value="1">
											<nested:text property="accountDebitSectionCd" maxlength="15" size="10" styleId="accountDebitSectionCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
											</nested:notEqual>
										</td>
										<td width="120px">
											<div id="lblAccountDebitSectionName">
												<nested:write property="accountDebitSectionName" />
											</div>
											<nested:hidden property="accountDebitSectionName" styleId="accountDebitSectionName" />
										</td>
										<%-- 貸方 --%>
										<td>
								    		<nested:equal property="cancelFlg" value="1">
											<nested:text property="accountCreditSectionCd" maxlength="15" size="10" styleId="accountCreditSectionCd" styleClass="alignLeft" readonly="true"/>
								    		</nested:equal>
								    		<nested:notEqual property="cancelFlg" value="1">
											<nested:text property="accountCreditSectionCd" maxlength="15" size="10" styleId="accountCreditSectionCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
											</nested:notEqual>
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
										<%-- 借方 --%>
										<td>
								    		<nested:equal property="cancelFlg" value="1">
											<nested:text property="debitTitleCd" maxlength="15" size="10" styleId="debitTitleCd" styleClass="alignLeft" readonly="true"/>
								    		</nested:equal>
								    		<nested:notEqual property="cancelFlg" value="1">
											<nested:text property="debitTitleCd" maxlength="15" size="10" styleId="debitTitleCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
											</nested:notEqual>
										</td>
										<td width="120px">
											<div id="lblDebitTitleName">
												<nested:write property="debitTitleName" />
											</div>
											<nested:hidden property="debitTitleName" styleId="debitTitleName" />
										</td>
										<%-- 貸方 --%>
										<td>
								    		<nested:equal property="cancelFlg" value="1">
											<nested:text property="creditTitleCd" maxlength="15" size="10" styleId="creditTitleCd" styleClass="alignLeft" readonly="true"/>
								    		</nested:equal>
								    		<nested:notEqual property="cancelFlg" value="1">
											<nested:text property="creditTitleCd" maxlength="15" size="10" styleId="creditTitleCd" styleClass="alignLeft" onchange="setDirtyFlg();"/>
											</nested:notEqual>
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
							<tr>
								<td height="2"></td>
							</tr>
						</table>


						<div id="divReturns" style="display: none;" >
							<table cellspacing="2" cellpadding="1"  border="0">

								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">受払選択</td>

									<td colspan="2">
										<span id="cssButton">
											<!-- 20211208 Asclab Kouchi EdgeIE対応 -->
											<a href="#"  class="cssButton"
											   onclick="<%="open_modal_popup_edge(765, 600,'BuyingInoutSearch', '','srhItemCd', $('itemCd').value,'srhVenderCd', $('venderCd').value); setDirtyFlg(); return false;"%>">
												選
											</a>&nbsp;
										</span>
									</td>
								</tr>
								<tr>

									<table border="0" cellpadding="0" cellspacing="0" width="750">
										<tr>
											<td>
												<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
													<tr class="bcTitleList fb fcTitleList">
														<td>ロット番号</td>
														<td>数量</td>
														<td>重量</td>
														<td>受払日付</td>
														<td>理由</td>
														<td>登録者</td>
													</tr>

													<tr>
														<nested:hidden property="inoutQty" />
														<nested:hidden property="inoutWeight" />
														<%-- ロット番号 --%>
														<td class="alignLeft">
															<nested:text property="inoutLotNo" maxlength="9" size="8" readonly="true" styleId="inoutLotNo" styleClass="noborderAl" tabindex="-1"/>
														</td>

														<%-- 数量 --%>
														<td class="alignRight">
															<span id="lblInoutQty"><nested:write property="inoutQty"/>
														</td>

														<%-- 重量 --%>
														<td class="alignRight">
															<span id="lblInoutWeight"><nested:write property="inoutWeight"/>
														</td>

														<%-- 受払日付 --%>
														<td class="alignLeft">
															<nested:text property="inoutDate" maxlength="9" size="8" readonly="true" styleId="inoutDate" styleClass="noborderAl" tabindex="-1"/>
														</td>

														<%-- 理由 --%>
														<td class="alignLeft">
															<nested:text property="ryName" maxlength="25" size="25" readonly="true" styleId="ryName" styleClass="noborderAl" tabindex="-1"/>
														</td>

														<%-- 登録者 --%>
														<td class="alignLeft">
															<nested:text property="inputorName" maxlength="10" size="10" readonly="true" styleId="inputorName" styleClass="noborderAl" tabindex="-1"/>
														</td>

													</tr>
												</table>
											</td>
										</tr>
									</table>

								</tr>
							</table>
						</div>


						<table cellspacing="2" cellpadding="1"  border="0">
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">担当部署</td>
								<td>
								    <nested:equal property="cancelFlg" value="1">
									<nested:text property="chargeOrganizationCd" maxlength="10" styleId="chargeOrganizationCd" readonly="true" />
								    </nested:equal>
								    <nested:notEqual property="cancelFlg" value="1">
									<nested:text property="chargeOrganizationCd" maxlength="10" styleId="chargeOrganizationCd" onchange="setDirtyFlg();"/>
									</nested:notEqual>
								</td>
								<td align="left">
									<div id="tantoSectionName"><nested:write property="tantoSectionName" /></div>
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">部署</td>
								<td>
								    <nested:equal property="cancelFlg" value="1">
									<nested:text property="organizationCd" maxlength="10" styleId="organizationCd" readonly="true" />
								    </nested:equal>
								    <nested:notEqual property="cancelFlg" value="1">
									<nested:text property="organizationCd" maxlength="10" styleId="organizationCd" onchange="setDirtyFlg();"/>
									</nested:notEqual>
								</td>
								<td align="left">
									<div id="sectionName"><nested:write property="sectionName" /></div>
								</td>
							</tr>
							<tr>
								<td class="bcTitleSearch fb fcTitleSearch">担当者コード</td>
								<td>
								    <nested:equal property="cancelFlg" value="1">
									<nested:text property="tantoCd" maxlength="10" styleId="tantoCd" readonly="true" />
								    </nested:equal>
								    <nested:notEqual property="cancelFlg" value="1">
									<nested:text property="tantoCd" maxlength="10" styleId="tantoCd" onchange="setDirtyFlg();"/>
									</nested:notEqual>
								</td>
								<td align="left">
									<div id="tantoNm"><nested:write property="tantoNm" /></div>
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail">摘要</td>
								<td colspan="2" width="100">
								    <nested:equal property="cancelFlg" value="1">
									<nested:textarea property="remark2" cols="50" rows="5" styleId="remark2" onchange="setDirtyFlg();" />
								    </nested:equal>
								    <nested:notEqual property="cancelFlg" value="1">
									<nested:textarea property="remark2" cols="50" rows="5" styleId="remark2" onchange="setDirtyFlg();" />
									</nested:notEqual>
								</td>
							</tr>

						</table>
					</td>
				</tr>
				<tr>
					<td class="alignCenter">
						<table>
							<tr>
								<nested:equal property="insertFlg" value="0">

									<%-- 更新時 --%>
									<%-- 仕入ステータス：未承認  AND 仕入で新規作成したデータのみ --%>
									<nested:equal property="status2" value="1">
										<nested:empty property="buySubcontractOrderNo" >
											<nested:empty property="cancelSlipNo" >
											<nested:notMatch property="categoryDivision" value="-" >
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td width="50"><br></td>
											</nested:notMatch>
											</nested:empty>
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
													&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td width="50"><br></td>
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(approvalRrequestAlert())) {return false;}else{return form_submit('op', 'approvalRrequest'); return false;}" class="cssButton">
													&nbsp;&nbsp;承認依頼&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td width="50"><br></td>
										</nested:empty>
									</nested:equal>

									<%-- 仕入ステータス：承認依頼中 --%>
									<nested:equal property="status2" value="2">
									</nested:equal>

									<%-- 仕入ステータス：承認済み  AND 月次更新済  AND 取消済みでない    cancelFlgをActionでセットしています --%>
									<nested:equal property="status2" value="3">
										<nested:equal property="cancelFlg" value="1">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if (!(cancelAlert())) {return false;}else{return form_submit('op', 'cancel'); return false;}" class="cssButton">
													&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td width="50"><br></td>
										</nested:equal>
									</nested:equal>

									<%-- 仕入ステータス：否認  AND 仕入で新規作成したデータのみ --%>
									<nested:equal property="status2" value="4">
										<nested:empty property="buySubcontractOrderNo">
											<nested:equal property="updateAuthority" value="true">
												<td class="alignCenter">
													<div id="cssButton">
														<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
														</a>
													</div>
												</td>
												<td width="50"><br></td>
											</nested:equal>
											<nested:equal property="deleteAuthority" value="true">
												<td class="alignCenter">
													<div id="cssButton">
														<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
														&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
														</a>
													</div>
												</td>
												<td width="50"><br></td>
											</nested:equal>
										</nested:empty>
									</nested:equal>

								</nested:equal>

								<%-- 新規時 --%>
								<nested:equal property="insertFlg" value="1">
									<nested:equal property="updateAuthority" value="true">
										<td class="alignCenter">
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>
										<td width="50"><br></td>
									</nested:equal>
								</nested:equal>

								<%-- 戻るボタンは常に表示 --%>
								<td class="alignCenter">
									<div id="cssButton">
										<a href="#" onclick="if (!(putDirtyConfirmIssue())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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
</nested:form>
</body>
</html:html>

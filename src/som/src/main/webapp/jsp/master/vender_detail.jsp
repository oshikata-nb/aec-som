<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

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
        defineAsRequiredField("paymentInvoiceCd");
        defineAsRequiredField("venderCd");
        defineAsRequiredField("venderShortedName");
        defineAsRequiredField("strClosingDate");
		defineAsRequiredField("strCreditLimitPrice");
		defineAsRequiredField("strTaxRatio");

		if ($('venderDivision').value == 'SI') {
			defineAsRequiredField("otherBankMasterCd");
			defineAsRequiredField("otherAccountNo");
			defineAsRequiredField("otherAccountStockhold");

			defineAsRequiredField("siInvoicePtn");
			if($('siInvoicePtn').value == '1') {
				defineAsRequiredField("invoiceNo");
			}
		} else{
			<%-- 売上インボイスパターンの入力が有効の場合、必須入力 --%>
			if($('tsInvoiceActiveFlg').value == "true"){
 				defineAsRequiredField("tsInvoicePtn");
			}
		}

		<%-- 数値型フィールド定義 --%>
        defineAsNumberField("strClosingDate");
		defineAsNumberField("strBoundAmount3");
		defineAsNumberField("strBoundAmount4");
		defineAsNumberField("strNoteSight3");
		defineAsNumberField("strNoteSight4");
		defineAsNumberField("strNoteSight5");
		defineAsNumberField("strCreditLimitPrice");
		defineAsNumberField("strTaxRatio");
		defineAsNumberField("strCreditScheduledDate1");
		defineAsNumberField("strCreditScheduledDate2");
		defineAsNumberField("strCreditScheduledDate3");

		if ($('venderDivision').value == 'SI') {
			defineAsNumberField("strPurchaseDiscountDays1");
			defineAsNumberField("strPurchaseDiscountDays2");
			defineAsNumberField("strPurchaseDiscountDays3");
		}

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("venderCd");
       	};

		<%-- 請求(支払)先のautocomplete --%>
		new Ajax.Autocompleter(
			"paymentInvoiceCd",
			"autocomplete_vender",
			"<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
			{
				parameters : 'venderDivision='+$("venderDivision").value,
				paramName : "code",
				afterUpdateElement : setVenderLabel
			}
		);

		<%-- 担当部署のautocomplete --%>
		new Ajax.Autocompleter(
			"organizationCd",
			"autocomplete_organization",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);

		<%-- 営業担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"tantoCd",
			"autocomplete_login",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setLoginLabel
			}
		);

		<%-- 地区のautocomplete --%>
		new Ajax.Autocompleter(
			"areaCd",
			"autocomplete_area",
			"<%= request.getContextPath() %>/AreaForAutoComplete.do?op=searchArea",
			{
				paramName : "code",
				afterUpdateElement : setAreaLabel
			}
		);

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

		<%-- 勘定科目のautocomplete --%>
		new Ajax.Autocompleter(
			"accountsCd",
			"autocomplete_accounts",
			"<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
			{
				paramName : "code",
				afterUpdateElement : setAccountsLabel
			}
		);

		<%-- カレンダーのautocomplete --%>
		new Ajax.Autocompleter(
			"calendarCd",
			"autocomplete_cal",
			"<%= request.getContextPath() %>/CalForAutoComplete.do?op=searchCal",
			{
				paramName : "code",
				afterUpdateElement : setCalLabel
			}
		);

		<%-- 相手銀行のautocomplete --%>
		new Ajax.Autocompleter(
			"otherBankMasterCd",
			"autocomplete_bank",
			"<%= request.getContextPath() %>/BankForAutoComplete.do?op=searchBankMaster",
			{
				paramName : "code",
				afterUpdateElement : setBankLabel
			}
		);

		<%-- メール送信元部署のautocomplete --%>
		new Ajax.Autocompleter(
			"mailOrganizationCd",
			"autocomplete_mailOrganization",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setMailOrganizationLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('paymentInvoiceCd', 'keypress', clearLabel.bindAsEventListener($('paymentInvoiceShortName')), false);
		Event.observe('organizationCd', 'keypress', clearLabel.bindAsEventListener($('organizationName')), false);
		Event.observe('tantoCd', 'keypress', clearLabel.bindAsEventListener($('tantoNm')), false);
		Event.observe('areaCd', 'keypress', clearLabel.bindAsEventListener($('areaName')), false);
		Event.observe('sectionCd', 'keypress', clearLabel.bindAsEventListener($('sectionName')), false);
		Event.observe('accountsCd', 'keypress', clearLabel.bindAsEventListener($('accountsName')), false);
		Event.observe('calendarCd', 'keypress', clearLabel.bindAsEventListener($('calName')), false);
		Event.observe('otherBankMasterCd', 'keypress', clearLabel.bindAsEventListener($('otherBankName')), false);
		Event.observe('otherBankMasterCd', 'keypress', clearLabel.bindAsEventListener($('otherBranchName')), false);
		Event.observe('mailOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('mailOrganizationName')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		if ($('cursor').value == '1') {
			Field.activate('taxDivision');
		} else if ($('cursor').value == '2') {
			Field.activate('sectionCd');
		}

		<%-- カーソルセット --%>
		setCursor(null);
	}, false);

	<%-- ajax --%>
	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("paymentInvoiceShortName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("organizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("tantoNm").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setAreaLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("areaName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setBumonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("sectionName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setAccountsLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("accountsName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setCalLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("calName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setBankLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("otherBankName").value = li.getElementsByTagName('span')[0].innerText;
		$("otherBranchName").value = getHiddenValue(li,"branchName");
		refreshLabel();
		setDirtyFlg();
	}

	function setMailOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("mailOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		<%-- カーソルセット --%>
		setCursor(null);

		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").checked;
		if (flg) {
			/* 何か値が変更されている場合 */
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 変更確認メッセージ --%>
	function putInfoDirtyConfirm() {
		var flg = false;

		if (!blank($("organizationCd").value)) {
			flg = true;
		}

		if (!blank($("venderName1").value)) {
			flg = true;
		}

		if (!blank($("venderName2").value)) {
			flg = true;
		}

		if (!blank($("venderShortedName").value)) {
			flg = true;
		}

		if (!blank($("zipcodeNo").value)) {
			flg = true;
		}

		if (!blank($("cityCd").value)) {
			flg = true;
		}

		if (!blank($("address1").value)) {
			flg = true;
		}

		if (!blank($("address2").value)) {
			flg = true;
		}

		if (!blank($("address3").value)) {
			flg = true;
		}

		if (flg == true) {
			var venderDivisionName;

			if ($("venderDivision").value == "TS") {
				venderDivisionName = "仕入先";
			} else {
				venderDivisionName = "得意先";
			}

			alertMsg = new Array();
			alertMsg[0] = "値が入力されていますが" + venderDivisionName + "情報を取得してよろしいですか？";
			return confirm(alertMsg[0]);
		}

		return true;
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}

	<%-- カーソルセット --%>
	function setCursor(pos) {
		$("cursor").value = pos;
	}

	<%-- カーソルセット --%>
	function setCreditDivision() {
		if ($("venderDivision").value == 'TS') {
			<%-- カーソル位置設定 --%>
			setCursor(2);

			<%-- リフレッシュ --%>
			form_submit('op', 'refresh');
		}
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/VenderDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="venderDivision" styleId="venderDivision"/>
	<nested:hidden property="newFlg" styleId="newFlg"/>
	<nested:hidden property="cursor" styleId="cursor" />
	<nested:hidden property="strTaxRatio" styleId="strTaxRatio" />
	<nested:hidden property="tsInvoiceActiveFlg" styleId="tsInvoiceActiveFlg" />


	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb">取引先マスタ</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<%-- メッセージ表示 --%>
													<%@ include file="/jsp/common/disp_info_message.jsf" %>
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
									<td height="5" colspan="2"></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<%-- メッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>

					<tr>
						<td>
							<!-- 明細部 -->
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="210" colspan="2">取引先区分</td>
									<td colspan="2">
										<nested:write property="venderDivisionName"/>
									</td>

									<nested:equal property="venderDivision" value="TS">
										<td class="fcTitleDetail fb bcTitleDetail" width="110">請求先</td>
									</nested:equal>

									<nested:equal property="venderDivision" value="SI">
										<td class="fcTitleDetail fb bcTitleDetail" width="110">支払先</td>
									</nested:equal>

									<td>
										<nested:text property="paymentInvoiceCd" maxlength="15" size="18" styleId="paymentInvoiceCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_vender" class="autocomplete"></div>
									</td>

									<td width="180" colspan="2">
										<div id="lblPaymentInvoiceShortName">
											<nested:write property="paymentInvoiceShortName" />
										</div>
										<nested:hidden property="paymentInvoiceShortName" styleId="paymentInvoiceShortName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">取引先コード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="venderCd" maxlength="15" size="15" style="ime-mode:disabled" styleId="venderCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="venderCd"/>
										</nested:notEqual>
									</td>

									<td>
										<div id="cssButton">
											<a href="#" onclick="if(!putInfoDirtyConfirm()){return false;}else{return form_submit('op', 'copy'); return false;}" class="cssButton">
												&nbsp;取&nbsp;
											</a>
										</div>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">担当部署</td>
									<td>
										<nested:text property="organizationCd" maxlength="10" size="10" styleId="organizationCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_organization" class="autocomplete"></div>
									</td>

									<td colspan="5">
										<div id="lblOrganizationName">
											<nested:write property="organizationName" />
										</div>
										<nested:hidden property="organizationName" styleId="organizationName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">取引先名称1</td>
									<td colspan="6">
										<nested:text property="venderName1" maxlength="30" size="55" styleId="venderName1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">取引先名称2</td>
									<td colspan="6">
										<nested:text property="venderName2" maxlength="30" size="55" styleId="venderName2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">取引先略称</td>
									<td colspan="6">
										<nested:text property="venderShortedName" maxlength="20" size="35" styleId="venderShortedName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">郵便番号</td>
									<td colspan="3">
										<nested:text property="zipcodeNo" maxlength="9" size="9" style="ime-mode:disabled" styleId="zipcodeNo" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">市町村コード</td>
									<td colspan="2">
										<nested:text property="cityCd" maxlength="5" size="5" style="ime-mode:disabled" styleId="cityCd" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">住所1</td>
									<td colspan="6">
										<nested:text property="address1" maxlength="60" size="75" styleId="address1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">住所2</td>
									<td colspan="6">
										<nested:text property="address2" maxlength="60" size="75" styleId="address3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">住所3</td>
									<td colspan="6">
										<nested:text property="address3" maxlength="60" size="75" styleId="address3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">電話番号</td>
									<td colspan="3">
										<nested:text property="telNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="telNo" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">FAX番号</td>
									<td colspan="2">
										<nested:text property="faxNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="faxNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<nested:equal property="venderDivision" value="TS">
								<tr>
<!-- 									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">仕入伝票FAX番号</td> -->
<!-- 									<td colspan="3"> -->
<%-- 										<nested:text property="slipFaxNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="slipFaxNo" onchange="setDirtyFlg();" /> --%>
<!-- 									</td> -->
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">郵送区分</td>
									<td>
											<% pageContext.setAttribute("SelectSalesVenderFaxDivision", new com.asahikaseieng.app.comboboxes.SelectSalesVenderFaxDivision(false, false)); %>
											<nested:select property="faxOutput" onchange="setDirtyFlg();">
												<nested:options name="SelectSalesVenderFaxDivision" property="values" labelName="SelectSalesVenderFaxDivision" labelProperty="labels"/>
											</nested:select>
									</td>
								</tr>
								</nested:equal>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">受注FAX番号</td>
									<td colspan="3">
										<nested:text property="orderFaxNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="orderFaxNo" onchange="setDirtyFlg();" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">受注FAX送信区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectMailSendDivision", new com.asahikaseieng.app.comboboxes.SelectMailSendDivision(false, false)); %>
										<nested:select property="orderFaxOutput" onchange="setDirtyFlg();" >
											<nested:options name="SelectMailSendDivision" property="values" labelName="SelectMailSendDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">受注メール送信区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectMailSendDivision", new com.asahikaseieng.app.comboboxes.SelectMailSendDivision(false, false)); %>
										<nested:select property="orderMailOutput" onchange="setDirtyFlg();" >
											<nested:options name="SelectMailSendDivision" property="values" labelName="SelectMailSendDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">受注メールアドレス1</td>
									<td colspan="6">
										<nested:text property="orderMailAddress1" maxlength="254" size="75" style="ime-mode:disabled" styleId="orderMailAddress1" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">受注メールアドレス2</td>
									<td colspan="6">
										<nested:text property="orderMailAddress2" maxlength="254" size="75" style="ime-mode:disabled" styleId="orderMailAddress2" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">受注メールアドレス3</td>
									<td colspan="6">
										<nested:text property="orderMailAddress3" maxlength="254" size="75" style="ime-mode:disabled" styleId="orderMailAddress3" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">売上FAX番号</td>
									<td colspan="3">
										<nested:text property="salesFaxNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="salesFaxNo" onchange="setDirtyFlg();" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">売上FAX送信区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectMailSendDivision", new com.asahikaseieng.app.comboboxes.SelectMailSendDivision(false, false)); %>
										<nested:select property="salesFaxOutput" onchange="setDirtyFlg();" >
											<nested:options name="SelectMailSendDivision" property="values" labelName="SelectMailSendDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">売上メール送信区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectMailSendDivision", new com.asahikaseieng.app.comboboxes.SelectMailSendDivision(false, false)); %>
										<nested:select property="salesMailOutput" onchange="setDirtyFlg();" >
											<nested:options name="SelectMailSendDivision" property="values" labelName="SelectMailSendDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">売上メールアドレス1</td>
									<td colspan="6">
										<nested:text property="salesMailAddress1" maxlength="254" size="75" style="ime-mode:disabled" styleId="salesMailAddress1" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">売上メールアドレス2</td>
									<td colspan="6">
										<nested:text property="salesMailAddress2" maxlength="254" size="75" style="ime-mode:disabled" styleId="salesMailAddress2" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">売上メールアドレス3</td>
									<td colspan="6">
										<nested:text property="salesMailAddress3" maxlength="254" size="75" style="ime-mode:disabled" styleId="salesMailAddress3" onchange="setDirtyFlg();" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">メール送信元部署</td>
									<td>
										<nested:text property="mailOrganizationCd" maxlength="10" size="10" styleId="mailOrganizationCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_mailOrganization" class="autocomplete"></div>
									</td>

									<td colspan="5">
										<div id="lblMailOrganizationName">
											<nested:write property="mailOrganizationName" />
										</div>
										<nested:hidden property="mailOrganizationName" styleId="mailOrganizationName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">代表者役職</td>
									<td colspan="3">
										<nested:text property="representRole" maxlength="20" size="25" styleId="representRole" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">代表者氏名</td>
									<td colspan="2">
										<nested:text property="representPerson" maxlength="20" size="20" styleId="representPerson" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">営業担当者</td>
									<td>
										<nested:text property="tantoCd" maxlength="10" size="10" styleId="tantoCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_login" class="autocomplete"></div>
									</td>

									<td width="530" colspan="5">
										<div id="lblTantoNm">
											<nested:write property="tantoNm" />
										</div>
										<nested:hidden property="tantoNm" styleId="tantoNm"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">地区</td>
									<td>
										<nested:text property="areaCd" maxlength="10" size="10" styleId="areaCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_area" class="autocomplete"></div>
									</td>

									<td width="530" colspan="5">
										<div id="lblAreaName">
											<nested:write property="areaName" />
										</div>
										<nested:hidden property="areaName" styleId="areaName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">締日</td>
									<td colspan="3">
										<nested:text property="strClosingDate" maxlength="2" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strClosingDate" onchange="setDirtyFlg();" />日
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">下請法該当</td>
									<td colspan="2">
										<nested:checkbox property="subcontractLaw" styleId="subcontractLaw" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">与信限度</td>
									<td colspan="3">
										<nested:text property="strCreditLimitPrice" maxlength="22" size="22" styleClass="alignRight" style="ime-mode:disabled" styleId="strCreditLimitPrice" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">前受金区分</td>
									<td colspan="2">
										<% pageContext.setAttribute("SelectAdvanceDivision", new com.asahikaseieng.app.comboboxes.SelectAdvanceDivision(false, false)); %>
										<nested:select property="advanceDivision" onchange="setCreditDivision(); setDirtyFlg();" >
											<nested:options name="SelectAdvanceDivision" property="values" labelName="SelectAdvanceDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<nested:equal property="venderDivision" value="TS">
										<td class="fcTitleDetail fb bcTitleDetail" rowspan="2" width="10">売掛</td>
									</nested:equal>

									<nested:equal property="venderDivision" value="SI">
										<td class="fcTitleDetail fb bcTitleDetail" rowspan="2" width="10">買掛</td>
									</nested:equal>

									<td class="fcTitleDetail fb bcTitleDetail" width=120>会計部門</td>
									<td>
										<nested:text property="sectionCd" maxlength="10" size="10" styleId="sectionCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_bumon" class="autocomplete"></div>
									</td>

									<td width="530" colspan="5">
										<div id="lblSectionName">
											<nested:write property="sectionName" />
										</div>
										<nested:hidden property="sectionName" styleId="sectionName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">勘定科目</td>
									<td>
										<nested:text property="accountsCd" maxlength="10" size="10" styleId="accountsCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_accounts" class="autocomplete"></div>
									</td>

									<td width="530" colspan="5">
										<div id="lblAccountsName">
											<nested:write property="accountsName" />
										</div>
										<nested:hidden property="accountsName" styleId="accountsName"/>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">区分</td>
									<td class="fcTitleDetail fb bcTitleDetail">手形サイト</td>
									<td class="fcTitleDetail fb bcTitleDetail">境界額</td>

									<nested:equal property="venderDivision" value="TS">
										<td class="fcTitleDetail fb bcTitleDetail">入金月区分</td>
										<td class="fcTitleDetail fb bcTitleDetail">入金予定</td>
									</nested:equal>

									<nested:equal property="venderDivision" value="SI">
										<td class="fcTitleDetail fb bcTitleDetail">支払月区分</td>
										<td class="fcTitleDetail fb bcTitleDetail">支払予定</td>
										<td class="fcTitleDetail fb bcTitleDetail">仕入割引日数</td>
									</nested:equal>
								</tr>

								<tr>
									<td>
										<%-- 得意先 --%>
										<nested:equal property="venderDivision" value="TS">
											<%-- 前受金対象でない --%>
											<nested:equal property="advanceDivision" value="1">
												<nested:select property="creditDivision3" onblur="setDirtyFlg();" >
													<nested:options property="notCreditDivisionValues" labelProperty="notCreditDivisionLabels" />
												</nested:select>
											</nested:equal>

											<%-- 前受金対象 --%>
											<nested:equal property="advanceDivision" value="2">
												<nested:select property="creditDivision3" onblur="setDirtyFlg();" >
													<nested:options property="creditDivisionValues" labelProperty="creditDivisionLabels" />
												</nested:select>
											</nested:equal>
										</nested:equal>

										<%-- 仕入先 --%>
										<nested:equal property="venderDivision" value="SI">
											<nested:select property="creditDivision3" onblur="setDirtyFlg();" >
												<nested:options property="creditDivisionValues" labelProperty="creditDivisionLabels" />
											</nested:select>
										</nested:equal>
									</td>

									<td>
										<nested:text property="strNoteSight3" maxlength="3" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strNoteSight3" onchange="setDirtyFlg();" />日
									</td>

									<td>
										<nested:text property="strBoundAmount3" maxlength="22" size="10" styleClass="alignRight" style="ime-mode:disabled" styleId="strBoundAmount3" onchange="setDirtyFlg();" />まで
									</td>

									<td>
										<% pageContext.setAttribute("SelectCreditMonthDivision", new com.asahikaseieng.app.comboboxes.SelectCreditMonthDivision(false, false)); %>
										<nested:select property="creditMonthDivision1" onchange="setDirtyFlg();" >
											<nested:options name="SelectCreditMonthDivision" property="values" labelName="SelectCreditMonthDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td>
										<nested:text property="strCreditScheduledDate1" maxlength="2" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strCreditScheduledDate1" onchange="setDirtyFlg();" />日
									</td>

									<td>
										<nested:equal property="venderDivision" value="SI">
											<nested:text property="strPurchaseDiscountDays1" maxlength="3" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strPurchaseDiscountDays1" onchange="setDirtyFlg();" />日
										</nested:equal>
									</td>
								</tr>

								<tr>
									<td>
										<%-- 得意先 --%>
										<nested:equal property="venderDivision" value="TS">
											<%-- 前受金対象でない --%>
											<nested:equal property="advanceDivision" value="1">
												<nested:select property="creditDivision4" onblur="setDirtyFlg();" >
													<nested:options property="notCreditDivisionValues" labelProperty="notCreditDivisionLabels" />
												</nested:select>
											</nested:equal>

											<%-- 前受金対象 --%>
											<nested:equal property="advanceDivision" value="2">
												<nested:select property="creditDivision4" onblur="setDirtyFlg();" >
													<nested:options property="creditDivisionValues" labelProperty="creditDivisionLabels" />
												</nested:select>
											</nested:equal>
										</nested:equal>

										<%-- 仕入先 --%>
										<nested:equal property="venderDivision" value="SI">
											<nested:select property="creditDivision4" onblur="setDirtyFlg();" >
												<nested:options property="creditDivisionValues" labelProperty="creditDivisionLabels" />
											</nested:select>
										</nested:equal>
									</td>

									<td>
										<nested:text property="strNoteSight4" maxlength="3" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strNoteSight4" onchange="setDirtyFlg();" />日
									</td>

									<td>
										<nested:text property="strBoundAmount4" maxlength="22" size="10" styleClass="alignRight" style="ime-mode:disabled" styleId="strBoundAmount4" onchange="setDirtyFlg();" />まで
									</td>

									<td>
										<% pageContext.setAttribute("SelectCreditMonthDivision", new com.asahikaseieng.app.comboboxes.SelectCreditMonthDivision(false, false)); %>
										<nested:select property="creditMonthDivision2" onchange="setDirtyFlg();" >
											<nested:options name="SelectCreditMonthDivision" property="values" labelName="SelectCreditMonthDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td>
										<nested:text property="strCreditScheduledDate2" maxlength="2" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strCreditScheduledDate2" onchange="setDirtyFlg();" />日
									</td>

									<td>
										<nested:equal property="venderDivision" value="SI">
											<nested:text property="strPurchaseDiscountDays2" maxlength="3" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strPurchaseDiscountDays2" onchange="setDirtyFlg();" />日
										</nested:equal>
									</td>
								</tr>

								<tr>
									<td>
										<%-- 得意先 --%>
										<nested:equal property="venderDivision" value="TS">
											<%-- 前受金対象でない --%>
											<nested:equal property="advanceDivision" value="1">
												<nested:select property="creditDivision5" onblur="setDirtyFlg();" >
													<nested:options property="notCreditDivisionValues" labelProperty="notCreditDivisionLabels" />
												</nested:select>
											</nested:equal>

											<%-- 前受金対象 --%>
											<nested:equal property="advanceDivision" value="2">
												<nested:select property="creditDivision5" onblur="setDirtyFlg();" >
													<nested:options property="creditDivisionValues" labelProperty="creditDivisionLabels" />
												</nested:select>
											</nested:equal>
										</nested:equal>

										<%-- 仕入先 --%>
										<nested:equal property="venderDivision" value="SI">
											<nested:select property="creditDivision5" onblur="setDirtyFlg();" >
												<nested:options property="creditDivisionValues" labelProperty="creditDivisionLabels" />
											</nested:select>
										</nested:equal>
									</td>

									<td>
										<nested:text property="strNoteSight5" maxlength="3" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strNoteSight5" onchange="setDirtyFlg();" />日
									</td>

									<td>
									</td>

									<td>
										<% pageContext.setAttribute("SelectCreditMonthDivision", new com.asahikaseieng.app.comboboxes.SelectCreditMonthDivision(false, false)); %>
										<nested:select property="creditMonthDivision3" onchange="setDirtyFlg();" >
											<nested:options name="SelectCreditMonthDivision" property="values" labelName="SelectCreditMonthDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td>
										<nested:text property="strCreditScheduledDate3" maxlength="2" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strCreditScheduledDate3" onchange="setDirtyFlg();" />日
									</td>

									<td>
										<nested:equal property="venderDivision" value="SI">
											<nested:text property="strPurchaseDiscountDays3" maxlength="3" size="5" styleClass="alignRight" style="ime-mode:disabled" styleId="strPurchaseDiscountDays3" onchange="setDirtyFlg();" />日
										</nested:equal>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="170">請求書発行区分</td>
									<td>
										<% pageContext.setAttribute("SelectBillPublish", new com.asahikaseieng.app.comboboxes.SelectBillPublish(false, false)); %>
										<nested:select property="billPublish" onchange="setDirtyFlg();" >
											<nested:options name="SelectBillPublish" property="values" labelName="SelectBillPublish" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="140">伝票発行区分</td>
									<td>
										<% pageContext.setAttribute("SelectSlipPublish", new com.asahikaseieng.app.comboboxes.SelectSlipPublish(false, false)); %>
										<nested:select property="slipPublish" onchange="setDirtyFlg();" >
											<nested:options name="SelectSlipPublish" property="values" labelName="SelectSlipPublish" labelProperty="labels"/>
										</nested:select>
									</td>

									<nested:equal property="venderDivision" value="TS">
										<td class="fcTitleDetail fb bcTitleDetail" width="110">休日入金指定</td>
									</nested:equal>

									<nested:equal property="venderDivision" value="SI">
										<td class="fcTitleDetail fb bcTitleDetail" width="110">休日支払指定</td>
									</nested:equal>

									<td>
										<% pageContext.setAttribute("SelectHolidayFlg", new com.asahikaseieng.app.comboboxes.SelectHolidayFlg(false, false)); %>
										<nested:select property="holidayFlg" onchange="setDirtyFlg();" >
											<nested:options name="SelectHolidayFlg" property="values" labelName="SelectHolidayFlg" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<nested:equal property="venderDivision" value="TS">
										<td class="fcTitleDetail fb bcTitleDetail">売上インボイスパターン</td>
										<td>
											<nested:select property="tsInvoicePtn" onblur="setDirtyFlg();" disabled="false">
												<nested:options property="tsInvoicePtnValues" labelProperty="tsInvoicePtnLabels" />
											</nested:select>
										</td>
									</nested:equal>

									<nested:equal property="venderDivision" value="SI">
										<tr>
											<td class="fcTitleDetail fb bcTitleDetail">仕入インボイスパターン</td>
											<td>
												<nested:select property="siInvoicePtn" onblur="setDirtyFlg();" >
													<nested:options property="siInvoicePtnValues" labelProperty="siInvoicePtnLabels" />
												</nested:select>
											</td>

											<td class="fcTitleDetail fb bcTitleDetail">インボイス登録番号</td>
											<td colspan="6">
												<nested:text property="invoiceNo" maxlength="14" size="25" styleId="invoiceNo" onchange="setDirtyFlg();" />
											</td>
										</tr>
									</nested:equal>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">カレンダー</td>
									<td>
										<nested:text property="calendarCd" maxlength="4" size="4" styleId="calendarCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_cal" class="autocomplete"></div>
									</td>

									<td width="500" colspan="5">
										<div id="lblCalName">
											<nested:write property="calName" />
										</div>
										<nested:hidden property="calName" styleId="calName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">会計用取引先コード</td>
									<td colspan="6">
										<nested:text property="accountCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="accountCd" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">自社銀行口座</td>
									<td colspan="6">
										<nested:select property="bankMasterCd" styleId="bankMasterCd" onblur="setDirtyFlg(); setCursor('1'); form_submit('op', 'setBankName');" >
											<nested:options property="bankMasterCdValues" labelProperty="bankMasterCdLabels" />
										</nested:select>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">銀行</td>
									<td colspan="2">
										<nested:write property="dispBankMasterCd"/>
									</td>

									<td colspan="2">
										<nested:write property="dispBankName"/>
									</td>

									<td colspan="2">
										<nested:write property="dispBranchName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座区分</td>
									<td>
										<nested:write property="accountDivisionName" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座番号</td>
									<td>
										<nested:write property="accountNo" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座名義人</td>
									<td colspan="2">
										<nested:write property="accountStockhold" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130" colspan="6">相手銀行口座</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">銀行</td>
									<td>
										<nested:equal property="venderDivision" value="TS">
											<nested:text property="otherBankMasterCd" maxlength="7" size="7" styleId="otherBankMasterCd" disabled="true" onchange="setDirtyFlg();" />
											<div id="autocomplete_bank" class="autocomplete"></div>
										</nested:equal>

										<nested:equal property="venderDivision" value="SI">
											<nested:text property="otherBankMasterCd" maxlength="7" size="7" styleId="otherBankMasterCd" onchange="setDirtyFlg();" />
											<div id="autocomplete_bank" class="autocomplete"></div>
										</nested:equal>
									</td>

									<td>
										<div id="lblOtherBankName">
											<nested:write property="otherBankName" />
										</div>
										<nested:hidden property="otherBankName" styleId="otherBankName"/>
									</td>

									<td>
										<div id="lblOtherBranchName">
											<nested:write property="otherBranchName" />
										</div>
										<nested:hidden property="otherBranchName" styleId="otherBranchName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座区分</td>
									<td>
										<nested:equal property="venderDivision" value="TS">
											<% pageContext.setAttribute("SelectAccountDivision", new com.asahikaseieng.app.comboboxes.SelectAccountDivision(false, false)); %>
											<nested:select property="otherAccountDivision" onchange="setDirtyFlg();" disabled="true" >
												<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels"/>
											</nested:select>
										</nested:equal>

										<nested:equal property="venderDivision" value="SI">
											<% pageContext.setAttribute("SelectAccountDivision", new com.asahikaseieng.app.comboboxes.SelectAccountDivision(false, false)); %>
											<nested:select property="otherAccountDivision" onchange="setDirtyFlg();">
												<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels"/>
											</nested:select>
										</nested:equal>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座番号</td>
									<td>
										<nested:equal property="venderDivision" value="TS">
											<nested:text property="otherAccountNo" maxlength="7" size="7" style="ime-mode:disabled" styleId="otherAccountNo" disabled="true" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:equal property="venderDivision" value="SI">
											<nested:text property="otherAccountNo" maxlength="7" size="7" style="ime-mode:disabled" styleId="otherAccountNo" onchange="setDirtyFlg();" />
										</nested:equal>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座名義人</td>
									<td>
										<nested:equal property="venderDivision" value="TS">
											<nested:text property="otherAccountStockhold" maxlength="30" size="30" styleId="otherAccountStockhold" disabled="true" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:equal property="venderDivision" value="SI">
											<nested:text property="otherAccountStockhold" maxlength="30" size="30" styleId="otherAccountStockhold" onchange="setDirtyFlg();" />
										</nested:equal>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="170">消費税区分</td>
									<td width="100">
										<% pageContext.setAttribute("SelectTaxDivision", new com.asahikaseieng.app.comboboxes.SelectTaxDivision(false, false)); %>
										<nested:select property="taxDivision" disabled="true" onchange="setDirtyFlg();" >
											<nested:options name="SelectTaxDivision" property="values" labelName="SelectTaxDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" colspan="2" width="170">消費税算出区分</td>
									<td>
										<% pageContext.setAttribute("SelectCalcDivision", new com.asahikaseieng.app.comboboxes.SelectCalcDivision(false, false)); %>
										<nested:select property="calcDivision" onchange="setDirtyFlg();" disabled="true" >
											<nested:options name="SelectCalcDivision" property="values" labelName="SelectCalcDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">消費税端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectTaxRoundup", new com.asahikaseieng.app.comboboxes.SelectTaxRoundup(false, false)); %>
										<nested:select property="taxRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectTaxRoundup" property="values" labelName="SelectTaxRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" colspan="2" width="170">消費税端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectTaxRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectTaxRoundupUnit(false, false)); %>
										<nested:select property="taxRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectTaxRoundupUnit" property="values" labelName="SelectTaxRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectRoundup", new com.asahikaseieng.app.comboboxes.SelectRoundup(false, false)); %>
										<nested:select property="roundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectRoundup" property="values" labelName="SelectRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectRoundupUnit(false, false)); %>
										<nested:select property="roundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectRoundupUnit" property="values" labelName="SelectRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<nested:equal property="venderDivision" value="TS">
										<td class="fcTitleDetail fb bcTitleDetail">売上金額端数区分</td>
									</nested:equal>

									<nested:equal property="venderDivision" value="SI">
										<td class="fcTitleDetail fb bcTitleDetail">仕入金額端数区分</td>
									</nested:equal>

									<td>
										<% pageContext.setAttribute("SelectSalesPurchaseRoundup", new com.asahikaseieng.app.comboboxes.SelectSalesPurchaseRoundup(false, false)); %>
										<nested:select property="salesPurchaseRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectSalesPurchaseRoundup" property="values" labelName="SelectSalesPurchaseRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<nested:equal property="venderDivision" value="TS">
										<td class="fcTitleDetail fb bcTitleDetail" colspan="2">売上金額端数単位</td>
									</nested:equal>

									<nested:equal property="venderDivision" value="SI">
										<td class="fcTitleDetail fb bcTitleDetail" colspan="2">仕入金額端数単位</td>
									</nested:equal>

									<td>
										<% pageContext.setAttribute("SelectSalesPurchaseRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectSalesPurchaseRoundupUnit(false, false)); %>
										<nested:select property="salesPurchaseRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectSalesPurchaseRoundupUnit" property="values" labelName="SelectSalesPurchaseRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">単価端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectUnitpriceRoundup", new com.asahikaseieng.app.comboboxes.SelectUnitpriceRoundup(false, false)); %>
										<nested:select property="unitpriceRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectUnitpriceRoundup" property="values" labelName="SelectUnitpriceRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">単価端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectUnitpriceRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectUnitpriceRoundupUnit(false, false)); %>
										<nested:select property="unitpriceRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectUnitpriceRoundupUnit" property="values" labelName="SelectUnitpriceRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
<%-- 2014/01/16 消費税対応で削除
									<td class="fcTitleDetail fb bcTitleDetail">消費税率</td>
									<td>
										<nested:text property="strTaxRatio" maxlength="6" size="10" style="ime-mode:disabled" styleClass="alignRight" styleId="strTaxRatio" onchange="setDirtyFlg();" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">振込手数料負担</td>
--%>
									<td class="fcTitleDetail fb bcTitleDetail">振込手数料負担</td>
									<td>
										<% pageContext.setAttribute("SelectTransferCommissionLoad", new com.asahikaseieng.app.comboboxes.SelectTransferCommissionLoad(false, false)); %>
										<nested:select property="transferCommissionLoad" onchange="setDirtyFlg();" >
											<nested:options name="SelectTransferCommissionLoad" property="values" labelName="SelectTransferCommissionLoad" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">客先担当者1</td>
									<td>
										<nested:text property="customerTantoName1" maxlength="20" size="25" styleId="customerTantoName1" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" colspan="2">客先担当者2</td>
									<td>
										<nested:text property="customerTantoName2" maxlength="20" size="25" styleId="customerTantoName2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入伝票送付先</td>
									<td colspan="4">
										<nested:textarea property="customerImpression1" rows="3" cols="65" styleId="customerImpression1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">客先所感</td>
									<td colspan="4">
										<nested:textarea property="customerImpression2" rows="3" cols="65" styleId="customerImpression2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">備考</td>
									<td colspan="4">
										<nested:textarea property="remarks" rows="3" cols="65" styleId="remarks" onchange="setDirtyFlg();" />
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="600" border="0">
								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>

								<tr>
									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>

										<td width="50">
											<br>
										</td>
									</nested:equal>

									<nested:notEqual property="newFlg" value="true">
										<nested:equal property="deleteAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
														&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>

										<nested:define id="oid1" property="venderDivision" />
										<nested:define id="oid2" property="venderCd" />

										<td class="alignLeft">
											<div id="cssButton">
												<a href="<%= request.getContextPath() + "/DeliveryDetail.do?op=copy&venderDivision=" + pageContext.findAttribute("oid1").toString() + "&venderCd=" + pageContext.findAttribute("oid2").toString() + "&copyFlg=1" %>" onclick="if(!putDirtyConfirm()){return false;}" class="cssButton">
													&nbsp;&nbsp;納入先作成&nbsp;&nbsp;
												</a>
											</div>
										</td>

										<td width="50">
											<br>
										</td>
									</nested:notEqual>

									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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

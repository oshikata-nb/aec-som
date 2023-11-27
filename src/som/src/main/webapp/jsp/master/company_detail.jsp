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
        defineAsRequiredField("homeName1");
        defineAsRequiredField("strSettlement");
        defineAsRequiredField("strClosingDay");
        defineAsRequiredField("bankMasterCd1");
        defineAsRequiredField("accountAbbreviation1");
        defineAsRequiredField("accountNo1");
        defineAsRequiredField("bankMasterCd");
        defineAsRequiredField("accountNo");
        defineAsRequiredField("transferClientCd");
        defineAsRequiredField("transferClientName");
        defineAsRequiredField("strPrime");
        defineAsRequiredField("strPasswordValidTerm");
        defineAsRequiredField("strPasswordKetaLowerLimit");
        defineAsRequiredField("strPasswordKetaUpperLimit");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strSettlement");
		defineAsNumberField("strClosingDay");
		defineAsNumberField("strTaxRatio");
		defineAsNumberField("strStockDiscountRate");
		defineAsNumberField("strPrime");
		defineAsNumberField("strPasswordValidTerm");
		defineAsNumberField("strPasswordKetaLowerLimit");
		defineAsNumberField("strPasswordKetaUpperLimit");

		defineAsDateField("strNewTaxApllyDate");

		<%-- 入金銀行1のautocomplete --%>
		new Ajax.Autocompleter(
			"bankMasterCd1",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/BankForAutoComplete.do?op=searchBankMaster",
			{
				paramName : "code",
				afterUpdateElement : setBankMaster1Label
			}
		);

		<%-- 入金銀行2のautocomplete --%>
		new Ajax.Autocompleter(
			"bankMasterCd2",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/BankForAutoComplete.do?op=searchBankMaster",
			{
				paramName : "code",
				afterUpdateElement : setBankMaster2Label
			}
		);

		<%-- 入金銀行3のautocomplete --%>
		new Ajax.Autocompleter(
			"bankMasterCd3",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/BankForAutoComplete.do?op=searchBankMaster",
			{
				paramName : "code",
				afterUpdateElement : setBankMaster3Label
			}
		);

		<%-- 入金銀行4のautocomplete --%>
		new Ajax.Autocompleter(
			"bankMasterCd4",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/BankForAutoComplete.do?op=searchBankMaster",
			{
				paramName : "code",
				afterUpdateElement : setBankMaster4Label
			}
		);

		<%-- 支払銀行のautocomplete --%>
		new Ajax.Autocompleter(
			"bankMasterCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/BankForAutoComplete.do?op=searchBankMaster",
			{
				paramName : "code",
				afterUpdateElement : setBankMasterLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('bankMasterCd1', 'keypress', clearLabel.bindAsEventListener($('bankMasterName1')), false);
		Event.observe('bankMasterCd2', 'keypress', clearLabel.bindAsEventListener($('bankMasterName2')), false);
		Event.observe('bankMasterCd3', 'keypress', clearLabel.bindAsEventListener($('bankMasterName3')), false);
		Event.observe('bankMasterCd4', 'keypress', clearLabel.bindAsEventListener($('bankMasterName4')), false);
		Event.observe('bankMasterCd', 'keypress', clearLabel.bindAsEventListener($('bankMasterName')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- ajax --%>
	function setBankMaster1Label(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("bankMasterName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setBankMaster2Label(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("bankMasterName2").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setBankMaster3Label(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("bankMasterName3").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setBankMaster4Label(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("bankMasterName4").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setBankMasterLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("bankMasterName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
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

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/CompanyDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="newFlg" styleId="newFlg"/>

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
									<td class="fcTitle fb">自社マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="180">自社名称</td>
									<td colspan="3">
										<nested:text property="homeName1" maxlength="30" size="55" styleId="homeName1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">郵便番号</td>
									<td colspan="3">
										<nested:text property="zipcodeNo" maxlength="9" size="9" style="ime-mode:disabled" styleId="zipcodeNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所1</td>
									<td colspan="3">
										<nested:text property="address1" maxlength="30" size="55" styleId="address1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所2</td>
									<td colspan="3">
										<nested:text property="address2" maxlength="30" size="55" styleId="address2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">住所3</td>
									<td colspan="3">
										<nested:text property="address3" maxlength="30" size="55" styleId="address3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">電話番号</td>
									<td>
										<nested:text property="telNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="telNo" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="130">FAX番号</td>
									<td>
										<nested:text property="faxNo" maxlength="13" size="13" style="ime-mode:disabled" styleId="faxNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">代表役職名</td>
									<td>
										<nested:text property="representRole" maxlength="20" size="35" styleId="representRole" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">代表者名</td>
									<td>
										<nested:text property="representPerson" maxlength="20" size="35" styleId="representPerson" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">インボイス登録番号</td>
									<td>
										<nested:text property="invoiceNo" maxlength="14" size="35" styleId="invoiceNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">決算月</td>
									<td>
										<nested:text property="strSettlement" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strSettlement" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">締日</td>
									<td>
										<nested:text property="strClosingDay" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strClosingDay" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">支払更新区分</td>
									<td colspan="3">
										<% pageContext.setAttribute("SelectPaymentUpdate", new com.asahikaseieng.app.comboboxes.SelectPaymentUpdate(false, false)); %>
										<nested:select property="paymentUpdate" onchange="setDirtyFlg();" >
											<nested:options name="SelectPaymentUpdate" property="values" labelName="SelectPaymentUpdate" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">消費税課税区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyTaxDivision", new com.asahikaseieng.app.comboboxes.SelectCompanyTaxDivision(false, false)); %>
										<nested:select property="taxDivision" disabled="true" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyTaxDivision" property="values" labelName="SelectCompanyTaxDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">消費税算出区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyCalcDivision", new com.asahikaseieng.app.comboboxes.SelectCompanyCalcDivision(false, false)); %>
										<nested:select property="calcDivision" disabled="true" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyCalcDivision" property="values" labelName="SelectCompanyCalcDivision" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">消費税端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="taxRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">消費税端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="taxRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

<%--							20190820 消費税マスタで管理するため不要
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">消費税率(%)</td>
									<td>
										<nested:text property="strTaxRatio" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strTaxRatio" onchange="setDirtyFlg();" />
									</td>
--%>
									<td class="fcTitleDetail fb bcTitleDetail">在庫割引率(%)</td>
									<td>
										<nested:text property="strStockDiscountRate" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strStockDiscountRate" onchange="setDirtyFlg();" />
									</td>
								</tr>
<%--							20190820 消費税マスタで管理するため不要
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">新消費税率(%)</td>
									<td>
										<nested:text property="strNewTaxRatio" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strNewTaxRatio" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">新消費税適用開始日</td>
									<td>
										<nested:text property="strNewTaxApllyDate" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strNewTaxApllyDate" onchange="setDirtyFlg();" />
									</td>
								</tr>
--%>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="roundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="roundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">売上金額<BR>端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="salesRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">売上金額<BR>端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="salesRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入金額<BR>端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="purchaseRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">仕入金額<BR>端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="purchaseRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">単価端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="unitpriceRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">単価端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="unitpriceRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">配合量端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="blendQtyRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">配合量端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="blendQtyRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">配合率端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="mixRateRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">配合率端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="mixRateRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">配合調整<BR>端数区分</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundup", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundup(false, false)); %>
										<nested:select property="adjRoundup" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundup" property="values" labelName="SelectCompanyRoundup" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">配合調整<BR>端数単位</td>
									<td>
										<% pageContext.setAttribute("SelectCompanyRoundupUnit", new com.asahikaseieng.app.comboboxes.SelectCompanyRoundupUnit(false, false)); %>
										<nested:select property="adjRoundupUnit" onchange="setDirtyFlg();" >
											<nested:options name="SelectCompanyRoundupUnit" property="values" labelName="SelectCompanyRoundupUnit" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">自社銀行口座</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">入金銀行マスタコード1</td>
									<td>
										<nested:text property="bankMasterCd1" maxlength="7" size="7" styleId="bankMasterCd1" onchange="setDirtyFlg();" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>

									<td colspan="4">
										<div id="lblBankMasterName1">
											<nested:write property="bankMasterName1" />
										</div>
										<nested:hidden property="bankMasterName1" styleId="bankMasterName1"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座略称1</td>
									<td colspan="5">
										<nested:text property="accountAbbreviation1" maxlength="20" size="35" styleId="accountAbbreviation1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座区分1</td>
									<td>
										<% pageContext.setAttribute("SelectAccountDivision", new com.asahikaseieng.app.comboboxes.SelectAccountDivision(false, false)); %>
										<nested:select property="accountDivision1" onchange="setDirtyFlg();" >
											<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座番号1</td>
									<td>
										<nested:text property="accountNo1" maxlength="10" size="10" style="ime-mode:disabled" styleId="accountNo1" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座名義人1</td>
									<td>
										<nested:text property="accountStockhold1" maxlength="30" size="30" styleId="accountStockhold1" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">入金銀行マスタコード2</td>
									<td>
										<nested:text property="bankMasterCd2" maxlength="7" size="7" styleId="bankMasterCd2" onchange="setDirtyFlg();" />
									</td>

									<td colspan="4">
										<div id="lblBankMasterName2">
											<nested:write property="bankMasterName2" />
										</div>
										<nested:hidden property="bankMasterName2" styleId="bankMasterName2"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座略称2</td>
									<td colspan="5">
										<nested:text property="accountAbbreviation2" maxlength="20" size="35" styleId="accountAbbreviation2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座区分2</td>
									<td>
										<% pageContext.setAttribute("SelectAccountDivision", new com.asahikaseieng.app.comboboxes.SelectAccountDivision(false, false)); %>
										<nested:select property="accountDivision2" onchange="setDirtyFlg();" >
											<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座番号2</td>
									<td>
										<nested:text property="accountNo2" maxlength="10" size="10" style="ime-mode:disabled" styleId="accountNo2" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座名義人2</td>
									<td>
										<nested:text property="accountStockhold2" maxlength="30" size="30" styleId="accountStockhold2" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">入金銀行マスタコード3</td>
									<td>
										<nested:text property="bankMasterCd3" maxlength="7" size="7" styleId="bankMasterCd3" onchange="setDirtyFlg();" />
									</td>

									<td colspan="4">
										<div id="lblBankMasterName3">
											<nested:write property="bankMasterName3" />
										</div>
										<nested:hidden property="bankMasterName3" styleId="bankMasterName3"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座略称3</td>
									<td colspan="5">
										<nested:text property="accountAbbreviation3" maxlength="20" size="35" styleId="accountAbbreviation3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座区分3</td>
									<td>
										<% pageContext.setAttribute("SelectAccountDivision", new com.asahikaseieng.app.comboboxes.SelectAccountDivision(false, false)); %>
										<nested:select property="accountDivision3" onchange="setDirtyFlg();" >
											<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座番号3</td>
									<td>
										<nested:text property="accountNo3" maxlength="10" size="10" style="ime-mode:disabled" styleId="accountNo3" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座名義人3</td>
									<td>
										<nested:text property="accountStockhold3" maxlength="30" size="30" styleId="accountStockhold3" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">入金銀行マスタコード4</td>
									<td>
										<nested:text property="bankMasterCd4" maxlength="7" size="7" styleId="bankMasterCd4" onchange="setDirtyFlg();" />
									</td>

									<td colspan="4">
										<div id="lblBankMasterName4">
											<nested:write property="bankMasterName4" />
										</div>
										<nested:hidden property="bankMasterName4" styleId="bankMasterName4"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座略称4</td>
									<td colspan="5">
										<nested:text property="accountAbbreviation4" maxlength="20" size="35" styleId="accountAbbreviation4" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座区分4</td>
									<td>
										<% pageContext.setAttribute("SelectAccountDivision", new com.asahikaseieng.app.comboboxes.SelectAccountDivision(false, false)); %>
										<nested:select property="accountDivision4" onchange="setDirtyFlg();" >
											<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座番号4</td>
									<td>
										<nested:text property="accountNo4" maxlength="10" size="10" style="ime-mode:disabled" styleId="accountNo4" onchange="setDirtyFlg();" />
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">口座名義人4</td>
									<td>
										<nested:text property="accountStockhold4" maxlength="30" size="30" styleId="accountStockhold4" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">支払銀行マスタコード</td>
									<td>
										<nested:text property="bankMasterCd" maxlength="7" size="7" styleId="bankMasterCd" onchange="setDirtyFlg();" />
									</td>

									<td colspan="2" width="400">
										<div id="lblBankMasterName">
											<nested:write property="bankMasterName" />
										</div>
										<nested:hidden property="bankMasterName" styleId="bankMasterName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">口座区分</td>
									<td>
										<% pageContext.setAttribute("SelectAccountDivision", new com.asahikaseieng.app.comboboxes.SelectAccountDivision(false, false)); %>
										<nested:select property="accountDivision" onchange="setDirtyFlg();" >
											<nested:options name="SelectAccountDivision" property="values" labelName="SelectAccountDivision" labelProperty="labels"/>
										</nested:select>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="130">口座番号</td>
									<td width="350">
										<nested:text property="accountNo" maxlength="10" size="10" style="ime-mode:disabled" styleId="accountNo" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">振込依頼人コード</td>
									<td colspan="3">
										<nested:text property="transferClientCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="transferClientCd" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">振込依頼人名</td>
									<td colspan="3">
										<nested:text property="transferClientName" maxlength="30" size="30" styleId="transferClientName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">短プラ+金利</td>
									<td colspan="3">
										<nested:text property="strPrime" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPrime" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="160">パスワード有効期限</td>
									<td>
										<nested:text property="strPasswordValidTerm" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPasswordValidTerm" onchange="setDirtyFlg();" />
										日(0で無期限)
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">パスワード有効桁数</td>
									<td>
										<nested:text property="strPasswordKetaLowerLimit" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPasswordKetaLowerLimit" onchange="setDirtyFlg();" />
										～
										<nested:text property="strPasswordKetaUpperLimit" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPasswordKetaUpperLimit" onchange="setDirtyFlg();" />
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="450" border="0">
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

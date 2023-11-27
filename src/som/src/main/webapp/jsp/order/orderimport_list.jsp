<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<%-- ▼業務固有css  ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
			location.href=url;
			g_lock = true;
		}
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		<logic:equal name="orderImportListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhImportDateFrom");
		defineAsDateField("srhImportDateTo");
		defineAsDateField("srhOrderDateFrom");
		defineAsDateField("srhOrderDateTo");
		defineAsDateField("srhScheduledShippingDateFrom");
		defineAsDateField("srhScheduledShippingDateTo");
		defineAsDateField("srhSuggestedDeliverlimitFrom");
		defineAsDateField("srhSuggestedDeliverlimitTo");
		defineAsDateField("srhDeliveryExpectedDateFrom");
		defineAsDateField("srhDeliveryExpectedDateTo");
		//リスト


		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		<%-- 納入先のautocomplete --%>
		new Ajax.Autocompleter(
			"srhDeliveryCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
			{
				paramName : "code",
				afterUpdateElement : setDeliveryLabel
			}
		);

		<%-- 得意先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhVenderCd",
		  "autocomplete_choices",
		  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
		  {
		  	paramName : "code",
		  	parameters : 'venderDivision=TS',
		    afterUpdateElement : setVenderLabel
		  }
		);

		<%-- 部署のautocomplete --%>
		new Ajax.Autocompleter(
			"srhOrganizationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);

		<%-- 営業担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"srhSalesTantoCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setSalesLoginLabel
			}
		);

		<%-- 入力担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"srhInputTantoCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setInputLoginLabel
			}
		);

		<%-- 品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setInputItemLabel
			}
		);

		<%-- 他社コードのautocomplete --%>
		new Ajax.Autocompleter(
			"srhOtherCompanyCd1",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueueOtherCompany",
			{
				paramName : "code",
				afterUpdateElement : setOtherCompanyItemLabel
			}
		);

		<%-- コードキープレスイベント時にラベルをクリアするリスナーを追加 --%>
		Event.observe('srhDeliveryCd', 'keypress', clearLabel.bindAsEventListener($('srhDeliveryName')), false);
		Event.observe('srhVenderCd', 'keypress', clearLabel.bindAsEventListener($('srhVenderName')), false);
		Event.observe('srhOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('srhOrganizationName')), false);
		Event.observe('srhSalesTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhSalesTantoName')), false);
		Event.observe('srhInputTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhInputTantoName')), false);
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd',  'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);
		}

		refreshLabel();

		if($("sendCompFlg") != null && $("sendCompFlg").value == 1){
			alert("送信が完了しました。");
			$("sendCompFlg").value  = 0;

		}

	}, false);

	<%-- ajax --%>
	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhDeliveryName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- 得意先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setSalesLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhSalesTantoName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setInputLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhInputTantoName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setInputItemLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		refreshLabel();
	}

	function setOtherCompanyItemLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhItemCd").value = getHiddenValue(li,"code");
		refreshLabel();
	}

	function setImportStatus() {
		$("importStatus").value = $("selectImportStatus").value;
	}

	function setErrorStatus() {
		$("errorStatus").value = $("selectErrorStatus").value;
	}

	function setSlipPublishComp() {
		$("slipPublishComp").value = $("selectSlipPublishComp").value;
	}

	function setTmpUnitpriceFlg() {
		$("tmpUnitpriceFlg").value = $("selectTmpUnitpriceFlg").value;
	}

	function setInputDivision() {
		$("inputDivision").value = $("selectInputDivision").value;
	}

	function setDelDateSendStatus() {
		$("delDateSendStatus").value = $("selectDelDateSendStatus").value;
	}

	function setInputCheckStatus() {
		$("inputCheckStatus").value = $("selectInputCheckStatus").value;
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- ポップアップ後の設定処理 --%>
	// ファイル取込ボタン押下時の警告避け
	function setOrderFileImportValues(orderFileImportFlg) {
		if (orderFileImportFlg) {

		}
	}

	<%-- 増付数のフォーマット --%>
	function formatMatss(obj,i){
		var value = obj.value;
		if (value != null && value != "") {
			<%-- 数値フォーマット --%>
			var res = digitStringFormat($F("smallnumLengthNum" + i), $F("roundDivisionNum" + i), value);
			if (res != "") {
				$("strMatss" + i).value = res;
			}
		}
	}


	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		return true;
	}

	<%-- 確認メッセージ --%>
	function outputConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function replyConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "ファイル返信してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function delDateReplyConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "納期連絡表返信してもよろしいですか？";

		return confirm(alertMsg[0]);
	}


	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
	<%-- チェックボックス 全設定 --%>
	function allCheck(flg) {
        for (var i=0; i < $("searchListLength").value; i++) {
        	if($("rank"+i).value == 1){
            	$("orderImportCheckBox" + i).checked = flg;
        	}
        }
	}
	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
		var count = 0;
		for (var i=0; i < $("searchListLength").value; i++) {
        	if($("rank"+i).value == 1){
				if ($("orderImportCheckBox" + i).checked) {
					count++;
				}
        	}
		}
		if (count == 0) {
			alert("対象が選択されていません。");
			return false;
		}
		return true;
	}


	<%-- 確認メッセージ --%>
	function putFixConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "確定してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ:行削除 --%>
	function deleteConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "引当取消してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function orderReportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "受注一覧表出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}



</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/OrderImportList" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" value="false"/>
	<nested:hidden property="searchListCount" styleId="searchListCount" />
	<nested:hidden property="sendCompFlg" styleId="sendCompFlg" />

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
							<td class="fcTitle fb" width="250" height="20">受注取込</td>
							<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><%-- メッセージ表示 --%> <%@ include
										file="/jsp/common/disp_info_message.jsf"%>
									<%-- メッセージ表示 ここまで --%></td>
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
					<td><%-- メッセージ表示 --%> <%@ include
						file="/jsp/common/disp_error_message.jsf"%>
					<%-- メッセージ表示 ここまで --%></td>
				</tr>
				<tr>
					<td><!-- 検索部 -->
					<table cellspacing="0" cellpadding="0" border="0" width="1400">
						<tr>
							<td>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">先付受注番号</td>
									<td>
										<nested:text property="srhFrstOrderNoFrom" maxlength="12" size="9" styleId="srhFrstOrderNoFrom"  style="ime-mode:disabled" />
									</td>
									<td align="center">～</td>
									<td>
										<nested:text property="srhFrstOrderNoTo" maxlength="12" size="9" styleId="srhFrstOrderNoTo"  style="ime-mode:disabled" />
									</td>
									<td colspan="1"/>
									<td class="bcTitleSearch fb fcTitleSearch" width="120">得意先グループ</td>
									<td >
										<nested:select property="srhVenderGroupCd" style="margin: 0;padding: 0" styleId="srhVenderGroupCd" >
											<nested:optionsCollection property="venderGroupCombo" label="labales" value="values" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">受注区分</td>
									<td >
										<%
											pageContext.setAttribute("selectOrderDivisionCode",
											new com.asahikaseieng.app.comboboxes.SelectOrderDivisionCode(true, false));
										%>
										<nested:select property="srhOrderDivision" styleId="srhOrderDivision" >
											<nested:options name="selectOrderDivisionCode" property="values" labelName="selectOrderDivisionCode" labelProperty="labels"/>
										</nested:select>
									</td>
									<td class="bcTitleYellowCard fb fcTitleSearch" width="80">取込日</td>
									<td>
										<nested:text property="srhImportDateFrom" maxlength="10" size="6" styleId="srhImportDateFrom"  style="ime-mode:disabled" />
									</td>
									<td width="10" align="center">～</td>
									<td >
										<nested:text property="srhImportDateTo" maxlength="10" size="6" styleId="srhImportDateTo"  style="ime-mode:disabled" />
									</td>
								</tr>

								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">受注番号</td>
									<td>
										<nested:text property="srhOrderNoFrom" maxlength="20" size="8" styleId="srhOrderNoFrom"  style="ime-mode:disabled" />
									</td>
									<td align="center">～</td>
									<td>
										<nested:text property="srhOrderNoTo" maxlength="20" size="8" styleId="srhOrderNoTo"  style="ime-mode:disabled" />
									</td>
									<td colspan=3/>

									<td class="bcTitleSearch fb fcTitleSearch">受注ステータス</td>
									<td>
										<nested:select property="srhOrderStatus" >
											<nested:options property="orderStatusValues" labelProperty="orderStatusLabels"/>
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">受注日</td>
									<td>
										<nested:text property="srhOrderDateFrom" maxlength="10" size="6" styleId="srhOrderDateFrom"  style="ime-mode:disabled" />
									</td>
									<td width="10" align="center">～</td>
									<td>
										<nested:text property="srhOrderDateTo" maxlength="10" size="6" styleId="srhOrderDateTo"  style="ime-mode:disabled" />
									</td>
								</tr>

								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">納入先</td>
									<td>
										<nested:text property="srhDeliveryCd" maxlength="15" size="8" styleId="srhDeliveryCd" />
										<div id="autocomplete_choices" class="autocomplete"></div>
									</td>
									<td width="230" colspan="3">
										<div id="lblSrhDeliveryName">
											<nested:write property="srhDeliveryName" />
										</div>
										<nested:hidden property="srhDeliveryName" styleId="srhDeliveryName" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">住所</td>
									<td colspan="3">
										<nested:text property="srhAddress" maxlength="60" size="45" styleId="srhAddress" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">電話番号</td>
									<td colspan="3">
										<nested:text property="srhDeliveryTelNo" maxlength="20" size="22" styleId="srhDeliveryTelNo"  style="ime-mode:disabled" />
									</td>
								</tr>

								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">得意先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="15" size="8" styleId="srhVenderCd" />
									</td>
									<td width="230" colspan="3">
										<div id="lblSrhVenderName">
											<nested:write property="srhVenderName" />
										</div>
										<nested:hidden property="srhVenderName" styleId="srhVenderName" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">営業担当者</td>
									<td>
										<nested:text property="srhSalesTantoCd" maxlength="10" size="6" styleId="srhSalesTantoCd" />
									</td>
									<td width="230" colspan="2">
										<div id="lblSrhSalesTantoName">
											<nested:write property="srhSalesTantoName" />
										</div>
										<nested:hidden property="srhSalesTantoName" styleId="srhSalesTantoName"/>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">出荷予定日</td>
									<td>
										<nested:text property="srhScheduledShippingDateFrom" maxlength="10" size="6" styleId="srhScheduledShippingDateFrom"  style="ime-mode:disabled" />
									</td>
									<td align="center">～</td>
									<td>
									<nested:text property="srhScheduledShippingDateTo" maxlength="10" size="6" styleId="srhScheduledShippingDateTo"  style="ime-mode:disabled" />
									</td>
								</tr>

								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">担当部署</td>
									<td>
										<nested:text property="srhOrganizationCd" maxlength="10" size="8" styleId="srhOrganizationCd" />
									</td>
									<td colspan="3">
										<div id="lblSrhOrganizationName">
											<nested:write property="srhOrganizationName" />
										</div>
										<nested:hidden property="srhOrganizationName" styleId="srhOrganizationName"/>
									</td>

									<td class="bcTitleSearch fb fcTitleSearch">入力担当者</td>
									<td>
										<nested:text property="srhInputTantoCd" maxlength="10" size="6" styleId="srhInputTantoCd" />
									</td>
									<td colspan="2">
										<div id="lblSrhInputTantoName">
											<nested:write property="srhInputTantoName" />
										</div>
										<nested:hidden property="srhInputTantoName" styleId="srhInputTantoName"/>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">納入予定日</td>
									<td>
										<nested:text property="srhDeliveryExpectedDateFrom" maxlength="10" size="6" styleId="srhDeliveryExpectedDateFrom"  style="ime-mode:disabled" />
									</td>
									<td align="center">～</td>
									<td>
									<nested:text property="srhDeliveryExpectedDateTo" maxlength="10" size="6" styleId="srhDeliveryExpectedDateTo"  style="ime-mode:disabled" />
									</td>
								</tr>

								<tr>
									<td class="bcTitleYellowCard fb fcTitleSearch">品目</td>
									<td>
										<nested:text property="srhItemCd" maxlength="10" size="8" styleId="srhItemCd" />
									</td>
									<td width="230" colspan="3">
										<div id="lblSrhItemName">
											<nested:write property="srhItemName" />
										</div>
										<nested:hidden property="srhItemName" styleId="srhItemName"/>
									</td>

									<td class="bcTitleYellowCard fb fcTitleSearch">他社コード1</td>
									<td >
										<nested:text property="srhOtherCompanyCd1" maxlength="10" size="6" styleId="srhOtherCompanyCd1" />
									</td>
									<td class="bcTitleYellowCard fb fcTitleSearch">仮単価</td>
									<td>
										<%
											pageContext.setAttribute("selectTmpUnitpriceFlg",
											new com.asahikaseieng.app.comboboxes.SelectTmpUnitpriceFlg(false, false));
										%>
										<nested:select property="srhTmpUnitpriceFlg" styleId="setTmpUnitpriceFlg();" >
											<nested:options name="selectTmpUnitpriceFlg" property="values" labelName="selectTmpUnitpriceFlg" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">希望納期</td>
									<td>
										<nested:text property="srhSuggestedDeliverlimitFrom" maxlength="10" size="6" styleId="srhSuggestedDeliverlimitFrom" style="ime-mode:disabled" />
									</td>
									<td align="center">～</td>
									<td>
									<nested:text property="srhSuggestedDeliverlimitTo" maxlength="10" size="6" styleId="srhSuggestedDeliverlimitTo"  style="ime-mode:disabled" />
									</td>
								</tr>

								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">運送会社コード</td>
									<td colspan="3">
										<nested:select property="srhCarryCd" style="margin: 0;padding: 0" styleId="srhCarryCd" >
											<nested:optionsCollection property="carryCombo" label="labales" value="values" />
										</nested:select>
									</td>
									<td>
									<td class="bcTitleYellowCard fb fcTitleSearch" width="120">エラー状態</td>
									<td>
										<%
											pageContext.setAttribute("selectErrorStatus",
											new com.asahikaseieng.app.comboboxes.SelectErrorStatus(true, false));
										%>
										<nested:select property="srhErrorStatus" styleId="setErrorStatus();" >
											<nested:options name="selectErrorStatus" property="values" labelName="selectErrorStatus" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleYellowCard fb fcTitleSearch">データ作成区分</td>
									<td>
										<%
											pageContext.setAttribute("selectInputDivision",
											new com.asahikaseieng.app.comboboxes.SelectInputDivision(true, false));
										%>
										<nested:select property="srhInputDivision" styleId="setInputDivision();" >
											<nested:options name="selectInputDivision" property="values" labelName="selectInputDivision" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleYellowCard fb fcTitleSearch">出荷BC</td>
									<td colspan="3">
										<nested:text property="srhCarryBc" maxlength="20" size="22" styleId="srhCarryBc" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleYellowCard fb fcTitleSearch">取込ファイル番号</td>
									<td>
										<nested:text property="srhTempNoFrom" maxlength="20" size="8" styleId="srhTempNoFrom"  style="ime-mode:disabled" />
									</td>
									<td align="center">～</td>
									<td colspan="1">
										<nested:text property="srhTempNoTo" maxlength="20" size="8" styleId="srhTempNoTo"  style="ime-mode:disabled" />
									</td>
									<td colspan="1">
									<td class="bcTitleYellowCard fb fcTitleSearch">ファイル返信</td>
									<td>
										<%
											pageContext.setAttribute("selectSlipPublishComp",
											new com.asahikaseieng.app.comboboxes.SelectSlipPublishComp(true, false));
										%>
										<nested:select property="srhSlipPublishComp" styleId="setSlipPublishComp();" >
											<nested:options name="selectSlipPublishComp" property="values" labelName="selectSlipPublishComp" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" colspan="1">納期連絡</td>
									<td>
										<%
											pageContext.setAttribute("selectOrderDelDateSend",
											new com.asahikaseieng.app.comboboxes.SelectOrderDelDateSend(true, false));
										%>
										<nested:select property="srhDelDateSend" styleId="setDelDateSendStatus();" >
											<nested:options name="selectOrderDelDateSend" property="values" labelName="selectOrderDelDateSend" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleYellowCard fb fcTitleSearch">入力チェック</td>
									<td>
										<%
											pageContext.setAttribute("selectOrderInputCheck",
											new com.asahikaseieng.app.comboboxes.SelectOrderInputCheck(true, false));
										%>
										<nested:select property="srhOrderInputCheck" styleId="setInputCheckStatus();" >
											<nested:options name="selectOrderInputCheck" property="values" labelName="selectOrderInputCheck" labelProperty="labels"  />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="120">客先注文番号</td>
									<td colspan="3">
										<nested:text property="srhCtmOrderNo" maxlength="30" size="22" styleId="srhCtmOrderNo"  style="ime-mode:disabled" />
									</td>
									<td colspan="1">

									<td class="bcTitleYellowCard fb fcTitleSearch">削除・キャンセル</td>
									<td>
										<%
											pageContext.setAttribute("selectOrderDelStatus",
											new com.asahikaseieng.app.comboboxes.SelectOrderDelStatus(true, false));
										%>
										<nested:select property="srhDeleteCancel" styleId="setDeleteCancel();" >
											<nested:options name="selectOrderDelStatus" property="values" labelName="selectOrderDelStatus" labelProperty="labels" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td colspan="8">
									<nested:equal property="updateAuthority" value="true">
										<td colspan="2" class="alignLeft">
											<div id="cssButton">
												<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
												<a href="#" onclick="open_modal_popup_edge(800, 450, 'OrderFileImport', ''); return false;" class="cssButton">
													&nbsp;ファイル取込&nbsp;
												</a>
											</div>
										</td>
									</nested:equal>
									<td class="alignRight">
										<div id="cssButton"><a href="#"
											onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'search'); return false;}"
											class="cssButton"> &nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
										</a></div>
									</td>
									<nested:equal property="updateAuthority" value="true">
										<td colspan="2" class="alignRight">
											<div id="cssButton">
												<a href="#"	onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'newPage'); return false;}" class="cssButton">
											&nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
											</a>
											</div>
										</td>
									</nested:equal>
								</tr>
							</table>
							</td>
						</tr>

						<tr height="5">
							<td></td>
						</tr>
						<tr>
							<td class="bcTitleLine"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<nested:notEmpty property="searchList">
				<table border="0" cellpadding="0" cellspacing="0" width="1400">
					<tr>
						<td><!-- 明細部 -->
						<table border="0">
							<tr>
								<td>
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<nested:equal property="noActionSearchFlg" value="false">
											<td>
												<div id="cssButton"><a href="#"
													onclick="allCheck(true); return false;"
													class="cssButton"> &nbsp;全&nbsp;選&nbsp;択&nbsp;
												</a></div>
											</td>
											<td>
											<td>
												<div id="cssButton"><a href="#"
													onclick="allCheck(false); return false;"
													class="cssButton"> &nbsp;全&nbsp;解&nbsp;除&nbsp;
												</a></div>
											</td>
											<td width="50">
											<nested:equal property="updateAuthority" value="true">

												<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(noCheck())) {return false;} else{if (!putDirtyConfirm()) {return false;}else{if(!putFixConfirm()) {return false;} else{return form_submit('op', 'fix'); return false;}}}" class="cssButton">
														在庫引当
													</a>
												</div>
												</td>

												<td width="15">
												<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(noCheck())) {return false;} else{if (!putDirtyConfirm()) {return false;} else{if(!deleteConfirm()) {return false;} else{return form_submit('op', 'orderRelease'); return false;}}}" class="cssButton">
														引当取消
													</a>
												</div>
												</td>


												<td width="50">
												<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(noCheck())) {return false;} else{if (!putDirtyConfirm()) {return false;} else{if(!outputConfirm()) {return false;}else{return form_submit('op', 'createContactTable'); return false;}}}" class="cssButton">
														納期連絡表出力
													</a>
												</div>
												</td>
												<td width="20">
												<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(noCheck())) {return false;} else{ if (!(putDirtyConfirm())) {return false;} else{if(!delDateReplyConfirm()) {return false;}else{return form_submit('op', 'sendMail'); return false;}}}" class="cssButton">
														納期連絡表返信(mail,FAX)
													</a>
												</div>
												</td>
											</nested:equal>
										</nested:equal>

										<td width="20">
										<nested:equal property="updateAuthority" value="true">
											<td>
											<div id="cssButton">
												<a href="#" onclick="if (!(noCheck())) {return false;} else{ if (!(putDirtyConfirm())) {return false;} else{if(!replyConfirm()) {return false;}else{return form_submit('op', 'sendFileToMail'); return false;}}}" class="cssButton">
													返信(ファイル)
												</a>
											</div>
											</td>
										</nested:equal>
										<td width="75">
										<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(orderReportConfirm())) {return false;}else{return form_submit('op', 'orderReport'); return false;}" class="cssButton">
												受注一覧表
											</a>
										</div>
										</td>
										<td width="20">
										<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(reportConfirm())) {return false;} else{if (!putDirtyConfirm()) {return false;} else{return form_submit('op', 'report'); return false;}}" class="cssButton">
												帳票(EXCEL)
											</a>
										</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>

							<tr>
								<td>
								<table cellspacing="2" cellpadding="1" id="tblList" >
									<nested:hidden property="searchListLength" styleId="searchListLength" />
									<tr class="bcTitleList fb fcTitleList">
										<td width="20" rowspan="2"></td>
										<td width="20" rowspan="2"></td>
										<td width="130">先付受注番号</td>
										<td width="20" rowspan="2"></td>
										<td width="150">AP納入先</td>
										<td width="150">AP得意先</td>
										<td width="240" colspan="2">AP品目</td>
										<td width="80">出荷予定</td>
										<td width="80">納入予定</td>
										<td width="50" rowspan="2" >有効<br />在庫</td>
										<td width="200" rowspan="2">備考</td>
										<td width="45" rowspan="2">入力ﾁｪｯｸ</td>
										<td width="45" rowspan="2">納期<br />連絡</td>

									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="130">受注番号</td>
										<td width="150">取込ステータス</td>
										<td width="150">受注ステータス</td>
										<td width="120">エラー状態</td>
										<td width="120">客先注文番号</td>
										<td width="80">数量</td>
										<td width="80">増付数</td>
									</tr>

									<nested:iterate id="searchList" property="searchList" indexId="index">

										<app:modulo
											numerator='<%=pageContext.findAttribute("index").toString()%>'
											denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo
											numerator='<%=pageContext.findAttribute("index").toString()%>'
											denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>



										<nested:hidden property="frstOrderNo" styleId="<%="frstOrderNo" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="frstOrderRowNo" styleId="<%="frstOrderRowNo" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="itemCd" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="rank" styleId="<%="rank" + pageContext.findAttribute("index").toString() %>" />
										<nested:define id="oid1" property="frstOrderNo" />
										<nested:define id="oid2" property="frstOrderRowNo" />

											<%-- チェックボックス --%>
											<td rowspan="2">
												<nested:equal property="rank" value="1">
													<nested:checkbox property="orderImportCheckBox" styleId="<%="orderImportCheckBox" + pageContext.findAttribute("index").toString() %>" />
													<nested:hidden property="orderImportCheckBox" styleId="<%="orderImportCheckBox" + pageContext.findAttribute("index").toString() %>" value="0" />
												</nested:equal>
											</td>
											<td rowspan="2">
												<nested:write property="rowNum"/>
											</td>
											<td><%-- 先付け受注番号--%>
												<nested:equal property="rank" value="1">
													<html:link href="#"
														onclick='<%="toDetail('" + request.getContextPath() + "/OrderImportDetail.do?op=init&frstOrderNo=" + pageContext.findAttribute("oid1").toString() + "'); return false;"%>'>
														<nested:write property="frstOrderNo" />
													</html:link>
												</nested:equal>
											</td>
											<td rowspan="2">
												<nested:write property="rank"/>
											</td>
											<td width="150"><%-- AP納入先 --%>
												<nested:equal property="rank" value="1">
													<nested:write property="deliveryShortedName"/>
												</nested:equal>
											</td>
											<td width="150"><%-- AP得意先 --%>
												<nested:equal property="rank" value="1">
													<nested:write property="venderName" />
												</nested:equal>
											</td>
											<td width="240" colspan="2"><%-- AP品目 --%>
												<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
												<html:link href="#" onclick='<%="open_modal_popup_edge(730, 600,'OrderLotSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value); return false;"%>'>
													<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
														<nested:write property="itemName" />
													</div>
												</html:link>
												<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
											</td>
											<td><%-- 出荷予定日 --%>
												<nested:equal property="rank" value="1">
													<nested:write property="strScheduledShippingDate" />
												</nested:equal>
											</td>
											<td><%-- 納期 --%>
												<nested:equal property="rank" value="1">
													<nested:write property="strDeliveryDate" />
												</nested:equal>
											</td>
											<td rowspan="2" class="alignRight"><%-- 有効在庫 --%> <nested:write property="inventoryQty"/></td>
											<td rowspan="2"><%-- 備考 --%>
												<nested:write property="allRemarks"/>
											</td>
											<td rowspan="2" class="alignCenter"><%-- 入力チェック --%>
												<nested:checkbox property="inputApprovaledCheck" disabled="true"/>
											</td>
											<td rowspan="2" class="alignCenter"><%-- 納期連絡 --%>
												<nested:checkbox property="delDateApprovaledCheck" disabled="true"/>
											</td>
										</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											<td>
												<nested:equal property="rank" value="1">
													<%-- 受注番号 --%><nested:write property="orderNo"/>
												</nested:equal>
											</td>
											<td><%-- 取込ステータス --%> <nested:write property="importStatusName" /></td>
											<td><%-- 受注ステータス --%>
												<nested:write property="orderStatusName"/>
											</td>
											<td><%-- エラー状態 --%><nested:write property="errorStatusName"/></td>
											<td><%-- 客先受注番号 --%>
												<nested:notEmpty property="ctmOrderNo">
													<nested:write property="ctmOrderNo" />
												</nested:notEmpty>
											</td>
											<td class="alignRight" > <%-- 数量 --%>
												<nested:write property="strOrderQty" />
											</td>
											<td class="alignRight"><%-- 増付数 --%>
												<nested:write property="strMatss" />
											</td>
										</tr>
									</nested:iterate>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter"><%-- ページング --%> <%@ include
							file="/jsp/common/pager/pager.jsf"%> <%-- ページング ここまで --%>
						</td>
					</tr>
				</table>
			</nested:notEmpty></td>
		</tr>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH"
				CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelSlipDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH"
				CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
	</table>
</nested:form>

</body>
</html:html>

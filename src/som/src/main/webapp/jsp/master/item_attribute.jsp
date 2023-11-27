<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>' media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 検索画面からCallされるメソッド
	(postjavascript.jsfのopen_modal_popup_edgeと対応) --%>
	function setRegistValues(reason,registFlg) {
		$('reason').value = reason;
		$('registFlg').value = registFlg;
		
		if ($('registFlg').value == "true") {
			form_submit('op','update');			
			
		}
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	function setInputDate(strInputDate, inputFlg) {
		$('strInputDate').value = strInputDate;
		$('inputFlg').value = inputFlg;
		if ($('inputFlg').value == "true") {
			form_submit('op','updatePrice');			
			
		}
	}

	<%-- 基本画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemDetail.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 成分画面への遷移 --%>
	function toComponent(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemComponent.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 関連画面への遷移 --%>
	function toRelated(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemRelated.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 技術画面への遷移 --%>
	function toTech(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemTech.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- その他画面への遷移 --%>
	function toOther(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemOther.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 更新画面への遷移 --%>
	function toHistory(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemHistory.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();
		<%-- 必須フィールド定義 --%>
        defineAsRequiredField("strExpireMonths");
        defineAsRequiredField("strContractMonths");

		<%-- 数値型フィールド定義 --%>
        defineAsNumberField("strExpireMonths");
        defineAsNumberField("strContractMonths");

		<%-- 製造品扱い属性 --%>
		if ($('productDivision').value != '0') {
			<%-- 必須フィールド定義 --%>
	        defineAsRequiredField("strOrderPattern");

			<%-- 数値型フィールド定義 --%>
	        defineAsNumberField("strOrderPattern");
	        defineAsNumberField("strInspectionDays");
	        defineAsNumberField("strProductOrderPoint");
		}

		<%-- 販売品扱い属性 --%>
		if ($('articleDivision').value != '0') {
			<%-- 必須フィールド定義 --%>
	        defineAsRequiredField("strSellingPrice");

			<%-- 数値型フィールド定義 --%>
	        defineAsNumberField("strSellingPrice");
	        defineAsNumberField("strPaletteProducts");
	        defineAsNumberField("strSafetyLeadTime");
		}


		<%-- 購入品扱い属性 --%>
		if ($('purchaseDivision').value != '0') {
			<%-- 必須フィールド定義 --%>
	        defineAsRequiredField("strPurchaseLeadTime");
	        defineAsRequiredField("strPurchasePrice");

			<%-- 数値型フィールド定義 --%>
	        defineAsNumberField("strPurchaseLeadTime");
	        defineAsNumberField("strPurchasePrice");
	        defineAsNumberField("strPurchaseOrderPoint");
	        defineAsNumberField("strOrderQty");
		}

		if ($('productDivision').value != '0') {
			<%-- 生産工場のautocomplete --%>
			new Ajax.Autocompleter(
				"productionLine",
				"autocomplete_product",
				"<%= request.getContextPath() %>/LineForAutoComplete.do?op=searchLine",
				{
					paramName : "code",
					afterUpdateElement : setLineLabel
				}
			);

			<%-- 原価部門のautocomplete --%>
			new Ajax.Autocompleter(
				"productSectionCd",
				"autocomplete_product",
				"<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
				{
					paramName : "code",
					afterUpdateElement : setProductBumonLabel
				}
			);
		}

		if ($('articleDivision').value != '0') {
			<%-- 売上会計部門のautocomplete --%>
			new Ajax.Autocompleter(
				"articleSectionCd",
				"autocomplete_article",
				"<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
				{
					paramName : "code",
					afterUpdateElement : setArticleBumonLabel
				}
			);

			<%-- 売上勘定科目のautocomplete --%>
			new Ajax.Autocompleter(
				"articleAccountsCd",
				"autocomplete_article",
				"<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
				{
					paramName : "code",
					afterUpdateElement : setArticleAccountsLabel
				}
			);

			<%-- 財務分類のautocomplete --%>
			new Ajax.Autocompleter(
				"financialClassCd",
				"autocomplete_article",
				"<%= request.getContextPath() %>/FinancialClassForAutoComplete.do?op=searchFinancialClass",
				{
					paramName : "code",
					afterUpdateElement : setFinancialClassLabel
				}
			);
		}

		if ($('purchaseDivision').value != '0') {
			<%-- 基準仕入先のautocomplete --%>
			new Ajax.Autocompleter(
				"venderCd",
				"autocomplete_purchase",
				"<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
				{
					parameters : 'venderDivision='+$("venderDivision").value,
					paramName : "code",
					afterUpdateElement : setVenderLabel
				}
			);

			<%-- 納入先のautocomplete --%>
			new Ajax.Autocompleter(
				"deliveryCd",
				"autocomplete_purchase",
				"<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
				{
					paramName : "code",
					afterUpdateElement : setDeliveryLabel
				}
			);

			<%-- 仕入会計部門のautocomplete --%>
			new Ajax.Autocompleter(
				"purchaseSectionCd",
				"autocomplete_purchase",
				"<%= request.getContextPath() %>/BumonForAutoComplete.do?op=searchBumon",
				{
					paramName : "code",
					afterUpdateElement : setPurchaseBumonLabel
				}
			);

			<%-- 仕入勘定科目のautocomplete --%>
			new Ajax.Autocompleter(
				"purchaseAccountsCd",
				"autocomplete_purchase",
				"<%= request.getContextPath() %>/AccountsForAutoComplete.do?op=searchAccounts",
				{
					paramName : "code",
					afterUpdateElement : setPurchaseAccountsLabel
				}
			);
		}

		<%-- 検索後入力された場合の不整合をただす --%>
		if ($('productDivision').value != '0') {
			Event.observe('productionLine', 'keypress', clearLabel.bindAsEventListener($('productionLineName')), false);
			Event.observe('productSectionCd', 'keypress', clearLabel.bindAsEventListener($('productSectionName')), false);
		}

		if ($('articleDivision').value != '0') {
			Event.observe('articleSectionCd', 'keypress', clearLabel.bindAsEventListener($('articleSectionName')), false);
			Event.observe('articleAccountsCd', 'keypress', clearLabel.bindAsEventListener($('articleAccountsName')), false);
			Event.observe('financialClassCd', 'keypress', clearLabel.bindAsEventListener($('financialClassName')), false);
		}

		if ($('purchaseDivision').value != '0') {
			Event.observe('venderCd', 'keypress', clearLabel.bindAsEventListener($('venderName1')), false);
			Event.observe('deliveryCd', 'keypress', clearLabel.bindAsEventListener($('deliveryName1')), false);
			Event.observe('purchaseSectionCd', 'keypress', clearLabel.bindAsEventListener($('purchaseSectionName')), false);
			Event.observe('purchaseAccountsCd', 'keypress', clearLabel.bindAsEventListener($('purchaseAccountsName')), false);
		}

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg', 'strInputDate', 'inputFlg');
	}, false);

	<%-- ajax --%>
	function setLineLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("productionLineName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setProductBumonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("productSectionName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setArticleBumonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("articleSectionName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setArticleAccountsLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("articleAccountsName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setFinancialClassLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("financialClassName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("venderName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("deliveryName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setPurchaseBumonLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("purchaseSectionName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setPurchaseAccountsLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("purchaseAccountsName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	<%-- 支給品区分確認メッセージ --%>
	function divisionConfirm() {
		<%-- 購入品扱い属性 --%>
		if ($('purchaseDivision').value != '0') {
			<%-- 支給品区分が無償の場合 --%>
			if($("suppliedGoodsDivision").value==2) {
				alertMsg = new Array();
				alertMsg[0] = "支給品区分が無償です。このまま登録してよろしいですか？";
				return confirm(alertMsg[0]);
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
		open_modal_popup_edge(750, 210,'ItemRegist', '', 'reason', $('reason').value);

	}

	<%-- 承認依頼メッセージ --%>
	function putApprovalRequestConfirm() {
		var flg = $("dirtyFlg").checked;
		alertMsg = new Array();

		if (flg) {
			/* 何か値が変更されている場合 */
			alertMsg[0] = "画面の編集内容は破棄して承認依頼されます。よろしいですか？";
		} else {
			alertMsg[0] = "承認依頼を行います。よろしいですか？";
		}

		return confirm(alertMsg[0]);
	}

	<%-- 承認メッセージ --%>
	function putApprovalConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 否認メッセージ --%>
	function putNegationConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "否認します。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 承認取消メッセージ --%>
	function putApprovalCancelConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "承認取消します。よろしいですか？";
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

	<%-- 標準販売単価チェック --%>
	function checkPrice() {
		var flg = $("dirtyFlg").checked;

		if (flg) {
			alert("<bean:message key="message.confirm.dirty.value" />");
			return false;
		}

		var price = $("strSellingPrice").value;
		price = unformatNumber(price);

		if (isEmpty(price) || price == '0') {
			/* 標準販売単価が未入力の場合 */
			alert("<bean:message key="message.confirm.empty.selling.price" />");
			return false;
		}

		<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
		open_modal_popup_edge(420, 260,'ItemUnitUpdate', '', 'strInputDate', $('strInputDate').value);

		return true;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ItemAttribute" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="headItemCd" styleId="headItemCd"/>
	<nested:hidden property="headVersion" styleId="headVersion"/>
	<nested:hidden property="venderDivision" styleId="venderDivision"/>
	<nested:hidden property="productDivision" styleId="productDivision"/>
	<nested:hidden property="articleDivision" styleId="articleDivision"/>
	<nested:hidden property="purchaseDivision" styleId="purchaseDivision"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="reason" styleId="reason"/>
	<nested:hidden property="registFlg" styleId="registFlg"/>
	<nested:hidden property="strInputDate" styleId="strInputDate"/>
	<nested:hidden property="inputFlg" styleId="inputFlg"/>

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
									<td class="fcTitle fb">品目マスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">品目コード</td>
									<td>
										<nested:write property="itemCd"/>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="130">品目名称</td>
									<td>
										<nested:write property="headItemName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">有効日</td>
									<td>
										<nested:write property="strHeadActiveDate"/>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">有効</td>
									<td>
										<nested:write property="headActivate"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" rowspan="2">ステータス</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">基本情報</td>
									<td>
										<nested:write property="headDetailStatusName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">在庫・単価情報</td>
									<td>
										<nested:write property="headAttributeStatusName"/>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="5"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="5"></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
										<span id="slidetabsmenu">
											<ul>
												<li><html:link href="#" onclick='toDetail();return false;' title="基本"><span>基本</span></html:link></li>
												<li class="current"><a title="在庫・単価"><span>在庫・単価</span></a></li>
												<li><html:link href="#" onclick='toComponent();return false;' title="成分"><span>成分</span></html:link></li>
												<li><html:link href="#" onclick='toRelated();return false;' title="関連"><span>関連</span></html:link></li>
												<li><html:link href="#" onclick='toTech();return false;' title="技術"><span>技術</span></html:link></li>
												<li><html:link href="#" onclick='toOther();return false;' title="その他"><span>その他</span></html:link></li>
												<li><html:link href="#" onclick='toHistory();return false;' title="更新"><span>更新</span></html:link></li>
											</ul>
										</span>

										<span id="slidetabs" style="clear: left;">
											<table cellpadding="2" cellspacing="2" width="100%" border="0">
												<tr>
													<td class="fcTitle fb" colspan="5">共通項目</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="130" colspan="2">使用期限</td>
													<td>
														<nested:text property="strExpireMonths" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strExpireMonths" onchange="setDirtyFlg();" />
													</td>

													<td class="fcTitleDetail fb bcTitleDetail" width="130">取引可能期限</td>
													<td>
														<nested:text property="strContractMonths" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strContractMonths" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" colspan="2">発注情報</td>
													<td colspan="3">
														<nested:textarea property="orderInfo" rows="3" cols="65" styleId="orderInfo" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" colspan="2">備考</td>
													<td colspan="3">
														<nested:textarea property="remarks" rows="3" cols="65" styleId="remarks" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" colspan="2">適用法令</td>
													<td colspan="3">
														<nested:text property="applicationLaw" maxlength="5" size="5" styleId="applicationLaw" onchange="setDirtyFlg();" />
													</td>
												</tr>
											</table>

											<table cellpadding="2" cellspacing="2" width="100%" border="0">
												<nested:notEqual property="productDivision" value="0">
													<tr>
														<td class="fcTitle fb" colspan="5">製造品扱い属性</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" width="130" colspan="2">指図書パターン</td>
														<td colspan="3">
															<nested:text property="strOrderPattern" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strOrderPattern" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">生産工場No</td>
														<td colspan="2">
															<nested:text property="productionLine" maxlength="2" size="10" styleId="productionLine" onchange="setDirtyFlg();" />
															<div id="autocomplete_product" class="autocomplete"></div>
														</td>

														<td width="450">
															<div id="lblProductionLineName">
																<nested:write property="productionLineName" />
															</div>
															<nested:hidden property="productionLineName" styleId="productionLineName"/>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">検査時間</td>
														<td colspan="3">
															<nested:text property="strInspectionDays" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strInspectionDays" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">発注点</td>
														<td colspan="3">
															<nested:text property="strProductOrderPoint" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strProductOrderPoint" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">原価部門コード</td>
														<td colspan="2">
															<nested:text property="productSectionCd" maxlength="10" size="10" styleId="productSectionCd" onchange="setDirtyFlg();" />
														</td>

														<td width="450">
															<div id="lblProductSectionName">
																<nested:write property="productSectionName" />
															</div>
															<nested:hidden property="productSectionName" styleId="productSectionName"/>
														</td>
													</tr>
												</nested:notEqual>
											</table>

											<table cellpadding="2" cellspacing="2" width="100%" border="0">
												<nested:notEqual property="articleDivision" value="0">
													<tr>
														<td class="fcTitle fb" colspan="5">販売品扱い属性</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" width="130" colspan="2">単価決定単位区分</td>
														<td colspan="3">
															<% pageContext.setAttribute("SelectPriceCalcDivision", new com.asahikaseieng.app.comboboxes.SelectPriceCalcDivision(false, false)); %>
															<nested:select property="articlePriceCalcDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectPriceCalcDivision" property="values" labelName="SelectPriceCalcDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">標準販売単価</td>
														<td>
															<nested:text property="strSellingPrice" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strSellingPrice" onchange="setDirtyFlg();" />
														</td>

														<td class="fcTitleDetail fb bcTitleDetail" width="130">パレット上製品数</td>
														<td>
															<nested:text property="strPaletteProducts" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPaletteProducts" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">JANコード</td>
														<td>
															<nested:text property="janCd" maxlength="13" size="13" style="ime-mode:disabled" styleId="janCd" onchange="setDirtyFlg();" />
														</td>

														<td class="fcTitleDetail fb bcTitleDetail">ITFコード</td>
														<td>
															<nested:text property="itfCd" maxlength="16" size="16" style="ime-mode:disabled" styleId="itfCd" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">リードタイム</td>
														<td colspan="3">
															<nested:text property="strSafetyLeadTime" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strSafetyLeadTime" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" rowspan="2" width="20">売上</td>
														<td class="fcTitleDetail fb bcTitleDetail">会計部門コード</td>
														<td>
															<nested:text property="articleSectionCd" maxlength="10" size="22" styleId="articleSectionCd" onchange="setDirtyFlg();" />
															<div id="autocomplete_article" class="autocomplete"></div>
														</td>

														<td width="250" colspan="2">
															<div id="lblArticleSectionName">
																<nested:write property="articleSectionName" />
															</div>
															<nested:hidden property="articleSectionName" styleId="articleSectionName"/>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail">勘定科目コード</td>
														<td>
															<nested:text property="articleAccountsCd" maxlength="10" size="22" styleId="articleAccountsCd" onchange="setDirtyFlg();" />
														</td>

														<td width="250" colspan="2">
															<div id="lblArticleAccountsName">
																<nested:write property="articleAccountsName" />
															</div>
															<nested:hidden property="articleAccountsName" styleId="articleAccountsName"/>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">売上消費税課税区分</td>
														<td colspan="3">
															<%
															pageContext.setAttribute("SelectNewTaxDivisionItem", new com.asahikaseieng.app.comboboxes.SelectNewTaxDivisionItem(false, false));
															%>
															<nested:select property="articleTaxDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectNewTaxDivisionItem" property="values" labelName="SelectNewTaxDivisionItem" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">財務分類コード</td>
														<td>
															<nested:text property="financialClassCd" maxlength="7" size="22" styleId="financialClassCd" onchange="setDirtyFlg();" />
														</td>

														<td width="250" colspan="2">
															<div id="lblFinancialClassName">
																<nested:write property="financialClassName" />
															</div>
															<nested:hidden property="financialClassName" styleId="financialClassName"/>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">預かり品区分</td>
														<td colspan="3">
															<%
															pageContext.setAttribute("SelectKeepDivision", new com.asahikaseieng.app.comboboxes.SelectKeepDivision(false, false));
															%>
															<nested:select property="keepDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectKeepDivision" property="values" labelName="SelectKeepDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>
												</nested:notEqual>
											</table>

											<table cellpadding="2" cellspacing="2" width="100%" border="0">
												<nested:notEqual property="purchaseDivision" value="0">
													<tr>
														<td class="fcTitle fb" colspan="5">購入品扱い属性</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" width="130" colspan="2">メーカー名</td>
														<td colspan="3">
															<nested:text property="materialMakerName" maxlength="40" size="75" styleId="materialMakerName" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">基準仕入先コード</td>
														<td>
															<nested:text property="venderCd" maxlength="15" size="22" styleId="venderCd" onchange="setDirtyFlg();" />
															<div id="autocomplete_purchase" class="autocomplete"></div>
														</td>

														<td width="250" colspan="2">
															<div id="lblVenderName1">
																<nested:write property="venderName1" />
															</div>
															<nested:hidden property="venderName1" styleId="venderName1"/>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">納入先コード</td>
														<td>
															<nested:text property="deliveryCd" maxlength="15" size="22" styleId="deliveryCd" onchange="setDirtyFlg();" />
														</td>

														<td width="250" colspan="2">
															<div id="lblDeliveryName1">
																<nested:write property="deliveryName1" />
															</div>
															<nested:hidden property="deliveryName1" styleId="deliveryName1"/>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">リードタイム</td>
														<td>
															<nested:text property="strPurchaseLeadTime" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPurchaseLeadTime" onchange="setDirtyFlg();" />
														</td>

														<td class="fcTitleDetail fb bcTitleDetail" width="130">標準仕入単価</td>
														<td>
															<nested:text property="strPurchasePrice" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPurchasePrice" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">購買消費税課税区分</td>
														<td>
															<%
															pageContext.setAttribute("SelectNewTaxDivision", new com.asahikaseieng.app.comboboxes.SelectNewTaxDivisionItem(false, false));
															%>
															<nested:select property="purchaseTaxDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectNewTaxDivision" property="values" labelName="SelectNewTaxDivision" labelProperty="labels"/>
															</nested:select>
														</td>

														<td class="fcTitleDetail fb bcTitleDetail">発注基準</td>
														<td>
															<% pageContext.setAttribute("SelectPurchaseTrigger", new com.asahikaseieng.app.comboboxes.SelectPurchaseTrigger(false, false)); %>
															<nested:select property="purchaseTrigger" onblur="setDirtyFlg();">
																<nested:options name="SelectPurchaseTrigger" property="values" labelName="SelectPurchaseTrigger" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">複数社発注区分</td>
														<td>
															<% pageContext.setAttribute("SelectMultiSupplierDivision", new com.asahikaseieng.app.comboboxes.SelectMultiSupplierDivision(false, false)); %>
															<nested:select property="multiSupplierDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectMultiSupplierDivision" property="values" labelName="SelectMultiSupplierDivision" labelProperty="labels"/>
															</nested:select>
														</td>

														<td class="fcTitleDetail fb bcTitleDetail">発注点</td>
														<td>
															<nested:text property="strPurchaseOrderPoint" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strPurchaseOrderPoint" onchange="setDirtyFlg();" />
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">標準発注数</td>
														<td>
															<nested:text property="strOrderQty" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strOrderQty" onchange="setDirtyFlg();" />
														</td>

														<td class="fcTitleDetail fb bcTitleDetail">基準検査方法</td>
														<td>
															<% pageContext.setAttribute("SelectInspectionType", new com.asahikaseieng.app.comboboxes.SelectInspectionType(false, false)); %>
															<nested:select property="inspectionType" onblur="setDirtyFlg();">
																<nested:options name="SelectInspectionType" property="values" labelName="SelectInspectionType" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">単価決定単位区分</td>
														<td>
															<% pageContext.setAttribute("SelectPriceCalcDivision", new com.asahikaseieng.app.comboboxes.SelectPriceCalcDivision(false, false)); %>
															<nested:select property="purchasePriceCalcDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectPriceCalcDivision" property="values" labelName="SelectPriceCalcDivision" labelProperty="labels"/>
															</nested:select>
														</td>

														<td class="fcTitleDetail fb bcTitleDetail">支給品区分</td>
														<td>
															<% pageContext.setAttribute("SelectSuppliedGoodsDivision", new com.asahikaseieng.app.comboboxes.SelectSuppliedGoodsDivision(false, false)); %>
															<nested:select property="suppliedGoodsDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectSuppliedGoodsDivision" property="values" labelName="SelectSuppliedGoodsDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">リースドラムフラグ</td>
														<td colspan="3">
															<% pageContext.setAttribute("SelectLeaseDrumFlag", new com.asahikaseieng.app.comboboxes.SelectLeaseDrumFlag(false, false)); %>
															<nested:select property="leaseDrumFlag" onblur="setDirtyFlg();">
																<nested:options name="SelectLeaseDrumFlag" property="values" labelName="SelectLeaseDrumFlag" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" colspan="2">ローリー区分</td>
														<td colspan="3">
															<% pageContext.setAttribute("SelectLorryDivision", new com.asahikaseieng.app.comboboxes.SelectLorryDivision(false, false)); %>
															<nested:select property="lorryDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectLorryDivision" property="values" labelName="SelectLorryDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" rowspan="2" width="20">仕入</td>
														<td class="fcTitleDetail fb bcTitleDetail">会計部門コード</td>
														<td>
															<nested:text property="purchaseSectionCd" maxlength="10" size="22" styleId="purchaseSectionCd" onchange="setDirtyFlg();" />
														</td>

														<td width="250" colspan="2">
															<div id="lblPurchaseSectionName">
																<nested:write property="purchaseSectionName" />
															</div>
															<nested:hidden property="purchaseSectionName" styleId="purchaseSectionName"/>
														</td>
													</tr>

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail">勘定科目コード</td>
														<td>
															<nested:text property="purchaseAccountsCd" maxlength="10" size="22" styleId="purchaseAccountsCd" onchange="setDirtyFlg();" />
														</td>

														<td width="250" colspan="2">
															<div id="lblPurchaseAccountsName">
																<nested:write property="purchaseAccountsName" />
															</div>
															<nested:hidden property="purchaseAccountsName" styleId="purchaseAccountsName"/>
														</td>
													</tr>
												</nested:notEqual>
											</table>
										</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="550" border="0">
								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>

								<tr>
									<nested:equal property="status" value="3">
										<nested:equal property="headerStatus" value="3">
											<nested:notEqual property="articleDivision" value="0">
												<td class="alignRight">
													<div id="cssButton">
														<a href="#" onclick="if(!checkPrice()){return false;}else{return false;}" class="cssButton">
															&nbsp;&nbsp;標準単価更新&nbsp;&nbsp;
														</a>
													</div>
												</td>

												<td width="50">
													<br>
												</td>
											</nested:notEqual>
										</nested:equal>
									</nested:equal>

									<nested:equal property="status" value="1">
										<nested:equal property="approvalRequestAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!putApprovalRequestConfirm()){return false;}else{return form_submit('op', 'approvalRequest'); return false;}" class="cssButton">
														&nbsp;&nbsp;承認依頼&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>

										<nested:equal property="updateAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if(!divisionConfirm()){return false;}else{if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:equal>

									<nested:equal property="status" value="2">
										<nested:equal property="approvalAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!putApprovalConfirm()){return false;}else{return form_submit('op', 'approval'); return false;}" class="cssButton">
														&nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>

										<nested:equal property="negationAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if(!putNegationConfirm()){return false;}else{return form_submit('op', 'negation'); return false;}" class="cssButton">
														&nbsp;&nbsp;否&nbsp;&nbsp;認&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:equal>

									<nested:equal property="status" value="3">
										<nested:equal property="approvalCancelAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!putApprovalCancelConfirm()){return false;}else{return form_submit('op', 'approvalCancel'); return false;}" class="cssButton">
														&nbsp;&nbsp;承認取消&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:equal>

									<nested:equal property="status" value="4">
										<nested:equal property="updateAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!divisionConfirm()){return false;}else{if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:equal>

									<nested:equal property="status" value="0">
										<nested:equal property="updateAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!divisionConfirm()){return false;}else{if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>
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

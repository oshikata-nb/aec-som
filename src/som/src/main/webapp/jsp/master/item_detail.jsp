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

	<%-- 在庫・単価画面への遷移 --%>
	function toAttribute(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemAttribute.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
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
        defineAsRequiredField("itemName");
        defineAsRequiredField("strActiveDate");
        defineAsRequiredField("strKgOfFractionManagement");
        defineAsRequiredField("defaultLocation");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strActiveDate");

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strNumberOfInsertions");
		defineAsNumberField("strAllUpWeight");
		defineAsNumberField("strKgOfFractionManagement");
		defineAsNumberField("strKgConversionCoefficient");

		<%-- 親品目のautocomplete --%>
		new Ajax.Autocompleter(
			"parentItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setParentItemQueueLabel
			}
		);

		<%-- 基準保管場所のautocomplete --%>
		new Ajax.Autocompleter(
			"defaultLocation",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LocationForAutoComplete.do?op=searchLocation",
			{
				paramName : "code",
				afterUpdateElement : setDefaultLocationLabel
			}
		);

		<%-- 発注まとめ場所のautocomplete --%>
		new Ajax.Autocompleter(
			"orderLocation",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LocationForAutoComplete.do?op=searchLocation",
			{
				paramName : "code",
				afterUpdateElement : setOrderLocationLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('parentItemCd', 'keypress', clearLabel.bindAsEventListener($('parentItemName')), false);
		Event.observe('defaultLocation', 'keypress', clearLabel.bindAsEventListener($('defaultLocationName')), false);
		Event.observe('orderLocation', 'keypress', clearLabel.bindAsEventListener($('orderLocationName')), false);

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- ajax --%>
	function setParentItemQueueLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("parentItemName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setDefaultLocationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("defaultLocationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
	}

	function setOrderLocationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("orderLocationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
		setDirtyFlg();
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
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ItemDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="headItemCd" styleId="headItemCd"/>
	<nested:hidden property="headVersion" styleId="headVersion"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="insertFlg" styleId="insertFlg"/>
	<nested:hidden property="reason" styleId="reason"/>
	<nested:hidden property="registFlg" styleId="registFlg"/>

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="3"></td>
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
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
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

									<nested:notEqual property="insertFlg" value="true">
										<nested:notEqual property="insertFlg" value="versionup">
											<td class="alignRight">
												<table cellspacing="1" cellpadding="1"  border="0">
													<tr>
														<nested:equal property="headDetailStatusName" value="承認済み">
															<nested:equal property="headAttributeStatusName" value="承認済み">
																<td>
																	<div id="cssButton">
																		<a href="#" onclick="return form_submit('op', 'versionup'); return false;" class="cssButton">
																			&nbsp;&nbsp;新バージョン予約&nbsp;&nbsp;
																		</a>
																	</div>
																</td>
															</nested:equal>
														</nested:equal>

														<td>
															<div id="cssButton">
																<a href="#" onclick="return form_submit('op', 'copy'); return false;" class="cssButton">
																	&nbsp;&nbsp;コピー作成&nbsp;&nbsp;
																</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
										</nested:notEqual>
									</nested:notEqual>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="4"></td>
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
												<li class="current"><a title="基本"><span>基本</span></a></li>

												<nested:notEqual property="insertFlg" value="versionup">
													<nested:notEqual property="insertFlg" value="true">
														<li><html:link href="#" onclick='toAttribute();return false;' title="在庫・単価"><span>在庫・単価</span></html:link></li>
														<li><html:link href="#" onclick='toComponent();return false;' title="成分"><span>成分</span></html:link></li>
														<li><html:link href="#" onclick='toRelated();return false;' title="関連"><span>関連</span></html:link></li>
														<li><html:link href="#" onclick='toTech();return false;' title="技術"><span>技術</span></html:link></li>
														<li><html:link href="#" onclick='toOther();return false;' title="その他"><span>その他</span></html:link></li>
														<li><html:link href="#" onclick='toHistory();return false;' title="更新"><span>更新</span></html:link></li>
													</nested:notEqual>
												</nested:notEqual>
											</ul>
										</span>

										<span id="slidetabs" style="clear: left;">
											<table cellpadding="2" cellspacing="2" width="100%" border="0">
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="150">品目名称</td>
													<td colspan="4">
														<nested:text property="itemName" maxlength="40" size="75" styleId="itemName" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">品目サブ名称</td>
													<td colspan="4">
														<nested:text property="itemSubName" maxlength="40" size="75" styleId="itemSubName" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">研究用品目</td>
													<td>
														<% pageContext.setAttribute("SelectResearchItem", new com.asahikaseieng.app.comboboxes.SelectResearchItem(false, false)); %>
														<nested:select property="researchItem" onblur="setDirtyFlg();">
															<nested:options name="SelectResearchItem" property="values" labelName="SelectResearchItem" labelProperty="labels"/>
														</nested:select>
													</td>

													<nested:notEqual property="insertFlg" value="versionup">
														<nested:notEqual property="insertFlg" value="true">
															<nested:equal property="taxCategoryFlg" value="SALES">
																	<td class="fcTitleDetail fb bcTitleDetail">売上課税区分</td>
																	<td>
																		<nested:select property="salesTaxCategory" onblur="setDirtyFlg();" >
																			<nested:options property="salesTaxCategoryValues" labelProperty="salesTaxCategoryLabels" />
																		</nested:select>
																	</td>
															</nested:equal>
															<nested:equal property="taxCategoryFlg" value="BOTH">
																	<td class="fcTitleDetail fb bcTitleDetail">売上課税区分</td>
																	<td>
																		<nested:select property="salesTaxCategory" onblur="setDirtyFlg();" >
																			<nested:options property="salesTaxCategoryValues" labelProperty="salesTaxCategoryLabels" />
																		</nested:select>
																	</td>
															</nested:equal>
														</nested:notEqual>
													</nested:notEqual>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">有効日</td>
													<td>
														<nested:text property="strActiveDate" maxlength="10" size="10" styleId="strActiveDate" style="ime-mode:disabled" onchange="setDirtyFlg();" />
													</td>


													<nested:notEqual property="insertFlg" value="versionup">
														<nested:notEqual property="insertFlg" value="true">
															<nested:equal property="taxCategoryFlg" value="PURCHASE">
																	<td class="fcTitleDetail fb bcTitleDetail">仕入課税区分</td>
																	<td>
																		<nested:select property="purchaseTaxCategory" onblur="setDirtyFlg();" >
																			<nested:options property="purchaseTaxCategoryValues" labelProperty="purchaseTaxCategoryLabels" />
																		</nested:select>
																	</td>
															</nested:equal>

															<nested:equal property="taxCategoryFlg" value="BOTH">
																	<td class="fcTitleDetail fb bcTitleDetail">仕入課税区分</td>
																	<td>
																		<nested:select property="purchaseTaxCategory" onblur="setDirtyFlg();" >
																			<nested:options property="purchaseTaxCategoryValues" labelProperty="purchaseTaxCategoryLabels" />
																		</nested:select>
																	</td>
															</nested:equal>
														</nested:notEqual>
													</nested:notEqual>

												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">荷主</td>

													<nested:notEqual property="insertFlg" value="true">
														<td>
															<% pageContext.setAttribute("SelectShipperDivision", new com.asahikaseieng.app.comboboxes.SelectShipperDivision(false, true)); %>
															<nested:select property="shipperDivision" onblur="setDirtyFlg();" disabled="true">
																<nested:options name="SelectShipperDivision" property="values" labelName="SelectShipperDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</nested:notEqual>

													<nested:equal property="insertFlg" value="true">
														<td>
															<% pageContext.setAttribute("SelectShipperDivision", new com.asahikaseieng.app.comboboxes.SelectShipperDivision(false, true)); %>
															<nested:select property="shipperDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectShipperDivision" property="values" labelName="SelectShipperDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</nested:equal>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">種別</td>

													<nested:notEqual property="insertFlg" value="true">
														<td>
															<% pageContext.setAttribute("SelectTypeDivision", new com.asahikaseieng.app.comboboxes.SelectTypeDivision(false, true)); %>
															<nested:select property="typeDivision" onblur="setDirtyFlg();" disabled="true">
																<nested:options name="SelectTypeDivision" property="values" labelName="SelectTypeDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</nested:notEqual>

													<nested:equal property="insertFlg" value="true">
														<td>
															<% pageContext.setAttribute("SelectTypeDivision", new com.asahikaseieng.app.comboboxes.SelectTypeDivision(false, true)); %>
															<nested:select property="typeDivision" onblur="setDirtyFlg();">
																<nested:options name="SelectTypeDivision" property="values" labelName="SelectTypeDivision" labelProperty="labels"/>
															</nested:select>
														</td>
													</nested:equal>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">品目運賃区分</td>
													<td>
														<% pageContext.setAttribute("SelectItemDivision", new com.asahikaseieng.app.comboboxes.SelectItemDivision(false, false)); %>
														<nested:select property="itemDivision" onblur="setDirtyFlg();">
															<nested:options name="SelectItemDivision" property="values" labelName="SelectItemDivision" labelProperty="labels"/>
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">他社コード1</td>
													<td>
														<nested:text property="otherCompanyCd1" maxlength="8" size="8" styleId="otherCompanyCd1" style="ime-mode:disabled" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
													<td>
														<nested:text property="styleOfPacking" maxlength="12" size="19" styleId="styleOfPacking" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">入数</td>
													<td>
														<nested:text property="strNumberOfInsertions" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strNumberOfInsertions" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">総重量</td>
													<td>
														<nested:text property="strAllUpWeight" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strAllUpWeight" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">運用管理単位</td>
													<td>
														<nested:select property="unitOfOperationManagement" onblur="setDirtyFlg();" >
															<nested:options property="unitOfOperationManagementValues" labelProperty="unitOfOperationManagementLabels" />
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">在庫管理単位</td>
													<td>
														<nested:select property="unitOfStockControl" onblur="setDirtyFlg();" >
															<nested:options property="unitOfStockControlValues" labelProperty="unitOfStockControlLabels" />
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">Kg換算係数(在庫)</td>
													<td>
														<nested:text property="strKgOfFractionManagement" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strKgOfFractionManagement" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">端数管理単位</td>
													<td>
														<nested:select property="unitOfFractionManagement" onblur="setDirtyFlg();" >
															<nested:options property="unitOfFractionManagementValues" labelProperty="unitOfFractionManagementLabels" />
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">Kg換算係数(端数)</td>
													<td>
														<nested:text property="strKgConversionCoefficient" maxlength="22" size="22" style="ime-mode:disabled" styleClass="alignRight" styleId="strKgOfConversionCoefficient" onchange="setDirtyFlg();" />
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">品目分類コード</td>
													<td>
														<nested:select property="itemCategory" onblur="setDirtyFlg();" >
															<nested:options property="itemCategoryValues" labelProperty="itemCategoryLabels" />
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">親品目コード</td>
													<td>
														<nested:text property="parentItemCd" maxlength="20" size="20" styleId="parentItemCd" onchange="setDirtyFlg();" />
														<div id="autocomplete_choices" class="autocomplete"></div>
													</td>

													<td>
														<div id="lblParentItemName">
															<nested:write property="parentItemName" />
														</div>
														<nested:hidden property="parentItemName" styleId="parentItemName"/>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">スポット区分</td>
													<td>
														<% pageContext.setAttribute("SelectSpotDivision", new com.asahikaseieng.app.comboboxes.SelectSpotDivision(false, false)); %>
														<nested:select property="spotDivision" onblur="setDirtyFlg();">
															<nested:options name="SelectSpotDivision" property="values" labelName="SelectSpotDivision" labelProperty="labels"/>
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">在庫管理区分</td>
													<td>
														<% pageContext.setAttribute("SelectItemStockDivision", new com.asahikaseieng.app.comboboxes.SelectItemStockDivision(false, false)); %>
														<nested:select property="stockDivision" onblur="setDirtyFlg();">
															<nested:options name="SelectItemStockDivision" property="values" labelName="SelectItemStockDivision" labelProperty="labels"/>
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">基準保管場所</td>
													<td>
														<nested:text property="defaultLocation" maxlength="20" size="20" styleId="defaultLocation" onchange="setDirtyFlg();" />
													</td>

													<td>
														<div id="lblDefaultLocationName">
															<nested:write property="defaultLocationName" />
														</div>
														<nested:hidden property="defaultLocationName" styleId="defaultLocationName"/>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">発注まとめ場所</td>
													<td>
														<nested:text property="orderLocation" maxlength="20" size="20" styleId="orderLocation" onchange="setDirtyFlg();" />
													</td>

													<td>
														<div id="lblOrderLocationName">
															<nested:write property="orderLocationName" />
														</div>
														<nested:hidden property="orderLocationName" styleId="orderLocationName"/>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">ロット管理区分</td>
													<td>
														<% pageContext.setAttribute("SelectLotDivision", new com.asahikaseieng.app.comboboxes.SelectLotDivision(false, false)); %>
														<nested:select property="lotDivision" onblur="setDirtyFlg();">
															<nested:options name="SelectLotDivision" property="values" labelName="SelectLotDivision" labelProperty="labels"/>
														</nested:select>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">水区分</td>
													<td>
														<% pageContext.setAttribute("SelectWaterDivision", new com.asahikaseieng.app.comboboxes.SelectWaterDivision(false, false)); %>
														<nested:select property="waterDivision" onblur="setDirtyFlg();">
															<nested:options name="SelectWaterDivision" property="values" labelName="SelectWaterDivision" labelProperty="labels"/>
														</nested:select>
													</td>
												</tr>

											</table>
										</span>
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
													<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
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
													<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>

											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:equal>

									<nested:equal property="insertFlg" value="true">
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
									</nested:equal>

									<nested:equal property="insertFlg" value="versionup">
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

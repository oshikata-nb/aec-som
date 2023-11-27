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
	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 選択して終了 --%>
	function selectClose(index){
		window.opener.setShippingOrderValues(true
			, $F("orderNo" + index)
			, $F("rowNo" + index)
			, $F("deliveryCd" + index)
			, $F("deliveryName1" + index)
			, $F("deliveryAddress" + index)

			, $F("venderCd" + index)
			, $F("itemCd" + index)
			, $F("itemName" + index)
			, $F("otherCompanyCd1" + index)
			, $F("styleOfPacking" + index)

			, $F("unitDivision" + index)
			, $F("decimalPoint" + index)
			, $F("round" + index)
			, $F("balanceCd" + index)
			, $F("strSuggestedDeliverlimit" + index)

			, $F("strScheduledShippingDate" + index)
			, $F("carryCd" + index)
			, $F("strCarryFare" + index)
			, $F("strOrderQty" + index)
			, $F("strMatss" + index)

			, $F("updateDate" + index)
			, $F("updateDateDetail" + index)
			, $F("srhOrderNo")
			, $F("srhOrderDivision")
			, $F("srhScheduledShippingDateFrom")

			, $F("srhScheduledShippingDateTo")
			, $F("srhDeliveryCd")
			, $F("srhDeliveryName1")
			, $F("srhVenderCd")
			, $F("srhVenderName1")

			, $F("srhItemCd")
			, $F("srhItemName")
			, $F("srhOtherCompanyCd1")
			, $F("srhStyleOfPacking")
			, $F("strDeliveryExpectedDate" + index)
		);

		//モーダルダイアログの終了
		window.close();
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- キャンセルして終了 --%>
	function selectCancel(){
		window.opener.setShippingOrderValues(false, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

		//モーダルダイアログの終了
		window.close();
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhScheduledShippingDateFrom");
		defineAsDateField("srhScheduledShippingDateTo");

		<%-- 納入先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhDeliveryCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
		  {
		  	paramName : "code",
		    afterUpdateElement : setDeliveryCdLabel
		  }
		);
		Event.observe('srhDeliveryCd',  'keypress', clearText.bindAsEventListener($('srhDeliveryName')), false);

		<%-- 得意先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhVenderCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
		  {
		  	paramName : "code",
		  	parameters : 'venderDivision=TS',
		    afterUpdateElement : setVenderLabel
		  }
		);
		Event.observe('srhVenderCd',  'keypress', clearText.bindAsEventListener($('srhVenderName')), false);

		<%-- 品目のauto complete --%>
		new Ajax.Autocompleter(
		  "srhItemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemLabel
		  }
		);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhStyleOfPacking')), false);
		Event.observe('srhDeliveryCd', 'keypress', clearText.bindAsEventListener($('srhDeliveryName1')), false);
		Event.observe('srhVenderCd', 'keypress', clearText.bindAsEventListener($('srhVenderName1')), false);

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "srhOtherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItemOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);

	}, false);

	<%-- 納入先auto completeの選択後処理 --%>
	function setDeliveryCdLabel(text, li) {
	    $("srhDeliveryName1").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 得意先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName1").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 品目auto completeの選択後処理 --%>
	function setItemLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	    $("srhStyleOfPacking").value = getHiddenValue(li,"styleOfPacking");
	}

	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhItemCd").value = getHiddenValue(li,"code");
	    $("srhStyleOfPacking").value = getHiddenValue(li,"styleOfPacking");
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>

<body>

<nested:form action="/ShippingOrderSearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="710">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" width="240">受注検索</td>
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
							<!-- 検索部 -->
							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="2"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">受注番号</td>
												<td>
													<nested:text property="srhOrderNo" maxlength="20" size="10" styleId="srhOrderNo" style="ime-mode:disabled" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">受注区分</td>
												<td>
													<%
														pageContext.setAttribute("selectShippingOrderDivision",
														new com.asahikaseieng.app.comboboxes.SelectShippingOrderDivision(true, false));
													%>
													<nested:select property="srhOrderDivision" styleId="srhOrderDivision">
														<nested:options name="selectShippingOrderDivision" property="values" labelName="selectShippingOrderDivision" labelProperty="labels" />
													</nested:select>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">出荷予定日</td>
												<td width="">
													<nested:text property="srhScheduledShippingDateFrom" maxlength="10" size="10" styleId="srhScheduledShippingDateFrom" style="ime-mode:disabled" /> ～
													<nested:text property="srhScheduledShippingDateTo" maxlength="10" size="10" styleId="srhScheduledShippingDateTo" style="ime-mode:disabled" />
												</td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">納入先</td>
												<td>
													<nested:text property="srhDeliveryCd" maxlength="10" size="12" styleId="srhDeliveryCd" />
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
												<td>
													<nested:text property="srhDeliveryName1" size="18" readonly="true" styleId="srhDeliveryName1" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">得意先</td>
												<td>
													<nested:text property="srhVenderCd" maxlength="10" size="12" styleId="srhVenderCd" />
												</td>
												<td>
													<nested:text property="srhVenderName1" size="18" readonly="true" styleId="srhVenderName1" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">品目</td>
												<td>
													<nested:text property="srhItemCd" maxlength="20" size="12" styleId="srhItemCd" />
												</td>
												<td>
													<nested:text property="srhItemName" size="18" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">他社コード1</td>
												<td colspan="2">
													<nested:text property="srhOtherCompanyCd1" maxlength="20" size="12" styleId="srhOtherCompanyCd1" />
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">荷姿</td>
												<td colspan="5">
													<nested:text property="srhStyleOfPacking" size="12" readonly="true" styleId="srhStyleOfPacking" styleClass="noborderAl" tabindex="-1"/>
												<td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="alignRight valignMiddle">
										<div id="cssButton">
											<a href="#" onclick="return form_submit('op', 'search'); return false;" class="cssButton">
												&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
											</a>
										</div>
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
				<table border="0" cellpadding="0" cellspacing="0" width="710">
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table cellspacing="2" cellpadding="2" width="100%">
								<tr class="bcTitleList fb fcTitleList">
									<td></td>
									<td>受注番号</td>
									<td>行番</td>
									<td>納入先</td>
									<td>得意先</td>
									<td>品目</td>
									<td>納期</td>
									<td>出荷予定日</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
									<tr class="bcList1">
								</app:modulo>
								<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
									<tr class="bcList2">
								</app:modulo>
									<td width="20">
										<div id="cssButton">
											<a href="#" class="cssButton"
											   onClick="<%="selectClose('" + pageContext.findAttribute("index").toString() + "')" %>">
												選
     										</a>
										</div>
										<nested:hidden property="deliveryAddress" styleId="<%="deliveryAddress" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="itemCd" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="otherCompanyCd1" styleId="<%="otherCompanyCd1" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="styleOfPacking" styleId="<%="styleOfPacking" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="balanceCd" styleId="<%="balanceCd" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="unitDivision" styleId="<%="unitDivision" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="decimalPoint" styleId="<%="decimalPoint" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="round" styleId="<%="round" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="carryCd" styleId="<%="carryCd" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="strCarryFare" styleId="<%="strCarryFare" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="strOrderQty" styleId="<%="strOrderQty" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="strMatss" styleId="<%="strMatss" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="updateDate" styleId="<%="updateDate" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="updateDateDetail" styleId="<%="updateDateDetail" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="strDeliveryExpectedDate" styleId="<%="strDeliveryExpectedDate" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 受注番号 --%>
									<td width="80">
										<nested:write property="orderNo" />
										<nested:hidden property="orderNo" styleId="<%="orderNo" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 行番 --%>
									<td class="alignCenter" width="35">
										<nested:write property="rowNo" />
										<nested:hidden property="rowNo" styleId="<%="rowNo" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 納入先 --%>
									<td width="100">
										<nested:write property="deliveryName1" />
										<nested:hidden property="deliveryCd" styleId="<%="deliveryCd" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="deliveryName1" styleId="<%="deliveryName1" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 得意先 --%>
									<td width="90">
										<nested:write property="venderName1" />
										<nested:hidden property="venderCd" styleId="<%="venderCd" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="venderName1" styleId="<%="venderName1" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 品目 --%>
									<td>
										<nested:write property="itemName" />
										<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 納期 --%>
									<td width="70">
										<nested:write property="strSuggestedDeliverlimit" />
										<nested:hidden property="strSuggestedDeliverlimit" styleId="<%="strSuggestedDeliverlimit" + pageContext.findAttribute("index").toString() %>" />
									</td>
									<%-- 出荷予定日 --%>
									<td width="80">
										<nested:write property="strScheduledShippingDate" />
										<nested:hidden property="strScheduledShippingDate" styleId="<%="strScheduledShippingDate" + pageContext.findAttribute("index").toString() %>" />
									</td>
						</nested:iterate>
							</table>
						</td>
					</tr>
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td class="alignCenter">
							<%-- ページング --%>
							<%@ include file="/jsp/common/pager/pager.jsf" %>
							<%-- ページング ここまで --%>
						</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
				</table>
			</nested:notEmpty>
				<table border="0" cellpadding="2" cellspacing="2" width="710">
					<tr>
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onClick="<%="selectCancel()" %>" class="cssButton">
									&nbsp;キャンセル&nbsp;
								</a>
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
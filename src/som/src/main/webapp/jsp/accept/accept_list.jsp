<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		<logic:equal name="acceptListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhSuggestedDeliverlimitDateFrom");
		defineAsDateField("srhSuggestedDeliverlimitDateTo");
		defineAsDateField("srhAcceptDateFrom");
		defineAsDateField("srhAcceptDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "srhItemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);

		<%-- 仕入先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhVenderCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
		  {
		  	paramName : "code",
		  	parameters : 'venderDivision=SI',
		    afterUpdateElement : setVenderLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhVenderCd', 'keypress', clearText.bindAsEventListener($('srhVenderName')), false);

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "srhOtherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueueOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);
		
		<%-- 担当部署のauto complete --%>
		new Ajax.Autocompleter(
		  "srhChargeOrganizationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
		  {
		  	paramName : "code",
		    afterUpdateElement : setChargeOrganizationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhChargeOrganizationCd', 'keypress', clearText.bindAsEventListener($('srhTantoSectionName')), false);

		<%-- 部署のauto complete --%>
		new Ajax.Autocompleter(
		  "srhOrganizationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOrganizationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhOrganizationCd', 'keypress', clearText.bindAsEventListener($('srhSectionName')), false);

		<%-- 発注者のauto complete --%>
		new Ajax.Autocompleter(
		  "srhTantoCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
		  {
		  	paramName : "code",
		    afterUpdateElement : setLoginLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhTantoCd', 'keypress', clearText.bindAsEventListener($('srhTantoNm')), false);

		<%-- 納入先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhLocationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/BuyingApprovalDeliveryForAutoComplete.do?op=searchBuyingApprovalDelivery",
		  {
		  	paramName : "code",
		    afterUpdateElement : setLocationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('srhLocationCd', 'keypress', clearText.bindAsEventListener($('srhLocationName')), false);

	}, false);

	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}
	<%-- 仕入先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhItemCd").value = getHiddenValue(li,"code");
	}
	<%-- 担当部署auto completeの選択後処理 --%>
	function setChargeOrganizationLabel(text, li) {
	    $("srhTantoSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
	    $("srhSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 発注者auto completeの選択後処理 --%>
	function setLoginLabel(text, li) {
	    $("srhTantoNm").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 納入先auto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
	      if (!g_lock) {
	        if (continueConfirm()) {
	          location.href=url;
	  		  g_lock = true;
	    }
      }
	}

	<%-- まとめ入力画面への遷移 --%>
	function toAcceptBuyingDetail(url, index) {
	    if (!g_lock) {
	        if (continueConfirm()) {
	        	var slipNo = $F("searchList[" + index + "].slipNo");
	          	location.href = url + slipNo;
	  			g_lock = true;
	    	}
      	}
	}
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/AcceptList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="buttonStatus" styleId="buttonStatus" />

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
							<td class="fcTitle fb" width="250">受入・仕入</td>
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
					<td><!-- 検索部 -->
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">発注番号</td>
									<td>
										<nested:text property="srhBuySubcontractOrderNo" maxlength="20" size="10" styleId="srhBuySubcontractOrderNo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">仕入番号</td>
									<td>
										<nested:text property="srhSlipNo" maxlength="20" size="10" styleId="srhSlipNo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">品目</td>
									<td>
										<nested:text property="srhItemCd" maxlength="20" size="15" styleId="srhItemCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
										<nested:text property="srhItemName" size="14" styleId="srhItemName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">仕入先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="10" size="10" styleId="srhVenderCd" />
										<nested:text property="srhVenderName" size="14" styleId="srhVenderName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">他社コード１</td>
									<td>
										<nested:text property="srhOtherCompanyCd1" maxlength="9" size="15" styleId="srhOtherCompanyCd1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">担当部署</td>
									<td>
										<nested:text property="srhChargeOrganizationCd" maxlength="10" size="10" styleId="srhChargeOrganizationCd" />
										<nested:text property="srhTantoSectionName" size="14" styleId="srhTantoSectionName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">納品希望日</td>
									<td>
										<nested:text property="srhSuggestedDeliverlimitDateFrom" maxlength="10" size="10" styleId="srhSuggestedDeliverlimitDateFrom" style="ime-mode:disabled" /> ～
										<nested:text property="srhSuggestedDeliverlimitDateTo" maxlength="10" size="10" styleId="srhSuggestedDeliverlimitDateTo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">受入日</td>
									<td>
										<nested:text property="srhAcceptDateFrom" maxlength="10" size="10" styleId="srhAcceptDateFrom" style="ime-mode:disabled" /> ～
										<nested:text property="srhAcceptDateTo" maxlength="10" size="10" styleId="srhAcceptDateTo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">部署</td>
									<td>
										<nested:text property="srhOrganizationCd" maxlength="10" size="10" styleId="srhOrganizationCd" />
										<nested:text property="srhSectionName" size="14" styleId="srhSectionName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">発注者</td>
									<td>
										<nested:text property="srhTantoCd" maxlength="10" size="10" styleId="srhTantoCd" />
										<nested:text property="srhTantoNm" size="14" styleId="srhTantoNm" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">オーダー区分</td>
									<td>
										<%
											pageContext.setAttribute("selectOrderDivision",
											new com.asahikaseieng.app.comboboxes.SelectOrderDivision(true, true));
										%>
										<nested:select property="srhOrderDivision" styleId="srhOrderDivision">
											<nested:options name="selectOrderDivision" property="values" labelName="selectOrderDivision" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">納入先</td>
									<td>
										<nested:text property="srhLocationCd" maxlength="10" size="10" styleId="srhLocationCd" />
										<nested:text property="srhLocationName" size="14" styleId="srhLocationName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">購買ステータス</td>
									<td>
										<%
											pageContext.setAttribute("selectPurchaseStatusAccept",
											new com.asahikaseieng.app.comboboxes.SelectPurchaseStatusAccept(true, true));
										%>
										<nested:select property="srhStatus" styleId="srhStatus">
											<nested:options name="selectPurchaseStatusAccept" property="values" labelName="selectPurchaseStatusAccept" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">仕入ステータス</td>
									<td>
										<%
											pageContext.setAttribute("selectStockingStatus",
											new com.asahikaseieng.app.comboboxes.SelectStockingStatus(true, true));
										%>
										<nested:select property="srhStatus2" styleId="srhStatus2">
											<nested:options name="selectStockingStatus" property="values" labelName="selectStockingStatus" labelProperty="labels" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="110">仕入区分</td>
									<td>
										<nested:select property="srhCategoryDivision" styleId="srhCategoryDivision">
											<nested:optionsCollection property="stockinDivisionCombo" label="labales" value="values" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">月次更新済み</td>
									<td>
										<nested:checkbox property="srhMonthlyUpdCheck" styleId="srhMonthlyUpdCheck" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignRight">
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td>
										<div id="cssButton"><a href="#"
											onclick="return check_form_submit('op', 'search'); return false;"
											class="cssButton"> &nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
										</a></div>
									</td>
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

				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td class="alignRight">
						<table cellspacing="1" cellpadding="1" border="0">
							<tr>
								<td>
									<div id="cssButton">
										<a href="#" onclick="if (!(reportConfirm())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
											帳票(EXCEL) 
										</a>
									</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
						<table width="" border="0">
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td rowspan="3" width="80"></td>
										<td colspan="2">発注番号</td>
										<td width="170">発注書NO</td>
										<td colspan="4">品目</td>
										<td width="160">購買ｽﾃｰﾀｽ</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="110">仕入番号</td>
										<td width="55">行No</td>
										<td width="170">仕入先受注番号</td>
										<td colspan="4">仕入先</td>
										<td width="160">仕入ｽﾃｰﾀｽ</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td colspan="2">受入日</td>
										<td width="170">納品希望日</td>
										<td width="130">発注数量</td>
										<td width="130">受入数量</td>
										<td width="50">荷姿</td>
										<td width="130">重量</td>
										<td width="160">分納</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

												<nested:define id="pno" property="purchaseNo" />
												<nested:define id="kbn" property="kbn" />
												<nested:define id="flg" property="nyukaFlg" />
												<nested:hidden property="status" styleId="<%="status" + pageContext.findAttribute("index").toString() %>"></nested:hidden>

												<td align="center">
													<nested:equal property="buttonStatus" value="1">
														<div id="cssButton">
															<a href='<%= request.getContextPath() + "/AcceptDetail.do?op=init&purchaseNo=" + pageContext.findAttribute("pno").toString() + "&kbn=" + pageContext.findAttribute("kbn").toString() + "&nyukaFlg=" + pageContext.findAttribute("flg").toString()%>'
																class="cssButton">受入</a>
														</div>
													</nested:equal>
													<nested:equal property="buttonStatus" value="0">
														<div id="cssButton">
															<a href="###" class="cssButton" disabled>受入</a>
														</div>
													</nested:equal>
												</td>
												<%-- 発注番号 --%><%-- 発注番号分納枝番 --%>
												<td colspan="2">
													<nested:equal property="buttonStatus" value="1">
														<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/AcceptDetail.do?op=init&purchaseNo=" + pageContext.findAttribute("pno").toString() + "&kbn=" + pageContext.findAttribute("kbn").toString() + "&nyukaFlg=" + pageContext.findAttribute("flg").toString() + "'); return false;"%>'>
															<nested:write property="buySubcontractOrderNo" />
															<nested:notEmpty property="orderDivideNo">
																-<nested:write property="orderDivideNo" />
															</nested:notEmpty>
														</html:link>
													</nested:equal>
													<nested:equal property="buttonStatus" value="0">
														<nested:write property="buySubcontractOrderNo" />
														<nested:notEmpty property="orderDivideNo">
															- <nested:write property="orderDivideNo" />
														</nested:notEmpty>
													</nested:equal>
												</td>
												<%-- 発注書NO --%>
												<td>
													<nested:write property="orderSheetNo" />
												</td>
												<%-- 品目 --%>
												<td colspan="4">
													<nested:write property="itemQueueName" />
												</td>
												<%-- 購買ステータス --%>
												<td>
													<nested:write property="strStatus" />
												</td>
											</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

												<td align="center">
													<div id="cssButton">
													<nested:lessEqual property="minStatus" value="5">
														<a href="###" class="cssButton" disabled>仕入</a>
													</nested:lessEqual>
													<nested:equal property="minStatus" value="6">
														<a href="#"
															onclick="<%="toAcceptBuyingDetail('" + request.getContextPath() + "/AcceptBuyingDetail.do?op=init&slipNo='," + index + "); return false;"%>"
															class="cssButton">仕入</a>
													</nested:equal>
													</div>
												</td>
												<%-- 仕入番号 --%>
												<td>
													<nested:write property="slipNo" />
													<nested:hidden property="slipNo" styleId="slipNo" />
												</td>
												<%-- 行番号 --%>
												<td align="right">
													<nested:notEqual property="rowNo" value="0">
														<nested:write property="rowNo" />
													</nested:notEqual>
												</td>
												<%-- 仕入先受注番号 --%>
												<td>
													<nested:write property="siOrderNo" />
												</td>
												<%-- 仕入先 --%>
												<td colspan="4">
													<nested:write property="venderShortedName" />
												</td>
												<%-- 仕入ステータス --%>
												<td>
													<nested:write property="strStatus2" />
												</td>
											</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

												<td></td>	
												<%-- 受入日 --%>
												<td colspan="2">
													<nested:write property="strAcceptDate" />
												</td>
												<%-- 納品希望日 --%>
												<td>
													<nested:write property="strSuggestedDeliverlimitDate" />
												</td>
												<%-- 発注数量 --%>
												<td align="right">
													<nested:write property="strOrderQuantity" />
												</td>
												<%-- 受入数量 --%>
												<td align="right">
													<nested:write property="strAcceptQuantity" />
												</td>
												<%-- 荷姿 --%>
												<td>
													<nested:write property="styleOfPacking" />
												</td>
												<%-- 重量 --%>
												<td align="right">
													<nested:write property="strOrderConvertQuantity" />
												</td>
												<%-- 分納区分 --%>
												<td>
													<nested:write property="strReplyContentsDivision" />
												</td>

											</tr>
									</nested:iterate>
								</table>
								</td>
							</tr>
						</table>
						</td>
					<tr>
						<td class="alignCenter">
							<%-- ページング --%>
							<%@ include file="/jsp/common/pager/pager.jsf"%>
							<%-- ページング ここまで --%>
						</td>
					</tr>
				</table>
			</nested:notEmpty>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelReportDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>
</body>
</html:html>

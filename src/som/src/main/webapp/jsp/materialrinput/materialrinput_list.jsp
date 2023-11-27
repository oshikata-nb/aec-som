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

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
	      if (!g_lock) {
	        if (continueConfirm()) {
	          location.href=url;
	  g_lock = true;
	    }
      }
	}

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		<logic:equal name="materialRinputListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhOrderDateFrom");
		defineAsDateField("srhOrderDateTo");
		defineAsDateField("srhSuggestedDeliverlimitDateFrom");
		defineAsDateField("srhSuggestedDeliverlimitDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 数値型フィールド定義 --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				<%-- ラベル数 --%>
				for (i = 0; null != $("labelCount" + i); i++) {
					defineAsNumberField("labelCount" + i);
				}
				<%-- 入荷予定数量 --%>
				for (i = 0; null != $("status" + i); i++) {
					if ($("status" + i).value < 5) {
						defineAsNumberField("strArrivalQuantity" + i);
					}
				}
			}
		}

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
		Event.observe('srhOrganizationCd', 'keypress', clearText.bindAsEventListener($('srhOrganizationName')), false);

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
		Event.observe('srhChargeOrganizationCd', 'keypress', clearText.bindAsEventListener($('srhChargeOrganizationName')), false);

		<%-- 検索後入力された場合の不整合をただす --%>
		<%-- storeInitValues(/^srh.*/, 'dirtyFlg', 'notInputFlg'); --%>
		refreshLabel();

	}, false);

	<%-- 納入先auto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 仕入先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhItemCd").value = getHiddenValue(li,"code");
	}
	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
	    $("srhOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 発注者auto completeの選択後処理 --%>
	function setLoginLabel(text, li) {
	    $("srhTantoNm").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 担当部署auto completeの選択後処理 --%>
	function setChargeOrganizationLabel(text, li) {
	    $("srhChargeOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
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

<nested:form action="/MaterialRinputList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

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
							<td class="fcTitle fb" width="250">外注原材料投入実績</td>
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
									<td class="bcTitleSearch fb fcTitleSearch" width="100">発注番号</td>
									<td>
										<nested:text property="srhBuySubcontractOrderNo" maxlength="20" size="15" styleId="srhBuySubcontractOrderNo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">発注日</td>
									<td>
										<nested:text property="srhOrderDateFrom" maxlength="10" size="10" styleId="srhOrderDateFrom" style="ime-mode:disabled" /> ～
										<nested:text property="srhOrderDateTo" maxlength="10" size="10" styleId="srhOrderDateTo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">納品希望日</td>
									<td>
										<nested:text property="srhSuggestedDeliverlimitDateFrom" maxlength="10" size="10" styleId="srhSuggestedDeliverlimitDateFrom" style="ime-mode:disabled" /> ～
										<nested:text property="srhSuggestedDeliverlimitDateTo" maxlength="10" size="10" styleId="srhSuggestedDeliverlimitDateTo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">納入先</td>
									<td>
										<nested:text property="srhLocationCd" maxlength="10" size="10" styleId="srhLocationCd" />
										<nested:text property="srhLocationName" size="14" styleId="srhLocationName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">仕入先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="10" size="15" styleId="srhVenderCd" />
										<nested:text property="srhVenderName" size="14" styleId="srhVenderName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">品目</td>
									<td>
										<nested:text property="srhItemCd" maxlength="20" size="15" styleId="srhItemCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
										<nested:text property="srhItemName" size="14" styleId="srhItemName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">他社コード１</td>
									<td>
										<nested:text property="srhOtherCompanyCd1" maxlength="9" size="10" styleId="srhOtherCompanyCd1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">部署</td>
									<td>
										<nested:text property="srhOrganizationCd" maxlength="10" size="10" styleId="srhOrganizationCd" />
										<nested:text property="srhOrganizationName" size="14" styleId="srhOrganizationName" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">発注者</td>
									<td>
										<nested:text property="srhTantoCd" maxlength="10" size="10" styleId="srhTantoCd" />
										<nested:text property="srhTantoNm" size="14" styleId="srhTantoNm" styleClass="noborderAl" readonly="true" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">発注書NO</td>
									<td>
										<nested:text property="srhOrderSheetNo" maxlength="10" size="10" styleId="srhOrderSheetNo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">担当部署</td>
									<td>
										<nested:text property="srhChargeOrganizationCd" maxlength="10" size="10" styleId="srhChargeOrganizationCd" />
										<nested:text property="srhChargeOrganizationName" size="14" styleId="srhChargeOrganizationName" styleClass="noborderAl" readonly="true" tabindex="-1" />
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
										<nested:checkbox property="notInputFlg" styleId="notInputFlg" onclick="" >未入力のみ</nested:checkbox>
									</td>
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
										<td colspan="2" width="170">発注番号</td>
										<td colspan="2" width="170">発注書NO</td>
										<td width="80">発注日</td>
										<td width="130">仕入先</td>
										<td width="130">納入先</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="120">数量</td>
										<td width="50">単位</td>
										<td width="50">荷姿</td>
										<td width="120">重量</td>
										<td width="80">納品希望日</td>
										<td colspan="2">品目</td>
									</tr>
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<nested:define id="bsono" property="buySubcontractOrderNo" />

												<%-- 発注番号 --%>
												<td colspan="2">
													<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/MaterialRinputDetail.do?op=init&buySubcontractOrderNo=" + pageContext.findAttribute("bsono").toString() + "'); return false;"%>'>
														<nested:write property="buySubcontractOrderNo" />
													</html:link>
												</td>
												<%-- 発注書NO --%>
												<td colspan="2">
													<nested:write property="orderSheetNo" />
												</td>
												<%-- 発注日 --%>
												<td>
													<nested:write property="strOrderDate" />
												</td>
												<%-- 仕入先 --%>
												<td>
													<nested:write property="venderShortedName" />
												</td>
												<%-- 納入先 --%>
												<td>
													<nested:write property="locationName" />
												</td>
											</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

												<%-- 発注数量 --%>
												<td align="right">
													<nested:write property="strOrderQuantity" />
												</td>
												<%-- 単位 --%>
												<td>
													<nested:write property="unit" />
												</td>
												<%-- 荷姿 --%>
												<td>
													<nested:write property="styleOfPacking" />
												</td>
												<%-- 重量 --%>
												<td align="right">
													<nested:write property="strOrderConvertQuantity" />
												</td>
												<%-- 納品希望日 --%>
												<td>
													<nested:write property="strSuggestedDeliverlimitDate" />
												</td>
												<%-- 品目 --%>
												<td colspan="2">
													<nested:write property="itemName" />
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

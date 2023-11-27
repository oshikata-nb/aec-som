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

	<%-- 発注詳細画面への遷移 --%>
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
		<logic:equal name="purchaseOrderListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 納入先のauto complete --%>
		new Ajax.Autocompleter(
		  "srhLocationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/PurchaseOrderDeliveryForAutoComplete.do?op=searchPurchaseOrderDelivery",
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
		Event.observe('srhVenderCd', 'keypress', clearText.bindAsEventListener($('srhSupplierName')), false);

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
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhTashaCd')), false);

		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "srhTashaCd",
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
		Event.observe('srhTantoCd', 'keypress', clearText.bindAsEventListener($('srhTantoName')), false);

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

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhOrderDateFrom");
		defineAsDateField("srhOrderDateTo");
		defineAsDateField("srhSuggestedDeliverlimitDateFrom");
		defineAsDateField("srhSuggestedDeliverlimitDateTo");

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);
	
	
	<%-- チェックボックス　全設定--%> 
	function allCheck(flg) {
        for (var i=0; i < $("purchaseApprovalListLength").value; i++) {
        	$("searchList[" + i + "].orderCheckBox").checked = flg;
        }
	}
	
	<%-- 納入先auto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 仕入先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhSupplierName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhTashaCd").value = getHiddenValue(li,"otherCompanyCd1");
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhItemCd").value = getHiddenValue(li,"code");
	}
	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
	    $("srhSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 発注者auto completeの選択後処理 --%>
	function setLoginLabel(text, li) {
	    $("srhTantoName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 担当部署auto completeの選択後処理 --%>
	function setChargeOrganizationLabel(text, li) {
	    $("srhTantoSectionName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
	  var count = 0;
	  var item = document.purchaseOrderListForm.orderCheckBox;
	  var len = item.length;

	  // 明細１件のみの場合
	  if (len == undefined) {
	    if(item.disabled == false){
	      if(item.checked){
		    count = count + 1;
          }
		}
	  } else {
	    for (var i = 0; i < len; i++) {
          if(item[i].checked){
		    count = count + 1;
		    break;
		  }
	    }
	  }
	  if (count == 0) {
        alert("発注書発行対象が選択されていません。");
	    return false;
	  }
	  return true;
	}
	
	<%-- 発注書発行メッセージ --%>
	function issueOrderAlert() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "発注書を発行してよろしいですか？";
		return confirm(alertMsg[0]);
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
<nested:form action="/PurchaseOrderList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="location" styleId="location" /><%-- まとめ処理区分 --%>
	<nested:hidden property="searchType" styleId="searchType" />
	

	<table cellpadding="0" cellspacing="0" border="0" width="750">
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
							<td class="fcTitle fb" width="250">発注書発行</td>
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
							<table border="0" cellspacing="1" cellpadding="1">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">発注番号</td>
									<td width="40%">
										<nested:text property="srhBuySubcontractOrderNo" maxlength="20" size="10" styleId="srhBuySubcontractOrderNo" style="ime-mode:disabled"/>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">購買ステータス</td>
									<td>
										<%
											pageContext.setAttribute("selectPurchaseStatus",
											new com.asahikaseieng.app.comboboxes.SelectPurchaseStatusOrder(true, true));
										%>
										<nested:select property="srhStatus" styleId="srhStatus">
										<nested:options name="selectPurchaseStatus" property="values" labelName="selectPurchaseStatus" labelProperty="labels"/>
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">発注日</td>
									<td>
										<nested:text property="srhOrderDateFrom"
											maxlength="8" size="10" styleId="srhOrderDateFrom" style="ime-mode:disabled" />
										～					
										<nested:text property="srhOrderDateTo"
										maxlength="8" size="10" styleId="srhOrderDateTo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">納品希望日</td>
									<td>
										<nested:text property="srhSuggestedDeliverlimitDateFrom"
											maxlength="8" size="10" styleId="srhSuggestedDeliverlimitDateFrom" style="ime-mode:disabled"/>
										～					
										<nested:text property="srhSuggestedDeliverlimitDateTo"
										maxlength="8" size="10" styleId="srhSuggestedDeliverlimitDateTo" style="ime-mode:disabled"/>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">納入先</td>
									<td>
										<nested:text property="srhLocationCd" maxlength="20" size="10" styleId="srhLocationCd" />
									<!-- 納入先名称 -->
										<nested:text property="srhLocationName" maxlength="30" size="20" readonly="true" styleId="srhLocationName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">仕入先</td>
									<td>
										<nested:text property="srhVenderCd" maxlength="15" size="10" styleId="srhVenderCd" />
									<!-- 仕入先名称 -->
										<nested:text property="srhSupplierName" maxlength="30" size="16" readonly="true" styleId="srhSupplierName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">品目名</td>
									<td>
										<nested:text property="srhItemCd" maxlength="20" size="10" styleId="srhItemCd" />
									<!-- 品目名称 -->
										<div id="autocomplete_selection" class="autocomplete"></div>
										<nested:text property="srhItemName" maxlength="40" size="20" readonly="true" styleId="srhItemName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">他社コード１</td>
									<td>
										<nested:text property="srhTashaCd" maxlength="20" size="10" styleId="srhTashaCd" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">部署</td>
									<td>
										<nested:text property="srhOrganizationCd" maxlength="10" size="10" styleId="srhOrganizationCd" />
									<!-- 部署名称 -->
										<nested:text property="srhSectionName" maxlength="60" size="20" readonly="true" styleId="srhSectionName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">発注者</td>
									<td>
										<nested:text property="srhTantoCd" maxlength="10" size="10" styleId="srhTantoCd" />
									<!-- 発注者名 -->
										<nested:text property="srhTantoName" maxlength="20" size="16" readonly="true" styleId="srhTantoName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">オーダ区分</td>
									<td width="230">
										<%
											pageContext.setAttribute("selectOrderDivision",
											new com.asahikaseieng.app.comboboxes.SelectOrderDivision(true, true));
										%>
										<nested:select property="srhOrderDivision" styleId="srhOrderDivision">
											<nested:options name="selectOrderDivision" property="values" labelName="selectOrderDivision" labelProperty="labels"/>
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">発注書Ｎｏ</td>
									<td>
										<nested:text property="srhOrderSheetNo" maxlength="20" size="10" styleId="srhOrderSheetNo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">担当部署</td>
									<td>
										<nested:text property="srhChargeOrganizationCd" maxlength="10" size="10" styleId="srhChargeOrganizationCd" />
										<!-- 担当部署名称 -->
										<nested:text property="srhTantoSectionName" maxlength="60" size="20" readonly="true" styleId="srhTantoSectionName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
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
											onclick="return form_submit('op', 'searchAlarm'); return false;"
											class="cssButton"> &nbsp;&nbsp;アラーム検索&nbsp;&nbsp;
										</a></div>
									</td>
									<td>
										<div id="cssButton"><a href="#"
											onclick="return form_submit('op', 'search'); return false;"
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
			<nested:hidden property="purchaseApprovalListLength"></nested:hidden>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<table width="100%" cellspacing="0" cellpadding="0">
								<tr>
									<td class="alignLeft">
									<table cellspacing="1" cellpadding="1" border="0">
										<tr>
											<td>
												<div id="cssButton">
													<a href="#" onclick="allCheck(true); return false;" class="cssButton"> &nbsp;&nbsp;全チェック&nbsp;&nbsp;</a>
												</div>
											</td>
											<td>
												<div id="cssButton">
													<a href="#" onclick="allCheck(false); return false;" class="cssButton"> &nbsp;&nbsp;全チェック解除&nbsp;&nbsp;</a>
												</div>
											</td>
											<td>
												<nested:equal property="searchType" value="2">
													アラーム検索結果一覧を表示しています。
												</nested:equal>
											</td>
										</tr>
									</table>
									</td>
									<td class="alignRight">
									<table cellspacing="1" cellpadding="1" border="0">
										<tr>
											<td>
												<div id="cssButton">
													<a href="#" onclick="if (!(reportConfirm())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">帳票(EXCEL)</a>
												</div>
											</td>
										</tr>
									</table>
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
								<table cellspacing="1" cellpadding="1" id="tblList">
									<%-- 1段目 --%>
									<tr class="bcTitleList fb fcTitleList">
										<td rowspan="2" width="10"></td>
										<td colspan="2">発注番号</td>
										<td colspan="2">発注書Ｎｏ</td>
										<td>発注日</td>
										<td>仕入先</td>
										<td>品目</td>
									</tr>
									<%-- 2段目 --%>
									<tr class="bcTitleList fb fcTitleList">	
										<td>数量</td>
										<td>単位</td>
										<td>荷姿</td>
										<td>重量</td>
										<td>納品希望日</td>
										<td>納入先</td>
										<td>ｽﾃｰﾀｽ</td>
									</tr>
									
									<%-- 1段目 --%>
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<nested:define id="pno" property="purchaseNo" />

										<%--チェックボックス --%>
										<td rowspan="2" width="10">
											<nested:checkbox property="orderCheckBox" styleId="orderCheckBox" />
										</td>
										<%-- 発注番号 --%>
										<td width="80" colspan="2">
											<nested:write property="buySubcontractOrderNo" />
										</td>
										<%-- 発注書NO --%>
										<td width="140" colspan="2">
												<nested:write property="orderSheetNo" />
										</td>
										<%-- 発注日 --%>
										<td width="120">
												<nested:write property="strOrderDate" />
										</td>
										<%-- 仕入先名称--%>
										<td width="200">
												<nested:write property="supplierName" />
										</td>
										<%-- 品目 --%>
										<td width="200">
												<nested:write property="itmName" />
										</td>
									</tr>
									<%-- 2段目 --%>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
										<%-- 発注数量 --%>
										<td width="75" align="right">
												<nested:write property="strOrderQuantity" />
										</td>
										<%-- 単位 --%>
										<td width="70">
												<nested:write property="orderUnit" />
										</td>
										<%-- 荷姿 --%>
										<td width="70">
												<nested:write property="loadForm" />
										</td>
										<%-- 重量 --%>
										<td width="70" align="right">
												<nested:write property="strOrderConvertQuantity" />
										</td>
										<%-- 納品希望日 --%>
										<td width="150">
												<nested:write property="strSuggestedDeliverlimitDate" />
										</td>
										<%-- 納入先名称 --%>
										<td width="150">
												<nested:write property="deliveryName" />
										</td>
										<%-- ステータス --%>
										<td width="150">
												<nested:write property="strStatus" />
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
							<%@ include file="/jsp/common/pager/pager.jsf" %>
							<%-- ページング ここまで --%>
						</td>
					</tr>
					<tr>
						<td>
						<table bdelivery="0" cellpadding="2" cellspacing="5" width="750">
							<tr>
								<td class="alignCenter">
									発注書印刷ON<nested:checkbox property="printCheckBox" />
									<a href="#"	onclick="if (!(issueOrderAlert())) {return false;}else{return form_submit('op', 'order'); return false;}" class="cssButton">
									発注書</a>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
			</nested:notEmpty>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelReportDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
</nested:form>
</body>
</html:html>

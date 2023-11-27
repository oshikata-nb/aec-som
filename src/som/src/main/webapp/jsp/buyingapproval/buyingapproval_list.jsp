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
		<logic:equal name="buyingApprovalListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

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
		Event.observe('srhTantoCd', 'keypress', clearText.bindAsEventListener($('srhTantoName')), false);

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

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhStockingDateFrom");
		defineAsDateField("srhStockingDateTo");
		defineAsDateField("srhAcceptDateFrom");	
		defineAsDateField("srhAcceptDateTo");
		
		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/, 'srhPostId');
		}

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- チェックボックス　全設定 --%>
	function allCheck(flg) {
        for (var i=0; i < $("buyingApprovalListLength").value; i++) {
        	$("searchList[" + i + "].approvalCheckBox").checked = flg;
        }
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
	  var count = 0;
	  var item = document.buyingApprovalListForm.approvalCheckBox;
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
	    if (index == 1) {
	      alert("承認対象が選択されていません。");
	    } else {
	      alert("否認対象が選択されていません。");
	    }
	    return false;
	  }
	  return true;
	}
	
	<%-- 確認メッセージ --%>
	function issueApprovalAlert() {
		if (!noCheck(1)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "承認してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 確認メッセージ --%>
	function issueDenyAlert() {
		if (!noCheck(2)) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "否認してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 仕入先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 担当部署auto completeの選択後処理 --%>
	function setChargeOrganizationLabel(text, li) {
	    $("srhChargeOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
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
	    $("srhTantoName").value = li.getElementsByTagName('span')[0].innerText;
	}
	<%-- 納入先auto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
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

<nested:form action="/BuyingApprovalList" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb" width="250">仕入承認一覧</td>
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
									<td class="bcTitleSearch fb fcTitleSearch" width="80">発注番号</td>
									<td width="100">
										<nested:text property="srhBuySubcontractOrderNo" maxlength="20" size="10" styleId="srhBuySubcontractOrderNo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="80">仕入番号</td>
									<td width="100">
										<nested:text property="srhSlipNo" maxlength="20" size="10" styleId="srhSlipNo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">受入実績日</td>
									<td width="230">
										<nested:text property="srhAcceptDateFrom" maxlength="8" size="8" styleId="srhAcceptDateFrom" style="ime-mode:disabled" />
										～
										<nested:text property="srhAcceptDateTo" maxlength="8" size="8" styleId="srhAcceptDateTo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">仕入日</td>
									<td width="230">
										<nested:text property="srhStockingDateFrom" maxlength="8" size="8" styleId="srhStockingDateFrom" style="ime-mode:disabled" />
										～
										<nested:text property="srhStockingDateTo" maxlength="8" size="8" styleId="srhStockingDateTo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="90">仕入先</td>
									<td width="310">
										<nested:text property="srhVenderCd" maxlength="15" size="10" styleId="srhVenderCd" />
										<nested:text property="srhVenderName" maxlength="30" size="16" readonly="true" styleId="srhVenderName" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="90">担当部署</td>
									<td width="310">
										<nested:text property="srhChargeOrganizationCd" maxlength="10" size="10" styleId="srhChargeOrganizationCd" />
										<nested:text property="srhChargeOrganizationName" maxlength="60" size="16" readonly="true" styleId="srhChargeOrganizationName" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="90">品目名</td>
									<td width="310">
										<nested:text property="srhItemCd" maxlength="20" size="10" styleId="srhItemCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
										<nested:text property="srhItemName" maxlength="40" size="16" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="90">他社コード１</td>
									<td width="310">
										<nested:text property="srhOtherCompanyCd1" maxlength="20" size="10" styleId="srhOtherCompanyCd1" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="150">オーダ区分</td>
									<td>
										<% pageContext.setAttribute("selectOrderDivision",	new com.asahikaseieng.app.comboboxes.SelectOrderDivision(true, true));	%>
										<nested:select property="srhOrderDivision">
											<nested:options name="selectOrderDivision" property="values" labelName="selectOrderDivision" labelProperty="labels"/>
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="150">発注書No</td>
									<td width="100">
										<nested:text property="srhOrderSheetNo" maxlength="20" size="10" styleId="srhOrderSheetNo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="90">部署</td>
									<td width="310">
										<nested:text property="srhOrganizationCd" maxlength="10" size="10" styleId="srhOrganizationCd" />
										<nested:text property="srhOrganizationName" maxlength="60" size="16" readonly="true" styleId="srhOrganizationName" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="90">発注者</td>
									<td width="310">
										<nested:text property="srhTantoCd" maxlength="10" size="10" styleId="srhTantoCd" style="ime-mode:disabled" />
										<nested:text property="srhTantoName" maxlength="20" size="16" readonly="true" styleId="srhTantoName" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="120">仕入区分</td>
									<td>
										<nested:select property="srhCategoryDivision" style="margin: 0;padding: 0" >
											<nested:optionsCollection property="stockinDivisionCombo" label="labales" value="values" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">取消</td>
									<td>
										<nested:checkbox property="srhCancelCheck" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="120">納入先</td>
									<td width="500" colspan="4">
										<nested:text property="srhLocationCd" maxlength="20" size="10" styleId="srhLocationCd" style="ime-mode:disabled" />
										<nested:text property="srhLocationName" maxlength="30" size="40" readonly="true" styleId="srhLocationName" styleClass="noborderAl" tabindex="-1" />
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
				<%-- 1ページあたりの検索結果件数 --%>
				<nested:hidden property="buyingApprovalListLength" />
				
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
										</tr>
									</table>
									</td>
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
										<td width="10" rowspan="2"></td>
										<td width="70">発注番号</td>
										<td width="71">発注書No</td>
										<td width="76" colspan="3">仕入番号</td>
										<%-- <td width="12">行No</td> --%>
										<td width="45">単価</td>
										<td width="120" colspan="3">品目</td>
										<td width="70">仕入区分</td>
										<td width="70">受入日</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="70">発注数量</td>
										<td width="71">仕入数量</td>
										<td width="45">重量</td>
										<td width="2" colspan="2">荷姿</td>
										<td width="45">金額</td>
										<td width="70">仕入日</td>
										<td width="70">仕入先</td>
										<td width="90">仕入 ｽﾃｰﾀｽ</td>
										<td width="70">支払先 ｺｰﾄﾞ</td>
										<td width="90">支払先</td>
									</tr>
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

											<%--チェックボックス --%>
											<td rowspan="2">
												<nested:checkbox property="approvalCheckBox"  styleId="approvalCheckBox" />
											</td>
											
											<%-- 発注番号 --%>
											<td>
													<%-- 受入仕入のデータの場合のみ表示 --%>
													<nested:notEmpty property="buySubcontractOrderNo">
														<nested:write property="buySubcontractOrderNo" />
													</nested:notEmpty>
											</td>
											
											<%-- 発注書NO --%>
											<td width="71">
													<%-- 受入仕入のデータの場合のみ表示 --%>
													<nested:notEmpty property="buySubcontractOrderNo">
														<nested:write property="orderSheetNo" />
													</nested:notEmpty>
											</td>
											
											<%-- 仕入番号 --%>
											<td width="76" colspan="3">
													<nested:write property="slipNo" />
											</td>
											
											<%-- 行番号
											<td width="12">
													<nested:write property="rowNo" />
											</td> --%>
											
											<%-- 仕入単価 --%>
											<td class="alignRight" width="45">
													<nested:write property="strHousingUnitprice" />
											</td>
											
											<%-- 品目 --%>
											<td colspan="3">
													<nested:write property="itemQueueName" />
											</td>
											
											<%-- 仕入区分 --%>
											<td>
													<nested:write property="categoryName" />
											</td>
											
											<%-- 受入日付 --%>
											<td>
													<%-- 受入仕入のデータの場合のみ表示 --%>
													<nested:notEmpty property="buySubcontractOrderNo">
														<nested:write property="strAcceptDate" />
													</nested:notEmpty>
											</td>
											
										</tr>
										
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											<%-- 発注数量 --%>
											<td class="alignRight">
													<%-- 受入仕入のデータの場合のみ表示 --%>
													<nested:notEmpty property="buySubcontractOrderNo">
														<nested:write property="strOrderQuantity" />
													</nested:notEmpty>
											</td>
											
											<%-- 受入数量（仕入数量） --%>
											<td class="alignRight" width="71">
													<nested:write property="strStockingQuantity" />
											</td>
											
											<%-- 重量 --%>
											<td class="alignRight">
													<nested:write property="strOrderConvertQuantity" />
											</td>
											
											<%-- 荷姿 --%>
											<td class="alignRight" colspan="2" width="2">
													<nested:write property="styleOfPacking" />
											</td>
											
											<%-- 仕入金額 --%>
											<td class="alignRight" width="45">
													<nested:write property="strStockingAmount" />
											</td>
											
											<%-- 仕入日付 --%>
											<td>
													<nested:write property="strStockingDate" />
											</td>
											
											<%-- 仕入先 --%>
											<td>
													<nested:write property="venderName1" />
											</td>
											
											<%-- 仕入ステータス --%>
											<td>
													<nested:write property="strStatus2" />
											</td>
											
											<%-- 支払先ｺｰﾄﾞ --%>
											<td>
													<nested:write property="paymentInvoiceCd" />
											</td>
											
											<%-- 支払先 --%>
											<td>
													<nested:write property="venderPaymentName" />
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
				<table cellpadding="0" cellspacing="0" width="100%" border="0">
				<tr>
					<nested:equal property="approvalAuthority" value="true">
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onclick="if (!(issueApprovalAlert())) {return false;}else{return form_submit('op', 'approval'); return false;}" class="cssButton">
								&nbsp;&nbsp;承&nbsp;&nbsp;認&nbsp;&nbsp;
								</a>
							</div>
						</td>
					</nested:equal>
					<nested:equal property="negationAuthority" value="true">
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onclick="if (!(issueDenyAlert())) {return false;}else{return form_submit('op', 'deny'); return false;}" class="cssButton">
								&nbsp;&nbsp;否&nbsp;&nbsp;認&nbsp;&nbsp;
								</a>
							</div>
						</td>
					</nested:equal>
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

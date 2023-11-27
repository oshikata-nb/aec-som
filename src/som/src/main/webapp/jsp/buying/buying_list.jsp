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
		<logic:equal name="buyingListForm" property="searchList" value="false">
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
		
		<%-- 担当者のauto complete --%>
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

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhStockingDateFrom");
		defineAsDateField("srhStockingDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		}

		refreshLabel();

	}, false);
	
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
	<%-- 担当者auto completeの選択後処理 --%>
	function setLoginLabel(text, li) {
	    $("srhTantoName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 仕入伝票発行確認メッセージ --%>
	function putConfirm() {
		if (!noCheck()) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "仕入伝票を発行してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
	<%-- 全チェック --%>
	function allCheck(checked){
		var count = $F("count");
		for(var i = 0 ; i < count ; i++){
			var target = $("searchList[" + i + "].slipBuyingCheckBox");
			if(target != null){
				target.checked = checked;
			}
		}
		return false;
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var count = $F("count");
		var checkFlag = false;
		for (var i = 0; i < count; i++) {
			var target = $("searchList[" + i + "].slipBuyingCheckBox");
			if(target != null && target.checked){
				checkFlag = true;
				break;
			}
		}
		if (!checkFlag) {
		    alert("仕入伝票発行対象が選択されていません。");
		    return false;
		}
		return true;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/BuyingList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="count" styleId="count" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><%-- ヘッダー部 --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb" width="250">仕入</td>
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
					<td><%-- 検索部 --%>
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<%-- 仕入番号 --%>							
									<td class="bcTitleSearch fb fcTitleSearch" width="80">仕入番号</td>
									<td width="100">
										<nested:text property="srhSlipNo" maxlength="20" size="10" styleId="srhSlipNo" />
									</td>
					
									<%-- 仕入日 --%>	
									<td class="bcTitleSearch fb fcTitleSearch" width="100">仕入日</td>
									<td width="230">
										<nested:text property="srhStockingDateFrom" maxlength="8" size="7" styleId="srhStockingDateFrom" style="ime-mode:disabled" />
										～
										<nested:text property="srhStockingDateTo" maxlength="8" size="7" styleId="srhStockingDateTo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<%-- 仕入先--%>									
									<td class="bcTitleSearch fb fcTitleSearch" width="90">仕入先</td>
									<td width="310">
										<nested:text property="srhVenderCd" maxlength="15" size="10" styleId="srhVenderCd" />
										<nested:text property="srhVenderName" maxlength="30" size="18" readonly="true" styleId="srhVenderName" styleClass="noborderAl" tabindex="-1" />
									</td>
									
									<%-- 担当部署--%>		
									<td class="bcTitleSearch fb fcTitleSearch" width="90">担当部署</td>
									<td width="310">
										<nested:text property="srhChargeOrganizationCd" maxlength="10" size="10" styleId="srhChargeOrganizationCd" />
										<nested:text property="srhChargeOrganizationName" maxlength="60" size="18" readonly="true" styleId="srhChargeOrganizationName" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								
								<tr>
									<%-- 品目名 --%>							
									<td class="bcTitleSearch fb fcTitleSearch" width="90">品目名</td>
									<td width="310">
										<nested:text property="srhItemCd" maxlength="20" size="10" styleId="srhItemCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
											
										<nested:text property="srhItemName" maxlength="40" size="18" readonly="true" styleId="srhItemName" styleClass="noborderAl" onchange="setDirtyFlg();" tabindex="-1" />
									</td>
									
									<%-- 他社コード１ --%>		
									<td class="bcTitleSearch fb fcTitleSearch" width="90">他社コード１</td>
									<td width="310">
										<nested:text property="srhOtherCompanyCd1" maxlength="20" size="10" styleId="srhOtherCompanyCd1" />
									</td>
									
								</tr>
								
								<tr>
									<%-- 部署(オートコンプリート) --%>		
									<td class="bcTitleSearch fb fcTitleSearch" width="90">部署</td>
									<td width="310">
										<nested:text property="srhOrganizationCd" maxlength="10" size="10" styleId="srhOrganizationCd" />
										<nested:text property="srhOrganizationName" maxlength="60" size="18" readonly="true" styleId="srhOrganizationName" styleClass="noborderAl" tabindex="-1" />
									</td>
									
									<%--担当者(オートコンプリート) --%>		
									<td class="bcTitleSearch fb fcTitleSearch" width="90">担当者</td>
									<td width="310">
										<nested:text property="srhTantoCd" maxlength="10" size="10" styleId="srhTantoCd" />
										<nested:text property="srhTantoName" maxlength="20" size="18" readonly="true" styleId="srhTantoName" styleClass="noborderAl"  tabindex="-1" />
									</td>
								</tr>
								
								<tr>
									<%-- 分類コード区分 --%>		
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
									<%-- 仕入ステータス --%>		
									<td class="bcTitleSearch fb fcTitleSearch" width="125">仕入ステータス</td>
									<td>
										<%
											pageContext.setAttribute("selectStockingStatus",
											new com.asahikaseieng.app.comboboxes.SelectStockingStatus(true, true));
										%>
										<nested:select property="srhStockingStatus" styleId="srhStockingStatus">
											<nested:options name="selectStockingStatus" property="values" labelName="selectStockingStatus" labelProperty="labels"/>
										</nested:select>
									</td>
									<%-- 月次更新済み --%>		
									<td class="bcTitleSearch fb fcTitleSearch">月次更新済</td>
									<td>
										<nested:checkbox property="srhMonthlyCheck" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">仕入伝票未発行</td>
									<td>
										<nested:checkbox property="srhSlipIssueDivision" styleId="srhSlipIssueDivision"/>
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
									<td>
										<div id="cssButton">
										<a href='<%= request.getContextPath() + "/BuyingDetail.do?op=initNew"%>'
											class="cssButton"> &nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
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
						<td>
							<table width="100%" cellspacing="0" cellpadding="0">
								<tr>
									<td class="alignLeft">
									<table cellspacing="1" cellpadding="1" border="0">
										<tr>
											<td>
												<div id="cssButton">
													<a href="#" onclick="return allCheck(true);" class="cssButton">
														&nbsp;&nbsp;全チェック&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td>
												<div id="cssButton">
													<a href='#' onclick="return allCheck(false);" class="cssButton">
														&nbsp;&nbsp;全チェック解除&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</tr>
									</table>
									</td>
									<td class="alignRight">
									<table cellspacing="1" cellpadding="1" border="0">
											<tr>
												<td>
													<div id="cssButton"><a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'buyingReport'); return false;}" class="cssButton">仕入伝票</a></div>
												</td>
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
						<td><%-- 明細部 --%>
						<table width="" border="0">
							<tr>
								<td class="alignCenter">

							<tr>

								<td class="alignCenter">
								<table cellspacing="1" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="20" rowspan="2"></td>
										<td width="80">仕入番号</td>
										<td width="80">仕入区分</td>
										<td width="200" colspan="2">仕入先</td>
										<td width="300" colspan="3">品目</td>
										<td width="60" rowspan="2">発行済</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td width="40">数量</td>
										<td width="40">部署</td>
										<td width="100">担当者</td>
										<td width="100">仕入日</td>
										<td width="100">単価</td>
										<td width="120">金額</td>
										<td width="105">仕入ステータス</td>
									</tr>
												
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										
										<%-- 1段目 --%>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

											<%-- チェックボックス --%>
											<td rowspan="2">
												<nested:checkbox property="slipBuyingCheckBox" styleId="<%="slipBuyingCheckBox" + pageContext.findAttribute("index").toString() %>" />
											</td>

											<%-- 仕入番号(購買番号リンク) --%>
											<td>
												<nested:define id="oid" property="slipNo" />
												<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/BuyingDetail.do?op=init&slipNo=" + pageContext.findAttribute("oid").toString() + "'); return false;"%>'>
													<nested:write property="slipNo" />
												</html:link>
											</td>
											
											<%-- 仕入区分 --%>
											<td>
												<nested:write property="categoryName" />
											</td>
											
											<%-- 仕入先 --%>
											<td colspan="2">
												<nested:write property="venderShortedName" />
											</td>
											<%-- 品目名称 --%>
											<td colspan="3">
												<nested:write property="itemQueueName" />
											</td>
											
											<%-- 発行済チェック --%>
											<td rowspan="2" align="center">
												<nested:checkbox property="boolSlipPublishComp" styleId="boolSlipPublishComp" disabled="true"/>
											</td>
										</tr>
										
										<%-- 2段目 --%>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
											
											<%-- 数量 --%>
											<td class="alignRight">
												<nested:write property="strStockingQuantity" />
											</td>

											<%-- 部署 --%>
											<td>
												<nested:write property="organizationName" />
											</td>
										
											<%-- 担当者 --%>
											<td class="alignRight">
												<nested:write property="tantoNm" />
											</td>
											
											<%-- 仕入日 --%>
											<td>
												<nested:write property="strStockingDate" />
											</td>
											
											<%-- 単価 --%>
											<td class="alignRight">
												<nested:write property="strHousingUnitprice" />
											</td>
											
											<%-- 金額 --%>
											<td class="alignRight">
												<nested:write property="strStockingAmount" />
											</td>
											
											<%-- 仕入ステータス --%>
											<td>
												<nested:write property="status2Name" />
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

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>
</nested:form>
</body>
</html:html>

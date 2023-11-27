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
		<logic:equal name="salesListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhSalesDateFrom");
		defineAsDateField("srhSalesDateTo");
		defineAsYMField("srhAccountYears");
		defineAsYMField("srhAccountYearsTo");

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

		Event.observe('srhVenderCd',  'keypress', clearText.bindAsEventListener($('srhVenderName1')), false);

		<%-- 納入先のautocomplete --%>
		new Ajax.Autocompleter(
			"srhDeliveryCd",
			"autocomplete_selection",
			"<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
			{
				paramName : "code",
				afterUpdateElement : setDeliveryLabel
			}
		);

		Event.observe('srhDeliveryCd',  'keypress', clearText.bindAsEventListener($('srhDeliveryName')), false);

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

		<%-- 品目のauto complete --%>
		new Ajax.Autocompleter(
		  "srhItemCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);
		Event.observe('srhItemCd',  'keypress', clearText.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd',  'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);

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

		refreshLabel();

	}, false);

	<%-- 得意先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName1").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 納入先auto completeの選択後処理 --%>
	function setDeliveryLabel(text, li) {
	    $("srhDeliveryName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 担当部署auto completeの選択後処理 --%>
	function setChargeOrganizationLabel(text, li) {
	    $("srhChargeOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 品目auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}

	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
	    $("srhItemCd").value = getHiddenValue(li,"code");
	}

	<%-- 正単価更新確認メッセージ --%>
	function updateUnitPriceAlert() {
		alertMsg = new Array();
		alertMsg[0] = "正単価更新してもよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 日付型フィールド定義 --%>
	var dateElementsYYYYMM = new Array();
	/* 日付型 ON_FOCUSの動作を設定 */
	function dateFocusListenerYYYYMM() {
		this.value = unformatDate(this.value);
		Field.select(this);
	}
	
	<%--  日付型 OFF_FOCUSの動作を設定 --%>
	function dateBlurListenerYYYYMM() {
		this.value = formatDateYYYYMM(this.value);
	}

	function defineAsYMField(id) {
	
		var o = $(id);
		Event.observe(o, 'focus', dateFocusListenerYYYYMM.bind(o), false);
		Event.observe(o, 'blur', dateBlurListenerYYYYMM.bind(o), false);
	
		if (o.style.imeMode) {
			o.style.imeMode = 'disabled';
		}
		o.style.textAlign = 'left';
		
		dateElementsYYYYMM[o.id] = o;
	}
	function formatDateYYYYMM(org) {
	
		if (org == null) {
			return null;
		}
	
		if(!org.match(/^(-?)[0-9]{4,6}$/)){
			return org;
		}
	
		var tmp = org;
		
		if (tmp.length == 4) {
			tmp = "20" + tmp;
		}
	
		if (tmp.length == 6) {
	
			var year  = parseInt(tmp.substring(0, 4), 10);
			var month = parseInt(tmp.substring(4, 6), 10)-1;
			var day   = parseInt('01', 10);
	
			if (isNaN(year) || isNaN(month)) {
				return org;
			}
				
			var d = new Date(year, month, day);
	
			if (month != d.getMonth()) {
				return org;
			}
				
			if (day != d.getDate()) {
				return org;
			}
	
			return tmp.substring(0, 4) + "/" + tmp.substring(4, 6);
		}
	
		return org;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/SalesList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

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
									<td class="fcTitle fb">売上・返品</td>
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
									<td height="5" colspan="2"></td>
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
<!-- 20210406 項番1166の変更 開始-->			
												<!-- <td class="bcTitleSearch fb fcTitleSearch">売上番号</td> -->
												<td class="bcTitleSearch fb fcTitleSearch" width="65" align="center">売上番号</td>
<!-- 20210406 項番1166の変更 終了 -->
												<td width="100">
													<nested:text property="srhSalesNo" maxlength="11" size="10" styleId="srhSalesNo" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">売上区分</td>
												<td>
													<nested:select property="srhCategoryDivision" style="margin: 0;padding: 0" styleId="srhCategoryDivision">
														<nested:optionsCollection property="categoryCombo" label="labales" value="values" />
													</nested:select>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">取消</td>
												<td>
													<nested:checkbox property="srhCancelFlg" styleId="srhCancelFlg" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">受注番号</td>
												<td width="">
													<nested:text property="srhOrderNoFrom" maxlength="11" size="10" styleId="srhOrderNoFrom" />
												</td>
												<td>
													～
												</td>
												<td width="">
													<nested:text property="srhOrderNoTo" maxlength="11" size="10" styleId="srhOrderNoTo" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
<!-- 20210406 項番1166の変更 開始-->								
								<tr>
									<td>
										<table border="0" cellspacing="2" cellpadding="1">
												<td class="bcTitleSearch fb fcTitleSearch" width="65" align="center">勘定年月</td>
												<td width="230">
													<nested:text property="srhAccountYears" maxlength="7" size="10" styleId="srhAccountYears" style="ime-mode:disabled"  />
													～
													<nested:text property="srhAccountYearsTo" maxlength="7" size="10" styleId="srhAccountYears" style="ime-mode:disabled"  />
													<td class="bcTitleSearch fb fcTitleSearch">仮単価</td>
												</td>
												<td>
													<nested:checkbox property="srhTmpUnitpriceFlg" styleId="srhTmpUnitpriceFlg" />
												</td>
												<td></td>
												<td class="bcTitleSearch fb fcTitleSearch">預り品</td>
												<td>
													<nested:checkbox property="srhKeepFlag" styleId="srhKeepFlag" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">月次更新済</td>
												<td>
													<nested:checkbox property="srhMonthlyUpdateFlag" styleId="srhMonthlyUpdateFlag" />
												</td>
										</table>
									</td>
								</tr>
<!-- 20210406 項番1166の変更 終了 -->
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch" width="65" align="center">売上日</td>
												<td width="230">
													<nested:text property="srhSalesDateFrom" maxlength="10" size="10" styleId="srhSalesDateFrom" style="ime-mode:disabled" />
													～
													<nested:text property="srhSalesDateTo" maxlength="10" size="10" styleId="srhSalesDateTo" style="ime-mode:disabled" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr> 
<!-- 20210406 項番1166の変更 開始-->			
												<!-- <td class="bcTitleSearch fb fcTitleSearch">得意先</td> -->
												<td class="bcTitleSearch fb fcTitleSearch" width="65" align="center">得意先</td>
<!-- 20210406 項番1166の変更 終了 -->
												<td>
													<nested:text property="srhVenderCd" maxlength="15" size="10" styleId="srhVenderCd" />
													<div id="autocomplete_selection" class="autocomplete"></div>
												</td>
												<td width="150">
													<nested:text property="srhVenderName1" size="22" readonly="true" styleId="srhVenderName1" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">担当部署</td>
												<td>
													<nested:text property="srhChargeOrganizationCd" maxlength="10" size="10" styleId="srhChargeOrganizationCd" />
												</td>
												<td width="150">
													<nested:text property="srhChargeOrganizationName" size="22" readonly="true" styleId="srhChargeOrganizationName" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
										</table>

										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
<!-- 20210406 項番1166の変更 開始-->		
												<!-- <td class="bcTitleSearch fb fcTitleSearch">品目</td> -->
												<td class="bcTitleSearch fb fcTitleSearch" width="65" align="center">品目</td>
<!-- 20210406 項番1166の変更 終了 -->
												<td>
													<nested:text property="srhItemCd" maxlength="20" size="12" styleId="srhItemCd" />
												</td>
												<td width="250">
													<nested:text property="srhItemName" size="35" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1"/>
												</td>
<!-- 20210406 項番1166の変更 開始-->	
												<!-- <td class="bcTitleSearch fb fcTitleSearch"  width="100" align="center">他社コード1</td> -->
												<td class="bcTitleSearch fb fcTitleSearch"  width="100" align="center">他社コード1</td>
<!-- 20210406 項番1166の変更 終了 -->
												<td>
													<nested:text property="srhOtherCompanyCd1" maxlength="20" size="12" styleId="srhOtherCompanyCd1" />
												</td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
<!-- 20210406 項番1166の変更 開始-->		
												<!-- <td class="bcTitleSearch fb fcTitleSearch" width="65" align="center">納入先</td> -->
												<td class="bcTitleSearch fb fcTitleSearch" width="65" align="center">納入先</td>
<!-- 20210406 項番1166の変更 終了 -->
												<td>
													<nested:text property="srhDeliveryCd" maxlength="20" size="12" styleId="srhDeliveryCd" />
												</td>
												<td width="250">
													<nested:text property="srhDeliveryName" size="35" readonly="true" styleId="srhDeliveryName" styleClass="noborderAl" tabindex="-1"/>
												</td>
<!-- 20210406 項番1166の変更 開始-->		
												<!-- <td class="bcTitleSearch fb fcTitleSearch" width="100" align="center">客先注文番号</td> -->
												<td class="bcTitleSearch fb fcTitleSearch" width="100" align="center">客先注文番号</td>
<!-- 20210406 項番1166の変更 終了 -->
												<td>
													<nested:text property="srhCustomerOrderNo" maxlength="20" size="12" styleId="srhCustomerOrderNo" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table cellspacing="1" cellpadding="1" border="0" width="750">
											<tr>
												<td align="left">
													<div id="cssButton">
														<a href="#" class="cssButton"
														 onclick="if (!(updateUnitPriceAlert())) {return false;}else{return check_form_submit('op', 'updateUnitPrice'); return false;}" >
														&nbsp;正単価更新&nbsp;
														</a>
													</div>
												</td>
												<td align="right">
													<div id="cssButton">
														<a href="#"
															onclick="return check_form_submit('op', 'search'); return false;"
															class="cssButton"> &nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
														</a>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<a href='<%= request.getContextPath() + "/SalesDetailStandard.do?op=initNew"%>'
															class="cssButton"> &nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
														</a>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<a href='<%= request.getContextPath() + "/SalesDetailKeep.do?op=initNew"%>'
															class="cssButton"> 新規(預り品)
														</a>
													</div>
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
							<td><%-- 明細部 --%>
								<table width="" border="0">
									<tr>
										<td class="alignRight">
											<table cellspacing="1" cellpadding="1" border="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" 
																onclick="if (!(reportConfirm())) {return false;}else{return check_form_submit('op', 'report'); return false;}"
																class="cssButton">
																帳票(EXCEL)
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="alignCenter">
											<table cellspacing="2" cellpadding="1" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td>売上番号</td>
													<td>売上区分</td>
													<td>勘定年月</td>
													<td>受注番号</td>
													<td colspan="3">品目</td>
													<td>荷姿</td>
													<td>数量</td>
													<td>金額(円)</td>
													<td rowspan="2">仮単価</td>
													<td rowspan="2">発行済</td>
												</tr>
												<tr class="bcTitleList fb fcTitleList">
													<td colspan="2">納入先</td>
													<td colspan="2">客先注文番号</td>
													<td>売上日</td>
													<td colspan="2">得意先</td>
													<td colspan="3"></td>
												</tr>
												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
													<nested:define id="sno" property="salesNo" />
															<%-- 売上番号 --%>
															<td>
																<nested:notEmpty property="orderNo">
																	<%-- 受注番号が存在する場合、預り品のレイアウトで表示 --%>
																	<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/SalesDetailKeep.do?op=init&salesNo=" + pageContext.findAttribute("sno").toString() + "'); return false;"%>'>
																		<nested:write property="salesNo" />
																	</html:link>
																</nested:notEmpty>
																<nested:empty property="orderNo">
																	<nested:equal property="keepFlag" value="1">
																		<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/SalesDetailStandard.do?op=init&salesNo=" + pageContext.findAttribute("sno").toString() + "'); return false;"%>'>
																			<nested:write property="salesNo" />
																		</html:link>
																	</nested:equal>
																	<nested:notEqual property="keepFlag" value="1">
																		<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/SalesDetailKeep.do?op=init&salesNo=" + pageContext.findAttribute("sno").toString() + "'); return false;"%>'>
																			<nested:write property="salesNo" />
																		</html:link>
																	</nested:notEqual>
																</nested:empty>
															</td>
															<%-- 分類コード --%>
															<td>
																<nested:write property="categoryName" />
															</td>
															<%-- 勘定年月 --%>
															<td>
																<nested:write property="strAccountYears" />
															</td>
															<%-- 受注番号 --%>
															<td>
																<nested:write property="orderNo" />
															</td>
															<%-- 品目 --%>
															<td colspan="3">
																<nested:write property="itemName" />
															</td>
															<%-- 荷姿 --%>
															<td>
																<nested:write property="styleOfPacking" />
															</td>
															<%-- 数量 --%>
															<td class="alignRight">
																<nested:write property="strSalesQuantity" />
															</td>
															<%-- 金額 --%>
															<td class="alignRight">
																<nested:write property="strSalesAmount" />
															</td>
															<%-- 仮単価 --%>
															<td class = "alignCenter" rowspan="2">
																<nested:checkbox property="tmpUnitpriceFlgCheck" styleId="tmpUnitpriceFlgCheck" disabled="true" />
															</td>
															<%-- 発行済 --%>
															<td class = "alignCenter" rowspan="2">
																<nested:checkbox property="slipPublishCompCheck" styleId="slipPublishCompCheck" disabled="true" />
															</td>
														</tr>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
															<%-- 納品先名称 --%>
															<td colspan="2">
																<nested:write property="searchKana" />
															</td>
															<%-- 客先注文番号 --%>
															<td colspan="2">
																<nested:write property="customerOrderNo" />
															</td>
															<%-- 売上日 --%>
															<td>
																<nested:write property="strSalesDate" />
															</td>
															<%-- 得意先 --%>
															<td colspan="2">
																<nested:write property="venderShortedName" />
															</td>
															<td  colspan="3">　</td>
														</tr>
												</nested:iterate>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
								<%-- ページング --%>
								<%@ include file="/jsp/common/pager/pager.jsf"%>
								<%-- ページング ここまで --%>
							</td>
						</tr>
					</table>
				</nested:notEmpty>
			</td>
		</tr>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
	</table>
</nested:form>
</body>
</html:html>

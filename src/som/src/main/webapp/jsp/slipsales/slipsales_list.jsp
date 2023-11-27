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
		<logic:equal name="slipSalesListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhSalesDateFrom");
		defineAsDateField("srhSalesDateTo");
		defineAsDateField("srhScheduledShippingDate");
		defineAsDateField("srhDeliveryExpectedDate");
		defineAsYMField("srhAccountYears");

		defineAsDateField("srhSlipDateFrom");
		defineAsDateField("srhSlipDateTo");

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

		<%-- ロケーションのauto complete --%>
		new Ajax.Autocompleter(
		  "srhLocationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/SlipSalesLocationForAutoComplete.do?op=searchLocation",
		  {
		  	paramName : "code",
		    afterUpdateElement : setLocationLabel
		  }
		);
		Event.observe('srhLocationCd',  'keypress', clearText.bindAsEventListener($('srhLocationName')), false);

		refreshLabel();
<!-- 20210531 項目がありません不具合対応　20210414　項番206売上伝票送信処理の強調 -->
		<!-- if(document.body.innerHTML.indexOf("送信を実行しました")>-1　&& document.getElementsByClassName("errorExplanation").length  == 0){ -->
		if(document.body.innerHTML.indexOf("送信を実行しました")>-1){
			alert("送信を実行しました。");
		}
<!-- 20210531 項目がありません不具合対応　20210414　項番206売上伝票送信処理の強調 -->
	}, false);

	<%-- 得意先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName1").value = li.getElementsByTagName('span')[0].innerText;
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
	
	<%-- 納入先auto completeの選択後処理 --%>
	function setDeliveryCdLabel(text, li) {
	    $("srhDeliveryName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- ロケーションauto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
	}


	<%-- チェックボックス　全設定 --%>
	function allCheck(flg) {
        for (var i=0; i < $("searchListLength").value; i++) {
        	$("slipSalesCheckBox" + i).checked = flg;
        }
	}

	<%-- 確認メッセージ --%>
	function canselConfirm() {
		if (!noCheck(1)) {
		  return false;
		}
		
		alertMsg = new Array();
		alertMsg[0] = "取消してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
	<%-- 確認メッセージ 20210806  課題管理　項番　1296--%>
	function slipSendConfirm() {
		if (!noCheck(1)) {
			return false;
		}
			
		alertMsg = new Array();
		alertMsg[0] = "伝票送信を行います。よろしいですか？";

		return confirm(alertMsg[0]);
	}
	function slipPublishConfirm() {
		if (!noCheck(1)) {
			return false;
		}
			
		alertMsg = new Array();
		alertMsg[0] = "売上伝票印刷を行います。よろしいですか？";

		return confirm(alertMsg[0]);
	}
	<%-- 確認メッセージ 20210806  課題管理　項番　1296--%>
	<%-- 送信完了メッセージ --%>
	function slipSalesSendAlert() {
		alertMsg = new Array();
		alertMsg[0] = "伝票を送信しました。";

		return alert(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
		var count = 0;
		for (var i=0; i < $("searchListLength").value; i++) {
			if ($("slipSalesCheckBox" + i).checked) {
				count++;
			}
		}
		if (count == 0) {
		     alert("対象が選択されていません。");
		  return false;
		}
		return true;
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

<nested:form action="/SlipSalesList" method="post" onsubmit="return false;" styleId="mainForm">

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
									<td class="fcTitle fb">売上伝票出力画面</td>
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
												<td class="bcTitleSearch fb fcTitleSearch">売上番号</td>
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
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">売上日</td>
												<td width="230">
													<nested:text property="srhSalesDateFrom" maxlength="10" size="10" styleId="srhSalesDateFrom" style="ime-mode:disabled" />
													～
													<nested:text property="srhSalesDateTo" maxlength="10" size="10" styleId="srhSalesDateTo" style="ime-mode:disabled" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">勘定年月</td>
												<td width="100">
													<nested:text property="srhAccountYears" maxlength="7" size="10" styleId="srhAccountYears" style="ime-mode:disabled"  />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">仮単価</td>
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
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">得意先</td>
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
												<td class="bcTitleSearch fb fcTitleSearch">品目</td>
												<td>
													<nested:text property="srhItemCd" maxlength="20" size="12" styleId="srhItemCd" />
												</td>
												<td width="200">
													<nested:text property="srhItemName" size="28" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">他社コード1</td>
												<td>
													<nested:text property="srhOtherCompanyCd1" maxlength="20" size="12" styleId="srhOtherCompanyCd1" />
												</td>
											</tr>
										</table>
										
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">売上伝票NO</td>
												<td>
													<nested:text property="srhSalesSlipNo" maxlength="9" size="12" styleId="srhSalesSlipNo" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">出荷予定日</td>
												<td>
													<nested:text property="srhScheduledShippingDate" maxlength="20" size="12" styleId="srhScheduledShippingDate" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">納入予定日</td>
												<td>
													<nested:text property="srhDeliveryExpectedDate" maxlength="20" size="12" styleId="srhDeliveryExpectedDate" />
												</td>
											</tr>
										</table>
										
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">上位ﾛｹｰｼｮﾝ</td>
												<td>
													<nested:text property="srhLocationCd" maxlength="20" size="10" styleId="srhLocationCd" />
												</td>
												<td>
													<nested:text property="srhLocationName" size="15" readonly="true" styleId="srhLocationName" styleClass="noborderAl" tabindex="-1"/>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">納入先</td>
												<td>
													<nested:text property="srhDeliveryCd" maxlength="20" size="10" styleId="srhDeliveryCd" />
												</td>
												<td>
													<nested:text property="srhDeliveryName" size="20" readonly="true" styleId="srhDeliveryName" styleClass="noborderAl" tabindex="-1"/>
												</td>
											</tr>
										</table>
										
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">運送店</td>
												<td>
													<nested:select property="srhCarryCd" style="margin: 0;padding: 0" styleId="srhCarryCd">
														<nested:optionsCollection property="carryCombo" label="labales" value="values" />
													</nested:select>
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">注文番号</td>
												<td>
													<nested:text property="srhCustomerOrderNo" maxlength="20" size="12" styleId="srhCustomerOrderNo" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">売上伝票未発行</td>
												<td>
													<nested:checkbox property="srhSlipPublishComp" styleId="srhSlipPublishComp"/>
												</td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">伝票発行日</td>
												<td width="230">
													<nested:text property="srhSlipDateFrom" maxlength="10" size="10" styleId="srhSlipDateFrom" style="ime-mode:disabled" />
													～
													<nested:text property="srhSlipDateTo" maxlength="10" size="10" styleId="srhSlipDateTo" style="ime-mode:disabled" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">仕入伝票未送信</td>
												<td>
													<nested:checkbox property="srhSlipSendComp" styleId="srhSlipSendComp"/>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" colspan="2">郵送区分</td>
												<td>
													<% pageContext.setAttribute("SelectSalesVenderFaxDivision", new com.asahikaseieng.app.comboboxes.SelectSalesVenderFaxDivision(true, false)); %>
													<nested:select property="srhFaxOutput" styleId="srhFaxOutput">
														<nested:options name="SelectSalesVenderFaxDivision" property="values" labelName="SelectSalesVenderFaxDivision" labelProperty="labels"/>
													</nested:select>
												</td>
												
												<td class="fcTitleDetail fb bcTitleDetail">FAX送信区分</td>
												<td colspan="2">
													<% pageContext.setAttribute("SelectMailSendDivision", new com.asahikaseieng.app.comboboxes.SelectMailSendDivision(true, false)); %>
													<nested:select property="srhSalesFaxOutput" onchange="setDirtyFlg();" >
														<nested:options name="SelectMailSendDivision" property="values" labelName="SelectMailSendDivision" labelProperty="labels"/>
													</nested:select>
												</td>
												<td class="fcTitleDetail fb bcTitleDetail">メール送信区分</td>
												<td colspan="2">
													<% pageContext.setAttribute("SelectMailSendDivision", new com.asahikaseieng.app.comboboxes.SelectMailSendDivision(true, false)); %>
													<nested:select property="srhSalesMailOutput" onchange="setDirtyFlg();" >
														<nested:options name="SelectMailSendDivision" property="values" labelName="SelectMailSendDivision" labelProperty="labels"/>
													</nested:select>
												</td>
												
												
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table cellspacing="1" cellpadding="1" border="0" width="1000">
											<tr>
												<td align="right">
													<div id="cssButton">
														<a href="#"
															onclick="return check_form_submit('op', 'search'); return false;"
															class="cssButton"> &nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
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
						<%-- ボタン部   --%>
						<tr>
							<td>
								<table cellspacing="1" cellpadding="1" bdelivery="0"  width="100%">
									<tr>
										<td align="left">
											<table cellspacing="1" cellpadding="1" bdelivery="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" onclick="allCheck(true); return false;" class="cssButton"> 全チェック</a>
														</div>
													</td>
													<td>
														<div id="cssButton">
															<a href="#" onclick="allCheck(false); return false;" class="cssButton"> 全チェック解除</a>
														</div>
													</td>
													<td>
														<div id="cssButton">
															<a href="#"
																onclick="if (!(canselConfirm())) {return false;}else{return form_submit('op', 'canselSlipPublish'); return false;}"
																class="cssButton"> &nbsp;&nbsp;発行済取消&nbsp;&nbsp;
															</a>
														</div>
													</td>

													<!-- <td>20190909　軽減税率対応　使用しない為コメントアウト
														<div id="cssButton">
															<a href="#"
																onclick="return check_form_submit('op', 'slipPublishOld'); return false;"
																class="cssButton"> 売上伝票印刷(旧)	
															</a>
														</div>
													</td> -->
													<td>
														<div id="cssButton">
															
														<%-- 確認メッセージ 20210806  課題管理　項番　1296--%>
															<!-- <a href="#"
																onclick="return check_form_submit('op', 'slipSend'); return false;"
																class="cssButton">-->
															<a href="#"
																onclick="if (!(slipSendConfirm())) {return false;}else{return form_submit('op', 'slipSend'); return false;}"
																class="cssButton"> 伝票送信
															</a>
														<%-- 確認メッセージ 20210806  課題管理　項番　1296--%>
														</div>
													</td>
													<td>
														<div id="cssButton">
														<%-- 確認メッセージ 20210806  課題管理　項番　1296--%>	
		  													<!-- <a href="#"
																onclick="return check_form_submit('op', 'slipPublish'); return false;"
																class="cssButton">-->
															<a href="#"
																onclick="if (!(slipPublishConfirm())) {return false;}else{return form_submit('op', 'slipPublish'); return false;}"
																class="cssButton"> 売上伝票印刷
															</a>
														<%-- 確認メッセージ 20210806  課題管理　項番　1296--%>
														</div>
													</td>

												</tr>
											</table>
										</td>
										<td align="right"  width="15%">
											<table cellspacing="1" cellpadding="1" bdelivery="0">
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
							<td><%-- 明細部 --%>
								<table width="" border="0" width="100%">
									<tr>
										<td class="alignCenter">
											<nested:hidden property="searchListLength" styleId="searchListLength" />
											<table cellspacing="2" cellpadding="1" id="tblList">
												
												<tr class="bcTitleList fb fcTitleList">
													<td width="20" rowspan="2"></td>
													<td width="90">売上番号</td>
													<td width="80">売上日</td>
													<td width="80">売上区分</td>
													<td width="450"colspan="2">品目名称</td>
													<td width="150">荷姿</td>
													<td width="150">他社コード1</td>
													<td width="50" rowspan="2">発行済</td>
												</tr>
												<tr class="bcTitleList fb fcTitleList">
													<!-- rowspan -->
													<td>売上伝票NO</td>
													<td>勘定年月</td>
													<td>上位ﾛｹｰｼｮﾝ</td>
													<td>受注番号</td>
													<td width="350">得意先</td>
													<td colspan="2">納入先</td>
													<!-- Roswpan -->
												</tr>
												<nested:iterate id="searchList" property="searchList" indexId="index">
													<%-- 1段目 --%>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
															<%-- チェックボックス --%>
															<td rowspan="2">
																<nested:checkbox property="slipSalesCheckBox" styleId="<%="slipSalesCheckBox" + pageContext.findAttribute("index").toString() %>" />
																<nested:hidden property="slipSalesCheckBox" styleId="<%="slipSalesCheckBox" + pageContext.findAttribute("index").toString() %>" value="0"/>
															</td>
															<%-- 売上番号 --%>
															<td>
																<nested:write property="salesNo" />
															</td>
															<%-- 売上日 --%>
															<td>
																<nested:write property="strSalesDate" />
															</td>
															<%-- 売上区分 --%>
															<td>
																<nested:write property="categoryDivisionName" />
															</td>
															<%-- 品目名称 --%>
															<td colspan="2">
																<nested:write property="itemName" />
															</td>
															<%-- 荷姿 --%>
															<td>
																<nested:write property="styleOfPacking" />
															</td>
															<%-- 他社コード１ --%>
															<td>
																<nested:write property="otherCompanyCd1" />
															</td>
															<%-- 発行済チェック --%>
															<td rowspan="2" class = "alignCenter">
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
															<!-- rowspan -->
															<%-- 売上伝票NO --%>
															<td>
																<nested:write property="salesSlipNo" />
															</td>
															<%-- 勘定年月 --%>
															<td>
																<nested:write property="strAccountYears" />
															</td>
															<%-- 上位ﾛｹｰｼｮﾝ --%>
															<td>
																<nested:write property="locationCd" />
															</td>
															<%-- 受注番号 --%>
															<td>
																<nested:write property="orderNo" />
															</td>
															<%-- 得意先 --%>
															<td>
																<nested:write property="venderShortedName" />
															</td>
															<%-- 納入先 --%>
															<td colspan="2">
																<nested:write property="searchKana" />
															</td>
															<!-- Roswpan -->
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

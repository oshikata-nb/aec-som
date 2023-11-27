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
		<logic:equal name="slipShippingListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhScheduledShippingDateFrom");
		defineAsDateField("srhScheduledShippingDateTo");

		<%-- ロケーションのauto complete --%>
		new Ajax.Autocompleter(
		  "srhLocationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/SlipShippingLocationForAutoComplete.do?op=searchLocation",
		  {
		  	paramName : "code",
		    afterUpdateElement : setLocationLabel
		  }
		);
		Event.observe('srhLocationCd',  'keypress', clearText.bindAsEventListener($('srhLocationName')), false);
	}, false);

	<%-- ロケーションauto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
	}
	
	<%-- チェックボックス　全設定 --%>
	function allCheck(flg) {
        for (var i=0; i < $("searchListLength").value; i++) {
        	$("slipShippingCheckBox" + i).checked = flg;
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

	
	<%-- 確認メッセージ --%>
	function putClearConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "解除してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
<%-- チェックボックス選択無しチェック --%>
	function noCheck(index){
		var count = 0;
		for (var i=0; i < $("searchListLength").value; i++) {
			if ($("slipShippingCheckBox" + i).checked) {
				count++;
			}
		}
		if (count == 0) {
		     alert("対象が選択されていません。");
		  return false;
		}
		return true;
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

<nested:form action="/SlipShippingList" method="post" onsubmit="return false;" styleId="mainForm">

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
									<td class="fcTitle fb" width="200">出荷関連帳票出力画面</td>
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
							<table width="100%" cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">上位ロケーション</td>
									<td colspan="3">
										<nested:text property="srhLocationCd" maxlength="11" size="11" styleId="srhLocationCd" />
										<div id="autocomplete_selection" class="autocomplete"></div>
										<nested:text property="srhLocationName" maxlength="40" size="22" readonly="true" styleId="srhLocationName" styleClass="noborderAl" tabindex="-1" />
									</td>
									
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">運送店</td>
									<td colsapn="3">
										<nested:select property="srhCarryCd" style="margin: 0;padding: 0">
											<nested:optionsCollection property="carryCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">出荷予定日</td>
									<td>
										<nested:text property="srhScheduledShippingDateFrom" maxlength="8" size="8" styleId="srhScheduledShippingDateFrom" style="ime-mode:disabled" />
										～
										<nested:text property="srhScheduledShippingDateTo" maxlength="8" size="8" styleId="srhScheduledShippingDateTo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">受注番号</td>
									<td>
										<nested:text property="srhOrderNoFrom" maxlength="11" size="8" styleId="srhOrderNoFrom" style="ime-mode:disabled" />
										～
										<nested:text property="srhOrderNoTo" maxlength="11" size="8" styleId="srhOrderNoTo" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">出荷番号</td>
									<td>
										<nested:text property="srhShippingNo" maxlength="11" size="8" styleId="srhShippingNo" style="ime-mode:disabled" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">ステータス</td>
									<td>
										<%
											pageContext.setAttribute("selectSlipShippingShippingStatus",
											new com.asahikaseieng.app.comboboxes.SelectSlipShippingShippingStatus(true, false));
										%>
										<nested:select property="srhShippingStatus" styleId="srhShippingStatus">
											<nested:options name="selectSlipShippingShippingStatus" property="values" labelName="selectSlipShippingShippingStatus" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">出荷BC</td>
									<td>
										<nested:text property="srhCarryBarcode" maxlength="50" size="8" styleId="srhCarryBarcode" style="ime-mode:disabled" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table width="100%" cellspacing="1" cellpadding="1" border="0">
											<tr>
<%--											
												<td class="bcTitleSearch fb fcTitleSearch">出荷伝票<br />未発行</td>
												<td>
													<nested:checkbox property="srhSlipPublishComp" styleId="srhSlipPublishComp" onclick="" />
												</td>
--%>
												<td class="bcTitleSearch fb fcTitleSearch">出荷指図書<br />未発行</td>
												<td>
													<nested:checkbox property="srhSlipShippingOrderComp" styleId="srhSlipShippingOrderComp" onclick="" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">出荷予定表<br />未発行</td>
												<td>
													<nested:checkbox property="srhSlipShippingScheduleComp" styleId="srhSlipShippingScheduleComp" onclick="" />
												</td>
<%--											
												<td class="bcTitleSearch fb fcTitleSearch">荷札<br />未発行</td>
												<td>
													<nested:checkbox property="srhSlipShippingTagComp" styleId="srhSlipShippingTagComp" onclick="" />
												</td>
--%>
												<td class="bcTitleSearch fb fcTitleSearch">出荷依頼書<br />未発行</td>
												<td>
													<nested:checkbox property="srhSlipShippingRequestComp" styleId="srhSlipShippingRequestComp" onclick="" />
												</td>
											</tr>
											<tr>
 												<td class="bcTitleSearch fb fcTitleSearch" >運賃表未発行<br /></td>
												<td>
													<nested:checkbox property="srhSlipShippingFareComp" styleId="srhSlipShippingFareComp" onclick="" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">納品伝票未発行<br /></td>
												<td>
													<nested:checkbox property="srhSlipDeliveryComp" styleId="srhSlipDeliveryComp" onclick="" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch">新荷札未発行<br /></td>
												<td>
													<nested:checkbox property="srhSlipNewShippingTagComp" styleId="srhSlipNewShippingTagComp" onclick="" />
												</td>
<%--
												<td class="bcTitleSearch fb fcTitleSearch" width="120">新郵政未発行<br /></td>
												<td>
													<nested:checkbox property="srhSlipPostalComp" styleId="srhSlipPostalComp" onclick="" />
												</td>
--%>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">荷札種別</td>
									<td>
										<%
											pageContext.setAttribute("selectLabelPublish",
											new com.asahikaseieng.app.comboboxes.SelectLabelPublish(true, false));
										%>
										<nested:select property="srhLabelPublish" styleId="srhLabelPublish">
											<nested:options name="selectLabelPublish" property="values" labelName="selectLabelPublish" labelProperty="labels" />
										</nested:select>
									</td>
									<td class="bcTitleSearch fb fcTitleSearch" width="100">出荷伝票番号</td>
									<td>
										<nested:text property="srhShippingSlipNo" maxlength="9" size="8" styleId="srhShippingSlipNo" style="ime-mode:disabled" />
									</td>


								</tr>
								<tr>
									<%--　検索ボタンとか --%>
									<table cellspacing="1" cellpadding="1" border="0" width="750">
										<tr>
											<td align="right">
												<div id="cssButton">
													<a href="#"
														onclick="return check_form_submit('op', 'search'); return false;"
														class="cssButton"> &nbsp;&nbsp;検索&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</tr>
									</table>
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
								<table width="750" border="0">
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
																onclick="if (!(canselConfirm())) {return false;}else{return form_submit('op', 'canselslippublish'); return false;}"
																class="cssButton"> 出荷伝票発行済取消
															</a>
														</div>
														<td>
															<div id="cssButton">
																<a href="#"
																	onclick="if(!putClearConfirm()){return false;}else{return check_form_submit('op', 'clearbarcode'); return false;}"
																	class="cssButton"> 出荷BC解除
																</a>
															</div>
														</td>
													</td>
<%--
 													<td class="bcTitleSearch fb fcTitleSearch" width="100">郵政依頼主</td>
													<td>
														<nested:select property="postalClientCd" style="margin: 0;padding: 0">
														<nested:optionsCollection property="postalClientCombo" label="labales" value="values" />
														</nested:select>
													</td>
--%>
												</tr>
											</table>
										</td>
										<td align="right">
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
								<table cellspacing="1" cellpadding="1" bdelivery="0">
									<tr class="alignLeft">
<%--
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slippublish'); return false;"
													class="cssButton"> 出荷伝票印刷
												</a>
											</div>
										</td>
--%>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingorder'); return false;"
													class="cssButton"> 出荷指図書印刷
												</a>
											</div>
										</td>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingschedule'); return false;"
													class="cssButton"> 出荷予定表印刷
												</a>
											</div>
										</td>
<%--
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingtag'); return false;"
													class="cssButton"> 荷札印刷
												</a>
											</div>
										</td>
--%>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingrequest'); return false;"
													class="cssButton"> 出荷依頼書印刷
												</a>
											</div>
										</td>
 										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingfare'); return false;"
													class="cssButton"> 運賃表印刷
												</a>
											</div>
										</td>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingdelivery'); return false;"
													class="cssButton"> 納品伝票印刷
												</a>
											</div>
										</td>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingnewtag'); return false;"
													class="cssButton"> 新荷札印刷
												</a>
											</div>
										</td>
<%--
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'slipshippingpostal'); return false;"
													class="cssButton"> 新郵政印刷
												</a>
											</div>
										</td>
--%>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return check_form_submit('op', 'createcarryfile'); return false;"
													class="cssButton"> 運送会社連携ファイル作成
												</a>
											</div>
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
											<table cellspacing="2" cellpadding="1" id="tblList">
												<nested:hidden property="searchListLength" styleId="searchListLength" />
												<tr class="bcTitleList fb fcTitleList">
													<td rowspan="2"></td>
													<td>出荷番号</td>
													<td>出荷伝票番号</td>
													<td>受注番号</td>
													<td>出荷予定日</td>
													<td>上位ロケ</td>
													<td>ステータス</td>
													<td>運送店名称</td>
													<td>荷札種別</td>
<%--
													<td rowspan="2">伝<br />票</td>
--%>													
													<td rowspan="2">指<br />図<br />書</td>
													<td rowspan="2">予<br />定<br />表</td>
<%--
													<td rowspan="2">荷<br />札</td>
--%>
													<td rowspan="2">依<br />頼<br />書</td>
													<td rowspan="2">運<br />賃<br />表</td>
													<td rowspan="2">納<br />品<br />伝</td>
													<td rowspan="2">新<br />荷<br />札</td>
<%--
													<td rowspan="2">新<br />郵<br />政</td>
--%>
												</tr>
												<tr class="bcTitleList fb fcTitleList">
													<td colspan="2">納入先</td>
													<td colspan="5">品目名称</td>
													<td>指図量</td>
												</tr>
												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

															<%-- チェックボックス --%>
															<td rowspan="2">
																<nested:checkbox property="slipShippingCheckBox" styleId="<%="slipShippingCheckBox" + pageContext.findAttribute("index").toString() %>" />
																<nested:hidden   property="slipShippingCheckBox" styleId="<%="slipShippingCheckBox" + pageContext.findAttribute("index").toString() %>" value="0" />

															</td>
															<%-- 出荷番号 --%>
															<td>
																<nested:write property="shippingNo" />
															</td>
															<%-- 出荷伝票番号 --%>
															<td>
																<nested:write property="shippingSlipNo" />
															</td>
															<%-- 受注番号 --%>
															<td>
																<nested:write property="orderNo" />
															</td>
															<%-- 出荷予定日 --%>
															<td>
																<nested:write property="strScheduledShippingDate" />
															</td>
															<%-- 上位ﾛｹｰｼｮﾝ --%>
															<td>
																<nested:write property="upperLocationCd" />
															</td>
															<%-- ステータス --%>
															<td>
																<nested:write property="strShippingStatus" />
															</td>
															<%-- 運送会社名称 --%>
															<td>
																<nested:write property="carryName1" />
															</td>
															<%-- 荷札種別 --%>
															<td>
																<nested:write property="strLabelPublish" />
															</td>
															<%-- 伝票 --%>
<%--
															<td rowspan="2">
																<nested:checkbox property="boolSlipPublishComp" styleId="slipPublishComp" disabled="true"/>
															</td>
--%>
															<%-- 指図書 --%>
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingOrderComp" styleId="slipShippingOrderComp" disabled="true"/>
															</td>
															<%-- 予定表--%>
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingScheduleComp" styleId="slipShippingScheduleComp" disabled="true"/>
															</td>
															<%-- 荷札 --%>
<%--
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingTagComp" styleId="slipShippingTagComp" disabled="true"/>
															</td>
--%>															
															<%-- 依頼書 --%>
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingRequestComp" styleId="slipShippingRequestComp" disabled="true"/>
															</td>
															<%-- 運賃表 --%>
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingFareComp" styleId="slipShippingFareComp" disabled="true"/>
															</td>
															<%-- 納品伝 --%>
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingDeliveryComp" styleId="slipShippingDeliveryComp" disabled="true"/>
															</td>
															<%-- 新荷札--%>
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingNewTagComp" styleId="slipShippingNewTagComp" disabled="true"/>
															</td>
															<%-- 新郵政 --%>
<%--
															<td rowspan="2">
																<nested:checkbox property="boolSlipShippingPostalComp" styleId="slipShippingPostalComp" disabled="true"/>
															</td>
--%>
														</tr>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
															<%-- 納入先 --%>
															<td colspan="2">
																<nested:write property="searchKana" />
															</td>
															<%-- 品目 --%>
															<td colspan="5">
																<nested:write property="itemName" />
															</td>
															<%-- 指図量 --%>
															<td class="alignRight">
																<nested:write property="orderQty" />
															</td>
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

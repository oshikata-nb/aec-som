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
			<%-- チェックボックスがチェックされている状態で表示されると必ず警告になるのでコメント --%>
//	        if (continueConfirm()) {
	          location.href=url;
	  g_lock = true;
//	    }
      }
	}

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		<logic:equal name="shippingResultListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("srhScheduledShippingDateFrom");
		defineAsDateField("srhScheduledShippingDateTo");
		defineAsDateField("srhShippingResultDateFrom");
		defineAsDateField("srhShippingResultDateTo");

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
		Event.observe('srhDeliveryCd',  'keypress', clearText.bindAsEventListener($('srhDeliveryName1')), false);

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

		<%-- ロケーションのauto complete --%>
		new Ajax.Autocompleter(
		  "srhLocationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/LocationForAutoComplete.do?op=searchLocation",
		  {
		  	paramName : "code",
		    afterUpdateElement : setLocationLabel
		  }
		);
		Event.observe('srhLocationCd',  'keypress', clearText.bindAsEventListener($('srhLocationName')), false);

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

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
	  if (!isExistsChecked()) {
        alert("完了登録対象がありません。");
	    return false;
	  }
	  return true;
	}

	<%-- チェックボックス選択無しチェック --%>
	function isExistsChecked(){
	  var count = 0;
	  var item = document.shippingResultListForm.checkFlg;
	  if (item == undefined) {
	  	return false;
	  }
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
          if(item[i].disabled == false && item[i].checked){
		    count = count + 1;
		    break;
		  }
	    }
	  }

	  if (count == 0) {
	    return false;
	  }
	  return true;
	}

	<%-- 完了登録確認メッセージ --%>
	function completeAlert() {
		if (!noCheck()) {
		  return false;
		}

		alertMsg = new Array();
		alertMsg[0] = "完了登録してもよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 帳票確認メッセージ --%>
	function printConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票を出力してもよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirm() {
		if (isExistsChecked()) {
			//チェックされている場合
			alertMsg = new Array();
			alertMsg[0] = "画面の編集内容は反映されません、よろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			return true;
		}
	}

	<%-- 納入先auto completeの選択後処理 --%>
	function setDeliveryCdLabel(text, li) {
	    $("srhDeliveryName1").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 得意先auto completeの選択後処理 --%>
	function setVenderLabel(text, li) {
	    $("srhVenderName1").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- ロケーションauto completeの選択後処理 --%>
	function setLocationLabel(text, li) {
	    $("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
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

<nested:form action="/ShippingResultList" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb" width="250">出荷実績検索</td>
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
							<td height= 5 colspan="2"></td>
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
						<table cellspacing="1" cellpadding="1" width="750" border="0">
							<tr>
								<td><!-- ヘッダー部 -->
									<table border="0" cellspacing="0" cellpadding="2">
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">出荷番号</td>
											<td>
												<nested:text property="srhShippingNo" maxlength="11" size="12" styleId="srhShippingNo" style="ime-mode:disabled" />
											</td>
											<td class="bcTitleSearch fb fcTitleSearch">出荷予定日</td>
											<td>
												<nested:text property="srhScheduledShippingDateFrom" maxlength="10" size="12" styleId="srhScheduledShippingDateFrom" style="ime-mode:disabled" /> ～												<nested:text property="srhScheduledShippingDateTo" maxlength="10" size="12" styleId="srhScheduledShippingDateTo" style="ime-mode:disabled" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellspacing="0" cellpadding="2" border="0">
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">納入先</td>
											<td>
												<nested:text property="srhDeliveryCd" maxlength="10" size="12" styleId="srhDeliveryCd" />
												<div id="autocomplete_selection" class="autocomplete"></div>
											</td>
											<td>
												<nested:text property="srhDeliveryName1" size="22" readonly="true" styleId="srhDeliveryName1" styleClass="noborderAl" tabindex="-1"/>
											</td>
											<td class="bcTitleSearch fb fcTitleSearch">得意先</td>
											<td>
												<nested:text property="srhVenderCd" maxlength="10" size="12" styleId="srhVenderCd" />
											</td>
											<td>
												<nested:text property="srhVenderName1" size="22" readonly="true" styleId="srhVenderName1" styleClass="noborderAl" tabindex="-1"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellspacing="0" cellpadding="2" border="0">
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">受注番号</td>
											<td>
												<nested:text property="srhOrderNoFrom" maxlength="11" size="12" styleId="srhOrderNoFrom" style="ime-mode:disabled" /> ～
												<nested:text property="srhOrderNoTo" maxlength="11" size="12" styleId="srhOrderNoTo" style="ime-mode:disabled" />
											</td>
											<td class="bcTitleSearch fb fcTitleSearch">ステータス</td>
											<td>
												<%
													pageContext.setAttribute("selectShippingResultShippingStatus",
													new com.asahikaseieng.app.comboboxes.SelectShippingResultShippingStatus(true, false));
												%>
												<nested:select property="srhShippingStatus" styleId="srhShippingStatus">
													<nested:options name="selectShippingResultShippingStatus" property="values" labelName="selectShippingResultShippingStatus" labelProperty="labels" />
												</nested:select>
											</td>
											<td class="bcTitleSearch fb fcTitleSearch">運送会社コード</td>
											<td>
												<nested:select property="srhCarryCd" style="margin: 0;padding: 0" styleId="srhCarryCd">
													<nested:optionsCollection property="carryCombo" label="labales" value="values" />
												</nested:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellspacing="0" cellpadding="2" border="0">
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">ロケーション</td>
											<td>
												<nested:text property="srhLocationCd" maxlength="20" size="12" styleId="srhLocationCd" />
											</td>
											<td>
												<nested:text property="srhLocationName" size="22" readonly="true" styleId="srhLocationName" styleClass="noborderAl" tabindex="-1"/>
											</td>
											<td class="bcTitleSearch fb fcTitleSearch">出荷完了日</td>
											<td>
												<nested:text property="srhShippingResultDateFrom" maxlength="10" size="12" styleId="srhShippingResultDateFrom" style="ime-mode:disabled" /> ～
												<nested:text property="srhShippingResultDateTo" maxlength="10" size="12" styleId="srhShippingResultDateTo" style="ime-mode:disabled" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellspacing="0" cellpadding="2" border="0">
										<tr>
											<td class="bcTitleSearch fb fcTitleSearch">品目</td>
											<td>
												<nested:text property="srhItemCd" maxlength="20" size="12" styleId="srhItemCd" />
											</td>
											<td>
												<nested:text property="srhItemName" size="22" readonly="true" styleId="srhItemName" styleClass="noborderAl" tabindex="-1"/>
											</td>
											<td class="bcTitleSearch fb fcTitleSearch">他社コード1</td>
											<td>
												<nested:text property="srhOtherCompanyCd1" maxlength="20" size="12" styleId="srhOtherCompanyCd1" />
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
												<div id="cssButton">
													<a href="#"
														onclick="if (!(putDirtyConfirm())) {return false;}else{ return form_submit('op', 'search') }; return false;"
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
					<tr>
						<td><!-- 明細部 -->
						<table width="100%" border="0">
							<tr>
								<td class="alignLeft">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
											<div id="cssButton">
												<a href="#" onclick="if (!(completeAlert())) {return false;}else{return form_submit('op', 'complete'); return false;}" class="cssButton">
													&nbsp;完&nbsp;了&nbsp;登&nbsp;録&nbsp;
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
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td>完了</td>
										<td>出荷番号</td>
										<td>受注番号</td>
										<td>納入先</td>
										<td>品目</td>
										<td>荷姿</td>
										<td>出荷予定</td>
										<td>指図量</td>
										<td>実績量</td>
										<td>出荷完了日</td>
										<td>ステータス</td>
									</tr>
									<nested:iterate id="searchList" property="searchList"
										indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<%-- 完了登録チェックボックス --%>
										<td>
											<%-- ステータスが「出荷完了」の場合 --%>
											<nested:equal property="shippingStatus" value="5">
												<nested:checkbox property="checkFlg" styleId="checkFlg" disabled="true"/>
											</nested:equal>
											<%-- ステータスが「実績受信中」の場合 --%>
											<nested:equal property="shippingStatus" value="4">
												<nested:checkbox property="checkFlg" styleId="checkFlg" disabled="true"/>
											</nested:equal>
											<%-- ステータスが「実績受信中」より前の場合 --%>
											<nested:lessThan property="shippingStatus" value="4">
												<nested:checkbox property="checkFlg" styleId="checkFlg" />
											</nested:lessThan>
										</td>
										<%-- 出荷番号 --%>
										<td>
											<nested:define id="sno" property="shippingNo" />
											<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/ShippingResultDetail.do?op=init&shippingNo=" + pageContext.findAttribute("sno").toString() + "'); return false;"%>'>
												<nested:write property="shippingNo" />
											</html:link>
										</td>
										<%-- 受注番号 --%>
										<td>
<%-- 受注番号リンクを一時的に停止20090715 
											<nested:notEmpty property="orderNo">
												<nested:define id="ono" property="orderNo" />
												<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/OrderDetail.do?op=init&orderNo=" + pageContext.findAttribute("ono").toString() + "'); return false;"%>'>
													<nested:write property="orderNo" />
												</html:link>
											</nested:notEmpty>
--%>
											<nested:write property="orderNo" />
										</td>
										<%-- 納入先 --%>
										<td width="150">
											<nested:write property="searchKana" />
										</td>
										<%-- 品目 --%>
										<td width="100">
											<nested:write property="itemName" />
										</td>
										<%-- 荷姿 --%>
										<td>
											<nested:write property="styleOfPacking" />
										</td>
										<%-- 出荷予定日 --%>
										<td>
											<nested:write property="strScheduledShippingDate" />
										</td>
										<%-- 指図量 --%>
										<td class="alignRight">
											<nested:write property="strShippingInstructionSum" />
										</td>
										<%-- 実績量 --%>
										<td class="alignRight">
											<nested:write property="strShippingResultQuantitySum" />
										</td>
										<%-- 出荷完了日 --%>
										<td>
											<nested:write property="strShippingResultDate" />
										</td>
										<%-- 出荷ステータス --%>
										<td>
											<nested:write property="strShippingStatus" />
										</td>
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
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH"
				CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
	</table>
</nested:form>
</body>
</html:html>

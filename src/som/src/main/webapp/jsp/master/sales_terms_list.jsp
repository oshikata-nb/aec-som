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
	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href=url;
				g_lock = true;
			}
		}
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 取引先のautocomplete --%>
		new Ajax.Autocompleter(
			"srhVenderCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/VenderForAutoComplete.do?op=searchVender",
			{
				parameters : 'venderDivision='+$("srhVenderDivision").value,
				paramName : "code",
				afterUpdateElement : setVenderLabel
			}
		);

		<%-- 納入先のautocomplete --%>
		new Ajax.Autocompleter(
			"srhDeliveryCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/DeliveryForAutoComplete.do?op=searchDelivery",
			{
				paramName : "code",
				afterUpdateElement : setDeliveryLabel
			}
		);

		<%-- 品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setItemQueueLabel
			}
		);

		<%-- 他社コードのautocomplete --%>
		new Ajax.Autocompleter(
			"srhOtherCompanyCd1",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueueOtherCompany",
			{
				paramName : "code",
				afterUpdateElement : setOtherCompanyLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhVenderCd', 'keypress', clearLabel.bindAsEventListener($('srhVenderName1')), false);
		Event.observe('srhDeliveryCd', 'keypress', clearLabel.bindAsEventListener($('srhDeliveryName1')), false);
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhStyleOfPacking')), false);
		Event.observe('srhOtherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhOtherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('srhStyleOfPacking')), false);

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);	
		}

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setVenderLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhVenderName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setDeliveryLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhDeliveryName1").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setItemQueueLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		$("srhStyleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		refreshLabel();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemCd").value = getHiddenValue(li,"code");
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhStyleOfPacking").value = getHiddenValue(li,"styleOfPacking");
		refreshLabel();
	}

	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/SalesTermsList" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="srhVenderDivision" styleId="srhVenderDivision"/>

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb" width="250">販売条件マスタ</td>
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
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
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
							<table width="100%" cellspacing="" cellpadding="" border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="130">得意先コード</td>
												<td>
													<nested:text property="srhVenderCd" maxlength="15" size="20" styleId="srhVenderCd"/>
													<div id="autocomplete_choices" class="autocomplete"></div>
												</td>

												<td width="500">
													<div id="lblSrhVenderName1">
														<nested:write property="srhVenderName1" />
													</div>
													<nested:hidden property="srhVenderName1" styleId="srhVenderName1"/>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">納入先コード</td>
												<td>
													<nested:text property="srhDeliveryCd" maxlength="15" size="20" styleId="srhDeliveryCd"/>
												</td>

												<td width="500">
													<div id="lblSrhDeliveryName1">
														<nested:write property="srhDeliveryName1" />
													</div>
													<nested:hidden property="srhDeliveryName1" styleId="srhDeliveryName1"/>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
												<td>
													<nested:text property="srhItemCd" maxlength="20" size="20" styleId="srhItemCd"/>
												</td>

												<td width="500">
													<div id="lblSrhItemName">
														<nested:write property="srhItemName" />
													</div>
													<nested:hidden property="srhItemName" styleId="srhItemName"/>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">他社コード1</td>
												<td colspan="3">
													<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" style="ime-mode:disabled" styleId="srhOtherCompanyCd1"/>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">荷姿</td>
												<td colspan="3">
													<div id="lblSrhStyleOfPacking">
														<nested:write property="srhStyleOfPacking" />
													</div>
													<nested:hidden property="srhStyleOfPacking" styleId="srhStyleOfPacking"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td class="alignRight">
										<table cellspacing="1" cellpadding="1"  border="0">
											<tr>
												<td>
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'search'); return false;" class="cssButton">
															&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
														</a>
													</div>
												</td>

												<td>
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'newPage'); return false;" class="cssButton">
															&nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
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
					<table border="0" cellpadding="0" cellspacing="0" width="750">
						<tr>
							<td>
								<!-- 明細部 -->
								<table width="100%" border="0">
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
										<td>
											<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="65">納入先<br>コード</td>
													<td>納入先名称</td>
													<td>住所</td>
													<td>電話番号</td>
													<td width="65">品目<br>コード</td>
													<td>品目名称</td>
													<td>荷姿</td>
												</tr>

												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

													<nested:define id="oid1" property="deliveryCd"/>
													<nested:define id="oid2" property="balanceCd"/>
													<nested:define id="oid3" property="itemCd"/>

													<td>
														<%-- 納入先コード --%>
														<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/SalesTermsDetail.do?op=init&deliveryCd=" + pageContext.findAttribute("oid1").toString() + "&balanceCd=" + pageContext.findAttribute("oid2").toString() + "&itemCd=" + pageContext.findAttribute("oid3").toString() + "'); return false;"%>'>
															<nested:write property="deliveryCd"/>
														</html:link>
													</td>

													<td>
														<%-- 納入先名称 --%>
														<nested:write property="deliveryName1"/>
													</td>
													<td>
														<%-- 住所 --%>
														<nested:write property="deliveryAddress"/>
													</td>
													<td>
														<%-- 電話番号 --%>
														<nested:write property="deliveryTelNo"/>
													</td>

													<td>
														<%-- 品目コード --%>
														<nested:write property="itemCd"/>
													</td>

													<td>
														<%-- 品目名称 --%>
														<nested:write property="itemName"/>
													</td>

													<td>
														<%-- 荷姿 --%>
														<nested:write property="styleOfPacking"/>
													</td>


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
								<%@ include file="/jsp/common/pager/pager.jsf" %>
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

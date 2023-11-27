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

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strSrhActiveDateFrom");
		defineAsDateField("strSrhActiveDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
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

		<%-- 親品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhParentItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchDetailItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setParentItemQueueLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		Event.observe('srhOtherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhParentItemCd', 'keypress', clearLabel.bindAsEventListener($('srhParentItemName')), false);

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);	
		}

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setItemQueueLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		refreshLabel();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhItemCd").value = getHiddenValue(li,"code");
		refreshLabel();
	}

	function setParentItemQueueLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhParentItemName").value = li.getElementsByTagName('span')[0].innerText;
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

<nested:form action="/ItemList" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>

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
									<td class="fcTitle fb" width="250">品目マスタ</td>
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
									<td class="bcTitleLine" colspan="4"></td>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
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
							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1"  border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch" width="150">品目コード</td>
												<td>
													<nested:text property="srhItemCd" maxlength="20" size="20" styleId="srhItemCd"/>
													<div id="autocomplete_choices" class="autocomplete"></div>
												</td>

												<td colspan="2" width="450">
													<div id="lblSrhItemName">
														<nested:write property="srhItemName" />
													</div>
													<nested:hidden property="srhItemName" styleId="srhItemName"/>
												</td>
											</tr>

											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">親品目コード</td>
												<td>
													<nested:text property="srhParentItemCd" maxlength="20" size="20" styleId="srhParentItemCd"/>
												</td>

												<td colspan="2" width="450">
													<div id="lblSrhParentItemName">
														<nested:write property="srhParentItemName" />
													</div>
													<nested:hidden property="srhParentItemName" styleId="srhParentItemName"/>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">他社コード1</td>
												<td colspan="3">
													<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" styleId="srhOtherCompanyCd1" style="ime-mode:disabled"/>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">基本情報STS</td>
												<td colspan="3">
													<% pageContext.setAttribute("SelectItemStatus", new com.asahikaseieng.app.comboboxes.SelectItemStatus(true, false)); %>
													<nested:select property="srhStatus">
														<nested:options name="SelectItemStatus" property="values" labelName="SelectItemStatus" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">在庫･単価情報STS</td>
												<td colspan="3">
													<% pageContext.setAttribute("SelectItemDetailStatus", new com.asahikaseieng.app.comboboxes.SelectItemStatus(true, false)); %>
													<nested:select property="srhDetailStatus">
														<nested:options name="SelectItemDetailStatus" property="values" labelName="SelectItemDetailStatus" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">有効日</td>
												<td colspan="3">
													<nested:text property="strSrhActiveDateFrom" maxlength="10" size="10" styleId="strSrhActiveDateFrom" style="ime-mode:disabled"/>
													～
													<nested:text property="strSrhActiveDateTo" maxlength="10" size="10" styleId="strSrhActiveDateTo" style="ime-mode:disabled"/>
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
													<td width="100">品目コード</td>
													<td>V</td>
													<td>品目名称</td>
													<td width="140">基本情報STS</td>
													<td width="140">在庫･単価情報STS</td>
													<td width="80">有効日</td>
													<td width="40">有効</td>
												</tr>

												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

	    											<nested:define id="oid1" property="itemCd"/>
	    											<nested:define id="oid2" property="version"/>

													<td>
														<%-- 品目コード --%>
	        											<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/ItemDetail.do?op=init&itemCd=" + pageContext.findAttribute("oid1").toString() + "&version=" + pageContext.findAttribute("oid2").toString() + "'); return false;"%>'>
	        												<nested:write property="itemCd"/>
	        											</html:link>
													</td>

													<td class="alignRight">
														<%-- バージョン --%>
														<nested:write property="version"/>
													</td>

													<td>
														<%-- 品目名称 --%>
														<nested:write property="itemName"/>
													</td>

													<td>
														<%-- 基本情報ステータス --%>
														<nested:write property="statusName"/>
													</td>

													<td>
														<%-- 在庫・単価情報ステータス --%>
														<nested:write property="detailStatusName"/>
													</td>

													<td>
														<%-- 有効日 --%>
														<nested:write property="strActiveDate"/>
													</td>

													<td>
														<%-- 有効 --%>
														<nested:write property="activate"/>
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

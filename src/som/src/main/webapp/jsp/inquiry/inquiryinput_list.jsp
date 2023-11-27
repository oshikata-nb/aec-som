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
	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strSrhCountDate");
		defineAsRequiredField("srhLocationCd");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("strSrhCountDate");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("strInputQty" + i);　i++) {
					<%-- 必須フィールド定義 --%>
					defineAsRequiredField("strInputQty");
					defineAsRequiredField("strInputfraction");

					<%-- 数値型フィールド定義 --%>
					defineAsNumberField("strInputQty" + i);
					defineAsNumberField("strInputfraction" + i);
				}
			}
		}

		<%-- ロケーションのautocomplete --%>
		new Ajax.Autocompleter(
			"srhLocationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LocationForAutoComplete.do?op=searchLocation",
			{
				paramName : "code",
				afterUpdateElement : setLocationLabel
			}
		);

		<%-- 品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchItem",
			{
				paramName : "code",
				afterUpdateElement : setItemLabel
			}
		);

		<%-- 他社コードのautocomplete --%>
		new Ajax.Autocompleter(
			"srhOtherCompanyCd1",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/ItemForAutoComplete.do?op=searchDetailItemOtherCompany",
			{
				paramName : "code",
				afterUpdateElement : setOtherCompanyLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhLocationCd', 'keypress', clearLabel.bindAsEventListener($('srhLocationName')), false);
		Event.observe('srhItemCd', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);
		Event.observe('srhItemCd', 'keypress', clearText.bindAsEventListener($('srhOtherCompanyCd1')), false);
		Event.observe('srhOtherCompanyCd1', 'keypress', clearLabel.bindAsEventListener($('srhItemName')), false);

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			storeInitValues(/^srh.*/);
		}

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setLocationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhLocationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setItemLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		$("srhOtherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
		refreshLabel();
	}

	function setOtherCompanyLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemCd").value = getHiddenValue(li,"code");
		$("srhItemName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").checked;
		if (flg) {
			/* 何か値が変更されている場合 */
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}
	
	
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/InquiryInputList" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="srhLink" styleId="srhLink" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />

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
									<td class="fcTitle fb" width="250">棚卸入力</td>
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
							<!-- 明細部 -->
							<table width="100%" cellspacing="" cellpadding="" border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="1" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="150">棚卸準備処理日</td>
												<td colspan="2">
													<nested:text property="strSrhCountDate" maxlength="10" size="10" styleId="strSrhCountDate" style="ime-mode:disabled" onchange="setDirtyFlg();"/>
												</td>
											</tr>
			
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">ロケーションコード</td>
												<td>
													<nested:text property="srhLocationCd" maxlength="20" size="20" styleId="srhLocationCd" onchange="setDirtyFlg();" />
													<div id="autocomplete_choices" class="autocomplete"></div>
												</td>
			
												<td width="500">
													<div id="lblSrhLocationName">
														<nested:write property="srhLocationName" />
													</div>
													<nested:hidden property="srhLocationName" styleId="srhLocationName"/>
												</td>
											</tr>
			
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">棚卸区分</td>
												<td colspan="2">
													<nested:select property="srhCountDivision" onblur="setDirtyFlg();" >
														<nested:options property="srhCountDivisionValues" labelProperty="srhCountDivisionLabels" />
													</nested:select>
												</td>
											</tr>
			
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
												<td>
													<nested:text property="srhItemCd" maxlength="20" size="20" styleId="srhItemCd" onchange="setDirtyFlg();" />
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
												<td colspan="2">
													<nested:text property="srhOtherCompanyCd1" maxlength="20" size="20" styleId="srhOtherCompanyCd1" style="ime-mode:disabled" onchange="setDirtyFlg();" />
												</td>
											</tr>
			
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">原料ロット番号<BR>製品ロット番号</td>
												<td colspan="2">
													<nested:text property="srhAliasLotNo" maxlength="20" size="20" styleId="srhAliasLotNo" style="ime-mode:disabled" onchange="setDirtyFlg();" />
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
											<table width="100%" cellspacing="2" cellpadding="2" border="0" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td rowspan="2">棚卸区分</td>
													<td rowspan="2">品目名称</td>
													<td rowspan="2">入荷ロット番号<BR>包装指図番号</td>
													<td rowspan="2">原料ロット番号<BR>製品ロット番号</td>
													<td colspan="2">荷姿数量</td>
													<td colspan="2">端数重量(Kg)</td>
												</tr>
				
												<tr class="bcTitleList fb fcTitleList">
													<td>ｼｽﾃﾑ在庫</td>
													<td>実棚数</td>
													<td>ｼｽﾃﾑ在庫</td>
													<td>実棚数</td>
												</tr>

												<nested:notEmpty property="searchList">
													<nested:iterate id="searchList" property="searchList" indexId="index">
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
															<tr class="bcList1">
														</app:modulo>
				
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
															<tr class="bcList2">
														</app:modulo>
				
														<td>
															<%-- 棚卸区分名称 --%>
															<nested:write property="name01" />
														</td>
				
														<td>
															<%-- 品目名称 --%>
															<nested:write property="itemName" />
														</td>
				
														<td>
															<%-- 入荷ロット番号/包装指図番号 --%>
															<nested:write property="lotNo" />
														</td>
				
														<td>
															<%-- 原料ロット番号/製品ロット番号 --%>
															<nested:write property="aliasLotNo" />
														</td>
				
														<td class="alignRight">
															<%-- 荷姿システム在庫 --%>
															<nested:write property="strCountQty" />
														</td>
				
														<td class="alignRight">
															<%-- 荷姿実棚数 --%>
															<nested:notEqual property="srhLink" value="1">
																<nested:text property="strInputQty" maxlength="22" size="8" style="alignRight ; ime-mode:disabled" styleId="<%="strInputQty" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" />
															</nested:notEqual>
					
															<nested:equal property="srhLink" value="1">
																<nested:write property="strInputQty" />
															</nested:equal>
														</td>
				
														<td class="alignRight">
															<%-- 端数システム在庫 --%>
															<nested:write property="strFractionQty" />
														</td>
				
														<td class="alignRight">
															<%-- 端数実棚数 --%>
															<nested:notEqual property="srhLink" value="1">
																<nested:text property="strInputfraction" maxlength="22" size="8" style="alignRight ; ime-mode:disabled" styleId="<%="strInputfraction" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" />
															</nested:notEqual>
					
															<nested:equal property="srhLink" value="1">
																<nested:write property="strInputfraction" />
															</nested:equal>
														</td>
													</nested:iterate>
												</nested:notEmpty>
											</table>
										</td>
									</tr>

									<tr>
										<td class="alignCenter">
											<table cellpadding="0" cellspacing="0" width="450" border="0">
												<tr>
													<td colspan="2" height="2">
													</td>
												</tr>
				
												<tr>
													<nested:notEqual property="srhLink" value="1">
														<nested:equal property="updateAuthority" value="true">
															<td class="alignRight">
																<div id="cssButton">
																	<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																		&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
																	</a>
																</div>
															</td>
						
															<td width="50">
																<br>
															</td>
														</nested:equal>
					
														<td class="alignLeft">
															<div id="cssButton">
																<a href="#" onclick="return form_submit('op', 'clear'); return false;" class="cssButton">
																	&nbsp;&nbsp;ク&nbsp;リ&nbsp;ア&nbsp;&nbsp;
																</a>
															</div>
														</td>
													</nested:notEqual>
	
													<nested:equal property="srhLink" value="1">
														<td class="alignCenter">
															<div id="cssButton">
																<a href="#" onclick="return form_submit('op', 'clear'); return false;" class="cssButton">
																	&nbsp;&nbsp;ク&nbsp;リ&nbsp;ア&nbsp;&nbsp;
																</a>
															</div>
														</td>
													</nested:equal>
												</tr>

												<tr>
													<td height="5" colspan="2"></td>
												</tr>
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

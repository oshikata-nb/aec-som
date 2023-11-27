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
		defineAsDateField("strSrhInputDateFrom");
		defineAsDateField("strSrhInputDateTo");

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- コピー元・削除品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCdFrom",
			"autocomplete_itemcd_from",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setItemQueueLabelFrom
			}
		);
		
		<%-- コピー先品目のautocomplete --%>
		new Ajax.Autocompleter(
			"srhItemCdTo",
			"autocomplete_itemcd_to",
			"<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
			{
				paramName : "code",
				afterUpdateElement : setItemQueueLabelTo
			}
		);
		
		<%-- 入力者のautocomplete --%>
		new Ajax.Autocompleter(
			"srhTantoCd",
			"autocomplete_tantocd",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setLoginLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhItemCdFrom', 'keypress', clearLabel.bindAsEventListener($('srhItemNameFrom')), false);
		Event.observe('srhItemCdTo', 'keypress', clearLabel.bindAsEventListener($('srhItemNameTo')), false);
		Event.observe('srhTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhtantoName')), false);
		
		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);	
		}

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setItemQueueLabelFrom(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemNameFrom").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setItemQueueLabelTo(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhItemNameTo").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	
	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhTantoName").value = li.getElementsByTagName('span')[0].innerText;
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
	
	<%-- 確定確認メッセージ --%>
	function putConfirm() {
		<%-- カーソルセット --%>
		alertMsg = new Array();
		alertMsg[0] = "確定してよろしいですか？";
		
		return confirm(alertMsg[0]);
	}
	<%-- 確定取消メッセージ --%>
	function putConfirmCancel() {
		<%-- カーソルセット --%>
		alertMsg = new Array();
		alertMsg[0] = "確定取消してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 全部チェックを入れる・外す --%>
	function setTargetCheckAll(bol) {
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("salestermsAndEstimateCheckBox" + i);　i++) {
					$("salestermsAndEstimateCheckBox" + i).checked = bol;
				}
			}
		}
		return false;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/SalestermsAndEstimateList" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb" width="300">販売条件・見積単価　コピー作成・削除</td>
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
												<td class="bcTitleSearch fb fcTitleSearch" width="125">入力日</td>
												<td>
													<nested:text property="strSrhInputDateFrom" maxlength="10" size="10" styleId="strSrhInputDateFrom" style="ime-mode:disabled"/>
													～
													<nested:text property="strSrhInputDateTo" maxlength="10" size="10" styleId="strSrhInputDateTo" style="ime-mode:disabled"/>
												</td>
												<td width="100"></td>
												<td class="bcTitleSearch fb fcTitleSearch" width="100">入力者</td>
												<td>
													<nested:text property="srhTantoCd" maxlength="10" size="10" styleId="srhTantoCd"/>
													<div id="autocomplete_Tantocd" class="autocomplete"></div>
												</td>

												<td width="80">
													<div id="lblSrhTantoName">
														<nested:write property="srhTantoName" />
													</div>
													<nested:hidden property="srhTantoName" styleId="srhTantoName"/>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">処理区分</td>
												<td>
													<nested:select property="srhProcessDivision" styleId="srhProcessDivision">
														<nested:options property="srhProcessDivisionValues" labelProperty="srhProcessDivisionLabels" />
													</nested:select>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">ステータス</td>
												<td>
													<nested:select property="srhStatus" styleId="srhStatus">
														<nested:options property="srhStatusValues" labelProperty="srhStatusLabels" />
													</nested:select>
												</td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1"  border="0">
 											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">コピー元・削除品目</td>
												<td>
													<nested:text property="srhItemCdFrom" maxlength="20" size="10" styleId="srhItemCdFrom"/>
													<div id="autocomplete_itemcd_from" class="autocomplete"></div>
												</td>

												<td>
													<div id="lblSrhItemNameFrom">
														<nested:write property="srhItemNameFrom" />
													</div>
													<nested:hidden property="srhItemNameFrom" styleId="srhItemNameFrom"/>
												</td>
											</tr>
 											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">コピー先品目</td>
												<td>
													<nested:text property="srhItemCdTo" maxlength="20" size="10" styleId="srhItemCdTo"/>
													<div id="autocomplete_itemcd_to" class="autocomplete"></div>
												</td>

  												<td>
													<div id="lblSrhItemNameTo">
														<nested:write property="srhItemNameTo" />
													</div>
													<nested:hidden property="srhItemNameTo" styleId="srhItemNameTo"/>
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
							<td><!-- 明細部 -->
								<table width="100%" border="0">
									<tr>
										<td class="alignRight">
											<table width="100%" border="0">
												<tr>
													<td class="alignLeft">
														<div id="cssButton">
															<a href="#" onClick="setTargetCheckAll(true);return false;" class="cssButton">全&nbsp;&nbsp;選&nbsp;&nbsp;択</a>
															<a href="#" onClick="setTargetCheckAll(false);return false;" class="cssButton">全&nbsp;&nbsp;解&nbsp;&nbsp;除</a>
														</div>
													</td>
													<td  class="alignRight">
														<div id="cssButton">
															<nested:equal property="confirmAuthority" value="true">
																<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'confirm'); return false;}" class="cssButton">
																	&nbsp;&nbsp;確&nbsp;&nbsp;定&nbsp;&nbsp;
																</a>
															</nested:equal>
															&nbsp;&nbsp;
															<nested:equal property="confirmCancelAuthority" value="true">
																<a href="#" onclick="if (!(putConfirmCancel())) {return false;} else{return form_submit('op', 'confirmCancel'); return false;}" class="cssButton">
																	&nbsp;&nbsp;確定取消&nbsp;&nbsp;
																</a>
															</nested:equal>
															&nbsp;&nbsp;
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
													<td width="20" rowspan="2">選択</td>
													<td width="50">入力日</td>
													<td width="180">処理区分</td>
													<td colspan="2">コピー元・削除品目</td>
													<td width="80" rowspan="2">荷姿</td>
													<td width="80" rowspan="2">他社コード1</td>
													<td rowspan="2">品目</td>
												</tr>
												<tr class="bcTitleList fb fcTitleList">
													<td>入力者</td>
													<td>ステータス</td>
													<td colspan="2">コピー先品目</td>
												</tr>
												
												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
													
													<nested:define id="oid1" property="pkNo"/>
													
														<%-- チェックボックス --%>
														<td rowspan="2">
															<nested:checkbox property="salestermsAndEstimateCheckBox" styleId="<%="salestermsAndEstimateCheckBox" + pageContext.findAttribute("index").toString() %>" />
															<nested:hidden   property="salestermsAndEstimateCheckBox"  styleId="salestermsAndEstimateCheckBox" value="0" />
														</td>
														<%-- 入力日 --%>
														<td>
															<nested:write property="strSrhInputDate"/>
														</td>
														<%-- 処理区分 --%>
														<td>
															<nested:write property="processName"/>
														</td>
														<%-- コピー元・削除品目コード --%>
														<td>
															<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/SalestermsAndEstimateDetail.do?op=init&pkNo=" + pageContext.findAttribute("oid1").toString() + "'); return false;"%>'>
															<nested:write property="srhItemCdFrom"/>
															</html:link>
														</td>
														<%-- コピー元・削除品目名称 --%>
														<td>
															<nested:write property="srhItemNameFrom"/>
														</td>
														<%-- 荷姿（コピー元・削除品目） --%>
														<td>
															<nested:write property="styleOfPackingFrom"/>
														</td>
														<%-- 他社コード1（コピー元・削除品目） --%>
														<td>
															<nested:write property="srhOtherCompanyCd1From"/>
														</td>
														<%-- 品目（コピー元・削除品目の有効） --%>
														<td>
															<nested:write property="activateFrom"/>
														</td>
														
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>
													
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>
														<%-- 入力者 --%>
														<td>
															<nested:write property="srhTantoName"/>
														</td>
														<%-- ステータス --%>
														<td>
															<nested:write property="statusName"/>
														</td>
														<%-- コピー先品目コード --%>
														<td>
															<nested:write property="srhItemCdTo"/>
														</td>
														<%-- コピー先品目名称 --%>
														<td>
															<nested:write property="srhItemNameTo"/>
														</td>
														<%-- 荷姿（コピー先品目） --%>
														<td>
															<nested:write property="styleOfPackingTo"/>
														</td>
														<%-- 他社コード1（コピー先品目） --%>
														<td>
															<nested:write property="srhOtherCompanyCd1To"/>
														</td>
														<%-- 品目（コピー先品目の有効） --%>
														<td>
															<nested:write property="activateTo"/>
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

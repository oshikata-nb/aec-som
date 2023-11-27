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

		<%-- 得意先のautocomplete --%>
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

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhVenderCd', 'keypress', clearLabel.bindAsEventListener($('srhVenderName1')), false);

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

<nested:form action="/BalanceList" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb" width="250">帳合マスタ</td>
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
												<td class="bcTitleSearch fb fcTitleSearch" width="130">区分</td>
												<td>
													<% pageContext.setAttribute("SelectBalanceType", new com.asahikaseieng.app.comboboxes.SelectBalanceType(true, false)); %>
													<nested:select property="srhBalanceType" >
														<nested:options name="SelectBalanceType" property="values" labelName="SelectBalanceType" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">得意先コード</td>
												<td>
													<nested:text property="srhVenderCd" maxlength="15" size="15" styleId="srhVenderCd"/>
													<div id="autocomplete_choices" class="autocomplete"></div>
												</td>

												<td>
													<div id="lblSrhVenderName1">
														<nested:write property="srhVenderName1" />
													</div>
													<nested:hidden property="srhVenderName1" styleId="srhVenderName1"/>
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
													<td width="80">帳合コード</td>
													<td>区分</td>
													<td>得意先名称</td>
													<td>二次店名称</td>
													<td>三次店名称</td>
													<td>四次店名称</td>
													<td>五次店名称</td>
												</tr>

												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

													<nested:define id="oid1" property="balanceCd"/>

													<td>
														<%-- 帳合コード --%>
														<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/BalanceDetail.do?op=init&balanceCd=" + pageContext.findAttribute("oid1").toString() + "'); return false;"%>'>
															<nested:write property="balanceCd"/>
														</html:link>
													</td>

													<td>
														<%-- 区分 --%>
														<nested:write property="balanceTypeName"/>
													</td>

													<td>
														<%-- 得意先名称 --%>
														<nested:write property="venderName1"/>
													</td>

													<td>
														<%-- 二次店名称 --%>
														<nested:write property="venderName2"/>
													</td>

													<td>
														<%-- 三次店名称 --%>
														<nested:write property="venderName3"/>
													</td>

													<td>
														<%-- 四次店名称 --%>
														<nested:write property="venderName4"/>
													</td>

													<td>
														<%-- 五次店名称 --%>
														<nested:write property="venderName5"/>
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

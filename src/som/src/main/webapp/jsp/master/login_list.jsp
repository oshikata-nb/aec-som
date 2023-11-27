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

		<%-- 部署のautocomplete --%>
		new Ajax.Autocompleter(
			"srhOrganizationCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
			{
				paramName : "code",
				afterUpdateElement : setOrganizationLabel
			}
		);

		<%-- 担当者のautocomplete --%>
		new Ajax.Autocompleter(
			"srhTantoCd",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/LoginForAutoComplete.do?op=searchLogin",
			{
				paramName : "code",
				afterUpdateElement : setLoginLabel
			}
		);

		<%-- 役職のautocomplete --%>
		new Ajax.Autocompleter(
			"srhPostId",
			"autocomplete_choices",
			"<%= request.getContextPath() %>/PostForAutoComplete.do?op=searchPost",
			{
				paramName : "code",
				afterUpdateElement : setPostLabel
			}
		);

		<%-- 検索後入力された場合の不整合をただす --%>
		Event.observe('srhOrganizationCd', 'keypress', clearLabel.bindAsEventListener($('srhOrganizationName')), false);
		Event.observe('srhTantoCd', 'keypress', clearLabel.bindAsEventListener($('srhtantoNm')), false);
		Event.observe('srhPostId', 'keypress', clearLabel.bindAsEventListener($('srhPostName')), false);

		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/, 'tantoSelCondition');
		}

		refreshLabel();
	}, false);

	<%-- ajax --%>
	function setOrganizationLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhOrganizationName").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhTantoNm").value = li.getElementsByTagName('span')[0].innerText;
		refreshLabel();
	}

	function setPostLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		$("srhPostName").value = li.getElementsByTagName('span')[0].innerText;
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

<nested:form action="/LoginList" method="post" onsubmit="return false;" styleId="mainForm">	
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
									<td class="fcTitle fb" width="250">担当者マスタ</td>
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
												<td class="bcTitleSearch fb fcTitleSearch" width="130">部署コード</td>
												<td>
													<nested:text property="srhOrganizationCd" maxlength="10" size="10" styleId="srhOrganizationCd"/>
													<div id="autocomplete_choices" class="autocomplete"></div>
												</td>

												<td width="500">
													<div id="lblSrhOrganizationName">
														<nested:write property="srhOrganizationName" />
													</div>
													<nested:hidden property="srhOrganizationName" styleId="srhOrganizationName"/>
												</td>
											</tr>

											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch" width="130">担当者コード</td>
												<td>
													<nested:text property="srhTantoCd" maxlength="10" size="10" styleId="srhTantoCd"/>
												</td>

												<td width="500">
													<div id="lblSrhTantoNm">
														<nested:write property="srhTantoNm" />
													</div>
													<nested:hidden property="srhTantoNm" styleId="srhTantoNm"/>
												</td>
											</tr>

											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch" width="130">役職コード</td>
												<td>
													<nested:text property="srhPostId" maxlength="10" size="10" styleId="srhPostId"/>
												</td>

												<td width="500">
													<div id="lblSrhPostName">
														<nested:write property="srhPostName" />
													</div>
													<nested:hidden property="srhPostName" styleId="srhPostName"/>
												</td>
											</tr>

											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="130">検索区分</td>
												<td colspan="2">
													<% pageContext.setAttribute("radioTantoSelCondition", new com.asahikaseieng.app.radiobuttons.RadioTantoSelCondition()); %>
													<logic:iterate id="mp" name="radioTantoSelCondition" property="map">
														<nested:radio idName="mp" property="tantoSelCondition" 
															styleId="tantoSelCondition" value="key" />
														<bean:write name="mp" property="value" />
													</logic:iterate>
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

												<nested:notEqual property="loginAdminFlg" value="1">
													<td>
														<div id="cssButton">
															<a href="#" onclick="return form_submit('op', 'newPage'); return false;" class="cssButton">
																&nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
															</a>
														</div>
													</td>
												</nested:notEqual>
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
													<td width="130">担当者コード</td>
													<td>担当者名称</td>
													<td>部署名称</td>
													<td>役職名称</td>
												</tr>

												<nested:iterate id="searchList" property="searchList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

	    											<nested:define id="oid1" property="tantoCd"/>

													<logic:equal name="loginListForm" property="loginAdminFlg" value="1">
														<logic:equal name="loginListForm" property="loginTantoCd" value='<%=pageContext.findAttribute("oid1").toString()%>'>
															<td>
																<%-- 担当者コード --%>
			        											<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/LoginDetail.do?op=init&tantoCd=" + pageContext.findAttribute("oid1").toString() + "'); return false;"%>'>
			        												<nested:write property="tantoCd"/>
			        											</html:link>
															</td>
														</logic:equal>

														<logic:notEqual name="loginListForm" property="loginTantoCd" value='<%=pageContext.findAttribute("oid1").toString()%>'>
															<td>
																<nested:write property="tantoCd" />
															</td>
														</logic:notEqual>
													</logic:equal>
													
													<logic:notEqual name="loginListForm" property="loginAdminFlg" value="1">
														<td>
															<%-- 担当者コード --%>
		        											<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/LoginDetail.do?op=init&tantoCd=" + pageContext.findAttribute("oid1").toString() + "'); return false;"%>'>
																<nested:write property="tantoCd" />
															</html:link>
														</td>
													</logic:notEqual>
													
													<td>
														<%-- 担当者名称 --%>
														<nested:write property="tantoNm"/>
													</td>

													<td>
														<%-- 部署名称 --%>
														<nested:write property="organizationName"/>
													</td>

													<td>
														<%-- 役職名称 --%>
														<nested:write property="postName"/>
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

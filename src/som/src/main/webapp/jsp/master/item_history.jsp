<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>' media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- 基本画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemDetail.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 在庫・単価画面への遷移 --%>
	function toAttribute(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemAttribute.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 成分画面への遷移 --%>
	function toComponent(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemComponent.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 関連画面への遷移 --%>
	function toRelated(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemRelated.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 技術画面への遷移 --%>
	function toTech(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemTech.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- その他画面への遷移 --%>
	function toOther(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemOther.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();
	}, false);
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ItemHistory" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="headItemCd" styleId="headItemCd"/>
	<nested:hidden property="headVersion" styleId="headVersion"/>

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
									<td class="fcTitle fb">品目マスタ</td>
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
							<!-- 明細部 -->
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">品目コード</td>
									<td>
										<nested:write property="itemCd"/>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail" width="130">品目名称</td>
									<td>
										<nested:write property="headItemName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">有効日</td>
									<td>
										<nested:write property="strHeadActiveDate"/>
									</td>

									<td class="fcTitleDetail fb bcTitleDetail">有効</td>
									<td>
										<nested:write property="headActivate"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" rowspan="2">ステータス</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">基本情報</td>
									<td>
										<nested:write property="headDetailStatusName"/>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">在庫・単価情報</td>
									<td>
										<nested:write property="headAttributeStatusName"/>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="5"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="5"></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
										<span id="slidetabsmenu">
											<ul>
												<li><html:link href="#" onclick='toDetail();return false;' title="基本"><span>基本</span></html:link></li>
												<li><html:link href="#" onclick='toAttribute();return false;' title="在庫・単価"><span>在庫・単価</span></html:link></li>
												<li><html:link href="#" onclick='toComponent();return false;'  title="成分"><span>成分</span></html:link></li>
												<li><html:link href="#" onclick='toRelated();return false;' title="関連"><span>関連</span></html:link></li>
												<li><html:link href="#" onclick='toTech();return false;' title="技術"><span>技術</span></html:link></li>
												<li><html:link href="#" onclick='toOther();return false;' title="その他"><span>その他</span></html:link></li>
												<li class="current"><a title="更新"><span>更新</span></a></li>
											</ul>
										</span>

										<span id="slidetabs" style="clear: left;">
											<table cellspacing="1" cellpadding="1" width="100%" border="0">
												<tr>
													<td class="fcTitle fb">更新情報</td>
												</tr>

												<tr>
													<td class="bcTitleLine" colspan="3"></td>
												</tr>
											</table>

											<table cellpadding="2" cellspacing="2" width="100%" border="0">
												<tr class="bcTitleList fb fcTitleList">
													<td width="80">更新日</td>
													<td>更新理由</td>
													<td>担当者名</td>
												</tr>
				
												<nested:notEmpty property="searchHistoryList">
													<nested:iterate id="searchHistoryList" property="searchHistoryList" indexId="index">
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
															<tr class="bcList1">
														</app:modulo>
				
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
															<tr class="bcList2">
														</app:modulo>
				
														<td>
															<%-- 更新日 --%>
															<nested:write property="strUpdateDate" />
														</td>
				
														<td>
															<%-- 更新理由 --%>
															<nested:write property="reason" />
														</td>
				
														<td>
															<%-- 担当者名 --%>
															<nested:write property="tantoNm" />
														</td>
													</nested:iterate>
												</nested:notEmpty>
											</table>
										</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="450" border="0">
								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>

								<tr>
									<td class="alignCenter">
										<div id="cssButton">
											<a href="#" onclick="return form_submit('op', 'back'); return false;" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
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
	</table>
</nested:form>

</body>
</html:html>

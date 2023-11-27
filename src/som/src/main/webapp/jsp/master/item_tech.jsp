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

	<%-- その他画面への遷移 --%>
	function toOther(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemOther.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 更新画面への遷移 --%>
	function toHistory(url) {
		if (!g_lock) {
			if (continueConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemHistory.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

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

	<%-- リンクセット --%>
	function setLink() {
		$("downloadDiv").value = "ITEM_LINK";
	}

	<%-- 技術セット --%>
	function setTech() {
		$("downloadDiv").value = "ITEM_TECH";
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ItemTech" method="post" onsubmit="return false;" styleId="mainForm" enctype="multipart/form-data">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="headItemCd" styleId="headItemCd"/>
	<nested:hidden property="headVersion" styleId="headVersion"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="downloadDiv" styleId="downloadDiv"/>

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
												<li class="current"><a title="技術"><span>技術</span></a></li>
												<li><html:link href="#" onclick='toOther();return false;' title="その他"><span>その他</span></html:link></li>
												<li><html:link href="#" onclick='toHistory();return false;' title="更新"><span>更新</span></html:link></li>
											</ul>
										</span>

										<span id="slidetabs" style="clear: left;">
											<table cellpadding="2" cellspacing="2" width="100%" border="0">
												<tr>
													<td class="fcTitleDetail fb bcTitleDetail" width="130">リンク情報</td>
													<td>
														<nested:file property="linkUploadFile" size="20" onchange="setDirtyFlg();" />
														<div id="cssButton">
															<nested:text property="dispLinkLabelPath" styleId="dispLinkLabelPath" readonly="true" size="40" onchange="setDirtyFlg();" />

															<a href="#" class="cssButton"
																onclick="setLink(); return form_submit('op', 'filedownload'); return false;">&nbsp;DOWN&nbsp;
															</a>
														</div>
													</td>
												</tr>

												<tr>
													<td class="fcTitleDetail fb bcTitleDetail">技術情報</td>
													<td>
														<nested:file property="techUploadFile" size="20" onchange="setDirtyFlg();" />
														<div id="cssButton">
															<nested:text property="dispTechLabelPath" styleId="dispTechLabelPath" readonly="true" size="40" onchange="setDirtyFlg();" />

															<a href="#" class="cssButton"
																onclick="setTech(); return form_submit('op', 'filedownload'); return false;">&nbsp;DOWN&nbsp;
															</a>
														</div>
													</td>
												</tr>
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
											<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="downloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
	</table>
</nested:form>

</body>
</html:html>

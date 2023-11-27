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
			if (putDirtyConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemDetail.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 在庫・単価画面への遷移 --%>
	function toAttribute(url) {
		if (!g_lock) {
			if (putDirtyConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemAttribute.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 関連画面への遷移 --%>
	function toRelated(url) {
		if (!g_lock) {
			if (putDirtyConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemRelated.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 技術画面への遷移 --%>
	function toTech(url) {
		if (!g_lock) {
			if (putDirtyConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemTech.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- その他画面への遷移 --%>
	function toOther(url) {
		if (!g_lock) {
			if (putDirtyConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemOther.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
				g_lock = true;
			}
		}
	}

	<%-- 更新画面への遷移 --%>
	function toHistory(url) {
		if (!g_lock) {
			if (putDirtyConfirm()) {
				location.href="<%= request.getContextPath() %>" + "/ItemHistory.do?op=init&itemCd=" + $("headItemCd").value + "&version=" + $("headVersion").value;
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

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("strCalcValue" + i); i++) {
					<%-- 必須フィールド定義 --%>
			        defineAsRequiredField("componentCd" + i);

					<%-- 数値型フィールド定義 --%>
					defineAsNumberField("strCalcValue" + i);
				}
			}
		}

		<%-- 成分のautocomplete --%>
		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("componentCd" + i); i++) {
					new Ajax.Autocompleter(
						"searchComponentList[" + i + "].componentCd",
						"autocomplete_choices" + i,
						"<%= request.getContextPath() %>/ComponentForAutoComplete.do?op=searchComponent",
						{
							paramName : "code",
							afterUpdateElement : eval("setComponentLabel" + i)
						}
					);
				}
			}
		}

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg', /^checked.*/);

		refreshLabel();
	}, false);

	<%-- ajax --%>
	<logic:iterate id="list" name="itemComponentForm" property="searchComponentList" indexId="index">
		function setComponentLabel<bean:write name='index'/>(text, li) {
			$("searchComponentList[<bean:write name='index'/>].componentName").value = li.getElementsByTagName('span')[0].innerText;
			$("lblComponentName<bean:write name='index'/>").innerHTML = li.getElementsByTagName('span')[0].innerText;
			setDirtyFlg();
		}
	</logic:iterate>

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 確認メッセージ:行削除 --%>
	function putDelListConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "行を削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg) {
			/* 何か値が変更されている場合 */
			<%-- return continueConfirm(); --%>
			return confirm("<bean:message key="message.confirm.under.edit" />");
		}else{
			return true;
		}
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ItemComponent" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="headItemCd" styleId="headItemCd"/>
	<nested:hidden property="headVersion" styleId="headVersion"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>

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
												<li class="current"><a title="成分"><span>成分</span></a></li>
												<li><html:link href="#" onclick='toRelated();return false;' title="関連"><span>関連</span></html:link></li>
												<li><html:link href="#" onclick='toTech();return false;' title="技術"><span>技術</span></html:link></li>
												<li><html:link href="#" onclick='toOther();return false;' title="その他"><span>その他</span></html:link></li>
												<li><html:link href="#" onclick='toHistory();return false;' title="更新"><span>更新</span></html:link></li>
											</ul>
										</span>

										<span id="slidetabs" style="clear: left;">
											<table cellpadding="2" cellspacing="2" width="100%" border="0" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="20"></td>
													<td width="80">成分コード</td>
													<td>成分名称</td>
													<td width="100">有効分</td>
													<td width="100">表示名称</td>
												</tr>
				
												<nested:notEmpty property="searchComponentList">
													<nested:iterate id="searchComponentList" property="searchComponentList" indexId="index">
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
															<tr class="bcList1">
														</app:modulo>
				
														<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
															<tr class="bcList2">
														</app:modulo>
				
														<td><%-- 行追加行削除用チェックボックス --%>
															<nested:checkbox property="checked" styleId="<%="checked" + pageContext.findAttribute("index").toString() %>" />
														</td>
				
														<td>
															<%-- 成分コード --%>
															<nested:text property="componentCd" maxlength="20" size="20" styleId="<%="componentCd" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();"/>
															<div id="autocomplete_choices<%= pageContext.findAttribute("index").toString() %>" class="autocomplete" ></div>
														</td>
				
														<td>
															<%-- 成分名称 --%>
															<div id="<%="lblComponentName" + pageContext.findAttribute("index").toString() %>">
																<nested:write property="componentName" />
															</div>
															<nested:hidden property="componentName" styleId="<%="componentName" + pageContext.findAttribute("index").toString() %>" />
														</td>
				
														<td>
															<%-- 有効分 --%>
															<nested:text property="strCalcValue" maxlength="22" size="22" styleId="<%="strCalcValue" + pageContext.findAttribute("index").toString() %>" style="ime-mode:disabled" onchange="setDirtyFlg();"/>
														</td>
				
														<td>
															<%-- 表示名称 --%>
															<nested:text property="indicateValue" maxlength="10" size="10" styleId="<%="indicateValue" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();"/>
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
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="setDirtyFlg(); return form_submit('op', 'addlist'); return false;" class="cssButton">
												&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50">
										<br>
									</td>

									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="if(!(putDelListConfirm())){return false;}else{setDirtyFlg(); return form_submit('op', 'dellist'); return false;}" class="cssButton">
												&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50">
										<br>
									</td>

									<nested:equal property="updateAuthority" value="true">
										<td class="alignLeft">
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
	</table>
</nested:form>

</body>
</html:html>

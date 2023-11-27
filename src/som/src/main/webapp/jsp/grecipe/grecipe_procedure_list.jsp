<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- チェックを入れる --%>
	function setLine(line) {
		$("line").value = line;
		setDirtyFlg();
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 工程検索後の設定処理 --%>
	function setOperationValues(operationCd, operationName, operationFlg) {
		if (operationFlg) {
			var line = eval($("line").value)
			$('lblOperationCd' + line).update(operationCd);
			$('lblOperationName' + line).update(operationName);
			$('operationCd' + line).value = operationCd;
			$('operationName' + line).value = operationName;
		}
	}

	<%-- 行追加・行削除 --%>
	function changeLine(msg) {
		if (msg == "del" && !noCheck()) {
		  return false;
		}
		setDirtyFlg();
		return true;
	}

	<%-- 工程詳細or配合詳細画面への遷移 --%>
	function toDetail(url) {
		if (dirtyConfirm()) {
			location.href=url;
			g_lock = true;
		}
	}

	<%-- 登録実行確認メッセージ --%>
	function putRegist(){
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var count = $F("count");
		var checkFlag = false;
		for (var i = 0; i < count; i++) {
			var item = $("searchProcList[" + i + "].checkFlg");
			if(item.checked){
				checkFlag = true;
				break;
			}
		}
		if (!checkFlag) {
		    alert("削除対象がありません。");
		    return false;
		}
		return true;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/GrecipeProcedureList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="line" styleId="line" />
	<nested:hidden property="count" styleId="count" />
	<nested:hidden property="recipeUse" styleId="recipeUse" />
	<nested:hidden property="srhLink" styleId="srhLink" />

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
							<td class="fcTitle fb">原処方</td>
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
							<td height= 5  colspan= 2 ></td>
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

				<%-- 共通項目部・タブ>>>>> --%>
				<%@ include file="/jsp/grecipe/grecipe_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

				<%-- 詳細内容 --%>
				<tr>
					<td>
						<table width="750" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<span id="slidetabs" style="clear: left;">
										<nested:notEmpty property="searchProcList">
											<table cellspacing="2" cellpadding="1" width="100%" border="0" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="7"></td>
													<td width="40">工程順序</td>
													<td width="5"></td>
													<td width="45">工程コード</td>
													<td width="630">工程名称</td>
													<td width="8">詳細</td>
													<td width="8">配合</td>
												</tr>
												<nested:iterate id="searchProcList" property="searchProcList" indexId="index">
													<app:modulo
														numerator='<%=pageContext.findAttribute("index").toString()%>'
														denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo
														numerator='<%=pageContext.findAttribute("index").toString()%>'
														denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

													<td>
														<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" />
													</td>

													<nested:hidden property="recipeId" styleId="<%="recipeId" + pageContext.findAttribute("index").toString() %>" />
													<nested:hidden property="strStepNo" styleId="<%="strStepNo" + pageContext.findAttribute("index").toString() %>" />
													<%-- 工程順序 --%>
													<td>
														<nested:write property="seq" />
													</td>
													<%-- 選択ボタン --%>
													<td>
														<div>
															<nested:notEqual property="srhLink" value="1">
																<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																<a href="#"  class="cssButton"
																	onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(450, 420,'OperationSearch', '', 'srhOperationCd', $('operationCd" + pageContext.findAttribute("index").toString() + "').value, 'srhOperationName', $('operationName" + pageContext.findAttribute("index").toString() + "').value, 'srhRecipeUse', $('recipeUse').value); return false;"%>">
																	選
																</a>
															</nested:notEqual>

															<nested:equal property="srhLink" value="1">
																<a href="#"  class="cssButton">
																	選
																</a>
															</nested:equal>
														</div>
													</td>
													<%-- 工程コード --%>
													<td>
														<div id="<%="lblOperationCd" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="operationCd" />
														</div>
														<nested:hidden property="operationCd" styleId="<%="operationCd" + pageContext.findAttribute("index").toString() %>" />
													</td>
													<%-- 工程名称 --%>
													<td>
														<div id="<%="lblOperationName" + pageContext.findAttribute("index").toString() %>">
															<nested:write property="operationName" />
														</div>
														<nested:hidden property="operationName" styleId="<%="operationName" + pageContext.findAttribute("index").toString() %>" />
													</td>

													<nested:empty property="strStepNo">
														<%-- 詳細 --%>
														<td>
																選
														</td>
														<%-- 配合 --%>
														<td>
															<nested:write property="formulaMark"  />
														</td>
													</nested:empty>
													<nested:notEmpty property="strStepNo">
														<nested:define id="rid" property="recipeId" />
														<nested:define id="sno" property="stepNo" />
														<nested:define id="lnk" property="srhLink" />
														<%-- 詳細 --%>
														<td>
															<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/GrecipeProcedureDetail.do?op=init&recipeId=" + pageContext.findAttribute("rid").toString() + "&stepNo=" + pageContext.findAttribute("sno").toString() + "&srhLink=" + pageContext.findAttribute("lnk").toString() + "'); return false;"%>'>
																選
															</html:link>
														</td>
														<%-- 配合 --%>
														<td>
															<nested:equal property="formulaMark" value="○">
															<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/GrecipeFormulaList.do?op=reSearch&recipeId=" + pageContext.findAttribute("rid").toString() + "&procStepNo=" + pageContext.findAttribute("sno").toString() + "&srhLink=" + pageContext.findAttribute("lnk").toString() + "'); return false;"%>'>
																<nested:write property="formulaMark"  />
															</html:link>
															</nested:equal>
															<nested:equal property="formulaMark" value="×">
																<nested:write property="formulaMark"  />
															</nested:equal>
														</td>
													</nested:notEmpty>
													</tr>
												</nested:iterate>
											</table>
										</nested:notEmpty>
									</span>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table cellpadding="0" cellspacing="0" width="450" border="0">
						<tr>
							<nested:notEqual property="srhLink" value="1">
								<nested:equal property="approvalStatus" value="1">
								<td class="alignCenter">
									<div id="cssButton">
										<a href="#" onclick="if (!(changeLine('add'))) {return false;}else{return form_submit('op', 'addlist'); return false;}"
											class="cssButton">&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
										</a>
									</div>
								</td>

								<td width="50"><br></td>

								<td class="alignCenter">
									<div id="cssButton">
										<a href="#" onclick="if (!(changeLine('del'))) {return false;}else{return form_submit('op', 'dellist'); return false;}"
											class="cssButton">&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
										</a>
									</div>
								</td>

								<td width="50"><br></td>

								<nested:equal property="updateAuthority" value="true">
									<td class="alignCenter">
										<div id="cssButton">
											<a href="#" onclick="if (!(putRegist())) {return false;}else{return form_submit('op', 'regist'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>
									<td width="50"><br></td>
								</nested:equal>

								</nested:equal>

								<td class="alignCenter">
									<div id="cssButton">
										<a href="#" onclick="if (!(dirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
											&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
										</a>
									</div>
								</td>
							</nested:notEqual>
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

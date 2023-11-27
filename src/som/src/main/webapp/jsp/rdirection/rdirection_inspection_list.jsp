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

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>

		<%-- 数値型フィールド定義 --%>


		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 行追加・行削除 --%>
	function changeLine(msg) {
		if (msg == "del" && !noCheck()) {
		  return false;
		}
		setDirtyFlg();
		return true;
	}

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
			if (dirtyConfirm()) {
				location.href=url;
				g_lock = true;
			}
		}
		return false;
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
			var item = $("searchInspectionList[" + i + "].checkFlg");
			if(item.checked){
				checkFlag = true;
				break;
			}
		}
		if (count > 0 && !checkFlag) {
		    alert("削除対象がありません。");
		    return false;
		}
		return true;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>
</head>

<body>

<nested:form action="/RdirectionInspectionList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>
	<nested:hidden property="count" styleId="count" />

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
							<td class="fcTitle fb" width="250">製造実績入力検査</td>
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
				<%@ include file="/jsp/rdirection/rdirection_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

<%-- 詳細内容 --%>
				<tr>
					<td>
						<table width="750" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<span id="slidetabs" style="clear: left;">
										<table border="0" cellspacing="2" cellpadding="2" width="100%">
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">工程順序</td>
												<td>
													<nested:select property="selectProcedureStepNo" style="margin: 0;padding: 0" styleId="selectProcedureStepNo" onchange="">
														<nested:optionsCollection property="procedureStepNoCombo" label="labales" value="values" />
													</nested:select>
											</tr>
											<tr>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">工程コード</td>
												<td width="100">
													<nested:write property="operationCd" />
												</td>
												<td class="fcTitleDetail fb bcTitleDetail" width="120">工程名称</td>
												<td>
													<nested:write property="operationName" />
												</td>
												<td class="alignRight valignMiddle">
													<div id="cssButton">
														<a href="#" onclick="if (!(dirtyConfirm())) {return false;}else{return form_submit('op', 'search'); return false;}" class="cssButton">
														&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</tr>
										</table>
										<table cellspacing="2" cellpadding="1" width="100%" border="0" id="tblList">
											<nested:notEmpty property="operationCd">
												<tr class="bcTitleList fb fcTitleList">
													<td width="7"></td>
													<td width="40">順序</td>
													<td width="80">検査コード</td>
													<td>検査名称</td>
													<td width="40">区分</td>
													<td width="150">基準値</td>
													<td width="100">実績値</td>
													<td width="8">詳細</td>
												</tr>
											</nested:notEmpty>
											<nested:notEmpty property="searchInspectionList">
												<nested:iterate id="searchInspectionList" property="searchInspectionList" indexId="index">
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
													<%--チェックボックス--%>
													<td width="7">
														<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" />
													</td>
													<%-- 順序 --%>
													<td width="40" class="alignRight">
														<nested:write property="seq" />
													</td>
													<%-- 検査コード --%>
													<td width="80">
														<nested:write property="inspectionCd" />
													</td>
													<%-- 検査名称 --%>
													<td>
														<nested:write property="inspectionName" />
													</td>
													<%-- 区分 --%>
													<td width="40">
														<nested:write property="divisionName" />
													</td>
													<%-- 値１ --%>
													<td width="150">
														<nested:write property="value1" />
														<nested:write property="conditionName" />
														<nested:write property="value2" />
													</td>
													<%-- 値２ --%>
													<td width="100">
														<nested:text property="resultValue1"  maxlength="22" size="12" styleId='<%= "resultValue1" + pageContext.findAttribute("index").toString() %>' onchange="setDirtyFlg();"/>
													</td>
													<%-- 詳細 --%>
													<nested:empty property="strLineNo">
														<td>
																選
														</td>
													</nested:empty>
													<nested:notEmpty property="strLineNo">
														<nested:define id="oid1" property="directionNo" />
														<nested:define id="oid2" property="stepNo" />
														<nested:define id="oid3" property="lineNo" />
														<td>
															<html:link href="#" onclick='<%="toDetail('"
																							+ request.getContextPath()
																							+ "/RdirectionInspectionDetail.do?op=init"
																							+ "&directionNo=" + pageContext.findAttribute("oid1").toString()
																							+ "&stepNo=" + pageContext.findAttribute("oid2").toString()
																							+ "&lineNo=" + pageContext.findAttribute("oid3").toString()
																							+ "'); return false;"%>'>
																選
															</html:link>
														</td>
													</nested:notEmpty>
													</tr>
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
					<td align="center">
					<table>
						<tr>
							<nested:lessThan property="directionStatus" value="8">
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
							</nested:equal>
							</nested:lessThan>
							<td width="50"><br></td>
							<td class="alignCenter">
								<div id="cssButton">
									<a href="#" onclick="if (!(dirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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

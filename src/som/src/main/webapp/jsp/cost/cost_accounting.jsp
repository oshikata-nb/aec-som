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
		defineAsRequiredField("srhStrInputDate");

		<%-- 日付型フィールド定義 --%>
		defineAsDateYMField("srhStrInputDate");

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues(/^srh.*/);	

		<%-- 送信区分チェック --%>
		checkDivision();
		
		refreshLabel();
	}, false);

	<%-- 確認メッセージ --%>
	function putExecuteConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "実行してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 全部チェックを入れる・外す --%>
	function setTargetCheckAll(bol) {
		var tblList = $('tblList');

		if (tblList != null) {
			if (0 < tblList.rows.length) {
				for (i = 0; null != $("checked" + i);　i++) {
					if($("srhSendDivision").value == 1){
						<%-- 期中 --%>
						if(i == 1 || i == 2 || i == 3){
							<%-- 品目マスタ・作業手順マスタ・部品構成マスタ --%>
							$("checked" + i).checked = true;
						}else{
							$("checked" + i).checked = bol;
						}
					}else{
						<%-- 期首 --%>
						$("checked" + i).checked = bol;
					}
				}
			}
		}

		return false;
	}

	<%-- 送信区分チェック --%>
	function checkDivision() {
		var disabled;
		
		if($("srhSendDivision").value == 1){
			<%-- 期中 --%>
			$("checked" + 1).checked = true;
			$("checked" + 2).checked = true;
			$("checked" + 3).checked = true;

			disabled = true;
		}else{
			<%-- 期首 --%>
			disabled = false;
		}
		
		$("checked" + 1).disabled = disabled;
		$("checked" + 2).disabled = disabled;
		$("checked" + 3).disabled = disabled;

		return false;
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/CostAccounting" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="index" styleId="index"/>

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
									<td class="fcTitle fb" width="250">原価計算データ送信・取込</td>
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
												<td class="bcTitleSearch fb fcTitleSearch" width="130">対象年月</td>
												<td>
													<nested:text property="srhStrInputDate" maxlength="10" size="10" styleId="srhStrInputDate" style="ime-mode:disabled"/>
												</td>
											</tr>
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">マスタ送信区分</td>
												<td>
													<% pageContext.setAttribute("SelectSendDivision", new com.asahikaseieng.app.comboboxes.SelectSendDivision(false, false)); %>
													<nested:select property="srhSendDivision" onchange="checkDivision();">
														<nested:options name="SelectSendDivision" property="values" labelName="SelectSendDivision" labelProperty="labels"/>
													</nested:select>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td class="alignLeft" colspan="4">
										<div id="cssButton">
											<a href="#" onClick="setTargetCheckAll(true);return false;" class="cssButton">全選択</a>
											<a href="#" onClick="setTargetCheckAll(false);return false;" class="cssButton">全解除</a>
										</div>
									</td>

									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<table cellspacing="1" cellpadding="1"  border="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" onclick="if(!putExecuteConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
																&nbsp;&nbsp;実&nbsp;&nbsp;行&nbsp;&nbsp;
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</nested:equal>
								</tr>

								<tr height="5">
									<td></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="5"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<nested:notEmpty property="executeList">
					<table border="0" cellpadding="0" cellspacing="0" width="750">
						<tr>
							<td>
								<!-- 明細部 -->
								<table width="100%" border="0">
									<tr>
										<td>
											<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
												<tr class="bcTitleList fb fcTitleList">
													<td width="20"></td>
													<td>送信テーブル名</td>
													<td width="100" class="alignRight">件数</td>
													<td width="100">結果</td>
												</tr>

												<nested:iterate id="executeList" property="executeList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

													<td><%-- 実行用チェックボックス --%>
														<nested:checkbox property="checked" styleId="<%="checked" + pageContext.findAttribute("index").toString() %>" />
													</td>

													<td>
														<%-- テーブル名 --%>
        												<nested:write property="tableName"/>
													</td>

													<td class="alignRight">
														<%-- 件数 --%>
														<nested:write property="strCnt"/>
													</td>

													<td>
														<%-- 結果 --%>
														<nested:write property="result"/>
													</td>
												</nested:iterate>
											</table>
										</td>
									</tr>

									<tr height="5">
										<td></td>
									</tr>

									<tr>
										<td>
											<table width="100%" cellspacing="2" cellpadding="1" id="tblList2">
												<tr class="bcTitleList fb fcTitleList">
													<td width="50"></td>
													<td>取込テーブル名</td>
													<td width="100" class="alignRight">件数</td>
													<td width="100">結果</td>
												</tr>

												<nested:iterate id="importList" property="importList" indexId="index">
													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
														<tr class="bcList1">
													</app:modulo>

													<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
														<tr class="bcList2">
													</app:modulo>

													<td width="50"><%-- 実行ボタン --%>
														<nested:equal name="costAccountingForm" property="updateAuthority" value="true">
															<div>
																<a href="#" class="cssButton" onclick="<%="if(!putExecuteConfirm()){return false;}else{return form_submit('op', 'importData', 'index', " + pageContext.findAttribute("index").toString() + "); return false;}"%>">
																	実行
																</a>
															</div>
														</nested:equal>
													</td>

													<td>
														<%-- テーブル名 --%>
        												<nested:write property="tableName"/>
													</td>

													<td class="alignRight">
														<%-- 件数 --%>
														<nested:write property="strCnt"/>
													</td>

													<td>
														<%-- 結果 --%>
														<nested:write property="result"/>
													</td>
												</nested:iterate>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</nested:notEmpty>
			</td>
		</tr>
	</table>
</nested:form>

</body>
</html:html>

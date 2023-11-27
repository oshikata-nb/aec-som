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

		var count = $F("count");
		for( var i = 0 ; i < count ; i++){
			<%-- 必須フィールド定義 --%>
			defineAsRequiredField("strResultQty" + i);
			<%-- 数値型フィールド定義 --%>
			defineAsNumberField("strResultQty" + i);
			defineAsNumberField("strResultFillQty" + i);
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	<%-- チェックを入れる --%>
	function setLine(line) {
		$("line").value = line;
	}
	<%-- 登録実行確認メッセージ --%>
	function putRegist(){
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 品目検索後の設定処理 --%>
	function setItemValues(itemCd, itemName, itemUnit, unitDiv, smallnumLength, roundDivision, itemFlg) {
		if (itemFlg) {
			var line = eval($("line").value)
			$('lblItemCd' + line).update(itemCd);
			$('lblItemName' + line).update(itemName);
			$('itemCd' + line).value = itemCd;
			$('itemName' + line).value = itemName;
			$('unitDivision' + line).value = unitDiv;
			$('decimalPoint' + line).value = smallnumLength;
			$('roundDivision' + line).value = roundDivision;
			formatResultFillQty();
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

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var count = $F("count");
		var checkFlag = false;
		for (var i = 0; i < count; i++) {
			var item = $("searchFinishList[" + i + "].checkFlg");
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

	<%-- 生産実績数量のフォーマット --%>
	function formatResultQty(){
		var index = $F("line");
		var value = $F("strResultQty" + index);
		<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
	    var decPoint = $('decimalPointHaigo').value;
	    var round = $('roundDivisionHaigo').value;
		if ((value != null) && (value != "")) {
			$("strResultQty" + index).value = digitStringFormat(decPoint,round,value);
		}
	}

	<%-- 充填実績数量のフォーマット --%>
	function formatResultFillQty(){
		var index = $F("line");

		<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
	    var decPoint = $('decimalPoint' + index).value;
	    var round = $('roundDivision' + index).value;

		var value = $F("strResultFillQty" + index);
		if ((value != null) && (value != "")) {
			$("strResultFillQty" + index).value = digitStringFormat(decPoint,round,value);
		}
	}


</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgRdirectionFinishList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="line" styleId="line" />
	<nested:hidden property="count" styleId="count" />
	<nested:hidden property="decimalPointHaigo" styleId="decimalPointHaigo" />
	<nested:hidden property="roundDivisionHaigo" styleId="roundDivisionHaigo" />

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
							<td class="fcTitle fb">包装実績出力仕上</td>
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
				<%@ include file="/jsp/pkgrdirection/pkgrdirection_common.jsp"%>
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
											<nested:notEmpty property="searchFinishList">
												<table cellspacing="2" cellpadding="1" width="100%" border="0" id="tblList">
													<tr class="bcTitleList fb fcTitleList">
														<td width="20"></td>
														<td width="20" class="alignRight">順</td>
														<td width="10"></td>
														<td width="75">品目コード</td>
														<td>品目名称</td>
														<td>区分</td>
														<td width="100">生産実績数量</td>
														<td width="100">充填実績数量</td>
														<td width="70">ロット番号</td>
													</tr>
													<nested:iterate id="searchFinishList" property="searchFinishList" indexId="index">
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

														<nested:hidden property="unitDivision" styleId="<%="unitDivision" + pageContext.findAttribute("index").toString() %>" />
														<nested:hidden property="decimalPoint" styleId="<%="decimalPoint" + pageContext.findAttribute("index").toString() %>" />
														<nested:hidden property="roundDivision" styleId="<%="roundDivision" + pageContext.findAttribute("index").toString() %>" />

														<%-- １行目 --%>
														<nested:equal name="index" value="0" >
															<%-- チェックボックス --%>
															<td>
																<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" disabled="true" />
															</td>
															<%-- 順 --%>
															<td class="alignRight">
																<nested:write property="strStepNo"/>
															</td>
															<%-- 選択ボタン --%>
															<td>
															</td>
															<%-- 品目コード --%>
															<td>
																<div id="<%="lblItemCd" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="itemCd" />
																</div>
																<nested:hidden property="itemCd" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<%-- 品目名称 --%>
															<td>
																<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="itemName" />
																</div>
																<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<%-- 区分 --%>
															<td>
																<% pageContext.setAttribute("selectRecipeLineTypeDivision", new com.asahikaseieng.app.comboboxes.SelectRecipeLineTypeDivision(false,true)); %>
																<nested:select property="strLineType" styleId="strLineType" disabled="true">
																	<nested:options name="selectRecipeLineTypeDivision" property="values" labelName="selectRecipeLineTypeDivision" labelProperty="labels" />
																</nested:select>
															</td>
															<%-- 生産実績数量 --%>
															<td>
																<nested:text property="strResultQty" maxlength="22" size="10" styleId='<%= "strResultQty" + pageContext.findAttribute("index").toString() %>'
																 style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatResultQty();"%>" />
															</td>
															<%-- 充填実績数量 --%>
															<td>
																<nested:text property="strResultFillQty" maxlength="22" size="10" styleId='<%= "strResultFillQty" + pageContext.findAttribute("index").toString() %>'
																 style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatResultFillQty();"%>" />
															</td>
															<%-- ロット番号 --%>
															<td>
																<nested:text property="lotNo" maxlength="20" size="10" styleId='<%= "lotNo" + pageContext.findAttribute("index").toString() %>'
																 style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" disabled="true" />
															</td>
														</nested:equal>
														<%-- ２行目以降 --%>
														<nested:notEqual name="index" value="0" >
															<%-- チェックボックス --%>
															<td>
																<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<%-- 順 --%>
															<nested:empty property="strSeq">
																<td>
																	<nested:select property="strStepNo" style="margin: 0;padding: 0" styleId="strStepNo" onchange="setDirtyFlg();">
																		<nested:optionsCollection name="pkgRdirectionFinishListForm" property="stepNoCombo" label="labales" value="values" />
																	</nested:select>
																</td>
															</nested:empty>
															<nested:notEmpty property="strSeq">
																<td class="alignRight">
																	<nested:write property="procSeq" /><nested:hidden property="strStepNo" />
																</td>
															</nested:notEmpty>
															<%-- 選択ボタン --%>
															<td>
																<div>
																	<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																	<a href="#"  class="cssButton"
																		onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(450, 490,'ItemSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value, 'srhItemName', $('itemName" + pageContext.findAttribute("index").toString() + "').value); setDirtyFlg(); return false;"%>">
																		選
																	</a>
																</div>
															</td>
															<%-- 品目コード --%>
															<td>
																<div id="<%="lblItemCd" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="itemCd" />
																</div>
																<nested:hidden property="itemCd" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<%-- 品目名称 --%>
															<td>
																<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
																	<nested:write property="itemName" />
																</div>
																<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
															</td>
															<%-- 区分 --%>
															<td>
																<% pageContext.setAttribute("selectRecipeLineTypeDivision",	new com.asahikaseieng.app.comboboxes.SelectRecipeLineTypeDivision(false,true)); %>
																<nested:select property="strLineType" styleId="strLineType">
																	<nested:options name="selectRecipeLineTypeDivision" property="values" labelName="selectRecipeLineTypeDivision" labelProperty="labels" />
																</nested:select>
															</td>
															<%-- 生産実績数量 --%>
															<td>
																<nested:text property="strResultQty" maxlength="22" size="10" styleId='<%= "strResultQty" + pageContext.findAttribute("index").toString() %>'
																	onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatResultQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
															</td>
															<%-- 充填実績数量 --%>
															<td>
																<nested:text property="strResultFillQty" maxlength="22" size="10" styleId='<%= "strResultFillQty" + pageContext.findAttribute("index").toString() %>'
																	onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatResultFillQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
															</td>
															<%-- ロット番号 --%>
															<td>
																<nested:text property="lotNo" maxlength="20" size="10" styleId='<%= "lotNo" + pageContext.findAttribute("index").toString() %>'
																	style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
															</td>
														</nested:notEqual>
													</nested:iterate>
												</table>
											</nested:notEmpty>
											</tr>
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
							<nested:lessThan property="directionStatus" value="7">
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

							</nested:lessThan>
							<td class="alignCenter">
								<div id="cssButton">
									<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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

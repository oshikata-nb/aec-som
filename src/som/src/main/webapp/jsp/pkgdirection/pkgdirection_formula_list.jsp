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
			defineAsRequiredField("strQty" + i);
			<%-- 数値型フィールド定義 --%>
			defineAsNumberField("strQty" + i);
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- チェックを入れる --%>
	function setLine(line) {
		$("line").value = line;
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
			$('lblUnitName' + line).update(itemUnit);
			$('unitName' + line).value = itemUnit;
			formatQty();
		}
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
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
			var item = $("searchFormList[" + i + "].checkFlg");
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
	<%-- 登録実行確認メッセージ --%>
	function putRegist(){
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 配合詳細画面への遷移 --%>
	function toDetail(url) {
		if (dirtyConfirm()) {
			location.href=url;
			g_lock = true;
		}
	}
	<%-- 予定数量のフォーマット --%>
	function formatQty(){
		var index = $F("line");
		var value = $F("strQty" + index);
		if ((value != null) && (value != "")) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var decPoint = $('decimalPoint').value;
		    var round = $('roundDivision').value;
			$("strQty" + index).value = digitStringFormat(decPoint,round,value);
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgDirectionFormulaList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="line" styleId="line" />
	<nested:hidden property="count" styleId="count" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="roundDivision" styleId="roundDivision" />

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
							<td class="fcTitle fb">包装指図入力配合</td>
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
				<%@ include file="/jsp/pkgdirection/pkgdirection_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

<%-- 詳細内容 --%>
				<tr>
					<td>
					<span id="slidetabs" style="clear: left;">
						<table border="0" cellspacing="2" cellpadding="2" width="100%">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="120">工程順序</td>
								<td width="100">
									<nested:select property="procStepNo" style="margin: 0;padding: 0" styleId="procStepNo" onchange="setDirtyFlg();">
										<nested:optionsCollection property="seqCombo" label="labales" value="values" />
									</nested:select>
								</td>
								<td class="alignRight valignMiddle">
									<div id="cssButton">
										<a href="#" onclick="if (!(dirtyConfirm())) {return false;}else{return form_submit('op', 'search'); return false;}"
											class="cssButton">&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
										</a>
									</div>
								</td>
							</tr>
							<tr>
							<nested:notEmpty property="searchFormList">
								<table cellspacing="2" cellpadding="1" width="100%" border="0" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td></td>
										<td width="35">工程<br>順序</td>
										<td width="100">工程</td>
										<td width="10"></td>
										<td width="80">品目コード</td>
										<td>品目名称</td>
										<td width="90">予定数量</td>
										<td width="50">単位</td>
										<td>在庫</td>
										<td>詳細</td>
									</tr>
									<nested:iterate id="searchFormList" property="searchFormList" indexId="index">
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

										<nested:hidden property="strLineNo" styleId="<%="strLineNo" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="unitDivision" styleId="<%="unitDivision" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="rcpUse" styleId="<%="rcpUse" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="decimalPoint" styleId="<%="decimalPoint" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="roundDivision" styleId="<%="roundDivision" + pageContext.findAttribute("index").toString() %>" />

										<%-- 工程順序 --%>
										<nested:empty property="strSeq">
											<td>
												<nested:select property="strStepNo" style="margin: 0;padding: 0" styleId="strStepNo" onchange="setDirtyFlg();">
													<nested:optionsCollection property="procSeqCombo" label="labales" value="values" />
												</nested:select>
											</td>
										</nested:empty>
										<nested:notEmpty property="strSeq">
											<td class="alignRight">
												<nested:write property="procSeq" /><nested:hidden property="strStepNo" />
											</td>
										</nested:notEmpty>

										<%-- 工程名称 --%>
										<td>
											<nested:write property="operationName" />
										</td>

										<%-- 選択ボタン --%>
										<td>
										<nested:empty property="strSeq">
											<div>
												<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
												<a href="#"  class="cssButton"
													onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(510, 470,'ItemSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value, 'srhItemName', $('itemName" + pageContext.findAttribute("index").toString() + "').value); setDirtyFlg(); return false;"%>">
													選
												</a>
											</div>
										</nested:empty>
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

										<%-- 予定数量 --%>
										<nested:empty property="strSeq">
											<td class="alignRight">
												<nested:text property="strQty" maxlength="22" size="15" styleId='<%= "strQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
											</td>
										</nested:empty>
										<nested:notEmpty property="strSeq">
											<td class="alignRight">
												<nested:write property="strQty" /><nested:hidden property="strQty" styleId='<%= "strQty" + pageContext.findAttribute("index").toString() %>' />
											</td>
										</nested:notEmpty>

										<%-- 単位 --%>
										<td>
											<div id="<%="lblUnitName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="unitName" />
											</div>
											<nested:hidden property="unitName" styleId="<%="unitName" + pageContext.findAttribute("index").toString() %>" />
										</td>

										<%-- 在庫確認ポップアップ --%>
										<td>
											<div>
												<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
												<a href="#"  class="cssButton"
													onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(600, 470,'PkgDirectionLotInventorySearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value, 'srhItemName', $('itemName" + pageContext.findAttribute("index").toString() + "').value); return false;"%>">
													選
												</a>
											</div>
										</td>
										<nested:empty property="strSeq">
											<%-- 詳細 --%>
											<td>
													選
											</td>
										</nested:empty>
										<nested:notEmpty property="strSeq">
											<nested:define id="div" property="directionDivision" />
											<nested:define id="dno" property="directionNo" />
											<nested:define id="sno" property="stepNo" />
											<nested:define id="lno" property="lineNo" />
											<%-- 詳細 --%>
											<td>
												<html:link href="#" onclick='<%="toDetail('" + request.getContextPath()
														                                    + "/PkgDirectionFormulaDetail.do?op=init&directionDivision=" + pageContext.findAttribute("div").toString()
																							+ "&directionNo=" + pageContext.findAttribute("dno").toString()
																							+ "&stepNo=" + pageContext.findAttribute("sno").toString()
																							+ "&lineNo=" + pageContext.findAttribute("lno").toString()
																							+ "'); return false;"%>'>
													選
												</html:link>
											</td>
										</nested:notEmpty>
									</nested:iterate>
								</table>
							</nested:notEmpty>
							</tr>
						</table>
					</span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table>
						<tr>
							<nested:equal property="jissekiFlag" value="0">
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

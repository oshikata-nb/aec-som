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


		if ($F("count") > 0) {
			// 指図量の計算
			sumQty();
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
	<%-- 工程詳細画面へ遷移する --%>
	function jumpFormulaDetail(url) {
		if (putDirtyConfirm()) {
			toProcedureDetail(url);
		}
	}
	<%-- 登録実行確認メッセージ --%>
	function putRegist(){
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 合計値の計算 --%>
	function sumQty(index){
		var total = 0;
		var atai = 0;
		var payList = $('tblList');
		if (payList != null) {
			if (0 < payList.rows.length) {
				for (i = 0; null != $("strQty" + i);　i++) {
					var qty = $("strQty" + i).value;
					if (qty != ""){
						qty = removeComma(qty);
						if(!isNaN(qty)) {
							atai = eval(qty);
							total = total + atai;
						}
					}
				}
			}
		}
		if ($("totalQty") != null) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var decPoint = $('decimalPoint').value;
		    var round = $('roundDivision').value;
			var totalQty = digitFormat(decPoint,round,total);
			$("totalQty").innerText = totalQty;
			$("totalQty").value = totalQty;
		}
	    return null;
	}

	<%-- 配合量のフォーマット --%>
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

	<%-- カンマ編集解除 --%>
	function removeComma(value) {
	    return value.split(",").join("")
	}

	<%-- 配合詳細画面への遷移 --%>
	function toDetail(url) {
		if (dirtyConfirm()) {
			location.href=url;
			g_lock = true;
		}
	}


</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DirectionFormulaList" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb">製造指図配合</td>
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
				<%@ include file="/jsp/direction/direction_common.jsp"%>
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
												<td width="100">
													<nested:select property="procStepNo" style="margin: 0;padding: 0" styleId="procStepNo" onchange="setDirtyFlg();">
														<nested:optionsCollection property="seqCombo" label="labales" value="values" />
													</nested:select>
												</td>

												<td class="fcTitleDetail fb bcTitleDetail" width="120">指図量計</td>
												<td id="totalQty" class="alignRight">
													<nested:write property="totalQty"/><nested:hidden property="totalQty"/>
												</td>
												<td>
													kg
													<%-- 単位はkg固定とするがコメントとして残す --%>
													<%--
													<nested:write property="unitName"/>
													--%>
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
														<td width="7"></td>
														<td width="40">工程<br>順序</td>
														<td width="50">ｻﾌﾞ<br>ｽﾃｯﾌﾟ</td>
														<td>工程</td>
														<td width="10"></td>
														<td width="80">品目コード</td>
														<td>品目名称</td>
														<td width="50">指図量</td>
														<td width="50">単位</td>
														<td width="8">詳細</td>
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
																<nested:write property="procSeq" /><nested:hidden property="strStepNo"></nested:hidden>
															</td>
														</nested:notEmpty>

														<%-- ｻﾌﾞｽﾃｯﾌﾟ --%>
														<td class="alignRight">
															<nested:write property="strSeq" />
														</td>

														<%-- 工程名称 --%>
														<td>
															<nested:write property="operName" />
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

														<%-- 指図量 --%>
														<nested:empty property="strSeq">
															<td>
																<nested:text property="strQty" maxlength="22" size="15" styleId='<%= "strQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");sumQty();formatQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
															</td>
														</nested:empty>
														<nested:notEmpty property="strSeq">
															<td class="alignRight">
																<nested:write property="strQty" /><nested:hidden property="strQty" styleId='<%= "strQty" + pageContext.findAttribute("index").toString() %>' />
															</td>
														</nested:notEmpty>
														<%-- 単位 --%>
														<td>
															kg
															<%-- 単位はkg固定とするがコメントとして残す --%>
															<%--
															<nested:write name="directionFormulaListForm" property="unitName"/>
															--%>
														</td>

														<%-- 詳細 --%>
														<td>
														<nested:empty property="strSeq">選</nested:empty>
														<nested:notEmpty property="strSeq">
															<nested:define id="oid1" property="directionNo" />
															<nested:define id="oid2" property="stepNo" />
															<nested:define id="oid3" property="lineNo" />
																<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/DirectionFormulaDetail.do?op=init&directionNo=" + pageContext.findAttribute("oid1").toString() + "&stepNo=" + pageContext.findAttribute("oid2").toString() + "&lineNo=" + pageContext.findAttribute("oid3").toString() + "'); return false;"%>'>
																	選
																</html:link>
														</nested:notEmpty>
														</td>
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
							<nested:lessEqual property="directionStatus" value="2">
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
										<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'regist'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
										</a>
									</div>
								</td>

								<td width="50"><br></td>
							</nested:equal>

							</nested:lessEqual>
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

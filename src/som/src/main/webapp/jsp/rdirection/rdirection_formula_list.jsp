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
			defineAsNumberField("strLossQty" + i);
			defineAsNumberField("strAdjustQty" + i);
		}


		//if ($F("count") > 0) {
		//	// 指図量の計算
		//	sumQty();
		//}

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

	<%-- 'HAIGO'でフォーマット --%>
	function formatHaigo(obj){
		//var index = $F("line");
		var value = obj.value;
		if ((value != null) && (value != "")) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var decPoint = $('decimalPointHaigo').value;
		    var round = $('roundDivisionHaigo').value;
			obj.value = digitStringFormat(decPoint,round,value);
		}
	}

	<%-- 'HAIGO_ADJ'でフォーマット --%>
	function formatAdj(obj){
		//var index = $F("line");
		var value = obj.value;
		if ((value != null) && (value != "")) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var decPoint = $('decimalPointAdj').value;
		    var round = $('roundDivisionAdj').value;
			obj.value = digitStringFormat(decPoint,round,value);
		}
	}

	<%-- 配合詳細画面への遷移 --%>
	function toDetail(url) {
		if (dirtyConfirm()) {
			location.href=url;
			g_lock = true;
		}
	}

	<%-- 合計値の計算
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
			<%-- 小数点桁数・端数区分で数値を再フォーマット
		    var decPoint = $('decimalPoint').value;
		    var round = $('roundDivision').value;
			var totalQty = digitFormat(decPoint,round,total);
			$("totalQty").innerText = totalQty;
			$("totalQty").value = totalQty;
		}
	    return null;
	} --%>

	<%-- カンマ編集解除
	function removeComma(value) {
	    return value.split(",").join("")
	} --%>

	<%-- 工程詳細画面へ遷移する
	function jumpFormulaDetail(url) {
		if (putDirtyConfirm()) {
			toProcedureDetail(url);
		}
	} --%>

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/RdirectionFormulaList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="line" styleId="line" />
	<nested:hidden property="count" styleId="count" />
	<nested:hidden property="decimalPointHaigo" styleId="decimalPointHaigo" />
	<nested:hidden property="roundDivisionHaigo" styleId="roundDivisionHaigo" />
	<nested:hidden property="decimalPointAdj" styleId="decimalPointAdj" />
	<nested:hidden property="roundDivisionAdj" styleId="roundDivisionAdj" />

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
							<td class="fcTitle fb">製造実績配合</td>
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
												<td width="100">
													<nested:select property="procStepNo" style="margin: 0;padding: 0" styleId="procStepNo" onchange="setDirtyFlg();">
														<nested:optionsCollection property="seqCombo" label="labales" value="values" />
													</nested:select>
												</td>

<%--
												<td class="fcTitleDetail fb bcTitleDetail" width="120">指図量計</td>
												<td id="totalQty" class="alignRight">
													<nested:write property="totalQty"/><nested:hidden property="totalQty"/>
												</td>
												<td>Kg</td>
--%>

												<td class="alignRight valignMiddle">
													<div id="cssButton">
														<a href="#" onclick="return form_submit('op', 'search'); return false;"
															class="cssButton">&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
														</a>
													</div>
												</td>
											</tr>

											<tr>
											<nested:notEmpty property="searchFormList">
												<table cellspacing="2" cellpadding="1" width="100%" border="0" id="tblList">
													<tr class="bcTitleList fb fcTitleList">
														<td width="20" rowspan="2"><br></td>
														<td width="20" rowspan="2" class="alignRight">工程順序</td>
														<td width="20" rowspan="2"><br></td>
														<td width="80" rowspan="2">品目ｺｰﾄﾞ</td>
														<td rowspan="2">品目名称</td>
														<td width="100">メーカーロット</td>
														<td width="60" rowspan="2">指図量</td>
														<td width="40" rowspan="2">実績数量</td>
														<td width="40" rowspan="2">ロス数量</td>
														<td width="40" rowspan="2">調整数量</td>
														<td width="20" rowspan="2">単位</td>
														<td width="20" rowspan="2">在庫量</td>
														<td width="20" rowspan="2">詳細</td>
													</tr>
													<tr class="bcTitleList fb fcTitleList">
														<td>入荷ロット</td>
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

														<td rowspan="2">
															<nested:checkbox property="checkFlg" styleId="<%="checkFlg" + pageContext.findAttribute("index").toString() %>" />
														</td>

														<%-- 工程順序 --%>
														<nested:empty property="strSeq">
															<td rowspan="2">
																<nested:select property="strStepNo" style="margin: 0;padding: 0" styleId="strStepNo" onchange="setDirtyFlg();">
																	<nested:optionsCollection property="procSeqCombo" label="labales" value="values" />
																</nested:select>
															</td>
														</nested:empty>
														<nested:notEmpty property="strSeq">
															<td class="alignRight" rowspan="2">
																<nested:write property="procSeq" /><nested:hidden property="strStepNo"></nested:hidden>
															</td>
														</nested:notEmpty>

														<%-- 選択ボタン --%>
														<nested:empty property="strSeq">
															<td rowspan="2">
																<div>
																	<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																	<a href="#"  class="cssButton"
																		onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(450, 470,'ItemSearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value, 'srhItemName', $('itemName" + pageContext.findAttribute("index").toString() + "').value); setDirtyFlg(); return false;"%>">
																		選
																	</a>
																</div>
															</td>
														</nested:empty>
														<nested:notEmpty property="strSeq">
															<td rowspan="2"></td>
														</nested:notEmpty>
														<%-- 品目コード --%>
														<td rowspan="2">
															<div id="<%="lblItemCd" + pageContext.findAttribute("index").toString() %>">
																<nested:write property="itemCd" />
															</div>
															<nested:hidden property="itemCd" styleId="<%="itemCd" + pageContext.findAttribute("index").toString() %>" />
														</td>
														<%-- 品目名称 --%>
														<td rowspan="2">
															<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
																<nested:write property="itemName" />
															</div>
															<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
														</td>

														<%-- メーカーロット --%>
														<td>
															<nested:write property="manufacturerLotNo" /><br>
														</td>

														<%-- 指図量 --%>
														<td class="alignRight" rowspan="2">
															<nested:write property="strQty" /><nested:hidden property="strQty"></nested:hidden>
														</td>
														<%-- 実績数量 --%>
														<td rowspan="2">
															<nested:text property="strResultQty" maxlength="22" size="5" styleId='<%= "strResultQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatHaigo(this);"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();"/>
														</td>
														<%-- ロス数量 --%>
														<td rowspan="2">
															<nested:text property="strLossQty" maxlength="22" size="4" styleId='<%= "strLossQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatHaigo(this);"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();"/>
														</td>
														<%-- 調整数量 --%>
														<td rowspan="2">
															<nested:text property="strAdjustQty" maxlength="22" size="4" styleId='<%= "strAdjustQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatAdj(this);"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();"/>
														</td>
														<%-- 重量単位（Kg固定） --%>
														<td rowspan="2">
															kg
														<%--
															<div id="<%="lblUnitName" + pageContext.findAttribute("index").toString() %>">
																<nested:write property="unitName" />
															</div>
															<nested:hidden property="unitName" styleId="<%="unitName" + pageContext.findAttribute("index").toString() %>" />
														--%>
														</td>

														<%-- 在庫量 --%>
														<td rowspan="2">
															<div>
																<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
																<a href="#"  class="cssButton"
																	onclick="<%="setLine(" + pageContext.findAttribute("index").toString() + ");open_modal_popup_edge(700, 470,'RdirectionLotInventorySearch', '', 'srhItemCd', $('itemCd" + pageContext.findAttribute("index").toString() + "').value, 'srhItemName', $('itemName" + pageContext.findAttribute("index").toString() + "').value); return false;"%>">
																	選
																</a>
															</div>
														</td>
														<nested:empty property="strSeq">
															<%-- 詳細 --%>
															<td rowspan="2">
																	選
															</td>
														</nested:empty>
														<nested:notEmpty property="strSeq">
															<td rowspan="2">
															<%-- 詳細 --%>
															<nested:define id="oid1" property="directionNo" />
															<nested:define id="oid2" property="stepNo" />
															<nested:define id="oid3" property="lineNo" />
																<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/RdirectionFormulaDetail.do?op=init&directionNo=" + pageContext.findAttribute("oid1").toString() + "&stepNo=" + pageContext.findAttribute("oid2").toString() + "&lineNo=" + pageContext.findAttribute("oid3").toString() + "'); return false;"%>'>
																	選
																</html:link>
															</td>
														</nested:notEmpty>

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

														<%-- 入荷ロット --%>
														<td>
															<nested:write property="lotNo"/><br>
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
							<nested:lessThan property="directionStatus" value="8">
							<td class="alignCenter">
								<div id="cssButton">
									<a href="#" onclick="if (!(changeLine('add'))) {return false;}else{return form_submit('op', 'addlist'); setDirtyFlg(); return false;}"
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

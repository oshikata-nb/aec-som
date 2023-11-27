<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>
<%@ page import="java.net.URLEncoder" %>

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
		setDirtyFlg();
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

	<%-- ロット検索画面への遷移 --%>
	function toLotSearch(url) {
		if (dirtyConfirm()) {
			location.href=url;
			g_lock = true;
		}
	}
	<%-- 在庫引落リセットの確認メッセージ --%>
	function putResetConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "在庫引落を取消します。よろしいですか？";
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			if (!continueConfirm()) {
				return false;
			}
			return confirm(alertMsg[0]);
		} else {
			return confirm(alertMsg[0]);
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgRdirectionFormulaList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="line" styleId="line" />
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
							<td class="fcTitle fb">包装実績入力配合</td>
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
										<td rowspan="2"></td>
										<td width="35" rowspan="2">工程<br>順序</td>
										<td width="60" rowspan="2">工程</td>
										<td width="10" rowspan="2"></td>
										<td width="200">品目コード</td>
										<td width="70" rowspan="2">予定数量</td>
										<td width="40" rowspan="2">単位</td>
										<td width="120">メーカーロット</td>
										<td width="50" rowspan="2">使用数</td>
										<td width="50" rowspan="2">ｻﾝﾌﾟﾙ</td>
										<td width="50" rowspan="2">ロス</td>
										<td width="50" rowspan="2">不良</td>
										<td width="50" rowspan="2">調整</td>
										<td width="35"rowspan="2">在庫</td>
										<td width="35"rowspan="2">詳細</td>
									</tr>
									<tr class="bcTitleList fb fcTitleList">
										<td>品目名称</td>
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

										<nested:hidden property="strLineNo" styleId="<%="strLineNo" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="unitDivision" styleId="<%="unitDivision" + pageContext.findAttribute("index").toString() %>" />
										<nested:hidden property="rcpUse" styleId="<%="rcpUse" + pageContext.findAttribute("index").toString() %>" />

										<%-- 工程順序 --%>
										<nested:empty property="strSeq">
											<td rowspan="2">
												<nested:select property="strStepNo" style="margin: 0;padding: 0" styleId="strStepNo" onchange="setDirtyFlg();">
													<nested:optionsCollection property="procSeqCombo" label="labales" value="values" />
												</nested:select>
											</td>
										</nested:empty>
										<nested:notEmpty property="strSeq">
											<td rowspan="2" class="alignRight">
												<nested:write property="procSeq" /><nested:hidden property="strStepNo" />
											</td>
										</nested:notEmpty>

										<%-- 工程名称 --%>
										<td rowspan="2">
											<nested:write property="operName" />
										</td>

										<%-- 選択ボタン --%>
										<td rowspan="2">
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

										<%-- 予定数量 --%>
										<td rowspan="2" class="alignRight">
											<nested:write property="strQty" /><nested:hidden property="strQty" styleId='<%= "strQty" + pageContext.findAttribute("index").toString() %>' />
										</td>

										<%-- 単位 --%>
										<td rowspan="2">
											<div id="<%="lblUnitName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="unitName" />
											</div>
											<nested:hidden property="unitName" styleId="<%="unitName" + pageContext.findAttribute("index").toString() %>" />
										</td>

										<%-- メーカーロット --%>
										<td>
											<nested:write property="manufacturerLotNo" />
										</td>

										<%-- 使用数 --%>
										<td rowspan="2" class="alignRight">
											<nested:write property="strResultQty" />
										</td>

										<%-- ｻﾝﾌﾟﾙ --%>
										<td rowspan="2" class="alignRight">
											<nested:write property="strSampleQty" />
										</td>

										<%-- ロス --%>
										<td rowspan="2" class="alignRight">
											<nested:write property="strLossQty" />
										</td>

										<%-- 不良 --%>
										<td rowspan="2" class="alignRight">
											<nested:write property="strDefectQty" />
										</td>

										<%-- 調整 --%>
										<td rowspan="2" class="alignRight">
											<nested:write property="strAdjustQty" />
										</td>

										<%-- ロット検索画面 --%>
										<td rowspan="2">
										<nested:notEqual name="pkgRdirectionFormulaListForm" property="directionStatus" value="7">
										<nested:notEmpty property="strSeq">
										<nested:empty property="resultQty">
											<nested:define id="div" property="directionDivision" />
											<nested:define id="dno" property="directionNo" />
											<nested:define id="sno" property="stepNo"/>
											<nested:define id="lno" property="lineNo" />
											<nested:define id="icd" property="itemCd" />
											<nested:define id="iname" property="itemName" />
											<nested:define id="lot" property="lotNo" />
											<nested:define id="rqty" property="strResultQty" />
											<div>
												<a href="#"  class="cssButton"
													onclick="<%="toLotSearch('" + request.getContextPath()
														                        + "/PkgRdirectionLotInventorySearch.do?op=init&directionDivision=" + pageContext.findAttribute("div").toString()
																				+ "&directionNo=" + pageContext.findAttribute("dno").toString()
																				+ "&stepNo=" + pageContext.findAttribute("sno").toString()
																				+ "&lineNo=" + pageContext.findAttribute("lno").toString()
																				+ "&srhItemCd=" + pageContext.findAttribute("icd").toString()
																				+ "&srhItemName=" + URLEncoder.encode(pageContext.findAttribute("iname").toString(), "UTF-8")
																				+ "&srhLotNo=" + pageContext.findAttribute("lot").toString()
																				+ "&useQty=" + pageContext.findAttribute("rqty").toString()
																				+ "'); return false;"%>">選
												</a>
											</div>
										</nested:empty>
										<nested:notEmpty property="resultQty">
											<%-- 在庫 キャンセル --%>
											<div id="cssButton">
													<a href='#' onclick="if (!(putResetConfirm())) {return false;}else{setLine('<%=pageContext.findAttribute("index").toString()%>');return form_submit('op', 'reset');return false;}" class="cssButton">戻</a>
											</div>
										</nested:notEmpty>
										</nested:notEmpty>
										</nested:notEqual>
										</td>
										<nested:empty property="strSeq">
											<%-- 詳細 --%>
											<td rowspan="2">
													選
											</td>
										</nested:empty>
										<nested:notEmpty property="strSeq">
											<nested:define id="div" property="directionDivision" />
											<nested:define id="dno" property="directionNo" />
											<nested:define id="sno" property="stepNo" />
											<nested:define id="lno" property="lineNo" />
											<%-- 詳細 --%>
											<td rowspan="2">
												<html:link href="#" onclick='<%="toDetail('" + request.getContextPath()
														                                    + "/PkgRdirectionFormulaDetail.do?op=init&directionDivision=" + pageContext.findAttribute("div").toString()
																							+ "&directionNo=" + pageContext.findAttribute("dno").toString()
																							+ "&stepNo=" + pageContext.findAttribute("sno").toString()
																							+ "&lineNo=" + pageContext.findAttribute("lno").toString()
																							+ "'); return false;"%>'>
													選
												</html:link>
											</td>
										</nested:notEmpty>
											</tr>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>

										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<%-- 品目名称 --%>
										<td>
											<div id="<%="lblItemName" + pageContext.findAttribute("index").toString() %>">
												<nested:write property="itemName" />
											</div>
											<nested:hidden property="itemName" styleId="<%="itemName" + pageContext.findAttribute("index").toString() %>" />
										</td>
										<%-- 入荷ロット --%>
										<td>
											<nested:write property="lotNo" />
										</td>
											</tr>
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
							<nested:notEqual property="directionStatus" value="7">
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

							</nested:notEqual>
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

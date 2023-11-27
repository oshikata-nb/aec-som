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

		<%-- 日付型フィールド定義 --%>
		defineAsDateField("nextDeliverlimitDate");
		<%-- 時刻型フィールド定義 --%>
		defineAsTimeField("nextDeliverlimitDateTime");

		<%-- 数値型フィールド定義 --%>
		var count = $F("detailCount");
		for(var i = 0 ; i < count; i++){
			var prefix = "detailList[" + i + "].";

			<%-- 入荷予定数量 --%>
			defineAsNumberField(prefix + "strArrivalQuantity");
			<%-- ラベル枚数 --%>
			defineAsNumberField(prefix + "labelCount");
		}

		<%-- 発注残数量算出 --%>
		calcBalanceOrderQuantity();

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 発注残数量の計算 --%>
	function calcBalanceOrderQuantity(){
		var count = $F("detailCount");
		var sum = 0;
		for(var i = 0 ; i < count ; i++ ){
			var buf =  $F("detailList[" + i + "].strArrivalQuantity");
			if( !blank(buf)){
				var buf2 = parseFloat(buf.replace(/,/g, ""));
				if( !isNaN(buf2) ){
					sum += buf2;
					$("detailList[" + i + "].strArrivalQuantity").value = digitFormat($F("decimalPoint"),$F("round"),buf2);
				}
			}
		}

		var orderQty = $F("orderQuantity");
		var sumArrivalQty = $F("sumArrivalQuantity");
		var balanceQty = orderQty - sumArrivalQty - sum
		if (balanceQty < 0) {
			balanceQty = 0;
		}

		<%-- 桁数丸め部品呼び出し --%>
		var buf1 = digitFormat($F("decimalPoint"),$F("round"),balanceQty);
		$("balanceOrder").innerText = buf1 + " " + $F("unit");
		$("balanceOrderQuantity").value = buf1;
	}

	<%-- 行削除確認メッセージ --%>
	function putDelListConfirm() {
		var count = $F("detailCount");

		var flag = false;
		for(var i = 0 ; i < count ; i++){
			var chk = $F("detailList[" + i + "].checkFlg");
			if(chk){
				flag = true;
				break;
			}
		}
		if(flag){
			alertMsg = new Array();
			alertMsg[0] = "削除してよろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			alertMsg = new Array();
			alertMsg[0] = "行が選択されていません。";
			alert(alertMsg[0]);
			return false;
		}
		
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- ラベル発行確認メッセージ --%>
	function issueAlert() {
		var labelCount = 0;

		if (!putDirtyConfirmIssue()) {
			return false;
		}

		var count = $F("detailCount");
		for(var i = 0 ; i < count; i++){
			var prefix = "detailList[" + i + "].";
			if ($F("detailList[" + i + "].labelCount") != ""
					 && !isNaN($F("detailList[" + i + "].labelCount"))){
			  labelCount = labelCount + eval($F("detailList[" + i + "].labelCount"));
			}
		}

		alertMsg = new Array();
		alertMsg[0] = "ラベル枚数" + labelCount + "枚発行してもよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}
	
	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 変更有の確認メッセージ --%>
	function putDirtyConfirmIssue() {
		if ($F("status") < 5) {
			return alert("入荷登録が行われていません。");
		}

		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			alertMsg = new Array();
			alertMsg[0] = "画面の編集内容は反映されません、よろしいですか？";
			return confirm(alertMsg[0]);
		}else{
			return true;
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ArrivalDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />
	<nested:hidden property="detailCount" styleId="detailCount" />
	<nested:hidden property="orderQuantity" styleId="orderQuantity" />
	<nested:hidden property="sumArrivalQuantity" styleId="sumArrivalQuantity" />
	<nested:hidden property="balanceOrderQuantity" styleId="balanceOrderQuantity" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="unit" styleId="unit" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="status" styleId="status" />
	<nested:hidden property="nyukaFlg" styleId="nyukaFlg" />

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
							<td class="fcTitle fb" width="250">入荷入力</td>
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

				<tr>
					<td><!-- 明細部 -->
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td> 
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注番号</td>
									<td>
										<nested:write property="buySubcontractOrderNo" />
										<nested:notEmpty property="orderDivideNo">
											-<nested:write property="orderDivideNo" />
										</nested:notEmpty>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">発注書NO</td>
									<td><nested:write property="orderSheetNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注日</td>
									<td><nested:write property="strOrderDate" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入番号</td>
									<td><nested:write property="slipNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td><nested:write property="itemCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">品目名称</td>
									<td><nested:write property="itemQueueName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード</td>
									<td><nested:write property="otherCompanyCd1" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先コード</td>
									<td><nested:write property="venderCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先名称</td>
									<td><nested:write property="venderShortedName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先コード</td>
									<td><nested:write property="locationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">納入先名称</td>
									<td><nested:write property="locationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注数量</td>
									<td>
										<span style="width:120;" class="alignRight">
											<nested:write property="strOrderQuantity" />
										</span>
										　<nested:write property="unit" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">荷姿</td>
									<td><nested:write property="styleOfPacking" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注重量</td>
									<td>
										<span style="width:120;" class="alignRight">
											<nested:write property="strOrderConvertQuantity" />
										</span>
										　Kg
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">発注残数量</td>
									<td>
										<span id="balanceOrder" style="width:120;" class="alignRight">
										</span>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納品希望日</td>
									<td><nested:write property="strSuggestedDeliverlimitDate" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先受注番号</td>
									<td><nested:write property="siOrderNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">次回納品希望日</td>
									<td>
										<nested:text property="nextDeliverlimitDate" maxlength="8" size="12" styleId="nextDeliverlimitDate" style="ime-mode:disabled" onchange="setDirtyFlg();" />
										<nested:text property="nextDeliverlimitDateTime" maxlength="5" size="6" styleId="nextDeliverlimitDateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">担当部署</td>
									<td>
										<nested:write property="organizationName" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="fcTitle fb" height="20">ロット分割</td>
						</tr>
						<tr>
							<td><%-- 複数明細部 --%>
							<table cellspacing="2" cellpadding="1" id="tblList">
								<tr class="bcTitleList fb fcTitleList">
									<td></td>
									<td>行番号</td>
									<td>ﾒｰｶｰﾛｯﾄ番号</td>
									<td>入荷ロット番号</td>
									<td>入荷予定量</td>
									<td>単位</td>
									<td>ラベル枚数</td>
								</tr>
								<%-- 明細部 >>>>> --%>
								<nested:iterate id="detailList" property="detailList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<td><%-- 削除チェックボックス --%>
										<nested:checkbox property="checkFlg" styleId="checkFlg" />
									</td>
									<td align="right"><%-- 行番号 --%>
										<nested:write property="strRowNo" />
									</td>
									<td><%-- メーカーロット番号 --%>
										<nested:text property="supplierLotno" maxlength="20" size="15" styleId="supplierLotno" onchange="setDirtyFlg();" style="ime-mode:disabled" />
									</td>
									<td><%-- 入荷ロット番号 --%>
										<nested:write property="lotNo" />
									</td>
									<td><%-- 入荷予定数量 --%>
										<nested:text property="strArrivalQuantity" maxlength="22" size="13" styleId="arrivalQuantity"
											onblur="calcBalanceOrderQuantity();" onchange="setDirtyFlg();" style="ime-mode:disabled" />
									</td>
									<td><%-- 単位 --%>
										<nested:write property="unit" />
									</td>
									<td><%-- ラベル枚数 --%>
										<nested:text property="labelCount" maxlength="4" size="5" styleId="labelCount" style="ime-mode:disabled" />
									</td>
								</tr>
								</nested:iterate>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
							<nested:lessThan property="labelHakko" value = "1">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return form_submit('op', 'addRow'); return false;"
													class="cssButton">行&nbsp;&nbsp;追&nbsp;&nbsp;加</a>
											</div>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="if (!(putDelListConfirm())) {return false;}else{return form_submit('op', 'delRow'); return false;}"
													class="cssButton">行&nbsp;&nbsp;削&nbsp;&nbsp;除</a>
											</div>
										</td>
									</tr>
								</table>
								</nested:lessThan>
							</td>
						</tr>
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">発注書備考</td>
									<td><nested:textarea property="orderSheetRemark2" cols="70" rows="3" styleId="orderSheetRemark2" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">備考</td>
									<td><nested:textarea property="remark2" cols="70" rows="3" styleId="remark2" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
							<table>
								<tr>
									<nested:equal property="ifUpdateStatus" value="0">
									    <nested:lessThan property="labelHakko" value = "1">
										<nested:equal property="updateAuthority" value="true">
											<td>
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
											</td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</td>
										</nested:equal>
										<td class="alignCenter">
										<div id="cssButton">
											<a href="#" onclick="if (!(issueAlert())) {return false;}else{return form_submit('op', 'issue'); return false;}" class="cssButton">
											&nbsp;&nbsp;ラベル発行&nbsp;&nbsp;
											</a>
										</div>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										</nested:lessThan>
										</nested:equal>
										<td class="allRight">
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
			</td>
		</tr>
		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		<%-- Excelダウンロード処理 ここまで --%>
	</table>

</nested:form>
</body>
</html:html>

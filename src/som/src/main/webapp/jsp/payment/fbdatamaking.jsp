<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから --%>
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
		defineAsRequiredField("paymentDate");

		var count = $F("detailCount");
		for(var i = 0 ; i < count; i++){
			var prefix = "fbDataMakingList[" + i + "].";

			<%-- 数値型フィールド定義 --%>
			<%-- 振込金額 --%>
			defineAsNumberField(prefix + "strTransferAmountN");
			
			<%-- 振込金額の変更イベント --%>			
			Event.observe(prefix + "strTransferAmountN",'change',setDirtyFlg, false);
		}

		if ($("transferClientCd") != null) {
			defineAsNumberField("trasferTotalNumber");
			defineAsNumberField("trasferTotalAmount");
		}
		if (count == 0) {
			<%-- 日付型フィールド定義 --%>
			defineAsDateField("paymentDate");
		}

		<%-- FBデータダウンロード処理 --%>
		downloadFb();
		
		if (count == 0) {
			$("paymentDate").focus();
		} else {
			$("fbDataMakingList[0].strTransferAmountN").focus();
		}
		
	}, false);

	<%-- 登録確認メッセージ --%>
	function putInsertConfirm() {
		if(!noCheck()) {
			return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
	
		return confirm(alertMsg[0]);
	}

	<%-- 再作成確認メッセージ --%>
	function putDeleteConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "削除／再作成を実行してよろしいですか？";
	
		return confirm(alertMsg[0]);
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			<%-- 何か値が変更されている場合 --%>
			return continueConfirm2();
		} else {
			if ($("strDlDate").value == "") {
				return continueConfirm2();
			}
			return true;
		}
	}

	<%-- 編集内容破棄時に、処理を実行するかの判断を戻す --%>
	function continueConfirm2() {
		<%-- 画面の編集内容は破棄されます、よろしいですか？ --%>
		var flag = confirm("<bean:message key="message.confirm.under.edit" />");
		if (!flag) {
			return false;
		}
		return true;
	}

	<%-- 振込金額のフォーマット --%>
	function format(index){
		var buf =  $F("fbDataMakingList[" + index + "].strTransferAmountN");
		if( !blank(buf)){
			var buf2 = parseFloat(buf.replace(/,/g, ""));
			if( !isNaN(buf2) ){
				$("fbDataMakingList[" + index + "].strTransferAmountN").value = digitFormat($F("decimalPoint"),$F("round"),buf2);
			}
		}
	}

	<%-- 合計件数の計算 --%>
	function calcTransferTotalNumber() {
		var count = $F("detailCount");
		var transferTotalNumber = 0;

		for(var i = 0 ; i < count ; i++ ){
			if (!$("fbDataMakingList[" + i + "].checkFlg").checked) {
				continue;
			}
			transferTotalNumber += 1;
		}

		$("transferTotalNumber").value = transferTotalNumber;
	}

	<%-- 合計金額の計算 --%>
	function calcTransferTotalAmount() {
		var count = $F("detailCount");
		var transferAmount = 0;
		var transferTotalAmount = 0;

		for(var i = 0 ; i < count ; i++ ){
			if (!$("fbDataMakingList[" + i + "].checkFlg").checked) {
				continue;
			}

			var transferAmount =  $F("fbDataMakingList[" + i + "].strTransferAmountN");	// 振込金額
			if(blank(transferAmount)){
				transferAmount = "0";
			} else {
				transferAmount = digitStringFormat($F("decimalPoint"),$F("round"),transferAmount);
				if( !blank(transferAmount) ){
					$("fbDataMakingList[" + i + "].strTransferAmountN").value = transferAmount;
				} else {
					transferAmount = "0";
				}
			}

			transferTotalAmount += parseFloat(transferAmount.replace(/,/g, ""));
		}

		// 振込金額合計
		transferTotalAmount = digitFormat($F("decimalPoint"),$F("round"),transferTotalAmount);
		$("transferTotalAmount").value = transferTotalAmount;
	}
	
	<%-- FBデータダウンロード --%>
	function downloadFb() {
		if ($('fbDownloadFlag').value == "1") { 
			$('mainForm').target = "hideDownLoadFrame";
			$('op').value = "downLoadFbdata";
			$('mainForm').submit();
			$('mainForm').target = "";
			$('fbDownloadFlag').value = "";
		}
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck() {
		var count = $F("detailCount");
		var selectFlag = 0;

		for(var i = 0 ; i < count; i++ ) {
			if (!$("fbDataMakingList[" + i + "].checkFlg").checked) {
				continue;
			}
			selectFlag = 1;
			break;
		}
		if (selectFlag == 0) {
			alert("データ・レコードが選択されていません。");
	    	return false;
	    }
		return true;
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<!-- 隠し表示エリア -->
<IFRAME name="hideDownLoadFrame" style="display:none" ></IFRAME>

<nested:form action="/FbdataMaking" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="detailCount" styleId="detailCount" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="fbDownloadFlag" styleId="fbDownloadFlag" />	

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">ＦＢデータ作成</td>
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
							<td height="5" colspan="2"></td>
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
					<td><!-- 検索部 -->
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch" width="130">銀行</td>
									<td>
										<nested:text property="bankMasterCd" size="20" styleId="bankMasterCd" readonly="true" styleClass="noborderAl" tabindex="-1" />
										<nested:text property="bankMasterName" size="40" styleId="bankMasterName" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">支払日付</td>
									<td>
										<nested:equal property="detailCount" value="0"><%-- 一覧表示なし --%>
											<nested:text property="paymentDate" maxlength="10" size="10" styleId="paymentDate" style="ime-mode:disabled"/>
										</nested:equal>
										<nested:notEqual property="detailCount" value="0"><%-- 一覧表示あり --%>
											<nested:text property="paymentDate" readonly="true" size="10" styleClass="noborderAl" tabindex="-1" />
										</nested:notEqual>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignRight">
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<nested:equal property="updateAuthority" value="true">
										<nested:equal property="detailCount" value="0"><%-- 一覧表示なし --%>
											<td>
											<div id="cssButton">
												<a href="#" onclick="return form_submit('op', 'search'); return false;" class="cssButton">
												&nbsp;&nbsp;実&nbsp;&nbsp;行&nbsp;&nbsp;
												</a>
											</div>
											</td>
										</nested:equal>
									</nested:equal>
									<nested:notEqual property="detailCount" value="0"><%-- 一覧表示あり --%>
										<td class="fcTitleDetail fb bcTitleDetail">作成日時</td>
										<td>
											<nested:text property="strDlDate" size="12" styleId="strDlDate" readonly="true" styleClass="noborderAl" tabindex="-1" />
										</td>									
									</nested:notEqual>
								</tr>
							</table>
							</td>
						</tr>

						<tr height="5">
							<td></td>
						</tr>

						<tr>
							<td class="bcTitleLine"></td>
						</tr>

					</table>
					</td>
				</tr>
				<nested:notEmpty property="transferClientCd">
				<tr height="5">
					<td></td>
				</tr>
				<tr>
					<td><!-- 明細部 -->
					<table border="0" cellspacing="" cellpadding="" width="750">
						<tr>
							<td class="fcTitle fb">ヘッダー・レコード</td>
						</tr>
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">依頼人コード</td>
									<td>
										<nested:text property="transferClientCd" size="10" styleId="transferClientCd" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">依頼人名</td>
									<td>
										<nested:text property="transferClientName" size="30" styleId="transferClientName" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">振込指定日</td>
									<td>
										<nested:text property="trasferDate" size="5" styleId="trasferDate" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">銀行コード</td>
									<td>
										<nested:text property="bankCd" size="5" styleId="bankCd" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">銀行名</td>
									<td>
										<nested:text property="bankKanaName" size="30" styleId="bankKanaName" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">支店コード</td>
									<td>
										<nested:text property="branchCd" size="5" styleId="branchCd" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">支店名</td>
									<td>
										<nested:text property="branchKanaName" size="30" styleId="branchKanaName" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">預金種目</td>
									<td>
										<nested:text property="accountDivision" size="5" styleId="accountDivision" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">口座番号</td>
									<td>
										<nested:text property="accountNo" styleId="accountNo" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="bcTitleLine"></td>
						</tr>
						<tr height="5">
							<td></td>
						</tr>
						<tr>
							<td class="fcTitle fb">データ・レコード</td>
						</tr>
						<tr>
							<td>
							<table width="750" border="0" cellspacing="2" cellpadding="1">
								<tr class="bcTitleList fb fcTitleList">
									<td></td>
									<td width="50">銀行<br>コード</td>
									<td>銀行名</td>
									<td>支店<br>コード</td>
									<td>支店名</td>
									<td>手形<br>交換所</td>
									<td>預金<br>種目</td>
									<td>口座番号</td>
									<td>受取人名</td>
									<td class="alignRight">振込金額</td>
								</tr>
								<nested:iterate id="detailList" property="fbDataMakingList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<td><%-- チェックボックス --%>
										<nested:checkbox property="checkFlg" styleId="checkFlg" onclick="calcTransferTotalNumber();calcTransferTotalAmount();" /><%=index.intValue() + 1%>
									</td>
									<td><%-- 銀行コード --%>
										<nested:write  property="bankMasterCd" />
									</td>
									<td><%-- 銀行名 --%>
										<nested:write  property="bankKanaName" />
									</td>
									<td><%-- 支店コード --%>
										<nested:write  property="branchCd" />
									</td>
									<td><%-- 支店名 --%>
										<nested:write  property="branchKanaName" />
									</td>
									<td><%-- 手形交換所 --%>
										<nested:write  property="clearinghouse" />
									</td>
									<td><%-- 預金種目 --%>
										<nested:write  property="depositDivision" />
									</td>
									<td><%-- 口座番号 --%>
										<nested:write  property="accountNo" />
									</td>
									<td><%-- 受取人名 --%>
										<nested:write  property="accountStockhold" />
									</td>
									<td><%-- 振込金額 --%>
										<nested:text property="strTransferAmountN" size="10" styleId="strTransferAmountN" onblur="<%="format(" + pageContext.findAttribute("index").toString()  + ");calcTransferTotalAmount();" %>" style="ime-mode:disabled" />
									</td>
								</tr>
								</nested:iterate>
							</table>
							</td>
						</tr>
						<tr>
							<td class="bcTitleLine"></td>
						</tr>
						<tr height="5">
							<td></td>
						</tr>
						<tr>
							<td class="fcTitle fb">トレーラ・レコード</td>
						</tr>
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr class="bcTitleList fb fcTitleList">
									<td class="alignRight" >合計件数</td>
									<td class="alignRight" >合計金額</td>
								</tr>
								<tr>
									<td><%-- 合計数 --%>
										<nested:text property="transferTotalNumber" size="10" styleId="trasferTotalNumber" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
									<td><%-- 振込金額 --%>
										<nested:text property="transferTotalAmount" size="10" styleId="trasferTotalAmount" readonly="true" styleClass="noborderAl" tabindex="-1" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table>
						<tr>
							<nested:notEqual property="fbDownloadFlag" value="1">
								<nested:equal property="updateAuthority" value="true">
									<td>
										<div id="cssButton">
											<a href="#" onclick="if (!(putInsertConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:equal>

								<nested:equal property="deleteAuthority" value="true">
									<nested:notEqual property="strDlDate" value="">
										<td>
											<div id="cssButton">
												<a href="#" onclick="if (!(putDeleteConfirm())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
												&nbsp;&nbsp;削除／再作成&nbsp;&nbsp;
												</a>
											</div>
										</td>
									</nested:notEqual>
								</nested:equal>
							</nested:notEqual>
							<td>
								<div id="cssButton">
									<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'clear'); return false;}" class="cssButton">
										&nbsp;&nbsp;クリア&nbsp;&nbsp;
									</a>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				</nested:notEmpty>
			</table>
			</td>
		</tr>
	</table>
</nested:form>
</body>
</html:html>

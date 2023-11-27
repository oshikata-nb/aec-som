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

		var count = $F("detailCount");
		for(var i = 0 ; i < count; i++){
			var prefix = "detailList[" + i + "].";

			<%-- 必須フィールド定義 --%>
			var kbn = $F("kbn");
			if (kbn != "F" && kbn != "G") {
				// F:仕入直送品,G:スポット品以外の場合
				defineAsRequiredField(prefix + "housingLocationCd");
			}
			defineAsRequiredField(prefix + "strAcceptQuantity");
			defineAsRequiredField(prefix + "strAcceptDate");

			<%-- 数値型フィールド定義 --%>
			<%-- 受入数量 --%>
			defineAsNumberField(prefix + "strAcceptQuantity");
			<%-- 増付枚数 --%>
			defineAsNumberField(prefix + "strIncreaseQuantity");

			<%-- 日付型フィールド定義 --%>
			defineAsDateField(prefix + "strAcceptDate");
		}

		<%-- 発注残数量算出 --%>
		calcBalanceOrderQuantity();

		<%-- 完納フラグチェック制御 --%>
		checkedDelCompInit();

		<%-- 部署のauto complete --%>
		new Ajax.Autocompleter(
		  "organizationCd",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/OrganizationForAutoComplete.do?op=searchOrganization",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOrganizationLabel
		  }
		);
		<%-- 名称のクリアauto complete --%>
		Event.observe('organizationCd', 'keypress', clearLabel.bindAsEventListener($('organizationName')), false);

		<%-- 入庫ロケーションのautocomplete --%>
		var count = $F("detailCount");
		for(var i = 0 ; i < count ; i++ ){
			new Ajax.Autocompleter(
				"detailList[" + i + "].housingLocationCd",
				"autocomplete_selection",
				"<%= request.getContextPath() %>/LocationForAutoComplete.do?op=searchAvailableLocation",
				{
					paramName : "code"
				}
			);
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 部署auto completeの選択後処理 --%>
	function setOrganizationLabel(text, li) {
	    $("lblOrganizationName").update(li.getElementsByTagName('span')[0].innerText);
	    $("organizationName").value = li.getElementsByTagName('span')[0].innerText;
	}

	<%-- 発注残数量の計算 --%>
	function calcBalanceOrderQuantity(){
		var count = $F("detailCount");
		var sum = 0;
		for(var i = 0 ; i < count ; i++ ){
			var buf =  $F("detailList[" + i + "].strAcceptQuantity");
			if( !blank(buf)){
				var buf2 = parseFloat(buf.replace(/,/g, ""));
				if( !isNaN(buf2) ){
					sum += buf2;
					$("detailList[" + i + "].strAcceptQuantity").value = digitFormat($F("decimalPoint"),$F("round"),buf2);
				}
			}
		}

		var orderQty = $F("orderQuantity");
		var sumAcceptQty = $F("sumAcceptQuantity");
		var balanceQty = orderQty - sumAcceptQty - sum;
		var balanceQtyRaw = balanceQty;
		if (balanceQty < 0) {
			balanceQty = 0;
		}

		<%-- 桁数丸め部品呼び出し --%>
		var buf1 = digitFormat($F("decimalPoint"),$F("round"),balanceQty);
		$("balanceOrder").innerText = buf1 + "　" + $F("unit");
		var buf1 = digitFormat($F("decimalPoint"),$F("round"),balanceQtyRaw);
		$("balanceOrderQuantity").value = buf1;
	}

	<%-- 増付数量フォーマット --%>
	function fmtIncQty(index){
		var buf =  $F("detailList[" + index + "].strIncreaseQuantity");
		if( !blank(buf)){
			var buf2 = parseFloat(buf.replace(/,/g, ""));
			if( !isNaN(buf2) ){
				$("detailList[" + index + "].strIncreaseQuantity").value = digitFormat($F("decimalPoint"),$F("round"),buf2);
			}
		}
	}

	<%-- 完納フラグチェック制御(初期表示) --%>
	function checkedDelCompInit() {
		if ($F("op") == "init") {
			if ($F("nyukaFlg") != "0" && $F("lastDataFlg") == "1") {
				var balanceQty = $F("balanceOrderQuantity");	

				// 発注残数＝０の場合
				if (balanceQty == 0) {
					$("deliveryComp").checked = true;
				} else {
					$("deliveryComp").checked = false;
				}
			}
		}
	}

	<%-- 完納フラグチェック制御 --%>
	function checkedDelComp() {
		// 発注残数
		var balanceQty = $F("balanceOrderQuantity");

		// 発注残数＝０の場合
		if (balanceQty == 0) {
			$("deliveryComp").checked = true;
		} else {
			$("deliveryComp").checked = false;
		}
	}

	<%-- 行削除確認メッセージ --%>
	function putDelListConfirm() {
		var count = $F("detailCount");

		if( $F("detailList[0].checkFlg")){
			alertMsg = new Array();
			alertMsg[0] = "１行目は削除できません。";
			alert(alertMsg[0]);
			return false;
		} 
		var flag = false;
		for(var i = 1 ; i < count ; i++){
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

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/AcceptDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="detailCount" styleId="detailCount" />
	<nested:hidden property="orderQuantity" styleId="orderQuantity" />
	<nested:hidden property="sumAcceptQuantity" styleId="sumAcceptQuantity" />
	<nested:hidden property="balanceOrderQuantity" styleId="balanceOrderQuantity" />
	<nested:hidden property="venderCd" styleId="venderCd" />
	<nested:hidden property="unit" styleId="unit" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="round" styleId="round" />
	<nested:hidden property="kbn" styleId="kbn" />
	<nested:hidden property="nyukaFlg" styleId="nyukaFlg" />
	<nested:hidden property="lastDataFlg" styleId="lastDataFlg" />
	<nested:hidden property="status" styleId="status" />

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
							<td class="fcTitle fb" width="250">受入入力</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="120">仕入先受注番号</td>
									<td><nested:write property="siOrderNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">品目コード</td>
									<td><nested:write property="itemCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">品目名称</td>
									<td><nested:write property="itemQueueName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">他社コード１</td>
									<td><nested:write property="otherCompanyCd1" /></td>
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
										　kg
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入番号</td>
									<td><nested:write property="slipNo" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先</td>
									<td><nested:write property="venderCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入先名称</td>
									<td><nested:write property="venderShortedName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納入先</td>
									<td><nested:write property="locationCd" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">納入先名称</td>
									<td><nested:write property="locationName" /></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">部署コード</td>
									<td>
										<nested:text property="organizationCd" maxlength="10" size="12" styleId="organizationCd" onchange="setDirtyFlg();" />
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">部署名称</td>
									<td>
										<span id="lblOrganizationName">
											<nested:write property="organizationName" />
										</span>
										<nested:hidden property="organizationName" styleId="organizationName" />
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">納品希望日</td>
									<td><nested:write property="strSuggestedDeliverlimitDate" /></td>
									<td class="fcTitleDetail fb bcTitleDetail">発注残数</td>
									<td>
										<span id="balanceOrder" style="width:120;" class="alignRight">
										</span>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">完納フラグ</td>
									<td>
										<nested:equal property="nyukaFlg" value="0">
											<nested:equal property="status" value="6">
												<nested:checkbox property="deliveryComp" styleId="deliveryComp" disabled="true"/>完納
												<nested:hidden property="deliveryComp" styleId="deliveryComp"></nested:hidden>
											</nested:equal>
											<nested:notEqual property="status" value="6">
												<nested:checkbox property="deliveryComp" styleId="deliveryComp" disabled="false"/>完納
												<nested:hidden property="deliveryComp" styleId="deliveryComp"></nested:hidden>
											</nested:notEqual>
										</nested:equal>
										<nested:notEqual property="nyukaFlg" value="0">
											<nested:equal property="lastDataFlg" value="0">
												<nested:checkbox property="deliveryComp" styleId="deliveryComp" disabled="true" />完納
												<nested:hidden property="deliveryComp" styleId="deliveryComp"></nested:hidden>
											</nested:equal>
											<nested:equal property="lastDataFlg" value="1">
												<nested:equal property="status" value="6">
													<nested:checkbox property="deliveryComp" styleId="deliveryComp" disabled="true" />完納
													<nested:hidden property="deliveryComp" styleId="deliveryComp"></nested:hidden>
												</nested:equal>												
												<nested:notEqual property="status" value="6">
													<nested:checkbox property="deliveryComp" styleId="deliveryComp" />完納
													<nested:hidden property="deliveryComp" styleId="deliveryComp"></nested:hidden>
												</nested:notEqual>												
												
											</nested:equal>
										</nested:notEqual>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">仕入区分</td>
									<td>
										<nested:select property="categoryDivision" styleId="categoryDivision">
											<nested:optionsCollection property="stockinDivisionCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">次回納品希望日</td>
									<td width="200">
										<nested:equal property="nyukaFlg" value="0">
											<nested:equal property="status" value="6">
												<nested:text property="nextDeliverlimitDate" maxlength="8" size="12" styleId="nextDeliverlimitDate" style="ime-mode:disabled" onchange="setDirtyFlg();" readonly="true" />
												<nested:text property="nextDeliverlimitDateTime" maxlength="5" size="6" styleId="nextDeliverlimitDateTime" style="ime-mode:disabled" onchange="setDirtyFlg();" readonly="true" />
											</nested:equal>
											<nested:notEqual property="status" value="6">
												<nested:text property="nextDeliverlimitDate" maxlength="8" size="12" styleId="nextDeliverlimitDate" style="ime-mode:disabled" onchange="setDirtyFlg();"  />
												<nested:text property="nextDeliverlimitDateTime" maxlength="5" size="6" styleId="nextDeliverlimitDateTime" style="ime-mode:disabled" onchange="setDirtyFlg();"  />
											</nested:notEqual>

										</nested:equal>
										<nested:notEqual property="nyukaFlg" value="0">
											<nested:text property="nextDeliverlimitDate" size="12" styleId="nextDeliverlimitDate" readonly="true" />
											<nested:text property="nextDeliverlimitDateTime" size="6" styleId="nextDeliverlimitDateTime" readonly="true" />
										</nested:notEqual>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail">担当部署</td>
									<td>
										<nested:write property="chargeOrganizationName" />
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
									<td width="30"></td>
									<td width="20">No.</td>
									<td>ﾒｰｶｰﾛｯﾄ番号</td>
									<td width="100">入荷ﾛｯﾄ番号</td>
									<td>入庫ﾛｹｰｼｮﾝ</td>
									<td>入荷数量</td>
									<td>受入数量</td>
									<td>増付数量</td>
									<td>受入日</td>
								</tr>
								<%-- 明細部 >>>>> --%>
								<nested:iterate id="detailList" property="detailList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>

									<td class="alignCenter"><%-- 削除チェックボックス --%>
										<nested:checkbox property="checkFlg" styleId="checkFlg" />
									</td>
									<td align="right"><%-- 行番号 --%>
										<nested:write property="strRowNo" />
									</td>
									<td><%-- ﾒｰｶｰﾛｯﾄ番号 --%>
										<nested:text property="supplierLotno" maxlength="20" size="17" styleId="supplierLotno" onchange="setDirtyFlg();" style="ime-mode:disabled" />
									</td>
									<td><%-- 入荷ﾛｯﾄ番号 --%>
										<nested:write property="lotNo" />
									</td>
									<td><%-- 入庫ﾛｹｰｼｮﾝ --%>
										<nested:equal name="acceptDetailForm" property="kbn" value="F">
											<nested:text property="housingLocationCd" size="15" styleId="housingLocationCd" readonly="true" />
										</nested:equal>
										<nested:equal name="acceptDetailForm" property="kbn" value="G">
											<nested:text property="housingLocationCd" size="15" styleId="housingLocationCd" readonly="true" />
										</nested:equal>
										<nested:notEqual name="acceptDetailForm" property="kbn" value="F">
											<nested:notEqual name="acceptDetailForm" property="kbn" value="G">
												<nested:text property="housingLocationCd" maxlength="20" size="15" styleId="housingLocationCd" />
											</nested:notEqual>
										</nested:notEqual>
									</td>
									<td align="right"><%-- 入荷数量 --%>
										<nested:write property="strArrivalQuantity" />
									</td>
									<td><%-- 受入数量 --%>
										<nested:text property="strAcceptQuantity" maxlength="22" size="10" styleId="strAcceptQuantity"
											onblur="calcBalanceOrderQuantity();checkedDelComp();" onchange="setDirtyFlg();" />
									</td>
									<td><%-- 増付数量 --%>
										<nested:text property="strIncreaseQuantity" maxlength="22" size="10" styleId="strIncreaseQuantity"
											onblur='<%="fmtIncQty(" + pageContext.findAttribute("index").toString() + ");"%>' onchange="setDirtyFlg();" />
									</td>
									<td><%-- 受入日 --%>
										<nested:text property="strAcceptDate" maxlength="8" size="10" styleId="strAcceptDate" onchange="setDirtyFlg();" />
									</td>
								</tr>
								</nested:iterate>
							</table>
							</td>
						</tr>
						<tr>
							<nested:greaterThan property="status" value="5">
								<td height="20"></td>
							</nested:greaterThan>
							<nested:lessEqual property="status" value="5">
							<nested:equal property="nyukaFlg" value="0">
							<td class="alignCenter">
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
							</td>
							</nested:equal>
							<nested:equal property="nyukaFlg" value="1">
							<td height="20"></td>
							</nested:equal>
							<nested:equal property="nyukaFlg" value="2">
							<td class="alignCenter">
								<table cellspacing="1" cellpadding="1" border="0">
									<tr>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="return form_submit('op', 'addLoc'); return false;"
													class="cssButton">ロ&nbsp;ケ&nbsp;追&nbsp;加</a>
											</div>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<div id="cssButton">
												<a href="#"
													onclick="if (!(putDelListConfirm())) {return false;}else{return form_submit('op', 'delLoc'); return false;}"
													class="cssButton">ロ&nbsp;ケ&nbsp;削&nbsp;除</a>
											</div>
										</td>
									</tr>
								</table>
							</td>
							</nested:equal>
							</nested:lessEqual>
							
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
									<nested:equal property="updateAuthority" value="true">
										<nested:equal property="nyukaFlg" value="0">
											<td>
											<div id="cssButton">
												<nested:notEqual property="status" value="6">
													<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</nested:notEqual>
												<nested:equal property="status" value="6">
													<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</nested:equal>
											</div>
											</td>
										</nested:equal>
										<nested:notEqual property="nyukaFlg" value="0">
											<td>
											<div id="cssButton">
												<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
											</td>
										</nested:notEqual>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</nested:equal>
									<nested:equal property="deleteAuthority" value="true">
									
										<nested:equal property="status" value="6">
											<td class="allRight">
												<div id="cssButton">
													<a href="#" onclick="if (!(deleteConfirm())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
														&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:equal>
									
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>

									<td class="allRight">
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:equal>
								<nested:equal property="ifUpdateStatus" value="1">
									<td class="allRight">
										<div id="cssButton">
											<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:equal>
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
	</table>

</nested:form>
</body>
</html:html>

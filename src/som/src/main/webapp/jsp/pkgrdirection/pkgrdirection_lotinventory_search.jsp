<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>
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
	
	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();
	}, false);
	
	<%-- チェックを入れる --%>
	function setLine(line) {
		$("line").value = line;
	}
	
	<%-- フォーマット処理 --%>
	function decimalFormat(decimalPoint, val){
		var fmt = formatNumber(String(val));
		var pos = fmt.lastIndexOf(".");
		if(pos >= 0){
			var len = fmt.length;
			var decNum = fmt.substring(pos+1,len).length;
			if(decNum < decimalPoint){
				for(var i = 0 ; i < (decimalPoint - decNum) ; i++){
					fmt = fmt + "0";
				}
			}
		} else {
			if(decimalPoint > 0){
				fmt = fmt + "."
				for(var i = 0 ; i < decimalPoint ; i++){
					fmt = fmt + "0";
				}
			}
		}
		return fmt;
	}

	<%-- 加算処理 --%>
	function decimalAdd(val1, val2, decPoint) {
		var str1 = removeComma(String(val1));
		var str2 = String(val2);
		var decNum = "1"
		var decLen = 0;
		var dLen1 = str1.lastIndexOf(".") + 1;
		dLen1 = str1.length - dLen1;
		var dLen2 = str2.lastIndexOf(".") + 1;
		dLen2 = str2.length - dLen2;
		if (dLen1 > dLen2) {
			decLen = dLen1
		} else {
			decLen = dLen2
		}
		for(var i = 0 ; i < decLen ; i++){
			decNum = decNum + "0";
		}
		var intVal1 = eval(rightMoveDecPoint(str1, decLen));
		var intVal2 = eval(rightMoveDecPoint(str2, decLen));
		var addIntVal = intVal1 + intVal2;
		var decVal = leftMoveDecPoint(String(addIntVal), decLen);
		return decimalFormat(decPoint, decVal);
	}
	
	<%-- 少数点右移動処理 --%>
	function rightMoveDecPoint(str, decPoint) {
		var strVal = "";
		var strRetVal = "";
		var sign = str.indexOf("-");
		strVal = str.replace("-", "");
		var decIdx = strVal.lastIndexOf(".");
		
		for ( var i = 0; i < decIdx; i++) {
			if (strRetVal == "") {
   				if (strVal.charAt(i) != "0") {
   					strRetVal = strRetVal + strVal.charAt(i);
   				}
   			} else {
				strRetVal = strRetVal + strVal.charAt(i);
   			}
   		}
   		var j = 0;
		for ( j = decIdx + 1; j < decIdx + 1 + decPoint; j++) {
			if (strVal.length > j) {
				if (strRetVal != "") {
   					strRetVal = strRetVal + strVal.charAt(j);
   				} else {
   					if (strVal.charAt(j) != "0") {
	    				strRetVal = strRetVal + strVal.charAt(j);
	    			}
   				}
   			} else {
   				strRetVal = strRetVal + "0";
   			}
		}
		if ( j < strVal.length) {
			strRetVal = "." + strRetVal;
			for (; j < strVal.length; j++) {
   				strRetVal = strRetVal + strVal.charAt(j);
			}
		}
		if (sign != -1) {
   			strRetVal = "-" + strRetVal;		
		}
		if (strRetVal == "") strRetVal = "0";
		return strRetVal;
	}

	<%-- 少数点左移動処理 --%>
	function leftMoveDecPoint(str, decPoint) {
		var strVal = "";
		var strRetVal = "";
		var sign = str.indexOf("-");
		strVal = str.replace("-", "");
		var decIdx = strVal.lastIndexOf(".");
		
		for ( var i = strVal.length; i < decIdx; i++) {
			strRetVal = strVal.charAt(i) + strRetVal;
   		}
   		if (decIdx == -1) decIdx = strVal.length;
   		var j = 0;
		for ( j = decIdx - 1; j > decIdx - 1 - decPoint; j--) {
			if (0 <= j) {
   				strRetVal = strVal.charAt(j) + strRetVal;
   			} else {
   				strRetVal = "0" + strRetVal;
   			}
		}
		if (-1 < j) {
			strRetVal = "." + strRetVal;
			for (; j > -1; j--) {
   				strRetVal = strVal.charAt(j) + strRetVal;
			}
		}
		if (strRetVal.length > 0) {
			decIdx = strRetVal.lastIndexOf(".");
			if (decIdx == -1) {
   				strRetVal = "0." + strRetVal;
			} else if (decIdx == 0) {
   				strRetVal = "0" + strRetVal;
			}
		}
		if (sign != -1) {
   			strRetVal = "-" + strRetVal;		
		}
		return strRetVal;
	}
	
	<%-- 横計の計算 --%>
	function sumLineQty(){
		var decPoint = $('decimalPoint').value;
		var index = $F("line");
		var total = parseFloat("0.0");
		var atai = 0;
		var none = 0;
		var strArray = new Array("strUseQty", "strSampleQty", "strLossQty", "strAdjustQty");
		for(i = 0; i < strArray.length; i++) {
			var qty = $(strArray[i] + index).value;
			if (qty != ""){
				qty = removeComma(qty);
				if(!isNaN(qty)) {
					total = decimalAdd(total, qty, decPoint)
				} else {
					none++;
				}
			} else {
				none++;
			}
		}
		if (none != 5) {
	    	$("lblUseSumQty" + index).update(total);
			$("strUseSumQty" + index).value = total;
		} else {
	    	$("lblUseSumQty" + index).update("");
			$("strUseSumQty" + index).value = "";
		}
	}

	<%-- 使用数量合計の計算 --%>
	function sumUseSumQty(){
	    var decPoint = $('decimalPoint').value;
		var total = "0";
		var atai = 0;
		var payList = $('tblList');
		if (payList != null) {
			if (0 < payList.rows.length) {
				for (i = 0; null != $("strUseSumQty" + i);　i++) {
					var qty = $("strUseSumQty" + i).value;
					if (qty != ""){
						qty = removeComma(qty);
						if(!isNaN(qty)) {
							total = decimalAdd(total, qty, decPoint)
						}
					}
				}
			}
		}
		if (total == "0") {
			total = decimalFormat(decPoint, total);
		}
		$("lblTotalQty").update(total);
		$("totalQty").value = total;
	    return null;
	}

	<%-- カンマ編集解除 --%>
	function removeComma(value) {
	    return value.split(",").join("")
	}
	
	<%-- フォーマット --%>
	function formatQty(val){
		var index = $F("line");
		var value = $F(val + index);
		if ((value != null) && (value != "")) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var decPoint = $('decimalPoint').value;
		    var round = $('roundDivision').value;
			$(val + index).value = digitStringFormat(decPoint,round,value);
		}
	}

	<%-- 確認メッセージ --%>
	function putRegist() {
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = "true";
	}

	<%-- 変更確認 --%>
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
<nested:form action="/PkgRdirectionLotInventorySearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<nested:hidden property="line" styleId="line" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg" />
	<nested:hidden property="decimalPoint" styleId="decimalPoint" />
	<nested:hidden property="roundDivision" styleId="roundDivision" />
	<nested:hidden property="totalQty" styleId="totalQty" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="5" colspan="2"></td>
								</tr>
								<tr>
									<td class="fcTitle fb" nowrap>ロット検索</td>
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
										<table cellspacing="2" cellpadding="2"  border="0">
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch" width="90">コード</td>
												<td width="250">
													<nested:write property="srhItemCd" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch" width="90">使用数</td>
												<td width="100" class="alignRight">
													<nested:write property="useQty" />
												</td>
											</tr>
											<tr> 
												<td class="bcTitleSearch fb fcTitleSearch">名称</td>
												<td>
													<nested:write property="srhItemName" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch" width="90">total数</td>
												<td class="alignRight">
													<div id="lblTotalQty">
													<nested:write property="totalQty" />
													</div>
												</td>
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
				</table>
				<nested:notEmpty property="searchList">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="3"></td>
						</tr>
						<tr>
							<td><!-- 明細部 -->
								<table cellspacing="2" cellpadding="2" width="100%" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="50">ロケーション</td>
										<td>名称</td>
										<td>ロット</td>
										<td>発生日</td>
										<td>在庫量</td>
										<td>使用量</td>
										<td>使用数</td>
										<td>ｻﾝﾌﾟﾙ</td>
										<td>ロス</td>
										<td>不良</td>
										<td>調整</td>
										<td width="15">単位</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
										<td>
											<nested:write property="locationCd" />
										</td>
										<td>
											<nested:write property="locationName" />
										</td>
										<td>
											<nested:write property="lotNo" />
										</td>
										<td>
											<nested:write property="issueDate" format="yyyy/MM/dd" />
										</td>
										<td class="alignRight">
											<nested:write property="strInventoryQty" />
										</td>
										<td class="alignRight">
											<div id="<%="lblUseSumQty" + pageContext.findAttribute("index").toString() %>">
											<nested:write property="strUseSumQty" />
											</div>
											<nested:hidden property="strUseSumQty" styleId="<%="strUseSumQty" + pageContext.findAttribute("index").toString() %>" />
										</td>
										<td>
											<nested:text property="strUseQty" maxlength="22" size="7" styleId='<%= "strUseQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatQty('strUseQty');sumLineQty();sumUseSumQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
										</td>
										<td>
											<nested:text property="strSampleQty" maxlength="22" size="5" styleId='<%= "strSampleQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatQty('strSampleQty');sumLineQty();sumUseSumQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
										</td>
										<td>
											<nested:text property="strLossQty" maxlength="22" size="5" styleId='<%= "strLossQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatQty('strLossQty');sumLineQty();sumUseSumQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
										</td>
										<td>
											<nested:text property="strDefectQty" maxlength="22" size="5" styleId='<%= "strDefectQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatQty('strDefectQty');"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
										</td>
										<td>
											<nested:text property="strAdjustQty" maxlength="22" size="5" styleId='<%= "strAdjustQty" + pageContext.findAttribute("index").toString() %>' onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatQty('strAdjustQty');sumLineQty();sumUseSumQty();"%>" style="ime-mode:disabled" styleClass="alignRight" onchange="setDirtyFlg();" />
										</td>
										<td>
											<nested:write property="unitName" />
										</td>
									</nested:iterate>
								</table>
							</td>
						</tr>
						<tr>
							<td height="3"></td>
						</tr>
						<tr>
							<td class="alignCenter">
								<%-- ページング --%>
								<%@ include file="/jsp/common/pager/pager.jsf" %>
								<%-- ページング ここまで --%>
							</td>
						</tr>
						<tr>
							<td height="5"></td>
						</tr>
					</table>
				</nested:notEmpty>
				<tr>
				<td class="alignCenter">
					<table cellpadding="0" cellspacing="0" width="80%" border="0">
						<tr>
							<td colspan="3" height="2"></td>
						</tr>	
						<tr>
							<nested:notEmpty property="searchList">
							<td class="alignCenter">
								<div id="cssButton">
									<a href="#" onclick="if (!(putRegist())) {return false;}else{return form_submit('op', 'regist'); return false;}" class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
									</a>
								</div>
							</td>
							</nested:notEmpty>
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
			</td>
		</tr>
	</table>
</nested:form>
</body>
</html:html>
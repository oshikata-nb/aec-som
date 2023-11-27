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
	
	<%-- カンマ編集解除 --%>
	function removeComma(value) {
	    return value.split(",").join("")
	}
	
	<%-- フォーマット --%>
	function formatQty(){
		var index = $F("line");
		var value = $F("strResultQty" + index);
		if ((value != null) && (value != "")) {
			<%-- 小数点桁数・端数区分で数値を再フォーマット --%>
		    var decPoint = $('decimalPointHaigo').value;
		    var round = $('roundHaigo').value;
			$("strResultQty" + index).value = digitStringFormat(decPoint,round,value);
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

	<%-- 使用量の合計 --%>
	function sumResultQty(){
		var atai = 0;
		var total = 0;
		var payList = $('tblList');
		if (payList != null) {
			if (0 < $F("count")) {
				for (i = 0; i < $F("count");　i++) {
					var qty = $("strResultQty" + i).value;
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
		    var decPoint = $('decimalPointHaigo').value;
		    var round = $('roundHaigo').value;
			var totalQty = digitFormat(decPoint,round,total);
			$("lblTotalQty").update(totalQty);
			$("totalQty").value = totalQty;
		}
	    return null;
	}

	<%-- 画面への遷移 --%>
	function toDetail(url) {
		if (putDirtyConfirm()) {
			location.href=url;
			g_lock = true;
		}
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>
</head>
<body>
<nested:form action="/MaterialRinputLotInventorySearch" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="line" styleId="line"/>
	<nested:hidden property="count" styleId="count"/>
	<nested:hidden property="totalQty" styleId="totalQty" />
	<nested:hidden property="decimalPointHaigo" styleId="decimalPointHaigo"/>
	<nested:hidden property="roundHaigo" styleId="roundHaigo"/>

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
												<td class="bcTitleSearch fb fcTitleSearch" width="90">品目コード</td>
												<td width="250">
													<nested:write property="srhItemCd" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch" width="90">使用量</td>
												<td width="100" class="alignRight">
													<nested:write property="strSumUseQty" />
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch">品目名称</td>
												<td>
													<nested:write property="srhItemName" />
												</td>
												<td class="bcTitleSearch fb fcTitleSearch" width="90">total量</td>
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
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td><!-- 明細部 -->
							<table cellspacing="2" cellpadding="2" width="100%" id="tblList">
								<tr class="bcTitleList fb fcTitleList">
									<td width="90">ロケーション</td>
									<td>名称</td>
									<td>ロット</td>
									<td width="60">荷姿</td>
									<td width="60">荷姿数</td>
									<td width="60">端数</td>
									<td>在庫量</td>
									<td>使用量</td>
									<td width="15">単位</td>
								</tr>
								<nested:iterate id="searchList" property="searchList" indexId="index">
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
										<tr class="bcList1">
									</app:modulo>
									<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
										<tr class="bcList2">
									</app:modulo>
									<%-- ロケーション --%>
									<td>
										<nested:write property="locationCd" />
									</td>
									<%-- 名称 --%>
									<td>
										<nested:write property="locationName" />
									</td>
									<%-- ロット --%>
									<td>
										<nested:write property="lotNo" />
									</td>
									<%-- 荷姿 --%>
									<td>
										<nested:write property="styleOfPacking" />
									</td>
									<%-- 荷姿数 --%>
									<td class="alignRight">
										<nested:write property="strInoutQty" />
									</td>
									<%-- 端数 --%>
									<td class="alignRight">
										<nested:write property="strFraction" />
									</td>
									<%-- 在庫量 --%>
									<td class="alignRight">
										<nested:write property="strInventoryQty" />
									</td>
									<%-- 使用量 --%>
									<td class="alignRight">
										<nested:text property="strResultQty" maxlength="22" size="15" styleId='<%= "strResultQty" + pageContext.findAttribute("index").toString() %>'
											onblur="<%="setLine(" + pageContext.findAttribute("index").toString() + ");formatQty();sumResultQty();"%>"
											onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignRight" />
									</td>
									<%-- 単位 --%>
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
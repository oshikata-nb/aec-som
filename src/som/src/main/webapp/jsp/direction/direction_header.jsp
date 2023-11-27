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

		<%-- ホールドタンクNOオートコンプリート --%>
		new Ajax.Autocompleter(
			"inputHoldTankNo",
			"autocomplete_selection",
			"<%= request.getContextPath() %>/DirectionRecipePegResouceForAutoComplete.do?op=searchHoldTank",
			{
				paramName : "code",
				callback : holdTankNoCallBack,
				afterUpdateElement : setHoldTankNoLabel
			}
		);
		Event.observe("inputHoldTankNo", 'keypress', clearDivLabel.bindAsEventListener($('holdTankNoLabel')), false);
		Event.observe("inputHoldTankNo", 'keypress', clearText.bindAsEventListener($('holdTankName')), false);


		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);
	<%-- ホールドタンクのオートコンプリート実行時調合タンクNO取得 --%>
	function holdTankNoCallBack(editor,paramText){
        return paramText + '&compoundTankNo='+ $F('compoundTankNo');
    }
	<%-- ホールドタンクauto completeの選択後処理 --%>
	function setHoldTankNoLabel(text, li) {
		var name = li.getElementsByTagName('span')[0].innerText;
		$("holdTankNoLabel").update(name);
		$("holdTankName").value = name;
	}


</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DirectionHeader" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

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
							<td class="fcTitle fb">製造指図ヘッダー情報</td>
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
					<span id="slidetabs" style="clear: left;">
						<table border="0" cellspacing="2" cellpadding="2" width="100%">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">ホールドタンクNO</td>
								<td>
									<nested:text property="inputHoldTankNo" maxlength="10" size="10" styleId="inputHoldTankNo" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
									<div id="autocomplete_selection" class="autocomplete"></div>
									<span id="holdTankNoLabel"><nested:write property="holdTankName" /></span>
									<nested:hidden property="holdTankName" styleId="holdTankName" />
									<nested:hidden property="compoundTankNo" styleId="compoundTankNo" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">工程ロット番号</td>
								<td>
									<nested:text property="lotNo" maxlength="20" size="20" styleId="lotNo" onchange="setDirtyFlg();" style="ime-mode:disabled" styleClass="alignLeft"/>
								</td>

							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">備考</td>
								<td>
									<nested:textarea property="remark" cols="80" rows="4" onchange="setDirtyFlg();" styleId="remark" />
								</td>

							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130">注釈</td>
								<td>
									<nested:textarea property="notes" cols="80" rows="4" onchange="setDirtyFlg();" styleId="notes" />
								</td>
							</tr>
						</table>
					</span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table>
						<tr>
							<nested:lessThan property="directionStatus" value="3">
								<nested:equal property="updateAuthority" value="true">
									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:equal>
								<nested:equal property="deleteAuthority" value="true">
									<td class="alignCenter">
										<div id="cssButton">
											<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
											&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</nested:equal>
							</nested:lessThan>
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

</nested:form>
</body>
</html:html>

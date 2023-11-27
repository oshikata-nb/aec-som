<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.asahikaseieng.Constants"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />
<%-- ▼業務固有css ここから --%>
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>
<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();
		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("topicTitle");
		defineAsRequiredField("topicContent");
		<%-- 数値型フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		<%-- 検索後入力された場合の不整合をただす --%>
<!--		Event.observe('topicId',  'keypress', -->
<!--			clearLabel.bindAsEventListener($('topicTitle')), false);-->
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();
	}, false);
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
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合			return continueConfirm();
		}else{
			return true;
		}
	}
	<%-- 確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}
</script>
<%-- ▲業務固有javaScriptここまで --%>
</head>
<body>
<nested:form action="/TopicDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>
	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />
	<table cellpadding="0" cellspacing="0" border="0" width="750">
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
							<td class="fcTitle fb">不具合登録</td>
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
					<td><!-- 明細部 -->
					<table cellspacing="2" cellpadding="2" border="0" width="">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">トピックID</td>
							<td>
								<nested:write property="topicId"/>
								<nested:hidden property="topicId" styleId="topicId"/>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">見出し</td>
							<td>
								<nested:text property="topicTitle" maxlength="100" size="50" styleId="topicTitle" onchange="setDirtyFlg();"/>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">内容</td>
							<td>
								<table>
								<tr>
									<nested:textarea property="topicContent" rows="10" cols="80" styleId="topicContent" onchange="setDirtyFlg();"/>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">入力者</td>
									<td>
										<nested:text property="topicInputor" maxlength="20" size="30" styleId="topicInputor" onchange="setDirtyFlg();"/>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">返答</td>
							<td>
								<table>
								<tr>
									<nested:textarea property="topicContentRet" rows="10" cols="80" styleId="topicContent" onchange="setDirtyFlg();"/>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">入力者</td>
									<td>
										<nested:text property="topicRetInputor" maxlength="20" size="30" styleId="topicRetInputor" onchange="setDirtyFlg();"/>
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
							<td>
							<div id="cssButton">
								<nested:equal property="insertFlg" value="0">
									<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
									</a>
&nbsp;&nbsp;&nbsp;&nbsp;
								</nested:equal>
								<nested:equal property="insertFlg" value="1">
									<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
									</a>
&nbsp;&nbsp;&nbsp;&nbsp;
								</nested:equal>
								<nested:equal property="insertFlg" value="0">
									<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
										&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
									</a>
&nbsp;&nbsp;&nbsp;&nbsp;
								</nested:equal>
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
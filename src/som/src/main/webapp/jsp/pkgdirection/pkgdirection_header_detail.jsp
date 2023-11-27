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

		<%-- 必須フィールド定義 --%>

		<%-- 日付型フィールド定義 --%>

		<%-- 数値型フィールド定義 --%>


		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/PkgDirectionHeaderDetail" method="post" onsubmit="return false;" styleId="mainForm">

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
							<td class="fcTitle fb" width="250" >包装指図ヘッダー情報</td>
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
				<%@ include file="/jsp/pkgdirection/pkgdirection_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

<%-- 詳細内容 --%>
				<tr>
					<td>
					<span id="slidetabs" style="clear: left;">
						<table cellspacing="2" cellpadding="1"  border="0">
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130" >備考</td>
								<td>
									<nested:textarea property="remark" cols="80" rows="4" styleId="remark" onchange="setDirtyFlg();" />
								</td>
							</tr>
							<tr>
								<td class="fcTitleDetail fb bcTitleDetail" width="130" >注釈</td>
								<td>
									<nested:textarea property="notes" cols="80" rows="4" styleId="notes" onchange="setDirtyFlg();" />
								</td>
							</tr>
						</table>
					</span>
					</td>
				</tr>
				<tr>
					<td class="alignCenter">
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<nested:equal property="jissekiFlag" value="0">
								<nested:equal property="updateAuthority" value="true">
									<td class="alignRight">
									<div id="cssButton">
										<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;録&nbsp;&nbsp;
										</a>
									</div>
									</td>
								</nested:equal>
								<nested:equal property="deleteAuthority" value="true">
									<td class="alignCenter">
									<div id="cssButton">
										<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
										&nbsp;&nbsp;削&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除&nbsp;&nbsp;
										</a>
									</div>
									</td>
								</nested:equal>
								<td class="alignLeft">
									<div id="cssButton">
										<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
											&nbsp;&nbsp;戻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;る&nbsp;&nbsp;
										</a>
									</div>
								</td>
							</nested:equal>
							<nested:notEqual property="jissekiFlag" value="0">
								<td class="alignCenter">
									<div id="cssButton">
										<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
											&nbsp;&nbsp;戻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;る&nbsp;&nbsp;
										</a>
									</div>
								</td>
							</nested:notEqual>
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

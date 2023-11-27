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

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);


</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/MgrecipeOther" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>
	<nested:hidden property="srhLink" styleId="srhLink" />

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
							<td class="fcTitle fb">基本処方</td>
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
				<%@ include file="/jsp/mgrecipe/mgrecipe_common.jsp"%>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">原処方備考</td>
									<td><nested:textarea property="generalRecipeRemark" cols="60" rows="6" styleId="generalRecipeRemark" onchange="setDirtyFlg();"/></td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">基本処方備考</td>
									<td><nested:textarea property="masterRecipeRemark" cols="60" rows="6" styleId="masterRecipeRemark" onchange="setDirtyFlg();"/></td>
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
							<nested:notEqual property="srhLink" value="1">
								<nested:equal property="approvalStatus" value="1">
									<nested:equal property="updateAuthority" value="true">
										<td class="alignCenter">
										<div id="cssButton">
											<nested:equal property="insertFlg" value="1">
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'regist'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a></nested:equal>
											<nested:equal property="insertFlg" value="0">
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a></nested:equal>
										</div>
										</td>
		
										<td width="50"><br></td>
									</nested:equal>
	
								</nested:equal>
						
								<td class="alignCenter">
									<div id="cssButton">
										<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
											&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
										</a>
									</div>
								</td>
							</nested:notEqual>
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

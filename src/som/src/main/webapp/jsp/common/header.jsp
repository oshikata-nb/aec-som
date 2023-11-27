<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>
<%@ include file="/jsp/common/postjavascript.jsf"%>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>

<%-- ここにページ固有のcssを書く
<style type="text/css">
</style>
 --%>
 
<%-- ここにページ固有のjavascriptを書く --%>
<script language="JavaScript" type="text/javascript">

function toMyPageSet(url) {
	parent.main_iframe.location.href=url;
}

</script>
</head>
<body>
<nested:form action="/Header" method="post" enctype="multipart/form-data" styleId="mainForm">
	<nested:hidden property="op"/>
	<table cellpadding="0" cellspacing="0" width="100%" border="0">
		<tr>
			<td class="fcTitle" width="240" nowrap>
				<table width="100%" height="100%" border="0">
					<tr>
						<td width="240" height="40" class="bcLogo">
							<img src='<%= request.getContextPath() + "/images/logo_img.gif"%>'>
						</td>
					</tr>
				</table>
			</td>		
			<td class="alignLeft" nowrap>
				<table width="750" border="0">
						<tr>
							<td width="80" class="bcTitleDetail fb fcTitleDetail">担当者名</td>
							<td width="150"><nested:write property="loginInfo.tantoNm"/><br></td>
							<td width="70" class="bcTitleDetail fb fcTitleDetail">所属部署</td>
							<td><nested:write property="loginInfo.organizationName"/><br></td>
							<td class="bcTitleDetail fb fcTitleDetail" width="100">ログイン日時</td>
							<td style="text-align: right;">
								<nested:define id="tcd" name="headerForm" property="loginInfo.tantoCd"/>
								<html:link href="#"
									onclick='<%=
										"toMyPageSet('" + request.getContextPath()
										+ "/MyPageSet.do?op=init&tantoCd="
										+ pageContext.findAttribute("tcd").toString()
										+ "'); return false;"
										%>'>[設　定]
								</html:link>
							<br></td>
						</tr>
						<tr>
							<td class="bcTitleDetail fb fcTitleDetail">担当者ｺｰﾄﾞ</td>
							<td><nested:write property="loginInfo.tantoCd"/><br></td>
							<td class="bcTitleDetail fb fcTitleDetail">役職</td>
							<td><nested:write property="loginInfo.roleName"/><br></td>
							<td width="130">
								<%-- ログイン日時 --%>
								<nested:write property="loginInfo.loginDate" format="yyyy/MM/dd HH:mm:ss"/>
							</td>
							<td style="text-align: right;">
								<%-- ログアウト --%>
								<div id="cssSysButton"><html:link page="/Logout.do" styleClass="sysButton">ログアウト</html:link></div>
							</td>
						</tr>
					</table>
			</td>
		</tr>
		<tr>
			<td class="bcTitleLine" colspan="2"></td>
		</tr>
	</table>
</nested:form>

</body>
</html:html>
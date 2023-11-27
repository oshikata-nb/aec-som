<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<!-- Style Sheet(mypage用) -->
<link href='<%= request.getContextPath() + "/stylesheets/mypage.css"%>' media="screen" rel="Stylesheet" type="text/css" />
<!-- Javascript(mypage用) -->
<script src='<%= request.getContextPath() + "/javascripts/minmax.js"%>' type="text/javascript"></script>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
		if (!g_lock) {
			location.href=url;
			g_lock = true;
		}
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/MyPageSet" method="post" onsubmit="return false;">
	<nested:hidden property="op" styleId="op"/>

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td>
							<!-- ヘッダー部 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb">マイページ設定</td>
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

								<tr>
									<td height="5" colspan="2"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td><!-- URL -->
				<table border="0" cellspacing="" cellpadding="" width="100%">
					<tr>
						<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td>
										<nested:define id="tcd" property="tantoCd"/>
										<nested:define id="myPageSet" property="myPageSetFlg" />
										<html:link href="#" onclick='<%="toDetail('" + request.getContextPath()	+ "/ChgPwd.do?op=init&tantoCd=" + pageContext.findAttribute("tcd").toString() + "'); return false;"%>'>パスワードの変更
										</html:link>
									</td>
								</tr>

								<tr>
									<td>
										<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/ShortCutSet.do?op=init&tantoCd=" + pageContext.findAttribute("tcd").toString() + "'); return false;"%>'>ショートカットの設定
										</html:link>
									</td>
								</tr>

								<tr>
									<td>
										<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/GadgetSet.do?op=init&tantoCd=" + pageContext.findAttribute("tcd").toString() + "'); return false;"%>'>ガジェットの変更
										</html:link>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<div id="contents">
		<div id="canvas">
			<div class="wrapper">
			</div><%-- wrapper --%>
		</div><%-- canvas --%>
	</div><%-- content --%>
</nested:form>

<div id="dummy"></div>

</body>
</html:html>

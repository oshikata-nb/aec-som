<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>

<!-- ここにページ固有のJavaScriptを書く -->
<script language="JavaScript" type="text/javascript">

	<%-- CSS切り替え --%>
	function setStyleSheet(alternate) {
		setActiveStyleSheet(alternate);
		return false;
	}

	<%-- CSSコンボデフォルト選択 --%>
	function initSelectCss(name) {
		var alternate = getActiveStyleSheet();
		var obj = $(name);
		var index = -1;
		if (alternate != null) {
			for (i = 0; i < obj.options.length; i++) {
				if (obj.options[i].value == alternate) {
					index = i;
					break;
				}
			}
		}
		<%-- 存在しない内容の場合、デフォルトを設定する --%>
		if (index == -1) {
			index = 0;
			<%-- CSS --%>
			setActiveStyleSheet(obj.options[index].value);
		}
		obj.selectedIndex = index;
	}

	<%-- リセット --%>
	function form_reset() {
		$("mainForm").reset();
		$("loginId").focus();
	}

	Event.observe(window, 'load', function() {
	
		<logic:notEqual name="loginForm" property="loginFlag" value="true">	
		
		<%-- フォーム設定の初期化 --%>
		initializeFormState();
		
		</logic:notEqual>
		
		<%-- CSSコンボデフォルト選択 --%>
		initSelectCss("selectCss");
		
	}, false);

</script>

</head>

<body>

<nested:form action="/Login" method="post" onsubmit="return false;" styleId="mainForm">
	<nested:hidden property="op" />
	<table width="100%" height="100%" cellspacing="0" cellpadding="0"  >
		<tr>
			<td class="fcTitle" height="45px" nowrap>
				<table width="100%" height="100%" border="0">
					<tr>
						<td class="bcLogo">
							<table width="100%" height="100%" border="0">
								<tr></tr>
								<tr>
									<td class="alignLeft valigenMiddle">
										<img src='<%= request.getContextPath() + "/images/logo_img.gif"%>'>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr class="alignCenter valigenMiddle">
			<td>
				<table cellspacing="2" cellpadding="2"  border="0">
					<tr>
						<td class="alignCenter fcTitle fb">こちらからログインしてください。</td>
					</tr>
					<tr>
						<td class="alignCenter">
							<%-- メッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
							<table cellspacing="0" cellpadding="0" border="3" class="bdcTable bsTable">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="2"  border="0">
											<tr class="bcList2">
												<td class="bcTitleSearch fb fcTitleSearch" width="100">ログインID</td>
												<td width="150">
													<nested:text property="userId" maxlength="10" size="12" styleId="loginId"/>
												</td>
											</tr>
											<tr class="bcList2">
												<td class="bcTitleSearch fb fcTitleSearch">パスワード</td>
												<td>
													<nested:password property="password" maxlength="10" size="12" styleId="password"/>
												</td>
											</tr>
											<tr class="bcList2">
												<td class="bcTitleSearch fb fcTitleSearch" width="100">CSSカラー</td>
												<td width="150">
													<select name="selectCss" id="selectCss"
														onChange="setStyleSheet($(selectCss).value);">
														<option value="blue" selected>青(デフォルト)</option>
														<option value="green">緑</option>
														<option value="red">赤</option>
													</select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="alignCenter" colspan="2" height="25">
							<div id="cssSysButton">
								<a href="#" onclick="return form_submit('op', 'login'); return false;" class="cssButton">ログイン</a>
								<a href="#" onclick="return form_reset(); return false;" class="cssButton">リセット</a>
							</div>
						</td>
					</tr>
				</table>
				<%-- TOP画面POPUP --%>
				<nested:equal property="loginFlag" value="true">
					<script language="JavaScript">
						
						var ret = open_main_popup(1000, 650, "MAIN_APPLICATION", '<%= request.getContextPath() + "/Login.do?op=popup" %>');
						if (!isIeVersionSeven()) {
						  <%-- ポップアップブロック対応 --%>
						  if (ret != null) {
							window.opener = window;
							window.close();
						  }
						} else {
						  <%-- IE7は閉じられない --%>
						  if (ret != null) {
							location.href = '<%= request.getContextPath() %>/blank.html';
							ret.focus();
						  }
						}
					</script>
				</nested:equal>
			</td>
		</tr>
	</table>
</nested:form>

</body>
</html:html>
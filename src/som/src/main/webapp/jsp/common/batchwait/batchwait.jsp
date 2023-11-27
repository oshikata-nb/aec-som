<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	var timer;
	
	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {
		<%-- start_interval(); --%>
		timer = setTimeout( "interval()", 5000 );
	}, false);
	
	<%--unload時の処理--%>
	Event.observe(window, 'unload', function() {
		<%-- 検索後入力された場合の不整合をただす --%>
		clearTimeout(timer);
	}, false);

	function blink() {
		if (!document.all) { return; }

		for (i = 0; i < document.all.length; i++) {
			obj = document.all(i);

			if (obj.className == "blink") {
				if (obj.style.visibility == "visible") {
					obj.style.visibility = "hidden";
				} else {
					obj.style.visibility = "visible";
				}
			}
		}

		setTimeout("blink()", 800);
	}
	
	// 時間ごとの処理
	function interval(){
		form_submit('op', 'search');
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body onload="blink()">

<nested:form action="/BatchWait" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="screenId" styleId="screenId" />
	<nested:hidden property="num" styleId="num" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr>
						<td><%-- ヘッダー部 --%>
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td height="10" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitle fb"><nested:write property="titleName"/></td>
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
							<!-- 明細部 -->
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td height="200"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb alignCenter">
										<!-- 処理中 -->
										<h1 class="blink">
											処理中
										</h1>
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

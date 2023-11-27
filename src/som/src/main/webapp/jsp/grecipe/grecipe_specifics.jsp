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

		for(var i = 0 ; i < 4 ; i++){
			var index = i + 1;
			$('lblGrFileLink' + i).update("ファイルリンク" + index);
		}

		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

	function setDownloadDiv(orb) {

	}

	<%-- チェックを入れる --%>
	function setGDiv(index) {
		var lineNo = index + 1;
		$("downloadDiv").value = "GRECIPE_DETAIL" + lineNo;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/GrecipeSpecifics" method="post" enctype="multipart/form-data" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

 	<nested:hidden property="downloadDiv"></nested:hidden>
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
							<td class="fcTitle fb">原処方</td>
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
				<%@ include file="/jsp/grecipe/grecipe_common.jsp"%>
				<%-- 共通項目部・タブ<<<<< --%>

<%-- 詳細内容 --%>
				<tr>
					<td>
						<table width="750" cellspacing="2" cellpadding="2" border="0">
							<tr>
								<td>
									<span id="slidetabs" style="clear: left;">
										<table border="0" cellspacing="2" cellpadding="2" width="100%">

											<nested:iterate id="searchGrList" property="searchGrList" indexId="index">

												<nested:hidden property="commonCd" styleId="<%="commonCd" + pageContext.findAttribute("index").toString() %>" />

													<tr>
														<td class="fcTitleDetail fb bcTitleDetail" width="130">製品仕様書
															<div id="<%="lblGrFileLink" + pageContext.findAttribute("index").toString() %>">
															</div>
														</td>
														<td>
															<nested:file property="uploadFile" size="20" onchange="setDirtyFlg();" />
															<div id="cssButton">
															<nested:text property="dispLabelPath" styleId="<%="dispLabelPath" + pageContext.findAttribute("index").toString() %>" readonly="true" size="40" onchange="setDirtyFlg();" />
																<a href="#" class="cssButton"
																	onclick="<%="setGDiv(" + pageContext.findAttribute("index").toString() + ");return form_submit('op', 'filedownload'); return false;"%>">
																&nbsp;DOWN&nbsp;
																</a>
															</div>
														</td>
													</tr>
											</nested:iterate>

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
											<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'regist'); return false;}" class="cssButton">
											&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
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
	<nested:equal property="downloadFlg" value="true">
		<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
	</nested:equal>

</nested:form>
</body>
</html:html>

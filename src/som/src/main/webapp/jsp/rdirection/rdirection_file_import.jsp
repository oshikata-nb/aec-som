<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<%-- ▼業務固有css ここから  --%>
<style type="text/css">
<!--
	#yscrollbar {width: 100%; height: 200px; overflow: auto; }
-->
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		refreshLabel();
		if($("exFileName").value != null && $("exFileName").value.length != 0){
			form_submit('op', 'getElsFileData');
		}
	}, false);

	<%-- 戻るを選択して終了 --%>
	function selectClose(){
		window.opener.setRdirectionFileImportValues(false);

		//モーダルダイアログの終了
		window.close();
	}

	<%-- 入力制限--%>
	function lockInput(){
		return false;
	}

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "ファイル読込を実行してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ --%>
	function putCheckConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "確認しましたか？";
		return confirm(alertMsg[0]);
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self">
<%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>
<body>
	<nested:form action="/RdirectionFileImport" method="post"
		enctype="multipart/form-data" onsubmit="return false;"
		styleId="mainForm">
		<%@ include file="/jsp/common/postjavascript.jsf"%>
		<nested:hidden property="op" styleId="op" />
		<nested:hidden property="exFileName" styleId="exFileName" />
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td class="valignTop alignLeft">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td>
								<!-- ヘッダー部 -->
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td height="5" colspan="2"></td>
									</tr>
									<tr>
										<td class="fcTitle fb" nowrap>ファイル取込</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<%-- メッセージ表示 --%> <%@ include
															file="/jsp/common/disp_info_message.jsf"%>
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
						<tr><td>
							<nested:equal property="logFlg" value="true">
							<div id="yscrollbar">
							</nested:equal>
							<nested:notEqual property="logFlg" value="true">
							<div>
							</nested:notEqual>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<%-- メッセージ表示 --%> <%@ include
											file="/jsp/common/disp_error_message.jsf"%>
										<%-- メッセージ表示 ここまで --%>
									</td>
								</tr>
							</table>
							</div>
						</td></tr>
						<tr>
							<td>
								<table width="100%" cellspacing="" cellpadding="" border="0">
									<tr>
										<td>
											<table cellspacing="2" cellpadding="0" border="0">
												<tr>
													<td class="bcTitleSearch fb fcTitleSearch" width="140">アップロードファイル</td>
													<td colspan="3"><nested:file property="uploadFile"
															styleId="uploadFile" size="80" onchange="return false;"
															onkeypress="return lockInput();" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="2" cellspacing="2" width="100%">
						<tr>
							<td class="alignCenter">
								<div id="cssButton">
									<a href="#"
										onclick="if(!(putConfirm())){return false;} else{return form_submit('op', 'upload');}"
										class="cssButton"> &nbsp;読込&nbsp; </a>
								</div>
							</td>
							<td class="alignCenter">
								<div id="cssButton">
									<a href="#" onclick="<%="selectClose()"%>" class="cssButton">
										&nbsp;戻る&nbsp; </a>
								</div>
							</td>
						</tr>
					</table> 
				</td>
			</tr>
		</table>
	</nested:form>
</body>
</html:html>
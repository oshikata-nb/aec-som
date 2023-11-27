<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

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

	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		refreshLabel();
		if($("exFileName").value != null && $("exFileName").value.length != 0){
			if(putMd5Confirm()){
				form_submit('op', 'getCsvFileData');
			}
		}
	}, false);

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 戻るを選択して終了 --%>
	function selectClose(){
		window.opener.setOrderFileImportValues(false);

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

	<%-- MD5確認メッセージ--%>
	function putMd5Confirm() {
		if($("checkFileImpFlg").value == 1){
			alertMsg = new Array();
			alertMsg[0] = "過去1ヵ月以内に同じファイルが取り込まれていますが、本当に実行してよろしいですか？";
			return confirm(alertMsg[0]);
		}
		return true;
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>
</head>
<body>
<nested:form action="/OrderFileImport" method="post" enctype="multipart/form-data" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>
	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="md5" styleId="md5" />
	<nested:hidden property="checkFileImpFlg" styleId="checkFileImpFlg" />
	<nested:hidden property="exFileName" styleId="exFileName" />
	<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><!-- ヘッダー部 -->
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
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<%-- メッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" cellspacing="" cellpadding=""  border="0">
								<tr>
									<td>
										<table cellspacing="2" cellpadding="0" border="0">
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="140">得意先グループ</td>
												<td>
													<nested:select property="venderGroupCd" style="margin: 0;padding: 0" styleId="venderGroupCd">
														<nested:optionsCollection property="venderGroupCombo" label="labales" value="values" />
													</nested:select>
												</td>
											</tr>
											<tr>
												<td class="bcTitleSearch fb fcTitleSearch" width="140">アップロードファイル</td>
												<td colspan="3">
													<nested:file property="uploadFile" styleId="uploadFile" size="80" onchange="return false;" onkeypress="return lockInput();"/>
												</td>
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
								<a href="#" onclick="if(!(putConfirm())){return false;} else{return form_submit('op', 'upload');}" class="cssButton">
									&nbsp;読込&nbsp;
								</a>
							</div>
						</td>
						<td class="alignCenter">
							<div id="cssButton">
								<a href="#" onclick="<%="selectClose()" %>" class="cssButton">
									&nbsp;戻る&nbsp;
								</a>
							</div>
						</td>
					</tr>
				</table>
				<nested:equal property="logFlg" value="true">
					<tr>
						<td>
							<table cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td>
										<nested:textarea property="logMsg" rows="13" cols="95" readonly="true"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="alignCenter">
							<div id="cssButton"><a href="#"
								onclick="if(!putCheckConfirm()){return false;}else{return form_submit('op', 'updateCheckFlg'); return false;}"
								class="cssButton"> &nbsp;&nbsp;確&nbsp;&nbsp;認&nbsp;&nbsp;
							</a></div>
						</td>
					</tr>
				</nested:equal>
			</td>
		</tr>
	</table>
</nested:form>
</body>
</html:html>
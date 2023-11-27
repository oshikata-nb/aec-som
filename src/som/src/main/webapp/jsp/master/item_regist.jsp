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

		<%-- 必須フィールド定義 --%>
        defineAsRequiredField("reason");
	}, false);

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 選択して終了 --%>
	function selectOK(){
		var tmp = new String("");
	  	var bc=0;

		tmp = $("reason").value;

		if(0 <tmp.length){
			bc = wc()

			if (bc <= 100){
				window.opener.setRegistValues($("reason").value, "true");				
				<%-- モーダルダイアログの終了 --%>
				window.close();
			}else{
				alert("半角100文字、全角50文字以内で入力してください。");
			}
		}else{
			alert("空欄は無効です。");
		}
	}

	<%-- 20211209 Asclab Kouchi EdgeIE対応 --%>
	<%-- 選択して終了 --%>
	function selectCancel(){
		window.opener.setRegistValues($("reason").value, "false");
		window.close();	 		
		
	}

	<%-- 入力文字バイト数を取得 --%>
	function wc(){
		var tmp = new String("");
		tmp = $("reason").value
		var bc=0;

		for(var i = 0;i < tmp.length;i++){
			if(tmp.charCodeAt(i)<0x100){
				<%-- case: ascii character. --%>
				bc+=1;
			}else{
				<%-- case: double byte character. --%>
				bc+=2;
			}
		}

		return bc;
	  }
</script>
<%-- ▲業務固有javaScript ここまで --%>

<base target="_self"><%-- ←重要(subumit時に新規windowが開かないようにするおまじない) --%>

</head>

<body>

<nested:form action="/ItemRegist" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

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
									<td class="fcTitle fb">品目マスタ</td>
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

					<tr>
						<td>
							<%-- メッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>

					<tr>
						<td>
							<!-- 明細部 -->
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">登録理由</td>
									<td>
										<nested:textarea property="reason" rows="5" cols="65" styleId="reason" />
									</td>
								</tr>

								<tr>
									<td height="5" colspan="5"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="5"></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="450" border="0">
								<tr>
									<td colspan="3" height="2">
									</td>
								</tr>

								<tr>
									<td class="alignRight">
										<div id="cssButton">
											<a href="#" onclick="<%="selectOK()" %>" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50">
										<br>
									</td>

									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="<%="selectCancel()" %>" class="cssButton">
												&nbsp;&nbsp;キャンセル&nbsp;&nbsp;
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

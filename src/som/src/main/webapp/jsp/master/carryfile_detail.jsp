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
        defineAsRequiredField("carryCd");
        
		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("strReportOutputNum");


		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- 確認メッセージ:行削除 --%>
	function putDelListConfirm() {
		if (!noCheck()) {
		  return false;
		}
		alertMsg = new Array();
		alertMsg[0] = "行を削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}

	<%-- チェックボックス選択無しチェック --%>
	function noCheck(){
		var count = $F("carryFileSettingListCount");
		var checkFlag = false;
		for (var i = 0; i < count; i++) {
			var check = $("checkline" + i).checked;
			if(check){
				checkFlag = true;
				break;
			}
		}
		if (!checkFlag) {
		    alert("削除対象がありません。");
		    return false;
		}
		return true;
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").checked;
		if (flg) {
			/* 何か値が変更されている場合 */
			return continueConfirm();
		}else{
			return true;
		}
	}
	
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}

	</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/CarryFileDetail" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="carryFileSettingListCount" styleId="carryFileSettingListCount" />

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
									<td class="fcTitle fb">運送会社連携ファイルマスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">運送会社コード</td>
									<td colspan="1">
										<nested:write property="carryCd"/>
									</td>
									<td colspan="1">
										<nested:write property="carryName"/>
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ヘッダ出力フラグ</td>
									<td colspan="1">
										<nested:checkbox property="headerFlgCheck"  styleId="headerFlgCheck" onchange="setDirtyFlg();" />
										<nested:hidden   property="headerFlgCheck"  styleId="headerFlgCheck" value="0" />
									</td>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="5"></td>
								</tr>
								<table width="100%" cellspacing="2" cellpadding="2" border="0">
									<tr>
										<td colspan="6" align="left">
											<div id="cssButton">
												<a href="#" onclick="return form_submit('op', 'addlist'); return false;" class="cssButton">
													&nbsp;&nbsp;行&nbsp;追&nbsp;加&nbsp;&nbsp;
												</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="if (!(putDelListConfirm())) {return false;}else{return form_submit('op', 'dellist'); return false;}" class="cssButton">
													&nbsp;&nbsp;行&nbsp;削&nbsp;除&nbsp;&nbsp;
												</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="return form_submit('op', 'rowup'); return false;" class="cssButton">
													&nbsp;&nbsp;上&nbsp;へ&nbsp;&nbsp;&nbsp;
												</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="return form_submit('op', 'rowdown'); return false;" class="cssButton">
													&nbsp;&nbsp;下&nbsp;へ&nbsp;&nbsp;&nbsp;
												</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="return form_submit('op', 'clearselect'); return false;" class="cssButton">
													&nbsp;&nbsp;選&nbsp;択&nbsp;解&nbsp;除&nbsp;
												</a>

											</div>
										</td>
									</tr>
								</table>
								<table width="100%" cellspacing="2" cellpadding="2" border="0">
									<tr>
										<td>
										<table cellspacing="2" cellpadding="1" id="tblList">
											<tr class="bcTitleList fb fcTitleList">
												<td width="20"></td>
												<td width="45">データ区分</td>
												<td width="120">テーブル</td>
												<td width="120">カラム</td>
												<td width="120">出力ヘッダ名</td>
												<td width="150">フリー入力</td>
												<td width="20">連結</td>
												<td width="20">集計</td>
											</tr>
							<!-- ★ループ -->
											<nested:iterate id="carryFileSettingList" property="carryFileSettingList" indexId="index">
												<app:modulo
													numerator='<%=pageContext.findAttribute("index").toString()%>'
													denominator="2" zero="false">
													<tr class="bcList1">
												</app:modulo>
			
												<app:modulo
													numerator='<%=pageContext.findAttribute("index").toString()%>'
													denominator="2" zero="true">
													<tr class="bcList2">
												</app:modulo>
			
												<%-- Itemごとの　運用管理単位、端数区分、小数点桁数を設定 --%>
												<nested:hidden property="seq" styleId="<%="seq" + pageContext.findAttribute("index").toString() %>" />
			
												<td><%-- 行追加行削除用チェックボックス --%>
													<nested:checkbox property="checkline" styleId="<%="checkline" + pageContext.findAttribute("index").toString() %>"/>
													<nested:hidden property="checkline" styleId="<%="checkline" + pageContext.findAttribute("index").toString() %>" value="0" />
												</td>
												<td><%-- データ区分 --%>
													<nested:select property="dataCls" styleId="<%="dataCls" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();return form_submit('op', 'changeselect');" >
				 										<nested:options property="dataClsValues"  labelProperty="dataClsLabels" />
													</nested:select>
												</td>
												<td><%-- テーブル --%>
													<nested:select property="tableName" styleId="<%="tableName" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();return form_submit('op', 'changeselect');" >
				 										<nested:options property="tableNameValues"  labelProperty="tableNameLabels" />
													</nested:select>
												</td>
												<td><%-- カラム --%>
													<nested:select property="columnName" styleId="<%="columnName" + pageContext.findAttribute("index").toString() %>" onchange="setDirtyFlg();" >
				 										<nested:options property="columnNameValues"  labelProperty="columnNameLabels" />
													</nested:select>
												</td>
												<td><%-- 出力ヘッダ名 --%>
													<nested:text property="headerName" styleId="<%="headerName" + pageContext.findAttribute("index").toString() %>" maxlength="64" size="18" onchange="setDirtyFlg();"/>
												</td>
												<td><%-- 固定値 /日付書式--%>
													<nested:equal property="inputReadOnly" value="false">
														<nested:text property="description" styleId="<%="description" + pageContext.findAttribute("index").toString() %>" maxlength="255" size="18" onchange="setDirtyFlg();" />
													</nested:equal>		
													<nested:notEqual property="inputReadOnly" value="false">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</nested:notEqual>		
												</td>
												<td><%-- 連結フラグ --%>
													<nested:checkbox property="linkFlgCheck"  styleId="<%="linkFlgCheck" + pageContext.findAttribute("index").toString() %>"/>
													<nested:hidden property="linkFlgCheck" styleId="<%="linkFlgCheck" + pageContext.findAttribute("index").toString() %>" value="0" />
												</td>
												<td><%-- 集計フラグ --%>
													<nested:checkbox property="sumFlgCheck"  styleId="<%="sumFlgCheck" + pageContext.findAttribute("index").toString() %>"/>
													<nested:hidden property="sumFlgCheck" styleId="<%="sumFlgCheck" + pageContext.findAttribute("index").toString() %>" value="0" />
												</td>
											</nested:iterate>
							<!-- ★ループ ここまで-->
										</table>
										</td>
									</tr>
								</table>
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
									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>
	
										<td width="50">
											<br>
										</td>
									</nested:equal>

									<nested:equal property="deleteAuthority" value="true">
										<td class="alignCenter">
											<div id="cssButton">
												<a href="#" onclick="if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
													&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
												</a>
											</div>
										</td>
	
										<td width="50">
											<br>
										</td>
										</nested:equal>

									<td class="alignLeft">
										<div id="cssButton">
											<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
												&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
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

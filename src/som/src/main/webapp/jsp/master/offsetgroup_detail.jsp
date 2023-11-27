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
		defineAsRequiredField("offsetGroupCd");
		defineAsRequiredField("offsetGroupName");
		defineAsRequiredField("venderCdSaki");

		<%-- 前後空白除去定義 --%>
		if ($('insertFlg').value == '1'){
        	defineAsKeyField("offsetGroupCd");
       	};

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');
	}, false);

	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
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

	<%-- 追加・削除ボタン --%>
	function move(type){
		if (type == "LEFT") {
			moto = document.forms[0].venderCdSaki;
			saki = document.forms[0].venderCdMoto;
		} else {
			moto = document.forms[0].venderCdMoto;
			saki = document.forms[0].venderCdSaki;			
		}
		
		<%-- 選択したアイテムを移動して元より削除 --%>
	    while (moto.selectedIndex >= 0) { 
	    	var newOption = new Option(); 
	   		newOption.text = moto.options[moto.selectedIndex].text; 
	   		newOption.value = moto.options[moto.selectedIndex].value; 
	   		saki.options[saki.length] = newOption;
			moto.remove(moto.selectedIndex); 
  		} 

		<%-- 先頭に選択を移動 --%>
		moto.selectedIndex=0;
	}

	<%-- コンボの選択をはずす --%>
	function notSel(type){
		if (type == "LEFT") {
			saki = document.forms[0].venderCdSaki;
		} else {
			saki = document.forms[0].venderCdMoto;			
		}
		
		<%-- 反対側の選択を非選択に変える --%>
		saki.selectedIndex=-1;
	}

	<%-- コンボの中身を全選択 --%>
	function submitex(){
		listallselect(document.forms[0].venderCdSaki);
		listallselect(document.forms[0].venderCdMoto);
	}

	<%-- コンボのlistを全選択状態に変更する --%>
	function listallselect(listtmp){
		if (listtmp != null) {
			for (i=0; i<listtmp.length; i++) {
				listtmp.options[i].selected = true;
			}
		}
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/OffsetGroupDetail" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="insertFlg" styleId="insertFlg" />

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
									<td class="fcTitle fb">相殺グループマスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="150">相殺グループコード</td>
									<td colspan="3">
										<nested:equal property="insertFlg" value="1">
											<nested:text property="offsetGroupCd" maxlength="10" size="10" style="ime-mode:disabled" styleId="offsetGroupCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:equal property="insertFlg" value="0">
											<nested:write property="offsetGroupCd"/>
								</nested:equal>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="4"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="4"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">相殺グループ名称</td>
									<td colspan="3">
										<nested:text property="offsetGroupName" maxlength="30" size="55" styleId="offsetGroupName" onchange="setDirtyFlg();" />
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">請求先<br>支払先名称</td>
									<td>
										<nested:text property="venderName" readonly="false" size="20%" styleId="venderName"/>
									</td>

									<td colspan="2">
										<div id="cssButton">
											<a href="#" onclick="submitex(); return form_submit('op', 'search'); return false;" class="cssButton">
												&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td></td>
									<td class="fcTitleDetail fb">選択元</td>

									<td></td>
									<td class="fcTitleDetail fb">選択先</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" rowspan="8" height="360">請求先<br>　／<br>支払先</td>
									<td rowspan="9">
										<nested:select property="venderCdMoto" multiple="true" styleId="venderCdMoto" style="width:250px;height:360px;" onclick="return notSel('LEFT');" >
											<nested:optionsCollection name="offsetGroupDetailForm" property="venderMotoList" value="values" label="labales"/>
										</nested:select>
									</td>

									<td>&nbsp;&nbsp;</td>
									<td rowspan="9">
										<nested:select property="venderCdSaki" multiple="true" styleId="venderCdSaki" style="width:250px;height:360px;" onclick="return notSel('RIGHT');" >
											<nested:optionsCollection name="offsetGroupDetailForm" property="venderSakiList" value="values" label="labales"/>
										</nested:select>
									</td>
								</tr>

								<tr><td>&nbsp;&nbsp;</td></tr>
								<tr><td>&nbsp;&nbsp;</td></tr>

								<tr>
									<td>
										<button onclick="setDirtyFlg(); return move('RIGHT');" class="cssButton">追加→</button>
									</td>
								</tr>

								<tr>
									<td>
										<button onclick="setDirtyFlg(); return move('LEFT');" class="cssButton">←削除</button>
									</td>
								</tr>

								<tr><td>&nbsp;&nbsp;</td></tr>
								<tr><td>&nbsp;&nbsp;</td></tr>
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
										<nested:equal property="insertFlg" value="0">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!putConfirm()){return false;}else{submitex(); return form_submit('op', 'update'); return false;}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
	
										<nested:equal property="insertFlg" value="1">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!putConfirm()){return false;}else{submitex(); return form_submit('op', 'insert'); return false;}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>

										<td width="50">
											<br>
										</td>
									</nested:equal>

									<nested:equal property="insertFlg" value="0">
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

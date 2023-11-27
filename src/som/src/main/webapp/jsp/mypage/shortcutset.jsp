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

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		refreshLabel();
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
		if(type == "LEFT"){
			moto = document.forms[0].menuIdSaki;
			saki = document.forms[0].menuIdMoto;
		}else{
			moto = document.forms[0].menuIdMoto;
			saki = document.forms[0].menuIdSaki;			
		}
		
		/* 選択したアイテムを移動して元より削除 */
		while ( moto.selectedIndex >= 0 ) { 
			var newOption = new Option(); 
	   		newOption.text = moto.options[moto.selectedIndex].text; 
	   		newOption.value = moto.options[moto.selectedIndex].value; 
	   		saki.options[saki.length] = newOption;
			moto.remove(moto.selectedIndex); 
  		} 

		/* listの中身をソートする */
  		listsort(moto);
		listsort(saki);
		 
		/* 先頭に選択を移動 */
		moto.selectedIndex=0;
	}

	<%-- コンボの選択をはずす --%>
	function notSel(type){
		if(type=="LEFT"){
			saki = document.forms[0].menuIdSaki;
		}else{
			saki = document.forms[0].menuIdMoto;			
		}
		
		/* 反対側の選択を非選択に変える */
		saki.selectedIndex=-1;

	}

	<%-- コンボのlistの中身をソートする --%>
	function listsort(listtmp){
		for (i=0; i<listtmp.length-1; i++){
			for (j=i+1; j<listtmp.length; j++){
				if (listtmp.options[j].value < listtmp.options[i].value){
					tmp  = listtmp.options[j].value;
					listtmp.options[j].value = listtmp.options[i].value;
					listtmp.options[i].value = tmp;
					tmp2 = listtmp.options[j].text;
					listtmp.options[j].text  = listtmp.options[i].text;
					listtmp.options[i].text  = tmp2;
				}
			}
		}
	}

	<%-- コンボの中身を全選択 --%>
	function submitex(){
		listallselect(document.forms[0].menuIdSaki);
		listallselect(document.forms[0].menuIdMoto);
	}

	<%-- コンボのlistを全選択状態に変更する --%>
	function listallselect(listtmp){
		if (listtmp != null) {
			for (i=0; i<listtmp.length; i++){
				listtmp.options[i].selected = true;
			}
		}
	}
</script>
<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/ShortCutSet" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="tantoCd" styleId="tantoCd"/>

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
									<td class="fcTitle fb">ショートカット設定</td>
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
							<table cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td></td>

									<td class="fcTitleDetail fb">
										選択元
									</td>

									<td></td>

									<td class="fcTitleDetail fb">
										選択先
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" rowspan="2">メニュー</td>
									<td rowspan="2">
										<nested:select property="menuIdMoto" multiple="true" size="10" styleId="menuIdMoto" style="width:210px;" onclick="return notSel('LEFT');" >
											<nested:optionsCollection name="shortCutSetForm" property="menuMoto" value="values" label="labales" />
										</nested:select>
									</td>

									<td>
										<button onclick="setDirtyFlg(); return move('RIGHT');" class="cssButton">追加→</button>
									</td>

									<td rowspan="2">
										<nested:select property="menuIdSaki" multiple="true" size="10" styleId="menuIdSaki" style="width:210px;" onclick="return notSel('RIGHT');" >
											<nested:optionsCollection name="shortCutSetForm" property="menu" value="values" label="labales" />
										</nested:select>
									</td>
								</tr>

								<tr>
									<td>
										<button onclick="setDirtyFlg(); return move('LEFT');" class="cssButton">←削除</button>
									</td>
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
											<a href="#" onclick="if(!putConfirm()){return false;}else{submitex(); return form_submit('op', 'update'); return false;}" class="cssButton">
												&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
											</a>
										</div>
									</td>

									<td width="50">
										<br>
									</td>

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


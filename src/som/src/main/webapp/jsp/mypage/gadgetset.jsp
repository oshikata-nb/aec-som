<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>' media="screen" rel="Stylesheet" type="text/css" />

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

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
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

	<%-- レーン移動ボタン(←→) --%>
	function move(type){
		if(type=="LEFT"){
			moto = document.forms[0].gadgetId2;
			saki = document.forms[0].gadgetId1;
		}else{
			moto = document.forms[0].gadgetId1;
			saki = document.forms[0].gadgetId2;			
		}
		
		/* 選択したアイテムを移動して元より削除 */
		while ( moto.selectedIndex >= 0 ) { 
			var newOption = new Option(); 
	   		newOption.text = moto.options[moto.selectedIndex].text; 
	   		newOption.value = moto.options[moto.selectedIndex].value; 
	   		saki.options[saki.length] = newOption;
			moto.remove(moto.selectedIndex); 
  		} 
		 
		/* 先頭に選択を移動 */
		moto.selectedIndex=0;
		saki.selectedIndex=-1;
	}

	<%-- 表示順移動ボタン(↑↓) --%>
	function upDown(type, lane) {
		if (lane == 0) {
			moto = document.forms[0].gadgetId1;
		} else {
			moto = document.forms[0].gadgetId2;
		}

		if (moto.selectedIndex == null) {
			return;
		}

		/* 選択したインデックス保持 */
		var selIdx = new Array();
		var sel = 0;

		/* 選択したアイテムを移動 */
		var newCombo = new Array();
		var selFlg = "0";
		if (type == "UP") {
			/* 上へ移動 */
			for (var i = 0; i < moto.length; i++) {
				var newOption = new Option();
		   		newOption.text = moto.options[i].text;
		   		newOption.value = moto.options[i].value;
	
				if (moto.options[i].selected == true) {
					/* 選択したインデックス保持 */
					selIdx[sel] = i;
					sel = sel + 1;

					if (i - 1 < 0 || selFlg == "1") {
						/* 上へ移動不可 または 連続して選択項目の場合 */
				   		newCombo[i] = newOption;
				   		selFlg = "1";
					} else {
						var temp = newCombo[i - 1];
				   		newCombo[i - 1] = newOption;
				   		newCombo[i] = temp;
				   		selFlg = "0";
					}
				} else {
			   		newCombo[i] = newOption;
			   		selFlg = "0";
				}
			}

			/* 移動後のコンボボックスを再設定 */
			if (newCombo.length > 0) {
				for (var i = 0; i < moto.length; i++) {
					moto.options[i] = newCombo[i];
				}
			}
		} else {
			/* 下へ移動 */
			var index = 0;

			for (var i = moto.length; i > 0; i--) {
				var newOption = new Option();
		   		newOption.text = moto.options[i - 1].text;
		   		newOption.value = moto.options[i - 1].value;
	
				if (moto.options[i - 1].selected == true) {
					/* 選択したインデックス保持 */
					selIdx[sel] = index;
					sel = sel + 1;

					if (index - 1 < 0 || selFlg == "1") {
						/* 下へ移動不可 または 連続して選択項目の場合 */
				   		newCombo[index] = newOption;
				   		selFlg = "1";
					} else {
						var temp = newCombo[index - 1];
				   		newCombo[index - 1] = newOption;
				   		newCombo[index] = temp;
				   		selFlg = "0";
					}
				} else {
			   		newCombo[index] = newOption;
			   		selFlg = "0";
				}

				index = index + 1;
			}

			/* 移動後のコンボボックスを再設定 */
			if (newCombo.length > 0) {
				var idx = moto.length - 1;

				for (var i = 0; i < moto.length; i++) {
					moto.options[i] = newCombo[idx];
					idx = idx - 1;
				}
			}
		}

		/* 選択状態を設定 */
		var idx;

		for (var i = 0; i < selIdx.length; i++) {
			if (type == "UP") {
				var num = selIdx[i] - 1;
				if (num < 0) {
					/* 先頭の項目を選択された場合 */
					idx = 0;
				} else {
					if (num == idx) {
						/* 先頭の項目を含め連続して選択された場合 */
						idx = num + 1;
					} else {
						idx = num;
					}
				}
			} else {
				var num = moto.length - selIdx[i];

				if (num == moto.length) {
					/* 最後の項目を選択された場合 */
					idx = moto.length - 1;
				} else {
					if (num == idx) {
						/* 最後の項目を含め連続して選択された場合 */
						idx = num - 1;
					} else {
						idx = num;
					}
				}
			}

			moto.options[idx].selected = true;
		}
	}

	<%-- 追加・削除ボタン --%>
	function addDel(type, lane){
		if(type=="DEL"){
			if(lane == 0){
				moto = document.forms[0].gadgetId1;
			}else{
				moto = document.forms[0].gadgetId2;
			}
			saki = document.forms[0].gadgetIdMoto;
		}else{
			moto = document.forms[0].gadgetIdMoto;
			if(lane == 0){
				saki = document.forms[0].gadgetId1;
			}else{
				saki = document.forms[0].gadgetId2;
			}
		}
		
		/* 選択したアイテムを移動して元より削除 */
		while ( moto.selectedIndex >= 0 ) { 
			var newOption = new Option(); 
	   		newOption.text = moto.options[moto.selectedIndex].text; 
	   		newOption.value = moto.options[moto.selectedIndex].value; 
	   		saki.options[saki.length] = newOption;
			moto.remove(moto.selectedIndex); 
  		} 
		 
		/* 先頭に選択を移動 */
		moto.selectedIndex=0;
		saki.selectedIndex=-1;
	}

	<%-- コンボの選択をはずす --%>
	function notSel(type) {
		/* 自分以外の選択を非選択に変える */
		if (type=="LANE1") {
			document.forms[0].gadgetId2.selectedIndex = -1;
			document.forms[0].gadgetIdMoto.selectedIndex = -1;
		}else if (type=="LANE2"){
			document.forms[0].gadgetId1.selectedIndex = -1;
			document.forms[0].gadgetIdMoto.selectedIndex = -1;
		}else{
			document.forms[0].gadgetId1.selectedIndex = -1;
			document.forms[0].gadgetId2.selectedIndex = -1;
		}
	}

	<%-- コンボの中身を全選択 --%>
	function submitex(){
		listallselect(document.forms[0].gadgetId1);
		listallselect(document.forms[0].gadgetId2);
		listallselect(document.forms[0].gadgetIdMoto);
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
<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/GadgetSet" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>

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
									<td class="fcTitle fb">ガジェット設定</td>
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
									<td class="fcTitleDetail fb" colspan="2">１列目</td>
									<td></td>
									<td class="fcTitleDetail fb" colspan="2">２列目</td>
									<td></td>
								</tr>

								<tr>
									<td>
										<button onclick="setDirtyFlg(); return upDown('UP',0);" class="cssButton">↑</button>
									</td>

									<td rowspan="2" colspan="2">
										<nested:select property="gadgetId1" multiple="true" size="10" styleId="gadgetId1" style="width:210px;" onclick="return notSel('LANE1');" >
											<nested:optionsCollection name="gadgetSetForm" property="gadget1" value="values" label="labales" />
										</nested:select>
									</td>

									<td>
										<button onclick="setDirtyFlg(); return move('RIGHT');" class="cssButton">→</button>
									</td>

									<td rowspan="2" colspan="2">
										<nested:select property="gadgetId2" multiple="true" size="10" styleId="gadgetId2" style="width:210px;" onclick="return notSel('LANE2');" >
											<nested:optionsCollection name="gadgetSetForm" property="gadget2" value="values" label="labales" />
										</nested:select>
									</td>

									<td>
										<button onclick="setDirtyFlg(); return upDown('UP',1);" class="cssButton">↑</button>
									</td>
								</tr>

								<tr>
									<td>
										<button onclick="setDirtyFlg(); return upDown('DOWN',0);" class="cssButton">↓</button>
									</td>

									<td>
										<button onclick="setDirtyFlg(); return move('LEFT');" class="cssButton">←</button>
									</td>

									<td>
										<button onclick="setDirtyFlg(); return upDown('DOWN',1);" class="cssButton">↓</button>
									</td>
								</tr>

								<tr>
									<td height="5"></td>
								</tr>

								<tr>
									<td></td>

									<td align="center">
										<button onclick="setDirtyFlg(); return addDel('ADD',0);" class="cssButton">↑</button>
									</td>

									<td align="center">
										<button onclick="setDirtyFlg(); return addDel('DEL',0);" class="cssButton">↓</button>
									</td>

									<td></td>

									<td align="center">
										<button onclick="setDirtyFlg(); return addDel('ADD',1);" class="cssButton">↑</button>
									</td>

									<td align="center">
										<button onclick="setDirtyFlg(); return addDel('DEL',2);" class="cssButton">↓</button>
									</td>

									<td></td>
								</tr>

								<tr>
									<td height="5"></td>
								</tr>

								<tr>
									<td></td>
									<td colspan="5" class="fcTitleDetail fb">選択可能ガジェット</td>
									<td></td>
								</tr>

								<tr>
									<td></td>

									<td colspan="5">
										<nested:select property="gadgetIdMoto" multiple="true" size="10" style="width:465px;" onclick="return notSel('MOTO');" >
											<nested:optionsCollection name="gadgetSetForm" property="gadgetMoto" value="values" label="labales" />
										</nested:select>
									</td>

									<td></td>
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

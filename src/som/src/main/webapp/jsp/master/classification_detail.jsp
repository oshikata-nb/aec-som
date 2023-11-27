<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@page import="com.asahikaseieng.app.comboboxes.SelectAdminFlg"%>
<%@ page import="com.asahikaseieng.Constants"%>
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

<%-- ▼業務固有css ここから --%>
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- コンボボックスの連動 --%>
<%--
	var dataTypeText   = new Array("1：売上", "2：入金", "3：仕入", "4：支払","5：グループ間相殺");
	var dataTypeValue  = new Array("1","2","3","4","5");
	var dataTotalDivisionText = new Array();
	var dataTotalDivisionValue = new Array();
	dataTotalDivisionText[0] = new Array("1：売上", "2：返品", "3：値引", "9：その他");
	dataTotalDivisionText[1] = new Array("1：入金", "2：相殺", "9:その他");
	dataTotalDivisionText[2] = new Array("1：仕入", "2：返品", "3：値引", "9：その他");
	dataTotalDivisionText[3] = new Array("1：支払", "2：相殺", "9：その他");
	dataTotalDivisionText[4] = new Array("1：相殺", "9：その他");
	dataTotalDivisionValue[0] = new Array("1", "2", "3", "9");
	dataTotalDivisionValue[1] = new Array("1", "2", "9");
	dataTotalDivisionValue[2] = new Array("1", "2", "3", "9");
	dataTotalDivisionValue[3] = new Array("1", "2", "9");
	dataTotalDivisionValue[4] = new Array("1", "9");
	function funcMain(b) {
	    if (document.mainForm.strDataType.selectedIndex == 0) {
	        document.mainForm.strDataTotalDivision.length = 1;
	        document.mainForm.strDataTotalDivision.selectedIndex = 0;
	    } else {
	        if (b) {
	            document.mainForm.strDataTotalDivision.length = 1;
	            document.mainForm.strDataTotalDivision.selectedIndex = 0;
	        }
	        var SecondTextArray  = dataTotalDivisionText[document.mainForm.strDataType.selectedIndex - 1];
	        var SecondValueArray = dataTotalDivisionValue[document.mainForm.strDataType.selectedIndex - 1];
	        document.mainForm.strDataTotalDivision.length = SecondValueArray.length + 1;
	        for (var i = 0; i < SecondValueArray.length; i++) {
	            document.mainForm.strDataTotalDivision.options[i + 1].value = SecondValueArray[i];
	            document.mainForm.strDataTotalDivision.options[i + 1].text  = SecondTextArray[i];
	        }
	    }
	}
--%>	

	<%-- データ種別コンボボックスの連動 --%>
	function funcMain(b) {
		setDirtyFlg();
		form_submit('op', 'changeDataType');
		return false;
	}
	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
		defineAsRequiredField("strDataType");
		defineAsRequiredField("strDataTotalDivision");
		defineAsRequiredField("categoryDivision");
		defineAsRequiredField("categoryName");
		defineAsRequiredField("externalCategoryName");
		defineAsRequiredField("debitAccountsCd");
		defineAsRequiredField("creditAccountsCd");

		var flg = $("insertFlg").value;
		if (flg == "1") {
			defineAsRequiredField("password");
		}
		<%-- 日付型フィールド定義 --%>
		<%-- 数値型フィールド定義 --%>
		<%-- 担当者のauto complete --%>	    
		<%-- 検索後入力された場合の不整合をただす --%>


		storeInitValues(/^srh.*/, 'dirtyFlg');
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
		$("dirtyFlg").value = "true";
	}
	
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}

	<%-- 確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";

		return confirm(alertMsg[0]);
	}

	<%-- 追加・削除ボタン --%>
<%--	function move(type){
		
		if(type=="LEFT"){
			moto = document.forms[0].roleId;
			saki = document.forms[0].roleIdMoto;
		}else{
			moto = document.forms[0].roleIdMoto;
			saki = document.forms[0].roleId;			
		}
		
		//選択したアイテムを移動して元より削除
	    while ( moto.selectedIndex >= 0 ) { 
	    	var newOption = new Option(); 
	   		newOption.text = moto.options[moto.selectedIndex].text; 
	   		newOption.value = moto.options[moto.selectedIndex].value; 
	   		saki.options[saki.length] = newOption;
			moto.remove(moto.selectedIndex); 
  		} 
		//listの中身をソートする
  		listsort(moto);
		listsort(saki);
		 
		//先頭に選択を移動
		moto.selectedIndex=0;
	}
--%>
</script>

<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/ClassificationDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="insertFlg" styleId="insertFlg" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">分類マスタ</td>
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
							<td height="5" colspan="2"></td>
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
				<tr>
					<td><!-- 明細部 -->
					<table width="" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">データ種別</td>
							<td colspan="3">
								<nested:equal property="insertFlg" value="0">
									<nested:write property="textDataType"/>
									<nested:hidden property="strDataType"></nested:hidden>
								</nested:equal>
								<nested:equal property="insertFlg" value="1">
									<nested:select property="strDataType" onchange="funcMain(true);" size="1" styleId="strDataType">
										<html:option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:option>
										<html:option value="1">1：売上</html:option>
										<html:option value="2">2：入金</html:option>
										<html:option value="3">3：仕入</html:option>
										<html:option value="4">4：支払</html:option>
										<html:option value="5">5：グループ間相殺</html:option>
									</nested:select>
								</nested:equal>
							</td>
						</tr>
						<tr>
							<td class="fcTitleDetail fb bcTitleDetail">データ集計区分</td>
							<td colspan="3">
								<nested:equal property="insertFlg" value="0">
									<nested:write property="textDataTotalDivision"/>
								</nested:equal>
								<nested:equal property="insertFlg" value="1">
									<nested:select property="strDataTotalDivision" styleId="strDataTotalDivision">
										<html:option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:option>
										<nested:optionsCollection name="classificationDetailForm" property="totalDivisionList" value="values" label="labales"/>
									</nested:select>
<%--
									<nested:select property="strDataTotalDivision" size="1" styleId="strDataTotalDivision">
										<html:option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:option>
										<html:option value=""></html:option>
										<html:option value=""></html:option>
										<html:option value=""></html:option>
										<html:option value=""></html:option>
										<html:option value=""></html:option>
									</nested:select>
--%>									
								</nested:equal>
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">分類コード</td>
							<td colspan="3">
								<nested:equal property="insertFlg" value="0">
									<nested:write property="categoryDivision"/>
								</nested:equal>
								<nested:equal property="insertFlg" value="1">
									<nested:text property="categoryDivision" styleId="categoryDivision" maxlength="3" style="ime-mode:disabled" />
								</nested:equal>
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">分類名称</td>
							<td colspan="3">
								<nested:text property="categoryName" size="50" styleId="categoryDivision" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">対外分類名称</td>
							<td colspan="3">
								<nested:text property="externalCategoryName" size="50" styleId="externalCategoryName" onchange="setDirtyFlg();" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">借方勘定科目</td>
							<td>
								<nested:select property="debitAccountsCd" onchange="setDirtyFlg();return form_submit('op', 'subdebit'); return false;">
									<nested:optionsCollection name="classificationDetailForm" property="debitAccountsCdList" value="values" label="labales"/>
								</nested:select>
							</td>
							<td class="bcTitleSearch fb fcTitleSearch">補助科目</td>
							<td>
								<nested:select property="debitSubAccountsCd" onchange="setDirtyFlg();">
									<nested:optionsCollection name="classificationDetailForm" property="debitSubAccountsCdList" value="values" label="labales"/>
								</nested:select>
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">貸方勘定科目</td>
							<td>
								<nested:select property="creditAccountsCd" onchange="setDirtyFlg();return form_submit('op', 'subcredit'); return false;">
									<nested:optionsCollection name="classificationDetailForm" property="creditAccountsCdList" value="values" label="labales"/>
								</nested:select>
							</td>
							<td class="bcTitleSearch fb fcTitleSearch">補助科目</td>
							<td>
								<nested:select property="creditSubAccountsCd" onchange="setDirtyFlg();">
									<nested:optionsCollection name="classificationDetailForm" property="creditSubAccountsCdList" value="values" label="labales"/>
								</nested:select>
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">売掛対象区分</td>
							<td colspan="3">
								<nested:checkbox property="arDivision" onchange="setDirtyFlg();" value="<%= Constants.FLG_ON %>"/>対象
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">請求対象区分</td>
							<td colspan="3">
								<nested:checkbox property="claimDivision" onchange="setDirtyFlg();" value="<%= Constants.FLG_ON %>"/>対象
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">買掛対象区分</td>
							<td colspan="3">
								<nested:checkbox property="creditDivision" onchange="setDirtyFlg();" value="<%= Constants.FLG_ON %>"/>対象
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">支払対象区分</td>
							<td colspan="3">
								<nested:checkbox property="paymentDivision" onchange="setDirtyFlg();" value="<%= Constants.FLG_ON %>"/>対象
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">銀行必須区分</td>
							<td colspan="3">
								<nested:checkbox property="bankDivision" onchange="setDirtyFlg();" value="<%= Constants.FLG_ON %>"/>必須
							</td>
						</tr>
						<tr>
							<td class="bcTitleSearch fb fcTitleSearch">手形必須区分</td>
							<td colspan="3">
								<nested:checkbox property="noteDivision" onchange="setDirtyFlg();" value="<%= Constants.FLG_ON %>"/>必須

							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table>
						<tr>
							<td class="alignLeft">
								<div id="cssButton">
									<nested:equal property="insertFlg" value="0">
										<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
										</a>
									</nested:equal>
									<nested:equal property="insertFlg" value="1">
										<a href="#" onclick="if (!(putConfirm())) {return false;}else{return form_submit('op', 'insert'); return false;}" class="cssButton">
										&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
										</a>
									</nested:equal>
								</div>
							</td>
							<td class="alignCenter">
								<div id="cssButton">
									<nested:equal property="insertFlg" value="0">
										<a href="#" onclick="if (!(deleteAlert())) {return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
											&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
										</a>
									</nested:equal>
								</div>
							</td>
							<td class="alignRight">
								<div id="cssButton">
									<a href="#" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
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


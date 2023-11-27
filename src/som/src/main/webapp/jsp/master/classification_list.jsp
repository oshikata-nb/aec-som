<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">

	<%-- 詳細画面への遷移 --%>
	function toDetail(url) {
      if (!g_lock) {
        if (continueConfirm()) {
          location.href=url;
    	  g_lock = true;
	    }
      }
	}

	<%-- コンボボックスの連動 --%>
	var srhDataTypeText   = new Array("1：売上", "2：入金", "3：仕入", "4：支払", "5：グループ間相殺");
	var srhDataTypeValue  = new Array("1", "2", "3", "4", "5");
	var srhDataTotalDivisionText = new Array();
	var srhDataTotalDivisionValue = new Array();
	srhDataTotalDivisionText[0] = new Array("1：売上", "2：返品", "3：値引", "9：その他");
	srhDataTotalDivisionText[1] = new Array("1：入金", "2：相殺", "9：その他");
	srhDataTotalDivisionText[2] = new Array("1：仕入", "2：返品", "3：値引", "9：その他");
	srhDataTotalDivisionText[3] = new Array("1：支払", "2：相殺", "9：その他");
	srhDataTotalDivisionText[4] = new Array("1：相殺", "9：その他");
	srhDataTotalDivisionValue[0] = new Array("1", "2", "3", "9");
	srhDataTotalDivisionValue[1] = new Array("1", "2", "9");
	srhDataTotalDivisionValue[2] = new Array("1", "2", "3", "9");
	srhDataTotalDivisionValue[3] = new Array("1", "2", "9");
	srhDataTotalDivisionValue[4] = new Array("1", "9");
	function funcMain(b) {
	    if (document.mainForm.srhDataType.selectedIndex == 0) {
	        document.mainForm.srhDataTotalDivision.length = 1;
	        document.mainForm.srhDataTotalDivision.selectedIndex = 0;
	    } else {
	        if (b) {
	            document.mainForm.srhDataTotalDivision.length = 1;
	            document.mainForm.srhDataTotalDivision.selectedIndex = 0;
	        }
	        var SecondTextArray  = srhDataTotalDivisionText[document.mainForm.srhDataType.selectedIndex - 1];
	        var SecondValueArray = srhDataTotalDivisionValue[document.mainForm.srhDataType.selectedIndex - 1];
	        document.mainForm.srhDataTotalDivision.length = SecondValueArray.length + 1;
	        for (var i = 0; i < SecondValueArray.length; i++) {
	            document.mainForm.srhDataTotalDivision.options[i + 1].value = SecondValueArray[i];
	            document.mainForm.srhDataTotalDivision.options[i + 1].text  = SecondTextArray[i];
	        }
	    }
	}

	<%-- オートコンプリートの値の入替え --%>
	var auto;
	function makeAutoParams() {
		auto.options.defaultParams ='srhDataType='+document.getElementById('srhDataType').value
									+'&srhDataTotalDivision='+document.getElementById('srhDataTotalDivision').value;
	}
	
	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		<logic:equal name="classificationListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 必須フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>


		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 数値型フィールド定義 --%>
			<%-- 必須フィールド定義 --%>
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
		}

		<%-- コンボボックスの初期化 --%>
		funcMain(false);

		var temDataType = $('srhDataType').value;
		var temDataTotalDivision = $('hidennDataTotalDivision').value;
		if (temDataTotalDivision != "") {
			if (temDataTotalDivision != "9") {
				document.mainForm.srhDataTotalDivision.options[temDataTotalDivision].selected = true;
			} else {
				if (temDataType == "1") {
					document.mainForm.srhDataTotalDivision.options[4].selected = true;
				} else if (temDataType == "2") {
					document.mainForm.srhDataTotalDivision.options[3].selected = true;
				} else if (temDataType == "3") {
					document.mainForm.srhDataTotalDivision.options[4].selected = true;
				} else if (temDataType == "4") {
					document.mainForm.srhDataTotalDivision.options[3].selected = true;
				} else if (temDataType == "5") {
					document.mainForm.srhDataTotalDivision.options[2].selected = true;
				}
			}
		}

		<%-- 分類のauto complete --%>
		auto = new Ajax.Autocompleter(
		  "srhCategoryDivision",
		  "autocomplete_classification",
		  "<%= request.getContextPath() %>/AutoComplete.do?op=autoClassification",
		  {
		    parameters : 'srhDataType='+document.getElementById('srhDataType').value
		    			+'&srhDataTotalDivision='+document.getElementById('srhDataTotalDivision').value,
		    afterUpdateElement : setCategoryLabel
		  }
	    );

		<%-- 検索後入力された場合の不整合をただす --%>
		storeInitValues(/^srh.*/, 'dirtyFlg');
		refreshLabel();

	}, false);

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/ClassificationList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />

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
					<td><!-- 検索部 -->
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">データ種別</td>
									<td>
										<nested:select property="srhDataType" onchange="funcMain(true);" size="1" styleId="srhDataType">
											<html:option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:option>
											<html:option value="1">1：売上</html:option>
											<html:option value="2">2：入金</html:option>
											<html:option value="3">3：仕入</html:option>
											<html:option value="4">4：支払</html:option>
											<html:option value="5">5：グループ間相殺</html:option>
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">分類コード</td>
									<td>
										<nested:text property="srhCategoryDivision" maxlength="3" size="8" styleId="srhCategoryDivision" onfocus="makeAutoParams();"/>
										<div id="autocomplete_classification" style="display:none;" class="autocomplete"></div>
										<nested:text property="srhCategoryName" maxlength="30" size="40" styleId="srhCategoryName" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">データ集計区分</td>
									<td>
										<nested:hidden property="hidennDataTotalDivision"></nested:hidden>
										<nested:select property="srhDataTotalDivision" size="1" styleId="srhDataTotalDivision">
											<html:option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:option>
											<html:option value=""></html:option>
											<html:option value=""></html:option>
											<html:option value=""></html:option>
											<html:option value=""></html:option>
											<html:option value=""></html:option>
										</nested:select>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="alignRight">
							<table cellspacing="1" cellpadding="1" border="0">
								<tr>
									<td>
										<div id="cssButton"><a href="#"
											onclick="return form_submit('op', 'search'); return false;"
											class="cssButton"> &nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
										</a></div>
									</td>
									<td>
									<td>
										<div id="cssButton">
										<a href='<%= request.getContextPath() + "/ClassificationDetail.do?op=initNew"%>'
											class="cssButton"> &nbsp;&nbsp;新&nbsp;&nbsp;規&nbsp;&nbsp;
										</a></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<tr height="5">
							<td></td>
						</tr>

						<tr>
							<td class="bcTitleLine"></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>

			<nested:notEmpty property="searchList">

				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><!-- 明細部 -->
						<table width="" border="0">
							<tr>
								<td class="alignCenter">
								<table cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td width="100">データ種別</td>
										<td width="100">分類コード</td>
										<td width="130">データ集計区分</td>
										<td width="200">分類名称</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
			
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
			
										<nested:hidden property="dataType"></nested:hidden>
										<nested:hidden property="dataTotalDivision"></nested:hidden>
										<nested:define id="dtp" property="dataType" />
										<nested:define id="dtd" property="dataTotalDivision" />
										<nested:define id="ctd" property="categoryDivision" />

										<%-- データ種別 --%>
										<td>
											<nested:write property="strDataType" />
										</td>
										<%-- 分類コード --%>
										<td>
											<html:link href="#"
												onclick='<%=
													"toDetail('" + request.getContextPath()
													+ "/ClassificationDetail.do?op=init&strDataType="
													+ pageContext.findAttribute("dtp").toString() 
													+ "&strDataTotalDivision="
													+ pageContext.findAttribute("dtd").toString() 
													+ "&categoryDivision="
													+ pageContext.findAttribute("ctd").toString() 
													+ "'); return false;"
													%>'>
												<nested:write property="categoryDivision" />
											</html:link>
										</td>
										<%-- データ集計区分 --%>
										<td>
											<nested:write property="strDataTotalDivision" />
										</td>			
										<%-- 分類名称 --%>
										<td>
											<nested:write property="categoryName" />
										</td>
									</nested:iterate>
								</table>
								</td>
							</tr>
						</table>
						</td>
					<tr>
						<td class="alignCenter">
							<%-- ページング --%>
							<%@ include file="/jsp/common/pager/pager.jsf"%>
							<%-- ページング ここまで --%>
						</td>
					</tr>
				</table>
			</nested:notEmpty>
	</table>
</nested:form>
</body>
</html:html>

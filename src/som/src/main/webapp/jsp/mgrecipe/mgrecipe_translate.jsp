<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%@ include file="/jsp/common/clearText.jsf" %>
<%@ include file="/jsp/common/searchNameForMaster.jsf" %>

<%-- ▼業務固有css ここから　--%>
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

	<%--onload時の処理--%>
	Event.observe(window, 'load', function() {

		<%-- フォーム設定の初期化 --%>
		<logic:equal name="mgrecipeListForm" property="searchList" value="false">
			initializeFormState();
		</logic:equal>

		<%-- 品目名称のauto complete --%>
		new Ajax.Autocompleter(
		  "product",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueue",
		  {
		  	paramName : "code",
		    afterUpdateElement : setItemQueueLabel
		  }
		);
		<%-- 他社コード１のauto complete --%>
		new Ajax.Autocompleter(
		  "otherCompanyCd1",
		  "autocomplete_selection",
		  "<%= request.getContextPath() %>/ItemQueueForAutoComplete.do?op=searchItemQueueOtherCompany",
		  {
		  	paramName : "code",
		    afterUpdateElement : setOtherCompany1Label
		  }
		);

		refreshLabel();

		Event.observe("product", 'keypress', clearDivLabel.bindAsEventListener($('itemName')), false);
		Event.observe("product", 'keypress', clearText.bindAsEventListener($('otherCompanyCd1')), false);
		Event.observe("product", 'keypress', clearText.bindAsEventListener($('itemNameHidden')), false);
		Event.observe("otherCompanyCd1", 'keypress', clearDivLabel.bindAsEventListener($('itemName')), false);
		Event.observe("otherCompanyCd1", 'itemNameHidden', clearText.bindAsEventListener($('itemNameHidden')), false);

	}, false);

	<%-- 変更フラグセット --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合

			return continueConfirm();
		}else{
			return true;
		}
	}
	<%-- 品目名称auto completeの選択後処理 --%>
	function setItemQueueLabel(text, li) {
	    $("itemName").update(li.getElementsByTagName('span')[0].innerText);
	    $("itemNameHidden").value = li.getElementsByTagName('span')[0].innerText;
	    $("otherCompanyCd1").value = getHiddenValue(li,"otherCompanyCd1");
	}
	<%-- 他社コード１auto completeの選択後処理 --%>
	function setOtherCompany1Label(text, li) {
	    $("itemName").update(li.getElementsByTagName('span')[0].innerText);
	    $("itemNameHidden").value = li.getElementsByTagName('span')[0].innerText;
	    $("product").value = getHiddenValue(li,"code");
	}

</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/MgrecipeTranslateList" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />

	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="750">
				<tr>
					<td><%-- ヘッダー部 --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">翻訳検索</td>
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
							<td height="5" colspan="2" ></td>
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
					<td><%-- 検索部 --%>
					<table border="0" cellspacing="" cellpadding="" width="100%">
						<tr>
							<td>
							<table border="0" cellspacing="2" cellpadding="1">
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">原処方ｺｰﾄﾞ</td>
									<td>
										<nested:text property="recipeCd" maxlength="20" size="20" styleId="recipeCd" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">バージョン</td>
									<td>
										<nested:text property="recipeVersion" maxlength="4" size="20" styleId="recipeVersion" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">品目コード</td>
									<td>
										<nested:text property="product" maxlength="20" size="20" styleId="product"/>
										<div id="autocomplete_selection" class="autocomplete"></div>
									</td>
									<td colspan="2">
										<div id="itemName">
											<nested:write property="itemName" />
										</div>
										<nested:hidden property="itemName" styleId="itemNameHidden" />
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">他社コード１</td>
									<td>
										<nested:text property="otherCompanyCd1" maxlength="10" size="20" styleId="otherCompanyCd1" />
									</td>
									<td class="bcTitleSearch fb fcTitleSearch">用途</td>
									<td>
										<%
											pageContext.setAttribute( "selectRecipeUse",new com.asahikaseieng.app.comboboxes.SelectRecipeUse(false, false, true));
										%>
										<nested:select property="recipeUse" >
											<nested:options name="selectRecipeUse" property="values" labelName="selectRecipeUse" labelProperty="labels" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="bcTitleSearch fb fcTitleSearch">ステータス</td>
									<td>
										<nested:select property="recipeStatus" style="margin: 0;padding: 0" styleId="productionLine" >
											<nested:optionsCollection property="statusCombo" label="labales" value="values" />
										</nested:select>
									</td>
								</tr>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">最新バージョン</td>
									<td>
										<nested:checkbox property="latestVersion"/>最新バージョンを検索
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">展開済</td>
									<td colspan="3">
										<nested:checkbox property="explicate"/>基本処方へ展開済していない処方
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
										<div id="cssButton">
										<a href='<%= request.getContextPath() + "/MgrecipeTranslateList.do?op=clear"%>'
											class="cssButton"> &nbsp;ク&nbsp;リ&nbsp;ア&nbsp;
										</a></div>
									</td>
									<td>
										<div id="cssButton"><a href="#"
											onclick="return check_form_submit('op', 'search'); return false;"
											class="cssButton"> &nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
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
						<td><%-- 明細部 --%>
						<table width="100%" border="0">
							<tr>
								<td class="alignCenter">
								<table width="100%" cellspacing="2" cellpadding="1" id="tblList">
									<tr class="bcTitleList fb fcTitleList">
										<td></td>
										<td>原処方ｺｰﾄﾞ</td>
										<td>Ｖ</td>
										<td>品目名称</td>
										<td>ステータス</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList1">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>

										<%-- 選択ボタン --%>
										<td width="10">
											<div id="cssButton">
												<nested:define id="oid" property="recipeId" />
												<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() + "/MgrecipeHeader.do?op=recipeNew&recipeId=" + pageContext.findAttribute("oid").toString() + "'); return false;"%>' styleClass="cssButton">
													選
												</html:link>
											</div>
										</td>
										<%-- 原処方ｺｰﾄﾞ --%>
										<td width="150">
											<nested:write property="recipeCd" />
										</td>
										<%-- バージョン --%>
										<td width="25">
												<nested:write property="recipeVersion" />
										</td>
										<%-- 品目 --%>
										<td width="250">
												<nested:write property="itemName" />
										</td>
										<%-- ステータス --%>
										<td>
												<nested:write property="statusName" />
										</td>
									</tr>
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
					<tr>
						<td>
						</td>
					</tr>
				</table>
			</nested:notEmpty>
			<tr>
				<td align="center">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td colspan="2" height="2"></td>
						</tr>
						<tr>
							<td class="alignright">
								<div id="cssButton">
									<a href="javascript:void();" class="cssButton" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'cancel'); return false;}">
										&nbsp;キャンセル&nbsp;
									</a>
								</div>
							</td>
							<td width="50"><br></td>
							<td class="alignleft">
								<div id="cssButton">
									<a href="javascript:void();" class="cssButton" onclick="if (!(putDirtyConfirm())) {return false;}else{return form_submit('op', 'cleanNew'); return false;}">
										&nbsp;空処方作成&nbsp;
									</a>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<script>
	<%-- 変更確認 --%>
	function dirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			<%-- 画面の編集内容は破棄されます、よろしいですか？ --%>
			var flag = confirm("<bean:message key="message.confirm.under.edit" />");
			if (!flag) {
				return false;
			}
			return true;
		}else{
			return true;
		}
	}
	<%--
		タブ押下時の遷移処理
	--%>
	function moveTab(actionName) {
		if (!g_lock) {
			if (dirtyConfirm()) {
				location.href="<%= request.getContextPath() %>/" + actionName + ".do?op=init&recipeId=" + $F("recipeId") + "&srhLink=" + $F("srhLink");
				g_lock = true;
			}
		}
		return false;
	}
</script>

<%-- タブ --%>
<span id="slidetabsmenu">
	<ul>
		<nested:equal property="tabId" value="Header">
			<li class="current"><a title="ヘッダー情報"><span>ヘッダー情報</span></a></li>
		</nested:equal>
		<nested:notEqual property="tabId" value="Header">
			<li><a href="#" title="ヘッダー情報" onclick="return moveTab('GrecipeHeader');"><span>ヘッダー情報</span></a></li>
		</nested:notEqual>
		<nested:equal property="tabId" value="ProcedureList">
			<li class="current"><a title="工程情報"><span>工程</span></a></li>
		</nested:equal>
		<nested:notEqual property="tabId" value="ProcedureList">
			<li><a href="#" title="工程情報" onclick="return moveTab('GrecipeProcedureList');"><span>工程</span></a></li>
		</nested:notEqual>
		<nested:equal property="tabId" value="FormulaList">
			<li class="current"><a title="配合情報"><span>配合</span></a></li>
		</nested:equal>
		<nested:notEqual property="tabId" value="FormulaList">
			<li><a href="#" title="配合情報" onclick="return moveTab('GrecipeFormulaList');"><span>配合</span></a></li>
		</nested:notEqual>
		<nested:equal property="tabId" value="InspectionList">
			<li class="current"><a title="検査情報"><span>検査</span></a></li>
		</nested:equal>
		<nested:notEqual property="tabId" value="InspectionList">
			<li><a href="#" title="検査情報" onclick="return moveTab('GrecipeInspectionList');"><span>検査</span></a></li>
		</nested:notEqual>
		<nested:equal property="tabId" value="FinishList">
			<li class="current"><a title="仕上情報"><span>仕上</span></a></li>
		</nested:equal>
		<nested:notEqual property="tabId" value="FinishList">
			<li><a href="#" title="仕上情報" onclick="return moveTab('GrecipeFinishList');"><span>仕上</span></a></li>
		</nested:notEqual>
		<nested:equal property="tabId" value="Specifics">
			<li class="current"><a title="詳細"><span>詳細</span></a></li>
		</nested:equal>
		<nested:notEqual property="tabId" value="Specifics">
			<li><a href="#" title="詳細" onclick="return moveTab('GrecipeSpecifics');"><span>詳細</span></a></li>
		</nested:notEqual>
		<nested:equal property="tabId" value="Other">
			<li class="current"><a title="その他"><span>その他</span></a></li>
		</nested:equal>
		<nested:notEqual property="tabId" value="Other">
			<li><a href="#" title="その他" onclick="return moveTab('GrecipeOther');"><span>その他</span></a></li>
		</nested:notEqual>
	</ul>
</span>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<%-- 共通フィールド --%>
<nested:hidden property="op" styleId="op" />
<nested:hidden property="dirtyFlg" />
<nested:hidden property="recipeId" styleId="recipeId" />
<nested:hidden property="recipeStatus" styleId="recipeStatus" />
<nested:hidden property="approvalStatus" styleId="approvalStatus" />
<script>
	<%--
		共通確認メッセージ表示関数
	--%>
	<%-- 変更確認 --%>
	function putDirtyConfirm() {
		var flg = $("dirtyFlg").value;
		if (flg == "true") {
			// 何か値が変更されている場合
			return continueConfirm();
		}else{
			return true;
		}
	}
	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").value = true;
	}
	<%-- 確認メッセージ --%>
	function putConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "登録してよろしいですか？";
		return confirm(alertMsg[0]);
	}
	<%-- 削除確認メッセージ --%>
	function deleteAlert() {
		alertMsg = new Array();
		alertMsg[0] = "削除してよろしいですか？";
		return confirm(alertMsg[0]);
	}
</script>
<%-- 共通項目部>>>>> --%>
<tr>
	<td>
		<table cellspacing="2" cellpadding="2" border="0" width="100%">
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="120">基本処方ｺｰﾄﾞ</td>
				<td width="150">
					<nested:write property="recipeCd" />
				</td>
				<td class="fcTitleDetail fb bcTitleDetail" width="120">バージョン</td>
				<td>
					<nested:write property="recipeVersion" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="120" >基本処方名称</td>
				<td>
					<nested:write property="recipeName" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="120">品目コード</td>
				<td width="155">
					<nested:write property="product" />
				</td>

				<td class="fcTitleDetail fb bcTitleDetail" width="120">品目名称</td>
				<td>
					<nested:write property="itemName" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="120">生産工場</td>
				<td width="155">
					<nested:write property="productionLineName" />
				</td>
				<td class="fcTitleDetail fb bcTitleDetail" width="120">用途</td>
				<td>
					<nested:write property="recipeUseName" />
				</td>
			</tr>
			<nested:equal property="recipeUse" value="4">
				<tr>
					<td class="fcTitleDetail fb bcTitleDetail" width="120">荷姿</td>
					<td width="155">
						<nested:write property="styleOfPacking" />
					</td>
					<td></td>
					<td></td>
				</tr>
			</nested:equal>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="120">承認ステータス</td>
				<td width="155">
					<nested:write property="approvalStatusName" />
				</td>
			</tr>
		</table>
	</td>
</tr>
<%-- 共通項目部<<<<< --%>
<%-- タブ --%>
<tr>
	<td>
	<table border="0" cellspacing="" cellpadding="" width="100%">
		<tr>
			<td>
				<%@ include file="/jsp/mgrecipe/mgrecipe_tab.jsp"%>
			</td>
		</tr>
	</table>
</tr>


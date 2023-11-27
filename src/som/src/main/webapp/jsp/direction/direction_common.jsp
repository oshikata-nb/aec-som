<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<%-- 共通フィールド --%>
<nested:hidden property="op" styleId="op" />
<nested:hidden property="dirtyFlg" />
<nested:hidden property="directionDivision" styleId="directionDivision" />
<nested:hidden property="directionNo" styleId="directionNo" />
<nested:hidden property="directionStatus" styleId="directionStatus" />
<nested:hidden property="unitOfOperationManagement" styleId="unitOfOperationManagement" />
<nested:hidden property="unitName" styleId="unitName" />
<nested:hidden property="shipperDivision" styleId="shipperDivision" />
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
				<td class="bcTitleSearch fb fcTitleSearch" width="130">製造指図番号</td>
				<td colspan="4">
					<nested:write property="directionNo" />
				</td>
			</tr>
			<tr>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">調合タンクNO</td>
				<td>
					<nested:write property="compoundTankNo" />
				</td>
				<td></td>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">ホールドタンクNO</td>
				<td>
					<nested:write property="holdTankNo" />
				</td>
			</tr>
			<tr>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">予備溶解ﾀﾝｸNO</td>
				<td>
					<nested:write property="dissolutionTankNo" />
				</td>
				<td></td>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">自社/花王区分</td>
				<td>
					<nested:write property="shipperDivisionLabel" />
				</td>
			</tr>
			<tr>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">他社コード１</td>
				<td colspan="4">
					<nested:write property="otherCompanyCd1" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">生産工場</td>
				<td>
					<nested:write property="productionLine" />
				</td>
				<td></td>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">生産工場名称</td>
				<td>
					<nested:write property="productionLineName" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">品目コード</td>
				<td>
					<nested:write property="itemCd" />
				</td>
				<td></td>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">品目名称</td>
				<td>
					<nested:write property="itemName" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">基本処方コード</td>
				<td>
					<nested:write property="recipeCd" />,<nested:write property="recipeVersion" />
				</td>
				<td></td>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">洗浄水絶対重量計</td>
				<td>
					<nested:write property="waterWeight" />
					<nested:notEmpty property="waterWeight">kg</nested:notEmpty>
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">仕込み予定数量</td>
				<td class="alignRight">
					<nested:write property="planedQty" />
				</td>
				<td>
					<nested:write property="unitName" />
				</td>
			</tr>
			<tr>

				<td class="fcTitleDetail fb bcTitleDetail" width="130">製造開始予定日時</td>
				<td colspan="4">
					<nested:write property="planedSdate" format="yyyy/MM/dd HH:mm"/>
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">製造終了予定日時</td>
				<td colspan="4">
					<nested:write property="planedEdate" format="yyyy/MM/dd HH:mm"/>
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">ステータス</td>
				<td colspan="4">
					<nested:write property="directionStatusName" />
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
				<%@ include file="/jsp/direction/direction_tab.jsp"%>
			</td>
		</tr>
	</table>
</tr>


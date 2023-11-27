<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<%-- 共通フィールド --%>
<nested:hidden property="op" styleId="op" />
<nested:hidden property="dirtyFlg" />
<nested:hidden property="directionDivision" styleId="directionDivision" />
<nested:hidden property="directionNo" styleId="directionNo" />
<nested:hidden property="unitOfOperationManagement" styleId="unitOfOperationManagement" />
<nested:hidden property="unitName" styleId="unitName" />
<nested:hidden property="itemName" styleId="itemName" />
<nested:hidden property="parentItemCd" styleId="parentItemCd" />
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
				<td class="bcTitleSearch fb fcTitleSearch" width="130">包装指図番号</td>
				<td width="200">
					<nested:write property="directionNo" />
				</td>
				<td></td>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">製造指図番号</td>
				<td>
					<!-- 20211209 Asclab Kouchi EdgeIE対応 -->
					<html:link href ="#" onclick='<%="open_modal_popup_edge(600, 600,'PkgDirectionDirectionDetailSearch', '',"
										+ "'pkgDirectionNo',$('directionNo').value,"
										+ "'directionDivision',$('directionDivision').value,"
										+ "'itemName',$('itemName').value); return false;"%>'>
					製造指図詳細
					</html:link>
				</td>
			</tr>
			<tr>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">生産工場</td>
				<td>
					<nested:write property="productionLine" />
				</td>
				<td></td>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">生産工場名称</td>
				<td>
					<nested:write property="productionLineName" />
				</td>
			</tr>
			<tr>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">包装ライン</td>
				<td>
					<nested:write property="packageLine" />
				</td>
				<td></td>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">包装ライン名称</td>
				<td>
					<nested:write property="packageLineName" />
				</td>
			</tr>
			<tr>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">充填タンクNo</td>
				<td>
					<nested:write property="filltankNo" />
				</td>
				<td></td>
				<td class="bcTitleSearch fb fcTitleSearch" width="130">充填タンク名称</td>
				<td>
					<nested:write property="filltankName" />
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
				<td class="bcTitleSearch fb fcTitleSearch" width="130">他社コード１</td>
				<td colspan="4">
					<nested:write property="otherCompanyCd1" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">基本処方コード</td>
				<td>
					<nested:write property="recipeCdVersion" />
				</td>
				<td></td>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">基本処方名称</td>
				<td>
					<nested:write property="recipeName" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">生産予定数量</td>
				<td class="alignRight">
					<nested:write property="planedQty" />
				</td>
				<td>
					<nested:write property="unitName" />
				</td>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">荷姿</td>
				<td>
					<nested:write property="styleOfPacking" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">包装開始予定日時</td>
				<td>
					<nested:write property="strPlanedSdate" />
				</td>
				<td></td>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">包装終了予定日時</td>
				<td>
					<nested:write property="strPlanedEdate" />
				</td>
			</tr>
			<tr>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">ロット番号</td>
				<td>
					<nested:write property="lotNo"/>
				</td>
				<td></td>
				<td class="fcTitleDetail fb bcTitleDetail" width="130">ステータス</td>
				<td>
					<nested:write property="directionStatus"/>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<%-- 共通項目部<<<<< --%>
<%-- タブ --%>
<tr>
	<td>
	<table border="0" cellspacing="" cellpadding="" width="100%">
		<tr>
			<td>
				<%@ include file="/jsp/pkgdirection/pkgdirection_tab.jsp"%>
			</td>
		</tr>
	</table>
</tr>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@page import="com.asahikaseieng.app.radiobuttons.RadioAutoOrHand"%>
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<!-- Style Sheet(Item用) -->
<link href='<%= request.getContextPath() + "/stylesheets/tab.css"%>'
	media="screen" rel="Stylesheet" type="text/css" />

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

		<%-- 数値型フィールド定義 --%>
		defineAsNumberField("recipePriority");
		defineAsNumberField("maejikan");
		defineAsNumberField("atojikan");
		defineAsNumberField("processWorkTime1");
		defineAsNumberField("processWorkTime2");
		defineAsNumberField("machineWorkTime1");
		defineAsNumberField("machineWorkTime2");
		defineAsNumberField("manWorkTime1");
		defineAsNumberField("manWorkTime2");

		storeInitValues('dirtyFlg');


	}, false);

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
	function putUpdate() {
		alertMsg = new Array();
		alertMsg[0] = "登録を行います。よろしいですか？";
		return confirm(alertMsg[0]);
	}
	
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>
<nested:form action="/MgrecipeAsprovaDetail" method="post" onsubmit="return false;" styleId="mainForm">

	<%@ include file="/jsp/common/postjavascript.jsf"%>

	<nested:hidden property="op" styleId="op" />
	<nested:hidden property="dirtyFlg" />
	<nested:hidden property="approvalStatus" styleId="approvalStatus" />
	<nested:hidden property="recipeUse" styleId="recipeUse" />
	<nested:hidden property="recipeId" styleId="recipeId" />
	<nested:hidden property="resouceGroupCd" styleId="resouceGroupCd" />
	<nested:hidden property="srhLink" styleId="srhLink" />

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
									<td class="fcTitle fb">Asprova情報詳細</td>
									<td>
										<table cellspacing="0" cellpadding="0" border="0">
											<tr>
												<td>
													<%@ include file="/jsp/common/disp_info_message.jsf"%>
													<%-- メッセージ表示は、共通のメッセージ表示jsfをincludeする --%>
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
							<%-- メッセージ表示 --%> <%@ include file="/jsp/common/disp_error_message.jsf"%>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>

					<%-- ヘッダー部 --%>
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
								<nested:equal property="recipeUse" value="4"><%-- 包装時 --%>
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="120">荷姿</td>
									<td width="155">
										<nested:write property="styleOfPacking" />
									</td>
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
					<tr>
						<td class="bcTitleLine"></td>
					</tr>
					<tr>
						<td height="5" colspan="2"></td>
					</tr>

					<tr>
						<td><!-- 明細部 -->
							<table width="750" cellspacing="2" cellpadding="1" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="220">設備コード</td>
									<td width="100">
										<nested:write property="resouceCd" />
									</td>
									<td class="fcTitleDetail fb bcTitleDetail" width="220">設備名称</td>
									<td>
										<nested:write property="resouceName" />
									</td>
								</tr>
							</table>
	
							<table cellspacing="2" cellpadding="1" width="100%" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="220">優先度</td>
									<td width="5">
										<nested:text property="recipePriority" styleId="recipePriority" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
									<td></td>
								</tr>
							</table>
	
							<table cellspacing="2" cellpadding="1" width="100%" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="220">前段取時間</td>
									<td width="5">
										<nested:text property="maejikan" styleId="maejikan" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
									<td>分</td>
								</tr>
							</table>
	
							<table cellspacing="2" cellpadding="1" width="100%" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="220">後段取時間</td>
									<td width="5">
										<nested:text property="atojikan" styleId="atojikan" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
									<td>分</td>
								</tr>
							</table>
	
							<table cellspacing="2" cellpadding="0" width="100%" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" rowspan="4" width="120">標準生産量当り</td>
								</tr>
	
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="100">工程時間</td>
									<td width="5">
										<nested:text property="processWorkTime1" styleId="processWorkTime1" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
	
									<td width="30">x +</td>
	
									<td width="5">
										<nested:text property="processWorkTime2" styleId="processWorkTime2" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
	
									<td>分</td>
								</tr>
	
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="100">設備稼働時間</td>
									<td width="5">
										<nested:text property="machineWorkTime1" styleId="machineWorkTime1" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
	
									<td width="30">x +</td>
	
									<td width="5">
										<nested:text property="machineWorkTime2" styleId="machineWorkTime2" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
	
									<td>分</td>
								</tr>
	
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="100">人作業時間</td>
									<td width="5">
										<nested:text property="manWorkTime1" styleId="manWorkTime1" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
	
									<td width="30">x +</td>
	
									<td width="5">
										<nested:text property="manWorkTime2" styleId="manWorkTime2" size="6" onchange="setDirtyFlg();" style="ime-mode:disabled"/>
									</td>
	
									<td>分</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td class="alignCenter">
							<table cellpadding="0" cellspacing="0" width="450" border="0">
								<tr>
									<td colspan="3" height="2"></td>
								</tr>

								<tr>
									<nested:notEqual property="srhLink" value="1">
										<nested:equal property="approvalStatus" value="1">
											<nested:equal property="updateAuthority" value="true">
												<td class="alignCenter">
													<div id="cssButton">
														<a href="#" onclick="if (!(putUpdate())) {return false;}else{return form_submit('op', 'update'); return false;}" class="cssButton">
															&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
														</a>
													</div>
												</td>
			
												<td width="50"><br></td>
											</nested:equal>
	
										</nested:equal>
									</nested:notEqual>
									<td class="alignCenter">
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

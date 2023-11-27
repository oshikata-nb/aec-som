<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<!-- Style Sheet -->
<link href='<%= request.getContextPath() + "/stylesheets/tree.css"%>' media="screen" rel="Stylesheet" type="text/css" />
<script src='<%= request.getContextPath() + "/javascripts/Tree-optimized.js"%>' type="text/javascript"></script>

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
        defineAsRequiredField("roleId");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("roleId");
       	};

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		refreshLabel();
	}, false);

	<%-- メニューTREE作成 --%>
	<bean:size id="listSize" name="roleDetailMenuForm" property="menus" scope="session"/>

	<logic:notEqual name="listSize" value="0">
		var g_tree = null;
		function TafelTreeInit() {
			<bean:write name="roleDetailMenuForm" property="js" filter="false" scope="session" />
				g_tree = new TafelTree('treeSpace', struct, {
					'generate' : true,
					'imgBase' : 'images/',
					'defaultImgClose' : 'folder.gif',
					'checkboxesThreeState' : true ,
					'onDrop' : function(){return true;},
					'copyDrag' : false,
					'behaviourDrop' : 'child',
					'cookies' : false 
				});

				Event.observe('openTreeAll',  'click', function() { this.expend(); return false; }.bind(g_tree), false);
				Event.observe('closeTreeAll', 'click', function() { this.collapse(); return false; }.bind(g_tree), false);
			}
	</logic:notEqual>

	function getChkMenuList() {
		var menuList = "";
		<%-- チェック項目取得 --%>
		var branches = g_tree.getCheckedBranches();

		for (var i = 0; i < branches.length; i++) {
			menuList = menuList + branches[i].getId() + '@';
		}

		<%-- グレーアウトチェック項目取得 --%>
		branches = g_tree.getPartCheckedBranches();

		for (var i = 0; i < branches.length; i++) {
			menuList = menuList + branches[i].getId() + '@';
		}

		$("chkMenuList").value = menuList;
	}

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
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/RoleDetail" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="chkMenuList" />
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="newFlg" styleId="newFlg"/>

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
									<td class="fcTitle fb">ロールマスタ</td>
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
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td class="fcTitleDetail fb bcTitleDetail" width="130">ロールID</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="roleId" maxlength="10" size="10" style="ime-mode:disabled" styleId="roleId" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="roleId"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="2"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">ロール名称</td>
									<td>
										<nested:text property="roleName" maxlength="64" size="75" styleId="roleName" onchange="setDirtyFlg();" />
									</td>
								</tr>
							</table>

							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td height="100%" class="valignTop" nowrap>
										<logic:notEqual name="listSize" value="0">						
											<a id="openTreeAll" href="#">
												open all
											</a>
											|
											<a id="closeTreeAll" href="#">
												close all
											</a>

											<div id="treeSpace" style="{height: 100%; width: 100%;}"></div>						
										</logic:notEqual>

										<logic:equal name="listSize" value="0">
											<div class="errorMenu" id="errorMenu">
												<bean:message key="errors.menu"/>
											</div>
										</logic:equal>
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
									<nested:equal property="updateAuthority" value="true">
										<td class="alignRight">
											<div id="cssButton">
												<a href="#" onclick="if(!putConfirm()){return false;}else{getChkMenuList(); return form_submit('op', 'update'); return false;}" class="cssButton">
													&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
												</a>
											</div>
										</td>
	
										<td width="50">
											<br>
										</td>
									</nested:equal>

									<nested:notEqual property="newFlg" value="true">
										<nested:equal property="deleteAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if(!deleteConfirm()){return false;}else{return form_submit('op', 'delete'); return false;}" class="cssButton">
														&nbsp;&nbsp;削&nbsp;&nbsp;除&nbsp;&nbsp;
													</a>
												</div>
											</td>
		
											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:notEqual>

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

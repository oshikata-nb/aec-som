<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.asahikaseieng.Constants"%>
<%@ include file="/jsp/common/taginclude.jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>
<%-- ▼業務固有css ここから --%>
<style type="text/css">
</style>
<%-- ▲業務固有css ここまで --%>
<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- 詳細画面への遷移：ローカルjspで使用 --%>
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
		initializeFormState();
		<%-- 必須フィールド定義 --%>
		<%-- 数値型フィールド定義 --%>
		<%-- 日付型フィールド定義 --%>
		<%-- 明細部 --%>
		var tblList = $('tblList');
		if (tblList != null) {
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);
		}
		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues(/^srh.*/);
		refreshLabel();
	}, false);
</script>
<%-- ▲業務固有javaScriptここまで --%>
</head>
<body>
<nested:form action="/TopicList" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf"%>
	<nested:hidden property="op" styleId="op"/>
	<table cellpadding="0" cellspacing="0" border="0" width="750">
		<tr>
			<td class="valignTop alignLeft">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><!-- ヘッダー部 -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="10" colspan="3"></td>
						</tr>
						<tr>
							<td class="fcTitle fb">不具合リスト</td>
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
							<td class="bcTitleLine" colspan="3"></td>
						</tr>
						<tr>
							<td height="5"></td>
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
					<td class="alignRight">
					<table cellspacing="1" cellpadding="1" border="0">
						<tr>
							<td>
								<div id="cssButton">
									<a href='<%= request.getContextPath() + "/TopicDetail.do?op=initNew"%>' class="cssButton">
										トピックの追加
									</a>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<nested:notEmpty property="searchList">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><!-- 明細部 -->
						<table>
							<tr>
								<td>
								<table cellspacing="2" cellpadding="2" id="tblList" width="750">
									<tr class="bcTitleList fb fcTitleList">
										<td width="30">ID</td>
										<td width="">見出し</td>
										<td width="120">入力者</td>
										<td width="120">回答者</td>
										<td width="120">追加日</td>
									</tr>
									<nested:iterate id="searchList" property="searchList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="false">
											<tr class="bcList2">
										</app:modulo>
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="2" zero="true">
											<tr class="bcList2">
										</app:modulo>
										<td>
											<nested:write property="topicId" />
											<nested:define id="topicId" property="topicId"/>
										</td>
										<td>
											<html:link href="#" onclick='<%="toDetail('" + request.getContextPath() 
												+ "/TopicDetail.do?op=init&topicId="
												+ pageContext.findAttribute("topicId").toString() 
												+ "'); return false;"%>'>
											<nested:write property="topicTitle" />
											</html:link>
										</td>
										<td>
											<nested:write property="topicInputor" />
										</td>
										<td>
											<nested:write property="topicRetInputor" />
										</td>
										<td>
											<nested:write property="inputDate" format="yyyy/MM/dd HH:mm" />
										</td>
									</nested:iterate>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td class="alignCenter">
							<%-- ページング --%>
							<%@ include file="/jsp/common/pager/pager.jsf"%>
							<%-- ページング ここまで --%>
						</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
				</table>
			</nested:notEmpty>
			</td>
		</tr>
	</table>
</nested:form>
</body>
</html:html>
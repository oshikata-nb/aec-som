<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

<%-- ▼業務固有css ここから--%>
<style type="text/css">
<!--
	td.TTLC    { text-align:center; vertical-align:middle; font-weight:bold; font-size:8pt; }
	input.R    { text-align:right;  background-color:white; font-weight:normal; font-size:9pt; border: 1px solid #7f9db9; ime-mode:disabled; }
	input.IMEL { text-align:left;   background-color:white; font-weight:normal; font-size:9pt; border: 1px solid #7f9db9; ime-mode:active;   }
	input.LBLL { text-align:left;   background-color:transparent; font-weight:normal; font-size:9pt; border-width: 0px; border-style: solid;}
	input.LBLR { text-align:right;  background-color:transparent; font-weight:normal; font-size:9pt; border-width: 0px; border-style: solid;}
-->
</style>
<%-- ▲業務固有css ここまで --%>

<%-- ▼業務固有javaScript ここから --%>
<script language="JavaScript" type="text/javascript">
	<%-- Load時の処理 --%>
	Event.observe(window, 'load', function() {
		<%-- フォーム設定の初期化 --%>
		initializeFormState();

		<%-- 必須フィールド定義 --%>
        defineAsRequiredField("srhDateNew");
        defineAsRequiredField("srhLineNew");

		<%-- 日付型フィールド定義 --%>
		defineAsDateField( "srhDateNew" );

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg');

		<%-- 明細部 --%>
		var tblList = $('tblList');

		<%-- 数値型フィールド定義 --%>
		if (tblList!=null) {
			for (i=0; i<5; i++) {
				defineAsTimeField_DaylyrRport("headerListPage[" + i.toString() + "].insideTotalHhmm");
				defineAsTimeField_DaylyrRport("headerListPage[" + i.toString() + "].outsideTotalHhmm");
				defineAsTimeField_DaylyrRport("headerListPage[" + i.toString() + "].employTimeHhmm");
				defineAsTimeField_DaylyrRport("headerListPage[" + i.toString() + "].indirectTimeHhmm");
			}

			<%-- 担当者のautocomplete --%>
			for (i=0; i<5; i++) {
				new Ajax.Autocompleter(
					"headerListPage[" + i.toString() + "].tantoCd",
					"autocomplete_choices",
					"<%= request.getContextPath() %>/<%=getAutocomplitePath(session)%>",
					{
						paramName : "code",
						afterUpdateElement : setLoginLabel
					}
				);
				<%-- 検索後入力された場合の不整合をただす --%>
				Event.observe('headerListPage[' + i.toString() + '].tantoCd', 'keypress',
					clearLabel.bindAsEventListener($('headerListPage[' + i.toString() + '].tantoNm')), false);
			}
			<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
			<%-- 対象外の例：検索条件部と明細のチェックボックス --%>
			storeInitValues(/^srh.*/);	
		}

		$("execute").value = false;

		refreshLabel();
	}, false);

	function open_window(theURL,winName,features) { //v2.0
		win = window.open(theURL,winName,features);
		win.focus();
		return false;
	}

	<%-- ajax --%>
	function setLoginLabel(text, li) {
		<%-- ここでinnerTextをラベルとして表示 --%>
		//$("headerListPage[0].tantoNm").value = li.getElementsByTagName('span')[0].innerText;
		if (objActive != null) {
			objActive.value = li.getElementsByTagName('span')[0].innerText;
			refreshLabel();

			var name0 = objActive.name;
			var name = "";
			name = name0.replace("tantoNm", "insideTotalHhmm");
			$(name).value = "07:30";
			name = name0.replace("tantoNm", "outsideTotalHhmm");
			$(name).value = "00:00";
			name = name0.replace("tantoNm", "employTimeHhmm");
			$(name).value = "07:30";
			name = name0.replace("tantoNm", "indirectTimeHhmm");
			$(name).value = "00:00";
		}
	}
	<%-- オートコンプリート用 --%>
	var objActive = null;
	function setNameObj( obj ) {
		var str = obj.name.replace("tantoCd","tantoNm");
		objActive = $(str);
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}

	<%-- 登録確認メッセージ --%>
	function putConfirm() {
		if($("execute").value == "false"){
			alertMsg = new Array();
			alertMsg[0] = "登録してよろしいですか？";

			if (confirm(alertMsg[0])) {
				putNextCommand( 'update' );
			}
		}

		return false;
	}
	<%-- 変更確認メッセージ --%>
	function putDirtyConfirm( value ) {
		if($("execute").value == "false"){
			chkDirtyFlg();
			var flg = $("dirtyFlg").checked;
			if (flg) {
				/* 何か値が変更されている場合 */
				if (!continueConfirm()) {
					return false;
				} else {
					return putNextCommand( value );
				}
			} else {
				return putNextCommand( value );
			}
		}

		return false;
	}
	<%-- 担当者削除確認メッセージ --%>
	function putDeleteConfirm() {
		if($("execute").value == "false"){
			if (deleteConfirm()) {
				return putNextCommand( 'delete' );
			}
		}

		return false;
	}
	<%-- 担当区分の変更 --%>
	function putChangeKubun(obj, obj2Name) {
		// 区分変更なしは、終了
		if( obj.value == tantoDivValue ) {
			return true;
		}
		if( obj.checked ) {
			if( putDirtyConfirm('reSearch') ) {
				return true;
			}
			// 区分変更しない場合は、元に戻す
			var obj2 = $(obj2Name);
			obj2.checked = true;
		}
		return false;
	}

	<%-- 次のコマンド設定 --%>
	function putNextCommand(value) {
		if($("execute").value == "false"){
			$("execute").value = true;
			
			DETAIL.$("nextCmd").value = "op=" + value;
			DETAIL.form_submit("op", "reload");
		}

		return true;
	}
	<%-- DETAILから呼ぶ --%>
	function putDETAIL_OK(name, value) {
		return form_submit(name, value);
	}

	function putSearch() {
		var tblList = $('tblList');
		if (tblList != null ) {
		//if( $("headerListPage[0].tantoCd")!=null ) {
			chkDirtyFlg();
			var flg = $("dirtyFlg").checked;
			if (flg) {
				return check_form_submit('op', 'search');
			} else {
				return form_submit('op', 'search');
			}
		} else {
			return form_submit('op', 'search');
		}
	}
	function chkDirtyFlg() {
		if ($("dirtyFlg").checked == false) {
			if (DETAIL.$("dirtyFlg").checked == true) {
				$("dirtyFlg").checked = true;
			} else if (DETAIL.$("dirtyFlg").value == "true") {
				$("dirtyFlg").checked = true;
			} else if ($("dirtyFlg").value == "true") {
				$("dirtyFlg").checked = true;
			}
		}
		var flg = $("dirtyFlg").checked;
		if (flg) {
			<%-- DETAIL用の変更フラグ --%>
			$("modify").value = "1";
		}
	}
	
	<%-- 確認メッセージ --%>
	function reportConfirm() {
		alertMsg = new Array();
		alertMsg[0] = "帳票(EXCEL)出力してもよろしいですか？";

		return confirm(alertMsg[0]);
	}
</script>
<%-- ▲業務固有javaScript ここまで --%>

</head>

<body>

<nested:form action="/DailyReportList" method="post" onsubmit="return false;" styleId="mainForm">	
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="nowpage" styleId="nowpage"/>
	<nested:hidden property="execute" styleId="execute"/>

	<%-- DETAIL用の変更フラグ --%>
	<input type="hidden" name="modify" id="modify">

	<%-- 担当区分の変更チェック用 --%>
	<script language="javascript" type="text/javascript">
	<!--
		var tantoDivValue = '<nested:write property="tantoDiv"/>';
	//-->
	</script>
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="valignTop alignLeft">
				<%-- ヘッダー部 --%>
				<table border="0" cellpadding="0" cellspacing="0" width="750">
					<tr height="10">
						<td nowrap width="250"></td>
						<td nowrap width="500"></td>
					</tr>
					<tr>
						<td class="fcTitle fb">作業日報</td>
						<td><%-- infoメッセージ表示 --%>
							<%@ include file="/jsp/common/disp_info_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>
					<tr>
						<td class="bcTitleLine" colspan="2"></td>
					</tr>
					<tr height="5">
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="2">
							<%-- errorメッセージ表示 --%>
							<%@ include file="/jsp/common/disp_error_message.jsf" %>
							<%-- メッセージ表示 ここまで --%>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<%-- 検索部 --%>
							<table width="100%" cellspacing="0" cellpadding="0"  border="0">
								<tr>
									<td nowrap width="450" class="alignLeft">
										<table cellspacing="2" cellpadding="0"  border="0">
											<tr>
												<td nowrap width="130" class="bcTitleSearch fb fcTitleSearch">製造日</td>
												<td>
													<nested:text property="srhDateNew" maxlength="8" size="10" styleId="srhDateNew" style="ime-mode:disabled;" onfocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td nowrap width="130" class="bcTitleSearch fb fcTitleSearch">生産工場</td>
												<td>
													<nested:select property="srhLineNew"  styleId="srhLineNew">
														<nested:options property="srhLineValues" labelProperty="srhLineLabels" />
													</nested:select>
												</td>
											</tr>
										</table>
									</td>
									<td nowrap width="300" class="alignRight valignBottom">
										<div id="cssButton">
											<a href="#" onclick="putSearch(); return false;" class="cssButton">
												&nbsp;&nbsp;検&nbsp;&nbsp;索&nbsp;&nbsp;
											</a>
										</div>
									</td>
								</tr>
								<tr height="5">
									<td colspan="2"></td>
								</tr>
								<tr>
									<td class="bcTitleLine" colspan="2"></td>
								</tr>
								<tr height="10">
									<td colspan="2"></td>
								</tr>
							</table>
						</td>
					</tr>

				</table>
			</td>
		</tr>

		<%-- 検索結果表示部 --%>
		<nested:notEmpty property="headerList">
			<%-- 社員・協力会社切り替え＆帳票ボタン表示部 --%>
			<tr>
				<td class="valignTop alignLeft">
					<table width="750" cellspacing="2" cellpadding="1" border="0">
						<tr>
							<td nowrap width="232" class="bcTitleList">
								<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
									<tr height="24">
										<td class="fcTitleList TTLC bcTitleList" style="font-size:11pt;font-weight:normal;">
											<nested:radio property="tantoDiv" styleId="tantoDiv1" value="1" onclick="return putChangeKubun(this, 'tantoDiv2');"/>
											<label for="tantoDiv1">社員</label>&nbsp;
											<nested:radio property="tantoDiv" styleId="tantoDiv2" value="2" onclick="return putChangeKubun(this, 'tantoDiv1');"/>
											<label for="tantoDiv2">協力会社</label>
										</td>
									</tr>
								</table>
							</td>
							<td nowrap width="100" class="valignTop">
								<div id="autocomplete_choices" class="autocomplete" style="width:0px;"></div>
							</td>
							<td nowrap width="414" class="alignRight">
								<div id="cssButton">
									<a href="#" onclick="if (!(reportConfirm())) {return false;}else{return form_submit('op', 'report'); return false;}" class="cssButton">
										帳票(EXCEL) 
									</a>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%-- ヘッダー表示部 --%>
			<tr>
				<td class="valignTop alignLeft">
					<table border="0" cellpadding="0" cellspacing="0" width="750">
						<tr>
							<td class="alignLeft valignTop">
								<table width="750" cellspacing="2" cellpadding="1" id="tblList">
									<%-- ヘッダー部の見出し --%>
									<tr height="24" class="bcTitleList fcTitleList">
										<td nowrap width="90" class="TTLC" rowspan="8">指図番号</td>
										<td nowrap width="68" class="TTLC" rowspan="8">製品名</td>
										<td nowrap width="70" class="TTLC">&nbsp;</td>
										<td nowrap width="370" class="TTLC" colspan="6">
											<nested:equal property="tantoDiv" value="1">社員</nested:equal>
											<nested:equal property="tantoDiv" value="2">協力会社</nested:equal>
										</td>
										<td nowrap width="60" class="TTLC">
											<nested:equal property="tantoDiv" value="1">協力会社</nested:equal>
											<nested:equal property="tantoDiv" value="2">社員</nested:equal>
										</td>
										<td nowrap width="60" class="TTLC" rowspan="8">指図<BR>作業<BR>合計</td>
										<td nowrap width="20" class="TTLC" rowspan="8"></td>
									</tr>
									<%-- ヘッダー部の明細 --%>
									<tr height="24" class="bcTitleList fcTitleList">
										<td class="TTLC">削除</td>
										<nested:iterate id="headerListPage" property="headerListPage" indexId="index">
											<td class="alignCenter"><%-- 削除FLG --%>
												<nested:checkbox property="delFlg" onchange="setDirtyFlg();"/>
											</td>
										</nested:iterate>
										<td nowrap width="60" class="TTLC" rowspan="7">合計</td>
										<td nowrap width="60" class="TTLC" rowspan="7">合計</td>
									</tr>
									<tr height="24" class="bcTitleList fcTitleList">
										<td class="TTLC">コード</td>
										<nested:iterate id="headerListPage" property="headerListPage" indexId="index">
											<td><%-- 担当者コード --%>
												<nested:text property="tantoCd" maxlength="7" styleClass="IMEL" style="width:58px;" onfocus="setNameObj(this);this.select();" onchange="setDirtyFlg();"/>
											</td>
										</nested:iterate>
									</tr>
									<tr height="24" class="bcTitleList fcTitleList">
										<td class="TTLC">担当者</td>
										<%-- オートコンプリート用 --%>
										<span id="lblTantonm" style="visibility:none;"></span>
										<nested:iterate id="headerListPage" property="headerListPage" indexId="index">
											<td><%-- 担当者 --%>
												<nested:text property="tantoNm" styleId="tantoNm" styleClass="LBLL fcTitleList" style="width:58px;" onfocus="this.select();" readonly="True"/>
											</td>
										</nested:iterate>
									</tr>
									<tr height="24" class="bcTitleList fcTitleList">
										<td class="TTLC">時間内</td>
										<nested:iterate id="headerListPage" property="headerListPage" indexId="index">
											<td><%-- 時間内 --%>
   												<nested:text property="insideTotalHhmm" maxlength="6" styleClass="R" style="width:58px;" onfocus="this.select();" onchange="setDirtyFlg();"/>
											</td>
										</nested:iterate>
									</tr>
									<tr height="24" class="bcTitleList fcTitleList">
										<td class="TTLC">時間外</td>
										<nested:iterate id="headerListPage" property="headerListPage" indexId="index">
											<td><%-- 時間外 --%>
												<nested:text property="outsideTotalHhmm" maxlength="6" styleClass="R" style="width:58px;" onfocus="this.select();" onchange="setDirtyFlg();"/>
											</td>
										</nested:iterate>
									</tr>
									<tr height="24" class="bcTitleList fcTitleList">
										<td class="TTLC">就業時間</td>
										<nested:iterate id="headerListPage" property="headerListPage" indexId="index">
											<td><%-- 就業時間 --%>
   												<nested:text property="employTimeHhmm" styleClass="LBLR fcTitleList" style="width:58px;" onfocus="this.select();" readonly="True"/>
											</td>
										</nested:iterate>
									</tr>
									<tr height="24" class="bcTitleList fcTitleList">
										<td class="TTLC">間接時間</td>
										<nested:iterate id="headerListPage" property="headerListPage" indexId="index">
											<td><%-- 間接時間 --%>
												<nested:text property="indirectTimeHhmm" styleClass="LBLR fcTitleList" style="width:58px;" onfocus="this.select();" readonly="True"/>
											</td>
										</nested:iterate>
									</tr>
								</table>
							</td>
						</tr>

						<%-- 明細部 --%>
						<tr height="222">
							<td nowrap width="750" class="alignLeft valignTop">
								<iframe src='<%= request.getContextPath() + "/DailyReportDetailList.do?op=reload" %>' frameborder="0" width="100%" height="100%" scrolling="yes" name="DETAIL"></iframe>
							</td>
						</tr>

						<%-- 余白 --%>
						<tr height="5">
							<td>
							</td>
						</tr>

						<%-- ボタン類 --%>
						<tr>
							<td class="alignCenter">
								<table cellpadding="0" cellspacing="0" width="450" border="0">
									<tr>
										<td nowrap width="50">&nbsp;</td>
										<td class="alignCenter">
											<nested:greaterThan property="nowpage" value="1">
												<div id="cssButton">
													<a href="#" onclick="putNextCommand('prev'); return false;" class="cssButton">
														&nbsp;&nbsp;＜&nbsp;前&nbsp;へ&nbsp;&nbsp;
													</a>
												</div>
											</nested:greaterThan>
										</td>
										<td nowrap width="50">&nbsp;</td>
										<td class="alignCenter">
											<div id="cssButton">
												<a href="#" onclick="putNextCommand('next'); return false;" class="cssButton">
													&nbsp;&nbsp;次&nbsp;へ&nbsp;＞&nbsp;&nbsp;
												</a>
											</div>
										</td>
										<td nowrap width="50">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr height="5">
							<td>
							</td>
						</tr>
						<tr>
							<td class="alignCenter">
								<table cellpadding="0" cellspacing="0" width="450" border="0">
									<tr>
										<nested:equal property="updateAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="putConfirm(); return false;" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;　&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
											<td nowrap width="50">&nbsp;</td>
										</nested:equal>
										<nested:equal property="deleteAuthority" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="putDeleteConfirm(); return false;" class="cssButton">
														&nbsp;担当者削除&nbsp;
													</a>
												</div>
											</td>
											<td nowrap width="50">&nbsp;</td>
										</nested:equal>
										<td class="alignLeft">
											<div id="cssButton">
												<a href="#" onclick="putDirtyConfirm('clear'); return false;" class="cssButton">
													&nbsp;キャンセル&nbsp;
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
		</nested:notEmpty>

		<%-- Excelダウンロード処理 --%>
		<nested:equal property="excelDownloadFlg" value="true">
			<META HTTP-EQUIV="REFRESH" CONTENT="0; URL=<%= request.getContextPath() %>/FileDownload/">
		</nested:equal>
		
	</table>
</nested:form>
</body>
<%!
    /**
     * オートコンプリートのパス
     * @param session
     * @return
     */
	private String getAutocomplitePath(HttpSession session) {
		String path = (String)session.getAttribute( "autocomplitePath" );
		if ( path == null ) {
			path = "LoginForAutoComplete.do?op=searchLogin";
		}
        return path;
    }
%>
</html:html>

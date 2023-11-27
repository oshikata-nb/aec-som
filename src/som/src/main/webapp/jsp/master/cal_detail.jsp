<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/jsp/common/taginclude.jsf" %>

<html:html>
<head>
<%@ include file="/jsp/common/head.jsf"%>
<%@ include file="/jsp/common/head_main.jsf"%>

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
		defineAsRequiredField("calCd");
		defineAsRequiredField("calYear");

		<%-- 前後空白除去定義 --%>
		if ($('newFlg').value == 'true'){
        	defineAsKeyField("calCd");
       	};

		<%-- 初期値を退避(対象外IDを文字列または正規表現で渡す) --%>
		storeInitValues('dirtyFlg','dirtyYearFlg');
	}, false);

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
	
	<%-- 会計年度変更確認メッセージ --%>
	function changeYear() {
		var flg = $("dirtyYearFlg").checked;
		if (flg) {
			/* 会計年度が変更されている場合 */
			alertMsg = new Array();
			alertMsg[0] = "会計年度が変更されています。カレンダーを作成し直して下さい。";
			alert(alertMsg[0]);
			return false;
		}else{
			return true;
		}
	}

	<%-- 変更フラグセット --%>
	function setDirtyFlg() {
		$("dirtyFlg").checked = true;
	}

	<%-- 会計年度変更フラグセット --%>
	function setDirtyYearFlg(val) {
		$("dirtyYearFlg").checked = val;
	}

	<%-- 色の変更 --%>
	function changeColor(obj) {
		<%-- IDの取得 --%>
		var textId = obj.id;

		<%-- 日付のないセルの場合、処理なし --%>
		var day = document.getElementById(textId).innerHTML;

		if (day == "") {
			return obj;
		}

		<%-- テキストの色を取得 --%>
		var textColor = document.getElementById(textId).bgColor;

		<%-- 色変更 --%>
		if(textColor == "#ffcccc") {
			document.getElementById(textId).bgColor = "#FFFFFF";
		} else {
			document.getElementById(textId).bgColor = "#FFCCCC";
		}

		<%-- 平日休日フラグの変更 --%>
		changeHoliday(obj);

		return obj;
	}

	<%-- 平日休日フラグの変更 --%>
	function changeHoliday(obj) {
		<%-- IDの取得 --%>
		var textId = obj.id;

		<%-- 日付を取得 --%>
		var day = document.getElementById(textId).innerHTML;

		<%-- tableのindex取得(tab99day99) --%>
		var tableIdx = textId.substr(3, 2);

		if (!(tableIdx.substr(1, 1) == "0" || tableIdx.substr(1, 1) == "1")) {
			<%-- ２桁目が数値以外の場合は、１桁目のみ取得 --%>
			tableIdx = tableIdx.substr(0, 1);
		}

		<%-- 変更月の平日休日フラグ取得 --%>
		var hiddenId = "calDd" + tableIdx;
		var calDd = document.getElementById(hiddenId).value;

		<%-- クリックした日付の平日休日フラグ取得 --%>
		var dayFlg = calDd.substr(day - 1, 1);

		<%-- クリックした日付のフラグを反転 --%>
		if (dayFlg == "0") {
			<%-- 平日→休日 --%>
			dayFlg = "1";
		} else {
			<%-- 休日→平日 --%>
			dayFlg = "0";
		}

		<%-- 変更月の平日休日フラグ再設定 --%>
		var length = calDd.length

		if (day == 1) {
			calDd = dayFlg + calDd.substring(day, length);
		} else {
			if (day == length) {
				calDd = calDd.substring(0, day-1) + dayFlg;
			} else {
				calDd = calDd.substring(0, day-1) + dayFlg + calDd.substring(day, length);
			}
		}

		document.getElementById(hiddenId).value = calDd;

		return true;
	}
</script>
<%-- ▲業務固有javaScriptここまで --%>

</head>

<body>

<nested:form action="/CalDetail" method="post" onsubmit="return false;" styleId="mainForm">
	<%@ include file="/jsp/common/postjavascript.jsf" %>

	<nested:hidden property="op" styleId="op"/>
	<nested:hidden property="dirtyFlg" styleId="dirtyFlg"/>
	<nested:hidden property="dirtyYearFlg" styleId="dirtyYearFlg"/>
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
									<td class="fcTitle fb">カレンダーマスタ</td>
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
									<td class="fcTitleDetail fb bcTitleDetail" width="130">カレンダーコード</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="calCd" maxlength="4" size="4" style="ime-mode:disabled" styleId="calCd" onchange="setDirtyFlg();" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="calCd"/>
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">会計年度</td>
									<td>
										<nested:equal property="newFlg" value="true">
											<nested:text property="calYear" maxlength="4" size="4" styleClass="alignRight" style="ime-mode:disabled" styleId="calYear" onchange="setDirtyFlg();setDirtyYearFlg(true);" />
										</nested:equal>

										<nested:notEqual property="newFlg" value="true">
											<nested:write property="calYear" />
										</nested:notEqual>
									</td>
								</tr>

								<tr>
									<td height="5" colspan="3"></td>
								</tr>

								<tr>
									<td class="bcTitleLine" colspan="3"></td>
								</tr>

								<tr>
									<td class="fcTitleDetail fb bcTitleDetail">カレンダー用途名</td>
									<td>
										<nested:text property="calName" maxlength="30" size="55" styleId="calName" onchange="setDirtyFlg();" />
									</td>

									<nested:notEqual property="newFlg" value="true">
										<td class="alignRight">
											<table cellspacing="1" cellpadding="1"  border="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" onclick="return form_submit('op', 'copy'); return false;" class="cssButton">
																&nbsp;&nbsp;コピー作成&nbsp;&nbsp;
															</a>
														</div>
													</td>	
												</tr>
											</table>
										</td>
									</nested:notEqual>
								</tr>

								<nested:equal property="newFlg" value="true">
									<tr>
										<td class="fcTitleDetail fb bcTitleDetail">初期休日</td>
										<td>
											<nested:multibox property="holiday" value="0" onchange="setDirtyFlg();" />日
											<nested:multibox property="holiday" value="1" onchange="setDirtyFlg();" />月
											<nested:multibox property="holiday" value="2" onchange="setDirtyFlg();" />火
											<nested:multibox property="holiday" value="3" onchange="setDirtyFlg();" />水
											<nested:multibox property="holiday" value="4" onchange="setDirtyFlg();" />木
											<nested:multibox property="holiday" value="5" onchange="setDirtyFlg();" />金
											<nested:multibox property="holiday" value="6" onchange="setDirtyFlg();" />土
										</td>

										<td class="alignRight">
											<table cellspacing="1" cellpadding="1"  border="0">
												<tr>
													<td>
														<div id="cssButton">
															<a href="#" onclick="setDirtyYearFlg(false);return form_submit('op', 'make'); return false;" class="cssButton">
																&nbsp;&nbsp;作&nbsp;&nbsp;成&nbsp;&nbsp;
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</nested:equal>
							</table>

							<nested:notEmpty property="calDetailList">
								<table width="100%" cellspacing="2" cellpadding="2" border="0">
									<tr>
										<td align=center height="5" colspan="3">
											※日付をダブルクリックすることで、平日／休日を置き換えることができます。
										</td>
									</tr>

									<nested:iterate id="calDetailList" property="calDetailList" indexId="index">
										<app:modulo numerator='<%=pageContext.findAttribute("index").toString()%>' denominator="3" zero="true">
										<tr>
										</app:modulo>

										<td align=center valign=top class="fb">
											<nested:write name="calDetailForm" property='<%= "title[" + index + "]" %>' />

											<table width="200" cellspacing="2" cellpadding="2" border="0" cols="7">
												<tr>
													<td align=center width="14%" class="fcTitleDetail fb bcTitleDetail">日</td>
													<td align=center width="14%" class="fcTitleDetail fb bcTitleDetail">月</td>
													<td align=center width="14%" class="fcTitleDetail fb bcTitleDetail">火</td>
													<td align=center width="14%" class="fcTitleDetail fb bcTitleDetail">水</td>
													<td align=center width="14%" class="fcTitleDetail fb bcTitleDetail">木</td>
													<td align=center width="14%" class="fcTitleDetail fb bcTitleDetail">金</td>
													<td align=center width="14%" class="fcTitleDetail fb bcTitleDetail">土</td>
												</tr>

												<tr>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day01" %>' value="0">
														<td align=center id='<%= "tab" + index + "day01" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day01" %>' value="1">
														<td align=center id='<%= "tab" + index + "day01" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day01" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day02" %>' value="0">
														<td align=center id='<%= "tab" + index + "day02" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day02" %>' value="1">
														<td align=center id='<%= "tab" + index + "day02" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day02" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day03" %>' value="0">
														<td align=center id='<%= "tab" + index + "day03" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day03" %>' value="1">
														<td align=center id='<%= "tab" + index + "day03" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day03" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day04" %>' value="0">
														<td align=center id='<%= "tab" + index + "day04" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day04" %>' value="1">
														<td align=center id='<%= "tab" + index + "day04" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day04" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day05" %>' value="0">
														<td align=center id='<%= "tab" + index + "day05" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day05" %>' value="1">
														<td align=center id='<%= "tab" + index + "day05" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day05" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day06" %>' value="0">
														<td align=center id='<%= "tab" + index + "day06" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day06" %>' value="1">
														<td align=center id='<%= "tab" + index + "day06" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day06" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day07" %>' value="0">
														<td align=center id='<%= "tab" + index + "day07" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day07" %>' value="1">
														<td align=center id='<%= "tab" + index + "day07" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day07" />
														</td>
												</tr>

												<tr>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day08" %>' value="0">
														<td align=center id='<%= "tab" + index + "day08" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day08" %>' value="1">
														<td align=center id='<%= "tab" + index + "day08" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day08" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day09" %>' value="0">
														<td align=center id='<%= "tab" + index + "day09" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day09" %>' value="1">
														<td align=center id='<%= "tab" + index + "day09" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day09" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day10" %>' value="0">
														<td align=center id='<%= "tab" + index + "day10" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day10" %>' value="1">
														<td align=center id='<%= "tab" + index + "day10" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day10" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day11" %>' value="0">
														<td align=center id='<%= "tab" + index + "day11" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day11" %>' value="1">
														<td align=center id='<%= "tab" + index + "day11" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day11" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day12" %>' value="0">
														<td align=center id='<%= "tab" + index + "day12" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day12" %>' value="1">
														<td align=center id='<%= "tab" + index + "day12" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day12" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day13" %>' value="0">
														<td align=center id='<%= "tab" + index + "day13" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day13" %>' value="1">
														<td align=center id='<%= "tab" + index + "day13" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day13" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day14" %>' value="0">
														<td align=center id='<%= "tab" + index + "day14" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day14" %>' value="1">
														<td align=center id='<%= "tab" + index + "day14" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day14" />
														</td>
												</tr>

												<tr>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day15" %>' value="0">
														<td align=center id='<%= "tab" + index + "day15" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day15" %>' value="1">
														<td align=center id='<%= "tab" + index + "day15" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day15" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day16" %>' value="0">
														<td align=center id='<%= "tab" + index + "day16" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day16" %>' value="1">
														<td align=center id='<%= "tab" + index + "day16" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day16" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day17" %>' value="0">
														<td align=center id='<%= "tab" + index + "day17" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day17" %>' value="1">
														<td align=center id='<%= "tab" + index + "day17" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day17" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day18" %>' value="0">
														<td align=center id='<%= "tab" + index + "day18" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day18" %>' value="1">
														<td align=center id='<%= "tab" + index + "day18" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day18" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day19" %>' value="0">
														<td align=center id='<%= "tab" + index + "day19" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day19" %>' value="1">
														<td align=center id='<%= "tab" + index + "day19" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day19" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day20" %>' value="0">
														<td align=center id='<%= "tab" + index + "day20" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day20" %>' value="1">
														<td align=center id='<%= "tab" + index + "day20" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day20" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day21" %>' value="0">
														<td align=center id='<%= "tab" + index + "day21" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day21" %>' value="1">
														<td align=center id='<%= "tab" + index + "day21" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day21" />
														</td>
												</tr>

												<tr>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day22" %>' value="0">
														<td align=center id='<%= "tab" + index + "day22" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day22" %>' value="1">
														<td align=center id='<%= "tab" + index + "day22" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day22" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day23" %>' value="0">
														<td align=center id='<%= "tab" + index + "day23" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day23" %>' value="1">
														<td align=center id='<%= "tab" + index + "day23" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day23" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day24" %>' value="0">
														<td align=center id='<%= "tab" + index + "day24" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day24" %>' value="1">
														<td align=center id='<%= "tab" + index + "day24" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day24" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day25" %>' value="0">
														<td align=center id='<%= "tab" + index + "day25" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day25" %>' value="1">
														<td align=center id='<%= "tab" + index + "day25" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day25" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day26" %>' value="0">
														<td align=center id='<%= "tab" + index + "day26" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day26" %>' value="1">
														<td align=center id='<%= "tab" + index + "day26" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day26" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day27" %>' value="0">
														<td align=center id='<%= "tab" + index + "day27" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day27" %>' value="1">
														<td align=center id='<%= "tab" + index + "day27" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day27" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day28" %>' value="0">
														<td align=center id='<%= "tab" + index + "day28" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day28" %>' value="1">
														<td align=center id='<%= "tab" + index + "day28" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day28" />
														</td>
												</tr>

												<tr>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day29" %>' value="0">
														<td align=center id='<%= "tab" + index + "day29" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day29" %>' value="1">
														<td align=center id='<%= "tab" + index + "day29" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day29" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day30" %>' value="0">
														<td align=center id='<%= "tab" + index + "day30" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day30" %>' value="1">
														<td align=center id='<%= "tab" + index + "day30" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day30" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day31" %>' value="0">
														<td align=center id='<%= "tab" + index + "day31" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day31" %>' value="1">
														<td align=center id='<%= "tab" + index + "day31" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day31" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day32" %>' value="0">
														<td align=center id='<%= "tab" + index + "day32" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day32" %>' value="1">
														<td align=center id='<%= "tab" + index + "day32" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day32" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day33" %>' value="0">
														<td align=center id='<%= "tab" + index + "day33" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day33" %>' value="1">
														<td align=center id='<%= "tab" + index + "day33" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day33" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day34" %>' value="0">
														<td align=center id='<%= "tab" + index + "day34" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day34" %>' value="1">
														<td align=center id='<%= "tab" + index + "day34" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day34" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day35" %>' value="0">
														<td align=center id='<%= "tab" + index + "day35" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day35" %>' value="1">
														<td align=center id='<%= "tab" + index + "day35" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day35" />
														</td>
												</tr>

												<tr>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day36" %>' value="0">
														<td align=center id='<%= "tab" + index + "day36" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day36" %>' value="1">
														<td align=center id='<%= "tab" + index + "day36" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day36" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day37" %>' value="0">
														<td align=center id='<%= "tab" + index + "day37" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day37" %>' value="1">
														<td align=center id='<%= "tab" + index + "day37" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day37" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day38" %>' value="0">
														<td align=center id='<%= "tab" + index + "day38" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day38" %>' value="1">
														<td align=center id='<%= "tab" + index + "day38" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day38" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day39" %>' value="0">
														<td align=center id='<%= "tab" + index + "day39" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day39" %>' value="1">
														<td align=center id='<%= "tab" + index + "day39" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day39" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day40" %>' value="0">
														<td align=center id='<%= "tab" + index + "day40" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day40" %>' value="1">
														<td align=center id='<%= "tab" + index + "day40" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day40" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day41" %>' value="0">
														<td align=center id='<%= "tab" + index + "day41" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day41" %>' value="1">
														<td align=center id='<%= "tab" + index + "day41" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day41" />
														</td>

													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day42" %>' value="0">
														<td align=center id='<%= "tab" + index + "day42" %>' ondblclick="changeColor(this)">
													</nested:equal>
													<nested:equal name="calDetailForm" property='<%= "calHolidayList[" + index + "].day42" %>' value="1">
														<td align=center id='<%= "tab" + index + "day42" %>' ondblclick="changeColor(this)" bgcolor="#FFCCCC">
													</nested:equal>
															<nested:write property="day42" />
														</td>
												</tr>
											</table>
										</td>

										<td>
											<nested:hidden property="calDd" styleId='<%= "calDd" + index %>'></nested:hidden>
										</td>

										<nested:equal name="index" value="2">
										</tr>
										</nested:equal>

										<nested:equal name="index" value="5">
										</tr>
										</nested:equal>

										<nested:equal name="index" value="8">
										</tr>	
										</nested:equal>

										<nested:equal name="index" value="11">
										</tr>
										</nested:equal>
									</nested:iterate>
								</table>
							</nested:notEmpty>
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
									<nested:notEmpty property="calDetailList">
										<nested:equal property="updateAuthority" value="true">
											<td class="alignRight">
												<div id="cssButton">
													<a href="#" onclick="if(!changeYear()){return false;}else{if(!putConfirm()){return false;}else{return form_submit('op', 'update'); return false;}}" class="cssButton">
														&nbsp;&nbsp;登&nbsp;&nbsp;録&nbsp;&nbsp;
													</a>
												</div>
											</td>
		
											<td width="50">
												<br>
											</td>
										</nested:equal>
									</nested:notEmpty>

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

									<nested:notEmpty property="calDetailList">
										<nested:notEqual property="newFlg" value="true">
											<td class="alignLeft">
												<div id="cssButton">
													<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
														&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:notEqual>
	
										<nested:equal property="newFlg" value="true">
											<td class="alignCenter">
												<div id="cssButton">
													<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
														&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
													</a>
												</div>
											</td>
										</nested:equal>
									</nested:notEmpty>

									<nested:empty property="calDetailList">
										<td class="alignCenter">
											<div id="cssButton">
												<a href="#" onclick="if(!putDirtyConfirm()){return false;}else{return form_submit('op', 'back'); return false;}" class="cssButton">
													&nbsp;&nbsp;戻&nbsp;&nbsp;る&nbsp;&nbsp;
												</a>
											</div>
										</td>
									</nested:empty>
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

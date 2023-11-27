/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.dailyreport;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.dailyreport.DailyReport;
import com.asahikaseieng.dao.entity.dailyreportheader.DailyReportHeader;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxes;
import com.asahikaseieng.dao.nonentity.common.commonproc.CommonProcDao;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportListConditionForReport;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportDetailList;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportHeaderList;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportListPagerCondition;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 作業日報一覧 Actionクラス.
 * @author fml
 */
public final class DailyReportListAction extends AbstractSearchAction {

	private DailyReportListLogic dailyReportListLogic;

	private DailyReportListExcelDecorator dailyReportListExcelDecorator;
	
	private CommonProcDao commonProcDao;

	/**
	 * コンストラクタ.
	 */
	public DailyReportListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		DailyReportListForm frm = (DailyReportListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_DAILY_REPORT,
			Constants.TAB_ID_DAILY_REPORT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		/* 生産日＆生産ラインのリセット */
		// frm.setSrhDate("");
		// frm.setSrhLine("");
		frm.setTantoDiv("1"); // 担当区分
		frm.setNowpage(1); // ページ番号

		/* 作業日報ヘッダーのクリア */
		frm.setHeaderList(new DailyReportListForm.DailyReportHeader[0]);

		/* 作業日報明細のクリア */
		frm.setDetailList(new DailyReportListForm.DailyReportDetail[0]);

		frm.setDirtyFlg(new Boolean(false));

		/* 生産工場のリストをセット */
		setSrhLineCombobox(frm);
		// String[] tmp = frm.getSrhLineValues();
		// if (tmp.length > 0) {
		// frm.setSrhLine(tmp[0]);
		// }

		// 初期検索無し
		return mapping.findForward("success");
	}

	/**
	 * クリア処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		DailyReportListForm frm = (DailyReportListForm) form;

		/* クリア */
		frm.clear();
		frm.setTantoDiv("1"); // 担当区分
		frm.setNowpage(1); // ページ番号

		/* 生産工場のリストをセット */
		setSrhLineCombobox(frm);

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,	final HttpServletRequest request, final AbstractSearchForm form)throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		DailyReportListForm frm = (DailyReportListForm) form;
		String op = frm.getOp();
		if ("search".equals(op)) {
			frm.setSrhDate(frm.getSrhDateNew());
			frm.setSrhLine(frm.getSrhLineNew());
		} else {
			frm.setSrhDateNew(frm.getSrhDate());
			frm.setSrhLineNew(frm.getSrhLine());
		}

		// ページ番号を戻す
		frm.setNowpage(1);
		frm.setHeaderList(new DailyReportListForm.DailyReportHeader[0]);
		frm.setDetailList(new DailyReportListForm.DailyReportDetail[0]);
		frm.setDirtyFlg(new Boolean(false));

		/* 検索条件の入力チェック */
		if (StringUtils.isEmpty(frm.getSrhDate())) {
			saveError(request, "errors.daily.report.search.production.date");
			return mapping.findForward("success");
		} else if (StringUtils.isEmpty(frm.getSrhLine())) {
			saveError(request, "errors.daily.report.search.production.line");
			return mapping.findForward("success");
		}

		// 担当区分をチェック
		HttpSession session = request.getSession(false);
		if (StringUtils.equals("2", frm.getTantoDiv())) {
			// 協力会社のオートコンプリート用
			session.setAttribute("autocomplitePath","OrganizationForAutoComplete.do?op=searchOrganization");
		} else {
			// 社員のオートコンプリート用
			session.setAttribute("autocomplitePath","LoginForAutoComplete.do?op=searchLogin");
			frm.setTantoDiv("1");
		}

		/* 検索条件を取得 */
		DailyReportListPagerCondition condition = (DailyReportListPagerCondition) frm.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhDate(frm.getSrhDate());
		condition.setSrhLine(frm.getSrhLine());
		condition.setSrhTantoDiv(frm.getTantoDiv());

		/* 帳票(Excel)用に検索条件を保持 */
		DailyReportListConditionForReport reportCondition = new DailyReportListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 指図データと小計２を取得
		DailyReportDetailList[] dlist = dailyReportListLogic.getDetailListItemEntity(condition);
		// 指図データがない場合は終了。
		if (dlist.length == 0) {
			saveError(request, "errors.nodata");
			return mapping.findForward("success");
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);

		// 明細データ取得
		DailyReportDetailList[] dlist0 = dailyReportListLogic.getDetailListEntity(condition);
		// 指図番号と担当者CDで、作業時間のマップを作成
		HashMap<String, String[]> dtMap = new HashMap<String, String[]>();
		for (int i = 0; i < dlist0.length; i++) {
			String key = dlist0[i].getDirectionDiv() + "_"	+ dlist0[i].getDirectionNo() + "_" + dlist0[i].getTantoCd();
			if (!dtMap.containsKey(key)) {
				// 作業時間をフォーマット
				dlist0[i].setStrJobTime(checker.format("SAGYOJIKAN", dlist0[i].getJobTime()));
				String[] dt = new String[] {dlist0[i].getLine(), // 0:生産ライン
						dlist0[i].getDate(), // 1:製造日
						dlist0[i].getDirectionDiv(), // 2:指図区分
						dlist0[i].getDirectionNo(), // 3:指図番号
						dlist0[i].getTantoCd(), // 4:担当者CD
						dlist0[i].getStrJobTime(), // 5:作業時間
						dlist0[i].getJobTimeHhmm()};// 6:作業時間YYMM 2021/03/29 
				
				dtMap.put(key, dt);
			}
		}

		// 担当者の一覧を取得
		DailyReportHeaderList[] hlist = dailyReportListLogic.getHeaderListEntity(condition);
		/* 表示件数を制御 */
		int maxCnt = frm.getCorrectCol(hlist.length);

		BigDecimal[] hTotals = new BigDecimal[maxCnt];
		String[] hTotalsHhmm = new String[maxCnt];
		// ヘッダ情報をセット
		DailyReportListForm.DailyReportHeader[] headerList = new DailyReportListForm.DailyReportHeader[maxCnt];
		for (int i = 0; i < headerList.length; i++) {
			hTotals[i] = BigDecimal.ZERO; // 列合計を初期化
			headerList[i] = ((DailyReportListForm) form).new DailyReportHeader();
			headerList[i].setDelFlg("0"); // "1" 削除
			if (i < hlist.length) {
				headerList[i].setSeq(hlist[i].getSeq());
				headerList[i].setTantoCdKey(hlist[i].getTantoCd()); // 元の担当者CD
				headerList[i].setTantoCd(hlist[i].getTantoCd());
				headerList[i].setTantoNm(hlist[i].getTantoNm());
				headerList[i].setInsideTotal(checker.format("SAGYOJIKAN",hlist[i].getInTotal()));
				headerList[i].setOutsideTotal(checker.format("SAGYOJIKAN",hlist[i].getOutTotal()));

				headerList[i].setInsideTotalHhmm(hlist[i].getInTotalHhmm());
				headerList[i].setOutsideTotalHhmm(hlist[i].getOutTotalHhmm());
				
				// 就業時間 = 時間外 + 時間内
				BigDecimal tmp0 = BigDecimal.ZERO;
				BigDecimal tmp = AecNumberUtils.convertBigDecimal(headerList[i].getInsideTotal());
				tmp0 = tmp0.add(AecNumberUtils.convertNullToZero(tmp));
				tmp = AecNumberUtils.convertBigDecimal(headerList[i].getOutsideTotal());
				tmp0 = tmp0.add(AecNumberUtils.convertNullToZero(tmp));
				headerList[i].setEmployTime(checker.format("SAGYOJIKAN", tmp0));
				
				// 2021/03/30 終業時間をHHMMの形で計算
				String employTimeHhss = commonProcDao.getStringData("10", headerList[i].getInsideTotalHhmm(), headerList[i].getOutsideTotalHhmm(), null, null, null, null, null, null, null, null);
				headerList[i].setEmployTimeHhmm(employTimeHhss);
				
				// 間接時間
				tmp0 = BigDecimal.ZERO;
				headerList[i].setIndirectTime(checker.format("SAGYOJIKAN", tmp0));
				headerList[i].setIndirectTimeHhmm("00:00");
			}
		}
		// 担当区分
		String tantoDiv = frm.getTantoDiv();
		// 指図データをセット
		DailyReportListForm.DailyReportDetail[] detailList = new DailyReportListForm.DailyReportDetail[dlist.length];
		for (int i = 0; i < detailList.length; i++) {
			detailList[i] = ((DailyReportListForm) form).new DailyReportDetail();
			detailList[i].setDate(dlist[i].getDate()); // 製造日
			detailList[i].setLine(dlist[i].getLine()); // 製造ライン
			detailList[i].setDirectionDiv(dlist[i].getDirectionDiv()); // 指図区分
			detailList[i].setDirectionNo(dlist[i].getDirectionNo()); // 指図番号
			detailList[i].setItemNm(dlist[i].getItemNm()); // 品目名称

			// 範囲外データの表示変更用
			detailList[i].setAreaFlg(new Boolean(StringUtils.equals("1",dlist[i].getAreaFlg())));

			BigDecimal subtotal1 = BigDecimal.ZERO;
			String subtotal1Hhmm = "00:00";

			// 担当者毎の作業時間データの初期化
			DailyReportListForm.SagyoTime[] tantoList = new DailyReportListForm.SagyoTime[headerList.length];
			for (int n = 0; n < tantoList.length; n++) {
				tantoList[n] = ((DailyReportListForm) form).new SagyoTime();
				tantoList[n].setTantoDiv(tantoDiv);
				tantoList[n].setTantoCd(headerList[n].getTantoCd());
				tantoList[n].setTantoCdKey(headerList[n].getTantoCdKey());
				tantoList[n].setSeq(headerList[n].getSeq());

				// マップから作業時間を取り出す
				String key = dlist[i].getDirectionDiv() + "_"
						+ dlist[i].getDirectionNo() + "_"
						+ headerList[n].getTantoCd();
				if (dtMap.containsKey(key)) {
					String[] dt = (String[]) dtMap.get(key);
					tantoList[n].setJobTime(dt[5]); // 作業時間
					tantoList[n].setJobTimeHhmm(dt[6]); // 作業時間 2021/3/30 

					BigDecimal tmp = AecNumberUtils.convertBigDecimal(dt[5]);
					hTotals[n] = hTotals[n].add(AecNumberUtils.convertNullToZero(tmp)); // 列合計計算
					hTotalsHhmm[n] = commonProcDao.getStringData("10", hTotalsHhmm[n], dt[6], null, null, null, null, null, null, null, null);
					subtotal1 = subtotal1.add(AecNumberUtils.convertNullToZero(tmp)); // 小計計算
					subtotal1Hhmm = commonProcDao.getStringData("10", subtotal1Hhmm, dt[6], null, null, null, null, null, null, null, null);
					
				} else {
					tantoList[n].setJobTime("");
					tantoList[n].setJobTimeHhmm("");
				}
			}

			// 作業時間をセット
			detailList[i].setSagyoTimeList(tantoList);

			// 小計１計算
			detailList[i].setSubTotal1(checker.format("SAGYOJIKAN", subtotal1));
			detailList[i].setSubTotal1Hhmm(subtotal1Hhmm); 		// 2021/3/30

			// 小計２をセット
			BigDecimal subtotal2 = AecNumberUtils.convertNullToZero(dlist[i].getSubTotal());
			detailList[i].setSubTotal2(checker.format("SAGYOJIKAN", subtotal2));
			detailList[i].setSubTotal2Hhmm(dlist[i].getSubTotalHhmm());
				
			// 合計計算
			BigDecimal total = subtotal2.add(subtotal1);
			detailList[i].setTotal(checker.format("SAGYOJIKAN", total));
			detailList[i].setTotalHhmm(commonProcDao.getStringData("10", detailList[i].getSubTotal1Hhmm(), detailList[i].getSubTotal2Hhmm(), null, null, null, null, null, null, null, null));
		}
		// 間接時間を計算
		for (int i = 0; i < headerList.length; i++) {
			// 担当者なしの場合は表示なし
			if (StringUtils.isNotEmpty(headerList[i].getTantoCd())) {
				// 就業時間から、担当者の指図合計を引く。
				BigDecimal tmp = AecNumberUtils.convertBigDecimal(headerList[i].getEmployTime());
				tmp = AecNumberUtils.convertNullToZero(tmp);
				tmp = tmp.subtract(hTotals[i]);
				headerList[i].setIndirectTime(checker.format("SAGYOJIKAN", tmp));
				headerList[i].setIndirectTimeHhmm(commonProcDao.getStringData("11", headerList[i].getEmployTimeHhmm(), hTotalsHhmm[i], null, null, null, null, null, null, null, null));
			}
		}
		// ヘッダ情報をセット
		frm.setHeaderList(headerList);
		// 明細情報をセット
		frm.setDetailList(detailList);
		// 変更フラグをOFFにする
		frm.setDirtyFlg(new Boolean(false));
		return mapping.findForward("success");
	}
	/**
	 * 
	 * 数値チェック
	 * @param val
	 * @return
	 */
	public boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}
	/**
	 * 
	 *  時刻対応した項目に対してエラーメッセージを表示する
	 * @param request
	 * @param fieldName
	 */
	public boolean dispErrorMessageHhmm(final HttpServletRequest request,final String fieldName,final String data) throws Exception {
		
		if(data != null && !data.equals("")){
			
			// 日付チェック
			if(!isDateHhmm(data)){
				saveError(request, "errors.daily.report.datetime", fieldName);
				return false;
			}

			// マイナス時間のチェック
			if(data!=null && data.length() > 1){
				if(data.substring(0, 1).equals("-")){
					saveError(request, "errors.daily.report.datecheck", fieldName);
					return false;
				}
			}

			// 分を取得
			int len = data.length();
			String minute = data.substring(len - 2, len);
			String hour = data.substring(0, data.indexOf(":"));
			
			int hour_check = new BigDecimal(hour).intValue();
			
			// ゼロ以下かつ999以上の場合はエラーとする。
			if(hour_check < 0 || hour_check > 999){
				saveError(request, "errors.daily.report.datecheck", fieldName);
				
			}
			
			BigDecimal bMinute = AecNumberUtils.convertBigDecimalNullToZeroFromString(minute);
			
			if(bMinute.intValue() % 5 != 0){
				// 5分単位の入力とする
				saveError(request, "errors.daily.report.update.minute.five", fieldName);
				return false;
				
			}
			if(bMinute.intValue() >= 60){
				// 60分を超えた場合エラーとする
				saveError(request, "errors.daily.report.update.minute.sixty", fieldName);
				return false;
				
			}
			
		}
		return true;
	}
	
	
	/**
	 * ＤＢ更新前のチェック処理.
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public boolean chkUpdate(final ActionForm form,final HttpServletRequest request) throws Exception {

		DailyReportListForm frm = (DailyReportListForm) form;

		// 数値桁数チェック部品呼び出し
		String tantoDiv = frm.getTantoDiv();

		// フォームからヘッダ部のデータ一覧を取得
		DailyReportListForm.DailyReportHeader[] headerlist = frm.getHeaderList();
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < headerlist.length; i++) {
			String key = headerlist[i].getTantoCd();
			if (StringUtils.isNotEmpty(key)) {
				// 担当者コードは入っている。
				if (map.containsKey(key)) {
					// エラー発生
					saveError(request, "errors.daily.report.update.tantocd",
						key);
					return false;

				} else if (StringUtils.isEmpty(headerlist[i].getInsideTotalHhmm())) {
					saveError(request, "errors.required", "時間内");
					return false;

				} else if (StringUtils.isEmpty(headerlist[i].getOutsideTotalHhmm())) {
					saveError(request, "errors.required", "時間外");
					return false;
				}
				// 担当者のチェック
				java.io.Serializable bean = null;
				String strMsg = "";
				if ("1".equals(tantoDiv)) {
					// 社員チェック
					bean = dailyReportListLogic.getLoginEntity(key);
					strMsg = "社員(担当者)";
				} else if ("2".equals(tantoDiv)) {
					// 協力会社チェック
					bean = dailyReportListLogic.getOrganizationEntity(key);
					strMsg = "協力会社(担当者)";
				}
				if (bean == null && StringUtils.isNotEmpty(strMsg)) {
					/* エラーメッセージ */
					saveError(request, "errors.nodata.master", strMsg);
					return false;
				}
//2021/3/30 入力値変更により修正
//				// 時間内チェック
//				msg = check.checkDigitMessage("SAGYOJIKAN", " ", " ",headerlist[i].getInsideTotal(), "時間内");
//				if (msg != null) {
//					saveError(request, msg.getKey(), msg.getValues());
//					return false;
//				}
//				// 時間外チェック
//				msg = check.checkDigitMessage("SAGYOJIKAN", " ", " ",headerlist[i].getOutsideTotal(), "時間外");
//				if (msg != null) {
//					saveError(request, msg.getKey(), msg.getValues());
//					return false;
//				}
//				// 間接時間チェック
//				BigDecimal tmp = AecNumberUtils.convertBigDecimal(headerlist[i].getIndirectTime());
//				if (tmp == null) {
//					saveError(request, "errors.invalid", "間接時間");
//					return false;
//				} else if (tmp.compareTo(BigDecimal.ZERO) == -1) {
//					saveError(request, "errors.invalid", "間接時間");
//					return false;
//				}
				// 時間内チェック
				if(!dispErrorMessageHhmm(request,"時間内",headerlist[i].getInsideTotalHhmm())){
					return false;
				}
				// 時間外チェック
				if(!dispErrorMessageHhmm(request,"時間外",headerlist[i].getOutsideTotalHhmm())){
					return false;
				}
				// 間接時間のマイナス処理
				if(headerlist[i].getIndirectTimeHhmm()!=null && headerlist[i].getIndirectTimeHhmm().length() > 1){
					if(headerlist[i].getIndirectTimeHhmm().substring(0, 1).equals("-")){
						saveError(request, "errors.invalid", "間接時間");
						return false;
					}
				}
				//2021/3/30 入力値変更により修正
				
				// キーを登録
				map.put(key, key);

			} else if (StringUtils.isNotEmpty(headerlist[i].getInsideTotal())) {
				saveError(request, "errors.required", "担当者");
				return false;
			} else if (StringUtils.isNotEmpty(headerlist[i].getOutsideTotal())) {
				saveError(request, "errors.required", "担当者");
				return false;
			}
		}
		DailyReportListForm.DailyReportDetail[] detaillist = frm
				.getDetailList();
		for (int i = 0; i < detaillist.length; i++) {
			DailyReportListForm.SagyoTime[] sagyolist = detaillist[i]
					.getSagyoTimeList();
			for (int n = 0; n < sagyolist.length; n++) {
				String key = headerlist[n].getTantoCd();
				String chk = sagyolist[n].getJobTime();
				String chk2 = sagyolist[n].getJobTimeHhmm();
				if (StringUtils.isNotEmpty(chk) || StringUtils.isNotEmpty(chk2)) {
					if (!map.containsKey(key)) {
						// 登録されないデータに作業時間が入っている場合
						saveError(request, "errors.required", "担当者");
						return false;
					}
// 2021/03/30 
					// 時間内チェック
//					msg = check.checkDigitMessage("SAGYOJIKAN", " ", " ", chk,	"作業時間");
//					if (msg != null) {
//						saveError(request, msg.getKey(), msg.getValues());
//						return false;
//					}
					if(!dispErrorMessageHhmm(request,"作業時間",sagyolist[n].getJobTimeHhmm())){
						return false;
					}
				}
			}
		}

		return true;
	}
	/**
	 * 
	 * 日付かどうかチェックする
	 * @param date
	 * @return
	 */
	private boolean isDateHhmm(String date){
		
		int pos = date.indexOf(":");
		
		if(pos == 0){
			// :がない場合日付と判断しない
			return false;
		}
		
		String minute = date.substring(pos+1, date.length());
		
		pos = minute.indexOf(":");
		if(pos != -1){
			// :が複数ある場合エラー
			return false;
		}
		
		if(minute.length() > 2){
			// 分が2桁以上である場合エラー
			return false;
		}
		
		if(date == null || date.equals("")){
			// 空白の場合は、日付判断を行わない
			return true;
		}
		if(date.length()< 5){
			// hh:mmが最短であるが、それより短い場合、エラーとする
			return false;
		}
		
		// 数値チェック　数値ではない場合、エラーとする
		if(!isNumber(date.replace(":", ""))){
			return false;
		}
		
		return true;
	}


	/**
	 * ＤＢ更新処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}
		if (!chkUpdate(form, request)) {
			return mapping.getInputForward();
		}
		DailyReportListForm frm = (DailyReportListForm) form;

		// try {
		// フォームからヘッダ部のデータ一覧を取得
		DailyReportListForm.DailyReportHeader[] headerlist = frm.getHeaderList();
		String loginCd = getLoginInfo(request).getTantoCd();
		// 検索キー
		String[] keys1 = new String[] {frm.getSrhDate(), // 0:製造日
				frm.getSrhLine(), // 1:生産ライン
				frm.getTantoDiv()}; // 2:担当区分
		int seqNo = 1;
		for (int i = 0; i < headerlist.length; i++) {
			String key = headerlist[i].getTantoCd();
			String key1 = headerlist[i].getTantoCdKey();

			// 元の担当者をチェック
			if (StringUtils.isNotEmpty(key1)) {
				// 元の担当者あり
				if (StringUtils.equals(key1, key)) {
					// 担当者の変更はない。
					DailyReportHeader bean = updHeader(headerlist[i], keys1,loginCd);

					// SeqNo が違う場合はいったん削除して登録しなおす。
					if (!StringUtils.equals(String.valueOf(seqNo),headerlist[i].getSeq())) {
						dailyReportListLogic.deleteHeader(bean);
						bean.setSeq(new BigDecimal(seqNo));
						dailyReportListLogic.insertHeader(bean);
					} else {
						// データを更新。
						dailyReportListLogic.updateHeader(bean);
					}
					seqNo++;
				} else {
					headerlist[i].setTantoCd(key1);
					// 担当者が変更されている場合、元の担当者を削除します。
					// 元の担当者のデータを削除
					dailyReportListLogic.deleteHeader(delHeader(headerlist[i],keys1, loginCd));
					headerlist[i].setTantoCd(key);
					// このデータは、明細を一括削除してよい。
					// 新しい担当者があれば追加します。
					if (StringUtils.isNotEmpty(key)) {
						dailyReportListLogic.insertHeader(insHeader(headerlist[i], keys1, String.valueOf(seqNo++),	loginCd));
					}
				}
			} else {
				// 担当者があれば追加します。
				if (StringUtils.isNotEmpty(key)) {
					dailyReportListLogic.insertHeader(insHeader(headerlist[i],
						keys1, String.valueOf(seqNo++), loginCd));
				}
			}
		}

		/* DailyReportDetailListクラスの呼出し */
		DailyReportListForm.DailyReportDetail[] dlist = frm.getDetailList();

		for (int i = 0; i < dlist.length; i++) {
			// 検索キー
			String[] keys2 = new String[] {frm.getSrhDate(), // 0:製造日
					frm.getSrhLine(), // 1:生産ライン
					frm.getTantoDiv(), // 2:担当区分
					dlist[i].getDirectionNo()}; // 3:指図番号

			DailyReportListForm.SagyoTime[] slist = dlist[i].getSagyoTimeList();
			for (int n = 0; n < slist.length; n++) {
				String key = headerlist[n].getTantoCd();
				String key1 = headerlist[n].getTantoCdKey();
				String key2 = slist[n].getJobTime();
				String key2Hhmm = slist[n].getJobTimeHhmm();
				// 作業時間クラスへ担当CDをセット
				slist[n].setTantoCd(key);

				// 元の担当者をチェック
				if (StringUtils.isNotEmpty(key1)) {
					// 元の担当者あり
					if (StringUtils.equals(key1, key)) {
						// 担当者の変更はない。
						// データを更新。
						DailyReport bean = updDetail(slist[n], keys2, loginCd);
						if (StringUtils.isNotEmpty(key2) || StringUtils.isNotEmpty(key2Hhmm)) {
							if (bean != null) {
//								bean.setJobTime(AecNumberUtils.convertBigDecimal(key2));
								bean.setJobTimeHhmm(key2Hhmm);
								//20210510 下二桁まで修正 S.Fujimaki
								//bean.setJobTime(commonProcDao.getNumericData("10", bean.getJobTimeHhmm(), null, null, null, null, null, null, null, null, null));
								bean.setJobTime(commonProcDao.getNumericData("10", bean.getJobTimeHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
								//20210510 下二桁まで修正 S.Fujimaki
								dailyReportListLogic.updateDetail(bean);
							} else {
								bean = insDetail(slist[n], keys2, loginCd);
								dailyReportListLogic.insertDetail(bean);
							}
						} else {
							if (bean != null) {
								// 担当者データの削除
								dailyReportListLogic.deleteDetail(bean);
							}
						}

					} else {
						// 担当者が変更されている場合
						// 元の担当者のデータを削除
						// 作業時間クラスへ元の担当CDをセット
						slist[n].setTantoCd(key1);
						DailyReport bean = delDetail(slist[n], keys2, loginCd);
						if (bean != null) {
							dailyReportListLogic.deleteDetail(bean);
						}

						// 作業時間クラスへ担当CDをセット
						slist[n].setTantoCd(key);
						// 作業時間があれば新規追加
						if (StringUtils.isNotEmpty(key2)) {
							// 新しい担当者があれば追加
							if (StringUtils.isNotEmpty(key)) {
								dailyReportListLogic.insertDetail(insDetail(slist[n], keys2, loginCd));
							}
						}
					}
				} else {
					// 作業時間があれば追加。
					if (StringUtils.isNotEmpty(key2) || StringUtils.isNotEmpty(key2Hhmm)) {
						// 担当者があれば追加。
						if (StringUtils.isNotEmpty(key)) {
							// データを新規追加。
							dailyReportListLogic.insertDetail(insDetail(slist[n], keys2, loginCd));
						}
					}
				}
			}
		}
		//20210510 add
		/* DirectionHeader テーブルの更新 */
		// 作業時間の合計を計算
		String tmp = AecDateUtils.dateFormat(AecDateUtils.getTimestampYmdFormat(frm.getSrhDate()), "yyyy/MM/dd");
		dailyReportListLogic.update(tmp, frm.getSrhLine());

		// } catch (NoDataException e) {
		// saveError(request, "errors.nodata.deleted");
		// return mapping.getInputForward();
		// return mapping.findForward("search");
		// }
		/* メッセージ */
		saveMessage(request, "message.complete.regist");
		return mapping.findForward("reSearch");
	}

	/**
	 * ＤＢ削除前のチェック処理.
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public boolean chkDelete(final ActionForm form,
			final HttpServletRequest request) throws Exception {
		DailyReportListForm frm = (DailyReportListForm) form;

		// 数値桁数チェック部品呼び出し
		// CheckDigitUtilsLogic check =
		// CheckDigitUtil.getCheckDigitUtils(request);
		// ActionMessage msg = null;

		// 削除するデータのチェック
		boolean chk = false;
		DailyReportListForm.DailyReportHeader[] headerlist = frm
				.getHeaderList();
		for (int i = 0; i < headerlist.length; i++) {
			// 削除フラグが "1" の時
			if (StringUtils.equals("1", headerlist[i].getDelFlg())) {
				// 元の担当者コード
				String key = headerlist[i].getTantoCdKey();
				if (StringUtils.isNotEmpty(key)) {
					chk = true;
					break;
				}
			}
		}
		if (!chk) {
			// 削除対象のデータがない
			saveError(request, "errors.daily.report.delete.nodata");
			return false;
		}

		return true;
	}

	/**
	 * 削除処理を実行します。
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}
		if (!chkDelete(form, request)) {
			return mapping.getInputForward();
		}
		DailyReportListForm frm = (DailyReportListForm) form;
		String loginCd = getLoginInfo(request).getTantoCd();

		// try {
		// 検索キー
		String[] keys1 = new String[] {frm.getSrhDate(), // 0:製造日
				frm.getSrhLine(), // 1:生産ライン
				frm.getTantoDiv()}; // 2:担当区分

		DailyReportListForm.DailyReportHeader[] headerlist = frm
				.getHeaderList();
		for (int i = 0; i < headerlist.length; i++) {
			// 削除フラグが "1" の時
			if (StringUtils.equals("1", headerlist[i].getDelFlg())) {
				// 元の担当者コード
				String key = headerlist[i].getTantoCdKey();
				// 元の担当者コードが入っているものだけ
				if (StringUtils.isNotEmpty(key)) {
					/* 明細データを先に削除する */
					DailyReportDetailList bean1 = delDetail(frm, keys1, loginCd);
					bean1.setTantoCd(key); // 担当者コードをセット
					/* 削除処理を実行 */
					dailyReportListLogic.deleteDetail(bean1);

					/* ヘッダデータを削除する */
					headerlist[i].setTantoCd(key); // 削除するのは元の担当者
					DailyReportHeader bean = delHeader(headerlist[i], keys1,
						loginCd);
					if (bean != null) {
						dailyReportListLogic.deleteHeader(bean);
					}
				}
			}
		}

		/* DirectionHeader テーブルの更新 */
		// 作業時間の合計を計算
		String tmp = AecDateUtils.dateFormat(AecDateUtils
				.getTimestampYmdFormat(frm.getSrhDate()), "yyyy/MM/dd");
		dailyReportListLogic.update(tmp, frm.getSrhLine());

		// } catch (NoDataException e) {
		// saveError(request, "errors.nodata.deleted");
		// return mapping.getInputForward();
		// return mapping.findForward("search");
		// }

		/* メッセージ */
		saveMessage(request, "message.complete.delete");
		return mapping.findForward("reSearch");
	}

	/**
	 * 更新処理用のDailyReportHeaderを作成する.
	 * @param frm 画面データ
	 * @param keys 検索キー 0:製造日 1:製造ライン 2:担当区分
	 * @param loginCd ログイン担当者コード
	 * @return DailyReportHeader
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private DailyReportHeader updHeader(
			final DailyReportListForm.DailyReportHeader frm,
			final String[] keys, final String loginCd)
			throws IllegalAccessException, InvocationTargetException {

		DailyReportHeader newBean = new DailyReportHeader();

		try {
			newBean = dailyReportListLogic.getEntityHeader(keys[1],
				AecDateUtils.getTimestampYmdFormat(keys[0]), AecNumberUtils
						.convertBigDecimal(keys[2]), frm.getTantoCd(),
				AecNumberUtils.convertBigDecimal(frm.getSeq()));
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		// 2020/3/30 画面で入力したYYMMを数値に変換
		//newBean.setInsideTotal(commonProcDao.getNumericData("10", newBean.getInsideTotalHhmm(), null, null, null, null, null, null, null, null, null));
		//newBean.setOutsideTotal(commonProcDao.getNumericData("10", newBean.getOutsideTotalHhmm(), null, null, null, null, null, null, null, null, null));
		//newBean.setEmployTime(commonProcDao.getNumericData("10", newBean.getEmployTimeHhmm(), null, null, null, null, null, null, null, null, null));
		//newBean.setIndirectTime(commonProcDao.getNumericData("10", newBean.getIndirectTimeHhmm(), null, null, null, null, null, null, null, null, null));
		//20210510 丸目処理をする。
		newBean.setInsideTotal(commonProcDao.getNumericData("10", newBean.getInsideTotalHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		newBean.setOutsideTotal(commonProcDao.getNumericData("10", newBean.getOutsideTotalHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		newBean.setEmployTime(commonProcDao.getNumericData("10", newBean.getEmployTimeHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		newBean.setIndirectTime(commonProcDao.getNumericData("10", newBean.getIndirectTimeHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		
		// TimeStamp
		newBean.setUpdatorCd(loginCd);
		return newBean;
	}

	/**
	 * 追加処理用のDailyReportHeaderを作成する.
	 * @param frm 画面データ
	 * @param keys 検索キー 0:製造日 1:製造ライン 2:担当区分
	 * @param loginCd ログイン担当者コード
	 * @return DailyReportHeader
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private DailyReportHeader insHeader(
			final DailyReportListForm.DailyReportHeader frm,
			final String[] keys, final String seqNo, final String loginCd)
			throws IllegalAccessException, InvocationTargetException {

		DailyReportHeader newBean = new DailyReportHeader();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		// TimeStamp
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(loginCd);
		newBean.setUpdatorCd(loginCd);
		//
		newBean.setProductionDate(AecDateUtils.getTimestampYmdFormat(keys[0]));
		newBean.setProductionLine(keys[1]);
		newBean.setTantoDivision(AecNumberUtils.convertBigDecimal(keys[2]));
		newBean.setSeq(AecNumberUtils.convertBigDecimal(seqNo));

		/* コピーしきれなかった分は手で */

		//20210510 下二桁まで修正 S.Fujimaki
		// 2020/3/30 画面で入力したYYMMを数値に変換
		//newBean.setInsideTotal(commonProcDao.getNumericData("10", newBean.getInsideTotalHhmm(), null, null, null, null, null, null, null, null, null));
		//newBean.setOutsideTotal(commonProcDao.getNumericData("10", newBean.getOutsideTotalHhmm(), null, null, null, null, null, null, null, null, null));
		//newBean.setEmployTime(commonProcDao.getNumericData("10", newBean.getEmployTimeHhmm(), null, null, null, null, null, null, null, null, null));
		//newBean.setIndirectTime(commonProcDao.getNumericData("10", newBean.getIndirectTimeHhmm(), null, null, null, null, null, null, null, null, null));
		newBean.setInsideTotal(commonProcDao.getNumericData("10", newBean.getInsideTotalHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		newBean.setOutsideTotal(commonProcDao.getNumericData("10", newBean.getOutsideTotalHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		newBean.setEmployTime(commonProcDao.getNumericData("10", newBean.getEmployTimeHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		newBean.setIndirectTime(commonProcDao.getNumericData("10", newBean.getIndirectTimeHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		//20210510 下二桁まで修正 S.Fujimaki
		return newBean;
	}

	/**
	 * 削除処理用のDailyReportHeaderを作成する.
	 * @param frm 画面データ
	 * @param keys 検索キー 0:製造日 1:製造ライン 2:担当区分
	 * @param loginCd ログイン担当者コード
	 * @return DailyReportHeader
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private DailyReportHeader delHeader(
			final DailyReportListForm.DailyReportHeader frm,
			final String[] keys, final String loginCd)
			throws IllegalAccessException, InvocationTargetException {
		// throws Exception {

		DailyReportHeader newBean = new DailyReportHeader();

		try {
			newBean = dailyReportListLogic.getEntityHeader(keys[1],
				AecDateUtils.getTimestampYmdFormat(keys[0]), AecNumberUtils
						.convertBigDecimal(keys[2]), frm.getTantoCd(),
				AecNumberUtils.convertBigDecimal(frm.getSeq()));
		} catch (NoDataException e) {
			return null;
		}
		/* 値を更新用Beanにコピる */
		// IgnoreCaseBeanUtils.copyProperties(newBean, frm);
		/* コピーしきれなかった分は手で */
		// TimeStamp
		// newBean.setUpdatorCd(loginCd);
		/*
		 * newBean.setTantoCd(frm.getTantoCdKey()); // 元の担当者コード
		 * 
		 * newBean.setProductionDate(AecDateUtils.getTimestampYmdFormat(keys[0])); //
		 * 製造日 newBean.setProductionLine(keys[1]); // 製造ライン
		 * newBean.setTantoDivision(AecNumberUtils.convertBigDecimal(keys[2])); //
		 * 担当区分
		 */
		return newBean;
	}

	/**
	 * 更新処理用のDailyReportを作成する.
	 * @param frm 画面データ
	 * @param keys 検索キー 0:製造日 1:製造ライン 2:担当区分 3:指図番号
	 * @param loginCd ログイン担当者コード
	 * @return DailyReport
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private DailyReport updDetail(final DailyReportListForm.SagyoTime frm,
			final String[] keys, final String loginCd)
			throws IllegalAccessException, InvocationTargetException {

		DailyReport newBean = new DailyReport();

		try {
			newBean = dailyReportListLogic.getEntityDetail(keys[1],
				AecDateUtils.getTimestampYmdFormat(keys[0]), AecNumberUtils
						.convertBigDecimal(keys[2]), frm.getTantoCd(), keys[3]);
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		// TimeStamp
		//20210510 下二桁まで修正 S.Fujimaki
		newBean.setJobTime(commonProcDao.getNumericData("10", newBean.getJobTimeHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		//newBean.setJobTime(commonProcDao.getNumericData("10", newBean.getJobTimeHhmm(), null, null, null, null, null, null, null, null, null));
		//20210510 下二桁まで修正 S.Fujimaki
		newBean.setUpdatorCd(loginCd);

		return newBean;
	}

	/**
	 * 追加処理用のDailyReportを作成する.
	 * @param frm 画面データ
	 * @param keys 検索キー 0:製造日 1:製造ライン 2:担当区分 3:指図番号
	 * @param loginCd ログイン担当者コード
	 * @return DailyReportDetai
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private DailyReport insDetail(final DailyReportListForm.SagyoTime frm,
			final String[] keys, final String loginCd)
			throws IllegalAccessException, InvocationTargetException {

		DailyReport newBean = new DailyReport();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		// TimeStamp
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(loginCd);
		newBean.setUpdatorCd(loginCd);

		newBean.setProductionDate(AecDateUtils.getTimestampYmdFormat(keys[0]));
		newBean.setProductionLine(keys[1]);
		newBean.setTantoDivision(AecNumberUtils.convertBigDecimal(keys[2]));
		newBean.setDirectionNo(keys[3]);
		newBean.setTantoCd(frm.getTantoCd());
		//20210510 下二桁まで修正 S.Fujimaki
		//newBean.setJobTime(commonProcDao.getNumericData("10", newBean.getJobTimeHhmm(), null, null, null, null, null, null, null, null, null));
		newBean.setJobTime(commonProcDao.getNumericData("10", newBean.getJobTimeHhmm(), null, null, null, null, null, null, null, null, null).setScale(2, BigDecimal.ROUND_DOWN));
		//20210510 下二桁まで修正 S.Fujimaki
		return newBean;
	}

	/**
	 * 削除処理用のDailyReportを作成する.
	 * @param frm 画面データ
	 * @param keys 検索キー 0:製造日 1:製造ライン 2:担当区分 3:指図番号
	 * @param loginCd ログイン担当者コード
	 * @return DailyReport
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private DailyReport delDetail(final DailyReportListForm.SagyoTime frm,
			final String[] keys, final String loginCd)
			throws IllegalAccessException, InvocationTargetException {

		DailyReport newBean = new DailyReport();
		try {
			newBean = dailyReportListLogic.getEntityDetail(keys[1],
				AecDateUtils.getTimestampYmdFormat(keys[0]), AecNumberUtils
						.convertBigDecimal(keys[2]), frm.getTantoCd(), keys[3]);
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		// IgnoreCaseBeanUtils.copyProperties(newBean, frm);
		return newBean;
	}

	/**
	 * 削除処理用のDailyReportを作成する.
	 * @param frm 画面データ
	 * @param keys 検索キー 0:製造日 1:製造ライン 2:担当区分
	 * @param loginCd ログイン担当者コード
	 * @return DailyReport
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private DailyReportDetailList delDetail(final DailyReportListForm frm,
			final String[] keys, final String loginCd)
			throws IllegalAccessException, InvocationTargetException {

		DailyReportDetailList newBean = new DailyReportDetailList();
		// try {
		// newBean = dailyReportListLogic.getEntityDetail(
		// keys[1],
		// AecDateUtils.getTimestampYmdFormat(keys[0]),
		// AecNumberUtils.convertBigDecimal(keys[2]),
		// tantoCd,
		// keys[3]);
		// } catch (NoDataException e) {
		// return null;
		// }

		/* 値を更新用Beanにコピる */
		// IgnoreCaseBeanUtils.copyProperties(newBean, frm);
		newBean.setLine(keys[1]);
		newBean.setDate(keys[0]);
		newBean.setTantoDiv(keys[2]);
		// newBean.setTantoCd(keys[3]);

		return newBean;
	}

	/**
	 * 生産工場リスト取得
	 * @param frm 画面データ
	 */
	private void setSrhLineCombobox(final DailyReportListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<LineListForComboboxes> list = dailyReportListLogic
				.getSrhLineList();

		String[] values = new String[list.size()];
		String[] labels = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			values[i] = list.get(i).getProductionLine();
			labels[i] = list.get(i).getProductionLineName();
		}

		frm.setSrhLineLabels(labels);
		frm.setSrhLineValues(values);
	}
	/**
	 * 検索ボタンが押された時の処理用<br>
	 * ページ番号はURL変数"page"より取得すること<br>
	 * "page"が存在しない場合はpage=1として処理すること.
	 * @param mapping ActionMapping
	 * @param form AbstractSearchForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public ActionForward reload(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("reload.");
		}

		return mapping.findForward("success");
	}

	/**
	 * 前へ処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward prev(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("prev.");
		}
		DailyReportListForm frm = (DailyReportListForm) form;

		// ページ番号を減算
		int nowpage = frm.getNowpage();
		if (nowpage > 1) {
			nowpage--;
		}
		frm.setNowpage(nowpage);
		return mapping.findForward("success");
	}

	/**
	 * 次へ処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward next(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("next.");
		}
		DailyReportListForm frm = (DailyReportListForm) form;
		
		// 最大ページ数
		int maxpage = frm.getMaxPage();
		// ページ番号を加算
		int nowpage = frm.getNowpage();
		if (nowpage < maxpage) {
			nowpage++;
		} else {
			nowpage++;

			// 担当区分
			String tantoDiv = frm.getTantoDiv();
			DailyReportListForm.DailyReportHeader[] headerlist0 = frm
					.getHeaderList();
			// 追加データ登録用
			DailyReportListForm.DailyReportHeader[] headerlist = new DailyReportListForm.DailyReportHeader[headerlist0.length
					+ DailyReportListForm.PAGE_COL];
			for (int i = 0; i < headerlist.length; i++) {
				headerlist[i] = ((DailyReportListForm) form).new DailyReportHeader();
				if (i < headerlist0.length) {
					headerlist[i] = (DailyReportListForm.DailyReportHeader) headerlist0[i]
							.clone();
				} else {
					headerlist[i].setDelFlg("0");
				}
			}
			//2022/06/13 画面更新フラグの強制セットを抑止　S.Fujimaki　
			frm.setNextHeaderList(headerlist);

			DailyReportListForm.DailyReportDetail[] detaillist = frm
					.getDetailList();
			for (int i = 0; i < detaillist.length; i++) {
				DailyReportListForm.SagyoTime[] sagyotimelist0 = detaillist[i]
						.getSagyoTimeList();
				DailyReportListForm.SagyoTime[] sagyotimelist = new DailyReportListForm.SagyoTime[headerlist.length];
				for (int n = 0; n < sagyotimelist.length; n++) {
					sagyotimelist[n] = ((DailyReportListForm) form).new SagyoTime();
					if (n < sagyotimelist0.length) {
						sagyotimelist[n] = (DailyReportListForm.SagyoTime) sagyotimelist0[n]
								.clone();
					} else {
						sagyotimelist[n].setTantoDiv(tantoDiv);
					}
				}
				detaillist[i].setSagyoTimeList(sagyotimelist);
			}
			//2022/06/13 画面更新フラグの強制セットを抑止　S.Fujimaki
			frm.setNextDetailList(detaillist);
		}
		frm.setNowpage(nowpage);

		return mapping.findForward("success");
	}

	/**
	 * 帳票処理(検索画面の帳票(Excel)ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		DailyReportListForm frm = (DailyReportListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = dailyReportListExcelDecorator.createReport(frm
				.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);
		return mapping.findForward("success");

	}


	/* -------------------- setter -------------------- */
	/**
	 * dailyReportListLogicを設定します。
	 * @param dailyReportListLogic dailyReportListLogic
	 */
	public void setDailyReportListLogic(
			final DailyReportListLogic dailyReportListLogic) {
		this.dailyReportListLogic = dailyReportListLogic;
	}

	/**
	 * dailyReportListExcelDecoratorを設定します。
	 * @param dailyReportListExcelDecorator dailyReportListExcelDecorator
	 */
	public void setDailyReportListExcelDecorator(
			final DailyReportListExcelDecorator dailyReportListExcelDecorator) {
		this.dailyReportListExcelDecorator = dailyReportListExcelDecorator;
	}

	/**
	 * commonProcDaoを設定します。
	 * @param commonProcDao commonProcDao
	 */
	public void setCommonProcDao(CommonProcDao commonProcDao) {
		this.commonProcDao = commonProcDao;
	}
}

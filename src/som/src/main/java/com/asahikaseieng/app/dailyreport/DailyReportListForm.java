/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.dailyreport;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportListConditionForReport;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 作業日報一覧 Formクラス.
 * @author fml
 */
public final class DailyReportListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);
		PAGE_ROW = Integer.parseInt(rb.getString("linage.dailyreport"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.dailyreport"));
	}

	/** 最大列データ数 */
	public static final int PAGE_COL = 5;

	/* 次のコマンドを入れる箱 */
	private String nextCmd;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 現在のページ番号 */
	private int nowpage;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	private DailyReportListConditionForReport reportCondition;

	//
	// 検索条件
	//
	/* 製造日 */
	private String srhDate;

	/* 生産工場CD */
	private String srhLine;

	/* 生産工場リスト */
	private String[] srhLineValues;

	private String[] srhLineLabels;

	/* 担当区分 */
	private String tantoDiv;

	/* 検索した時の製造日 */
	private String srhDateNew;

	/* 検索した時の生産工場CD */
	private String srhLineNew;

	/* 作業日報ヘッダーリスト */
	private DailyReportListForm.DailyReportHeader[] headerList;

	/* 作業日報明細リスト */
	private DailyReportListForm.DailyReportDetail[] detailList;

	/* 実行中フラグ */
	private boolean isExecute;

	/**
	 * コンストラクタ.
	 */
	public DailyReportListForm() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return DailyReportListPagerCondition.class;
	}

	/**
	 * 社員(協力会社)の列数の PAGE_COL の倍数に補正します。
	 * @param cols int
	 * @return 最大列数 int
	 */
	public int getCorrectCol(final int cols) {
		int maxCnt = DailyReportListForm.PAGE_COL;
		if (cols > 0) {
			if ((cols % DailyReportListForm.PAGE_COL) > 0) {
				maxCnt = cols + DailyReportListForm.PAGE_COL- (cols % DailyReportListForm.PAGE_COL);
			} else {
				maxCnt = cols;
			}
		}
		return maxCnt;
	}

	/**
	 * 最大ページ数を計算します。
	 * @return 最大ページ数 int
	 */
	public int getMaxPage() {
		// PAGE_COLで割り切れるはず
		return this.headerList.length / DailyReportListForm.PAGE_COL;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			/* 初期化 */
			clear();
		}
		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,final HttpServletRequest request) {
		ActionErrors errors = null;
		String op = this.getOp();
		if ("/DailyReportList".equals(mapping.getPath())) {
			setNextCmd("");
		}
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		String line = "";
		int pagedata = (nowpage - 1) * DailyReportListForm.PAGE_COL;
		String chk = request.getParameter("headerListPage[0].tantoCd");
		BigDecimal tmp = null;
		if (chk != null) {
			// ヘッダー情報の取得
			DailyReportListForm.DailyReportHeader[] headerIn = getHeaderList();
			for (int i = 0; i < DailyReportListForm.PAGE_COL; i++) {
				int p = pagedata + i;
				line = "headerListPage[" + String.valueOf(i) + "].";
				headerIn[p].setDelFlg(getParameterChk(request, line + "delFlg",	"1", "0"));
				headerIn[p].setTantoCd(getParameter(request, line + "tantoCd"));
				headerIn[p].setTantoNm(getParameter(request, line + "tantoNm"));

				// 時間内
				chk = getParameter(request, line + "insideTotal");
				tmp = AecNumberUtils.convertBigDecimal(chk);
				if (tmp != null) {
					headerIn[p].setInsideTotal(checker.format("SAGYOJIKAN", tmp));
				} else {
					headerIn[p].setInsideTotal(chk); // 変換できない値もセットする。
				}
				
				chk = getParameter(request, line + "insideTotalHhmm");
				headerIn[p].setInsideTotalHhmm(chk); // 変換できない値もセットする。
				
				// 時間外
				chk = getParameter(request, line + "outsideTotal");
				tmp = AecNumberUtils.convertBigDecimal(chk);
				if (tmp != null) {
					headerIn[p].setOutsideTotal(checker.format("SAGYOJIKAN",tmp));
				} else {
					headerIn[p].setOutsideTotal(chk); // 変換できない値もセットする。
				}
				chk = getParameter(request, line + "outsideTotalHhmm");
				headerIn[p].setOutsideTotalHhmm(chk); // 変換できない値もセットする。

				// 担当者なしの場合は表示なし
				if (StringUtils.isEmpty(headerIn[p].getTantoCd())) {
					headerIn[p].setEmployTime("");
					headerIn[p].setIndirectTime("");
				} else {
					// 就業時間 = 時間内＋時間外
					BigDecimal tmp0 = BigDecimal.ZERO;
					String tmp0Hhmm = "00:00";
					tmp = AecNumberUtils.convertBigDecimal(headerIn[p].getInsideTotal());
					tmp0 = tmp0.add(AecNumberUtils.convertNullToZero(tmp));
					tmp = AecNumberUtils.convertBigDecimal(headerIn[p].getOutsideTotal());
					tmp0 = tmp0.add(AecNumberUtils.convertNullToZero(tmp));
					headerIn[p].setEmployTime(checker.format("SAGYOJIKAN", tmp0));
					
					
					// ※終業時間を計算 = 時間内＋時間外
					tmp0Hhmm = addDateHhmm("ADD",tmp0Hhmm,headerIn[p].getInsideTotalHhmm());
					tmp0Hhmm = addDateHhmm("ADD",tmp0Hhmm,headerIn[p].getOutsideTotalHhmm());
					headerIn[p].setEmployTimeHhmm(tmp0Hhmm);
					
//					// 間接時間
//					tmp0 = BigDecimal.ZERO;
//					headerIn[p].setIndirectTime(checker.format("SAGYOJIKAN",tmp0));
//					headerIn[p].setIndirectTimeHhmm("00:00");
				}

			}
			// setHeaderList(headerIn);

			// 列合計用
			BigDecimal[] hTotals = new BigDecimal[DailyReportListForm.PAGE_COL];
			String[] hTotalsHhmm = new String[DailyReportListForm.PAGE_COL];
			for (int i = 0; i < hTotals.length; i++) {
				hTotals[i] = BigDecimal.ZERO;
			}
			// 明細情報の取得
			DailyReportListForm.DailyReportDetail[] detailIn = getDetailList();
			for (int i = 0; i < detailIn.length; i++) {
				// 作業時間情報の取得
				DailyReportListForm.SagyoTime[] sagyoIn = detailIn[i].getSagyoTimeList();
				for (int n = 0; n < hTotals.length; n++) {
					int p = pagedata + n;
					tmp = AecNumberUtils.convertBigDecimal(sagyoIn[p].getJobTime());
					hTotals[n] = hTotals[n].add(AecNumberUtils.convertNullToZero(tmp));
					
					if(hTotalsHhmm[n] == null || hTotalsHhmm[n].isEmpty()){
						hTotalsHhmm[n] = "00:00";
					}
					
					hTotalsHhmm[n] = addDateHhmm("ADD",hTotalsHhmm[n],sagyoIn[p].getJobTimeHhmm());
				}

				// 小計計算
				BigDecimal tmp0 = BigDecimal.ZERO;
				String tmp0Hhmm = "00:00";
				for (int n = 0; n < sagyoIn.length; n++) {
					// ヘッダの担当ユーザが入力されている場合のみ計算。
					if (StringUtils.isNotEmpty(headerIn[n].getTantoCd())) {
						tmp = AecNumberUtils.convertBigDecimal(sagyoIn[n].getJobTime());
						tmp0 = tmp0.add(AecNumberUtils.convertNullToZero(tmp));
						tmp0Hhmm = addDateHhmm("ADD",tmp0Hhmm,sagyoIn[n].getJobTimeHhmm());
					}
				}
				detailIn[i].setSubTotal1(checker.format("SAGYOJIKAN", tmp0));
				detailIn[i].setSubTotal1Hhmm(tmp0Hhmm);

				// 合計計算
				tmp = AecNumberUtils.convertBigDecimal(detailIn[i].getSubTotal2());
				tmp0 = tmp0.add(AecNumberUtils.convertNullToZero(tmp));
				detailIn[i].setTotal(checker.format("SAGYOJIKAN", tmp0));
				tmp0Hhmm = addDateHhmm("ADD",tmp0Hhmm,detailIn[i].getSubTotal2Hhmm());
				detailIn[i].setTotalHhmm(tmp0Hhmm);
				
			}
			// 間接時間を計算
			for (int i = 0; i < hTotals.length; i++) {
				int p = pagedata + i;
				// 担当者なしの場合は表示なし
				if (StringUtils.isEmpty(headerIn[p].getTantoCd())) {
					headerIn[p].setIndirectTime("");
				} else {
					tmp = AecNumberUtils.convertBigDecimal(headerIn[p].getEmployTime());
					tmp = AecNumberUtils.convertNullToZero(tmp);
					tmp = tmp.subtract(hTotals[i]);
					headerIn[p].setIndirectTime(checker.format("SAGYOJIKAN",tmp));
					
//					// ※間接時間を計算する予定
//					chk = getParameter(request, line + "indirectTimeHhmm");
//					headerIn[p].setIndirectTimeHhmm(chk);
					headerIn[p].setIndirectTimeHhmm(addDateHhmm("SUBTRACT",headerIn[p].getEmployTimeHhmm(),hTotalsHhmm[i]));
				}
			}
			// ヘッダ情報をセット
			setHeaderList(headerIn);
			// 明細情報をセット
			//2022/06/13 画面更新フラグの強制セットを抑止　　S.Fujimaki
			setNextDetailList(detailIn);
		}
		chk = request.getParameter("detailListPage[0].sagyoTimeList[0].jobTime");
		String chk2 = request.getParameter("detailListPage[0].sagyoTimeList[0].jobTimeHhmm");
		if (chk != null || chk2 != null) {
			// 明細情報の取得
			DailyReportListForm.DailyReportDetail[] detailIn = getDetailList();
			for (int i = 0; i < detailIn.length; i++) {
				line = "detailListPage[" + String.valueOf(i) + "].";
				// 作業時間情報の取得
				DailyReportListForm.SagyoTime[] sagyoIn = detailIn[i].getSagyoTimeList();
				for (int n = 0; n < DailyReportListForm.PAGE_COL; n++) {
					int p = pagedata + n;
					String line1 = line + "sagyoTimeList[" + String.valueOf(n)	+ "].";
					chk = getParameter(request, line1 + "jobTime");
					tmp = AecNumberUtils.convertBigDecimal(chk);
					if (tmp != null) {
						sagyoIn[p].setJobTime(checker.format("SAGYOJIKAN", tmp));
					} else {
						sagyoIn[p].setJobTime(chk); // 変換できない値もセットする。
					}

					chk = getParameter(request, line1 + "jobTimeHhmm");
					sagyoIn[p].setJobTimeHhmm(chk);
				
				}
				detailIn[i].setSagyoTimeList(sagyoIn);
			}
			//2022/06/13 画面更新フラグの強制セットを抑止　　S.Fujimaki
			setNextDetailList(detailIn);
		}
		if ("search".equals(op) || "update".equals(op)) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}
	
	/**
	 * 
	 * ##ここにメソッドの説明を書いてください##
	 * @param date1
	 * @param date2
	 * @return
	 */
	private String addDateHhmm(String proc,String date1,String date2){
		
	    BigDecimal hour = BigDecimal.ZERO;
	    BigDecimal minute= BigDecimal.ZERO;
	    BigDecimal hour_1= BigDecimal.ZERO;
	    BigDecimal minute_1= BigDecimal.ZERO;
	    BigDecimal hour_2= BigDecimal.ZERO;
	    BigDecimal minute_2= BigDecimal.ZERO;
	    String datetime;
	    int length;
    	boolean isMinus = false;
	    
    	// 日付ではない場合、00:00とする
	    if(!isDateHhmm(date1) || date1 == null || date1.isEmpty()){
	    	date1 = "00:00";
	    }

    	// 日付ではない場合、00:00とする
	    if(!isDateHhmm(date2) || date2 == null || date2.isEmpty()){
	    	date2 = "00:00";
	    }
	    
		// =================================================================================
		// ①引数１の時間を時間と分に分解
		// =================================================================================
	    if(date1 == null || date1.equals("")){
		    datetime = "00:00".replace(":", "");
	    }else{
		    datetime = date1.replace(":", "");
	    }
	    length = date1.length();
	    hour_1 = AecNumberUtils.convertBigDecimal(date1.substring(0, length - 3));
	    minute_1 = AecNumberUtils.convertBigDecimal(date1.substring(length - 2, length));
	    
		// =================================================================================
		// ②引数2の時間を時間と分に分解
		// =================================================================================
	    if(date2 == null || date2.equals("")){
		    datetime = "00:00".replace(":", "");
	    }else{
		    datetime = date2.replace(":", "");
	    }
	    length = date2.length();
	    hour_2 = AecNumberUtils.convertBigDecimal(date2.substring(0, length - 3));
	    minute_2 = AecNumberUtils.convertBigDecimal(date2.substring(length - 2, length));
	    
	    if(proc.equals("ADD")){
	    	// 加算の場合
	    	hour = hour_1.add(hour_2);
	    	minute = minute_1.add(minute_2);
	    	if(minute.intValue() >= 60){
	    		hour = hour.add(BigDecimal.ONE);
	    		minute = minute.subtract(new BigDecimal(60));
	    	}
	    	
	    }else{
	    	// 減算の場合、
	    	BigDecimal c_hour1 = hour_1.multiply(new BigDecimal(60)).add(minute_1);
	    	BigDecimal c_hour2 = hour_2.multiply(new BigDecimal(60)).add(minute_2);
	    	
	    	// 引くほうが大きい場合、変数を逆転する。
	    	if(c_hour1.intValue() < c_hour2.intValue()){
	    		isMinus = true;
	    		BigDecimal t_hour1 = hour_1;
	    		BigDecimal t_minute1 = minute_1;
	    		
	    		hour_1 = hour_2;
	    		minute_1 = minute_2;
	    		
	    		hour_2 = t_hour1;
	    		minute_2 = t_minute1;
	    		
	    	}
	    	
	    	
	    	
	    	hour = hour_1.subtract(hour_2);
	    	if(minute_1.intValue() < minute_2.intValue()){
	    		minute = minute_1.add(new BigDecimal(60)).subtract(minute_2);
	    		hour_1 = hour_1.subtract(new BigDecimal(1));
	    		
	    	}else{
	    		minute = minute_1.subtract(minute_2);
	    	}
	    	hour = hour_1.subtract(hour_2);
	    	
	    }
	    
	    if(hour.intValue() > 9 || hour.intValue() <0){
	    	datetime = hour.toString() + ":" + String.format("%02d", minute.intValue());
	    }else{
	    	datetime = String.format("%02d", hour.intValue()) + ":" + String.format("%02d", minute.intValue());
	    }
	    
	    if(isMinus){
	    	datetime = "-" +datetime;
	    }
	    	
		return datetime;
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
	 * 初期化.
	 */
	public void clear() {
		setNowpage(1);
		setNextCmd(null);
		setTantoDiv(null);
		setSrhDateNew(null);
		setSrhLineNew(null);
		setSrhLineValues(null);
		setSrhLineLabels(null);
		setSrhDate(null);
		setSrhLine(null);
		setHeaderList(new DailyReportListForm.DailyReportHeader[0]);
		setDetailList(new DailyReportListForm.DailyReportDetail[0]);
		//2022/06/13 画面更新フラグの強制セットを抑止　　S.Fujimaki
		setDirtyFlg(false);
		setReportCondition(null);
		setExcelDownloadFlg(false);
		setExecute(false);
	}

	/**
	 * 次のコマンドを取得します。
	 * @return nextCmd String
	 */
	public String getNextCmd() {
		return this.nextCmd;
	}

	/**
	 * 次のコマンドを設定します。
	 * @param nextCmd String
	 */
	public void setNextCmd(final String nextCmd) {
		this.nextCmd = nextCmd;
	}

	/**
	 * 製造日を取得します。
	 * @return srhDate String
	 */
	public String getSrhDate() {
		return srhDate;
	}

	/**
	 * 製造日を設定します。
	 * @param srhDate String
	 */
	public void setSrhDate(final String srhDate) {
		this.srhDate = srhDate;
	}

	/**
	 * 生産工場CDを取得します。
	 * @return srhLine String
	 */
	public String getSrhLine() {
		return srhLine;
	}

	/**
	 * 生産工場CDを設定します。
	 * @param srhLine String
	 */
	public void setSrhLine(final String srhLine) {
		this.srhLine = srhLine;
	}

	/**
	 * 生産工場コンボの名称を取得します。
	 * @return srhLineLabels String[]
	 */
	public String[] getSrhLineLabels() {
		return srhLineLabels;
	}

	/**
	 * 生産工場コンボの名称を設定します。
	 * @param srhLineLabels String[]
	 */
	public void setSrhLineLabels(final String[] srhLineLabels) {
		this.srhLineLabels = srhLineLabels;
	}

	/**
	 * 生産工場コンボのコードを取得します。
	 * @return srhLineValues String[]
	 */
	public String[] getSrhLineValues() {
		return srhLineValues;
	}

	/**
	 * 生産工場コンボのコードを設定します。
	 * @param srhLineValues String[]
	 */
	public void setSrhLineValues(final String[] srhLineValues) {
		this.srhLineValues = srhLineValues;
	}

	/**
	 * 製造日を取得します。
	 * @return srhDateNew String
	 */
	public String getSrhDateNew() {
		return srhDateNew;
	}

	/**
	 * 製造日を設定します。
	 * @param srhDateNew String
	 */
	public void setSrhDateNew(final String srhDateNew) {
		this.srhDateNew = srhDateNew;
	}

	/**
	 * 生産工場CDを取得します。
	 * @return srhLineNew String
	 */
	public String getSrhLineNew() {
		return srhLineNew;
	}

	/**
	 * 生産工場CDを設定します。
	 * @param srhLineNew String
	 */
	public void setSrhLineNew(final String srhLineNew) {
		this.srhLineNew = srhLineNew;
	}

	/**
	 * 現在のページ番号を取得します。
	 * @return nowpage int
	 */
	public int getNowpage() {
		return this.nowpage;
	}

	/**
	 * 現在のページ番号を設定します。
	 * @param nowpage int
	 */
	public void setNowpage(final int nowpage) {
		this.nowpage = nowpage;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final DailyReportListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * reportCondition取得します。
	 * @return reportCondition reportCondition
	 */
	public DailyReportListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * 作業日報ヘッダーのフォームをセット 20220609 add　　S.Fujimaki
	 * @param headerList DailyReportHeader[]
	 */
	public void setNextHeaderList(
			final DailyReportListForm.DailyReportHeader[] headerList) {
		this.headerList = headerList;
	}
	/**
	 * 作業日報ヘッダーのフォームをセット
	 * @param headerList DailyReportHeader[]
	 */
	public void setHeaderList(
			final DailyReportListForm.DailyReportHeader[] headerList) {
		if (this.headerList == null) {
			if (headerList != null) {
				this.dirtyFlg = new Boolean(true);
			}
		} else if (this.headerList.length != headerList.length) {
			this.dirtyFlg = new Boolean(true);
		} else {
			for (int i = 0; i < this.headerList.length; i++) {
				if (this.headerList[i] == null) {
					if (headerList[i] != null) {
						this.dirtyFlg = new Boolean(true);
						break;
					}
				} else if (!this.headerList[i].equals(headerList[i])) {
					this.dirtyFlg = new Boolean(true);
					break;
				}
			}
		}
		this.headerList = headerList;
	}

	/**
	 * 作業日報ヘッダーのフォームを取得
	 * @return headerList DailyReportHeader[]
	 */
	public DailyReportListForm.DailyReportHeader[] getHeaderList() {
		DailyReportListForm.DailyReportHeader[] listClone = (DailyReportListForm.DailyReportHeader[]) headerList
				.clone();
		for (int i = 0; i < listClone.length; i++) {
			listClone[i] = (DailyReportListForm.DailyReportHeader) headerList[i]
					.clone();
		}
		return listClone;
	}

	/**
	 * １ページの作業日報ヘッダーのフォームを取得
	 * @return headerList DailyReportHeader[]
	 */
	public DailyReportListForm.DailyReportHeader[] getHeaderListPage() {
		int pagedata = (nowpage - 1) * DailyReportListForm.PAGE_COL;
		DailyReportListForm.DailyReportHeader[] listClone = new DailyReportListForm.DailyReportHeader[DailyReportListForm.PAGE_COL];
		for (int i = 0; i < listClone.length; i++) {
			listClone[i] = (DailyReportListForm.DailyReportHeader) headerList[pagedata
					+ i].clone();
		}
		return listClone;
		// String[][] dt = new String[5][8];
		// for (int i = (nowpage -1) *5; i < nowpage*5; i++) {
		// dt[i][0] = headerList[i].getDelFlg(); /* 削除フラグ */
		// dt[i][1] = headerList[i].getTantoCd(); /* 担当者CD */
		// dt[i][2] = headerList[i].getTantoNm(); /* 担当者名称 */
		// dt[i][3] = headerList[i].getSeq(); /* 表示順 */
		// dt[i][4] = headerList[i].getInsideTotal(); /* 時間内 */
		// dt[i][5] = headerList[i].getOutsideTotal(); /* 時間外 */
		// dt[i][6] = headerList[i].getEmployTime(); /* 就業時間 */
		// dt[i][7] = headerList[i].getIndirectTime(); /* 間接時間 */
		// }
		// return dt;
	}

	/**
	 * 作業日報明細のフォームをセット　20220609 add 　S.Fujimaki
	 * @param detailList DailyReportDetail[]
	 */
	public void setNextDetailList(
			final DailyReportListForm.DailyReportDetail[] detailList) {
		this.detailList = detailList;
	}
	/**
	 * 作業日報明細のフォームをセット
	 * @param detailList DailyReportDetail[]
	 */
	public void setDetailList(
			final DailyReportListForm.DailyReportDetail[] detailList) {
		if (this.detailList == null) {
			if (detailList != null) {
				this.dirtyFlg = new Boolean(true);
			}
		} else if (this.detailList.length != detailList.length) {
			this.dirtyFlg = new Boolean(true);
		} else {
			for (int i = 0; i < this.detailList.length; i++) {
				if (this.detailList[i] == null) {
					if (detailList[i] != null) {
						this.dirtyFlg = new Boolean(true);
						break;
					}
				} else if (!this.detailList[i].equals(detailList[i])) {
					this.dirtyFlg = new Boolean(true);
					break;
				}
			}
		}
		this.detailList = detailList;
	}

	/**
	 * 作業日報明細のフォームを取得
	 * @return detailList DailyReportDetail[]
	 */
	public DailyReportListForm.DailyReportDetail[] getDetailList() {
		DailyReportListForm.DailyReportDetail[] listClone = (DailyReportListForm.DailyReportDetail[]) detailList
				.clone();
		for (int i = 0; i < listClone.length; i++) {
			listClone[i] = (DailyReportListForm.DailyReportDetail) detailList[i]
					.clone();
		}
		return listClone;
	}

	/**
	 * １ページの作業日報明細のフォームを取得
	 * @return detailList DailyReportDetail[]
	 */
	public DailyReportListForm.DailyReportDetail[] getDetailListPage() {
		int pagedata = (nowpage - 1) * DailyReportListForm.PAGE_COL;
		DailyReportListForm.DailyReportDetail[] listClone = (DailyReportListForm.DailyReportDetail[]) detailList
				.clone();
		for (int i = 0; i < listClone.length; i++) {
			listClone[i] = (DailyReportListForm.DailyReportDetail) detailList[i]
					.clone();
			// 作業時間
			DailyReportListForm.SagyoTime[] listSagyo = listClone[i]
					.getSagyoTimeList();
			DailyReportListForm.SagyoTime[] listSagyoClone = new DailyReportListForm.SagyoTime[DailyReportListForm.PAGE_COL];
			for (int n = 0; n < listSagyoClone.length; n++) {
				listSagyoClone[n] = (DailyReportListForm.SagyoTime) listSagyo[pagedata
						+ n].clone();
			}
			listClone[i].setSagyoTimeList(listSagyoClone);
		}
		return listClone;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate java.sql.Timestamp
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate java.sql.Timestamp
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * tantoDivを取得します。
	 * @return tantoDiv String
	 */
	public String getTantoDiv() {
		return tantoDiv;
	}

	/**
	 * tantoDivを設定します。
	 * @param tantoDiv String
	 */
	public void setTantoDiv(final String tantoDiv) {
		this.tantoDiv = tantoDiv;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg Boolean
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg Boolean
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg boolean
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg boolean
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * 作業日報ヘッダーの配列
	 * @author fml
	 */
	public class DailyReportHeader implements Cloneable {
		/**
		 * コンストラクタ
		 */
		public DailyReportHeader() {
		}

		/**
		 * コピー作成可能にする
		 * @return clone
		 */
		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException ex) {
				return null;
			}
		}

		/**
		 * クラスのハッシュコードを取得
		 * @return hashCode int
		 */
		public int hashCode() {
			return this.hashCode();
		}

		/**
		 * 引数のクラスと等しいか調べます。
		 * @param other Object
		 * @return equals boolean
		 */
		public boolean equals(final Object other) {
			if (this == other) {
				// 同じインスタンスの場合
				return true;
			}
			if (this == null) {
				// nullの場合
				return false;
			}
			if (this.getClass() != other.getClass()) {
				// 違うクラスの場合
				return false;
			}

			DailyReportListForm.DailyReportHeader header = (DailyReportListForm.DailyReportHeader) other;

			/** 元の担当者CD */
			if (!this.tantoCdKey.equals(header.tantoCdKey)) {
				return false;
			}
			/** 担当者CD */
			if (!this.tantoCd.equals(header.tantoCd)) {
				return false;
			}
			/** 担当者名称 */
			if (!this.tantoNm.equals(header.tantoNm)) {
				return false;
			}
			/** 表示順 */
			if (!this.seq.equals(header.seq)) {
				return false;
			}
			/** 時間内 */
			if (!this.insideTotalHhmm.equals(header.insideTotalHhmm)) {
				return false;
			}
			/** 時間外 */
			if (!this.outsideTotalHhmm.equals(header.outsideTotalHhmm)) {
				return false;
			}
			/** 就業時間 */
			if (!this.employTimeHhmm.equals(header.employTimeHhmm)) {
				return false;
			}
			/** 間接時間 */
			if (!this.indirectTimeHhmm.equals(header.indirectTimeHhmm)) {
				return false;
			}
			/** 削除フラグ */
			if (!this.delFlg.equals(header.delFlg)) {
				return false;
			}
			return true;
		}

		/* 各項目 */
		private String delFlg = ""; /* 削除フラグ */

		private String tantoCdKey = ""; /* 元の担当者CD */

		private String tantoCd = ""; /* 担当者CD */

		private String tantoNm = ""; /* 担当者名称 */

		private String seq = ""; /* 表示順 */

		private String insideTotal = ""; /* 時間内 */

		private String outsideTotal = ""; /* 時間外 */

		private String employTime = ""; /* 就業時間 */

		private String indirectTime = ""; /* 間接時間 */

		private String insideTotalHhmm = ""; /* 時間内 */

		private String outsideTotalHhmm = ""; /* 時間外 */

		private String employTimeHhmm = ""; /* 就業時間 */

		private String indirectTimeHhmm = ""; /* 間接時間 */

		/**
		 * 削除フラグを設定します。
		 * @param delFlg delFlg
		 */
		public final void setDelFlg(final String delFlg) {
			this.delFlg = delFlg;
		}

		/**
		 * 削除フラグを取得します。
		 * @return delFlg
		 */
		public final String getDelFlg() {
			return this.delFlg;
		}

		/**
		 * 元の担当者CDを設定します。
		 * @param tantoCdKey String
		 */
		public final void setTantoCdKey(final String tantoCdKey) {
			this.tantoCdKey = tantoCdKey;
		}

		/**
		 * 元の担当者CDを取得します。
		 * @return tantoCdKey String
		 */
		public final String getTantoCdKey() {
			return this.tantoCdKey;
		}

		/**
		 * 担当者CDを設定します。
		 * @param tantoCd String
		 */
		public final void setTantoCd(final String tantoCd) {
			this.tantoCd = tantoCd;
		}

		/**
		 * 担当者CDを取得します。
		 * @return tantoCd String
		 */
		public final String getTantoCd() {
			return this.tantoCd;
		}

		/**
		 * 担当者名称を設定します。
		 * @param tantoNm String
		 */
		public final void setTantoNm(final String tantoNm) {
			this.tantoNm = tantoNm;
		}

		/**
		 * 担当者名称を取得します。
		 * @return tantoNm String
		 */
		public final String getTantoNm() {
			return this.tantoNm;
		}

		/**
		 * 表示順を設定します。
		 * @param seq String
		 */
		public final void setSeq(final String seq) {
			this.seq = seq;
		}

		/**
		 * 表示順を取得します。
		 * @return seq String
		 */
		public final String getSeq() {
			return this.seq;
		}

		/**
		 * 時間内を設定します。
		 * @param insideTotal String
		 */
		public final void setInsideTotal(final String insideTotal) {
			this.insideTotal = insideTotal;
		}

		/**
		 * 時間内を取得します。
		 * @return insideTotal String
		 */
		public final String getInsideTotal() {
			return this.insideTotal;
		}

		/**
		 * 時間外を設定します。
		 * @param outsideTotal String
		 */
		public final void setOutsideTotal(final String outsideTotal) {
			this.outsideTotal = outsideTotal;
		}

		/**
		 * 時間外を取得します。
		 * @return outsideTotal String
		 */
		public final String getOutsideTotal() {
			return this.outsideTotal;
		}

		/**
		 * 就業時間を設定します。
		 * @param employTime String
		 */
		public final void setEmployTime(final String employTime) {
			this.employTime = employTime;
		}

		/**
		 * 就業時間を取得します。
		 * @return employTime String
		 */
		public final String getEmployTime() {
			return this.employTime;
		}

		/**
		 * 間接時間を設定します。
		 * @param indirectTime String
		 */
		public final void setIndirectTime(final String indirectTime) {
			this.indirectTime = indirectTime;
		}

		/**
		 * 間接時間を取得します。
		 * @return indTime String
		 */
		public final String getIndirectTime() {
			return this.indirectTime;
		}

		/**
		 * insideTotalHhmmを取得します。
		 * @return insideTotalHhmm
		 */
		public String getInsideTotalHhmm() {
			return insideTotalHhmm;
		}

		/**
		 * insideTotalHhmmを設定します。
		 * @param insideTotalHhmm insideTotalHhmm
		 */
		public void setInsideTotalHhmm(String insideTotalHhmm) {
			this.insideTotalHhmm = insideTotalHhmm;
		}

		/**
		 * outsideTotalHhmmを取得します。
		 * @return outsideTotalHhmm
		 */
		public String getOutsideTotalHhmm() {
			return outsideTotalHhmm;
		}

		/**
		 * outsideTotalHhmmを設定します。
		 * @param outsideTotalHhmm outsideTotalHhmm
		 */
		public void setOutsideTotalHhmm(String outsideTotalHhmm) {
			this.outsideTotalHhmm = outsideTotalHhmm;
		}

		/**
		 * employTimeHhmmを取得します。
		 * @return employTimeHhmm
		 */
		public String getEmployTimeHhmm() {
			return employTimeHhmm;
		}

		/**
		 * employTimeHhmmを設定します。
		 * @param employTimeHhmm employTimeHhmm
		 */
		public void setEmployTimeHhmm(String employTimeHhmm) {
			this.employTimeHhmm = employTimeHhmm;
		}

		/**
		 * indirectTimeHhmmを取得します。
		 * @return indirectTimeHhmm
		 */
		public String getIndirectTimeHhmm() {
			return indirectTimeHhmm;
		}

		/**
		 * indirectTimeHhmmを設定します。
		 * @param indirectTimeHhmm indirectTimeHhmm
		 */
		public void setIndirectTimeHhmm(String indirectTimeHhmm) {
			this.indirectTimeHhmm = indirectTimeHhmm;
		}
	}

	/**
	 * 作業日報明細の配列
	 * @author fml
	 */
	public class DailyReportDetail implements Cloneable {
		/**
		 * コンストラクタ
		 */
		public DailyReportDetail() {
			areaFlg = new Boolean(false);
		}

		/**
		 * コピー作成可能にする
		 * @return clone
		 */
		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException ex) {
				return null;
			}
		}

		/**
		 * クラスのハッシュコードを取得
		 * @return hashCode int
		 */
		public int hashCode() {
			return this.hashCode();
		}

		/**
		 * 引数のクラスと等しいか調べます。
		 * @param other Object
		 * @return equals boolean
		 */
		public boolean equals(final Object other) {
			if (this == other) {
				// 同じインスタンスの場合
				return true;
			}
			if (this == null) {
				// nullの場合
				return false;
			}
			if (this.getClass() != other.getClass()) {
				// 違うクラスの場合
				return false;
			}

			DailyReportListForm.DailyReportDetail detail = (DailyReportListForm.DailyReportDetail) other;

			/** 指図区分 */
			if (!StringUtils.equals(this.directionDiv, detail.directionDiv)) {
				return false;
			}
			/** 指図番号 */
			if (!StringUtils.equals(this.directionNo, detail.directionNo)) {
				return false;
			}
			/** 生産ライン */
			if (!StringUtils.equals(this.line, detail.line)) {
				return false;
			}
			/** 製造日 */
			if (!StringUtils.equals(this.date, detail.date)) {
				return false;
			}
			/** 品目名称 */
			if (!StringUtils.equals(this.itemNm, detail.itemNm)) {
				return false;
			}
			/** 小計１ */
			if (!StringUtils.equals(this.subTotal1, detail.subTotal1)) {
				return false;
			}
			/** 小計２ */
			if (!StringUtils.equals(this.subTotal2, detail.subTotal2)) {
				return false;
			}
			/** 合計 */
			if (!StringUtils.equals(this.total, detail.total)) {
				return false;
			}
			/** 範囲外フラグ */
			if (!this.areaFlg.equals(detail.areaFlg)) {
				return false;
			}
			/** 作業時間 */
			if (this.sagyoTimeList.length != detail.sagyoTimeList.length) {
				return false;
			} else {
				for (int i = 0; i < this.sagyoTimeList.length; i++) {
					if (this.sagyoTimeList[i] == null) {
						if (detail.sagyoTimeList[i] != null) {
							return false;
						}
					} else if (!this.sagyoTimeList[i]
							.equals(detail.sagyoTimeList[i])) {
						return false;
					}
				}
			}
			return true;
		}

		/* 各項目 */
		private String directionDiv = ""; /* 指図区分 */

		private String directionNo = ""; /* 指図番号 */

		private String line = ""; /* 生産ライン */

		private String date = ""; /* 製造日 */

		private String itemNm = ""; /* 品目名称 */

		private String subTotal1 = ""; /* 小計１ */

		private String subTotal2 = ""; /* 小計２ */

		private String total = ""; /* 合計 */

		private String subTotal1Hhmm = ""; /* 小計１ */

		private String subTotal2Hhmm = ""; /* 小計２ */

		private String totalHhmm = ""; /* 合計 */
		
		
		
		
		private Boolean areaFlg = new Boolean(false); /* 範囲外フラグ */

		/* 作業時間の配列 */
		private DailyReportListForm.SagyoTime[] sagyoTimeList = new DailyReportListForm.SagyoTime[0];

		/**
		 * 指図区分を設定します。
		 * @param directionDiv directionDiv
		 */
		public final void setDirectionDiv(final String directionDiv) {
			this.directionDiv = directionDiv;
		}

		/**
		 * 指図区分を取得します。
		 * @return directionDiv
		 */
		public final String getDirectionDiv() {
			return this.directionDiv;
		}

		/**
		 * 指図番号を設定します。
		 * @param directionNo directionNo
		 */
		public final void setDirectionNo(final String directionNo) {
			this.directionNo = directionNo;
		}

		/**
		 * 指図番号を取得します。
		 * @return directionNo
		 */
		public final String getDirectionNo() {
			return this.directionNo;
		}

		/**
		 * 生産ラインを設定します。
		 * @param line line
		 */
		public final void setLine(final String line) {
			this.line = line;
		}

		/**
		 * 生産ラインを取得します。
		 * @return line
		 */
		public final String getLine() {
			return this.line;
		}

		/**
		 * 製造日を設定します。
		 * @param date date
		 */
		public final void setDate(final String date) {
			this.date = date;
		}

		/**
		 * 製造日を取得します。
		 * @return date
		 */
		public final String getDate() {
			return this.date;
		}

		/**
		 * 品目名称を設定します。
		 * @param itemNm itemNm
		 */
		public final void setItemNm(final String itemNm) {
			this.itemNm = itemNm;
		}

		/**
		 * 品目名称を取得します。
		 * @return itemNm
		 */
		public final String getItemNm() {
			return this.itemNm;
		}

		/**
		 * 小計１を設定します。
		 * @param subTotal1 subTotal1
		 */
		public final void setSubTotal1(final String subTotal1) {
			this.subTotal1 = subTotal1;
		}

		/**
		 * 小計１を取得します。
		 * @return subTotal1
		 */
		public final String getSubTotal1() {
			return this.subTotal1;
		}

		/**
		 * 小計２を設定します。
		 * @param subTotal2 subTotal2
		 */
		public final void setSubTotal2(final String subTotal2) {
			this.subTotal2 = subTotal2;
		}

		/**
		 * 小計２を取得します。
		 * @return subTotal2
		 */
		public final String getSubTotal2() {
			return this.subTotal2;
		}

		/**
		 * 合計を設定します。
		 * @param total total
		 */
		public final void setTotal(final String total) {
			this.total = total;
		}

		/**
		 * 合計を取得します。
		 * @return total
		 */
		public final String getTotal() {
			return this.total;
		}

		/**
		 * 合計を設定します。
		 * @param areaFlg Boolean
		 */
		public final void setAreaFlg(final Boolean areaFlg) {
			this.areaFlg = areaFlg;
		}

		/**
		 * 合計を取得します。
		 * @return areaFlg Boolean
		 */
		public final Boolean getAreaFlg() {
			return this.areaFlg;
		}

		/**
		 * 作業日報明細-作業時間のフォームをセット
		 * @param sagyoTimeList sagyoTimeList
		 */
		public void setSagyoTimeList(
				final DailyReportListForm.SagyoTime[] sagyoTimeList) {
			this.sagyoTimeList = sagyoTimeList;
		}

		/**
		 * 作業日報明細-作業時間のフォームを取得
		 * @return sagyoTimeList
		 */
		public DailyReportListForm.SagyoTime[] getSagyoTimeList() {
			DailyReportListForm.SagyoTime[] listClone = (DailyReportListForm.SagyoTime[]) sagyoTimeList
					.clone();
			for (int i = 0; i < listClone.length; i++) {
				listClone[i] = (DailyReportListForm.SagyoTime) sagyoTimeList[i]
						.clone();
			}
			return listClone;
		}

		/**
		 * subTotal1Hhmmを取得します。
		 * @return subTotal1Hhmm
		 */
		public String getSubTotal1Hhmm() {
			return subTotal1Hhmm;
		}

		/**
		 * subTotal1Hhmmを設定します。
		 * @param subTotal1Hhmm subTotal1Hhmm
		 */
		public void setSubTotal1Hhmm(String subTotal1Hhmm) {
			this.subTotal1Hhmm = subTotal1Hhmm;
		}

		/**
		 * subTotal2Hhmmを取得します。
		 * @return subTotal2Hhmm
		 */
		public String getSubTotal2Hhmm() {
			return subTotal2Hhmm;
		}

		/**
		 * subTotal2Hhmmを設定します。
		 * @param subTotal2Hhmm subTotal2Hhmm
		 */
		public void setSubTotal2Hhmm(String subTotal2Hhmm) {
			this.subTotal2Hhmm = subTotal2Hhmm;
		}

		/**
		 * totalHhmmを取得します。
		 * @return totalHhmm
		 */
		public String getTotalHhmm() {
			return totalHhmm;
		}

		/**
		 * totalHhmmを設定します。
		 * @param totalHhmm totalHhmm
		 */
		public void setTotalHhmm(String totalHhmm) {
			this.totalHhmm = totalHhmm;
		}
	}

	/**
	 * 作業日報-作業時間の配列
	 * @author fml
	 */
	public class SagyoTime implements Cloneable {
		/**
		 * コンストラクタ
		 */
		public SagyoTime() {
		}

		/**
		 * コピー作成可能にする
		 * @return clone
		 */
		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException ex) {
				return null;
			}
		}

		/* 各項目 */
		private String tantoDiv = ""; /* 担当区分 */

		private String tantoCdKey = ""; /* 元の担当者CD */

		private String tantoCd = ""; /* 担当者CD */

		private String jobTime = ""; /* 作業時間 */
		
		private String jobTimeHhmm = ""; /* 作業時間 */

		private String seq = ""; /* 表示順 */

		/**
		 * 担当区分を設定します。
		 * @param tantoDiv String
		 */
		public final void setTantoDiv(final String tantoDiv) {
			this.tantoDiv = tantoDiv;
		}

		/**
		 * 担当区分を取得します。
		 * @return tantoDiv String
		 */
		public final String getTantoDiv() {
			return this.tantoDiv;
		}

		/**
		 * 元の担当者CDを設定します。
		 * @param tantoCdKey String
		 */
		public final void setTantoCdKey(final String tantoCdKey) {
			this.tantoCdKey = tantoCdKey;
		}

		/**
		 * 元の担当者CDを取得します。
		 * @return tantoCdKey String
		 */
		public final String getTantoCdKey() {
			return this.tantoCdKey;
		}

		/**
		 * 担当者CDを設定します。
		 * @param tantoCd String
		 */
		public final void setTantoCd(final String tantoCd) {
			this.tantoCd = tantoCd;
		}

		/**
		 * 担当者CDを取得します。
		 * @return tantoCd String
		 */
		public final String getTantoCd() {
			return this.tantoCd;
		}

		/**
		 * 作業時間を設定します。
		 * @param jobTime String
		 */
		public final void setJobTime(final String jobTime) {
			this.jobTime = jobTime;
		}

		/**
		 * 作業時間を取得します。
		 * @return jobTime String
		 */
		public final String getJobTime() {
			return this.jobTime;
		}

		/**
		 * 表示順を設定します。
		 * @param seq String
		 */
		public final void setSeq(final String seq) {
			this.seq = seq;
		}

		/**
		 * 表示順を取得します。
		 * @return seq String
		 */
		public final String getSeq() {
			return this.seq;
		}

		/**
		 * jobTimeHhmmを取得します。
		 * @return jobTimeHhmm
		 */
		public String getJobTimeHhmm() {
			return jobTimeHhmm;
		}

		/**
		 * jobTimeHhmmを設定します。
		 * @param jobTimeHhmm jobTimeHhmm
		 */
		public void setJobTimeHhmm(String jobTimeHhmm) {
			this.jobTimeHhmm = jobTimeHhmm;
		}
	}

	/**
	 * isExecuteを取得します。
	 * @return isExecute
	 */
	public boolean isExecute() {
		return isExecute;
	}

	/**
	 * isExecuteを設定します。
	 * @param isExecute isExecute
	 */
	public void setExecute(final boolean isExecute) {
		this.isExecute = isExecute;
	}

	// --------------------------------------------------
	// --------------------------------------------------

	/**
	 * パラメータの値を取り出す。
	 * @param request HttpServletRequest
	 * @param paramname String
	 * @return param String
	 */
	private String getParameter(final HttpServletRequest request,
			final String paramname) {
		String param = request.getParameter(paramname);
		if (param == null) {
			param = "";
		}
		return param;
	}

	/**
	 * チェックボックスのパラメータの値を取り出す。
	 * @param request HttpServletRequest
	 * @param paramname String
	 * @param tvalue String
	 * @param fvalue String
	 * @return param String
	 */
	private String getParameterChk(final HttpServletRequest request,
			final String paramname, final String tvalue, final String fvalue) {
		String param = request.getParameter(paramname);
		if (param == null) {
			param = fvalue;
		} else {
			param = tvalue;
		}
		return param;
	}
}

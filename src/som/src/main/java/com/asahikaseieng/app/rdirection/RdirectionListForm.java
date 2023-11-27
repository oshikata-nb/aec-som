/*
 * Created on 2009/03/09
 *
 * $copyright$
 */
package com.asahikaseieng.app.rdirection;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製造実績検索 Formクラス.
 * @author tosco
 * 
 */
public final class RdirectionListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 日付チェックフォーマット */
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	/** 調合タンクNo */
	private String compoundTankNo;

	private static ResourceBundle rb;
	static {
		rb = ResourceBundle.getBundle(Constants.APPLICATION_PROPERTIES);
		ResourceBundle sysrb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(sysrb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(sysrb.getString("threshold.common"));
	}

	//
	// 検索用
	//

	/** 指図番号 */
	private String directionNo;

	/** 生産工場 */
	private String productionLine;

	/** 指図ステータス */
	private int directionStatus;

	/** 主要製品コード */
	private String itemCd;

	/** 製造開始実績日(From) */
	private String resultSdateDayFrom;

	/** 製造開始実績時(From) */
	private String resultSdateTimeFrom;

	/** 製造終了実績日(From) */
	private String resultEdateDayFrom;

	/** 製造終了実績時(From) */
	private String resultEdateTimeFrom;

	/** 製造開始実績日(To) */
	private String resultSdateDayTo;

	/** 製造開始実績時(To) */
	private String resultSdateTimeTo;

	/** 製造終了実績日(To) */
	private String resultEdateDayTo;

	/** 製造終了実績時(To) */
	private String resultEdateTimeTo;

	/** 品目名称 */
	private String itemName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** リスト */
	private List<RdirectionDirectionHeaderList> searchList;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private DirectionResultListConditionForReport reportCondition;

	/** 日付再チェック条件保持 */
	public int reCheckNum;

	/**
	 * コンストラクタ
	 */
	public RdirectionListForm() {
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
	protected Class<RdirectionDirectionHeaderListPagerCondition> getPagerConditionClass() {
		return RdirectionDirectionHeaderListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
		// チェックボックスをクリア
		clearSelectedCheck();
		setExcelReportDownloadFlg(false);
	}

	/**
	 * 選択チェックボックスを全てクリアする。
	 */
	public void clearSelectedCheck() {
		for (RdirectionDirectionHeaderList bean : searchList) {
			bean.setSelectedCheck(false);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<RdirectionDirectionHeaderList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			errors = validateDate(errors);
		}
		return errors;
	}

	/**
	 * 日付チェック
	 * @param errors errors
	 * @return ActionErrors
	 */
	private ActionErrors validateDate(final ActionErrors errors) {
		// 製造開始実績日(From)
		Date startFrom = checkDate(errors, resultSdateDayFrom,
			resultSdateTimeFrom, "item.direction.planed.sdate.date.from", true);
		Date startTo = checkDate(errors, resultSdateDayTo, resultSdateTimeTo,
			"item.direction.planed.sdate.date.to", false);
		Date endFrom = checkDate(errors, resultEdateDayFrom,
			resultEdateTimeFrom, "item.direction.planed.edate.date.from", true);
		Date endTo = checkDate(errors, resultEdateDayTo, resultEdateTimeTo,
			"item.direction.planed.edate.date.to", false);
		if (!compareDate(startFrom, startTo)) {
			addErrors(errors, "errors.compare",
				"item.direction.planed.sdate.date.from",
				"item.direction.planed.sdate.date.to");
		}
		if (!compareDate(endFrom, endTo)) {
			addErrors(errors, "errors.compare",
				"item.direction.planed.edate.date.from",
				"item.direction.planed.edate.date.to");
		}
		return errors;
	}

	/**
	 * 日付を比較する
	 * @param start 開始Date
	 * @param to 終了Date
	 * @return [true:開始≦終了][false:開始＞終了]
	 */
	private boolean compareDate(final Date start, final Date to) {
		boolean res = true;
		if ((start != null) && (to != null)) {
			if (start.compareTo(to) > 0) {
				// startがtoより後なのでエラー
				res = false;
			}
		}
		return res;
	}

	/**
	 * 日・時のチェックを行う
	 * @param errors ActionErrors
	 * @param day 日付
	 * @param time 時刻
	 * @param itemKey 項目名
	 * @param isStart [true:From][false:To]
	 * @return Date（チェックOK時）
	 */
	private Date checkDate(final ActionErrors errors, final String day,
			final String time, final String itemKey, final boolean isStart) {
		Date res = null;
		if (StringUtils.isNotEmpty(day)) {
			String dateString = getDateTime(day, time, isStart);
			try {
				res = checkDate(dateString, DATE_FORMAT);
			} catch (ParseException e) {
				// 日付フォーマットエラー
				addErrors(errors, "errors.direction.datetime", itemKey);
			}
		} else {
			if (StringUtils.isNotEmpty(time)) {
				// 時刻のみを入力した場合エラー
				addErrors(errors, "errors.direction.time.notinputday", itemKey);
			}
		}
		return res;
	}

	/**
	 * 日と時から日時文字列を作成する。
	 * @param day 日付
	 * @param time 時刻
	 * @param itemKey 項目名
	 * @param isStart [true:From][false:To]
	 * @return 日時文字列
	 */
	private String getDateTime(final String day, final String time,
			final boolean isStart) {
		String res = null;
		if (StringUtils.isNotEmpty(day)) {
			if (StringUtils.isEmpty(time)) {
				if (isStart) {
					res = day + " 00:00";
				} else {
					res = day + " 23:59";
				}
			} else {
				res = day + " " + time;
			}
		}
		return res;
	}

	/**
	 * ActionErrorsにエラーメッセージを追加する。
	 * @param errors ActionErrors
	 * @param key エラーメッセージキー
	 * @param replace 置換文字列
	 */
	private void addErrors(final ActionErrors errors, final String key,
			final String... replace) {
		ActionMessages messages = new ActionMessages();
		int num = replace.length;
		String[] repls = new String[num];
		for (int i = 0; i < num; i++) {
			repls[i] = rb.getString(replace[i]);
		}
		messages.add("", new ActionMessage(key, repls));
		errors.add(messages);
	}

	/**
	 * 日付フォーマットチェック
	 * @param source 対象文字列
	 * @param pattern 日付フォーマット
	 * @return [true:チェックOK][false:チェックNG]
	 * @throws ParseException 日付フォーマットエラー時発生
	 */
	private Date checkDate(final String source, final String pattern)
			throws ParseException {
		return AecDateUtils.getDate(source, pattern);
	}

	/**
	 * 初期化.基本処方検索
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<RdirectionDirectionHeaderList>());
		// 指図番号
		setDirectionNo(null);
		// 生産工場
		setProductionLine(null);
		// 指図ステータス
		setDirectionStatus(0);
		// 主要製品コード
		setItemCd(null);
		// 品目名称
		setItemName(null);
		// 他社コード１
		setOtherCompanyCd1(null);
		// 製造開始実績日(From)
		setResultSdateDayFrom(null);
		// 製造開始実績時(From)
		setResultSdateTimeFrom(null);
		// 製造終了実績日(From)
		setResultEdateDayFrom(null);
		// 製造終了実績時(From)
		setResultEdateTimeFrom(null);
		// 製造開始実績日(To)
		setResultSdateDayTo(null);
		// 製造開始実績時(To)
		setResultSdateTimeTo(null);
		// 製造終了実績日(To)
		setResultEdateDayTo(null);
		// 製造終了実績時(To)
		setResultEdateTimeTo(null);
		// 生産ラインコンボボックス
		setLineCombo(null);
		// エクセルダウンロードフラグ
		this.setExcelDownloadFlg(Boolean.FALSE);
		setExcelReportDownloadFlg(false);
		setReportCondition(null);
		setCompoundTankNo(null);
		setReCheckNum(0);
	}

	/**
	 * 製造開始実績日時(From)を取得する。
	 * @return 製造開始実績日時(From)
	 */
	public String getPlanedSdateDateFrom() {
		return getDateTime(resultSdateDayFrom, resultSdateTimeFrom, true);
	}

	/**
	 * 製造開始実績日時(To)を取得する。
	 * @return 製造開始実績日時(To)
	 */
	public String getPlanedSdateDateTo() {
		return getDateTime(resultSdateDayTo, resultSdateTimeTo, false);
	}

	/**
	 * 製造終了実績日時(From)を取得する。
	 * @return 製造終了実績日時(From)
	 */
	public String getPlanedEdateDateFrom() {
		return getDateTime(resultEdateDayFrom, resultEdateTimeFrom, true);
	}

	/**
	 * 製造終了実績日時(To)を取得する。
	 * @return 製造終了実績日時(To)
	 */
	public String getPlanedEdateDateTo() {
		return getDateTime(resultEdateDayTo, resultEdateTimeTo, false);
	}

	// getter,setter--------------------------------------------------------------------

	/**
	 * 基本処方検索：searchListを取得します。
	 * @return searchList
	 */
	public List<RdirectionDirectionHeaderList> getSearchList() {
		return searchList;
	}

	/**
	 * 基本処方検索： searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(
			final List<RdirectionDirectionHeaderList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * 検索入力：生産工場.
	 * @return String 生産工場
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 検索入力：生産工場.
	 * @param productionLine 生産工場
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 検索入力：他社コード１を取得します。
	 * @return 検索入力：他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１を設定します。
	 * @param otherCompanyCd1 検索入力：他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 検索入力：品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 検索入力：品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 検索結果数を取得する。
	 * @return 検索結果数
	 */
	public int getCount() {
		int res = 0;
		if (searchList != null) {
			res = searchList.size();
		}
		return res;
	}

	/**
	 * 生産工場コンボボックスを取得します。
	 * @return 生産工場コンボボックス
	 */
	public List<ComboBoxItems> getLineCombo() {
		return lineCombo;
	}

	/**
	 * 生産工場コンボボックスを設定します。
	 * @param lineCombo 生産工場コンボボックス
	 */
	public void setLineCombo(final List<ComboBoxItems> lineCombo) {
		this.lineCombo = lineCombo;
	}

	/**
	 * 指図番号を取得します。
	 * @return 指図番号
	 */
	public String getDirectionNo() {
		return directionNo;
	}

	/**
	 * 指図番号を設定します。
	 * @param directionNo 指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 指図ステータスを取得します。
	 * @return 指図ステータス
	 */
	public int getDirectionStatus() {
		return directionStatus;
	}

	/**
	 * 指図ステータスを設定します。
	 * @param directionStatus 指図ステータス
	 */
	public void setDirectionStatus(final int directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * 主要製品コードを取得します。
	 * @return 主要製品コード
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 主要製品コードを設定します。
	 * @param itemCd 主要製品コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 製造開始実績日(From)を取得します。
	 * @return 製造開始実績日(From)
	 */
	public String getResultSdateDayFrom() {
		return resultSdateDayFrom;
	}

	/**
	 * 製造開始実績日(From)を設定します。
	 * @param resultSdateDayFrom 製造開始実績日(From)
	 */
	public void setResultSdateDayFrom(final String resultSdateDayFrom) {
		this.resultSdateDayFrom = resultSdateDayFrom;
	}

	/**
	 * 製造開始実績時(From)を取得します。
	 * @return 製造開始実績時(From)
	 */
	public String getResultSdateTimeFrom() {
		return resultSdateTimeFrom;
	}

	/**
	 * 製造開始実績時(From)を設定します。
	 * @param resultSdateTimeFrom 製造開始実績時(From)
	 */
	public void setResultSdateTimeFrom(final String resultSdateTimeFrom) {
		this.resultSdateTimeFrom = resultSdateTimeFrom;
	}

	/**
	 * 製造終了実績日(From)を取得します。
	 * @return 製造終了実績日(From)
	 */
	public String getResultEdateDayFrom() {
		return resultEdateDayFrom;
	}

	/**
	 * 製造終了実績日(From)を設定します。
	 * @param resultEdateDayFrom 製造終了実績日(From)
	 */
	public void setResultEdateDayFrom(final String resultEdateDayFrom) {
		this.resultEdateDayFrom = resultEdateDayFrom;
	}

	/**
	 * 製造終了実績時(From)を取得します。
	 * @return 製造終了実績時(From)
	 */
	public String getResultEdateTimeFrom() {
		return resultEdateTimeFrom;
	}

	/**
	 * 製造終了実績時(From)を設定します。
	 * @param resultEdateTimeFrom 製造終了実績時(From)
	 */
	public void setResultEdateTimeFrom(final String resultEdateTimeFrom) {
		this.resultEdateTimeFrom = resultEdateTimeFrom;
	}

	/**
	 * 製造開始実績日(to)を取得します。
	 * @return 製造開始実績日(to)
	 */
	public String getResultSdateDayTo() {
		return resultSdateDayTo;
	}

	/**
	 * 製造開始実績日(to)を設定します。
	 * @param resultSdateDayTo 製造開始実績日(to)
	 */
	public void setResultSdateDayTo(final String resultSdateDayTo) {
		this.resultSdateDayTo = resultSdateDayTo;
	}

	/**
	 * 製造開始実績時(to)を取得します。
	 * @return 製造開始実績時(to)
	 */
	public String getResultSdateTimeTo() {
		return resultSdateTimeTo;
	}

	/**
	 * 製造開始実績時(to)を設定します。
	 * @param resultSdateTimeTo 製造開始実績時(to)
	 */
	public void setResultSdateTimeTo(final String resultSdateTimeTo) {
		this.resultSdateTimeTo = resultSdateTimeTo;
	}

	/**
	 * 製造終了実績日(to)を取得します。
	 * @return 製造終了実績日(to)
	 */
	public String getResultEdateDayTo() {
		return resultEdateDayTo;
	}

	/**
	 * 製造終了実績日(to)を設定します。
	 * @param resultEdateDayTo 製造終了実績日(to)
	 */
	public void setResultEdateDayTo(final String resultEdateDayTo) {
		this.resultEdateDayTo = resultEdateDayTo;
	}

	/**
	 * 製造終了実績時(to)を取得します。
	 * @return 製造終了実績時(to)
	 */
	public String getResultEdateTimeTo() {
		return resultEdateTimeTo;
	}

	/**
	 * 製造終了実績時(to)を設定します。
	 * @param resultEdateTimeTo 製造終了実績時(to)
	 */
	public void setResultEdateTimeTo(final String resultEdateTimeTo) {
		this.resultEdateTimeTo = resultEdateTimeTo;
	}

	/**
	 * EXCELダウンロードフラグを取得します。
	 * @return boolean EXCELダウンロードフラグ
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * EXCELダウンロードフラグを設定します。
	 * @param excelDownloadFlg EXCELダウンロードフラグ
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * excelReportDownloadFlgを取得します。
	 * @return excelReportDownloadFlg
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * excelReportDownloadFlgを設定します。
	 * @param excelReportDownloadFlg excelReportDownloadFlg
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public DirectionResultListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final DirectionResultListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * compoundTankNoを取得します。
	 * @return compoundTankNo
	 */
	public String getCompoundTankNo() {
		return compoundTankNo;
	}

	/**
	 * compoundTankNoを設定します。
	 * @param compoundTankNo compoundTankNo
	 */
	public void setCompoundTankNo(final String compoundTankNo) {
		this.compoundTankNo = compoundTankNo;
	}
	

	/**
	 * reCheckNumを取得します。
	 * @return compoundTankNo
	 */
	public int getReCheckNum() {
		return reCheckNum;
	}

	/**
	 * reCheckNumを設定します。
	 * @param compoundTankNo compoundTankNo
	 */
	public void setReCheckNum(int reCheckNum) {
		this.reCheckNum = reCheckNum;
	}

}

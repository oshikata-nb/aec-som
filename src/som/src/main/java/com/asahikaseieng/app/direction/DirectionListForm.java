/*
 * Created on 2009/02/18
 *
 * $copyright$
 */
package com.asahikaseieng.app.direction;

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
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製造指図検索検索 Formクラス.
 * @author tosco
 * 
 */
public final class DirectionListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 日付チェックフォーマット */
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

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

	/** 開始予定日(From) */
	private String planedSdateDayFrom;

	/** 開始予定時(From) */
	private String planedSdateTimeFrom;

	/** 終了予定日(From) */
	private String planedEdateDayFrom;

	/** 終了予定時(From) */
	private String planedEdateTimeFrom;

	/** 開始予定日(To) */
	private String planedSdateDayTo;

	/** 開始予定時(To) */
	private String planedSdateTimeTo;

	/** 終了予定日(To) */
	private String planedEdateDayTo;

	/** 終了予定時(To) */
	private String planedEdateTimeTo;

	/** 品目名称 */
	private String itemName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** APSオーダーコード */
	private String aspOrderNo;

	/** リスト */
	private List<DirectionDirectionHeaderList> searchList;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** エラーメッセージ */
	private String errMsg;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 手持ち在庫不足通知フラグ */
	private String insufficientFlag;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private DirectionOrderListConditionForReport reportCondition;

	/** 調合タンクNo  */
	private String compoundTankNo;

	/**
	 * コンストラクタ
	 */
	public DirectionListForm() {
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
	protected Class<DirectionDirectionHeaderListPagerCondition> getPagerConditionClass() {
		return DirectionDirectionHeaderListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
			clearSelectedCheck();
		}
		if ("reFresh".equals(getOp())) {
			clearSelectedCheck();
		}
		// チェックボックスをクリア
		// clearSelectedCheck();
		setExcelReportDownloadFlg(false);
	}

	/**
	 * 選択チェックボックスを全てクリアする。
	 */
	public void clearSelectedCheck() {
		for (DirectionDirectionHeaderList bean : searchList) {
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
			setSearchList(new ArrayList<DirectionDirectionHeaderList>());
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
		// 開始予定日(From)
		Date startFrom = checkDate(errors, planedSdateDayFrom,
			planedSdateTimeFrom, "item.direction.planed.sdate.date.from", true);
		Date startTo = checkDate(errors, planedSdateDayTo, planedSdateTimeTo,
			"item.direction.planed.sdate.date.to", false);
		Date endFrom = checkDate(errors, planedEdateDayFrom,
			planedEdateTimeFrom, "item.direction.planed.edate.date.from", true);
		Date endTo = checkDate(errors, planedEdateDayTo, planedEdateTimeTo,
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
		setSearchList(new ArrayList<DirectionDirectionHeaderList>());
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
		// 開始予定日(From)
		setPlanedSdateDayFrom(null);
		// 開始予定時(From)
		setPlanedSdateTimeFrom(null);
		// 終了予定日(From)
		setPlanedEdateDayFrom(null);
		// 終了予定時(From)
		setPlanedEdateTimeFrom(null);
		// 開始予定日(To)
		setPlanedSdateDayTo(null);
		// 開始予定時(To)
		setPlanedSdateTimeTo(null);
		// 終了予定日(To)
		setPlanedEdateDayTo(null);
		// 終了予定時(To)
		setPlanedEdateTimeTo(null);
		// 生産ラインコンボボックス
		setLineCombo(null);
		// エラーメッセージ
		setErrMsg(null);
		// ASPオーダーコード
		setAspOrderNo(null);
		// エクセルダウンロードフラグ
		this.setExcelDownloadFlg(Boolean.FALSE);

		setInsufficientFlag("false");
		setExcelReportDownloadFlg(false);
		setReportCondition(null);
	}

	/**
	 * 開始予定日時(From)を取得する。
	 * @return 開始予定日時(From)
	 */
	public String getPlanedSdateDateFrom() {
		return getDateTime(planedSdateDayFrom, planedSdateTimeFrom, true);
	}

	/**
	 * 開始予定日時(To)を取得する。
	 * @return 開始予定日時(To)
	 */
	public String getPlanedSdateDateTo() {
		return getDateTime(planedSdateDayTo, planedSdateTimeTo, false);
	}

	/**
	 * 終了予定日時(From)を取得する。
	 * @return 終了予定日時(From)
	 */
	public String getPlanedEdateDateFrom() {
		return getDateTime(planedEdateDayFrom, planedEdateTimeFrom, true);
	}

	/**
	 * 終了予定日時(To)を取得する。
	 * @return 終了予定日時(To)
	 */
	public String getPlanedEdateDateTo() {
		return getDateTime(planedEdateDayTo, planedEdateTimeTo, false);
	}

	// getter,setter--------------------------------------------------------------------

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<DirectionDirectionHeaderList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(
			final List<DirectionDirectionHeaderList> searchList) {
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
	 * 開始予定日(From)を取得します。
	 * @return 開始予定日(From)
	 */
	public String getPlanedSdateDayFrom() {
		return planedSdateDayFrom;
	}

	/**
	 * 開始予定日(From)を設定します。
	 * @param planedSdateDayFrom 開始予定日(From)
	 */
	public void setPlanedSdateDayFrom(final String planedSdateDayFrom) {
		this.planedSdateDayFrom = planedSdateDayFrom;
	}

	/**
	 * 開始予定時(From)を取得します。
	 * @return 開始予定時(From)
	 */
	public String getPlanedSdateTimeFrom() {
		return planedSdateTimeFrom;
	}

	/**
	 * 開始予定時(From)を設定します。
	 * @param planedSdateTimeFrom 開始予定時(From)
	 */
	public void setPlanedSdateTimeFrom(final String planedSdateTimeFrom) {
		this.planedSdateTimeFrom = planedSdateTimeFrom;
	}

	/**
	 * 終了予定日(From)を取得します。
	 * @return 終了予定日(From)
	 */
	public String getPlanedEdateDayFrom() {
		return planedEdateDayFrom;
	}

	/**
	 * 終了予定日(From)を設定します。
	 * @param planedEdateDayFrom 終了予定日(From)
	 */
	public void setPlanedEdateDayFrom(final String planedEdateDayFrom) {
		this.planedEdateDayFrom = planedEdateDayFrom;
	}

	/**
	 * 終了予定時(From)を取得します。
	 * @return 終了予定時(From)
	 */
	public String getPlanedEdateTimeFrom() {
		return planedEdateTimeFrom;
	}

	/**
	 * 終了予定時(From)を設定します。
	 * @param planedEdateTimeFrom 終了予定時(From)
	 */
	public void setPlanedEdateTimeFrom(final String planedEdateTimeFrom) {
		this.planedEdateTimeFrom = planedEdateTimeFrom;
	}

	/**
	 * 開始予定日(to)を取得します。
	 * @return 開始予定日(to)
	 */
	public String getPlanedSdateDayTo() {
		return planedSdateDayTo;
	}

	/**
	 * 開始予定日(to)を設定します。
	 * @param planedSdateDayTo 開始予定日(to)
	 */
	public void setPlanedSdateDayTo(final String planedSdateDayTo) {
		this.planedSdateDayTo = planedSdateDayTo;
	}

	/**
	 * 開始予定時(to)を取得します。
	 * @return 開始予定時(to)
	 */
	public String getPlanedSdateTimeTo() {
		return planedSdateTimeTo;
	}

	/**
	 * 開始予定時(to)を設定します。
	 * @param planedSdateTimeTo 開始予定時(to)
	 */
	public void setPlanedSdateTimeTo(final String planedSdateTimeTo) {
		this.planedSdateTimeTo = planedSdateTimeTo;
	}

	/**
	 * 終了予定日(to)を取得します。
	 * @return 終了予定日(to)
	 */
	public String getPlanedEdateDayTo() {
		return planedEdateDayTo;
	}

	/**
	 * 終了予定日(to)を設定します。
	 * @param planedEdateDayTo 終了予定日(to)
	 */
	public void setPlanedEdateDayTo(final String planedEdateDayTo) {
		this.planedEdateDayTo = planedEdateDayTo;
	}

	/**
	 * 終了予定時(to)を取得します。
	 * @return 終了予定時(to)
	 */
	public String getPlanedEdateTimeTo() {
		return planedEdateTimeTo;
	}

	/**
	 * 終了予定時(to)を設定します。
	 * @param planedEdateTimeTo 終了予定時(to)
	 */
	public void setPlanedEdateTimeTo(final String planedEdateTimeTo) {
		this.planedEdateTimeTo = planedEdateTimeTo;
	}

	/**
	 * エラーメッセージを取得します。
	 * @return エラーメッセージ
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * エラーメッセージを設定します。
	 * @param errMsg エラーメッセージ
	 */
	public void setErrMsg(final String errMsg) {
		this.errMsg = errMsg;
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
	 * insufficientFlagを取得します。
	 * @return insufficientFlag
	 */
	public String getInsufficientFlag() {
		return insufficientFlag;
	}

	/**
	 * insufficientFlagを設定します。
	 * @param insufficientFlag insufficientFlag
	 */
	public void setInsufficientFlag(final String insufficientFlag) {
		this.insufficientFlag = insufficientFlag;
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
	public DirectionOrderListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final DirectionOrderListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * ASPオーダーコードを取得します。
	 * @return ASPオーダーコード
	 */
	public String getAspOrderNo() {
		return aspOrderNo;
	}

	/**
	 * ASPオーダーコードを設定します。
	 * @param aspOrderNo ASPオーダーコード
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
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
}

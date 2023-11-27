/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.beforehandmeltlbl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblList;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblPagerCondition;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 予備溶解ラベル発行画面 Formクラス.
 * @author tosco
 */
public final class BeforehandMeltLblListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59";

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用
	//

	/** 検索入力：指図番号 */
	private String srhDirectionNo;

	/** 検索入力：生産ライン */
	private String srhProductionLine;

	/** 検索入力：主要製品コード */
	private String srhItemCd;

	/** 検索入力：主要製品名称 */
	private String srhItemName;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：製造開始予定日(FROM) */
	private String srhPlanedSDayFrom;

	/** 検索入力：製造開始予定日(TO) */
	private String srhPlanedSDayTo;

	/** 検索入力：製造開始予定時(FROM) */
	private String srhPlanedSTimeFrom;

	/** 検索入力：製造開始予定時(TO) */
	private String srhPlanedSTimeTo;

	/** 検索入力：製造終了予定日(FROM) */
	private String srhPlanedEDayFrom;

	/** 検索入力：製造終了予定日(TO) */
	private String srhPlanedEDayTo;

	/** 検索入力：製造終了予定時(FROM) */
	private String srhPlanedETimeFrom;

	/** 検索入力：製造終了予定時(TO) */
	private String srhPlanedETimeTo;

	/** 検索入力：生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** エラーメッセージ */
	private String errMsg;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private BeforehandMeltLblListConditionForReport reportCondition;

	//
	// インスタンスフィールド
	//

	/** リスト */
	private List<BeforehandMeltLblList> searchList;

	/**
	 * コンストラクタ
	 */
	public BeforehandMeltLblListForm() {
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
		return BeforehandMeltLblPagerCondition.class;
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

		if ("issue".equals(getOp())) {
			clearCheck();
		}
		setExcelReportDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			// イレギュラーだけど、ここでリストをクリア
			setSearchList(new ArrayList<BeforehandMeltLblList>());
		}

		if ("search".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// validateメソッドによる入力チェック
			validateSearchList(errors);
		}
		return errors;
	}

	/**
	 * 検索ボタン押下時の入力チェック
	 * @param errors エラー内容
	 */
	private void validateSearchList(final ActionErrors errors) {
		String strSrhPlanedSdateFrom = null;
		String strSrhPlanedSdateTo = null;
		String strSrhPlanedEdateFrom = null;
		String strSrhPlanedEdateTo = null;
		Timestamp planedSdateFrom = null;
		Timestamp planedSdateTo = null;
		Timestamp planedEdateFrom = null;
		Timestamp planedEdateTo = null;
		String strFormat = "yyyy/MM/dd HH:mm";
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		// 製造開始予定日時(FROM)
		if (!StringUtils.isEmpty(getSrhPlanedSDayFrom())) {
			strSrhPlanedSdateFrom = getSrhPlanedSDayFrom();
			if (!StringUtils.isEmpty(getSrhPlanedSTimeFrom())) {
				strSrhPlanedSdateFrom = strSrhPlanedSdateFrom + " "
						+ getSrhPlanedSTimeFrom();
			} else {
				strSrhPlanedSdateFrom = strSrhPlanedSdateFrom + " "
						+ STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhPlanedSdateFrom, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.datetime",
									rb
											.getString("item.beforehandmeltlbl.planed.sdate.from")));
			} else {
				planedSdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strSrhPlanedSdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhPlanedSTimeFrom())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.time.notinputday",
									rb
											.getString("item.beforehandmeltlbl.planed.sdate.from")));
			}
		}
		// 製造開始予定日時(To)
		if (!StringUtils.isEmpty(getSrhPlanedSDayTo())) {
			strSrhPlanedSdateTo = getSrhPlanedSDayTo();
			if (!StringUtils.isEmpty(getSrhPlanedSTimeTo())) {
				strSrhPlanedSdateTo = strSrhPlanedSdateTo + " "
						+ getSrhPlanedSTimeTo();
			} else {
				strSrhPlanedSdateTo = strSrhPlanedSdateTo + " " + STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhPlanedSdateTo, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.datetime",
									rb
											.getString("item.beforehandmeltlbl.planed.sdate.to")));
			} else {
				planedSdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strSrhPlanedSdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhPlanedSTimeTo())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.time.notinputday",
									rb
											.getString("item.beforehandmeltlbl.planed.sdate.to")));
			}
		}
		// 製造終了予定日時(FROM)
		if (!StringUtils.isEmpty(getSrhPlanedEDayFrom())) {
			strSrhPlanedEdateFrom = getSrhPlanedEDayFrom();
			if (!StringUtils.isEmpty(getSrhPlanedETimeFrom())) {
				strSrhPlanedEdateFrom = strSrhPlanedEdateFrom + " "
						+ getSrhPlanedETimeFrom();
			} else {
				strSrhPlanedEdateFrom = strSrhPlanedEdateFrom + " "
						+ STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhPlanedEdateFrom, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.datetime",
									rb
											.getString("item.beforehandmeltlbl.planed.edate.from")));
			} else {
				planedEdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strSrhPlanedEdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhPlanedETimeFrom())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.time.notinputday",
									rb
											.getString("item.beforehandmeltlbl.planed.edate.from")));
			}
		}
		// 製造開始予定日時(To)
		if (!StringUtils.isEmpty(getSrhPlanedEDayTo())) {
			strSrhPlanedEdateTo = getSrhPlanedEDayTo();
			if (!StringUtils.isEmpty(getSrhPlanedETimeTo())) {
				strSrhPlanedEdateTo = strSrhPlanedEdateTo + " "
						+ getSrhPlanedETimeTo();
			} else {
				strSrhPlanedEdateTo = strSrhPlanedEdateTo + " " + STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhPlanedEdateTo, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.datetime",
									rb
											.getString("item.beforehandmeltlbl.planed.edate.to")));
			} else {
				planedEdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strSrhPlanedEdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhPlanedETimeTo())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.time.notinputday",
									rb
											.getString("item.beforehandmeltlbl.planed.edate.to")));
			}
		}

		// 製造開始予定日時(FROM)と製造開始予定日時(FROM)の大小関係チェック
		if (planedSdateFrom != null && planedSdateTo != null) {
			if (planedSdateFrom.compareTo(planedSdateTo) > 0) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.datetime.compare",
									rb
											.getString("item.beforehandmeltlbl.planed.sdate.from"),
									rb
											.getString("item.beforehandmeltlbl.planed.sdate.to")));
			}
		}

		// 製造終了予定日時(FROM)と製造終了予定日時(FROM)の大小関係チェック
		if (planedEdateFrom != null && planedEdateTo != null) {
			if (planedEdateFrom.compareTo(planedEdateTo) > 0) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.beforehandmeltlbl.datetime.compare",
									rb
											.getString("item.beforehandmeltlbl.planed.edate.from"),
									rb
											.getString("item.beforehandmeltlbl.planed.edate.to")));
			}
		}
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (BeforehandMeltLblList bean : getSearchList()) {
				bean.setBeforehandMeltLblCheckBox(Boolean.FALSE);
			}
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<BeforehandMeltLblList>());

		// 検索入力：指図番号
		setSrhDirectionNo(null);
		// 検索入力：生産ライン
		setSrhProductionLine(null);
		// 検索入力：主要製品コード
		setSrhItemCd(null);
		// 検索入力：主要製品名称
		setSrhItemName(null);
		// 検索入力：他社コード１
		setSrhOtherCompanyCd1(null);
		// 検索入力：製造開始予定日(FROM)
		setSrhPlanedSDayFrom(null);
		// 検索入力：製造開始予定日(TO)
		setSrhPlanedSDayTo(null);
		// 検索入力：製造開始予定時(FROM)
		setSrhPlanedSTimeFrom(null);
		// 検索入力：製造開始予定時(TO)
		setSrhPlanedSTimeTo(null);
		// 検索入力：製造終了予定日(FROM)
		setSrhPlanedEDayFrom(null);
		// 検索入力：製造終了予定日(TO)
		setSrhPlanedEDayTo(null);
		// 検索入力：製造終了予定時(FROM)
		setSrhPlanedETimeFrom(null);
		// 検索入力：製造終了予定時(TO)
		setSrhPlanedETimeTo(null);
		// 生産工場コンボボックス
		setLineCombo(null);
		// エラーメッセージ
		setErrMsg(null);
		// エクセルダウンロードフラグ
		setExcelDownloadFlg(Boolean.FALSE);
		setExcelReportDownloadFlg(false);
		setReportCondition(null);
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<BeforehandMeltLblList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<BeforehandMeltLblList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

	/**
	 * 検索入力：指図番号取得.
	 * @return String 指図番号
	 */
	public String getSrhDirectionNo() {
		return this.srhDirectionNo;
	}

	/**
	 * 検索入力：指図番号設定.
	 * @param srhDirectionNo 指図番号
	 */
	public void setSrhDirectionNo(final String srhDirectionNo) {
		this.srhDirectionNo = srhDirectionNo;
	}

	/**
	 * 検索入力：生産ライン取得.
	 * @return String 生産ライン
	 */
	public String getSrhProductionLine() {
		return this.srhProductionLine;
	}

	/**
	 * 検索入力：生産ライン設定.
	 * @param srhProductionLine 生産ライン
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * 検索入力：主要製品コード取得.
	 * @return String 主要製品コード
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力：主要製品コード設定.
	 * @param srhItemCd 主要製品コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 検索入力：他社コード１取得.
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：製造開始予定日(FROM)取得.
	 * @return String 製造開始予定日(FROM)
	 */
	public String getSrhPlanedSDayFrom() {
		return this.srhPlanedSDayFrom;
	}

	/**
	 * 検索入力：製造開始予定日(FROM)設定
	 * @param srhPlanedSDayFrom 製造開始予定日(FROM)
	 */
	public void setSrhPlanedSDayFrom(final String srhPlanedSDayFrom) {
		this.srhPlanedSDayFrom = srhPlanedSDayFrom;
	}

	/**
	 * 検索入力：製造開始予定日(TO)取得.
	 * @return String 製造開始予定日(TO)
	 */
	public String getSrhPlanedSDayTo() {
		return this.srhPlanedSDayTo;
	}

	/**
	 * 検索入力：製造開始予定日(TO)設定
	 * @param srhPlanedSDayTo 製造開始予定日(TO)
	 */
	public void setSrhPlanedSDayTo(final String srhPlanedSDayTo) {
		this.srhPlanedSDayTo = srhPlanedSDayTo;
	}

	/**
	 * 検索入力：製造開始予定時(FROM)取得.
	 * @return String 製造開始予定時(FROM)
	 */
	public String getSrhPlanedSTimeFrom() {
		return this.srhPlanedSTimeFrom;
	}

	/**
	 * 検索入力：製造開始予定時(FROM)設定
	 * @param srhPlanedSTimeFrom 製造開始予定日(FROM)
	 */
	public void setSrhPlanedSTimeFrom(final String srhPlanedSTimeFrom) {
		this.srhPlanedSTimeFrom = srhPlanedSTimeFrom;
	}

	/**
	 * 検索入力：製造開始予定時(TO)取得.
	 * @return String 製造開始予定時(TO)
	 */
	public String getSrhPlanedSTimeTo() {
		return this.srhPlanedSTimeTo;
	}

	/**
	 * 検索入力：製造開始予定時(TO)設定
	 * @param srhPlanedSTimeTo 製造開始予定時(TO)
	 */
	public void setSrhPlanedSTimeTo(final String srhPlanedSTimeTo) {
		this.srhPlanedSTimeTo = srhPlanedSTimeTo;
	}

	/**
	 * 検索入力：製造終了予定日(FROM)取得.
	 * @return String 製造終了予定日(FROM)
	 */
	public String getSrhPlanedEDayFrom() {
		return this.srhPlanedEDayFrom;
	}

	/**
	 * 検索入力：製造終了予定日(FROM)設定
	 * @param srhPlanedEDayFrom 製造終了予定日(FROM)
	 */
	public void setSrhPlanedEDayFrom(final String srhPlanedEDayFrom) {
		this.srhPlanedEDayFrom = srhPlanedEDayFrom;
	}

	/**
	 * 検索入力：製造終了予定日(TO)取得.
	 * @return String 製造終了予定日(TO)
	 */
	public String getSrhPlanedEDayTo() {
		return this.srhPlanedEDayTo;
	}

	/**
	 * 検索入力：製造終了予定日(TO)設定
	 * @param srhPlanedEDayTo 製造終了予定日(TO)
	 */
	public void setSrhPlanedEDayTo(final String srhPlanedEDayTo) {
		this.srhPlanedEDayTo = srhPlanedEDayTo;
	}

	/**
	 * 検索入力：製造終了予定時(FROM)取得.
	 * @return String 製造終了予定時
	 */
	public String getSrhPlanedETimeFrom() {
		return this.srhPlanedETimeFrom;
	}

	/**
	 * 検索入力：製造終了予定時(FROM)設定
	 * @param srhPlanedETimeFrom 製造終了予定時(FROM)
	 */
	public void setSrhPlanedETimeFrom(final String srhPlanedETimeFrom) {
		this.srhPlanedETimeFrom = srhPlanedETimeFrom;
	}

	/**
	 * 検索入力：製造終了予定時(TO)取得.
	 * @return String 製造終了予定時
	 */
	public String getSrhPlanedETimeTo() {
		return this.srhPlanedETimeTo;
	}

	/**
	 * 検索入力：製造終了予定時(TO)設定
	 * @param srhPlanedETimeTo 製造終了予定時(TO)
	 */
	public void setSrhPlanedETimeTo(final String srhPlanedETimeTo) {
		this.srhPlanedETimeTo = srhPlanedETimeTo;
	}

	/**
	 * 生産工場コンボボックスを取得します。
	 * @return List<ComboBoxItems> 生産工場コンボボックス
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
	 * 主要製品名称 を取得します。
	 * @return srhItemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 主要製品名称 を設定します。
	 * @param srhItemName 主要製品名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
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
	public BeforehandMeltLblListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final BeforehandMeltLblListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

}

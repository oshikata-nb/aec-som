/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.productinspectcomp;

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
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompList;
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompPagerCondition;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製品検査完了入力一覧 Formクラス.
 * @author tosco
 * 
 */
public final class ProductInspectCompListForm extends AbstractSearchForm {

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
	// 検索用.
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

	/** 検索入力：包装開始実績日(FROM) */
	private String srhResultSDayFrom;

	/** 検索入力：包装開始実績日(TO) */
	private String srhResultSDayTo;

	/** 検索入力：包装開始実績時(FROM) */
	private String srhResultSTimeFrom;

	/** 検索入力：包装開始実績時(TO) */
	private String srhResultSTimeTo;

	/** 検索入力：包装終了実績日(FROM) */
	private String srhResultEDayFrom;

	/** 検索入力：包装終了実績日(TO) */
	private String srhResultEDayTo;

	/** 検索入力：包装終了実績時(FROM) */
	private String srhResultETimeFrom;

	/** 検索入力：包装終了実績時(TO) */
	private String srhResultETimeTo;

	/** 検索入力：指図ステータス */
	private String srhDirectionStatus;

	/** 検索入力：包装ライン */
	private String srhPackageLine;

	/** 検索入力：包装ライン名称 */
	private String srhPackageLineName;

	/** 検索入力：検査合格日 */
	private String srhCertificationDate;

	/** 検索入力：ロット番号 */
	private String srhLotNo;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private ProductInspectCompListConditionForReport reportCondition;

	//
	// インスタンスフィールド.
	//

	/** リスト */
	private List<ProductInspectCompList> searchList;

	/**
	 * コンストラクタ
	 */
	public ProductInspectCompListForm() {
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
		return ProductInspectCompPagerCondition.class;
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

		if ("update".equals(getOp())) {
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
			setSearchList(new ArrayList<ProductInspectCompList>());
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// validateメソッドによる入力チェック
			validateSearchList(errors);
		}

		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// validateメソッドによる入力チェック
			validateUpdateList(errors);
		}
		return errors;
	}

	/**
	 * 検索処理の入力チェック
	 * @param errors エラー内容
	 */
	private void validateSearchList(final ActionErrors errors) {
		String strSrhResultSdateFrom = null;
		String strSrhResultSdateTo = null;
		String strSrhResultEdateFrom = null;
		String strSrhResultEdateTo = null;
		Timestamp resultSdateFrom = null;
		Timestamp resultSdateTo = null;
		Timestamp resultEdateFrom = null;
		Timestamp resultEdateTo = null;
		String strFormat = "yyyy/MM/dd HH:mm";
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		// 包装開始実績日時(FROM)
		if (!StringUtils.isEmpty(getSrhResultSDayFrom())) {
			strSrhResultSdateFrom = getSrhResultSDayFrom();
			if (!StringUtils.isEmpty(getSrhResultSTimeFrom())) {
				strSrhResultSdateFrom = strSrhResultSdateFrom + " "
						+ getSrhResultSTimeFrom();
			} else {
				strSrhResultSdateFrom = strSrhResultSdateFrom + " "
						+ STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhResultSdateFrom, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.datetime",
									rb
											.getString("item.productinspectcomp.result.sdate.from")));
			} else {
				resultSdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strSrhResultSdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhResultSTimeFrom())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.time.notinputday",
									rb
											.getString("item.productinspectcomp.result.sdate.from")));
			}
		}
		// 包装開始実績日時(To)
		if (!StringUtils.isEmpty(getSrhResultSDayTo())) {
			strSrhResultSdateTo = getSrhResultSDayTo();
			if (!StringUtils.isEmpty(getSrhResultSTimeTo())) {
				strSrhResultSdateTo = strSrhResultSdateTo + " "
						+ getSrhResultSTimeTo();
			} else {
				strSrhResultSdateTo = strSrhResultSdateTo + " " + STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhResultSdateTo, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.datetime",
									rb
											.getString("item.productinspectcomp.result.sdate.to")));
			} else {
				resultSdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strSrhResultSdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhResultSTimeTo())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.time.notinputday",
									rb
											.getString("item.productinspectcomp.result.sdate.to")));
			}
		}
		// 包装終了実績日時(FROM)
		if (!StringUtils.isEmpty(getSrhResultEDayFrom())) {
			strSrhResultEdateFrom = getSrhResultEDayFrom();
			if (!StringUtils.isEmpty(getSrhResultETimeFrom())) {
				strSrhResultEdateFrom = strSrhResultEdateFrom + " "
						+ getSrhResultETimeFrom();
			} else {
				strSrhResultEdateFrom = strSrhResultEdateFrom + " "
						+ STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhResultEdateFrom, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.datetime",
									rb
											.getString("item.productinspectcomp.result.edate.from")));
			} else {
				resultEdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strSrhResultEdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhResultETimeFrom())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.time.notinputday",
									rb
											.getString("item.productinspectcomp.result.edate.from")));
			}
		}
		// 包装開始実績日時(To)
		if (!StringUtils.isEmpty(getSrhResultEDayTo())) {
			strSrhResultEdateTo = getSrhResultEDayTo();
			if (!StringUtils.isEmpty(getSrhResultETimeTo())) {
				strSrhResultEdateTo = strSrhResultEdateTo + " "
						+ getSrhResultETimeTo();
			} else {
				strSrhResultEdateTo = strSrhResultEdateTo + " " + STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strSrhResultEdateTo, strFormat)) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.datetime",
									rb
											.getString("item.productinspectcomp.result.edate.to")));
			} else {
				resultEdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strSrhResultEdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getSrhResultETimeTo())) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.time.notinputday",
									rb
											.getString("item.productinspectcomp.result.edate.to")));
			}
		}

		// 包装開始実績日時(FROM)と包装開始実績日時(FROM)の大小関係チェック
		if (resultSdateFrom != null && resultSdateTo != null) {
			if (resultSdateFrom.compareTo(resultSdateTo) > 0) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.datetime.compare",
									rb
											.getString("item.productinspectcomp.result.sdate.from"),
									rb
											.getString("item.productinspectcomp.result.sdate.to")));
			}
		}

		// 包装終了実績日時(FROM)と包装終了実績日時(FROM)の大小関係チェック
		if (resultEdateFrom != null && resultEdateTo != null) {
			if (resultEdateFrom.compareTo(resultEdateTo) > 0) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.productinspectcomp.datetime.compare",
									rb
											.getString("item.productinspectcomp.result.edate.from"),
									rb
											.getString("item.productinspectcomp.result.edate.to")));
			}
		}
	}

	/**
	 * 更新処理の入力チェック
	 * @param errors エラー内容
	 */
	private void validateUpdateList(final ActionErrors errors) {

	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (ProductInspectCompList bean : getSearchList()) {
				bean.setProductInspectCompCheckBox(Boolean.FALSE);
			}
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<ProductInspectCompList>());

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
		// 検索入力：包装開始実績日(FROM)
		setSrhResultSDayFrom(null);
		// 検索入力：包装開始実績日(TO)
		setSrhResultSDayTo(null);
		// 検索入力：包装開始実績時(FROM)
		setSrhResultSTimeFrom(null);
		// 検索入力：包装開始実績時(TO)
		setSrhResultSTimeTo(null);
		// 検索入力：包装終了実績日(FROM)
		setSrhResultEDayFrom(null);
		// 検索入力：包装終了実績日(TO)
		setSrhResultEDayTo(null);
		// 検索入力：包装終了実績時(FROM)
		setSrhResultETimeFrom(null);
		// 検索入力：包装終了実績時(TO)
		setSrhResultETimeTo(null);
		// 検索入力：指図ステータス
		setSrhDirectionStatus(null);
		// 生産工場コンボボックス
		setLineCombo(null);
		// 検索入力：指図ステータス
		setSrhDirectionStatus(null);
		// 検索入力：包装ライン
		setSrhPackageLine(null);
		// 検索入力：包装ライン名称
		setSrhPackageLineName(null);
		// 検索入力：検査合格日
		setSrhCertificationDate(null);
		// 検索入力：ロット番号
		setSrhLotNo(null);
		setExcelReportDownloadFlg(false);
		setReportCondition(null);
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<ProductInspectCompList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ProductInspectCompList> searchList) {
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
	 * 検索入力：包装開始実績日(FROM)取得.
	 * @return String 包装開始実績日(FROM)
	 */
	public String getSrhResultSDayFrom() {
		return this.srhResultSDayFrom;
	}

	/**
	 * 検索入力：包装開始実績日(FROM)設定
	 * @param srhResultSDayFrom 包装開始実績日(FROM)
	 */
	public void setSrhResultSDayFrom(final String srhResultSDayFrom) {
		this.srhResultSDayFrom = srhResultSDayFrom;
	}

	/**
	 * 検索入力：包装開始実績日(TO)取得.
	 * @return String 包装開始実績日(TO)
	 */
	public String getSrhResultSDayTo() {
		return this.srhResultSDayTo;
	}

	/**
	 * 検索入力：包装開始実績日(TO)設定
	 * @param srhResultSDayTo 包装開始実績日(TO)
	 */
	public void setSrhResultSDayTo(final String srhResultSDayTo) {
		this.srhResultSDayTo = srhResultSDayTo;
	}

	/**
	 * 検索入力：包装開始実績時(FROM)取得.
	 * @return String 包装開始実績時(FROM)
	 */
	public String getSrhResultSTimeFrom() {
		return this.srhResultSTimeFrom;
	}

	/**
	 * 検索入力：包装開始実績時(FROM)設定
	 * @param srhResultSTimeFrom 開始実績日(FROM)
	 */
	public void setSrhResultSTimeFrom(final String srhResultSTimeFrom) {
		this.srhResultSTimeFrom = srhResultSTimeFrom;
	}

	/**
	 * 検索入力：包装開始実績時(TO)取得.
	 * @return String 開始実績時(TO)
	 */
	public String getSrhResultSTimeTo() {
		return this.srhResultSTimeTo;
	}

	/**
	 * 検索入力：包装開始実績時(TO)設定
	 * @param srhResultSTimeTo 開始実績時(TO)
	 */
	public void setSrhResultSTimeTo(final String srhResultSTimeTo) {
		this.srhResultSTimeTo = srhResultSTimeTo;
	}

	/**
	 * 検索入力：包装終了実績日(FROM)取得.
	 * @return String 終了実績日(FROM)
	 */
	public String getSrhResultEDayFrom() {
		return this.srhResultEDayFrom;
	}

	/**
	 * 検索入力：包装終了実績日(FROM)設定
	 * @param srhResultEDayFrom 終了実績日(FROM)
	 */
	public void setSrhResultEDayFrom(final String srhResultEDayFrom) {
		this.srhResultEDayFrom = srhResultEDayFrom;
	}

	/**
	 * 検索入力：包装終了実績日(TO)取得.
	 * @return String 終了実績日(TO)
	 */
	public String getSrhResultEDayTo() {
		return this.srhResultEDayTo;
	}

	/**
	 * 検索入力：包装終了実績日(TO)設定
	 * @param srhResultEDayTo 終了実績日(TO)
	 */
	public void setSrhResultEDayTo(final String srhResultEDayTo) {
		this.srhResultEDayTo = srhResultEDayTo;
	}

	/**
	 * 検索入力：包装終了実績時(FROM)取得.
	 * @return String 終了実績時
	 */
	public String getSrhResultETimeFrom() {
		return this.srhResultETimeFrom;
	}

	/**
	 * 検索入力：包装終了実績時(FROM)設定
	 * @param srhResultETimeFrom 終了実績時(FROM)
	 */
	public void setSrhResultETimeFrom(final String srhResultETimeFrom) {
		this.srhResultETimeFrom = srhResultETimeFrom;
	}

	/**
	 * 検索入力：包装終了実績時(TO)取得.
	 * @return String 終了実績時
	 */
	public String getSrhResultETimeTo() {
		return this.srhResultETimeTo;
	}

	/**
	 * 検索入力：包装終了実績時(TO)設定
	 * @param srhResultETimeTo 終了実績時(TO)
	 */
	public void setSrhResultETimeTo(final String srhResultETimeTo) {
		this.srhResultETimeTo = srhResultETimeTo;
	}

	/**
	 * 検索入力：指図ステータスを取得します。
	 * @return srhDirectionStatus
	 */
	public String getSrhDirectionStatus() {
		return srhDirectionStatus;
	}

	/**
	 * 検索入力：指図ステータスを設定します。
	 * @param srhDirectionStatus 指図ステータス
	 */
	public void setSrhDirectionStatus(final String srhDirectionStatus) {
		this.srhDirectionStatus = srhDirectionStatus;
	}

	/**
	 * 検索入力：生産工場コンボボックスを取得します。
	 * @return List<ComboBoxItems> 生産工場コンボボックス
	 */
	public List<ComboBoxItems> getLineCombo() {
		return lineCombo;
	}

	/**
	 * 検索入力：生産工場コンボボックスを設定します。
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
	 * 検索入力：主要製品名称 を取得します。
	 * @return srhItemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 検索入力：主要製品名称 を設定します。
	 * @param srhItemName 主要製品名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索入力：包装ラインを取得します。
	 * @return srhPackageLine
	 */
	public String getSrhPackageLine() {
		return srhPackageLine;
	}

	/**
	 * 検索入力：包装ラインを設定します。
	 * @param srhPackageLine 包装ライン
	 */
	public void setSrhPackageLine(final String srhPackageLine) {
		this.srhPackageLine = srhPackageLine;
	}

	/**
	 * 検索入力：包装ライン名称を取得します。
	 * @return srhPackageLineName
	 */
	public String getSrhPackageLineName() {
		return srhPackageLineName;
	}

	/**
	 * 検索入力：包装ライン名称を設定します。
	 * @param srhPackageLineName 包装ライン名称
	 */
	public void setSrhPackageLineName(final String srhPackageLineName) {
		this.srhPackageLineName = srhPackageLineName;
	}

	/**
	 * 検索入力：検査合格日を取得します。
	 * @return srhCertificationDate
	 */
	public String getSrhCertificationDate() {
		return srhCertificationDate;
	}

	/**
	 * 検索入力：検査合格日を設定します。
	 * @param srhCertificationDate 検査合格日
	 */
	public void setSrhCertificationDate(final String srhCertificationDate) {
		this.srhCertificationDate = srhCertificationDate;
	}

	/**
	 * 検索入力：ロット番号を取得します。
	 * @return srhLotNo
	 */
	public String getSrhLotNo() {
		return srhLotNo;
	}

	/**
	 * 検索入力：ロット番号を設定します。
	 * @param srhLotNo ロット番号
	 */
	public void setSrhLotNo(final String srhLotNo) {
		this.srhLotNo = srhLotNo;
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
	public ProductInspectCompListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final ProductInspectCompListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}
}

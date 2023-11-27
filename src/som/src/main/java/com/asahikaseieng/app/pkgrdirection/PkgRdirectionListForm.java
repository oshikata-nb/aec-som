/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 包装実績－検索画面 Formクラス.
 * @author tosco
 * 
 */
public final class PkgRdirectionListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 最大ラベル指定枚数 */
	private static final int MAX_LABEL_COUNT = 9999;

	/** ページの明細行数 */
	private static final int PAGE_ROW = Integer
			.parseInt(Constants.RB_SYSTEM_PROPERTIES.getString("linage.common"));

	/** 最大データ数 */
	private static final int DATA_ROW = Integer
			.parseInt(Constants.RB_SYSTEM_PROPERTIES
					.getString("threshold.common"));

	//
	// インスタンスフィールド
	//

	/** 指図区分 */
	private String directionDivision;

	/** 包装指図番号 */
	private String directionNo;

	/** 生産工場 */
	private String productionLine;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** ステータス */
	private String directionStatus;

	/** 包装開始実績日(FROM) */
	private String resultSDayFrom;

	/** 包装開始実績日(TO) */
	private String resultSDayTo;

	/** 包装開始実績時(FROM) */
	private String resultSTimeFrom;

	/** 包装開始実績時(TO) */
	private String resultSTimeTo;

	/** 包装終了実績日(FROM) */
	private String resultEDayFrom;

	/** 包装終了実績日(TO) */
	private String resultEDayTo;

	/** 包装終了実績時(FROM) */
	private String resultETimeFrom;

	/** 包装終了実績時(TO) */
	private String resultETimeTo;

	/** ロット番号 */
	private String lotNo;

	/** 検索リスト */
	private List<PkgRdirectionList> searchList;

	/** 指図区分コンボボックス */
	private List<ComboBoxItems> directionDivisionCombo;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** ステータスコンボボックス */
	private List<ComboBoxItems> statusCombo;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private PkgRdirectionListConditionForReport reportCondition;

	/** 包装ライン */
	private String packageLine;
	
	/** 日付再チェック条件保持 */
	public int reCheckNum;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionListForm() {
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
	protected Class<PkgRdirectionListPagerCondition> getPagerConditionClass() {
		return PkgRdirectionListPagerCondition.class;
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
		this.clearCheck();
		setExcelReportDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<PkgRdirectionList>());
		}

		if ("search".equals(getOp())) {
			errors = new ActionErrors();
			/* 検索時のチェック */
			validateSearchList(errors);
		}
		if ("issueDoc".equals(getOp())) {
			errors = new ActionErrors();
			/* 包装記録行時のチェック */
			validateIssueDoc(errors);
		}
		if ("issueLabel".equals(getOp())) {
			errors = new ActionErrors();
			/* ラベル発行時のチェック */
			validateIssueLabel(errors);
		}
		return errors;
	}

	/**
	 * 初期化
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<PkgRdirectionList>());

		/** 指図区分 */
		setDirectionDivision(null);

		/** 包装指図番号 */
		setDirectionNo(null);

		/** 生産工場 */
		setProductionLine(null);

		/** 品目コード */
		setItemCd(null);

		/** 品目名称 */
		setItemName(null);

		/** 他社コード１ */
		setOtherCompanyCd1(null);

		/** ステータス */
		setDirectionStatus(null);

		/** 包装開始予定日(FROM) */
		setResultSDayFrom(null);

		/** 包装開始予定日(TO) */
		setResultSDayTo(null);

		/** 包装開始予定時(FROM) */
		setResultSTimeFrom(null);

		/** 包装開始予定時(TO) */
		setResultSTimeTo(null);

		/** 包装終了予定日(FROM) */
		setResultEDayFrom(null);

		/** 包装終了予定日(TO) */
		setResultEDayTo(null);

		/** 包装終了予定時(FROM) */
		setResultETimeFrom(null);

		/** 包装終了予定時(TO) */
		setResultETimeTo(null);

		/** ロット番号 */
		setLotNo(null);

		/** 指図区分コンボボックス */
		setDirectionDivisionCombo(null);

		/** 生産工場コンボボックス */
		setLineCombo(null);

		/** ステータスコンボボックス */
		setStatusCombo(null);

		/** エクセルダウンロードフラグ */
		this.setExcelDownloadFlg(Boolean.FALSE);

		setExcelReportDownloadFlg(false);
		setReportCondition(null);

		setPackageLine(null);
		
		/**日付再チェック条件保持**/
		setReCheckNum(0);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (PkgRdirectionList bean : getSearchList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * 検索処理のチェック
	 * @param errors エラー内容
	 */
	private void validateSearchList(final ActionErrors errors) {
		String strPlanedSdateFrom = null;
		String strPlanedSdateTo = null;
		String strPlanedEdateFrom = null;
		String strPlanedEdateTo = null;
		Timestamp planedSdateFrom = null;
		Timestamp planedSdateTo = null;
		Timestamp planedEdateFrom = null;
		Timestamp planedEdateTo = null;
		String strFormat = "yyyy/MM/dd HH:mm";
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		// 包装開始日時(FROM)
		if (!StringUtils.isEmpty(getResultSDayFrom())) {
			strPlanedSdateFrom = getResultSDayFrom();
			if (!StringUtils.isEmpty(getResultSTimeFrom())) {
				strPlanedSdateFrom = strPlanedSdateFrom + " "
						+ getResultSTimeFrom();
			} else {
				strPlanedSdateFrom = strPlanedSdateFrom + " "
						+ PkgRdirectionConst.STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedSdateFrom, strFormat)) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.datetime",
						rb.getString("item.pkgrdirection.planed.sdate.from")));
			} else {
				planedSdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedSdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getResultSTimeFrom())) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.time.notinputday",
						rb.getString("item.pkgrdirection.planed.sdate.from")));
			}
		}
		// 包装開始日時(To)
		if (!StringUtils.isEmpty(getResultSDayTo())) {
			strPlanedSdateTo = getResultSDayTo();
			if (!StringUtils.isEmpty(getResultSTimeTo())) {
				strPlanedSdateTo = strPlanedSdateTo + " " + getResultSTimeTo();
			} else {
				strPlanedSdateTo = strPlanedSdateTo + " "
						+ PkgRdirectionConst.STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedSdateTo, strFormat)) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.datetime",
						rb.getString("item.pkgrdirection.planed.sdate.to")));
			} else {
				planedSdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedSdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getResultSTimeTo())) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.time.notinputday",
						rb.getString("item.pkgrdirection.planed.sdate.to")));
			}
		}
		// 包装終了日時(FROM)
		if (!StringUtils.isEmpty(getResultEDayFrom())) {
			strPlanedEdateFrom = getResultEDayFrom();
			if (!StringUtils.isEmpty(getResultETimeFrom())) {
				strPlanedEdateFrom = strPlanedEdateFrom + " "
						+ getResultETimeFrom();
			} else {
				strPlanedEdateFrom = strPlanedEdateFrom + " "
						+ PkgRdirectionConst.STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedEdateFrom, strFormat)) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.datetime",
						rb.getString("item.pkgrdirection.planed.edate.from")));
			} else {
				planedEdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedEdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getResultETimeFrom())) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.time.notinputday",
						rb.getString("item.pkgrdirection.planed.edate.from")));
			}
		}
		// 包装開始日時(To)
		if (!StringUtils.isEmpty(getResultEDayTo())) {
			strPlanedEdateTo = getResultEDayTo();
			if (!StringUtils.isEmpty(getResultETimeTo())) {
				strPlanedEdateTo = strPlanedEdateTo + " " + getResultETimeTo();
			} else {
				strPlanedEdateTo = strPlanedEdateTo + " "
						+ PkgRdirectionConst.STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedEdateTo, strFormat)) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.datetime",
						rb.getString("item.pkgrdirection.planed.edate.to")));
			} else {
				planedEdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedEdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getResultETimeTo())) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.time.notinputday",
						rb.getString("item.pkgrdirection.planed.edate.to")));
			}
		}

		// 包装開始日時(FROM)と包装開始日時(FROM)の大小関係チェック
		if (planedSdateFrom != null && planedSdateTo != null) {
			if (planedSdateFrom.compareTo(planedSdateTo) > 0) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.datetime.compare",
						rb.getString("item.pkgrdirection.planed.sdate.from"),
						rb.getString("item.pkgrdirection.planed.sdate.to")));
			}
		}

		// 包装終了日時(FROM)と包装終了日時(FROM)の大小関係チェック
		if (planedEdateFrom != null && planedEdateTo != null) {
			if (planedEdateFrom.compareTo(planedEdateTo) > 0) {
				errors.add("", new ActionMessage(
						"errors.pkgrdirection.datetime.compare",
						rb.getString("item.pkgrdirection.planed.edate.from"),
						rb.getString("item.pkgrdirection.planed.edate.to")));
			}
		}
	}

	/**
	 * 包装記録発行処理のチェック
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateIssueDoc(final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		List<PkgRdirectionList> list = this.getSearchList();
		int index = 1;

		for (PkgRdirectionList bean : list) {
			if (!bean.isCheckFlg()) {
				index++;
				continue;
			}

			// ロット番号 半角英数字チェック
			if (!StringUtils.isEmpty(bean.getLotNo())) {
				if (!AecStringUtils.isSomLot(bean.getLotNo())) {
					errors.add("", new ActionMessage(
							"errors.pkgrdirection.alphameric.code.row", Integer
									.toString(index), rb
									.getString("item.pkgrdirection.lot.no")));
				}
			}
			index++;
		}
	}

	/**
	 * ラベル発行処理のチェック
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateIssueLabel(final ActionErrors errors) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		List<PkgRdirectionList> list = this.getSearchList();
		int index = 1;

		for (PkgRdirectionList bean : list) {
			if (!bean.isCheckFlg()) {
				index++;
				continue;
			}

			// ラベル枚数のチェック
			if (!StringUtils.isEmpty(bean.getLabelCount())) {
				String labelCount = "";

				// カンマを除去する
				StringTokenizer st = new StringTokenizer(bean.getLabelCount(),
						",");
				while (st.hasMoreTokens()) {
					labelCount += st.nextToken();
				}

				// 数値チェック
				if (!AecStringUtils.isNumNumeric(labelCount)) {
					errors.add("", new ActionMessage("errors.number.row", rb
							.getString("item.pkgrdirection.label.count"),
							Integer.toString(index)));
				} else {

					// 入力範囲チェック
					if (Integer.parseInt(labelCount) < 0
							|| Integer.parseInt(labelCount) > MAX_LABEL_COUNT) {
						errors.add("", new ActionMessage("errors.rang.row", rb
								.getString("item.pkgrdirection.label.count"),
								"0", "9,999", Integer.toString(index)));
					}
				}
			}
			index++;
		}
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 検索リスト取得.
	 * @return List<PkgRdirectionList> 検索リスト
	 */
	public List<PkgRdirectionList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索リスト設定.
	 * @param searchList 検索リスト
	 */
	public void setSearchList(final List<PkgRdirectionList> searchList) {
		this.searchList = searchList;
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
	 * 指図区分取得.
	 * @return String 指図区分
	 */
	public String getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分設定.
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final String directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 包装指図番号取得.
	 * @return String 包装指図番号
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 包装指図番号設定.
	 * @param directionNo 包装指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 生産工場取得.
	 * @return String 生産工場
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産工場設定.
	 * @param productionLine 生産工場
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得.
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード１取得.
	 * @return 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１設定.
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * ステータス取得.
	 * @return String ステータス
	 */
	public String getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * ステータス設定
	 * @param directionStatus ステータス
	 */
	public void setDirectionStatus(final String directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * 包装開始予定日(FROM)取得.
	 * @return String 包装開始予定日(FROM)
	 */
	public String getResultSDayFrom() {
		return this.resultSDayFrom;
	}

	/**
	 * 包装開始予定日(FROM)設定
	 * @param planedSDayFrom 包装開始予定日(FROM)
	 */
	public void setResultSDayFrom(final String planedSDayFrom) {
		this.resultSDayFrom = planedSDayFrom;
	}

	/**
	 * 包装開始予定日(TO)取得.
	 * @return String 包装開始予定日(TO)
	 */
	public String getResultSDayTo() {
		return this.resultSDayTo;
	}

	/**
	 * 包装開始予定日(TO)設定
	 * @param planedSDayTo 包装開始予定日(TO)
	 */
	public void setResultSDayTo(final String planedSDayTo) {
		this.resultSDayTo = planedSDayTo;
	}

	/**
	 * 包装開始予定時(FROM)取得.
	 * @return String 包装開始予定時(FROM)
	 */
	public String getResultSTimeFrom() {
		return this.resultSTimeFrom;
	}

	/**
	 * 包装開始予定時(FROM)設定
	 * @param planedSTimeFrom 包装開始予定日(FROM)
	 */
	public void setResultSTimeFrom(final String planedSTimeFrom) {
		this.resultSTimeFrom = planedSTimeFrom;
	}

	/**
	 * 包装開始予定時(TO)取得.
	 * @return String 包装開始予定時(TO)
	 */
	public String getResultSTimeTo() {
		return this.resultSTimeTo;
	}

	/**
	 * 包装開始予定時(TO)設定
	 * @param planedSTimeTo 包装開始予定時(TO)
	 */
	public void setResultSTimeTo(final String planedSTimeTo) {
		this.resultSTimeTo = planedSTimeTo;
	}

	/**
	 * 包装終了予定日(FROM)取得.
	 * @return String 包装終了予定日(FROM)
	 */
	public String getResultEDayFrom() {
		return this.resultEDayFrom;
	}

	/**
	 * 包装終了予定日(FROM)設定
	 * @param planedEDayFrom 包装終了予定日(FROM)
	 */
	public void setResultEDayFrom(final String planedEDayFrom) {
		this.resultEDayFrom = planedEDayFrom;
	}

	/**
	 * 包装終了予定日(TO)取得.
	 * @return String 包装終了予定日(TO)
	 */
	public String getResultEDayTo() {
		return this.resultEDayTo;
	}

	/**
	 * 包装終了予定日(TO)設定
	 * @param planedEDayTo 包装終了予定日(TO)
	 */
	public void setResultEDayTo(final String planedEDayTo) {
		this.resultEDayTo = planedEDayTo;
	}

	/**
	 * 包装終了予定時(FROM)取得.
	 * @return String 包装終了予定時
	 */
	public String getResultETimeFrom() {
		return this.resultETimeFrom;
	}

	/**
	 * 包装終了予定時(FROM)設定
	 * @param planedETimeFrom 包装終了予定時(FROM)
	 */
	public void setResultETimeFrom(final String planedETimeFrom) {
		this.resultETimeFrom = planedETimeFrom;
	}

	/**
	 * 包装終了予定時(TO)取得.
	 * @return String ステータス
	 */
	public String getResultETimeTo() {
		return this.resultETimeTo;
	}

	/**
	 * 包装終了予定時(TO)設定
	 * @param planedETimeTo 包装終了予定時(TO)
	 */
	public void setResultETimeTo(final String planedETimeTo) {
		this.resultETimeTo = planedETimeTo;
	}

	/**
	 * ロット番号取得.
	 * @return String ロット番号
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロット番号設定
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 指図区分コンボボックスを取得します。
	 * @return List<ComboBoxItems> 指図区分コンボボックス
	 */
	public List<ComboBoxItems> getDirectionDivisionCombo() {
		return directionDivisionCombo;
	}

	/**
	 * 指図区分コンボボックスを設定します。
	 * @param directionDivisionCombo 指図区分コンボボックス
	 */
	public void setDirectionDivisionCombo(
			final List<ComboBoxItems> directionDivisionCombo) {
		this.directionDivisionCombo = directionDivisionCombo;
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
	 * ステータスコンボボックスを取得します。
	 * @return List<ComboBoxItems> ステータスコンボボックス
	 */
	public List<ComboBoxItems> getStatusCombo() {
		return statusCombo;
	}

	/**
	 * ステータスコンボボックスを設定します。
	 * @param statusCombo ステータスコンボボックス
	 */
	public void setStatusCombo(final List<ComboBoxItems> statusCombo) {
		this.statusCombo = statusCombo;
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
	public PkgRdirectionListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final PkgRdirectionListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * packageLineを取得します。
	 * @return packageLine
	 */
	public String getPackageLine() {
		return packageLine;
	}

	/**
	 * packageLineを設定します。
	 * @param packageLine packageLine
	 */
	public void setPackageLine(final String packageLine) {
		this.packageLine = packageLine;
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

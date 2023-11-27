/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

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
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 包装指図－検索画面 Formクラス.
 * @author tosco
 * 
 */
public final class PkgDirectionListForm extends AbstractSearchForm {

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

	/** 包装開始予定日(FROM) */
	private String planedSDayFrom;

	/** 包装開始予定日(TO) */
	private String planedSDayTo;

	/** 包装開始予定時(FROM) */
	private String planedSTimeFrom;

	/** 包装開始予定時(TO) */
	private String planedSTimeTo;

	/** 包装終了予定日(FROM) */
	private String planedEDayFrom;

	/** 包装終了予定日(TO) */
	private String planedEDayTo;

	/** 包装終了予定時(FROM) */
	private String planedETimeFrom;

	/** 包装終了予定時(TO) */
	private String planedETimeTo;

	/** APSオーダーコード */
	private String aspOrderNo;

	/** 包装ライン */
	private String packageLine;

	/** 検索リスト */
	private List<PkgDirectionList> searchList;

	/** 指図区分コンボボックス */
	private List<ComboBoxItems> directionDivisionCombo;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** ステータスコンボボックス */
	private List<ComboBoxItems> statusCombo;

	/** エラーメッセージ */
	private String errMsg;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 出力対象包装指図番号リスト */
	private ArrayList<String> printDirectionNoList;

	private String insufficientFlag;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private PkgDirectionOrderListConditionForReport reportCondition;

	/**
	 * コンストラクタ.包装指図－検索画面 Formクラス
	 */
	public PkgDirectionListForm() {
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
	protected Class<PkgDirectionListPagerCondition> getPagerConditionClass() {
		return PkgDirectionListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
			// チェックボックスをクリア
			this.clearCheck();

		}
		if ("reFresh".equals(getOp())) {
			// チェックボックスをクリア
			this.clearCheck();
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
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<PkgDirectionList>());
		}

		if ("search".equals(getOp())) {
			errors = new ActionErrors();
			/* 検索時のチェック */
			validateSearchList(errors);
		}
		if ("issueDirection".equals(getOp())) {
			errors = new ActionErrors();
			/* 包装指図書発行時のチェック */
			validateIssueDirection(errors);
		}
		if ("preIssuance".equals(getOp())) {
			errors = new ActionErrors();
			/* 包装指図書発行時のチェック */
			validateIssueDirection(errors);
		}
		if ("issueLabel".equals(getOp())) {
			errors = new ActionErrors();
			/* ラベル発行時のチェック */
			validateIssueLabel(errors);
		}
		return errors;
	}

	/**
	 * 初期化..包装指図－検索画面 Formクラス
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<PkgDirectionList>());

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
		setPlanedSDayFrom(null);

		/** 包装開始予定日(TO) */
		setPlanedSDayTo(null);

		/** 包装開始予定時(FROM) */
		setPlanedSTimeFrom(null);

		/** 包装開始予定時(TO) */
		setPlanedSTimeTo(null);

		/** 包装終了予定日(FROM) */
		setPlanedEDayFrom(null);

		/** 包装終了予定日(TO) */
		setPlanedEDayTo(null);

		/** 包装終了予定時(FROM) */
		setPlanedETimeFrom(null);

		/** 包装終了予定時(TO) */
		setPlanedETimeTo(null);

		/** アスプローバオーダーコード */
		setAspOrderNo(null);

		/** 指図区分コンボボックス */
		setDirectionDivisionCombo(null);

		/** 生産工場コンボボックス */
		setLineCombo(null);

		/** ステータスコンボボックス */
		setStatusCombo(null);

		/** 包装ライン */
		setPackageLine(null);

		/** エラーメッセージ */
		setErrMsg(null);

		/** エクセルダウンロードフラグ */
		this.setExcelDownloadFlg(Boolean.FALSE);

		/** 出力対象包装指図番号リスト */
		this.setPrintDirectionNoList(null);

		this.setInsufficientFlag("false");
		setExcelReportDownloadFlg(false);
		setReportCondition(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (PkgDirectionList bean : getSearchList()) {
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

		// 包装開始予定日時(FROM)
		if (!StringUtils.isEmpty(getPlanedSDayFrom())) {
			strPlanedSdateFrom = getPlanedSDayFrom();
			if (!StringUtils.isEmpty(getPlanedSTimeFrom())) {
				strPlanedSdateFrom = strPlanedSdateFrom + " "
						+ getPlanedSTimeFrom();
			} else {
				strPlanedSdateFrom = strPlanedSdateFrom + " "
						+ PkgDirectionConst.STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedSdateFrom, strFormat)) {
				errors.add("", new ActionMessage(
						"errors.pkgdirection.datetime",
						rb.getString("item.pkgdirection.planed.sdate.from")));
			} else {
				planedSdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedSdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getPlanedSTimeFrom())) {
				errors.add("", new ActionMessage(
						"errors.pkgdirection.time.notinputday",
						rb.getString("item.pkgdirection.planed.sdate.from")));
			}
		}
		// 包装開始予定日時(To)
		if (!StringUtils.isEmpty(getPlanedSDayTo())) {
			strPlanedSdateTo = getPlanedSDayTo();
			if (!StringUtils.isEmpty(getPlanedSTimeTo())) {
				strPlanedSdateTo = strPlanedSdateTo + " " + getPlanedSTimeTo();
			} else {
				strPlanedSdateTo = strPlanedSdateTo + " "
						+ PkgDirectionConst.STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedSdateTo, strFormat)) {
				errors.add("",
					new ActionMessage("errors.pkgdirection.datetime", rb
							.getString("item.pkgdirection.planed.sdate.to")));
			} else {
				planedSdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedSdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getPlanedSTimeTo())) {
				errors.add("",
					new ActionMessage("errors.pkgdirection.time.notinputday",
							rb.getString("item.pkgdirection.planed.sdate.to")));
			}
		}
		// 包装終了予定日時(FROM)
		if (!StringUtils.isEmpty(getPlanedEDayFrom())) {
			strPlanedEdateFrom = getPlanedEDayFrom();
			if (!StringUtils.isEmpty(getPlanedETimeFrom())) {
				strPlanedEdateFrom = strPlanedEdateFrom + " "
						+ getPlanedETimeFrom();
			} else {
				strPlanedEdateFrom = strPlanedEdateFrom + " "
						+ PkgDirectionConst.STR_MIN_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedEdateFrom, strFormat)) {
				errors.add("", new ActionMessage(
						"errors.pkgdirection.datetime",
						rb.getString("item.pkgdirection.planed.edate.from")));
			} else {
				planedEdateFrom = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedEdateFrom, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getPlanedETimeFrom())) {
				errors.add("", new ActionMessage(
						"errors.pkgdirection.time.notinputday",
						rb.getString("item.pkgdirection.planed.edate.from")));
			}
		}
		// 包装開始予定日時(To)
		if (!StringUtils.isEmpty(getPlanedEDayTo())) {
			strPlanedEdateTo = getPlanedEDayTo();
			if (!StringUtils.isEmpty(getPlanedETimeTo())) {
				strPlanedEdateTo = strPlanedEdateTo + " " + getPlanedETimeTo();
			} else {
				strPlanedEdateTo = strPlanedEdateTo + " "
						+ PkgDirectionConst.STR_MAX_TIME;
			}
			// 日付チェック
			if (!AecDateUtils.chkDate(strPlanedEdateTo, strFormat)) {
				errors.add("",
					new ActionMessage("errors.pkgdirection.datetime", rb
							.getString("item.pkgdirection.planed.edate.to")));
			} else {
				planedEdateTo = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedEdateTo, strFormat);
			}
		} else {
			if (!StringUtils.isEmpty(getPlanedETimeTo())) {
				errors.add("",
					new ActionMessage("errors.pkgdirection.time.notinputday",
							rb.getString("item.pkgdirection.planed.edate.to")));
			}
		}

		// 包装開始予定日時(FROM)と包装開始予定日時(FROM)の大小関係チェック
		if (planedSdateFrom != null && planedSdateTo != null) {
			if (planedSdateFrom.compareTo(planedSdateTo) > 0) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.pkgdirection.datetime.compare",
									rb
											.getString("item.pkgdirection.planed.sdate.from"),
									rb
											.getString("item.pkgdirection.planed.sdate.to")));
			}
		}

		// 包装終了予定日時(FROM)と包装終了予定日時(FROM)の大小関係チェック
		if (planedEdateFrom != null && planedEdateTo != null) {
			if (planedEdateFrom.compareTo(planedEdateTo) > 0) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.pkgdirection.datetime.compare",
									rb
											.getString("item.pkgdirection.planed.edate.from"),
									rb
											.getString("item.pkgdirection.planed.edate.to")));
			}
		}
	}

	/**
	 * 包装指図書発行処理のチェック
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateIssueDirection(final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		List<PkgDirectionList> list = this.getSearchList();
		int index = 1;

		for (PkgDirectionList bean : list) {
			if (!bean.isCheckFlg()) {
				index++;
				continue;
			}

			// ロット番号 半角英数字チェック
			if (!StringUtils.isEmpty(bean.getLotNo())) {
				if (!AecStringUtils.isSomLot(bean.getLotNo())) {
					errors.add("", new ActionMessage(
							"errors.pkgdirection.alphameric.code.row", Integer
									.toString(index), rb
									.getString("item.pkgdirection.lot.no")));
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
		List<PkgDirectionList> list = this.getSearchList();
		int index = 1;

		for (PkgDirectionList bean : list) {
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
							.getString("item.pkgdirection.label.count"),
							Integer.toString(index)));
				} else {

					// 入力範囲チェック
					if (Integer.parseInt(labelCount) < 0
							|| Integer.parseInt(labelCount) > MAX_LABEL_COUNT) {
						errors.add("", new ActionMessage("errors.rang.row", rb
								.getString("item.pkgdirection.label.count"),
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
	 * @return List<PkgDirectionList> 検索リスト
	 */
	public List<PkgDirectionList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索リスト設定.
	 * @param searchList 検索リスト
	 */
	public void setSearchList(final List<PkgDirectionList> searchList) {
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
	public String getPlanedSDayFrom() {
		return this.planedSDayFrom;
	}

	/**
	 * 包装開始予定日(FROM)設定
	 * @param planedSDayFrom 包装開始予定日(FROM)
	 */
	public void setPlanedSDayFrom(final String planedSDayFrom) {
		this.planedSDayFrom = planedSDayFrom;
	}

	/**
	 * 包装開始予定日(TO)取得.
	 * @return String 包装開始予定日(TO)
	 */
	public String getPlanedSDayTo() {
		return this.planedSDayTo;
	}

	/**
	 * 包装開始予定日(TO)設定
	 * @param planedSDayTo 包装開始予定日(TO)
	 */
	public void setPlanedSDayTo(final String planedSDayTo) {
		this.planedSDayTo = planedSDayTo;
	}

	/**
	 * 包装開始予定時(FROM)取得.
	 * @return String 包装開始予定時(FROM)
	 */
	public String getPlanedSTimeFrom() {
		return this.planedSTimeFrom;
	}

	/**
	 * 包装開始予定時(FROM)設定
	 * @param planedSTimeFrom 包装開始予定日(FROM)
	 */
	public void setPlanedSTimeFrom(final String planedSTimeFrom) {
		this.planedSTimeFrom = planedSTimeFrom;
	}

	/**
	 * 包装開始予定時(TO)取得.
	 * @return String 包装開始予定時(TO)
	 */
	public String getPlanedSTimeTo() {
		return this.planedSTimeTo;
	}

	/**
	 * 包装開始予定時(TO)設定
	 * @param planedSTimeTo 包装開始予定時(TO)
	 */
	public void setPlanedSTimeTo(final String planedSTimeTo) {
		this.planedSTimeTo = planedSTimeTo;
	}

	/**
	 * 包装終了予定日(FROM)取得.
	 * @return String 包装終了予定日(FROM)
	 */
	public String getPlanedEDayFrom() {
		return this.planedEDayFrom;
	}

	/**
	 * 包装終了予定日(FROM)設定
	 * @param planedEDayFrom 包装終了予定日(FROM)
	 */
	public void setPlanedEDayFrom(final String planedEDayFrom) {
		this.planedEDayFrom = planedEDayFrom;
	}

	/**
	 * 包装終了予定日(TO)取得.
	 * @return String 包装終了予定日(TO)
	 */
	public String getPlanedEDayTo() {
		return this.planedEDayTo;
	}

	/**
	 * 包装終了予定日(TO)設定
	 * @param planedEDayTo 包装終了予定日(TO)
	 */
	public void setPlanedEDayTo(final String planedEDayTo) {
		this.planedEDayTo = planedEDayTo;
	}

	/**
	 * 包装終了予定時(FROM)取得.
	 * @return String 包装終了予定時
	 */
	public String getPlanedETimeFrom() {
		return this.planedETimeFrom;
	}

	/**
	 * 包装終了予定時(FROM)設定
	 * @param planedETimeFrom 包装終了予定時(FROM)
	 */
	public void setPlanedETimeFrom(final String planedETimeFrom) {
		this.planedETimeFrom = planedETimeFrom;
	}

	/**
	 * 包装終了予定時(TO)取得.
	 * @return String ステータス
	 */
	public String getPlanedETimeTo() {
		return this.planedETimeTo;
	}

	/**
	 * 包装終了予定時(TO)設定
	 * @param planedETimeTo 包装終了予定時(TO)
	 */
	public void setPlanedETimeTo(final String planedETimeTo) {
		this.planedETimeTo = planedETimeTo;
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
	 * 出力対象包装指図番号リストを取得します。
	 * @return printDirectionNoList
	 */
	public ArrayList<String> getPrintDirectionNoList() {
		return printDirectionNoList;
	}

	/**
	 * 出力対象包装指図番号リストを設定します。
	 * 
	 * @param printDirectionNoList 出力対象製造指図番号リスト
	 */
	public void setPrintDirectionNoList(
			final ArrayList<String> printDirectionNoList) {
		this.printDirectionNoList = printDirectionNoList;
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
	public PkgDirectionOrderListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final PkgDirectionOrderListConditionForReport reportCondition) {
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
}

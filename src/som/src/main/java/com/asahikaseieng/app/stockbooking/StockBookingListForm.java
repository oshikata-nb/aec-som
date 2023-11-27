/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.stockbooking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingList;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingPagerCondition;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 検査待ち在庫計上一覧 Formクラス.
 * @author tosco
 * 
 */
public final class StockBookingListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.
	//

	/** 検索入力：生産ライン */
	private String srhProductionLine;

	/** 検索入力：包装実績日付(From) */
	private String srhResultEdateFrom;

	/** 検索入力：包装実績日付(To) */
	private String srhResultEdateTo;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** 検索結果リスト */
	private List<StockBookingList> searchList;

	/** エラーメッセージ */
	private String errMsg;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private StockBookingListConditionForReport reportCondition;

	//
	// インスタンスフィールド.
	//

	/**
	 * コンストラクタ
	 */
	public StockBookingListForm() {
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
		return StockBookingPagerCondition.class;
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
			setSearchList(new ArrayList<StockBookingList>());
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// validateメソッドによる入力チェック
			validateSearchList(errors);
		}

		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// validateメソッドによる入力チェック
			validateUpdateList(request, errors);
		}
		return errors;
	}

	/**
	 * 検索処理の入力チェック
	 * @param errors エラー内容
	 */
	private void validateSearchList(final ActionErrors errors) {

	}

	/**
	 * 更新処理の入力チェック
	 * @param request HttpServletRequest
	 * @param errors エラー内容
	 */
	private void validateUpdateList(final HttpServletRequest request,
			final ActionErrors errors) {

		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		List<StockBookingList> list = this.getSearchList();
		int index = 1;

		// 数値チェック
		for (StockBookingList bean : list) {
			if (bean.isStockBookingCheckBox()) {

				if (!StringUtils.isEmpty(bean.getStrSumSuuryou())) {
					CheckDigitUtilsLogic check = CheckDigitUtil
							.getCheckDigitUtils(request);

					// カンマを取り除く
					String value = StringUtils.replace(bean.getStrSumSuuryou(),
						",", "");

					// 数値チェックを行う
					CheckDigitResult result = check.checkDigit(bean
							.getUnitOfOperationManagement(), value);
					// チェックの結果取得
					int code = result.getCode();

					if (code != CheckDigitUtilsLogic.SUCCESS) {
						// チェック結果がチェックOKではない場合
						NumberChkDisitDetail detail = result.getDetail();
						switch (code) {
						case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT: // 数値ではない場合
							errors
									.add(
										"",
										new ActionMessage(
												"errors.digit.number.row",
												rb
														.getString("item.stockbooking.sumsuuryou"),
												Integer.toString(index)));
							break;

						case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH: // 最大値を超えた場合
							errors
									.add(
										"",
										new ActionMessage(
												"errors.digit.maxlength.row",
												rb
														.getString("item.stockbooking.sumsuuryou"),
												detail.getMaxLength()
														.toString(), Integer
														.toString(index)));
							break;

						case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:// 整数部桁数エラー
							errors
									.add(
										"",
										new ActionMessage(
												"errors.digit.integer.row",
												rb
														.getString("item.stockbooking.sumsuuryou"),
												detail.getIntegerLength()
														.toString(), Integer
														.toString(index)));
							break;

						case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:// 小数部桁数エラー
							errors
									.add(
										"",
										new ActionMessage(
												"errors.digit.decimal.row",
												rb
														.getString("item.stockbooking.sumsuuryou"),
												detail.getSmallnumLength()
														.toString(), Integer
														.toString(index)));
							break;

						case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE: // 範囲チェックエラー
							errors
									.add(
										"",
										new ActionMessage(
												"errors.digit.rang.row",
												rb
														.getString("item.stockbooking.sumsuuryou"),
												detail.getLowerLimit()
														.toString(), detail
														.getUpperLimit()
														.toString(), Integer
														.toString(index)));
							break;

						default:
							break;
						}

					} else {
						// 数値チェックの結果がチェックOKの場合
						// カンマを取り除く
						String strSumSuuryo = StringUtils.replace(bean
								.getStrSumSuuryou(), ",", "");
						bean.setSumSuuryou(new BigDecimal(strSumSuuryo));

						if (bean.getResultQty() != null) {
							// 実績生産量がnullではなかったら
							// 物流-生産 を計算
							BigDecimal subtractionQty = bean.getSumSuuryou()
									.subtract(bean.getResultQty());

							// 計算結果が0以外であればエラーメッセージを登録
							if (!(subtractionQty.compareTo(new BigDecimal(0)) == 0)) {
								errors
										.add(
											"",
											new ActionMessage(
													"errors.stockbooking.subtraction.qty.not.zero",
													rb
															.getString("item.stockbooking.subtraction.qty"),
													Integer.toString(index)));
							}
						} else {
							// 実績生産量がnullだったらエラー登録
							errors
									.add(
										"",
										new ActionMessage(
												"errors.stockbooking.result.qty.null",
												rb
														.getString("item.stockbooking.result.qty"),
												Integer.toString(index)));
						}

					}
				} else { // 物流入庫実績 入力がnullの場合
					errors.add("", new ActionMessage("errors.required.row", rb
							.getString("item.stockbooking.sumsuuryou"), Integer
							.toString(index)));
				}
			}
			index++;
		}
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (StockBookingList bean : getSearchList()) {
				bean.setStockBookingCheckBox(Boolean.FALSE);
			}
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<StockBookingList>());

		// 検索入力：生産ライン
		setSrhProductionLine(null);
		// 検索入力：包装実績日付(From)
		setSrhResultEdateFrom(null);
		// 検索入力：包装実績日付(To)
		setSrhResultEdateTo(null);
		// エラーメッセージ
		setErrMsg(null);
		setExcelReportDownloadFlg(false);
		setReportCondition(null);
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
	 * 検索結果を取得します。
	 * @return searchList
	 */
	public List<StockBookingList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果を設定します。
	 * @param searchList 検索結果
	 */
	public void setSearchList(final List<StockBookingList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

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
	 * 検索入力：包装実績日付(From)を取得します。
	 * @return srhResultEdateFrom
	 */
	public String getSrhResultEdateFrom() {
		return srhResultEdateFrom;
	}

	/**
	 * 検索入力：包装実績日付(From)を設定します。
	 * @param srhResultEdateFrom 検索入力：包装実績日付(From)
	 */
	public void setSrhResultEdateFrom(final String srhResultEdateFrom) {
		this.srhResultEdateFrom = srhResultEdateFrom;
	}

	/**
	 * 検索入力：包装実績日付(To)を取得します。
	 * @return srhResultSdateTo
	 */
	public String getSrhResultEdateTo() {
		return srhResultEdateTo;
	}

	/**
	 * 検索入力：包装実績日付(To)を設定します。
	 * @param srhResultSdateTo 検索入力：包装実績日付(To)
	 */
	public void setSrhResultEdateTo(final String srhResultSdateTo) {
		this.srhResultEdateTo = srhResultSdateTo;
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
	public StockBookingListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final StockBookingListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}
}

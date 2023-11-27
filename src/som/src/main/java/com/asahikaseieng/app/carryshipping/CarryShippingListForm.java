/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.carryshipping;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingList;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingPagerCondition;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 運送店別出荷指図画面 Formクラス.
 * 19/11/29 AECS佐藤　運送店別出荷指図の最大表示件数30件→500件（変更）
 * @author tosco
 * 
 */
public final class CarryShippingListForm extends AbstractSearchForm {

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
		
		//19/11/29 AECS佐藤(変更)
		PAGE_ROW = Integer.parseInt(rb.getString("linage.carryshipping.list"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.
	//
	
	/** 検索入力：出荷指図日 　
	 * 
	 * 　　指図作成後検索にて使用　検索条件に使用するのは
	 * 　　出荷予定日の為Action側でセットする際に出荷予定日をセット
	 * 　　SHIPPING_TEMPテーブルの出荷予定日のカラム名がShippingInstructDateとなっているので注意。
	* 
	 * 　　参考
	 * 　　SHIPPINGテーブル
	 * 　　ScheduledShippingDate　：　出荷予定日
	 * 　　ShippingInstructDate  ：  出荷指図日
	 * 
	 * 　　SHIPPING_TEMPテーブル
	 * 　　ShippingInstructDate  ：  出荷予定日
	 * 　　
	 * */
	private String srhShippingInstructDate;

	/** 検索入力：出荷予定日 */
	private String srhScheduledShippingDate;

	/** ログ出力用エラーコード */
	private String errorCd;

	/** ログ出力用エラーメッセージ */
	private String errorMsg;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	private boolean carryExcelDownloadFlg;

	private CarryShippingListConditionForReport reportCondition;

	//
	// インスタンスフィールド.
	//

	/** リスト */
	private List<CarryShippingList> searchList;

	/**
	 * コンストラクタ
	 */
	public CarryShippingListForm() {
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
		return CarryShippingPagerCondition.class;
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

		if ("directionSubmit".equals(getOp())) {
			clearCheck();
		}

		if ("orderMake".equals(getOp())) {
			clearCheck();
		}

		if ("listIssue".equals(getOp())) {
			clearCheck();
		}
		/** EXCELダウンロードフラグ */
		setExcelDownloadFlg(false);

		/** 運送店別出荷一覧用Ｅｘｃｅｌダウンロードフラグを倒す */
		setCarryExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			// イレギュラーだけど、ここでリストをクリア
			setSearchList(new ArrayList<CarryShippingList>());
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}

		if ("directionSubmit".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}

		if ("orderMake".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}

		if ("carryReport".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}

		if ("listIssue".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (CarryShippingList bean : getSearchList()) {
				bean.setCarryShippingCheckBox(Boolean.FALSE);
			}
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<CarryShippingList>());
		
		// 検索入力：出荷指図日
		setSrhShippingInstructDate(null);
		
		// 検索入力：出荷予定日
		setSrhScheduledShippingDate(null);

		/** EXCELダウンロードフラグ */
		setExcelDownloadFlg(false);

		setReportCondition(null);

		/** エラーコード */
		setErrorCd(null);
		/** エラーメッセージ */
		setErrorMsg(null);
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<CarryShippingList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<CarryShippingList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

	/**
	 * 検索入力：出荷予定日取得.
	 * @return String 出荷予定日
	 */
	public String getSrhScheduledShippingDate() {
		return this.srhScheduledShippingDate;
	}

	/**
	 * 検索入力：出荷予定日設定.
	 * @param srhScheduledShippingDate 出荷予定日
	 */
	public void setSrhScheduledShippingDate(final String srhScheduledShippingDate) {
		this.srhScheduledShippingDate = srhScheduledShippingDate;
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
	 * 検索結果件数取得
	 * @return carryShippingListLength
	 */
	public int getCarryShippingListLength() {
		return searchList.size();
	}

	/**
	 * エラーコードの取得.
	 * @return String エラーコード
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * エラーコードの設定.
	 * @param errorCd エラーコード
	 */
	public void setErrorCd(final String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * エラーメッセージ取得.
	 * @return String エラーメッセージ
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * エラーメッセージ設定
	 * @param errorMsg エラーメッセージ
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 帳票用コンディション取得
	 * @return reportCondition
	 */
	public CarryShippingListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * 帳票用コンディション設定.
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final CarryShippingListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isCarryExcelDownloadFlg() {
		return carryExcelDownloadFlg;
	}

	/**
	 * carryExcelDownloadFlgを設定します。
	 * @param carryExcelDownloadFlg carryExcelDownloadFlg
	 */
	public void setCarryExcelDownloadFlg(final boolean carryExcelDownloadFlg) {
		this.carryExcelDownloadFlg = carryExcelDownloadFlg;
	}

	/**
	 * srhShippingInstructDateを取得します。
	 * @return srhShippingInstructDate
	 */
	public String getSrhShippingInstructDate() {
		return srhShippingInstructDate;
	}

	/**
	 * srhShippingInstructDateを設定します。
	 * @param srhShippingInstructDate srhShippingInstructDate
	 */
	public void setSrhShippingInstructDate(String srhShippingInstructDate) {
		this.srhShippingInstructDate = srhShippingInstructDate;
	}
}

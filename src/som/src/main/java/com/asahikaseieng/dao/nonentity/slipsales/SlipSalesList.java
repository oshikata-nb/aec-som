/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.slipsales;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売上伝票出力画面_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class SlipSalesList extends SlipSalesListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 売上日(String) */
	private String strSalesDate;

	/** 勘定年月(String) */
	private String strAccountYears;

	/** 発行済チェックボックス(Bool) */
	private boolean boolSlipPublishComp;

	/** チェックボックス */
	private boolean slipSalesCheckBox;

	/**
	 * コンストラクタ.
	 */
	public SlipSalesList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		//売上日
		setStrSalesDate(AecDateUtils.dateFormat(getSalesDate(), "yyyy/MM/dd"));
		//勘定年月
		setStrAccountYears(AecDateUtils.dateFormat(
			AecDateUtils.getTimestampYmdHmFormat(getAccountYears().toString(), "yyyyMM"),
			"yyyy/MM"));
		//発行済フラグ
		boolean slipPublishCompFlg = getSlipPublishComp().toString().equals("1");
		setBoolSlipPublishComp(slipPublishCompFlg);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */
	/**
	 * boolSlipPublishCompを取得します。
	 * @return boolSlipPublishComp
	 */
	public boolean isBoolSlipPublishComp() {
		return boolSlipPublishComp;
	}

	/**
	 * boolSlipPublishCompを設定します。
	 * @param boolSlipPublishComp boolSlipPublishComp
	 */
	public void setBoolSlipPublishComp(final boolean boolSlipPublishComp) {
		this.boolSlipPublishComp = boolSlipPublishComp;
	}

	/**
	 * strAccountYearsを取得します。
	 * @return strAccountYears
	 */
	public String getStrAccountYears() {
		return strAccountYears;
	}

	/**
	 * strAccountYearsを設定します。
	 * @param strAccountYears strAccountYears
	 */
	public void setStrAccountYears(final String strAccountYears) {
		this.strAccountYears = strAccountYears;
	}

	/**
	 * strSalesDateを取得します。
	 * @return strSalesDate
	 */
	public String getStrSalesDate() {
		return strSalesDate;
	}

	/**
	 * strSalesDateを設定します。
	 * @param strSalesDate strSalesDate
	 */
	public void setStrSalesDate(final String strSalesDate) {
		this.strSalesDate = strSalesDate;
	}

	/**
	 * slipSalesCheckBoxを取得します。
	 * @return slipSalesCheckBox
	 */
	public boolean isSlipSalesCheckBox() {
		return slipSalesCheckBox;
	}

	/**
	 * slipSalesCheckBoxを設定します。
	 * @param slipSalesCheckBox slipSalesCheckBox
	 */
	public void setSlipSalesCheckBox(final boolean slipSalesCheckBox) {
		this.slipSalesCheckBox = slipSalesCheckBox;
	}

}

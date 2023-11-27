/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlistforreport;

/**
 * InquiryInputListForReportクラス.
 * @author t0011036
 */
public class InquiryInputListForReport extends InquiryInputListForReportBase {

	private static final long serialVersionUID = 1L;

	private String strCountDate;

	private String strCountQty;

	private String strFractionQty;

	private String strInputQty;

	private String strInputfraction;

	/**
	 * コンストラクタ.
	 */
	public InquiryInputListForReport() {
		super();
	}

	/**
	 * strCountDateを取得します。
	 * @return strCountDate
	 */
	public String getStrCountDate() {
		return strCountDate;
	}

	/**
	 * strCountDateを設定します。
	 * @param strCountDate strCountDate
	 */
	public void setStrCountDate(final String strCountDate) {
		this.strCountDate = strCountDate;
	}

	/**
	 * strCountQtyを取得します。
	 * @return strCountQty
	 */
	public String getStrCountQty() {
		return strCountQty;
	}

	/**
	 * strCountQtyを設定します。
	 * @param strCountQty strCountQty
	 */
	public void setStrCountQty(final String strCountQty) {
		this.strCountQty = strCountQty;
	}

	/**
	 * strFractionQtyを取得します。
	 * @return strFractionQty
	 */
	public String getStrFractionQty() {
		return strFractionQty;
	}

	/**
	 * strFractionQtyを設定します。
	 * @param strFractionQty strFractionQty
	 */
	public void setStrFractionQty(final String strFractionQty) {
		this.strFractionQty = strFractionQty;
	}

	/**
	 * strInputfractionを取得します。
	 * @return strInputfraction
	 */
	public String getStrInputfraction() {
		return strInputfraction;
	}

	/**
	 * strInputfractionを設定します。
	 * @param strInputfraction strInputfraction
	 */
	public void setStrInputfraction(final String strInputfraction) {
		this.strInputfraction = strInputfraction;
	}

	/**
	 * strInputQtyを取得します。
	 * @return strInputQty
	 */
	public String getStrInputQty() {
		return strInputQty;
	}

	/**
	 * strInputQtyを設定します。
	 * @param strInputQty strInputQty
	 */
	public void setStrInputQty(final String strInputQty) {
		this.strInputQty = strInputQty;
	}
}

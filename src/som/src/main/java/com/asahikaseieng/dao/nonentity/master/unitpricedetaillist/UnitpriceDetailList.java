/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedetaillist;

/**
 * UnitpriceDetailListクラス.
 * @author kanri-user
 */
public class UnitpriceDetailList extends UnitpriceDetailListBase {

	private static final long serialVersionUID = 1L;

	private String strValidDate;

	private String strQuantityFrom;

	private String strQuantityTo;

	private String strUnitprice;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceDetailList() {
		super();
	}

	/**
	 * strQuantityToを取得します。
	 * @return strQuantityTo
	 */
	public String getStrQuantityTo() {
		return strQuantityTo;
	}

	/**
	 * strQuantityToを設定します。
	 * @param strQuantityTo strQuantityTo
	 */
	public void setStrQuantityTo(final String strQuantityTo) {
		this.strQuantityTo = strQuantityTo;
	}

	/**
	 * strQuantityFromを取得します。
	 * @return strQuantityFrom
	 */
	public String getStrQuantityFrom() {
		return strQuantityFrom;
	}

	/**
	 * strQuantityFromを設定します。
	 * @param strQuantityFrom strQuantityFrom
	 */
	public void setStrQuantityFrom(final String strQuantityFrom) {
		this.strQuantityFrom = strQuantityFrom;
	}

	/**
	 * strUnitpriceを取得します。
	 * @return strUnitprice
	 */
	public String getStrUnitprice() {
		return strUnitprice;
	}

	/**
	 * strUnitpriceを設定します。
	 * @param strUnitprice strUnitprice
	 */
	public void setStrUnitprice(final String strUnitprice) {
		this.strUnitprice = strUnitprice;
	}

	/**
	 * strValidDateを取得します。
	 * @return strValidDate
	 */
	public String getStrValidDate() {
		return strValidDate;
	}

	/**
	 * strValidDateを設定します。
	 * @param strValidDate strValidDate
	 */
	public void setStrValidDate(final String strValidDate) {
		this.strValidDate = strValidDate;
	}
}

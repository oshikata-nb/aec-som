/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocklist;

import java.math.BigDecimal;

/**
 * InventoryStockListクラス.
 * @author t0011036
 */
public class InventoryStockList extends InventoryStockListBase {

	private static final long serialVersionUID = 1L;

	private String strInoutQty;

	private String strInoutDate;

	private String strPossibleQty;

	private BigDecimal inoutScheduleQty;

	private String strInoutScheduleQty;

	private String link;

	/**
	 * コンストラクタ.
	 */
	public InventoryStockList() {
		super();
	}

	/**
	 * strInoutDateを取得します。
	 * @return strInoutDate
	 */
	public String getStrInoutDate() {
		return strInoutDate;
	}

	/**
	 * strInoutDateを設定します。
	 * @param strInoutDate strInoutDate
	 */
	public void setStrInoutDate(final String strInoutDate) {
		this.strInoutDate = strInoutDate;
	}

	/**
	 * strInoutQtyを取得します。
	 * @return strInoutQty
	 */
	public String getStrInoutQty() {
		return strInoutQty;
	}

	/**
	 * strInoutQtyを設定します。
	 * @param strInoutQty strInoutQty
	 */
	public void setStrInoutQty(final String strInoutQty) {
		this.strInoutQty = strInoutQty;
	}

	/**
	 * strPossibleQtyを取得します。
	 * @return strPossibleQty
	 */
	public String getStrPossibleQty() {
		return strPossibleQty;
	}

	/**
	 * strPossibleQtyを設定します。
	 * @param strPossibleQty strPossibleQty
	 */
	public void setStrPossibleQty(final String strPossibleQty) {
		this.strPossibleQty = strPossibleQty;
	}

	/**
	 * strInoutScheduleQtyを取得します。
	 * @return strInoutScheduleQty
	 */
	public String getStrInoutScheduleQty() {
		return strInoutScheduleQty;
	}

	/**
	 * strInoutScheduleQtyを設定します。
	 * @param strInoutScheduleQty strInoutScheduleQty
	 */
	public void setStrInoutScheduleQty(final String strInoutScheduleQty) {
		this.strInoutScheduleQty = strInoutScheduleQty;
	}

	/**
	 * inoutScheduleQtyを取得します。
	 * @return inoutScheduleQty
	 */
	public BigDecimal getInoutScheduleQty() {
		return inoutScheduleQty;
	}

	/**
	 * inoutScheduleQtyを設定します。
	 * @param inoutScheduleQty inoutScheduleQty
	 */
	public void setInoutScheduleQty(final BigDecimal inoutScheduleQty) {
		this.inoutScheduleQty = inoutScheduleQty;
	}

	/**
	 * linkを取得します。
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * linkを設定します。
	 * @param link link
	 */
	public void setLink(final String link) {
		this.link = link;
	}
}

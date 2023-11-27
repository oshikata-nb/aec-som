/*
 * Created on 2010/09/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.salesinout;

/**
 * SalesInoutSearchListクラス.
 * @author t1344224
 */
public class SalesInoutSearchList extends SalesInoutSearchListBase {

	private static final long serialVersionUID = 1L;

	private String strInoutQty;

	private String strInoutWeight;

	/**
	 * コンストラクタ.
	 */
	public SalesInoutSearchList() {
		super();
	}

	/**
	 * strInoutQty取得.
	 * @return strInoutQty
	 */
	public String getStrInoutQty() {
		return strInoutQty;
	}

	/**
	 * strInoutQty設定.
	 * @param strInoutQty strInoutQty
	 */
	public void setStrInoutQty(final String strInoutQty) {
		this.strInoutQty = strInoutQty;
	}

	/**
	 * strInoutWeight取得.
	 * @return strInoutWeight
	 */
	public String getStrInoutWeight() {
		return strInoutWeight;
	}

	/**
	 * strInoutWeight設定.
	 * @param strInoutWeight strInoutWeight
	 */
	public void setStrInoutWeight(final String strInoutWeight) {
		this.strInoutWeight = strInoutWeight;
	}
}

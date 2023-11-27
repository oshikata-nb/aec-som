/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.productattributequeuedetail;

/**
 * ProductAttributeQueueDetailクラス.
 * @author t0011036
 */
public class ProductAttributeQueueDetail extends
		ProductAttributeQueueDetailBase {

	private static final long serialVersionUID = 1L;

	private String strOrderPattern;

	private String strInspectionDays;

	private String strProductOrderPoint;

	/**
	 * コンストラクタ.
	 */
	public ProductAttributeQueueDetail() {
		super();
	}

	/**
	 * strInspectionDaysを取得します。
	 * @return strInspectionDays
	 */
	public String getStrInspectionDays() {
		return strInspectionDays;
	}

	/**
	 * strInspectionDaysを設定します。
	 * @param strInspectionDays strInspectionDays
	 */
	public void setStrInspectionDays(final String strInspectionDays) {
		this.strInspectionDays = strInspectionDays;
	}

	/**
	 * strOrderPatternを取得します。
	 * @return strOrderPattern
	 */
	public String getStrOrderPattern() {
		return strOrderPattern;
	}

	/**
	 * strOrderPatternを設定します。
	 * @param strOrderPattern strOrderPattern
	 */
	public void setStrOrderPattern(final String strOrderPattern) {
		this.strOrderPattern = strOrderPattern;
	}

	/**
	 * strProductOrderPointを取得します。
	 * @return strProductOrderPoint
	 */
	public String getStrProductOrderPoint() {
		return strProductOrderPoint;
	}

	/**
	 * strProductOrderPointを設定します。
	 * @param strProductOrderPoint strProductOrderPoint
	 */
	public void setStrProductOrderPoint(final String strProductOrderPoint) {
		this.strProductOrderPoint = strProductOrderPoint;
	}
}

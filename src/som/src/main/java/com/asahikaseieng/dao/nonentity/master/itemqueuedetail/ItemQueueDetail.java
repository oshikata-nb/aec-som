/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedetail;

/**
 * ItemQueueDetailクラス.
 * @author t0011036
 */
public class ItemQueueDetail extends ItemQueueDetailBase {

	private static final long serialVersionUID = 1L;

	private String strActiveDate;

	private String strNumberOfInsertions;

	private String strAllUpWeight;

	private String strKgOfFractionManagement;

	private String strKgConversionCoefficient;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueDetail() {
		super();
	}

	/**
	 * strActiveDateを取得します。
	 * @return strActiveDate
	 */
	public String getStrActiveDate() {
		return strActiveDate;
	}

	/**
	 * strActiveDateを設定します。
	 * @param strActiveDate strActiveDate
	 */
	public void setStrActiveDate(final String strActiveDate) {
		this.strActiveDate = strActiveDate;
	}

	/**
	 * strAllUpWeightを取得します。
	 * @return strAllUpWeight
	 */
	public String getStrAllUpWeight() {
		return strAllUpWeight;
	}

	/**
	 * strAllUpWeightを設定します。
	 * @param strAllUpWeight strAllUpWeight
	 */
	public void setStrAllUpWeight(final String strAllUpWeight) {
		this.strAllUpWeight = strAllUpWeight;
	}

	/**
	 * strKgConversionCoefficientを取得します。
	 * @return strKgConversionCoefficient
	 */
	public String getStrKgConversionCoefficient() {
		return strKgConversionCoefficient;
	}

	/**
	 * strKgConversionCoefficientを設定します。
	 * @param strKgConversionCoefficient strKgConversionCoefficient
	 */
	public void setStrKgConversionCoefficient(
			final String strKgConversionCoefficient) {
		this.strKgConversionCoefficient = strKgConversionCoefficient;
	}

	/**
	 * strKgOfFractionManagementを取得します。
	 * @return strKgOfFractionManagement
	 */
	public String getStrKgOfFractionManagement() {
		return strKgOfFractionManagement;
	}

	/**
	 * strKgOfFractionManagementを設定します。
	 * @param strKgOfFractionManagement strKgOfFractionManagement
	 */
	public void setStrKgOfFractionManagement(
			final String strKgOfFractionManagement) {
		this.strKgOfFractionManagement = strKgOfFractionManagement;
	}

	/**
	 * strNumberOfInsertionsを取得します。
	 * @return strNumberOfInsertions
	 */
	public String getStrNumberOfInsertions() {
		return strNumberOfInsertions;
	}

	/**
	 * strNumberOfInsertionsを設定します。
	 * @param strNumberOfInsertions strNumberOfInsertions
	 */
	public void setStrNumberOfInsertions(final String strNumberOfInsertions) {
		this.strNumberOfInsertions = strNumberOfInsertions;
	}
}

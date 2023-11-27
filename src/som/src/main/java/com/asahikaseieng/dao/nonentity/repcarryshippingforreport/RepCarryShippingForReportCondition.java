/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repcarryshippingforreport;

/**
 * OrderListPagerConditionクラス.
 * @author t1344224
 */
public class RepCarryShippingForReportCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepCarryShippingForReportCondition() {
	}

	private String srhScheduledShippingDate;

	private String carryCd;

	/**
	 * 
	 * 出荷予定日
	 * @return 出荷予定日
	 */
	public String getSrhScheduledShippingDate() {
		return srhScheduledShippingDate;
	}

	/**
	 * 
	 * 出荷予定日 出荷予定日
	 * @param srhScheduledShippingDate srhScheduledShippingDate
	 */
	public void setSrhScheduledShippingDate(final String srhScheduledShippingDate) {
		this.srhScheduledShippingDate = srhScheduledShippingDate;
	}

	/**
	 * 
	 * carryCd
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * 
	 * carryCd
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

}

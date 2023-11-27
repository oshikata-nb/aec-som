/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail;

/**
 * CommonAttributeQueueDetailクラス.
 * @author t0011036
 */
public class CommonAttributeQueueDetail extends CommonAttributeQueueDetailBase {

	private static final long serialVersionUID = 1L;

	private String strExpireMonths;

	private String strContractMonths;

	/**
	 * コンストラクタ.
	 */
	public CommonAttributeQueueDetail() {
		super();
	}

	/**
	 * strContractMonthsを取得します。
	 * @return strContractMonths
	 */
	public String getStrContractMonths() {
		return strContractMonths;
	}

	/**
	 * strContractMonthsを設定します。
	 * @param strContractMonths strContractMonths
	 */
	public void setStrContractMonths(final String strContractMonths) {
		this.strContractMonths = strContractMonths;
	}

	/**
	 * strExpireMonthsを取得します。
	 * @return strExpireMonths
	 */
	public String getStrExpireMonths() {
		return strExpireMonths;
	}

	/**
	 * strExpireMonthsを設定します。
	 * @param strExpireMonths strExpireMonths
	 */
	public void setStrExpireMonths(final String strExpireMonths) {
		this.strExpireMonths = strExpireMonths;
	}
}

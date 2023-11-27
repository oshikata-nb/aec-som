/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

import java.io.Serializable;


/**
 * 品目名称客先コード番号採番DTO
 * @author 956
 */
public final class CustomerIdDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructor */
	public CustomerIdDto() {
	}

	/** customerCd_PROCEDURE_PARAMETER */
	public static final String customerCd_PROCEDURE_PARAMETER = "in";

	/** itemNameFull_PROCEDURE_PARAMETER */
	public static final String itemNameFull_PROCEDURE_PARAMETER = "out";

	/** customerId_PROCEDURE_PARAMETER */
	public static final String customerId_PROCEDURE_PARAMETER = "out";

	private String customerCd;

	private String itemNameFull;

	private String customerId;

	/**
	 * customerCdを取得します。
	 * @return customerCd
	 */
	public String getCustomerCd() {
		return customerCd;
	}

	/**
	 * customerCdを設定します。
	 * @param customerCd customerCd
	 */
	public void setCustomerCd(final String customerCd) {
		this.customerCd = customerCd;
	}

	/**
	 * customerIdを取得します。
	 * @return customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * customerIdを設定します。
	 * @param customerId customerId
	 */
	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	/**
	 * itemNameFullを取得します。
	 * @return itemNameFull
	 */
	public String getItemNameFull() {
		return itemNameFull;
	}

	/**
	 * itemNameFullを設定します。
	 * @param itemNameFull itemNameFull
	 */
	public void setItemNameFull(final String itemNameFull) {
		this.itemNameFull = itemNameFull;
	}


}

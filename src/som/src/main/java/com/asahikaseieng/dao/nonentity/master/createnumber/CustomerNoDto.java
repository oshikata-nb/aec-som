/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

import java.io.Serializable;


/**
 * 客先番号採番DTO
 * @author 956
 */
public final class CustomerNoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructor */
	public CustomerNoDto() {
	}

	/** customerNo_PROCEDURE_PARAMETER */
	public static final String customerNo_PROCEDURE_PARAMETER = "return";

	/** orgItemCd_PROCEDURE_PARAMETER */
	public static final String orgItemCd_PROCEDURE_PARAMETER = "in";

	private String customerNo;
	private String orgItemCd;

	/**
	 * customerNoを取得します。
	 * @return customerNo
	 */
	public String getCustomerNo() {
		return customerNo;
	}
	/**
	 * customerNoを設定します。
	 * @param customerNo customerNo
	 */
	public void setCustomerNo(final String customerNo) {
		this.customerNo = customerNo;
	}
	/**
	 * orgItemCdを取得します。
	 * @return orgItemCd
	 */
	public String getOrgItemCd() {
		return orgItemCd;
	}
	/**
	 * orgItemCdを設定します。
	 * @param orgItemCd orgItemCd
	 */
	public void setOrgItemCd(final String orgItemCd) {
		this.orgItemCd = orgItemCd;
	}


}

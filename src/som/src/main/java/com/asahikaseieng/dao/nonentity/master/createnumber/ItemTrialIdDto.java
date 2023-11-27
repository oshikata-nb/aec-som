/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

import java.io.Serializable;


/**
 * 品目名称試作番号DTO
 * @author 956
 */
public final class ItemTrialIdDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructor */
	public ItemTrialIdDto() {
	}

	/** itemNameTrialCd_PROCEDURE_PARAMETER */
	public static final String itemNameTrialCd_PROCEDURE_PARAMETER = "in";

	/** itemNameIdFull_PROCEDURE_PARAMETER */
	public static final String itemNameIdFull_PROCEDURE_PARAMETER = "out";

	/** itemNameTrialId_PROCEDURE_PARAMETER */
	public static final String itemNameTrialId_PROCEDURE_PARAMETER = "out";

	private String itemNameTrialCd;

	private String itemNameIdFull;

	private String itemNameTrialId;

	/**
	 * itemNameIdFullを取得します。
	 * @return itemNameIdFull
	 */
	public String getItemNameIdFull() {
		return itemNameIdFull;
	}

	/**
	 * itemNameIdFullを設定します。
	 * @param itemNameIdFull itemNameIdFull
	 */
	public void setItemNameIdFull(final String itemNameIdFull) {
		this.itemNameIdFull = itemNameIdFull;
	}

	/**
	 * itemNameTrialCdを取得します。
	 * @return itemNameTrialCd
	 */
	public String getItemNameTrialCd() {
		return itemNameTrialCd;
	}

	/**
	 * itemNameTrialCdを設定します。
	 * @param itemNameTrialCd itemNameTrialCd
	 */
	public void setItemNameTrialCd(final String itemNameTrialCd) {
		this.itemNameTrialCd = itemNameTrialCd;
	}

	/**
	 * itemNameTrialIdを取得します。
	 * @return itemNameTrialId
	 */
	public String getItemNameTrialId() {
		return itemNameTrialId;
	}

	/**
	 * itemNameTrialIdを設定します。
	 * @param itemNameTrialId itemNameTrialId
	 */
	public void setItemNameTrialId(final String itemNameTrialId) {
		this.itemNameTrialId = itemNameTrialId;
	}

}

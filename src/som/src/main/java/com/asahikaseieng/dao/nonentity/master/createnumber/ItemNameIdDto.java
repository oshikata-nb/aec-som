/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

import java.io.Serializable;


/**
 * 品目名称番号採番DTO
 * @author 956
 */
public final class ItemNameIdDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructor */
	public ItemNameIdDto() {
	}


	/** itemNameId_PROCEDURE_PARAMETER */
	public static final String itemNameId_PROCEDURE_PARAMETER = "out";

	private String itemNameId;

	/**
	 * itemNameIdを取得します。
	 * @return itemNameId
	 */
	public String getItemNameId() {
		return itemNameId;
	}

	/**
	 * itemNameIdを設定します。
	 * @param itemNameId itemNameId
	 */
	public void setItemNameId(final String itemNameId) {
		this.itemNameId = itemNameId;
	}

}

/*
 * Created on 2008/10/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

import java.io.Serializable;


/**
 * アプリケーション名称番号採番DTO
 * @author 956
 */
public final class AplNameIdDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructor */
	public AplNameIdDto() {
	}

	/** AplNameId_PROCEDURE_PARAMETER */
	public static final String aplNameId_PROCEDURE_PARAMETER = "out";

	private String aplNameId;

	/**
	 * itemNameIdを取得します。
	 * @return itemNameId
	 */
	public String getAplNameId() {
		return aplNameId;
	}

	/**
	 * aplNameIdを設定します。
	 * @param aplNameId aplNameId
	 */
	public void setAplNameId(final String aplNameId) {
		this.aplNameId = aplNameId;
	}

}

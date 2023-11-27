/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

import java.io.Serializable;


/**
 * 品目コード採番DTO
 * @author 956
 */
public final class ItemCdDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructor */
	public ItemCdDto() {
	}
	/** itemCd_PROCEDURE_PARAMETER */
	public static final String itemCd_PROCEDURE_PARAMETER = "return";

	/** itemType_PROCEDURE_PARAMETER */
	public static final String itemType_PROCEDURE_PARAMETER = "in";

	/** categoryCd_PROCEDURE_PARAMETER */
	public static final String categoryCd_PROCEDURE_PARAMETER = "in";


	private String itemCd;
	private String itemType;
	private String categoryCd;

	/**
	 * categoryCdを取得します。
	 * @return categoryCd
	 */
	public String getCategoryCd() {
		return categoryCd;
	}
	/**
	 * categoryCdを設定します。
	 * @param categoryCd categoryCd
	 */
	public void setCategoryCd(final String categoryCd) {
		this.categoryCd = categoryCd;
	}
	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}
	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}
	/**
	 * itemTypeを取得します。
	 * @return itemType
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * itemTypeを設定します。
	 * @param itemType itemType
	 */
	public void setItemType(final String itemType) {
		this.itemType = itemType;
	}


}

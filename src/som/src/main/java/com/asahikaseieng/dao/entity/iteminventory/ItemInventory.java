/*
 * Created on Thu Jan 22 18:23:14 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.iteminventory;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ItemInventoryクラス.
 * @author t0011036
 */
public class ItemInventory extends ItemInventoryBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ItemInventory() {
		super();
	}

	/**
	 * 更新日時を返す
	 * @return Timestamp
	 */
	public Timestamp getCurrentTimestamp() {
		return AecDateUtils.getCurrentTimestamp();
	}
}

/*
 * Created on Thu Jan 22 15:18:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inventorycount;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * InventoryCountクラス.
 * @author t0011036
 */
public class InventoryCount extends InventoryCountBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public InventoryCount() {
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

/*
 * Created on Thu Jan 22 20:01:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.locationinventory;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * LocationInventoryクラス.
 * @author t0011036
 */
public class LocationInventory extends LocationInventoryBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public LocationInventory() {
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

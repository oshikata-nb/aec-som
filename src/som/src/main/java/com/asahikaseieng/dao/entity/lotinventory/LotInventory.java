/*
 * Created on Thu Jan 22 20:02:59 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lotinventory;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * LotInventoryクラス.
 * @author t0011036
 */
public class LotInventory extends LotInventoryBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public LotInventory() {
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

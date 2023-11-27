/*
 * Created on Thu Jan 22 17:06:28 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.item;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Itemクラス.
 * @author t0011036
 */
public class Item extends ItemBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Item() {
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

/*
 * Created on Thu Jan 22 18:29:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemremark;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ItemRemarkクラス.
 * @author t0011036
 */
public class ItemRemark extends ItemRemarkBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ItemRemark() {
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

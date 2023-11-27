/*
 * Created on Thu Jan 22 17:18:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemcategory;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ItemCategoryクラス.
 * @author t0011036
 */
public class ItemCategory extends ItemCategoryBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ItemCategory() {
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

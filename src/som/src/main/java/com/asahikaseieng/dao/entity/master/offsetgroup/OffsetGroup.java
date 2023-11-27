/*
 * Created on Fri Jan 23 14:14:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.offsetgroup;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OffsetGroupクラス.
 * @author t0011036
 */
public class OffsetGroup extends OffsetGroupBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OffsetGroup() {
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

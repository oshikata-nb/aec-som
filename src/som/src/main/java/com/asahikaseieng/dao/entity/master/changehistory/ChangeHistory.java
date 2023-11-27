/*
 * Created on Mon Jan 19 16:31:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.changehistory;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ChangeHistoryクラス.
 * @author t0011036
 */
public class ChangeHistory extends ChangeHistoryBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ChangeHistory() {
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

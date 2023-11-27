/*
 * Created on Fri Jan 23 15:07:13 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.operationgroup;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OperationGroupクラス.
 * @author t0011036
 */
public class OperationGroup extends OperationGroupBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OperationGroup() {
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

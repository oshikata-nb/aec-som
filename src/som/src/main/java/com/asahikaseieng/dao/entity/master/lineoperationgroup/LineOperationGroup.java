/*
 * Created on Thu Jan 22 19:01:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.lineoperationgroup;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * LineOperationGroupクラス.
 * @author t0011036
 */
public class LineOperationGroup extends LineOperationGroupBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public LineOperationGroup() {
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

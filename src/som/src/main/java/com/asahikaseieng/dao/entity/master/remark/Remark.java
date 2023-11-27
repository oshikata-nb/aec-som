/*
 * Created on Wed Feb 06 19:01:45 JST 2008
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.remark;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Remarkクラス.
 * @author a1020630
 */
public class Remark extends RemarkBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Remark() {
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

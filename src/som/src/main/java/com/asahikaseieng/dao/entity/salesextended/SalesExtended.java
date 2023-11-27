/*
 * Created on Tue Sep 08 18:52:17 JST 2015
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.salesextended;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * SalesExtendedクラス.
 * @author a1041072
 */
public class SalesExtended extends SalesExtendedBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public SalesExtended() {
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

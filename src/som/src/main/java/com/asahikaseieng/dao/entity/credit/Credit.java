/*
 * Created on Fri Jun 19 11:01:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.credit;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Creditクラス.
 * @author t0011036
 */
public class Credit extends CreditBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Credit() {
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

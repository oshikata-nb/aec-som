/*
 * Created on Mon Feb 09 15:11:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.asporder;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspOrderクラス.
 * @author kanri-user
 */
public class AspOrder extends AspOrderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspOrder() {
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

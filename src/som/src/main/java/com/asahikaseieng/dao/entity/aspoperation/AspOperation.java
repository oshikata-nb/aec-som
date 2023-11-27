/*
 * Created on Tue Feb 17 10:00:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspoperation;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspOperationクラス.
 * @author kanri-user
 */
public class AspOperation extends AspOperationBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspOperation() {
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

/*
 * Created on Fri Feb 20 17:47:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.operation;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Operationクラス.
 * @author kanri-user
 */
public class Operation extends OperationBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Operation() {
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

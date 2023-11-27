/*
 * Created on Tue Feb 17 10:00:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspImportAspOperationクラス.
 * @author 
 */
public class AspImportAspOperation extends AspImportAspOperationBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspImportAspOperation() {
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

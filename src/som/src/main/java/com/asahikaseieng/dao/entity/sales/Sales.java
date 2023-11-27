/*
 * Created on Fri Feb 27 17:24:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sales;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Salesクラス.
 * @author kanri-user
 */
public class Sales extends SalesBase {

	private static final long serialVersionUID = 1L;


	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";
	/**
	 * コンストラクタ.
	 */
	public Sales() {
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

/*
 * Created on 2021/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderimportfilelog;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderImportFileLogクラス.
 * @author 
 */
public class OrderImportFileLog extends OrderImportFileLogBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderImportFileLog() {
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

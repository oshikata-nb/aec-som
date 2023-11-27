/*
 * Created on 2021/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportfilelogupdate;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderImportFileLogクラス.
 * @author 
 */
public class OrderImportFileLogUpdate extends OrderImportFileLogUpdateBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderImportFileLogUpdate() {
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

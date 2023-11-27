
package com.asahikaseieng.dao.entity.orderimportfileout;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Areaクラス.
 */
public class OrderImportFileOut extends OrderImportFileOutBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderImportFileOut() {
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

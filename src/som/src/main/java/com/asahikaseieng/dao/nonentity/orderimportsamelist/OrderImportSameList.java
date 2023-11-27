/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportsamelist;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderImportHeadクラス.
 * @author 
 */
public class OrderImportSameList extends OrderImportSameListBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderImportSameList() {
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

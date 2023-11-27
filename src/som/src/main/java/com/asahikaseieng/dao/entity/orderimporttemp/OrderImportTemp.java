/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderimporttemp;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderImportHeadクラス.
 * @author 
 */
public class OrderImportTemp extends OrderImportTempBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderImportTemp() {
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

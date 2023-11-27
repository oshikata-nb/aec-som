/*
 * Created on Thu Feb 12 18:54:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.purchasemateinjection;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * PurchaseMateInjectionクラス.
 * @author kanri-user
 */
public class PurchaseMateInjection extends PurchaseMateInjectionBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public PurchaseMateInjection() {
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

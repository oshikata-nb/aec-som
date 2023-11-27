/*
 * Created on Thu Jan 22 19:58:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailsalesterms;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderDetailSalesTermsクラス.
 * @author 
 */
public class OrderDetailSalesTerms extends OrderDetailSalesTermsBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderDetailSalesTerms() {
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

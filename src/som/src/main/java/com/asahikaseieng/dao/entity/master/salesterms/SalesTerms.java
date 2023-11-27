/*
 * Created on Thu Jan 22 19:58:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.salesterms;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * SalesTermsクラス.
 * @author kanri-user
 */
public class SalesTerms extends SalesTermsBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public SalesTerms() {
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

/*
 * Created on Thu Jan 22 18:21:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspImportUnitpriceクラス.
 * @author
 */
public class AspImportUnitprice extends AspImportUnitpriceBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspImportUnitprice() {
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

/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.entity.dailynumber;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * 日毎発番管理テーブルデータ格納クラス.
 *
 * @author tosco
 */
public class DailyNumber extends DailyNumberBase {


	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DailyNumber() {
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

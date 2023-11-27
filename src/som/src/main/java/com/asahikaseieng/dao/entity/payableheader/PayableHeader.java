/*
 * Created on Wed Feb 04 10:09:32 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.payableheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * PayableHeaderクラス.
 * @author kanri-user
 */
public class PayableHeader extends PayableHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public PayableHeader() {
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

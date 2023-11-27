/*
 * Created on Wed Feb 04 09:34:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.depositheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DepositHeaderクラス.
 * @author kanri-user
 */
public class DepositHeader extends DepositHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";


	/**
	 * コンストラクタ.
	 */
	public DepositHeader() {
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

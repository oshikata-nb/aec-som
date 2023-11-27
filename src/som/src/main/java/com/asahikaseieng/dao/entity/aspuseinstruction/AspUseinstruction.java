/*
 * Created on Tue Apr 21 10:54:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspuseinstruction;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspUseinstructionクラス.
 * @author kanri-user
 */
public class AspUseinstruction extends AspUseinstructionBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspUseinstruction() {
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

/*
 * Created on Wed Feb 04 09:31:06 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.claimheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ClaimHeaderクラス.
 * @author kanri-user
 */
public class ClaimHeader extends ClaimHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ClaimHeader() {
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

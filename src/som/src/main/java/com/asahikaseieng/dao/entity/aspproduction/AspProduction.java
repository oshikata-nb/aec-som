/*
 * Created on Mon Feb 09 15:26:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspproduction;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspProductionクラス.
 * @author kanri-user
 */
public class AspProduction extends AspProductionBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspProduction() {
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

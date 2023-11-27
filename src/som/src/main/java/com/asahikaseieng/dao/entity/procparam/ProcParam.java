/*
 * Created on Mon Mar 03 10:10:51 JST 2014
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.procparam;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ProcParamクラス.
 * @author atts
 */
public class ProcParam extends ProcParamBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ProcParam() {
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

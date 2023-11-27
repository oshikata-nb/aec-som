/*
 * Created on Fri Feb 20 19:09:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionformula;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DirectionFormulaクラス.
 * @author kanri-user
 */
public class DirectionFormula extends DirectionFormulaBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DirectionFormula() {
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

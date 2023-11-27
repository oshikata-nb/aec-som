/*
 * Created on Fri Jan 23 13:26:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeprocedure;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * RecipeProcedureクラス.
 * @author kanri-user
 */
public class RecipeProcedure extends RecipeProcedureBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public RecipeProcedure() {
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

/*
 * Created on Fri Feb 20 18:19:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.recipepegresouce;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * RecipePegResouceクラス.
 * @author kanri-user
 */
public class RecipePegResouce extends RecipePegResouceBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public RecipePegResouce() {
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

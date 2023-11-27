/*
 * Created on Fri Jan 23 13:32:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeinspection;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * RecipeInspectionクラス.
 * @author kanri-user
 */
public class RecipeInspection extends RecipeInspectionBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public RecipeInspection() {
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

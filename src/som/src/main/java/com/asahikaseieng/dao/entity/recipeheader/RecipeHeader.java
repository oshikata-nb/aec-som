/*
 * Created on Fri Jan 23 13:40:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * RecipeHeaderクラス.
 * @author kanri-user
 */
public class RecipeHeader extends RecipeHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public RecipeHeader() {
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

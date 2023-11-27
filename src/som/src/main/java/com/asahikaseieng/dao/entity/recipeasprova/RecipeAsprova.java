/*
 * Created on Fri Feb 20 17:24:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeasprova;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * RecipeAsprovaクラス.
 * @author kanri-user
 */
public class RecipeAsprova extends RecipeAsprovaBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public RecipeAsprova() {
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

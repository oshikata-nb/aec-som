/*
 * Created on Fri Jan 23 13:44:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeformula;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * RecipeFormulaクラス.
 * @author kanri-user
 */
public class RecipeFormula extends RecipeFormulaBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ. 
	 */
	public RecipeFormula() {
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

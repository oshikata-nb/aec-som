/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 基本処方－工程順序コンボボックス用データ.
 *
 * @author tosco
 */
public class RecipeProcedureSetpNoForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeProcedureSetpNoForComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_PROCEDURE";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	//
	// インスタンスフィールド
	//

	/** RECIPE_ID|レシピインデックス */
	private BigDecimal recipeId;

	/** STEP_NO */
	private BigDecimal stepNo;

	/** 表示順 */
	private BigDecimal seq;

	/**
	 * RECIPE_ID|レシピインデックスを取得する
	 * @return BigDecimal RECIPE_ID|レシピインデックス
	*/
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * RECIPE_ID|レシピインデックスを設定する
	 * @param recipeId RECIPE_ID|レシピインデックス
	*/
	public void setRecipeId(final BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * STEP_NOを取得する
	 * @return BigDecimal STEP_NO
	*/
	public BigDecimal getStepNo() {
		return this.stepNo;
	}

	/**
	 * STEP_NOを設定する
	 * @param stepNo STEP_NO
	*/
	public void setStepNo(final BigDecimal stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * 表示順を取得する
	 * @return BigDecimal 表示順
	*/
	public BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * 表示順を設定する
	 * @param seq 表示順
	*/
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

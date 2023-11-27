/*
 * Created on Fri Jan 23 12:27:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.reciperemark;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeRemarkBaseクラス.
 * @author kanri-user
 */
public class RecipeRemarkBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeRemarkBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "RECIPE_REMARK";


//	/** IDアノテーション recipeId. */
//	public static final String recipeId_ID = "assigned";

	/** COLUMNアノテーション recipeId. */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション generalRecipeRemark. */
	public static final String generalRecipeRemark_COLUMN = "GENERAL_RECIPE_REMARK";

	/** COLUMNアノテーション masterRecipeRemark. */
	public static final String masterRecipeRemark_COLUMN = "MASTER_RECIPE_REMARK";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal recipeId;

	private String generalRecipeRemark;

	private String masterRecipeRemark;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	private String updatorCd;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * recipeId取得.
	 * @return recipeId
	 */
	public java.math.BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * recipeId設定.
	 * @param recipeId recipeId
	 */
	public void setRecipeId(final java.math.BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * generalRecipeRemark取得.
	 * @return generalRecipeRemark
	 */
	public String getGeneralRecipeRemark() {
		return this.generalRecipeRemark;
	}

	/**
	 * generalRecipeRemark設定.
	 * @param generalRecipeRemark generalRecipeRemark
	 */
	public void setGeneralRecipeRemark(final String generalRecipeRemark) {
		this.generalRecipeRemark = generalRecipeRemark;
	}

	/**
	 * masterRecipeRemark取得.
	 * @return masterRecipeRemark
	 */
	public String getMasterRecipeRemark() {
		return this.masterRecipeRemark;
	}

	/**
	 * masterRecipeRemark設定.
	 * @param masterRecipeRemark masterRecipeRemark
	 */
	public void setMasterRecipeRemark(final String masterRecipeRemark) {
		this.masterRecipeRemark = masterRecipeRemark;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
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

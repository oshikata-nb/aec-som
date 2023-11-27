/*
 * Created on Fri Feb 20 17:47:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.operation;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OperationBaseクラス.
 * @author kanri-user
 */
public class OperationBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OperationBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "OPERATION";


//	/** IDアノテーション operationCd. */
//	public static final String operationCd_ID = "assigned";

	/** COLUMNアノテーション operationCd. */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operationName. */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション recipeUse. */
	public static final String recipeUse_COLUMN = "RECIPE_USE";

	/** COLUMNアノテーション operationGroupCd. */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** COLUMNアノテーション memo. */
	public static final String memo_COLUMN = "MEMO";

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

	private String operationCd;

	private String operationName;

	private java.math.BigDecimal recipeUse;

	private String operationGroupCd;

	private String memo;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	private String updatorCd;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * operationCd取得.
	 * @return operationCd
	 */
	public String getOperationCd() {
		return this.operationCd;
	}

	/**
	 * operationCd設定.
	 * @param operationCd operationCd
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * operationName取得.
	 * @return operationName
	 */
	public String getOperationName() {
		return this.operationName;
	}

	/**
	 * operationName設定.
	 * @param operationName operationName
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * recipeUse取得.
	 * @return recipeUse
	 */
	public java.math.BigDecimal getRecipeUse() {
		return this.recipeUse;
	}

	/**
	 * recipeUse設定.
	 * @param recipeUse recipeUse
	 */
	public void setRecipeUse(final java.math.BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * operationGroupCd取得.
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return this.operationGroupCd;
	}

	/**
	 * operationGroupCd設定.
	 * @param operationGroupCd operationGroupCd
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}

	/**
	 * memo取得.
	 * @return memo
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * memo設定.
	 * @param memo memo
	 */
	public void setMemo(final String memo) {
		this.memo = memo;
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

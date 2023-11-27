/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.operation;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OperationListForAutoCompleteクラス.
 * @author t0011036
 */
public class OperationForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OperationForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション recipeUse */
	public static final String recipeUse_COLUMN = "RECIPE_USE";

	//
	// インスタンスフィールド
	//

	private String operationCd;

	private String operationName;

	private BigDecimal recipeUse;

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
	 * recipeUseを取得します。
	 * @return recipeUse
	 */
	public BigDecimal getRecipeUse() {
		return recipeUse;
	}

	/**
	 * recipeUseを設定します。
	 * @param recipeUse recipeUse
	 */
	public void setRecipeUse(final BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
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


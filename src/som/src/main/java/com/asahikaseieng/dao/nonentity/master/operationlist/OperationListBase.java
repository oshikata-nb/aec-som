/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OperationListクラス.
 * @author t0011036
 */
public class OperationListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OperationListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション shortOperationName */
	public static final String shortOperationName_COLUMN = "SHORT_OPERATION_NAME";

	/** COLUMNアノテーション recipeUseName */
	public static final String recipeUseName_COLUMN = "RECIPE_USE_NAME";

	//
	// インスタンスフィールド
	//

	private String operationCd;

	private String operationName;

	private String shortOperationName;

	private String recipeUseName;

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
	 * shortOperationName取得.
	 * @return shortOperationName
	 */
	public String getShortOperationName() {
		return this.shortOperationName;
	}

	/**
	 * shortOperationName設定.
	 * @param shortOperationName shortOperationName
	 */
	public void setShortOperationName(final String shortOperationName) {
		this.shortOperationName = shortOperationName;
	}

	/**
	 * recipeUseName取得.
	 * @return recipeUseName
	 */
	public String getRecipeUseName() {
		return this.recipeUseName;
	}

	/**
	 * recipeUseName設定.
	 * @param recipeUseName recipeUseName
	 */
	public void setRecipeUseName(final String recipeUseName) {
		this.recipeUseName = recipeUseName;
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


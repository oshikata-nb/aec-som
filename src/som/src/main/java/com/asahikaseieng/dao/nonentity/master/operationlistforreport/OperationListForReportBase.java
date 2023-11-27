/*
 * Created on 2009/08/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OperationListForReportクラス.
 * @author t0011036
 */
public class OperationListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OperationListForReportBase() {
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

	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** COLUMNアノテーション memo */
	public static final String memo_COLUMN = "MEMO";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション recipeUseName */
	public static final String recipeUseName_COLUMN = "RECIPE_USE_NAME";

	/** COLUMNアノテーション operationGroupName */
	public static final String operationGroupName_COLUMN = "OPERATION_GROUP_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

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

	private String recipeUseName;

	private String operationGroupName;

	private String inputorName;

	private String updatorName;

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
	 * operationGroupName取得.
	 * @return operationGroupName
	 */
	public String getOperationGroupName() {
		return this.operationGroupName;
	}

	/**
	 * operationGroupName設定.
	 * @param operationGroupName operationGroupName
	 */
	public void setOperationGroupName(final String operationGroupName) {
		this.operationGroupName = operationGroupName;
	}

	/**
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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


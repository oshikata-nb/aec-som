/*
 * Created on 2009/08/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeResouceGroupListForReportクラス.
 * @author t0011036
 */
public class RecipeResouceGroupListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション resouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション resouceGroupName */
	public static final String resouceGroupName_COLUMN = "RESOUCE_GROUP_NAME";

	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション operationGroupName */
	public static final String operationGroupName_COLUMN = "OPERATION_GROUP_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String resouceGroupCd;

	private String resouceGroupName;

	private String operationGroupCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String operationGroupName;

	private String inputorName;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * resouceGroupCd取得.
	 * @return resouceGroupCd
	 */
	public String getResouceGroupCd() {
		return this.resouceGroupCd;
	}

	/**
	 * resouceGroupCd設定.
	 * @param resouceGroupCd resouceGroupCd
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * resouceGroupName取得.
	 * @return resouceGroupName
	 */
	public String getResouceGroupName() {
		return this.resouceGroupName;
	}

	/**
	 * resouceGroupName設定.
	 * @param resouceGroupName resouceGroupName
	 */
	public void setResouceGroupName(final String resouceGroupName) {
		this.resouceGroupName = resouceGroupName;
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


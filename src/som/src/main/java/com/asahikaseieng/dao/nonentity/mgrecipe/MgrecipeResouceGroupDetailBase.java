/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MgrecipeResouceGroupBaseクラス.
 * @author tosco
 */
public class MgrecipeResouceGroupDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeResouceGroupDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_RESOUCE_GROUP";

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

	//
	// インスタンスフィールド
	//

	/** 設備グループコード */
	private String resouceGroupCd;
	/** 設備グループ名称 */
	private String resouceGroupName;
	/** 工程グループコード */
	private String operationGroupCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 設備グループコード取得.
	 * @return String 設備グループコード
	 */
	public String getResouceGroupCd() {
		return this.resouceGroupCd;
	}

	/**
	 * 設備グループコード設定.
	 * @param resouceGroupCd 設備グループコード
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * 設備グループ名称を取得します。
	 * @return String 設備グループ名称
	 */
	public String getResouceGroupName() {
		return resouceGroupName;
	}

	/**
	 * 設備グループ名称を設定します。
	 * @param resouceGroupName 設備グループ名称
	 */
	public void setResouceGroupName(final String resouceGroupName) {
		this.resouceGroupName = resouceGroupName;
	}

	/**
	 * 工程グループコード取得
	 * @return String 工程グループコード
	 */
	public String getOperationGroupCd() {
		return operationGroupCd;
	}

	/**
	 * 工程グループコード設定
	 * @param operationGroupCd 工程グループコード
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


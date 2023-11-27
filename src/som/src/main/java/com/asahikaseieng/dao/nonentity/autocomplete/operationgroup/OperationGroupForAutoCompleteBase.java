/*
 * Created on 2011/04/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.operationgroup;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OperationGroupForAutoCompleteクラス.
 * @author t0011036
 */
public class OperationGroupForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OperationGroupForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/*  */
	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/*  */
	/** COLUMNアノテーション operationGroupName */
	public static final String operationGroupName_COLUMN = "OPERATION_GROUP_NAME";

	//
	// インスタンスフィールド
	//

	/**  */
	private String operationGroupCd;

	/**  */
	private String operationGroupName;

	//
	// インスタンスメソッド
	//

	/**
	 * operationGroupCd取得.
	 * 
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return this.operationGroupCd;
	}

	/**
	 * operationGroupCd設定.
	 * 
	 * @param operationGroupCd operationGroupCd
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}

	/**
	 * operationGroupName取得.
	 * 
	 * @return operationGroupName
	 */
	public String getOperationGroupName() {
		return this.operationGroupName;
	}

	/**
	 * operationGroupName設定.
	 * 
	 * @param operationGroupName operationGroupName
	 */
	public void setOperationGroupName(final String operationGroupName) {
		this.operationGroupName = operationGroupName;
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


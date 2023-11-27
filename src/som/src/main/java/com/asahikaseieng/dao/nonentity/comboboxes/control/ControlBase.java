/*
 * Created on 2008/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.control;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 操作IDと操作名称を保持するBeanクラス.
 * @author tosco
 */
public class ControlBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ControlBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CONTROL";

	/** COLUMNアノテーション 操作ID */
	public static final String controlId_COLUMN = "CONTROL_ID";

	/** COLUMNアノテーション 操作名称 */
	public static final String controlName_COLUMN = "CONTROL_NAME";

	//
	// インスタンスフィールド
	//

	//操作ID 
	private BigDecimal controlId;

	//操作名称
	private String controlName;

	//
	// インスタンスメソッド
	//

	/**
	 * 操作ID取得.
	 * @return BigDecimal 操作ID
	 */
	public BigDecimal getControlId() {
		return controlId;
	}

	/**
	 * 操作ID設定.
	 * @param controlId 操作ID
	 */
	public void setControlId(final BigDecimal controlId) {
		this.controlId = controlId;
	}

	/**
	 * 操作名称取得.
	 * @return String 操作名称
	 */
	public String getControlName() {
		return controlName;
	}

	/**
	 * 操作名称設定.
	 * @param controlName 操作名称
	 */
	public void setControlName(final String controlName) {
		this.controlName = controlName;
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


/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ComponentListクラス.
 * @author t0011036
 */
public class ComponentListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ComponentListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション componentCd */
	public static final String componentCd_COLUMN = "COMPONENT_CD";

	/** COLUMNアノテーション componentName */
	public static final String componentName_COLUMN = "COMPONENT_NAME";

	/** COLUMNアノテーション shortComponentName */
	public static final String shortComponentName_COLUMN = "SHORT_COMPONENT_NAME";

	//
	// インスタンスフィールド
	//

	private String componentCd;

	private String componentName;

	private String shortComponentName;

	//
	// インスタンスメソッド
	//

	/**
	 * componentCd取得.
	 * @return componentCd
	 */
	public String getComponentCd() {
		return this.componentCd;
	}

	/**
	 * componentCd設定.
	 * @param componentCd componentCd
	 */
	public void setComponentCd(final String componentCd) {
		this.componentCd = componentCd;
	}

	/**
	 * componentName取得.
	 * @return componentName
	 */
	public String getComponentName() {
		return this.componentName;
	}

	/**
	 * componentName設定.
	 * @param componentName componentName
	 */
	public void setComponentName(final String componentName) {
		this.componentName = componentName;
	}

	/**
	 * shortComponentName取得.
	 * @return shortComponentName
	 */
	public String getShortComponentName() {
		return this.shortComponentName;
	}

	/**
	 * shortComponentName設定.
	 * @param shortComponentName shortComponentName
	 */
	public void setShortComponentName(final String shortComponentName) {
		this.shortComponentName = shortComponentName;
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


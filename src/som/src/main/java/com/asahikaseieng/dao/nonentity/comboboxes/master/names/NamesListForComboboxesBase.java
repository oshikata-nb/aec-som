/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.names;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * NamesListForComboboxesクラス.
 * @author t0011036
 */
public class NamesListForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NamesListForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション nameCd */
	public static final String nameCd_COLUMN = "NAME_CD";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	//
	// インスタンスフィールド
	//

	private String nameCd;

	private String name01;

	//
	// インスタンスメソッド
	//

	/**
	 * nameCd取得.
	 * @return nameCd
	 */
	public String getNameCd() {
		return this.nameCd;
	}

	/**
	 * nameCd設定.
	 * @param nameCd nameCd
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * name01取得.
	 * @return name01
	 */
	public String getName01() {
		return this.name01;
	}

	/**
	 * name01設定.
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
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


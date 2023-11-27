/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeResouceForAutoCompleteクラス.
 * @author kanri-user
 */
public class RecipeResouceForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション resouceCd */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	//
	// インスタンスフィールド
	//

	private String resouceCd;

	private String resouceName;

	//
	// インスタンスメソッド
	//

	/**
	 * resouceCd取得.
	 * @return resouceCd
	 */
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * resouceCd設定.
	 * @param resouceCd resouceCd
	 */
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * resouceName取得.
	 * @return resouceName
	 */
	public String getResouceName() {
		return this.resouceName;
	}

	/**
	 * resouceName設定.
	 * @param resouceName resouceName
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
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


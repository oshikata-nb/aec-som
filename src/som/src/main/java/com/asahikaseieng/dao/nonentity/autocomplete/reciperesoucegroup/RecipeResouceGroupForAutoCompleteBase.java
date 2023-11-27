/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeResouceGroupForAutoCompleteクラス.
 * @author t0011036
 */
public class RecipeResouceGroupForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション resouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション resouceGroupName */
	public static final String resouceGroupName_COLUMN = "RESOUCE_GROUP_NAME";

	//
	// インスタンスフィールド
	//

	private String resouceGroupCd;

	private String resouceGroupName;

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


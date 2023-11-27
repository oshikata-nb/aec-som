/*
 * Created on 2009/07/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OffsetGroupForAutoCompleteクラス.
 * @author t0011036
 */
public class OffsetGroupForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetGroupForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション offsetGroupCd */
	public static final String offsetGroupCd_COLUMN = "OFFSET_GROUP_CD";

	/** COLUMNアノテーション offsetGroupName */
	public static final String offsetGroupName_COLUMN = "OFFSET_GROUP_NAME";

	//
	// インスタンスフィールド
	//

	private String offsetGroupCd;

	private String offsetGroupName;

	//
	// インスタンスメソッド
	//

	/**
	 * offsetGroupCd取得.
	 * @return offsetGroupCd
	 */
	public String getOffsetGroupCd() {
		return this.offsetGroupCd;
	}

	/**
	 * offsetGroupCd設定.
	 * @param offsetGroupCd offsetGroupCd
	 */
	public void setOffsetGroupCd(final String offsetGroupCd) {
		this.offsetGroupCd = offsetGroupCd;
	}

	/**
	 * offsetGroupName取得.
	 * @return offsetGroupName
	 */
	public String getOffsetGroupName() {
		return this.offsetGroupName;
	}

	/**
	 * offsetGroupName設定.
	 * @param offsetGroupName offsetGroupName
	 */
	public void setOffsetGroupName(final String offsetGroupName) {
		this.offsetGroupName = offsetGroupName;
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


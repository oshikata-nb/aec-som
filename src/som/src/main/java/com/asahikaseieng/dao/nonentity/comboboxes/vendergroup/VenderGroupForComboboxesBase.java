/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.vendergroup;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * VenderGroupForComboboxesBaseクラス.
 * @author 
 */
public class VenderGroupForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public VenderGroupForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション venderGroupCd */
	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";

	/** COLUMNアノテーション venderGroupName */
	public static final String venderGroupName_COLUMN = "VENDER_GROUP_NAME";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";


	//
	// インスタンスフィールド
	//

	private String venderGroupCd;

	private String venderGroupName;


	//
	// インスタンスメソッド
	//

	/**
	 * venderGroupCd取得.
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return this.venderGroupCd;
	}

	/**
	 * venderGroupCd設定.
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(final String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}

	/**
	 * venderGroupName取得.
	 * @return venderGroupName
	 */
	public String getVenderGroupName() {
		return this.venderGroupName;
	}

	/**
	 * venderGroupName設定.
	 * @param venderGroupName venderGroupName
	 */
	public void setVenderGroupName(final String venderGroupName) {
		this.venderGroupName = venderGroupName;
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


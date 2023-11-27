/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ReasonListクラス.
 * @author t0011036
 */
public class ReasonListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ReasonListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション ryCd */
	public static final String ryCd_COLUMN = "RY_CD";

	/** COLUMNアノテーション ryDescription */
	public static final String ryDescription_COLUMN = "RY_DESCRIPTION";

	/** COLUMNアノテーション defaultFlg */
	public static final String defaultFlg_COLUMN = "DEFAULT_FLG";

	/** COLUMNアノテーション defaultFlgName */
	public static final String defaultFlgName_COLUMN = "DEFAULT_FLG_NAME";

	//
	// インスタンスフィールド
	//

	private String ryCd;

	private String ryDescription;

	private java.math.BigDecimal defaultFlg;

	private String defaultFlgName;

	//
	// インスタンスメソッド
	//

	/**
	 * ryCd取得.
	 * @return ryCd
	 */
	public String getRyCd() {
		return this.ryCd;
	}

	/**
	 * ryCd設定.
	 * @param ryCd ryCd
	 */
	public void setRyCd(final String ryCd) {
		this.ryCd = ryCd;
	}

	/**
	 * ryDescription取得.
	 * @return ryDescription
	 */
	public String getRyDescription() {
		return this.ryDescription;
	}

	/**
	 * ryDescription設定.
	 * @param ryDescription ryDescription
	 */
	public void setRyDescription(final String ryDescription) {
		this.ryDescription = ryDescription;
	}

	/**
	 * defaultFlg取得.
	 * @return defaultFlg
	 */
	public java.math.BigDecimal getDefaultFlg() {
		return this.defaultFlg;
	}

	/**
	 * defaultFlg設定.
	 * @param defaultFlg defaultFlg
	 */
	public void setDefaultFlg(final java.math.BigDecimal defaultFlg) {
		this.defaultFlg = defaultFlg;
	}

	/**
	 * defaultFlgName取得.
	 * @return defaultFlgName
	 */
	public String getDefaultFlgName() {
		return this.defaultFlgName;
	}

	/**
	 * defaultFlgName設定.
	 * @param defaultFlgName defaultFlgName
	 */
	public void setDefaultFlgName(final String defaultFlgName) {
		this.defaultFlgName = defaultFlgName;
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


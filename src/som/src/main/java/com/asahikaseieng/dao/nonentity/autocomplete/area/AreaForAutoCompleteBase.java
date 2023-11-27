/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.area;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AreaForAutoCompleteクラス.
 * @author t0011036
 */
public class AreaForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AreaForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション areaCd */
	public static final String areaCd_COLUMN = "AREA_CD";

	/** COLUMNアノテーション areaName */
	public static final String areaName_COLUMN = "AREA_NAME";

	//
	// インスタンスフィールド
	//

	private String areaCd;

	private String areaName;

	//
	// インスタンスメソッド
	//

	/**
	 * areaCd取得.
	 * @return areaCd
	 */
	public String getAreaCd() {
		return this.areaCd;
	}

	/**
	 * areaCd設定.
	 * @param areaCd areaCd
	 */
	public void setAreaCd(final String areaCd) {
		this.areaCd = areaCd;
	}

	/**
	 * areaName取得.
	 * @return areaName
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * areaName設定.
	 * @param areaName areaName
	 */
	public void setAreaName(final String areaName) {
		this.areaName = areaName;
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


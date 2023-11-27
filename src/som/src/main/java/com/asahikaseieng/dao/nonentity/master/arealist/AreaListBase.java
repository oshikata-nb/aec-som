/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.arealist;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * AreaListクラス.
 * @author t0011036
 */
public class AreaListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AreaListBase() {
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
	 * areaCdを取得します。
	 * @return areaCd
	 */
	public String getAreaCd() {
		return areaCd;
	}

	/**
	 * areaCdを設定します。
	 * @param areaCd areaCd
	 */
	public void setAreaCd(final String areaCd) {
		this.areaCd = areaCd;
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

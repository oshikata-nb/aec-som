/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.areadetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * AreaDetailクラス.
 * @author t0011036
 */
public class AreaDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AreaDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション areaCd */
	public static final String areaCd_COLUMN = "AREA_CD";

	/** COLUMNアノテーション areaName */
	public static final String areaName_COLUMN = "AREA_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String areaCd;

	private String areaName;

	private java.sql.Timestamp updateDate;

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
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
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

/*
 * Created on Thu Jan 15 16:25:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.area;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * AreaBaseクラス.
 * @author t0011036
 */
public class AreaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AreaBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "AREA";

	// /** IDアノテーション areaCd. */
	// public static final String areaCd_ID = "assigned";

	/** COLUMNアノテーション areaCd. */
	public static final String areaCd_COLUMN = "AREA_CD";

	/** COLUMNアノテーション areaName. */
	public static final String areaName_COLUMN = "AREA_NAME";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String areaCd;

	private String areaName;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

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
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
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
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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
}

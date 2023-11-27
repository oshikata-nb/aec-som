/*
 * Created on 2009/07/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgroupdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OffsetGroupDetailクラス.
 * @author t0011036
 */
public class OffsetGroupDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetGroupDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション offsetGroupCd */
	public static final String offsetGroupCd_COLUMN = "OFFSET_GROUP_CD";

	/** COLUMNアノテーション offsetGroupName */
	public static final String offsetGroupName_COLUMN = "OFFSET_GROUP_NAME";

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String offsetGroupCd;

	private String offsetGroupName;

	private String venderDivision;

	private String venderCd;

	private String venderName1;

	private java.sql.Timestamp updateDate;

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
	 * venderDivision取得.
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * venderDivision設定.
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
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


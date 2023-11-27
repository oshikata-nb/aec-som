/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgroupdelete;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OffsetGroupDeleteクラス.
 * @author t0011036
 */
public class OffsetGroupDeleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetGroupDeleteBase() {
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

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	//
	// インスタンスフィールド
	//

	private String offsetGroupCd;

	private String offsetGroupName;

	private String venderDivision;

	private String venderCd;

	private String updatorCd;

	private java.sql.Timestamp updateDate;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

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


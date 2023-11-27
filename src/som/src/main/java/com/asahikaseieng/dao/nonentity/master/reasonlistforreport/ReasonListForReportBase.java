/*
 * Created on 2009/08/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ReasonListForReportクラス.
 * @author t0011036
 */
public class ReasonListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ReasonListForReportBase() {
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

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション defaultFlgName */
	public static final String defaultFlgName_COLUMN = "DEFAULT_FLG_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String ryCd;

	private String ryDescription;

	private java.math.BigDecimal defaultFlg;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	private String updatorCd;

	private java.sql.Timestamp updateDate;

	private String defaultFlgName;

	private String inputorName;

	private String updatorName;

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
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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


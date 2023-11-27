/*
 * Created on Mon Feb 02 11:37:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.label;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LabelBaseクラス.
 * @author t0011036
 */
public class LabelBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LabelBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LABEL";


//	/** IDアノテーション commonCd. */
//	public static final String commonCd_ID = "assigned";
//	/** IDアノテーション labelCd. */
//	public static final String labelCd_ID = "assigned";

	/** COLUMNアノテーション labelCd. */
	public static final String labelCd_COLUMN = "LABEL_CD";

	/** COLUMNアノテーション labelName. */
	public static final String labelName_COLUMN = "LABEL_NAME";

	/** COLUMNアノテーション labelPath. */
	public static final String labelPath_COLUMN = "LABEL_PATH";

	/** COLUMNアノテーション commonCd. */
	public static final String commonCd_COLUMN = "COMMON_CD";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String labelCd;

	private String labelName;

	private String labelPath;

	private String commonCd;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	private String updatorCd;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * labelCd取得.
	 * @return labelCd
	 */
	public String getLabelCd() {
		return this.labelCd;
	}

	/**
	 * labelCd設定.
	 * @param labelCd labelCd
	 */
	public void setLabelCd(final String labelCd) {
		this.labelCd = labelCd;
	}

	/**
	 * labelName取得.
	 * @return labelName
	 */
	public String getLabelName() {
		return this.labelName;
	}

	/**
	 * labelName設定.
	 * @param labelName labelName
	 */
	public void setLabelName(final String labelName) {
		this.labelName = labelName;
	}

	/**
	 * labelPath取得.
	 * @return labelPath
	 */
	public String getLabelPath() {
		return this.labelPath;
	}

	/**
	 * labelPath設定.
	 * @param labelPath labelPath
	 */
	public void setLabelPath(final String labelPath) {
		this.labelPath = labelPath;
	}

	/**
	 * commonCd取得.
	 * @return commonCd
	 */
	public String getCommonCd() {
		return this.commonCd;
	}

	/**
	 * commonCd設定.
	 * @param commonCd commonCd
	 */
	public void setCommonCd(final String commonCd) {
		this.commonCd = commonCd;
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

/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemTechLabelDetailクラス.
 * @author t0011036
 */
public class ItemTechLabelDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemTechLabelDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション labelCd */
	public static final String labelCd_COLUMN = "LABEL_CD";

	/** COLUMNアノテーション labelPath */
	public static final String labelPath_COLUMN = "LABEL_PATH";

	/** COLUMNアノテーション commonCd */
	public static final String commonCd_COLUMN = "COMMON_CD";

	/** COLUMNアノテーション commonName */
	public static final String commonName_COLUMN = "COMMON_NAME";

	/** COLUMNアノテーション commonValue */
	public static final String commonValue_COLUMN = "COMMON_VALUE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String labelCd;

	private String labelPath;

	private String commonCd;

	private String commonName;

	private String commonValue;

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
	 * commonName取得.
	 * @return commonName
	 */
	public String getCommonName() {
		return this.commonName;
	}

	/**
	 * commonName設定.
	 * @param commonName commonName
	 */
	public void setCommonName(final String commonName) {
		this.commonName = commonName;
	}

	/**
	 * commonValue取得.
	 * @return commonValue
	 */
	public String getCommonValue() {
		return this.commonValue;
	}

	/**
	 * commonValue設定.
	 * @param commonValue commonValue
	 */
	public void setCommonValue(final String commonValue) {
		this.commonValue = commonValue;
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


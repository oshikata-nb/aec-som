/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldetaillist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CalDetailListクラス.
 * @author t0011036
 */
public class CalDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CalDetailListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション calCd */
	public static final String calCd_COLUMN = "CAL_CD";

	/** COLUMNアノテーション calName */
	public static final String calName_COLUMN = "CAL_NAME";

	/** COLUMNアノテーション calYear */
	public static final String calYear_COLUMN = "CAL_YEAR";

	/** COLUMNアノテーション calYyyy */
	public static final String calYyyy_COLUMN = "CAL_YYYY";

	/** COLUMNアノテーション calMm */
	public static final String calMm_COLUMN = "CAL_MM";

	/** COLUMNアノテーション calDd */
	public static final String calDd_COLUMN = "CAL_DD";

	//
	// インスタンスフィールド
	//

	private String calCd;

	private String calName;

	private java.math.BigDecimal calYear;

	private String calYyyy;

	private String calMm;

	private String calDd;

	//
	// インスタンスメソッド
	//

	/**
	 * calCd取得.
	 * @return calCd
	 */
	public String getCalCd() {
		return this.calCd;
	}

	/**
	 * calCd設定.
	 * @param calCd calCd
	 */
	public void setCalCd(final String calCd) {
		this.calCd = calCd;
	}

	/**
	 * calName取得.
	 * @return calName
	 */
	public String getCalName() {
		return this.calName;
	}

	/**
	 * calName設定.
	 * @param calName calName
	 */
	public void setCalName(final String calName) {
		this.calName = calName;
	}

	/**
	 * calYear取得.
	 * @return calYear
	 */
	public java.math.BigDecimal getCalYear() {
		return this.calYear;
	}

	/**
	 * calYear設定.
	 * @param calYear calYear
	 */
	public void setCalYear(final java.math.BigDecimal calYear) {
		this.calYear = calYear;
	}

	/**
	 * calYyyy取得.
	 * @return calYyyy
	 */
	public String getCalYyyy() {
		return this.calYyyy;
	}

	/**
	 * calYyyy設定.
	 * @param calYyyy calYyyy
	 */
	public void setCalYyyy(final String calYyyy) {
		this.calYyyy = calYyyy;
	}

	/**
	 * calMm取得.
	 * @return calMm
	 */
	public String getCalMm() {
		return this.calMm;
	}

	/**
	 * calMm設定.
	 * @param calMm calMm
	 */
	public void setCalMm(final String calMm) {
		this.calMm = calMm;
	}

	/**
	 * calDd取得.
	 * @return calDd
	 */
	public String getCalDd() {
		return this.calDd;
	}

	/**
	 * calDd設定.
	 * @param calDd calDd
	 */
	public void setCalDd(final String calDd) {
		this.calDd = calDd;
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


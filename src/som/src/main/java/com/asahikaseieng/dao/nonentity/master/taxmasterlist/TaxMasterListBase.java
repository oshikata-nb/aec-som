/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.taxmasterlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * LocationListクラス.
 * @author t0011036
 */
public class TaxMasterListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TaxMasterListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション taxCd */
	public static final String taxCd_COLUMN = "TAX_CD";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	//
	// インスタンスフィールド
	//

	private String taxCd;

	private String taxRatio;

	//
	// インスタンスメソッド
	//

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
	 * taxRatioを取得します。
	 * @return taxRatio
	 */
	public String getTaxRatio() {
		return taxRatio;
	}

	/**
	 * taxRatioを設定します。
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(String taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * taxCdを取得します。
	 * @return taxCd
	 */
	public String getTaxCd() {
		return taxCd;
	}

	/**
	 * taxCdを設定します。
	 * @param taxCd taxCd
	 */
	public void setTaxCd(String taxCd) {
		this.taxCd = taxCd;
	}
}

/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ItemCategoryListForComboboxesクラス.
 * @author t0011036
 */
public class TaxMasterListForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TaxMasterListForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション taxCd */
	public static final String taxCd_COLUMN = "TAX_CD";
	
	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション comboboxValue */
	public static final String comboboxValue_COLUMN = "COMBOBOX_VALUE";

	/** COLUMNアノテーション comboboxName */
	public static final String comboboxName_COLUMN = "COMBOBOX_NAME";
	

	//
	// インスタンスフィールド
	//
	private String taxCd;
	
	/** taxRatio */
	private String taxRatio;

	private String comboboxValue;

	private String comboboxName;

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
	 * comboboxValueを取得します。
	 * @return comboboxValue
	 */
	public String getComboboxValue() {
		return comboboxValue;
	}

	/**
	 * comboboxValueを設定します。
	 * @param comboboxValue comboboxValue
	 */
	public void setComboboxValue(String comboboxValue) {
		this.comboboxValue = comboboxValue;
	}

	/**
	 * comboboxNameを取得します。
	 * @return comboboxName
	 */
	public String getComboboxName() {
		return comboboxName;
	}

	/**
	 * comboboxNameを設定します。
	 * @param comboboxName comboboxName
	 */
	public void setComboboxName(String comboboxName) {
		this.comboboxName = comboboxName;
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

}


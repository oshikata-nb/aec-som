/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.slipsales;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SlipSalesCarryListForComboboxesBaseクラス.
 * @author 
 */
public class SlipSalesCarryListForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipSalesCarryListForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carryName2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	/** COLUMNアノテーション tranceportLeadTime */
	public static final String tranceportLeadTime_COLUMN = "TRANCEPORT_LEAD_TIME";

	/** COLUMNアノテーション companyCd */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション labelCd */
	public static final String labelCd_COLUMN = "LABEL_CD";

	/** COLUMNアノテーション labelName */
	public static final String labelName_COLUMN = "LABEL_NAME";

	/** COLUMNアノテーション labelPath */
	public static final String labelPath_COLUMN = "LABEL_PATH";

	//
	// インスタンスフィールド
	//

	private String carryCd;

	private String carryName1;

	private String carryName2;

	//
	// インスタンスメソッド
	//

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryName1取得.
	 * @return carryName1
	 */
	public String getCarryName1() {
		return this.carryName1;
	}

	/**
	 * carryName1設定.
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * carryName2取得.
	 * @return carryName2
	 */
	public String getCarryName2() {
		return this.carryName2;
	}

	/**
	 * carryName2設定.
	 * @param carryName2 carryName2
	 */
	public void setCarryName2(final String carryName2) {
		this.carryName2 = carryName2;
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


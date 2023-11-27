/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.shipping;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 出荷指図－運送会社コンボボックス用データ
 *
 * @author tosco
 */
public class ShippingCarryForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingCarryForComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "CARRY";

	/** COLUMNアノテーション carry_cd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carry_name1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carry_name2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	//
	// インスタンスフィールド
	//

	/** 運送会社コード */
	private String carryCd;

	/** 運送会社名称1 */
	private String carryName1;

	/** 運送会社名称2 */
	private String carryName2;

	//
	// インスタンスメソッド
	//

	/**
	 * 運送会社コード取得
	 * @return String 運送会社コード
	*/
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * 運送会社コード設定
	 * @param carryCd 運送会社コード
	*/
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * 運送会社名称1取得
	 * @return String 運送会社名称1
	*/
	public String getCarryName1() {
		return this.carryName1;
	}

	/**
	 * 運送会社名称1設定
	 * @param carryName1 運送会社名称1
	*/
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * 運送会社名称2取得
	 * @return String 運送会社名称2
	*/
	public String getCarryName2() {
		return this.carryName2;
	}

	/**
	 * 運送会社名称2設定
	 * @param carryName2 運送会社名称2
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

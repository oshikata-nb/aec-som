/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfilelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CarryListクラス.
 * @author kiguchi
 */
public class CarryFileListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryFileListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName1 */
	public static final String carryName_COLUMN = "CARRY_NAME";

	/** COLUMNアノテーション shortCarryName1 */
	public static final String existsSetting_COLUMN = "EXISTS_SETTING";


	//
	// インスタンスフィールド
	//

	private String carryCd;

	private String carryName;

	private String existsSetting;


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
	 * carryNameを取得します。
	 * @return carryName
	 */
	public String getCarryName() {
		return carryName;
	}

	/**
	 * carryNameを設定します。
	 * @param carryName carryName
	 */
	public void setCarryName(String carryName) {
		this.carryName = carryName;
	}

	/**
	 * existsSettingを取得します。
	 * @return existsSetting
	 */
	public String getExistsSetting() {
		return existsSetting;
	}

	/**
	 * existsSettingを設定します。
	 * @param existsSetting existsSetting
	 */
	public void setExistsSetting(String existsSetting) {
		this.existsSetting = existsSetting;
	}
}


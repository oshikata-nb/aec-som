/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.slipshipping;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SlipShippingPostalClientListForComboboxesBaseクラス.
 * @author 
 */
public class SlipShippingPostalClientListForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipShippingPostalClientListForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション postalClientCd */
	public static final String carryCd_COLUMN = "POSTAL_CLIENT_CD";

	/** COLUMNアノテーション postalName */
	public static final String carryName1_COLUMN = "POSTAL_NAME";

	//
	// インスタンスフィールド
	//

	private String postalClientCd;

	private String postalName;

	//
	// インスタンスメソッド
	//
	
	/**
	 * postalClientCdを取得します。
	 * @return postalClientCd
	 */
	public String getPostalClientCd() {
		return postalClientCd;
	}

	/**
	 * postalClientCdを設定します。
	 * @param postalClientCd postalClientCd
	 */
	public void setPostalClientCd(String postalClientCd) {
		this.postalClientCd = postalClientCd;
	}

	/**
	 * postalNameを取得します。
	 * @return postalName
	 */
	public String getPostalName() {
		return postalName;
	}

	/**
	 * postalNameを設定します。
	 * @param postalName postalName
	 */
	public void setPostalName(String postalName) {
		this.postalName = postalName;
	}

}


/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatebalancelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * EstimateBalanceListクラス.
 * @author t0011036
 */
public class EstimateBalanceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EstimateBalanceListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション shopLevel */
	public static final String shopLevel_COLUMN = "SHOP_LEVEL";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション shopLevelName */
	public static final String shopLevelName_COLUMN = "SHOP_LEVEL_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal shopLevel;

	private String venderCd;

	private String venderName1;

	private String shopLevelName;

	//
	// インスタンスメソッド
	//

	/**
	 * shopLevel取得.
	 * @return shopLevel
	 */
	public java.math.BigDecimal getShopLevel() {
		return this.shopLevel;
	}

	/**
	 * shopLevel設定.
	 * @param shopLevel shopLevel
	 */
	public void setShopLevel(final java.math.BigDecimal shopLevel) {
		this.shopLevel = shopLevel;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * shopLevelName取得.
	 * @return shopLevelName
	 */
	public String getShopLevelName() {
		return this.shopLevelName;
	}

	/**
	 * shopLevelName設定.
	 * @param shopLevelName shopLevelName
	 */
	public void setShopLevelName(final String shopLevelName) {
		this.shopLevelName = shopLevelName;
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


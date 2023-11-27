/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売上詳細(預り品)画面_得意先取得用データ格納クラス.
 * 
 * @author tosco
 */
public class SalesDetailKeepVenderListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailKeepVenderListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "BALANCE";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション shopLevel */
	public static final String shopLevel_COLUMN = "SHOP_LEVEL";

	//
	// インスタンスフィールド
	//

	/** 取引先コード（得意先） */
	private String venderCd;

	/** 次店 */
	private BigDecimal shopLevel;

	//
	// インスタンスメソッド
	//

	/**
	 * 得意先コード（得意先）取得
	 * @return String 得意先コード（得意先）
	*/
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 得意先コード（得意先）設定
	 * @param venderCd 得意先コード（得意先）
	*/
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 次店取得
	 * @return BigDecimal 次店
	*/
	public BigDecimal getShopLevel() {
		return this.shopLevel;
	}

	/**
	 * 次店設定
	 * @param shopLevel 次店
	*/
	public void setShopLevel(final BigDecimal shopLevel) {
		this.shopLevel = shopLevel;
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

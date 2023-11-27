/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ロット在庫取得用データ格納クラス.
 * 
 * @author
 */
public class RdirectionLotInventoryListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RdirectionLotInventoryListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LOT_INVENTORY";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション aliasLotNo */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	//
	// インスタンスフィールド
	//

	/** ロケーションコード */
	private String locationCd;

	/** 在庫数量 */
	private BigDecimal inventoryQty;

	/** 原料ロット番号/製品ロット番号 */
	private String aliasLotNo;

	//
	// インスタンスメソッド
	//

	/**
	 * ロケーションコード取得
	 * @return String ロケーションコード
	*/
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * ロケーションコード設定
	 * @param locationCd ロケーションコード
	*/
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 在庫数量取得
	 * @return BigDecimal 在庫数量
	*/
	public BigDecimal getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * 在庫数量設定
	 * @param inventoryQty 在庫数量
	*/
	public void setInventoryQty(final BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * 原料ロット番号/製品ロット番号取得
	 * @return String 原料ロット番号/製品ロット番号
	*/
	public String getAliasLotNo() {
		return this.aliasLotNo;
	}

	/**
	 * 原料ロット番号/製品ロット番号設定
	 * @param aliasLotNo 原料ロット番号/製品ロット番号
	*/
	public void setAliasLotNo(final String aliasLotNo) {
		this.aliasLotNo = aliasLotNo;
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

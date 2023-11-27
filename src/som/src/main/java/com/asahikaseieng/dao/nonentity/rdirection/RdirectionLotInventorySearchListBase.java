/*
 * Created on 2009/03/27
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
 * 製造実績－ロット検索ポップアップ画面データ格納クラス.
 * @author tosco
 */
public class RdirectionLotInventorySearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RdirectionLotInventorySearchListBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション aliasLotNo. */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inventoryQty. */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション inoutQty. */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション fraction. */
	public static final String fraction_COLUMN = "FRACTION";

	//
	// インスタンスフィールド
	//
	/** ロケーションコード */
	private String locationCd;

	/** 品目コード */
	private String itemCd;

	/** 原料ロット番号/製品ロット番号 */
	private String aliasLotNo;

	/** 入荷ロット番号/包装指図番号 */
	private String lotNo;

	/** 在庫数量 */
	private BigDecimal inventoryQty;

	/** 荷姿数 */
	private BigDecimal inoutQty;

	/** 端数 */
	private BigDecimal fraction;

	//
	// インスタンスメソッド
	//
	/**
	 * ロケーションコード取得.
	 * @return String ロケーションコード
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * ロケーションコード設定.
	 * @param locationCd ロケーションコード
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 原料ロット番号/製品ロット番号を取得します。
	 * @return aliasLotNo
	 */
	public String getAliasLotNo() {
		return aliasLotNo;
	}

	/**
	 * 原料ロット番号/製品ロット番号を設定します。
	 * @param aliasLotNo 原料ロット番号/製品ロット番号
	 */
	public void setAliasLotNo(final String aliasLotNo) {
		this.aliasLotNo = aliasLotNo;
	}

	/**
	 * 入荷ロット番号/包装指図番号取得.
	 * @return String 入荷ロット番号/包装指図番号
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * 入荷ロット番号/包装指図番号設定.
	 * @param lotNo 入荷ロット番号/包装指図番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 在庫数量取得.
	 * @return BigDecimal 在庫数量
	 */
	public BigDecimal getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * 在庫数量設定.
	 * @param inventoryQty 在庫数量
	 */
	public void setInventoryQty(final java.math.BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * 荷姿数取得.
	 * @return BigDecimal 荷姿数
	 */
	public BigDecimal getInoutQty() {
		return inoutQty;
	}

	/**
	 * 荷姿数設定.
	 * @param inoutQty 荷姿数
	 */
	public void setInoutQty(final BigDecimal inoutQty) {
		this.inoutQty = inoutQty;
	}

	/**
	 * 端数取得.
	 * @return BigDecimal 端数
	 */
	public BigDecimal getFraction() {
		return fraction;
	}

	/**
	 * 端数設定.
	 * @param fraction 端数
	 */
	public void setFraction(final BigDecimal fraction) {
		this.fraction = fraction;
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


/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 包装指図－在庫確認ポップアップ画面データ格納クラス.
 * @author tosco
 */
public class PkgDirectionLotInventorySearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionLotInventorySearchListBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inventoryQty. */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	//
	// インスタンスフィールド
	//
	/** ロケーションコード */
	private String locationCd;

	/** 品目コード */
	private String itemCd;

	/** 入荷ロット番号/包装指図番号 */
	private String lotNo;

	/** 在庫数量 */
	private BigDecimal inventoryQty;

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


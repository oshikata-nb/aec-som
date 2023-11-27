/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 包装実績－ロット検索画面データ格納クラス.
 * @author tosco
 */
public class PkgRdirectionLotInventorySearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionLotInventorySearchListBase() {
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

	/** COLUMNアノテーション aliasLotNo. */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	/** COLUMNアノテーション issueDateNo. */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

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

	/** 原料ロット番号/製品ロット番号 */
	private String aliasLotNo;

	/** ロット発生時刻 */
	private Timestamp issueDate;

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
	 * 原料ロット番号/製品ロット番号取得.
	 * @return String 原料ロット番号/製品ロット番号
	 */
	public String getAliasLotNo() {
		return this.aliasLotNo;
	}

	/**
	 * 原料ロット番号/製品ロット番号設定.
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

	/**
	 * issueDateを取得します。
	 * @return issueDate
	 */
	public Timestamp getIssueDate() {
		return issueDate;
	}

	/**
	 * issueDateを設定します。
	 * @param issueDate issueDate
	 */
	public void setIssueDate(final Timestamp issueDate) {
		this.issueDate = issueDate;
	}
}

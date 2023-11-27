/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ロット検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class ShippingResultLotSearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultLotSearchListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LOT_INVENTORY";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション backorderQty */
	public static final String backorderQty_COLUMN = "BACKORDER_QTY";

	/** COLUMNアノテーション inspectionQty */
	public static final String inspectionQty_COLUMN = "INSPECTION_QTY";

	/** COLUMNアノテーション assignQty */
	public static final String assignQty_COLUMN = "ASSIGN_QTY";

	/** COLUMNアノテーション issueDate */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/** COLUMNアノテーション aliasLotNo */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	//
	// インスタンスフィールド
	//

	/** ロケーション */
	private String locationCd;

	/** ロット番号 */
	private String lotNo;

	/** 在庫量 */
	private BigDecimal inventoryQty;

	/** 発注残 */
	private BigDecimal backorderQty;

	/** 検査待 */
	private BigDecimal inspectionQty;

	/** 引当残 */
	private BigDecimal assignQty;

	/** 完成日 */
	private Timestamp issueDate;

	/** 自社ロット */
	private String aliasLotNo;

	//
	// インスタンスメソッド
	//

	/**
	 * ロケーション取得.
	 * @return String ロケーション
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * ロケーション設定.
	 * @param locationCd ロケーション
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * ロット番号取得.
	 * @return String ロット番号
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロット番号設定.
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 在庫量取得.
	 * @return String 在庫量
	 */
	public BigDecimal getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * 在庫量設定.
	 * @param inventoryQty 在庫量
	 */
	public void setInventoryQty(final BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * 発注残取得.
	 * @return String 発注残
	 */
	public BigDecimal getBackorderQty() {
		return this.backorderQty;
	}

	/**
	 * 発注残設定.
	 * @param backorderQty 発注残
	 */
	public void setBackorderQty(final BigDecimal backorderQty) {
		this.backorderQty = backorderQty;
	}

	/**
	 * 検査待取得.
	 * @return Timestamp 検査待
	 */
	public BigDecimal getInspectionQty() {
		return this.inspectionQty;
	}

	/**
	 * 検査待設定.
	 * @param inspectionQty 検査待
	 */
	public void setInspectionQty(final BigDecimal inspectionQty) {
		this.inspectionQty = inspectionQty;
	}

	/**
	 * 引当残取得.
	 * @return String 引当残
	 */
	public BigDecimal getAssignQty() {
		return this.assignQty;
	}

	/**
	 * 引当残設定.
	 * @param assignQty 引当残
	 */
	public void setAssignQty(final BigDecimal assignQty) {
		this.assignQty = assignQty;
	}

	/**
	 * 完成日取得.
	 * @return issueDate 完成日
	 */
	public Timestamp getIssueDate() {
		return this.issueDate;
	}

	/**
	 * 完成日設定.
	 * @param issueDate 完成日
	 */
	public void setIssueDate(final Timestamp issueDate) {
		this.issueDate = issueDate;
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
	 * 自社ロット
	 * @return String 自社ロット
	 */
	public String getAliasLotNo() {
		return aliasLotNo;
	}

	/**
	 * 自社ロット
	 * @param aliasLotNo 自社ロット
	 */
	public void setAliasLotNo(final String aliasLotNo) {
		this.aliasLotNo = aliasLotNo;
	}
}

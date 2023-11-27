/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売上詳細(預り品)画面_売上受払履歴格納クラス.
 * 
 * @author tosco
 */
public class SalesDetailKeepSalesInoutRecordListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailKeepSalesInoutRecordListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SALES_INOUT_RECORD";

	/** COLUMNアノテーション salesNo */
	public static final String salesNo_COLUMN = "SALES_NO";

	/** COLUMNアノテーション salesRowNo */
	public static final String salesRowNo_COLUMN = "SALES_ROW_NO";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション aliasLotNo */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	/** COLUMNアノテーション qty */
	public static final String qty_COLUMN = "QTY";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 売上番号 */
	private String salesNo;

	/** 売上行番号 */
	private BigDecimal salesRowNo;

	/** ロケーションコード */
	private String locationCd;

	/** ロット番号 */
	private String lotNo;

	/** 自社ロット番号 */
	private String aliasLotNo;

	/** 数量 */
	private BigDecimal qty;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 売上番号取得
	 * @return String 売上番号
	 */
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * 売上番号設定
	 * @param salesNo 売上番号
	 */
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * 売上行番号取得
	 * @return BigDecimal 売上行番号
	 */
	public BigDecimal getSalesRowNo() {
		return this.salesRowNo;
	}

	/**
	 * 売上行番号設定
	 * @param salesRowNo 売上行番号
	 */
	public void setSalesRowNo(final BigDecimal salesRowNo) {
		this.salesRowNo = salesRowNo;
	}

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
	 * 自社ロット番号取得
	 * @return String 自社ロット番号
	 */
	public String getAliasLotNo() {
		return aliasLotNo;
	}

	/**
	 * 自社ロット番号設定
	 * @param aliasLotNo 自社ロット番号
	 */
	public void setAliasLotNo(final String aliasLotNo) {
		this.aliasLotNo = aliasLotNo;
	}

	/**
	 * ロット番号取得
	 * @return String ロット番号
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロット番号設定
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 数量取得
	 * @return BigDecimal 数量
	 */
	public BigDecimal getQty() {
		return this.qty;
	}

	/**
	 * 数量設定
	 * @param qty 数量
	 */
	public void setQty(final BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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

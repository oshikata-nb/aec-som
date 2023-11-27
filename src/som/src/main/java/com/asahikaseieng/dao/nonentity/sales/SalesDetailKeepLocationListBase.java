/*
 * Created on 2009/03/11
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
 * 売上詳細(預り品)画面_出庫ロケーションデータ格納クラス.
 * 
 * @author tosco
 */
public class SalesDetailKeepLocationListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailKeepLocationListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "INOUT_RECORD";

	/** COLUMNアノテーション inoutNo */
	public static final String inoutNo_COLUMN = "INOUT_NO";

	/** COLUMNアノテーション oderLineNo */
	public static final String oderLineNo_COLUMN = "ODER_LINE_NO";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inoutQty */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	//
	// インスタンスフィールド
	//

	/** 受払番号 */
	private String inoutNo;

	/** オーダー行番号 */
	private BigDecimal oderLineNo;

	/** ロケーションコード */
	private String locationCd;

	/** ロット番号 */
	private String lotNo;

	/** 受払数 */
	private BigDecimal inoutQty;

	//
	// インスタンスメソッド
	//

	/**
	 * 受払番号取得
	 * @return String 受払番号
	*/
	public String getInoutNo() {
		return this.inoutNo;
	}

	/**
	 * 受払番号設定
	 * @param inoutNo 受払番号
	*/
	public void setInoutNo(final String inoutNo) {
		this.inoutNo = inoutNo;
	}

	/**
	 * オーダー行番号取得
	 * @return BigDecimal オーダー行番号
	*/
	public BigDecimal getOderLineNo() {
		return this.oderLineNo;
	}

	/**
	 * オーダー行番号設定
	 * @param oderLineNo オーダー行番号
	*/
	public void setOderLineNo(final BigDecimal oderLineNo) {
		this.oderLineNo = oderLineNo;
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
	 * 受払数取得
	 * @return BigDecimal 受払数
	*/
	public BigDecimal getInoutQty() {
		return this.inoutQty;
	}

	/**
	 * 受払数設定
	 * @param inoutQty 受払数
	*/
	public void setInoutQty(final BigDecimal inoutQty) {
		this.inoutQty = inoutQty;
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

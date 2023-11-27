/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.stockbooking;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 製品入出庫実績取得用データ格納クラス.
 * 
 * @author
 */
public class StockBookingJissekiSeihinListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public StockBookingJissekiSeihinListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JISSEKI_SEIHIN";

	/** COLUMNアノテーション location */
	public static final String location_COLUMN = "LOCATION";

	/** COLUMNアノテーション sumSuuryou */
	public static final String sumSuuryou_COLUMN = "SUM_SUURYOU";

	/** COLUMNアノテーション jikoku */
	public static final String jikoku_COLUMN = "JIKOKU";

	//
	// インスタンスフィールド
	//

	/** ロケーション */
	private String location;

	/** 数量 */
	private BigDecimal sumSuuryou;

	/** 時刻 */
	private Timestamp jikoku;

	//
	// インスタンスメソッド
	//

	/**
	 * ロケーション取得
	 * @return String ロケーション
	*/
	public String getLocation() {
		return this.location;
	}

	/**
	 * ロケーション設定
	 * @param location ロケーション
	*/
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * 数量取得
	 * @return BigDecimal 数量
	*/
	public BigDecimal getSumSuuryou() {
		return this.sumSuuryou;
	}

	/**
	 * 数量設定
	 * @param sumSuuryou 数量
	*/
	public void setSumSuuryou(final BigDecimal sumSuuryou) {
		this.sumSuuryou = sumSuuryou;
	}

	/**
	 * 時刻取得
	 * @return Timestamp 数量
	*/
	public Timestamp getJikoku() {
		return this.jikoku;
	}

	/**
	 * 時刻設定
	 * @param jikoku 数量
	*/
	public void setSumJikoku(final Timestamp jikoku) {
		this.jikoku = jikoku;
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

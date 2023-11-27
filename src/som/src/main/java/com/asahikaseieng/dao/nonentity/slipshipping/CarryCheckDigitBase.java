/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * チェックデジット用データ格納クラス
 * 
 * @author Kiguchi
 */
public class CarryCheckDigitBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryCheckDigitBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション inputorCd. */
	public static final String barcode_COLUMN = "BARCODE";


	//
	// インスタンスフィールド
	//
	private String barcode;

	//
	// インスタンスメソッド
	//
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
	 * barcodeを取得します。
	 * @return barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * barcodeを設定します。
	 * @param barcode barcode
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


}

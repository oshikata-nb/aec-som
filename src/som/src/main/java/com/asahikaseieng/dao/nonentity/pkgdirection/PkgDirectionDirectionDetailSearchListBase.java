/*
 * Created on 2009/02/17
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
 * 包装指図－製造指図明細ポップアップ画面データ格納クラス.
 * @author tosco
 */
public class PkgDirectionDirectionDetailSearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionDetailSearchListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション pkgDirectionNo */
	public static final String pkgDirectionNo_COLUMN = "PKG_DIRECTION_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	//
	// インスタンスフィールド
	//

	/** 包装指図番号 */
	private String pkgDirectionNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 製造指図番号 */
	private String directionNo;

	//
	// インスタンスメソッド
	//

	/**
	 * 包装指図番号取得.
	 * @return String 包装指図番号
	 */
	public String getPkgDirectionNo() {
		return this.pkgDirectionNo;
	}

	/**
	 * 包装指図番号設定.
	 * @param pkgDirectionNo 包装指図番号
	 */
	public void setPkgDirectionNo(final String pkgDirectionNo) {
		this.pkgDirectionNo = pkgDirectionNo;
	}

	/**
	 * 行番号取得.
	 * @return rowNo 行番号
	 */
	public BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * 行番号設定.
	 * @param rowNo 行番号
	 */
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 製造指図番号取得.
	 * @return String 製造指図番号
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 製造指図番号設定.
	 * @param directionNo 製造指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
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


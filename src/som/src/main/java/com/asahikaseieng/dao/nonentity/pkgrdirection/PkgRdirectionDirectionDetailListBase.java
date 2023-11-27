/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 包装実績－製造指図明細データ格納クラス.
 * 
 * @author tosco
 */
public class PkgRdirectionDirectionDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionDirectionDetailListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DIRECTION_DETAIL";

	/** COLUMNアノテーション pkgDirectionNo. */
	public static final String pkgDirectionNo_COLUMN = "PKG_DIRECTION_NO";

	/** COLUMNアノテーション rowNo. */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション directionNo. */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 包装指図番号 */
	private String pkgDirectionNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 製造指図番号 */
	private String directionNo;

	/** 登録日時 */
	private java.sql.Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private java.sql.Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

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
	 * @return BigDecimal 行番号
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
	 * 登録日時取得.
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定.
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得.
	 * @return String 登録者ID
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定.
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得.
	 * @return updateDate 更新日時
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得.
	 * @return String 更新者ID
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定.
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

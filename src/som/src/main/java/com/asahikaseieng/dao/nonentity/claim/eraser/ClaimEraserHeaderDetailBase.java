/*
 * Created on 2008/10/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 消込入力詳細 消込ヘッダー内訳登録更新用Daoクラス.
 * @author tosco
 */
public class ClaimEraserHeaderDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserHeaderDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ERASER_HEADER_DETAIL";

	/** COLUMNアノテーション eraserNo */
	public static final String eraserNo_COLUMN = "ERASER_NO";

	/** COLUMNアノテーション creditNo */
	public static final String creditNo_COLUMN = "CREDIT_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

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

	/** 消込番号 */
	private String eraserNo;

	/** 入金番号 */
	private String creditNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 消込額 */
	private BigDecimal eraserAmount;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ＩＤ */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 消込番号取得.
	 * @return String 消込番号
	 */
	public String getEraserNo() {
		return eraserNo;
	}

	/**
	 * 消込番号設定.
	 * @param eraserNo 消込番号
	 */
	public void setEraserNo(final String eraserNo) {
		this.eraserNo = eraserNo;
	}

	/**
	 * 入金番号取得.
	 * @return String 入金番号
	 */
	public String getCreditNo() {
		return creditNo;
	}

	/**
	 * 入金番号設定.
	 * @param creditNo 入金番号
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
	}

	/**
	 * 行番号取得.
	 * @return String 行番号
	 */
	public BigDecimal getRowNo() {
		return rowNo;
	}

	/**
	 * 行番号設定.
	 * @param rowNo 行番号
	 */
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 消込額取得.
	 * @return BigDecimal 消込額
	 */
	public BigDecimal getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * 消込額設定.
	 * @param eraserAmount 消込額
	 */
	public void setEraserAmount(final BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * 登録日時を取得します。
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * 登録日時を設定します。
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ＩＤを取得します。
	 * @return String 登録者ＩＤ
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * 登録者ＩＤを設定します。
	 * @param inputorCd 登録者ＩＤ
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時を取得します。
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日時を設定します。
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ＩＤを取得します。
	 * @return String 更新者ＩＤ
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * 更新者ＩＤを設定します。
	 * @param updatorCd 更新者ＩＤ
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


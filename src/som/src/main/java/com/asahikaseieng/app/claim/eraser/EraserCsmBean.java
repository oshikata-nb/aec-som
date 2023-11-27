/*
 * Created on 2010/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 消込用 bean クラス
 * @author t0011036
 */
public class EraserCsmBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EraserCsmBean() {
	}

	//
	// インスタンスフィールド
	//

	/* 処理後消込額 */
	private BigDecimal eraserAmount;

	/* 処理前消込額 */
	private BigDecimal beforeEraserAmount;

	/* データ種別 */
	private BigDecimal dataType;

	/* 入金番号 */
	private String creditNo;

	/* 行番号 */
	private BigDecimal rowNo;

	/* 取引先区分 */
	private String venderDivision;

	/* 取引先コード */
	private String venderCd;

	//
	// インスタンスメソッド
	//

	/**
	 * creditNoを取得します。
	 * @return creditNo
	 */
	public String getCreditNo() {
		return creditNo;
	}

	/**
	 * creditNoを設定します。
	 * @param creditNo creditNo
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
	}

	/**
	 * dataTypeを取得します。
	 * @return dataType
	 */
	public BigDecimal getDataType() {
		return dataType;
	}

	/**
	 * dataTypeを設定します。
	 * @param dataType dataType
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * eraserAmountを取得します。
	 * @return eraserAmount
	 */
	public BigDecimal getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * eraserAmountを設定します。
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * rowNoを取得します。
	 * @return rowNo
	 */
	public BigDecimal getRowNo() {
		return rowNo;
	}

	/**
	 * rowNoを設定します。
	 * @param rowNo rowNo
	 */
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * beforeEraserAmountを取得します。
	 * @return beforeEraserAmount
	 */
	public BigDecimal getBeforeEraserAmount() {
		return beforeEraserAmount;
	}

	/**
	 * beforeEraserAmountを設定します。
	 * @param beforeEraserAmount beforeEraserAmount
	 */
	public void setBeforeEraserAmount(final BigDecimal beforeEraserAmount) {
		this.beforeEraserAmount = beforeEraserAmount;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}
}

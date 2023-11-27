/*
 * Created on 2021/01/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 運賃取得用Dtoクラス
 * @author tosco
 */
public final class FunGetCarryFareCallDto {
	/** Constructor */
	public FunGetCarryFareCallDto() {
	}
	/** pCarryFare_PROCEDURE_PARAMETER */
	public static final String pCarryFare_PROCEDURE_PARAMETER = "return";

	/** pParamSequenceNo_PROCEDURE_PARAMETER */
	public static final String pParamSequenceNo_PROCEDURE_PARAMETER = "in";
	
	private BigDecimal pCarryFare;
	
	private BigDecimal pParamSequenceNo;

	/**
	 * pCarryFareを取得します。
	 * @return pCarryFare
	 */
	public BigDecimal getpCarryFare() {
		return pCarryFare;
	}

	/**
	 * pCarryFareを設定します。
	 * @param pCarryFare pCarryFare
	 */
	public void setpCarryFare(BigDecimal pCarryFare) {
		this.pCarryFare = pCarryFare;
	}

	/**
	 * pParamSequenceNoを取得します。
	 * @return pParamSequenceNo
	 */
	public BigDecimal getpParamSequenceNo() {
		return pParamSequenceNo;
	}

	/**
	 * pParamSequenceNoを設定します。
	 * @param pParamSequenceNo pParamSequenceNo
	 */
	public void setpParamSequenceNo(BigDecimal pParamSequenceNo) {
		this.pParamSequenceNo = pParamSequenceNo;
	}

}

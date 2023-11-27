/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.accept;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 受入入力詳細画面_売上データ登録用データ格納クラス.
 *
 * @author tosco
 */
public class AcceptDetailSales extends AcceptDetailSalesBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション calcDivision */
	public static final String calcDivision_COLUMN = "CALC_DIVISION";

	/** COLUMNアノテーション compCalcDivision */
	public static final String compCalcDivision_COLUMN = "COMP_CALC_DIVISION";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** 取引先マスタ.算出区分 */
	private BigDecimal calcDivision;

	/** 自社マスタ.消費税算出区分 */
	private BigDecimal compCalcDivision;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/**
	 * コンストラクタ.
	 */
	public AcceptDetailSales() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */
	/**
	 * 取引先マスタ.算出区分取得
	 * @return BigDecimal 取引先マスタ.算出区分
	 */
	public BigDecimal getCalcDivision() {
		return calcDivision;
	}

	/**
	 * 取引先マスタ.算出区分設定
	 * @param calcDivision 取引先マスタ.算出区分
	 */
	public void setCalcDivision(final BigDecimal calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分取得
	 * @return BigDecimal 自社マスタ.消費税算出区分
	 */
	public BigDecimal getCompCalcDivision() {
		return compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分設定
	 * @param compCalcDivision 自社マスタ.消費税算出区分
	 */
	public void setCompCalcDivision(final BigDecimal compCalcDivision) {
		this.compCalcDivision = compCalcDivision;
	}

	/**
	 * 運用管理単位(区分)取得
	 * @return String 運用管理単位(区分)
	 */
	public String getUnitDiv() {
		return unitDiv;
	}

	/**
	 * 運用管理単位(区分)設定
	 * @param unitDiv 運用管理単位(区分)
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
	}

}

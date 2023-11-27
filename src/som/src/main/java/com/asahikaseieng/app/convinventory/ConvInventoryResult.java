/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.convinventory;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 在庫数量換算
 * @author t0011036
 */
public class ConvInventoryResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/* 在庫数量 */
	private BigDecimal inventoryQty;

	/* 荷姿数量 */
	private BigDecimal packQty;

	/* 端数重量 */
	private BigDecimal fractionWeight;

	/* 端数数量 */
	private BigDecimal fractionQty;

	/* 種別 */
	private BigDecimal typeDivision;

	/* 戻値 */
	private BigDecimal code;

	/**
	 * コンストラクタ
	 */
	public ConvInventoryResult() {

	}

	/**
	 * コンストラクタ
	 * @param code エラーコード
	 */
	public ConvInventoryResult(final BigDecimal code) {
		this.code = code;
	}

	/**
	 * codeを取得します。
	 * @return code
	 */
	public BigDecimal getCode() {
		return code;
	}

	/**
	 * codeを設定します。
	 * @param code code
	 */
	public void setCode(final BigDecimal code) {
		this.code = code;
	}

	/**
	 * packQtyを取得します。
	 * @return packQty
	 */
	public BigDecimal getPackQty() {
		return packQty;
	}

	/**
	 * packQtyを設定します。
	 * @param packQty packQty
	 */
	public void setPackQty(final BigDecimal packQty) {
		this.packQty = packQty;
	}

	/**
	 * inventoryQtyを取得します。
	 * @return inventoryQty
	 */
	public BigDecimal getInventoryQty() {
		return inventoryQty;
	}

	/**
	 * inventoryQtyを設定します。
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * fractionQtyを取得します。
	 * @return fractionQty
	 */
	public BigDecimal getFractionQty() {
		return fractionQty;
	}

	/**
	 * fractionQtyを設定します。
	 * @param fractionQty fractionQty
	 */
	public void setFractionQty(final BigDecimal fractionQty) {
		this.fractionQty = fractionQty;
	}

	/**
	 * fractionWeightを取得します。
	 * @return fractionWeight
	 */
	public BigDecimal getFractionWeight() {
		return fractionWeight;
	}

	/**
	 * fractionWeightを設定します。
	 * @param fractionWeight fractionWeight
	 */
	public void setFractionWeight(final BigDecimal fractionWeight) {
		this.fractionWeight = fractionWeight;
	}

	/**
	 * typeDivisionを取得します。
	 * @return typeDivision
	 */
	public BigDecimal getTypeDivision() {
		return typeDivision;
	}

	/**
	 * typeDivisionを設定します。
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}
}

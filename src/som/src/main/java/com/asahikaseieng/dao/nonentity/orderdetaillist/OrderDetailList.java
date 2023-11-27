/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetaillist;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * OrderDetailListクラス.
 * @author kanri-user
 */
public class OrderDetailList extends OrderDetailListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;
	
	/** COLUMNアノテーション maxRow */
	public static final String maxRow_COLUMN = "MAX_ROW";

	// チェックボックス
	private Boolean checkline;

	private String strItemName;

	private String strStyleOfPacking;

	/** 受注数量(String) */
	private String strOrderQty;

	/** 受注単価(String) */
	private String strOrderUnitprice;

	/** 標準単価(String) */
	private String strStandardUnitprice;

	/** 標準値引(String) */
	private String strStandardDiscount;

	/** 特別値引(String) */
	private String strSpecialDiscount;

	/** 金額(String) */
	private String strOrderAmount;

	/** 増付数(String) */
	private String strMatss;

	/** 小数点桁数 */
	private String itemSmallnumLength;

	/** 端数区分 */
	private String itemRoundDivision;

	/** 得意先コード */
	private String venderCd;

	private String strEstimateStandardAmount;

	private String strEstimateMatss;
	
	/** 最大行数 */
	private BigDecimal maxRow;

	/**
	 * コンストラクタ.
	 */
	public OrderDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* Stringに変換 */
		if (getEstimateStandardAmount() != null) {
			setStrEstimateStandardAmount(getEstimateStandardAmount().toString());
		}
		if (getEstimateMatss() != null) {
			setStrEstimateMatss(getEstimateMatss().toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * checklineを取得します。
	 * @return checkline
	 */
	public Boolean getCheckline() {
		return checkline;
	}

	/**
	 * checklineを設定します。
	 * @param checkline checkline
	 */
	public void setCheckline(final Boolean checkline) {
		this.checkline = checkline;
	}

	/**
	 * strItemNameを取得します。
	 * @return strItemName
	 */
	public String getStrItemName() {
		return strItemName;
	}

	/**
	 * strItemNameを設定します。
	 * @param strItemName strItemName
	 */
	public void setStrItemName(final String strItemName) {
		this.strItemName = strItemName;
	}

	/**
	 * strStyleOfPackingを取得します。
	 * @return strStyleOfPacking
	 */
	public String getStrStyleOfPacking() {
		return strStyleOfPacking;
	}

	/**
	 * strStyleOfPackingを設定します。
	 * @param strStyleOfPacking strStyleOfPacking
	 */
	public void setStrStyleOfPacking(final String strStyleOfPacking) {
		this.strStyleOfPacking = strStyleOfPacking;
	}

	/**
	 * 仮単価フラグを取得します
	 * @return blnTmpUnitpriceFlg
	 */
	public Boolean getBlnTmpUnitpriceFlg() {

		switch (getTmpUnitpriceFlg().intValue()) {
		case 1:
			return true;

		default:
			return false;

		}
	}

	/**
	 * 受注数量(String)を取得します。
	 * @return strOrderQty
	 */
	public String getStrOrderQty() {
		return strOrderQty;
	}

	/**
	 * 受注数量(String)を設定します。
	 * @param strOrderQty 受注数量(String)
	 */
	public void setStrOrderQty(final String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	/**
	 * 特別値引(String)を取得します。
	 * @return strSpecialDiscount
	 */
	public String getStrSpecialDiscount() {
		return strSpecialDiscount;
	}

	/**
	 * 特別値引(String)を設定します。
	 * @param strSpecialDiscount 特別値引(String)
	 */
	public void setStrSpecialDiscount(final String strSpecialDiscount) {
		this.strSpecialDiscount = strSpecialDiscount;
	}

	/**
	 * 標準値引(String)を取得します。
	 * @return strStandardDiscount
	 */
	public String getStrStandardDiscount() {
		return strStandardDiscount;
	}

	/**
	 * 標準値引(String)を設定します。
	 * @param strStandardDiscount 標準値引(String)
	 */
	public void setStrStandardDiscount(final String strStandardDiscount) {
		this.strStandardDiscount = strStandardDiscount;
	}

	/**
	 * 標準単価(String)を取得します。
	 * @return strStandardUnitprice
	 */
	public String getStrStandardUnitprice() {
		return strStandardUnitprice;
	}

	/**
	 * 標準単価(String)を設定します。
	 * @param strStandardUnitprice 標準単価(String)
	 */
	public void setStrStandardUnitprice(final String strStandardUnitprice) {
		this.strStandardUnitprice = strStandardUnitprice;
	}

	/**
	 * 金額(String)を取得します。
	 * @return strOrderAmount
	 */
	public String getStrOrderAmount() {
		return strOrderAmount;
	}

	/**
	 * 金額(String)を設定します。
	 * @param strOrderAmount 金額(String)
	 */
	public void setStrOrderAmount(final String strOrderAmount) {
		this.strOrderAmount = strOrderAmount;
	}

	/**
	 * 増付数(String)を取得します。
	 * @return strMatss
	 */
	public String getStrMatss() {
		return strMatss;
	}

	/**
	 * 増付数(String)を設定します。
	 * @param strMatss 増付数(String)
	 */
	public void setStrMatss(final String strMatss) {
		this.strMatss = strMatss;
	}

	/**
	 * 端数区分を取得します。
	 * @return itemRoundDivision
	 */
	public String getItemRoundDivision() {
		return itemRoundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param itemRoundDivision 端数区分
	 */
	public void setItemRoundDivision(final String itemRoundDivision) {
		this.itemRoundDivision = itemRoundDivision;
	}

	/**
	 * 小数点桁数を取得します。
	 * @return itemSmallnumLength
	 */
	public String getItemSmallnumLength() {
		return itemSmallnumLength;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param itemSmallnumLength 小数点桁数
	 */
	public void setItemSmallnumLength(final String itemSmallnumLength) {
		this.itemSmallnumLength = itemSmallnumLength;
	}

	/**
	 * 得意先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 得意先コードを設定します。
	 * @param venderCd 得意先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 増付数を取得します。
	 * @return strEstimateMatss
	 */
	public String getStrEstimateMatss() {
		return strEstimateMatss;
	}

	/**
	 * 増付数を設定します。
	 * @param strEstimateMatss 増付数
	 */
	public void setStrEstimateMatss(final String strEstimateMatss) {
		this.strEstimateMatss = strEstimateMatss;
	}

	/**
	 * 基準数量を取得します。
	 * @return strEstimateStandardAmount
	 */
	public String getStrEstimateStandardAmount() {
		return strEstimateStandardAmount;
	}

	/**
	 * 基準数量を設定します。
	 * @param strEstimateStandardAmount 基準数量
	 */
	public void setStrEstimateStandardAmount(
			final String strEstimateStandardAmount) {
		this.strEstimateStandardAmount = strEstimateStandardAmount;
	}

	/**
	 * 基準数量を取得します。
	 * @return strOrderUnitprice
	 */
	public String getStrOrderUnitprice() {
		return strOrderUnitprice;
	}

	/**
	 * 受注単価を設定します。
	 * @param strOrderUnitprice 受注単価
	 */
	public void setStrOrderUnitprice(final String strOrderUnitprice) {
		this.strOrderUnitprice = strOrderUnitprice;
	}

	/**
	 * maxRowを取得します。
	 * @return maxRow
	 */
	public BigDecimal getMaxRow() {
		return maxRow;
	}

	/**
	 * maxRowを設定します。
	 * @param maxRow maxRow
	 */
	public void setMaxRow(BigDecimal maxRow) {
		this.maxRow = maxRow;
	}
	
}
